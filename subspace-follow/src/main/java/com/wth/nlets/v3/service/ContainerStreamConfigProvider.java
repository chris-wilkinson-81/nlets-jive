package com.wth.nlets.v3.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jivesoftware.base.User;
import com.jivesoftware.community.Community;
import com.jivesoftware.community.EntityDescriptor;
import com.jivesoftware.community.eae.streams.StreamManager;
import com.jivesoftware.eae.service.client.api.StreamConfiguration;
import com.wth.nlets.template.ContainerStreamConfig;

public class ContainerStreamConfigProvider {
	
	private StreamManager streamManager;
	
	public List<ContainerStreamConfig> getContainerStreamConfiguration(User user, Community community) {
		List<StreamConfiguration> streamConfigurations = this.streamManager.getUserStreams(user);
		List<ContainerStreamConfig> result = new ArrayList<ContainerStreamConfig>();
        for(StreamConfiguration sc : streamConfigurations) {
        	boolean following = this.streamManager.isAssociated(user, new EntityDescriptor(community),sc.getSource());
        	result.add(new ContainerStreamConfig(sc,following));
        }
        
        Collections.sort(result,new Comparator<ContainerStreamConfig>(){
        	public int compare(ContainerStreamConfig cs1, ContainerStreamConfig cs2) {
        		return cs1.getDisplayName().compareTo(cs2.getDisplayName());
        	}
        });
        
        return result;
	}

	public void setStreamManager(StreamManager streamManager) {
		this.streamManager = streamManager;
	}
}
