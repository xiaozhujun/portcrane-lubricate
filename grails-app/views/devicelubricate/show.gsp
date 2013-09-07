
<%@ page import="portcrane.lubricate.Devicelubricate" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="lubricate">
		<g:set var="entityName" value="${message(code: 'devicelubricate.label', default: 'Devicelubricate')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-devicelubricate" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-devicelubricate" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list devicelubricate">
                <g:if test="${devicelubricateInstance?.username}">
                    <li class="fieldcontain">
                        <span id="username-label" class="property-label"><g:message code="devicelubricate.username.label" default="Username" /></span>

                        <span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${devicelubricateInstance}" field="username"/></span>
                    </li>
                </g:if>
				<g:if test="${devicelubricateInstance?.deviceNum}">
				<li class="fieldcontain">
					<span id="deviceNum-label" class="property-label"><g:message code="devicelubricate.deviceNum.label" default="Device Num" /></span>
						<span class="property-value" aria-labelledby="deviceNum-label"><g:fieldValue bean="${devicelubricateInstance}" field="deviceNum"/></span>
				</li>
				</g:if>
				<g:if test="${devicelubricateInstance?.phone}">
				<li class="fieldcontain">
					<span id="phone-label" class="property-label"><g:message code="devicelubricate.phone.label" default="Phone" /></span>
						<span class="property-value" aria-labelledby="phone-label"><g:fieldValue bean="${devicelubricateInstance}" field="phone"/></span>
				</li>
				</g:if>
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${devicelubricateInstance?.id}" />
					<g:link class="edit" action="edit" id="${devicelubricateInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
