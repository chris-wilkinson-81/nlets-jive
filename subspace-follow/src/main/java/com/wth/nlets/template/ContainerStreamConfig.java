package com.wth.nlets.template;

import org.apache.cxf.common.util.StringUtils;

import com.jivesoftware.eae.service.client.api.StreamConfiguration;

public class ContainerStreamConfig {
	private StreamConfiguration streamConfiguration;
	private boolean following;
	
	public ContainerStreamConfig(StreamConfiguration streamConfiguration,boolean following) {
		this.streamConfiguration = streamConfiguration;
		this.following = following;
	}
	
	public StreamConfiguration getStreamConfiguration() {
		return streamConfiguration;
	}
	public void setStreamConfiguration(StreamConfiguration streamConfiguration) {
		this.streamConfiguration = streamConfiguration;
	}
	public boolean isFollowing() {
		return following;
	}
	public void setFollowing(boolean following) {
		this.following = following;
	}
	
	public String getDisplayName() {
		String result = "Unknown";
		
		if(StringUtils.isEmpty(streamConfiguration.getName())) {
			if(streamConfiguration.getSource().getName().equalsIgnoreCase("connections")) {
				result = "Connections Stream";
			} else if (streamConfiguration.getSource().getName().equalsIgnoreCase("communications")) {
				result = "Inbox";
			}
		} else {
			result = streamConfiguration.getName();
		}
		
		return result;
	}
}
