package org.esco.portlet.model;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FlashInfo implements Serializable {

	private String imgLink;
	private String title;
	private String text;
	private String knowMoreLink;
	private String imgAlt;
	private Set<Rubrique> rubriques;

	public FlashInfo() {
		super();
	}

	@JsonCreator
	public FlashInfo(@JsonProperty(value = "mediaUrl", required = true) String imgLink, @JsonProperty(value = "title", required = true) String title,
					 @JsonProperty(value = "summary", required = true) String text, @JsonProperty(value = "link", required = true) String knowMoreLink,
					 @JsonProperty("rubriques") Set<Rubrique> rubriques) {
		this.imgLink = imgLink;
		this.title = title;
		this.text = text;
		this.knowMoreLink = knowMoreLink;
		this.rubriques = rubriques;
	}

	public FlashInfo(String imgL, String title, String text, String kmL, String alt){
		super();
		this.imgLink = imgL;
		this.title = title;
		this.text = text;
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
				", knowMoreLink='" + knowMoreLink + '\'' +
				", imgAlt='" + imgAlt + '\'' +
				", rubriques='" + rubriques.toString() + '\'' +
				'}';
	}
}
