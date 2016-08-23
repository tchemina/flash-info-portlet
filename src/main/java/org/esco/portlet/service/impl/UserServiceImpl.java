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
		}catch (Exception e){
			logger.error("Exception parsing url : "+e.getMessage());
		}
		JsonParser parser = new JsonParser();
		JsonElement element = null;
		try {
			element = parser.parse(json);
		} catch (NullPointerException e) {
			logger.error("NullPointerException parsing JSON Element at  :"+url+" with message : " +e.getMessage());
		} catch(Exception e){
			logger.error("Exception parsing JSON Element at  :"+url+" with message : " +e.getMessage());
		}
		
		if (element != null && element.isJsonArray()) {
		    JsonArray flashinfos = element.getAsJsonArray();
		    for (int i = 0; i < flashinfos.size(); i++) {
		    	String active = "";
		    	if(i==0){
		    		active = "active";
		    	}
		        JsonObject dataset = flashinfos.get(i).getAsJsonObject();
		        String imgL = dataset.get("mediaUrl").getAsString();
		        String title = dataset.get("title").getAsString();
		        String text = dataset.get("summary").getAsString();
		        String kml = dataset.get("link").getAsString();
		        String alt = title;
		        FlashInfo info = new FlashInfo(imgL, title, text, kml, alt, active, i);
		        flL.add(info);
		    }
		}
		return flL;
	}
}
