<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>CDs Disponibles</title>
</head>
<body>
<h2>Liste des CDs Disponibles</h2>
<ul>
    <c:forEach var="cd" items="${availableCDs}">
        <li>
                ${cd.title} - ${cd.author}
            <form action="${pageContext.request.contextPath}/user/borrow" method="post">
                <input type="hidden" name="cdId" value="${cd.id}">
                <button type="submit">Emprunter</button>
            </form>
        </li>
    </c:forEach>
</ul>
</body>
</html>
