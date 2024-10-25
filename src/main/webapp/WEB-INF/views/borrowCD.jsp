<h2>Liste des CDs Disponibles</h2>

<!-- Affichage de la liste des CDs disponibles -->
<table>
    <tr>
        <th>Titre</th>
        <th>Auteur</th>
        <th>Action</th>
    </tr>
    <c:forEach var="cd" items="${cdList}">
        <tr>
            <td>${cd.title}</td>
            <td>${cd.author}</td>
            <td>
                <!-- Formulaire pour emprunter un CD spécifique -->
                <form action="BorrowServlet" method="post">
                    <input type="hidden" name="cdId" value="${cd.id}">
                    <button type="submit">Emprunter</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
