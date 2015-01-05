<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="stundenplanErstellen" scope="session"
    class="awk.usecase.impl.StundenplanErstellen"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>Stundenplan Erstellung</title>


<% if (stundenplanErstellen.isRunning()) { %>
        <SCRIPT LANGUAGE="JavaScript">
            setTimeout("location='/StundenplanProgramm/Stundenplan/StundenplanErstellung.jsp'", 1000);
        </SCRIPT>
<% } %>
</head>
<body>
<% stundenplanErstellen.erstelleUrplan(); %>
<h2 align="center">
    <% int percent = stundenplanErstellen.getPercent(); %>
    <%= percent %>%
</h2>

<table width="60%" align="center"
        border="1" cellpadding="0" cellspacing="2">
    <tr>
        <% for (int i = 1; i <= percent; i += 1) { %>
            <td width="5%" bgcolor="#000080">&nbsp;</td>
        <% } %>
        <% for (int i = 15; i > percent; i -= 1) { %>
            <td width="5%">&nbsp;</TD>
        <% } %>
    </tr>
</table>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td align="center">
            <% if (stundenplanErstellen.isRunning()) { %>
                Running
            <% } else { %>
                <% if (stundenplanErstellen.isCompleted()) { %>
                    Completed
                <% } else if (!stundenplanErstellen.isStarted()) { %>
                    Not Started
                <% } else { %>
                    Stopped
                <% } %>
            <% } %>
        </td>
    </tr>
</table>
<form name="stundenplanerstellen" action="Dispatch" method="GET">
<input type="hidden" value="stundenplanerstellen" name="page"/>
<input type="submit" value="Stundenplan erstellen" name ="erstellen"/>
</body>
</html>