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
package org.esco.portlet.flashinfo.model;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FlashInfo implements Serializable {

	private String imgLink;
	private String title;
	private String text;
	private String knowMoreText;
	private String knowMoreLink;
	private String imgAlt;
	private Set<Rubrique> rubriques;

	public FlashInfo() {
		super();
	}

	@JsonCreator
	public FlashInfo(@JsonProperty(value = "mediaUrl", required = true) String imgLink, @JsonProperty(value = "title", required = true) String title,
					 @JsonProperty(value = "summary", required = true) String text, @JsonProperty(value = "knowMoreText", required = true) String knowMoreText,
					 @JsonProperty(value = "link", required = true) String knowMoreLink, @JsonProperty("rubriques") Set<Rubrique> rubriques) {
		this.imgLink = imgLink;
		this.title = title;
		this.text = text;
		this.knowMoreText = knowMoreText;
		this.knowMoreLink = knowMoreLink;
		this.rubriques = rubriques;
	}

	public FlashInfo(String imgL, String title, String text, String kmT, String kmL, String alt){
		super();
		this.imgLink = imgL;
		this.title = title;
		this.text = text;
		this.knowMoreText = kmT;
		this.knowMoreLink = kmL;
		this.imgAlt = alt;
	}

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getKnowMoreText() {
		return knowMoreText;
	}

	public void setKnowMoreText(String knowMoreText) {
		this.knowMoreText = knowMoreText;
	}

	public String getKnowMoreLink() {
		return knowMoreLink;
	}

	public void setKnowMoreLink(String knowMoreLink) {
		this.knowMoreLink = knowMoreLink;
	}

	public String getImgAlt() {
		return imgAlt;
	}

	public void setImgAlt(String imgAlt) {
		this.imgAlt = imgAlt;
	}

	public Set<Rubrique> getRubriques() {
		return rubriques;
	}

	public void setRubriques(final Set<Rubrique> rubriques) {
		this.rubriques = rubriques;
	}

	@Override
	public String toString() {
		return "FlashInfo{" +
				"imgLink='" + imgLink + '\'' +
				", title='" + title + '\'' +
				", text='" + text + '\'' +
				", knowMoreText='" + knowMoreText + '\'' +
				", knowMoreLink='" + knowMoreLink + '\'' +
				", imgAlt='" + imgAlt + '\'' +
				", rubriques='" + rubriques.toString() + '\'' +
				'}';
	}
}
