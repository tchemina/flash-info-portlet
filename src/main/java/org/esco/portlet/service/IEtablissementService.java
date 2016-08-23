package org.esco.portlet.service;

import org.esco.portlet.model.FlashInfoList;

public interface IEtablissementService {

	FlashInfoList retrieveInfos(String escouai, String flashUrl);

}
