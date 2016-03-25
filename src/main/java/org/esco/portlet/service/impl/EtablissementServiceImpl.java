package org.esco.portlet.service.impl;

import org.esco.portlet.model.FlashInfoList;
import org.esco.portlet.service.IEtablissementService;
import org.esco.portlet.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class EtablissementServiceImpl implements IEtablissementService {

	@Autowired
	private IUserService userService;
	@Override
	public FlashInfoList retrieveInfos(String escouai) {
		if (escouai==null || escouai.equals("") || escouai.equals("guest")){
			return this.userService.retrieveGuestFlashInfo();
		}else{
			return this.userService.retrieveEtabInfos(escouai);
		}
		
	}

}
