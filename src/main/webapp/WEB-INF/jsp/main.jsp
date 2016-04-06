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
<rs:aggregatedResources path="skin.xml" />
<%@include file="/WEB-INF/jsp/scripts.jsp"%>
<c:if test="${fn:length(flashinfo.lesInfos) gt 0}">
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<c:if test="${fn:length(flashinfo.lesInfos) gt 1}">
		<ol class="carousel-indicators">
			<c:forEach var="entry" items="${flashinfo.lesInfos}">
				<li data-target="#myCarousel" data-slide-to="${entry.rowNumber}"
					class="${entry.active}"></li>
			</c:forEach>
		</ol>
		</c:if>
		<div class="carousel-inner" role="listbox">
			<c:forEach var="entry" items="${flashinfo.lesInfos}">
				<!-- Wrapper for slides first entry active-->
				<div class="item ${entry.active}">
					<img class='carousel-image${entry.rowNumber}' src="${entry.imgLink}"
						alt="${entry.imgAlt}">
					<div class="carousel-caption">
						<h3 class="carousel-text${entry.rowNumber}">${entry.title}</h3>
						<p class="carousel-text${entry.rowNumber}">${entry.text}</p>
						<a href="${entry.knowMoreLink}"> <span class="knowMoreText"><spring:message
									code="portlet.knowmore" /></span>
						</a>
					</div>
				</div>
			</c:forEach>
		</div>
		<!-- Left and right controls -->
		<c:if test="${fn:length(flashinfo.lesInfos) gt 1}">
		<a class="left carousel-control" href="#myCarousel" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
		</c:if>
	</div>
</c:if>