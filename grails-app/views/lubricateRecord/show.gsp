
<%@ page import="portcrane.lubricate.LubricateRecord" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="lubricate">
		<g:set var="entityName" value="${message(code: 'lubricateRecord.label', default: 'LubricateRecord')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-lubricateRecord" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-lubricateRecord" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list lubricateRecord">
			
				<g:if test="${lubricateRecordInstance?.devnum}">
				<li class="fieldcontain">
					<span id="devnum-label" class="property-label"><g:message code="lubricateRecord.devnum.label" default="Devnum" /></span>
					
						<span class="property-value" aria-labelledby="devnum-label"><g:fieldValue bean="${lubricateRecordInstance}" field="devnum"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${lubricateRecordInstance?.lubricatetime}">
				<li class="fieldcontain">
					<span id="lubricatetime-label" class="property-label"><g:message code="lubricateRecord.lubricatetime.label" default="Lubricatetime" /></span>
					
						<span class="property-value" aria-labelledby="lubricatetime-label"><g:formatDate date="${lubricateRecordInstance?.lubricatetime}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${lubricateRecordInstance?.id}" />
					<g:link class="edit" action="edit" id="${lubricateRecordInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
