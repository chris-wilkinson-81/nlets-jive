package com.wth.nlets.v3.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.jivesoftware.api.core.aaa.RequiresAuthentication;
import com.jivesoftware.base.User;
import com.jivesoftware.base.UserManager;
import com.jivesoftware.community.Community;
import com.jivesoftware.community.CommunityManager;
import com.jivesoftware.community.EntityDescriptor;
import com.jivesoftware.community.NotFoundException;
import com.jivesoftware.community.eae.streams.StreamManager;
import com.jivesoftware.eae.service.client.api.Stream;
import com.jivesoftware.eae.service.client.api.StreamConfiguration;
import com.wth.nlets.template.ContainerStreamConfig;

//import edu.emory.mathcs.backport.java.util.Collections;

@Path("/streams")
@Produces("application/json")
public class StreamService extends com.jivesoftware.api.core.v3.services.activity.StreamService {
	
	private static final Logger logger = Logger.getLogger(StreamService.class);
	
	private CommunityManager communityManager;
	private StreamManager streamManager;
	private UserManager userManager;
	private ContainerStreamConfigProvider containerStreamConfigProvider;	
	
	@GET
	@Path("/community/{communityID}/user/{userID}/streamconfig")
	@RequiresAuthentication
	public Response getContainerStreamConfig(@PathParam("communityID") long communityID, @PathParam("userID") long userID) {
		
		try {
			Community community = this.communityManager.getCommunity(communityID);
			User user = this.userManager.getUser(userID);
			
			//load the user's streams
	        List<ContainerStreamConfig> streamList = containerStreamConfigProvider.getContainerStreamConfiguration(user, community);
	        return Response.ok(streamList, MediaType.APPLICATION_JSON).build();
		} catch (NotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	private List<ContainerStreamConfig> manageAssociations(long communityID, long streamID, boolean addAssociation) throws NotFoundException {
		Stream stream = new Stream(streamID);
        StreamConfiguration streamConfig = streamManager.getStream(stream);
		Community community = this.communityManager.getCommunity(communityID);

		if(addAssociation) {
			this.streamManager.addAssociation(streamConfig, new EntityDescriptor(community));
			
			for(Community subCommunity: communityManager.getRecursiveCommunities(community)) {
				this.streamManager.addAssociation(streamConfig, new EntityDescriptor(subCommunity));
			}
		} else {
			this.streamManager.removeAssociation(streamConfig, new EntityDescriptor(community));
			
			for(Community subCommunity: communityManager.getRecursiveCommunities(community)) {
				this.streamManager.removeAssociation(streamConfig, new EntityDescriptor(subCommunity));
			}
		}
		
		List<ContainerStreamConfig> streamList = containerStreamConfigProvider.getContainerStreamConfiguration(this.userManager.getUser(streamConfig.getUser().getUserId()), community);
		return streamList;
	}
	
	@POST
    @Path("/followsubspaces/{communityID}/stream/{streamID}")
    @RequiresAuthentication
	public Response followSubspaces(@PathParam("communityID") long communityID, @PathParam("streamID") long streamID) {
		try {
			return Response.ok(manageAssociations(communityID, streamID, true)).build();
		} catch (NotFoundException e) {
			logger.error(String.format("Unable to find community %d",communityID),e);
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@POST
    @Path("/unfollowsubspaces/{communityID}/stream/{streamID}")
    @RequiresAuthentication
	public Response unFollowSubspaces(@PathParam("communityID") long communityID, @PathParam("streamID") long streamID) {
		try {
			return Response.ok(manageAssociations(communityID, streamID, false)).build();
		} catch (NotFoundException e) {
			logger.error(String.format("Unable to find community %d",communityID),e);
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@POST
    @Path("/unfollowsubspaces/{communityID}/user/{userID}")
    @RequiresAuthentication
	public Response unFollowSubspacesForUser(@PathParam("communityID") long communityID, @PathParam("userID") long userID) {
		try {
			User user = this.userManager.getUser(userID);
			List<ContainerStreamConfig> streamList = new ArrayList<ContainerStreamConfig>();
			for(StreamConfiguration sc: this.streamManager.getUserStreams(user)) {
				streamList = manageAssociations(communityID, sc.getStream().getStreamId(), false);
			}
			return Response.ok(streamList).build();
		} catch (NotFoundException e) {
			logger.error(String.format("Unable to find community %d",communityID),e);
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	public void setCommunityManager(CommunityManager communityManager) {
		this.communityManager = communityManager;
	}

	public void setStreamManager(StreamManager streamManager) {
		this.streamManager = streamManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
	public void setContainerStreamConfigProvider(
			ContainerStreamConfigProvider containerStreamConfigProvider) {
		this.containerStreamConfigProvider = containerStreamConfigProvider;
	}
}
