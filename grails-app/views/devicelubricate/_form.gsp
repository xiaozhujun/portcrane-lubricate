<%@ page import="portcrane.lubricate.Devicelubricate" %>
<meta name="layout" content="lubricate">
<div class="fieldcontain ${hasErrors(bean: devicelubricateInstance, field: 'deviceNum', 'error')} ">
    <label for="deviceNum">
        <g:message code="devicelubricate.deviceNum.label" default="Device Num" />

    </label>
    <g:textField name="deviceNum" value="${devicelubricateInstance?.deviceNum}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: devicelubricateInstance, field: 'username', 'error')} ">
    <label for="username">
        <g:message code="devicelubricate.username.label" default="Username" />

    </label>
    <g:textField name="username" value="${devicelubricateInstance?.username}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: devicelubricateInstance, field: 'phone', 'error')} ">
	<label for="phone">
		<g:message code="devicelubricate.phone.label" default="Phone" />
		
	</label>
	<g:textField name="phone" value="${devicelubricateInstance?.phone}"/>
</div>



