<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Mes Emprunts</title>
</head>
<body>
<h2>Mes CDs Empruntés</h2>
<ul>
    <c:forEach var="emprunt" items="${userEmprunts}">
        <li>
                ${emprunt.cd.title} - ${emprunt.cd.author}
            <form action="${pageContext.request.contextPath}/user/return" method="post">
                <input type="hidden" name="cdId" value="${emprunt.cd.id}">
                <button type="submit">Retourner</button>
            </form>
        </li>
    </c:forEach>
</ul>
</body>
</html>
