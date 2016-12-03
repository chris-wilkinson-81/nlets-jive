package com.wth.nlets.community.user.rest.impl;

import java.util.Collection;

import com.jivesoftware.community.browse.rest.impl.ItemsViewBean;
import com.jivesoftware.community.user.rest.UserItemBean;
import com.jivesoftware.community.user.rest.impl.UserServiceImpl;

public class NletsUserServiceImpl extends UserServiceImpl {

	@Override
    public ItemsViewBean<UserItemBean> getUsers(String userIDStr, int containerType, long containerID,
            String filterGroupID, Collection<String> filterIDs, String sortKey, int sortOrder, int start,
            int numResults, String query, long activityTime, String token, Collection<String> propertyNames) {
		
		ItemsViewBean<UserItemBean> result = super.getUsers(
			userIDStr, containerType, containerID, filterGroupID, 
			filterIDs, sortKey, sortOrder, start, numResults, query, 
			activityTime, token, propertyNames);
	
		return result;
	}
}
