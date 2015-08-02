<%@page import="com.xst.webform.element.CommonForm"%>
<%@ page contentType="text/html; charset=Windows-31J" language="java" pageEncoding="UTF-8" %>

<%@ taglib uri="/struts-html" prefix="html"%>
<%@ taglib uri="/struts-bean" prefix="bean"%>

<bean:define id="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html:html locale="true">

<script type="text/javascript" src="<bean:write name='contextPath' />/scripts/common.js?d"></script>
<!-- begin body-->
   <table id="logon-table" summary="ログオンフォームレイアウト用">
     <tr>
       <td>
         <%=((com.xst.webform.element.CommonForm)request.getAttribute("cmmForm")).render() %>
       </td>
     </tr>
   </table>
  <br>
  <br>
</html:html>
