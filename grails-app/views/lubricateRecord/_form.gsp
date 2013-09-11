<%@ page import="portcrane.lubricate.LubricateRecord" %>
<meta name="layout" content="lubricate">


<div class="fieldcontain ${hasErrors(bean: lubricateRecordInstance, field: 'devnum', 'error')} ">
	<label for="devnum">
		<g:message code="lubricateRecord.devnum.label" default="Devnum" />
		
	</label>
	<g:textField name="devnum" value="${lubricateRecordInstance?.devnum}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: lubricateRecordInstance, field: 'lubricatetime', 'error')} required">
	<label for="lubricatetime">
		<g:message code="lubricateRecord.lubricatetime.label" default="Lubricatetime" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="lubricatetime" precision="day"  value="${lubricateRecordInstance?.lubricatetime}"  />
</div>

