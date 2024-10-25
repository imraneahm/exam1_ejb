<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des CD</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            padding: 50px;
        }
        h1 {
            color: #333;
        }
        .button {
            background-color: #4CAF50; /* Vert */
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 5px;
        }
        .button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<h1>Gestion des CD</h1>
<div>
    <a href="listerCD.jsp" class="button">Lister les CD disponibles</a>
    <a href="emprunterCD.jsp" class="button">Emprunter un CD</a>
    <a href="voirCDempruntes.jsp" class="button">Voir les CD empruntés</a>
</div>

</body>
</html>
