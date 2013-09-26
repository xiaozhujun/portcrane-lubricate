
<%@ page import="portcrane.lubricate.Lubricate" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="lubricate">
		<g:set var="entityName" value="${message(code: 'lubricate.label', default: 'Lubricate')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-lubricate" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
                <li><span class="btitle"><g:message code="default.list.label" args="[entityName]" /></span></li>
			</ul>
		</div>
		<div id="list-lubricate" class="content scaffold-list" role="main">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
                      <g:sortableColumn property="name" title="${message(code: 'lubricate.name.label', default: 'Name')}" />

						<g:sortableColumn property="location" title="${message(code: 'lubricate.location.label', default: 'Location')}" />
					
						<g:sortableColumn property="lube" title="${message(code: 'lubricate.lube.label', default: 'Lube')}" />
					
						<g:sortableColumn property="number" title="${message(code: 'lubricate.number.label', default: 'Number')}" />

                      <g:sortableColumn property="cleancycle" title="${message(code: 'lubricate.cleancycle.label', default: 'Cleancycle')}" />

                      <g:sortableColumn property="refuelcycle" title="${message(code: 'lubricate.refuelcycle.label', default: 'Refuelcycle')}" />
					    <th>操作</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${lubricateInstanceList}" status="i" var="lubricateInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                      <td>${fieldValue(bean: lubricateInstance, field: "name")}</td>

						<td>${fieldValue(bean: lubricateInstance, field: "location")}</td>
					
						<td>${fieldValue(bean: lubricateInstance, field: "lube")}</td>

						<td>${fieldValue(bean: lubricateInstance, field: "number")}</td>

                      <td><g:link action="show" id="${lubricateInstance.id}">${fieldValue(bean: lubricateInstance, field: "cleancycle")}</g:link></td>

                      <td>${fieldValue(bean: lubricateInstance, field: "refuelcycle")}</td>
					    <td>
                            <g:form>
                                <fieldset class="buttons">
                                    <g:hiddenField name="id" value="${lubricateInstance?.id}" />
                                    <g:link class="edit" action="edit" id="${lubricateInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                                    <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                                </fieldset>
                            </g:form>
					    </td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${lubricateInstanceTotal}" />
                <div class="nav" role="navigation">
                    <ul>
                        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                    </ul>
                </div>
			</div>
		</div>
	</body>
</html>
