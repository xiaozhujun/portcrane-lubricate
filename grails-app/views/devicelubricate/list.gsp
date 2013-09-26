
<%@ page import="portcrane.lubricate.Devicelubricate" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="lubricate">
		<g:set var="entityName" value="${message(code: 'devicelubricate.label', default: 'Devicelubricate')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-devicelubricate" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
                <li><span class="btitle"><g:message code="default.list.label" args="[entityName]" /></span></li>
			</ul>
		</div>
		<div id="list-devicelubricate" class="content scaffold-list" role="main">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
                        <g:sortableColumn property="deviceNum" title="${message(code: 'devicelubricate.deviceNum.label', default: 'Device Num')}" />

                        <g:sortableColumn property="username" title="${message(code: 'devicelubricate.username.label', default: 'Username')}" />

						  <g:sortableColumn property="phone" title="${message(code: 'devicelubricate.phone.label', default: 'Phone')}" />

                        <th>操作</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${devicelubricateInstanceList}" status="i" var="devicelubricateInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                     <td><g:link action="show" id="${devicelubricateInstance.id}">${fieldValue(bean: devicelubricateInstance, field: "deviceNum")}</g:link></td>

                     <td>${fieldValue(bean: devicelubricateInstance, field: "username")}</td>

						<td>${fieldValue(bean: devicelubricateInstance, field: "phone")}</td>
                        <td>
                            <g:form>
                                <fieldset class="buttons">
                                    <g:hiddenField name="id" value="${devicelubricateInstance?.id}" />
                                    <g:link class="edit" action="edit" id="${devicelubricateInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                                    <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                                </fieldset>
                            </g:form>
                        </td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${devicelubricateInstanceTotal}" />
                <div class="nav" role="navigation">
                    <ul>
                        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                    </ul>
                </div>
			</div>
		</div>
	</body>
</html>
