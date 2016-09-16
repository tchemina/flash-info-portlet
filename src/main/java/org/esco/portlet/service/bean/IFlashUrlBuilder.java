package org.esco.portlet.service.bean;

import javax.portlet.PortletRequest;

/**
 * Created by jgribonvald on 15/09/16.
 */
public interface IFlashUrlBuilder {

    String transform(final PortletRequest request, final String url);
}
