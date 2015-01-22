<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="dlg.Dispatch"
%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="design.css">
<title>Stundenplan anzeigen</title>
</head>
<body>
	<div id="webside">
		<div id="header">
			<div id="header1">
				<h1>Stundenplan</h1>
			</div>
			<div id="logo">
			<img src="hlogo.png" alt="h2logo" width="93px" height="75px">
			</div>
		</div>
		<div id="main"></div>
    
<style type="text/css">
	table {width:dynamic;}
	td{border:1px solid #110 vertical-align:top; overflow:hidden;}
</style>

<table align="center" style="table-layout:fixed" border=1 align="center">
	 
<caption background="FFFFFF" >${studiengang}</caption>
  <tr>
  <thead>
    <th align="center" style="width:16%">Zeit</th>
    <th  colspan="3" align="center" style="width:16%">Mo</th>
    <th  colspan="3" align="center" style="width:16%">Di</th>
    <th  colspan="3" align="center" style="width:16%">Mi</th>
    <th  colspan="3" align="center" style="width:16%">Do</th>
    <th  colspan="3" align="center" style="width:16%">Fr</th>
  </thead>
  </tr>
    <tr>
  <thead>
  	<th></th>
    <th>Modul</th>
    <th>Raum</th>
    <th>Dozent</th>
     <th>Modul</th>
    <th>Raum</th>
    <th>Dozent</th>
     <th>Modul</th>
    <th>Raum</th>
    <th>Dozent</th>
     <th>Modul</th>
    <th>Raum</th>
    <th>Dozent</th>
     <th>Modul</th>
    <th>Raum</th>
    <th>Dozent</th>
    
    </thead>
  </tr>
  
  <tr> 
<!-- Eclipse zeigt hier einen Fehler an bei der Verwendung von intValue(), obwohl die Verwendung funktioniert. -->  		
    	<td>08:00 - 11:30</td>
    	<td><c:out value="${stundenplan.zuordnung[(1).intValue()].modul.bezeichnung}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(1).intValue()].raum.name}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(1).intValue()].dozent.name}"></c:out></td>
  
    	<td><c:out value="${stundenplan.zuordnung[(4).intValue()].modul.bezeichnung}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(4).intValue()].raum.name}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(4).intValue()].dozent.name}"></c:out></td>
   		
   		<td><c:out value="${stundenplan.zuordnung[(7).intValue()].modul.bezeichnung}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(7).intValue()].raum.name}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(7).intValue()].dozent.name}"></c:out></td>
   		
   		<td><c:out value="${stundenplan.zuordnung[(10).intValue()].modul.bezeichnung}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(10).intValue()].raum.name}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(10).intValue()].dozent.name}"></c:out></td>
    	
    	<td><c:out value="${stundenplan.zuordnung[(13).intValue()].modul.bezeichnung}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(13).intValue()].raum.name}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(13).intValue()].dozent.name}"></c:out></td>
  </tr>
 
  <tr>
    	<td>12:00 - 15:45</td>
    	<td><c:out value="${stundenplan.zuordnung[(2).intValue()].modul.bezeichnung}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(2).intValue()].raum.name}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(2).intValue()].dozent.name}"></c:out></td>
  
    	<td><c:out value="${stundenplan.zuordnung[(5).intValue()].modul.bezeichnung}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(5).intValue()].raum.name}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(5).intValue()].dozent.name}"></c:out></td>
   		
   		<td><c:out value="${stundenplan.zuordnung[(8).intValue()].modul.bezeichnung}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(8).intValue()].raum.name}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(8).intValue()].dozent.name}"></c:out></td>
   		
   		<td><c:out value="${stundenplan.zuordnung[(11).intValue()].modul.bezeichnung}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(11).intValue()].raum.name}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(11).intValue()].dozent.name}"></c:out></td>
    	
    	<td><c:out value="${stundenplan.zuordnung[(14).intValue()].modul.bezeichnung}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(14).intValue()].raum.name}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(14).intValue()].dozent.name}"></c:out></td>
  </tr>

   <tr>
    	<td>16:15 - 19:45</td>
    	<td><c:out value="${stundenplan.zuordnung[(3).intValue()].modul.bezeichnung}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(3).intValue()].raum.name}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(3).intValue()].dozent.name}"></c:out></td>
  
    	<td><c:out value="${stundenplan.zuordnung[(6).intValue()].modul.bezeichnung}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(6).intValue()].raum.name}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(6).intValue()].dozent.name}"></c:out></td>
   		
   		<td><c:out value="${stundenplan.zuordnung[(9).intValue()].modul.bezeichnung}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(9).intValue()].raum.name}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(9).intValue()].dozent.name}"></c:out></td>
   		
   		<td><c:out value="${stundenplan.zuordnung[(12).intValue()].modul.bezeichnung}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(12).intValue()].raum.name}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(12).intValue()].dozent.name}"></c:out></td>
    	
    	<td><c:out value="${stundenplan.zuordnung[(15).intValue()].modul.bezeichnung}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(15).intValue()].raum.name}"></c:out></td>
    	<td><c:out value="${stundenplan.zuordnung[(15).intValue()].dozent.name}"></c:out></td>
  </tr>
</table>
<br>
	</div>
</body>
</html>