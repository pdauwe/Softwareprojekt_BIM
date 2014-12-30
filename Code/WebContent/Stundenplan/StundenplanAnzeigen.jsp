<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="dlg.Dispatch"
%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
  
  <c:forEach var="entry" items="${stundenplan}" >
  <c:if test="${entry.key == 0}">
  <tr>
    	<td>08:00 - 11:30</td>
    	<td></td>
    	<td>${entry.value.modul.bezeichnung}</td>
    	<td>${entry.value.raum.name}</td>
    	<td>${entry.value.dozent.name}</td>
    	
    	<c:if test="${entry.key == 3}">
  		<td>${entry.value.modul.bezeichnung}</td>
    	<td>${entry.value.raum.name}</td>
    	<td>${entry.value.dozent.name}</td>
    	</c:if>
   		<c:if test="${entry.key == 6}">
  		<td>${entry.value.modul.bezeichnung}</td>
    	<td>${entry.value.raum.name}</td>
    	<td>${entry.value.dozent.name}</td>
    	</c:if>
   		<c:if test="${entry.key == 9}">
  		<td>${entry.value.modul.bezeichnung}</td>
    	<td>${entry.value.raum.name}</td>
    	<td>${entry.value.dozent.name}</td>
    	</c:if>
    	<c:if test="${entry.key == 12}">
  		<td>${entry.value.modul.bezeichnung}</td>
    	<td>${entry.value.raum.name}</td>
    	<td>${entry.value.dozent.name}</td>
    	</c:if>
  </tr>
  </c:if>
  <c:if test ="${entry.key == 1}">
  <tr>
    	<td>12:00 - 15:45</td>
    	<td>${entry.value.modul.bezeichnung}</td>
    	<td>${entry.value.raum.name}</td>
    	<td>${entry.value.dozent.name}</td>
    	
  		<c:if test="${entry.key == 4}">
  		<td>${entry.value.modul.bezeichnung}</td>
    	<td>${entry.value.raum.name}</td>
    	<td>${entry.value.dozent.name}</td>
    	</c:if>
    	
   		<c:if test="${entry.key == 7}">
  		<td>${entry.value.modul.bezeichnung}</td>
    	<td>${entry.value.raum.name}</td>
    	<td>${entry.value.dozent.name}</td>
    	</c:if>
    	
   		<c:if test="${entry.key == 10}">
  		<td>${entry.value.modul.bezeichnung}</td>
    	<td>${entry.value.raum.name}</td>
    	<td>${entry.value.dozent.name}</td>
    	</c:if>
    	
    	<c:if test="${entry.key == 13}">
  		<td>${entry.value.modul.bezeichnung}</td>
    	<td>${entry.value.raum.name}</td>
    	<td>${entry.value.dozent.name}</td>
    	</c:if>
  </tr>
  </c:if>
  <c:if test ="${entry.key == 2}">
   <tr>
    	<td>16:15 - 19:45</td>
    	<td>${entry.value.modul.bezeichnung}</td>
    	<td>${entry.value.raum.name}</td>
    	<td>${entry.value.dozent.name}</td>
    	
 		<c:if test="${entry.key == 5}">
  		<td>${entry.value.modul.bezeichnung}</td>
    	<td>${entry.value.raum.name}</td>
    	<td>${entry.value.dozent.name}</td>
    	</c:if>
    	
   		<c:if test="${entry.key == 8}">
  		<td>${entry.value.modul.bezeichnung}</td>
    	<td>${entry.value.raum.name}</td>
    	<td>${entry.value.dozent.name}</td>
    	</c:if>
    	
   		<c:if test="${entry.key == 11}">
  		<td>${entry.value.modul.bezeichnung}</td>
    	<td>${entry.value.raum.name}</td>
    	<td>${entry.value.dozent.name}</td>
    	</c:if>
    	
    	<c:if test="${entry.key == 14}">
  		<td>${entry.value.modul.bezeichnung}</td>
    	<td>${entry.value.raum.name}</td>
    	<td>${entry.value.dozent.name}</td>
    	</c:if>
  </tr>
  </c:if>
  </c:forEach>
</table>
<br>
	</div>
</body>
</html>