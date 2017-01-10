<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Multiple Select Example</title>
    <link href="../css/style.css" rel="stylesheet">
</head>
<body>
<ul class="navMenu">
    <li><a href="http://localhost:8080">Main page</a></li>
    <li><a href="create">Create table / Entry</a></li>
    <li><a href="insert">Insert data into <strong>films</strong></a></li>
    <li><a href="simpleSelect">Simple select into <strong>films</strong></a></li>
    <li><a href="multipleSelect">Multiple select into <strong>films</strong></a></li>
    <li><a href="http://localhost:8080/logout">Log Out</a></li>
</ul>

<div id="container">
    <div id="left"><h2>LEFT MENU</h2><hr>
        <ul id="leftMenu">
            <li>Item 1</li>
            <li>Item 2</li>
            <li>Item 3</li>
            <li>Item 4</li>
        </ul>
        <hr>
        <p>Advertisment</p>

    </div>
    <div id="main">
        <h1>Multiple Select Example</h1>
        <hr>
        <p><strong>Here is raw-list:</strong>  ${multipleSelect}
        <table border="1">
            <tr>
                <th>Code</th>
                <th>Title</th>
                <th>Date</th>
            </tr>
            <c:forEach items = "${multipleSelect}" var = "films">
                <tr>
                    <td><c:out value="${films.code}"/></td>
                    <td><c:out value="${films.title}"/></td>
                    <td><c:out value="${films.date_prod}"/></td>
                </tr>
            </c:forEach>
        </table>
        </p>
        <hr>
    </div></div>
<div id="footer"><h1>Footer</h1></div>
</body>
</html>



