package org.esco.portlet.dao.impl;

import java.util.List;

import com.google.common.collect.Lists;
import org.esco.portlet.dao.IFlashInfoResource;
import org.esco.portlet.model.FlashInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by jgribonvald on 13/09/16.
 */
public class FlashInfoResourceJacksonImpl implements IFlashInfoResource {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestTemplate restTemplate;

    public List<FlashInfo> retrieveInfos(String flashUrl) {
        return this.getServiceInfos(flashUrl);
    }

    private List<FlashInfo> getServiceInfos(String url) {
        if (log.isDebugEnabled()) {
            log.debug("Requesting Flash Infos on URL {}", url );
        }

        List<FlashInfo> flL;

        try {
            flL = Lists.newArrayList(restTemplate.getForObject(url, FlashInfo[].class));
        } catch (RestClientException ex) {
            log.warn("Error getting FlashInfoList from url '{}'", url, ex);
            return Lists.newArrayList();
        } catch (HttpMessageNotReadableException ex) {
            log.warn("Error getting FlashInfoList from url '{}' the object doesn't map FlashInfo Object properties", url, ex);
            return Lists.newArrayList();
        }

        return flL;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

}
