package org.esco.portlet.service;

import javax.portlet.PortletRequest;

import java.util.List;

import org.esco.portlet.model.FlashInfo;

/**
 * Created by jgribonvald on 14/09/16.
 */
public interface IFlashInfoService {

    List<FlashInfo> retrieveFlashInfos(final PortletRequest request);

}
