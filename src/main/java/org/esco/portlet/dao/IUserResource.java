package org.esco.portlet.dao;

import javax.portlet.PortletRequest;

import java.util.List;

public interface IUserResource {

	List<String> getUserInfo(final PortletRequest request, final String attributeName);
}
