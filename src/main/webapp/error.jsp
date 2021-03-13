<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.lang.String" %>
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
<body style="background: lavender; display: flex; align-items: center">
<div style="align-items: center;width: 100%;text-align: center;">
    <h1>Something went wrong</h1>
    <table class="table table-striped table-hover" style="margin: 0 auto; text-align: center;">
        <tr>
            <td>
                Error Type:
            </td>
            <td>
                ${errorType}
            </td>
        </tr>
        <c:if test="${errorMessage.isEmpty()}">
            <tr>
                <td>
                    Error Message:
                </td>
                <td>
                        ${errormessage}
                </td>
            </tr>
        </c:if>
    </table>
    <div style="width: 100%; display: flex;">
        <a class="btn btn-info" style="margin: 2rem auto; width: 14rem; text-align: center;"
           href="${pageContext.request.contextPath}/">To main page</a>
    </div>
</div>
</body>
</html>
