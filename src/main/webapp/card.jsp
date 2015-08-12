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


<br>Statistic:
<br>OK: <c:out value="${card.okValue}"/>
<br>FAIL: <c:out value="${card.failValue}"/>



</form>
</body>
</html>
