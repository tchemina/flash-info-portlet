package org.esco.portlet.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.esco.portlet.model.FlashInfo;
import org.esco.portlet.model.FlashInfoList;
import org.esco.portlet.service.IUserService;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class UserServiceImpl implements IUserService {

	private String guestServiceUrl;
	private String etabServiceUrl;
	protected final Log logger = LogFactory.getLog(getClass());
	@Override
	public FlashInfoList retrieveGuestFlashInfo() {
			return this.getServiceInfos(this.guestServiceUrl);
	}
	@Override
	public FlashInfoList retrieveEtabInfos(String escouai) {
		String getUrl = this.etabServiceUrl+"?escouai="+escouai;
		 return this.getServiceInfos(getUrl);
	}

	public String getGuestServiceUrl() {
		return guestServiceUrl;
	}

	public void setGuestServiceUrl(String guestServiceUrl) {
		this.guestServiceUrl = guestServiceUrl;
	}

	public String getEtabServiceUrl() {
		return etabServiceUrl;
	}

	public void setEtabServiceUrl(String etabServiceUrl) {
		this.etabServiceUrl = etabServiceUrl;
	}

	private FlashInfoList getServiceInfos(String url) {
		FlashInfoList flL = new FlashInfoList();
		String json = null;
		try {
			json = IOUtils.toString(new URL(url));
		} catch (MalformedURLException e) {
			logger.error("MalformedURLException parsing json object :" +e.getMessage());
		} catch (IOException e) {
			logger.error("IOException parsing json object :" +e.getMessage());
		}
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(json);
		if (element.isJsonObject()) {
		    JsonObject flashinfos = element.getAsJsonObject();
		    JsonObject rootflashinfos = flashinfos.getAsJsonObject("flashinfos");
		    JsonArray datasets = rootflashinfos.getAsJsonArray("flashinfos");
		    for (int i = 0; i < datasets.size(); i++) {
		    	String active = "";
		    	if(i==0){
		    		active = "active";
		    	}
		        JsonObject dataset = datasets.get(i).getAsJsonObject();
		        String imgL = dataset.get("imgLink").getAsString();
		        String title = dataset.get("title").getAsString();
		        String text = dataset.get("text").getAsString();
		        String kml = dataset.get("knowMoreLink").getAsString();
		        // TODO une alt pour les liens d'image
		        String alt = "image"+i;
		        FlashInfo info = new FlashInfo(imgL, title, text, kml, alt, active);
		        flL.add(info);
		    }
		}
		return flL;
	}
}