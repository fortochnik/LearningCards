<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
    <HEAD>
        <TITLE>Submitting Text Areas</TITLE>
    </HEAD>

    <BODY>
        <H1>delete <c:out value="${coll}"/> collection</H1>
        <form action="" method="post">
           <select name="collection">
                <c:forEach var="row" items="${rows}">

                    <option>
                        <c:out value="${row}"/>
                    </option>

                </c:forEach>
            </select>


            <p><input type="submit" value="delete" name="submit"/>
        </form>



        <form id="back" method="POST" action="collection">
            <input type="submit" value="back" name="backButton"/>
        </form>
    </BODY>
<HTML>