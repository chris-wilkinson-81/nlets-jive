package com.wth.nlets.navbar;

import com.jivesoftware.base.User;
import com.jivesoftware.base.aaa.AuthenticationProvider;
import com.jivesoftware.community.navbar.NavMenuLink;
import com.jivesoftware.community.web.GlobalResourceResolver;

public class MyProfileMenuLink extends NavMenuLink {

	private AuthenticationProvider authenticationProvider;
	private GlobalResourceResolver globalResourceResolver; 
	
	@Override
	public boolean isVisible() {
		return true;
	}
	
	@Override
	public String getUrl() {
		User user = this.authenticationProvider.getJiveUser();
		return globalResourceResolver.getURL(user, false);
	}

	public void setAuthenticationProvider(
			AuthenticationProvider authenticationProvider) {
		this.authenticationProvider = authenticationProvider;
	}

	public void setGlobalResourceResolver(
			GlobalResourceResolver globalResourceResolver) {
		this.globalResourceResolver = globalResourceResolver;
	}
}
