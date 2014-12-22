<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="dlg.Dispatch"
    %>
 <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	table {width:dynamic;}
	td{border:1px solid #110 vertical-align:top; overflow:hidden;}
	</style>
<title>Zeiterfassung</title>
</head>
<body>
<h2>Dozenten Zeiterfassung</h2>
<label>Bitte Dozenten waehlen!<br><br></label>

<form name="zeiterfassung" action="Dispatch" method="GET">
	<input type="hidden" value="zeiterfassung" name="page"/>
	<select id = "nameauswahl" name="name" size="1">
		<c:forEach var="dozent" items="${dozenten}" >
			<option> ${dozent.name } </option>
			</c:forEach>
	</select>
		
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
		<td align="center" valign="middle"><input type ="checkbox" name="1" value="true"/></td>
		<td align="center" valign="middle"><input type ="checkbox" name="4" value="true"/></td>
		<td align="center" valign="middle"><input type ="checkbox" name="7" value="true"/></td>
		<td align="center" valign="middle"><input type ="checkbox" name="10" value="true"/></td>
		<td align="center" valign="middle"><input type ="checkbox" name="13" value="true"/></td>
	  </tr>
	  <tr>
		<td>12:00 - 15:45</td>
		<td align="center" valign="middle"><input type ="checkbox" name="2" value="true"/></td>
		<td align="center" valign="middle"><input type ="checkbox" name="5" value="true"/></td>
		<td align="center" valign="middle"><input type ="checkbox" name="8" value="true"/></td>
		<td align="center" valign="middle"><input type ="checkbox" name="11" value="true"/></td>
		<td align="center" valign="middle"><input type ="checkbox" name="14" value="true"/></td>
	  </tr>
	   <tr>
		<td>16:15 - 19:45</td>
		<td align="center" valign="middle"><input type ="checkbox" name="3" value="true"/></td>
		<td align="center" valign="middle"><input type ="checkbox" name="6" value="true"/></td>
		<td align="center" valign="middle"><input type ="checkbox" name="9" value="true"/></td>
		<td align="center" valign="middle"><input type ="checkbox" name="12" value="true"/></td>
		<td align="center" valign="middle"><input type ="checkbox" name="15" value="true"/></td>
	  </tr>
	</table>
	<br>
	<input type="submit" value="speichern"/>
	<input type="submit" value="abbrechen"/>
</form>
</body>
</html>