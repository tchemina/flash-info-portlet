
<jsp:directive.include file="/WEB-INF/jsp/include.jsp" />
<rs:aggregatedResources path="skin.xml" />

<script type="text/javascript">
	// Bootstrap javascript fails if included multiple times on a page.
	// uPortal Bootstrap best practice: include bootstrap if and only if it is not present and save it to
	// portlets object. Bootstrap functions could be manually invoked via portlets.bootstrapjQuery variable.
	// All portlets using Bootstrap Javascript must use this approach.  Portlet's jQuery should be included
	// prior to this code block.
	var portlets = portlets || {};
	// If bootstrap is not present at uPortal jQuery nor a community bootstrap, dynamically load it.
	<c:choose>
		<c:when test="${usePortalJsLibs}">
		portlets.bootstrapjQuery;
		</c:when>
		<c:otherwise>
		portlets.bootstrapjQuery || document.write('<script src="rs/bootstrap/3.3.5/bootstrap.min.js"><\/script>');
		</c:otherwise>
	</c:choose>

</script>

<div id="flashInfoPortlet_${n}" class="flashInfoPortlet">
	<c:if test="${fn:length(flashinfos) gt 0}">
		<div id="myCarousel_${n}" class="carousel slide" data-ride="carousel">
			<!-- Indicators -->
			<c:if test="${fn:length(flashinfos) gt 1}">
				<ol class="carousel-indicators">
					<c:forEach var="entry" items="${flashinfos}" varStatus="loop">
						<c:set var="isactive" scope="page" ><c:if test="${loop.first}">active</c:if></c:set>
						<li data-target="#myCarousel_${n}" data-slide-to="${loop.index}"
							class="${isactive}"></li>
					</c:forEach>
				</ol>
			</c:if>
			<div class="carousel-inner" role="listbox">
				<c:forEach var="entry" items="${flashinfos}" varStatus="loop">
					<!-- Wrapper for slides first entry active-->
					<c:set var="isactive" scope="page" ><c:if test="${loop.first}">active</c:if></c:set>
					<div class="item ${isactive}">
						<img class='carousel-image${loop.index}' src="${entry.imgLink}"
							 alt="${(not empty entry.imgAlt) ? entry.imgAlt : entry.title}">
						<div class="carousel-caption">
							<h3 class="carousel-text${loop.index}">${entry.title}</h3>
							<p class="carousel-text${loop.index}">${entry.text}</p>
							<a href="${entry.knowMoreLink}"> <span class="knowMoreText"><spring:message
									code="portlet.knowmore" /></span>
							</a>
						</div>
					</div>
				</c:forEach>
			</div>
			<!-- Left and right controls -->
			<c:if test="${fn:length(flashinfos) gt 1}">
				<a class="left carousel-control" href="#myCarousel_${n}" role="button"
				   data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
											aria-hidden="true"></span> <span class="sr-only"><spring:message code="portlet.previous" /></span>
				</a> <a class="right carousel-control" href="#myCarousel_${n}" role="button"
						data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only"><spring:message code="portlet.next" /></span>
			</a>
			</c:if>
		</div>
	</c:if>
</div>

<%@include file="/WEB-INF/jsp/scripts.jsp"%>