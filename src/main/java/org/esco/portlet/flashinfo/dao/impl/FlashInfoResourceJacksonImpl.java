/**
 * Copyright © 2016 ESUP-Portail (https://www.esup-portail.org/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.esco.portlet.flashinfo.dao.impl;

import java.util.List;

import com.google.common.collect.Lists;
import org.esco.portlet.flashinfo.dao.IFlashInfoResource;
import org.esco.portlet.flashinfo.model.FlashInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(cacheNames = "flashinfos", key = "#flashUrl")
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
