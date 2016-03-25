package org.esco.portlet.model;

public class FlashInfo {

	private String imgLink;
	private String title;
	private String text;
	private String knowMoreLink;
	private String imgAlt;
	private String active;
	private int rowNumber; 
	
	public FlashInfo(String imgL, String title, String text, String kmL, String alt, String active, int rn){
		this.imgLink = imgL;
		this.title = title;
		this.text = text;
		this.knowMoreLink = kmL;
		this.setImgAlt(alt);
		this.setActive(active);
		this.setRowNumber(rn);
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
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}

	public int getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
}
