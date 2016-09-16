
<script type="text/javascript"><rs:compressJs>
	/*
	 * Don't overwrite an existing myPortletName variable, just add to it
	 */
	var ${n} = ${n} || {};

	/*
	 * Switch jQuery to extreme noConflict mode, keeping a reference to it in the myPortletName namespace
	 */
	<c:choose>
		<c:when test="${!usePortalJsLibs}">
			${n}.jQuery = jQuery.noConflict(true);
		</c:when>
		<c:otherwise>
		<c:set var="ns"><c:if test="${ not empty portalJsNamespace }">${ portalJsNamespace }.</c:if></c:set>
			${n}.jQuery = ${ ns }jQuery;
		</c:otherwise>
	</c:choose>

	/**
	 * Use an anonymous function to initialize all JavaScript for this portlet.
	 */
	(function($, namespace, portletId) {
		flashInfoPortlet.init($, namespace, portletId);
	})(${n}.jQuery, '#flashInfoPortlet_${n}', '${n}');

</rs:compressJs></script>