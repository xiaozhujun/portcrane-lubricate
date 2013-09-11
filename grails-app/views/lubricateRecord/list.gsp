
<%@ page import="portcrane.lubricate.LubricateRecord" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="lubricate">
		<g:set var="entityName" value="${message(code: 'lubricateRecord.label', default: 'LubricateRecord')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-lubricateRecord" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
                <li><span class="btitle"><g:message code="default.list.label" args="[entityName]" /></span></li>
			</ul>
		</div>
		<div id="list-lubricateRecord" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="devnum" title="${message(code: 'lubricateRecord.devnum.label', default: 'Devnum')}" />
					
						<g:sortableColumn property="lubricatetime" title="${message(code: 'lubricateRecord.lubricatetime.label', default: 'Lubricatetime')}" />
					    <th>操作</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${lubricateRecordInstanceList}" status="i" var="lubricateRecordInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${lubricateRecordInstance.id}">${fieldValue(bean: lubricateRecordInstance, field: "devnum")}</g:link></td>
					
						<td><g:formatDate date="${lubricateRecordInstance.lubricatetime}" /></td>
					    <td>
                            <g:form>
                                <fieldset class="buttons">
                                    <g:hiddenField name="id" value="${lubricateRecordInstance?.id}" />
                                    <g:link class="edit" action="edit" id="${lubricateRecordInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                                    <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                                </fieldset>
                            </g:form>
					    </td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${lubricateRecordInstanceTotal}" />
                <div class="nav" role="navigation">
                    <ul>
                        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                    </ul>
                </div>
			</div>
		</div>
	</body>
</html>
