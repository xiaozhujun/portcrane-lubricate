
<%@ page import="portcrane.lubricate.Lubricate" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="lubricate">
		<g:set var="entityName" value="${message(code: 'lubricate.label', default: 'Lubricate')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-lubricate" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-lubricate" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list lubricate">
			
				<g:if test="${lubricateInstance?.cleancycle}">
				<li class="fieldcontain">
					<span id="cleancycle-label" class="property-label"><g:message code="lubricate.cleancycle.label" default="Cleancycle" /></span>
					
						<span class="property-value" aria-labelledby="cleancycle-label"><g:fieldValue bean="${lubricateInstance}" field="cleancycle"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${lubricateInstance?.location}">
				<li class="fieldcontain">
					<span id="location-label" class="property-label"><g:message code="lubricate.location.label" default="Location" /></span>
					
						<span class="property-value" aria-labelledby="location-label"><g:fieldValue bean="${lubricateInstance}" field="location"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${lubricateInstance?.lube}">
				<li class="fieldcontain">
					<span id="lube-label" class="property-label"><g:message code="lubricate.lube.label" default="Lube" /></span>
					
						<span class="property-value" aria-labelledby="lube-label"><g:fieldValue bean="${lubricateInstance}" field="lube"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${lubricateInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="lubricate.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${lubricateInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${lubricateInstance?.number}">
				<li class="fieldcontain">
					<span id="number-label" class="property-label"><g:message code="lubricate.number.label" default="Number" /></span>
					
						<span class="property-value" aria-labelledby="number-label"><g:fieldValue bean="${lubricateInstance}" field="number"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${lubricateInstance?.refuelcycle}">
				<li class="fieldcontain">
					<span id="refuelcycle-label" class="property-label"><g:message code="lubricate.refuelcycle.label" default="Refuelcycle" /></span>
					
						<span class="property-value" aria-labelledby="refuelcycle-label"><g:fieldValue bean="${lubricateInstance}" field="refuelcycle"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${lubricateInstance?.remindday}">
				<li class="fieldcontain">
					<span id="remindday-label" class="property-label"><g:message code="lubricate.remindday.label" default="Remindday" /></span>
					
						<span class="property-value" aria-labelledby="remindday-label"><g:fieldValue bean="${lubricateInstance}" field="remindday"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${lubricateInstance?.type}">
				<li class="fieldcontain">
					<span id="type-label" class="property-label"><g:message code="lubricate.type.label" default="Type" /></span>
					
						<span class="property-value" aria-labelledby="type-label"><g:fieldValue bean="${lubricateInstance}" field="type"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${lubricateInstance?.id}" />
					<g:link class="edit" action="edit" id="${lubricateInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
