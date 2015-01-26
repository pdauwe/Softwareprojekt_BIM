<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    errorPage ="zentraleFehlerseite.jsp"
    import="dlg.Dispatch"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="design.css">
</head>
<body>
<div id="webside">
		<div id="header">
			<div id="header1">
				<h1>Hauptmenü</h1>
			</div>
			<div id="logo">
			<img src="hlogo.png" alt="h2logo" width="93px" height="75px">
			</div>
		</div>
		<div id="main"></div>
</div>
<form name="hauptmenue"  action="Dispatch" method="GET">
<input type="hidden" value="hauptmenue" name="page"/>
<input type="submit" value="Zeiterfassung" name ="zeiterfassung"/><br>
<input type="submit" value="Stundenplan erstellen" name ="stundenplanerstellen"/><br>
<input type="submit" value="Stundenplan anzeigen" name ="studiengangauswahl"/>
</form>
</body>
</html>