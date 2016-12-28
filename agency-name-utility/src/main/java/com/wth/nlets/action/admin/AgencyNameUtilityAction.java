package com.wth.nlets.action.admin;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jivesoftware.base.User;
import com.jivesoftware.base.UserNotFoundException;
import com.jivesoftware.community.JiveGlobals;
import com.jivesoftware.community.action.admin.AdminActionSupport;
import com.jivesoftware.community.user.profile.ProfileField;
import com.jivesoftware.community.user.profile.ProfileFieldManager;
import com.jivesoftware.community.user.profile.ProfileFieldOption;
import com.jivesoftware.community.user.profile.ProfileFieldValue;
import com.jivesoftware.community.user.profile.ProfileManager;

public class AgencyNameUtilityAction extends AdminActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6070246563301707858L;
	
	private static final String DEFAULT_AGENCIES_APPLIED_PROP = "nlets.default.agencies.applied";
	private static Map<String,String> DEFAULT_AGENCIES = Maps.newHashMap();
	
	static {
		DEFAULT_AGENCIES.put("AL", "Alabama Criminal Justice Information Center");
		DEFAULT_AGENCIES.put("AK", "Alaska Department of Public Safety, Alaska Records and Identification Bureau");
		DEFAULT_AGENCIES.put("AZ", "Arizona Department of Public Safety");
		DEFAULT_AGENCIES.put("AR", "Arkansas Crime Information Center");
		DEFAULT_AGENCIES.put("HQ", "Bureau of Counsular Affairs");
		DEFAULT_AGENCIES.put("BJ", "Bureau of Justice Statistics");
		DEFAULT_AGENCIES.put("BP", "Bureau of Prisons");
		DEFAULT_AGENCIES.put("CN", "Calgary Police Service");
		DEFAULT_AGENCIES.put("CA", "California Department of Justice");
		DEFAULT_AGENCIES.put("CW", "Capital Wireless Information Network");
		DEFAULT_AGENCIES.put("CO", "Colorado Bureau of Investigation");
		DEFAULT_AGENCIES.put("CT", "Connecticut Department of Public Safety");
		DEFAULT_AGENCIES.put("DE", "Delaware State Police");
		DEFAULT_AGENCIES.put("EP", "El Paso Intelligence Center");
		DEFAULT_AGENCIES.put("FA", "Federal Aviation Administration");
		DEFAULT_AGENCIES.put("FB", "Federal Bureau of Investigation");
		DEFAULT_AGENCIES.put("FM", "Federal Motor Carrier Safety Administration");
		DEFAULT_AGENCIES.put("FP", "Federal Protective Service");
		DEFAULT_AGENCIES.put("FL", "Florida Department of Law Enforcement");
		DEFAULT_AGENCIES.put("GA", "Georgia Bureau of Investigation");
		DEFAULT_AGENCIES.put("GU", "Guam Attorney General's Office, Guam Courts, Guam Police Department, Judiciary of Guam");
		DEFAULT_AGENCIES.put("HI", "Hawaii Criminal Justice Data Center");
		DEFAULT_AGENCIES.put("HL", "HELP Inc");
		DEFAULT_AGENCIES.put("ID", "Idaho State Police");
		DEFAULT_AGENCIES.put("IL", "Illinois State Police");
		DEFAULT_AGENCIES.put("IA", "Iowa Department of Public Safety");
		DEFAULT_AGENCIES.put("IN", "Indiana State Police");
		DEFAULT_AGENCIES.put("IP", "Interpol");
		DEFAULT_AGENCIES.put("KS", "Kansas Bureau of Investigation, Kansas Highway Patrol, Kansas Information Services Division, Kansas State Police");
		DEFAULT_AGENCIES.put("KY", "Kentucky State Police");
		DEFAULT_AGENCIES.put("LA", "Louisiana Department of Public Safety, Louisiana State Police");
		DEFAULT_AGENCIES.put("ME", "Maine State Police");
		DEFAULT_AGENCIES.put("MD", "Maryland State Police");
		DEFAULT_AGENCIES.put("MA", "Massachusetts DCJIS, Massachusetts Executive Office");
		DEFAULT_AGENCIES.put("DC", "Metropolitan Police Department");
		DEFAULT_AGENCIES.put("MI", "Michigan State Police");
		DEFAULT_AGENCIES.put("MN", "Minnesota Bureau of Criminal Apprehension");
		DEFAULT_AGENCIES.put("MS", "Mississippi Department of Public Safety");
		DEFAULT_AGENCIES.put("MO", "Missouri State Highway Patrol");
		DEFAULT_AGENCIES.put("MT", "Montana Department of Justice");
		DEFAULT_AGENCIES.put("MV", "MVTRAC Federal Crime Bureau");
		DEFAULT_AGENCIES.put("MC", "National Center for Missining Exploited Children");
		DEFAULT_AGENCIES.put("NA", "National Insurance Crime Bureau");
		DEFAULT_AGENCIES.put("VS", "National Vehicle Service");
		DEFAULT_AGENCIES.put("WS", "National Weather Service");
		DEFAULT_AGENCIES.put("NB", "Nebraska State Police");
		DEFAULT_AGENCIES.put("NV", "Nevada Department of Public Safety");
		DEFAULT_AGENCIES.put("NH", "New Hampshire Department of Public Safety, New Hampshire State Police");
		DEFAULT_AGENCIES.put("NJ", "New Jersey State Police");
		DEFAULT_AGENCIES.put("NM", "New Mexico Department of Public Safety");
		DEFAULT_AGENCIES.put("NY", "New York State Police");
		DEFAULT_AGENCIES.put("NY", "New York State Troopers");
		DEFAULT_AGENCIES.put("NL", "Nlets");
		DEFAULT_AGENCIES.put("NC", "North Carolina State Bureau of Investigation");
		DEFAULT_AGENCIES.put("ND", "North Dakota Bureau of Criminal Investigation, North Dakota State Radio");
		DEFAULT_AGENCIES.put("OH", "Ohio State Highway Patrol");
		DEFAULT_AGENCIES.put("OK", "Oklahoma LETS");
		DEFAULT_AGENCIES.put("OR", "Oregon State Police");
		DEFAULT_AGENCIES.put("PA", "Pennsylvania State Police");
		DEFAULT_AGENCIES.put("PR", "Puerto Rico CJIS");
		DEFAULT_AGENCIES.put("RI", "Rhode Island State Police");
		DEFAULT_AGENCIES.put("RS", "RISS");
		DEFAULT_AGENCIES.put("CN", "Royal Canadian Mounted Police");
        DEFAULT_AGENCIES.put("RJ", "San Diego ARJIS");
        DEFAULT_AGENCIES.put("SD", "South Dakota Department of Public Safety");
        DEFAULT_AGENCIES.put("TN", "Tennessee Bureau of Investigation");
        DEFAULT_AGENCIES.put("TX", "Texas Department of Public Safety");
        DEFAULT_AGENCIES.put("AI", "US Air Force");
        DEFAULT_AGENCIES.put("DA", "US Army");
        DEFAULT_AGENCIES.put("CG", "US Coast Guard");
        DEFAULT_AGENCIES.put("UC", "US Courts");
        DEFAULT_AGENCIES.put("DP", "US Department of Defense");
        DEFAULT_AGENCIES.put("AX", "US DHS Customs and Border Protection, US DHS Immigration and Customs Enforcement");
        DEFAULT_AGENCIES.put("IG", "US DHS Office of Inspector General");
        DEFAULT_AGENCIES.put("TS", "US DHS Transportation Security Admin");
        DEFAULT_AGENCIES.put("DI", "US Department of Interior");
        DEFAULT_AGENCIES.put("DJ", "US Department of Justice");
        DEFAULT_AGENCIES.put("DS", "US Department of State");
        DEFAULT_AGENCIES.put("DV", "US Department of Veteran's Affairs");
        DEFAULT_AGENCIES.put("DN", "US Navy NCIS");
        DEFAULT_AGENCIES.put("OP", "US Office of Personnel Management");
        DEFAULT_AGENCIES.put("PS", "US Postal Inspection Service");
        DEFAULT_AGENCIES.put("SS", "US Secret Service");
        DEFAULT_AGENCIES.put("SA", "US Social Security Administration");
        DEFAULT_AGENCIES.put("UT", "Utah Bureau of Criminal Identification, Utah Department of Public Safety");
        DEFAULT_AGENCIES.put("VT", "Vermont Department of Public Safety, Vermont State Police");
        DEFAULT_AGENCIES.put("VI", "Virgin Islands Police Department");
        DEFAULT_AGENCIES.put("VA", "Virginia Criminal Justice Information Serivce, Virginia State Police");
        DEFAULT_AGENCIES.put("WA", "Washington State Patrol");
        DEFAULT_AGENCIES.put("WN", "Western Identification Network");
        DEFAULT_AGENCIES.put("WV", "West Virginia State Police");
        DEFAULT_AGENCIES.put("WI", "Wisconsin Crime Information Bureau, Wisconsin Department of Justice");
        DEFAULT_AGENCIES.put("WY", "Wyoming Division of Criminal Investigation");
	}

	private String stateCode;
	private String agencyNames;
	private boolean success;
	private int numAffectedUsers;
	
	private ProfileFieldManager profileFieldManager;
	private ProfileManager profileManager;
	
	public String input() {
		if(!JiveGlobals.getJiveBooleanProperty(DEFAULT_AGENCIES_APPLIED_PROP)) {
			applyDefaultAgencies();
		}
		
		return INPUT;
	}
	
	public String execute() {
		applyAgencies(this.stateCode, this.agencyNames);
		return SUCCESS;
	}

	private void applyAgencies(String locationCode, String agencyNames) {
		ProfileField locationField = this.profileFieldManager.getProfileField("Location");
		ProfileField departmentField = this.profileFieldManager.getProfileField("Department");
		
		for(Long userID :this.userManager.getApplicationUserIDs()) {
			
			try {
				User applicationUser = this.userManager.getUser(userID);
				Map<Long,ProfileFieldValue> profile = profileManager.getProfile(applicationUser);
				
				if(isInteresting(profile, locationField, locationCode)) {
					saveProfileField(applicationUser, profile, departmentField, agencyNames);
				}
			} catch (UserNotFoundException e) {
				//swallow it
				//won't happen
			}
		}
	}
	
	private void applyDefaultAgencies() {
		ProfileField departmentField = this.profileFieldManager.getProfileField("Department");
		assertIsSingleListField(departmentField);
		
		for(String stateCode : DEFAULT_AGENCIES.keySet()) {
			this.applyAgencies(stateCode, DEFAULT_AGENCIES.get(stateCode));
		}
		
		JiveGlobals.setJiveProperty(DEFAULT_AGENCIES_APPLIED_PROP,true);
	}
	
	private void assertIsSingleListField(ProfileField departmentField) {
		if(departmentField.getType() != ProfileField.Type.SINGLELIST) {
			departmentField.setTypeID(ProfileField.Type.SINGLELIST.getID());
			this.profileFieldManager.editProfileField(departmentField);
		}
		
		List<ProfileFieldOption> pfos = Lists.newArrayList();
		for(String key : DEFAULT_AGENCIES.keySet()) {
			String deptartments = DEFAULT_AGENCIES.get(key);
			for(String dept: deptartments.split(",")) {
				ProfileFieldOption pfo = new ProfileFieldOption();
				pfo.setFieldID(departmentField.getID());
				pfo.setValue(dept.trim());
				pfo.setDefaultOption(false);
				pfos.add(pfo);
			}
			
		}
		
		Collections.sort(pfos, new Comparator<ProfileFieldOption>(){

			@Override
			public int compare(ProfileFieldOption o1, ProfileFieldOption o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
			
		});
		
		int fieldIdx = 0;
		for(ProfileFieldOption pfo: pfos) {
			pfo.setIndex(fieldIdx++);
		}
		
		departmentField.setOptions(pfos);
		this.profileFieldManager.editProfileFieldOptions(departmentField);
	}
	
	private boolean isInteresting(Map<Long,ProfileFieldValue> profile, ProfileField field, String locationCode) {
		if(profile.containsKey(field.getID())) {
			String userLocation = profile.get(field.getID()).getValue();
			return userLocation.equals(locationCode);
		}
		
		return false;
	}
	
	private void saveProfileField(User user, Map<Long,ProfileFieldValue> profile, ProfileField field, String values) {

		String[] valuesArray = values.split(",");
		String value = "";
		
		if(valuesArray.length == 1) {
			value = valuesArray[0];
		} else {
			return;
		}
		
		ProfileFieldValue pfv = null;
		if(profile.containsKey(field.getID())) {
			pfv = profile.get(field.getID());
		} else {
			pfv = new ProfileFieldValue();
    		pfv.setFieldID(field.getID());
    		pfv.setTypeID(ProfileField.Type.TEXT.getID());
		}
		
		pfv.setValue(value);
		profile.put(field.getID(), pfv);

        profileManager.setProfile(user, profile.values(), Maps.newHashMap());
	}
	
	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getAgencyNames() {
		return agencyNames;
	}

	public void setAgencyNames(String agencyNames) {
		this.agencyNames = agencyNames;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getNumAffectedUsers() {
		return numAffectedUsers;
	}

	public void setNumAffectedUsers(int numAffectedUsers) {
		this.numAffectedUsers = numAffectedUsers;
	}

	public void setProfileFieldManager(ProfileFieldManager profileFieldManager) {
		this.profileFieldManager = profileFieldManager;
	}

	public void setProfileManager(ProfileManager profileManager) {
		this.profileManager = profileManager;
	}
}
