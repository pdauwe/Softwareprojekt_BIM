<%@ page language="java" contentType="text/html; charset=UTF-8"
	import = "dlg.Dispatch"
    pageEncoding="UTF-8"
    errorPage ="../zentraleFehlerseite.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="design.css">
<body>
<div id="webside">
		<div id="header">
			<div id="header1">
				<h1>Urplan erstellen</h1>
			</div>
			<div id="logo">
			<img src="hlogo.png" alt="h2logo" width="93px" height="75px">
			</div>
		</div>
		<div id="main"></div>
</div>
<form name="stundenplanerstellen" action="Dispatch" method="GET">
<input type="hidden" value="stundenplanerstellen" name="page"/>
<input type="submit" value="Stundenplan erstellen" name ="erstellen"/>
<input type="submit" value="Hauptmenü" name ="hauptmenue"/>
</form>
</body>
</html>