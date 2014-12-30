<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="dlg.Dispatch"
%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Studiengang auswaehlen</title>
</head>
<body>

<form name="studiengangauswahl" action="Dispatch" method="GET">
	<input type="hidden" value="studiengangauswahl" name="page"/>
	<select id = "studiengang" name="studiengang" size="1">
		<c:forEach var="s" items="${studiengaenge}" >
			<option> ${s.name } </option>
			</c:forEach>
	</select>
	<input type="submit" value="Stundenplan anzeigen" name ="stundenplananzeigen"/>
</form>
</body>
</html>