package org.esco.portlet.model;

import java.util.ArrayList;
import java.util.List;

public class FlashInfoList {

	 private List<FlashInfo> lesInfos ;
	 
	 public void add(FlashInfo info){
		 if(this.lesInfos == null){
			 this.lesInfos = new ArrayList<FlashInfo>();
		 }
		 this.lesInfos.add(info);
	 }
	 
	 public List<FlashInfo> getLesInfos(){
		 return this.lesInfos;
	 }
	 
	 public void setLesInfos(List<FlashInfo>  lesinfos){
		  this.lesInfos = lesinfos;
	 }
}
