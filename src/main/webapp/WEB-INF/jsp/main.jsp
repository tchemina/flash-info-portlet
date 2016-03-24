<%--

    Licensed to Apereo under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Apereo licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License.  You may obtain a
    copy of the License at the following location:

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.

--%>
<jsp:directive.include file="/WEB-INF/jsp/include.jsp" />

<style>
.carousel-inner > .item > img,
  .carousel-inner > .item > a > img {
      width: 100%;
      height: 300px;
      margin: 0px;
  }
  .carousel-caption {
  padding :5%;
  padding-top: 1%;
  text-align: left;
  overflow: ellipsis;
  top: 0;
  right:0;
  left: auto;
  bottom: auto;
  height:100%;
  font-weight: bold;
  color: #ffffff;
  background-color: #000000;
  border: 1px solid black;
  opacity: 0.6;
  filter: alpha(opacity=60); /* For IE8 and earlier */
  }

  .carousel-caption > p {
/*  display: block;*/
  display: -webkit-box;
  height: 100px;
  max-width : 90%;
  -webkit-line-clamp: 5;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
}

.knowMoreText{
  position:absolute;
  right: 0;
  left : 95%;

 }

 @media (min-width: 1024px){
  .carousel-caption {
   width: 50%;
  }

 .carousel-indicators {
  right: -5%;
  left: auto;
 }


  }
 @media (max-width: 800px) {
  .carousel-caption {
        width: 100%;
  }
 .carousel-indicators {
  right: 0;
  left: 0;
 }


 }

</style>
<c:if test="${fn:length(flashinfo.lesInfos) gt 0}">
<div id="myCarousel" class="carousel slide" data-ride="carousel">
	<!-- Indicators -->
	<ol class="carousel-indicators">
		<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		<li data-target="#myCarousel" data-slide-to="1"></li>
		<li data-target="#myCarousel" data-slide-to="2"></li>
		<li data-target="#myCarousel" data-slide-to="3"></li>
	</ol>
	<div class="carousel-inner" role="listbox">
		<c:forEach var="entry" items="${flashinfo.lesInfos}">
			<!-- Wrapper for slides first entry active-->
			<div class="item ${entry.active}">
				<img src="${entry.imgLink}" alt="${entry.imgAlt}">
				<div class="carousel-caption car-des">
					<h3>${entry.title}</h3>
					<p>${entry.text}</p>
					<a  href="${entry.knowMoreLink}"> <span class="knowMoreText"><spring:message
								code="portlet.knowmore" /></span>
					</a>
				</div>
			</div>
		</c:forEach>
	</div>

	<!-- Left and right controls -->
	<a class="left carousel-control" href="#myCarousel" role="button"
		data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
		aria-hidden="true"></span> <span class="sr-only">Previous</span>
	</a> <a class="right carousel-control" href="#myCarousel" role="button"
		data-slide="next"> <span class="glyphicon glyphicon-chevron-right"
		aria-hidden="true"></span> <span class="sr-only">Next</span>
	</a>
</div>
</c:if>