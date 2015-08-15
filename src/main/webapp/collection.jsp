<%--
  Created by IntelliJ IDEA.
  User: mipan
  Date: 19.04.2015
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
  <link href="css/style.css" rel="stylesheet">
</head>
<body>
<div class="main">
<h2>Welcome!</h2>

<form id="test" method="POST" action="">
    <select name="collection">
        <c:forEach var="row" items="${rows}">

            <option>
                <c:out value="${row}"/>
            </option>

        </c:forEach>
    </select>
    <input type="submit" value="submit" name="buttonGetCollection" />

    <h4>Download collection in XML:</h4>
    <form method="POST" action="">
        <input type="submit" value="download" name="downloadButton" />
    </form>
    <form action="" id="delete" method="POST" >
        <input type="submit" value="delete" name="deleteButton" />
    </form>
</form>

<form action="" id="add" method="POST">
        <br/><input type="submit" value="add card" name="addButton"/>
</form>

<h4>Upload XML data:</h4>
<form action="upload" method="post" enctype="multipart/form-data">
		<input name="data" type="file"><br>
		<input type="submit"><br>
</form>

</div>
</body>
</html>
