<%@ page import="portcrane.lubricate.Lubricate" %>
<meta name="layout" content="lubricate">

<div class="fieldcontain ${hasErrors(bean: lubricateInstance, field: 'name', 'error')} ">
    <label for="name">
        <g:message code="lubricate.name.label" default="Name" />

    </label>
    <g:textField name="name" value="${lubricateInstance?.name}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: lubricateInstance, field: 'location', 'error')} ">
	<label for="location">
		<g:message code="lubricate.location.label" default="Location" />
		
	</label>
	<g:textField name="location" value="${lubricateInstance?.location}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: lubricateInstance, field: 'lube', 'error')} ">
	<label for="lube">
		<g:message code="lubricate.lube.label" default="Lube" />
		
	</label>
	<g:textField name="lube" value="${lubricateInstance?.lube}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: lubricateInstance, field: 'number', 'error')} ">
	<label for="number">
		<g:message code="lubricate.number.label" default="Number" />
		
	</label>
	<g:textField name="number" value="${lubricateInstance?.number}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: lubricateInstance, field: 'cleancycle', 'error')} ">
    <label for="cleancycle">
        <g:message code="lubricate.cleancycle.label" default="Cleancycle" />

    </label>
    <g:textField name="cleancycle" value="${lubricateInstance?.cleancycle}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: lubricateInstance, field: 'refuelcycle', 'error')} ">
	<label for="refuelcycle">
		<g:message code="lubricate.refuelcycle.label" default="Refuelcycle" />
		
	</label>
	<g:textField name="refuelcycle" value="${lubricateInstance?.refuelcycle}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: lubricateInstance, field: 'type', 'error')} ">
	<label for="type">
		<g:message code="lubricate.type.label" default="Type" />
		
	</label>
	<g:textField name="type" value="${lubricateInstance?.type}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: lubricateInstance, field: 'remindday', 'error')} required">
    %{--<label for="remindday">
        <g:message code="lubricate.remindday.label" default="Remindday" />
        <span class="required-indicator">*</span>
    </label>
    <g:field name="remindday" type="number" value="${lubricateInstance.remindday}" required=""/>--}%
     距离下一次润滑<g:field name="remindday" type="number" value="${lubricateInstance.remindday}" required=""/>天以上提醒
</div>
