<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<script>
    function showDiv()
    {
        document.getElementById('welcomeDiv').style.display = "block";
        document.getElementById('welcomeButton').style.display = "block";

        document.getElementById("mainButton").style.display = "none";
    }

    function display()
    {
        document.getElementById('mainButton').style.display = "block";
        document.getElementById('welcomeDiv').style.display = "none";
        document.getElementById('welcomeButton').style.display = "none";
    }
</script>

<h2>Card!</h2>

<form id="back" method="POST">
    <input type="submit" value="back" name="backButton"/>


</form>





<option><c:out value="${card.front}"/></option>
<br>----

<div id="welcomeDiv"  style="display:none;" class="answer_list" >
     <option><c:out value="${card.back}"/></option>
</div>
<div id="mainButton">
    <p>
    <input type="submit" value="Display" name="submit" onclick="showDiv()"/>
</div>

<div id="welcomeButton"  style="display:none;" class="answer_button" >
    <form id="result" method="POST" action="">
             <input type="submit" value="ok" name="okButton" onclick="display()"/>
             <input type="submit" value="fail" name="failButton" onclick="display()"/>
    </form>
</div>

<br>BASCKET: <c:out value="${bascket}"/>
<br>OK <c:out value="${ok}"/>
<br>FAIL <c:out value="${fail}"/>


<br>Collection <c:out value="${nameCollection}"/>




<%--
     <p>test
     <br><c:out value="${list1}"/> - test

    <c:forEach var="row" items="${rows}">

       <option><c:out value="${row.front}"/></option>
       ----
       <option><c:out value="${row.back}"/></option>
     </c:forEach>


--%>

</form>
</body>
</html>
