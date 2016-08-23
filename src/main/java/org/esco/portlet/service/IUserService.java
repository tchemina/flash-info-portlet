package org.esco.portlet.service;

import org.esco.portlet.model.FlashInfoList;

public interface IUserService {


	FlashInfoList retrieveInfos(String escouai, String flashUrl);

	
}
