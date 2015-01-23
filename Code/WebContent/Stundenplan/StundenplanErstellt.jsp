<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    errorPage ="../zentraleFehlerseite.jsp"
    import = "dlg.Dispatch"
    %>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="design.css">
<title>Stundenplan wurde erstellt</title>
</head>
<body>
<div id="webside">
		<div id="header">
			<div id="header1">
				<h1>Stundenplan wurde erstellt</h1>
			</div>
			<div id="logo">
			<img src="hlogo.png" alt="h2logo" width="93px" height="75px">
			</div>
		</div>
		<div id="main"></div>
</div>
Der Stundenplan wurde erfolgreich erstellt.
<form name="stundenplanerstellt"  action="Dispatch" method="GET">
<input type="hidden" value="stundenplanerstellt" name="page"/>
<input type="submit" value="Hauptmenü" name ="hauptmenue"/>
</form>
</body>
</html>