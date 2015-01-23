<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    isErrorPage = "true"
    import="java.io.PrintWriter"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fehler!</title>
</head>
<body>
Es ist ein Anwendungsfehler aufgetreten!<br/>
<br/>
Fehlerinfos:
<br/>
<%=exception.getMessage()%>
Hintergrundinfos:
<br/>
<%=exception.getCause()%>
Details:
<br/>
<%exception.printStackTrace(new PrintWriter(out));%>
</body>
</html>