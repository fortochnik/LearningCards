<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
    <HEAD>
        <TITLE>Submitting Text Areas</TITLE>
    </HEAD>

    <BODY>
        <H1>add card to <c:out value="${coll}"/></H1>
        <form action="" method="post">
           <select name="collection">
                <c:forEach var="row" items="${rows}">

                    <option>
                        <c:out value="${row}"/>
                    </option>

                </c:forEach>
            </select>
            <br/>front*:
            <br/><textarea name="front" cols="40" rows="3"></textarea>
            <br/>back*:
            <br/><textarea name="back" cols="40" rows="5"></textarea>

            <p><input type="submit" value="Add" name="submit"/>
        </form>



        <form id="back" method="POST" action="collection">
            <input type="submit" value="back" name="backButton"/>
        </form>
    </BODY>
<HTML>