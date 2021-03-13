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
<body style="background: lavender">


<table class="table table-striped table-hover" style="margin: 0 auto; text-align: center;">
    <tr scope="row">
        <th>Student</th>
        <th>Group Number</th>
        <th>Subject1</th>
        <th>Subject2</th>
        <th>Subject3</th>
    </tr>
    <c:forEach var="rating" items="${ratings}">
        <tr scope="row">
            <td>
                    ${rating.student}
            </td>
            <td>
                    ${rating.groupName}
            </td>
            <td>
                    ${rating.firstSubjectRating}
            </td>
            <td>
                    ${rating.secondSubjectRating}
            </td>
            <td>
                    ${rating.thirdSubjectRating}
            </td>
        </tr>
    </c:forEach>
</table>
<div style="width: 90%; display: flex; margin: 2rem auto;">
    <form action="/lab2/rating" method="post" style="width: 100%;">
        <div style="width: 100%; display: flex;">
            <div style="width: 20%;">
                <label for="firstName">First Name</label>
                <input style="width: 95%; height: 2rem;" type="text" id="firstName" name="firstName">
            </div>
            <div style="width: 20%;">
                <label for="lastName">Last Name</label>
                <input style="width: 95%; height: 2rem;" type="text" id="lastName" name="lastName">
            </div>
            <div style="width: 20%;">
                <label for="fatherName">Father Name</label>
                <input style="width: 95%; height: 2rem;" type="text" id="fatherName" name="fatherName">
            </div>
            <div style="width: 10%;">
                <label for="groupSelect">Group Number</label>
                <select style="width: 95%; height: 2rem;" name="groupSelect" id="groupSelect">
                    <c:forEach var="group" items="${groups}">
                        <option value="${group.id}">${group.groupNumber}</option>
                    </c:forEach>
                </select>
            </div>
            <div style="width: 10%;">
                <label for="sub1">Subject1</label>
                <input style="width: 95%; height: 2rem;" type="number" min="2" max="5" value="3" id="sub1" name="sub1">
            </div>
            <div style="width: 10%;">
                <label for="sub2">Subject2</label>
                <input style="width: 95%; height: 2rem;" type="number" min="2" max="5" value="3" id="sub2" name="sub2">
            </div>
            <div style="width: 10%;">
                <label for="sub3">Subject3</label>
                <input style="width: 95%; height: 2rem;" type="number" min="2" max="5" value="3" id="sub3" name="sub3">
            </div>
        </div>
        <div style="display: flex;">
            <input class="btn btn-success" type="submit" value="Add" style="margin-top: 1rem; width: 10rem; margin-left: auto;">
        </div>
    </form>
</div>
<div style="width: 100%; display: flex;">
    <a class="btn btn-info" style="margin: 0 1rem 2rem auto;width: 14rem; text-align: center;"
       href="${pageContext.request.contextPath}/">To main page</a>
    <a class="btn btn-info" style="margin: 0 auto 2rem 1rem;width: 14rem; text-align: center;"
       href="${pageContext.request.contextPath}/statistic">Get statistic</a>
</div>
</body>
</html>
