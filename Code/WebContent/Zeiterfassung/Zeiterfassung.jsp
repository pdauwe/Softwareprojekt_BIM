<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    errorPage ="../zentraleFehlerseite.jsp"
    import="dlg.Dispatch"
    %>
 <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="design.css">

<style type="text/css">
	table {width:dynamic;}
	td{border:1px solid #110 vertical-align:top; overflow:hidden;}
	</style>
<title>Zeiterfassung</title>
</head>
<body>
<div id="webside">
		<div id="header">
			<div id="header1">
				<h1>Zeitpräferenzen erfassen</h1>
			</div>
			<div id="logo">
			<img src="hlogo.png" alt="h2logo" width="93px" height="75px">
			</div>
		</div>
		<div id="main"></div>
</div>
<label>Bitte Dozenten waehlen!<br><br></label>

<form name="zeiterfassung" action="Dispatch" method="GET">
	<input type="hidden" value="zeiterfassung" name="page"/>
	<select id ="nameauswahl" name="name" size="1">
		<c:forEach var="dozent" items="${dozenten}" >
			<option> ${dozent.name } </option>
			</c:forEach>
	</select>
	<input type="submit" value="Vorhandene Präferenzen löschen" name="delete"/>	
	<table style="table-layout:fixed" border=1>
	  <tr>
		<th  style="width:16%">Zeit</th>
		<th  style="width:16%">Mo</th>
		<th  style="width:16%">Di</th>
		<th  style="width:16%">Mi</th>
		<th  style="width:16%">Do</th>
		<th  style="width:16%">Fr</th>
	  </tr>
	  <tr>
		<td>08:00 - 11:30</td>
		<td align="center" valign="middle"><input type ="checkbox" name="zeitslot" value="1"/></td>
		<td align="center" valign="middle"><input type ="checkbox" name="zeitslot" value="4"/></td>
		<td align="center" valign="middle"><input type ="checkbox" name="zeitslot" value="7"/></td>
		<td align="center" valign="middle"><input type ="checkbox" name="zeitslot" value="10"/></td>
		<td align="center" valign="middle"><input type ="checkbox" name="zeitslot" value="13"/></td>
	  </tr>
	  <tr>
		<td>12:00 - 15:45</td>
		<td align="center" valign="middle"><input type ="checkbox" name="zeitslot" value="2"/></td>
		<td align="center" valign="middle"><input type ="checkbox" name="zeitslot" value="5"/></td>
		<td align="center" valign="middle"><input type ="checkbox" name="zeitslot" value="8"/></td>
		<td align="center" valign="middle"><input type ="checkbox" name="zeitslot" value="11"/></td>
		<td align="center" valign="middle"><input type ="checkbox" name="zeitslot" value="14"/></td>
	  </tr>
	   <tr>
		<td>16:15 - 19:45</td>
		<td align="center" valign="middle"><input type ="checkbox" name="zeitslot" value="3"/></td>
		<td align="center" valign="middle"><input type ="checkbox" name="zeitslot" value="6"/></td>
		<td align="center" valign="middle"><input type ="checkbox" name="zeitslot" value="9"/></td>
		<td align="center" valign="middle"><input type ="checkbox" name="zeitslot" value="12"/></td>
		<td align="center" valign="middle"><input type ="checkbox" name="zeitslot" value="15"/></td>
	  </tr>
	</table>
	<br>
	<input type="submit" value="speichern" name="speichern"/>
	<input type="submit" value="abbrechen" name="abbrechen"/>
</form>
</body>
</html>