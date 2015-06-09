<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<h2>second!</h2>

<c:out value="${string1}"/> - string from first
<br>
<c:out value="${attr1}"/> - attribute from first
<br>
<c:out value="${string2}"/> - string from second
<br>
<c:out value="${attr2}"/> - attribute from second

<br><c:out value="${ind}"/> - IND
<br><c:out value="${list1}"/> - IND

<form id="test" method="POST" action="">
         <input type="submit" value="next" name="submit" />
     </form>

</form>
</body>
</html>
