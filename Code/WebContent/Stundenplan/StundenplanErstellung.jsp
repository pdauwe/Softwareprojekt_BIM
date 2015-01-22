<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="stundenplanErstellen" scope="session"
    class="awk.usecase.impl.StundenplanErstellen"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>Stundenplan Erstellung</title>
<form name="stundenplanerstellen" action="Dispatch" method="GET">
<input type="hidden" value="stundenplanerstellen" name="page"/>
<input type="submit" value="Stundenplan erstellen" name ="erstellen"/>
</body>
</html>