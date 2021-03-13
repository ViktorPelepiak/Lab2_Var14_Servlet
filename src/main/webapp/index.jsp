<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lab2_Var14_Servlets</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

</head>
<body style="background: lavender; text-align: center; height: 100%;">
<div style="display: flex; align-items: center; height: 100%; width: 60%; font-size: 2rem; margin-left: auto; margin-right: auto;">
    <div><p>Лабораторна робота №2</p>
        <p>студента 502 групи</p>
        <p>Пелепяка Віктора</p>
        <p>Варіант №14</p>
        <p style="font-size: 1.5rem;">Інформація про літню сесію знаходиться в БД (номер групи, прізвище та ініціали студента, оцінки з 3-х
            предметів).
            Знайти кількість одержаних оцінок 5,4,3,2 і середній бал із кожного предмета.</p>
        <a href="${pageContext.request.contextPath}/rating" class="btn btn-info"
           style="font-size: 2rem; padding-left: 2rem; padding-right: 2rem; border-radius: 2rem;">Переглянути</a>
    </div>
</div>
</body>
</html>
