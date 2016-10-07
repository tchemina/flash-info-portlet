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

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.esco.portlet.flashinfo.dao.IFlashInfoResource;
import org.esco.portlet.flashinfo.model.FlashInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jgribonvald on 13/09/16.
 */
public class FlashInfoResourceGsonImpl implements IFlashInfoResource {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private List<FlashInfo> getServiceInfos(String url) {
        if (log.isDebugEnabled()) {
            log.debug("Requesting Flash Infos on URL {}", url );
        }
        List<FlashInfo> flL = new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonElement element;
        try {
            element = parser.parse(url);
        } catch (JsonSyntaxException e) {
            log.error("JsonSyntaxException parsing JSON Element at  :"+url+" with message : " +e.getMessage());
            return flL;
        }

        if (element != null && element.isJsonArray()) {
            final JsonArray flashInfos = element.getAsJsonArray();
            for (JsonElement elm :flashInfos) {
                final JsonObject dataset = elm.getAsJsonObject();
                // getting waited attributes
                final JsonElement jeMediaUrl = dataset.get("mediaUrl");
                final JsonElement jeTitle = dataset.get("title");
                final JsonElement jeSummary = dataset.get("summary");
                final JsonElement jeLink = dataset.get("link");
                // testing if all goes well
                if (jeMediaUrl != null && jeTitle != null && jeSummary != null && jeLink != null) {
                    final String mediaUrl = jeMediaUrl.getAsString();
                    final String title = jeTitle.getAsString();
                    final String summary = jeSummary.getAsString();
                    final String link = jeLink.getAsString();
                    final String alt = title;
                    final FlashInfo info = new FlashInfo(mediaUrl, title, summary, link, alt);
                    flL.add(info);
                } else {
                    log.warn("Problems on reading json element, needed attributes aren't found, check json source ! \nJsonElement is {}", elm.toString());
                }
            }
        }
        return flL;
    }

    public List<FlashInfo> retrieveInfos(String flashUrl) {
        return this.getServiceInfos(flashUrl);
    }
}
