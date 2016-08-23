package org.esco.portlet.service;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.portlet.context.PortletRequestAttributes;

public class PortletService {

	private static final Log LOG = LogFactory.getLog(PortletService.class);

	public static String getPreference(final String name){
		if (LOG.isDebugEnabled()) {
			LOG.debug("getPreference(" + name + ")");
		}
		String value="";
		try {
			final PortletRequest request = ((PortletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
			final PortletPreferences portletPreferences = request.getPreferences();
			value = portletPreferences.getValue(name, "default");
		} catch (Exception e) {
			LOG.error("Exception getting portlet url for "+name+"");
		}
		return value;

	}
	public static String getAttributPortletUrl(String flashUrl){
		String userAttribute = flashUrl.substring(flashUrl.indexOf("{")+1, flashUrl.indexOf("}"));
		return userAttribute;
	}
	public static  String getAttributeToReplace(String flashUrl){
		return flashUrl.substring(flashUrl.indexOf("{"), flashUrl.indexOf("}")+1);
	}
	public static String getFlashUrl(){
		return getPreference("flashUrl");
	}
	public static String getFlashUrl(String value, String flashUrl){
		String ret = flashUrl.replace(getAttributeToReplace(flashUrl), value);
		return ret;
	}
}
