<%@ page language="java" contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants"%>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<c:set var="success" value="${success}" />

<%
	String saveDisabled = (String) request.getSession().getAttribute(IActionConstants.SAVE_DISABLED);
%>

	<table border="0" cellpadding="0" cellspacing="4" width="100%">
		<tbody valign="middle">
			<tr>
				<td align="right">
				<input type="button"
					   id="saveButtonId"
					   onclick="savePage();"
					   disabled="<%=Boolean.valueOf(saveDisabled).booleanValue()%>"
					   value="<spring:message code="label.button.save" />">
					   
					<%-- <html:button onclick="savePage();"
						property="save"
						id="saveButtonId"
						disabled="<%=Boolean.valueOf(saveDisabled).booleanValue()%>">
						<spring:message code="label.button.save" />
					</html:button> --%>
				
				</td>
				<td>
				<input type="button"
					   id="cancelButtonId"
					   onclick="setMyCancelAction(window.document.forms[0], 'Cancel', 'no', '');"
					   value="<spring:message code="label.button.cancel" />">
					<%-- <html:button
						onclick="setMyCancelAction(window.document.forms[0], 'Cancel', 'no', '');"
						property="cancel"
						id="cancelButtonId"
						>
						<spring:message code="label.button.cancel" />
					</html:button> --%>
				</td>
			</tr>
		</tbody>
	</table>

<script type="text/javascript">

<%if( request.getAttribute(IActionConstants.FWD_SUCCESS) != null &&
      ((Boolean)request.getAttribute(IActionConstants.FWD_SUCCESS)) ) { %>
if( typeof(showSuccessMessage) != 'undefined' ){
	showSuccessMessage( true );
}
<% } %>

</script>