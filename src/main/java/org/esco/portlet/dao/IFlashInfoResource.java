package org.esco.portlet.dao;

import java.util.List;

import org.esco.portlet.model.FlashInfo;

/**
 * Created by jgribonvald on 13/09/16.
 */
public interface IFlashInfoResource {

    List<FlashInfo> retrieveInfos(final String flashUrl) ;
}
