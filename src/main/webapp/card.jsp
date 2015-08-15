<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
  <link href="css/style.css" rel="stylesheet">
</head>
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
<div class="main">
<h2>Card!</h2>

<form id="back" method="POST">
    <input type="submit" value="back" name="backButton"/>


</form>

<div class="cardSide">
<div class="front">
<option><c:out value="${card.front}" default="emptyValue"/></option>
</div>
</div>
<br>----
<div class="cardSide">
<div id="welcomeDiv"  style="display:none;" class="answer_list" >
     <option><c:out value="${card.back}" default="emptyValue" escapeXml="false"/></option>
</div>
</div>
<div id="mainButton">

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
</div>
</body>
</html>

