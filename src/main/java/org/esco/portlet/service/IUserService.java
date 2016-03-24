package org.esco.portlet.service;

import org.esco.portlet.model.FlashInfoList;

public interface IUserService {

	FlashInfoList retrieveGuestFlashInfo();

	FlashInfoList retrieveEtabInfos(String escouai);

	
}
