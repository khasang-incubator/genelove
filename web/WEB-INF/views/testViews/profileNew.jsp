<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Menu page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <%-- <link href="resources/css/menuPage.css" rel="stylesheet" type="text/css" media="all" /> --%>
    <link href="../css/menuPage.css" rel="stylesheet" type="text/css" media="all"/>
</head>
<body style="background: url(../resources/images/loveFone.jpg) no-repeat fixed center;">

<jsp:include page="../include/header.jsp"/>

<div class="row content" id="menuBox">
    <div class="col-sm-10 text-left">
        <div class="container">
            <form:form action="/addProfile" method="post" commandName="addProfile"
                       cssClass="form-inline form-group-sm">

                <p><label for="nickname">Nickname:</label><input type="text" id="nickname" class="form-control" name="nickname"></p>
                <p><label for="age">Age:</label><input type="text" id="age" class="form-control" name="age"></p>
                <p><label for="hobbies">Your hobbies:</label><input type="text" id="hobbies" class="form-control" name="hobbies"></p>
                <p><label for="introduction">About yourself:</label><input type="text" id="introduction" class="form-control" name="introduction"></p>
                <p><label for="match">You want to find:</label><input type="text" id="match" class="form-control" name="match"></p>

                <p><label for="gender">Your gender:</label>
                <select name="gender" id="gender" class="form-control">
                    <option hidden disabled selected>Choose gender</option>
                    <option value="FEMALE">FEMALE</option>
                    <option value="MALE">MALE</option>
                    <option value="OTHER">OTHER</option>
                </select></p>

                <p><label for="maritalStatus">Your marital status:</label>
                <select name="maritalStatus" id="maritalStatus" class="form-control">
                    <option hidden disabled selected>Choose your status</option>
                    <option value="SINGLE">SINGLE</option>
                    <option value="MARRIED">MARRIED</option>
                    <option value="SEPARATED">SEPARATED</option>
                    <option value="DIVORCED">DIVORCED</option>
                    <option value="WIDOWED">WIDOWED</option>
                </select></p>


                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" class="btn btn-success" value="Add profile">
            </form:form>

        </div>
    </div>
</div>
<jsp:include page="../include/footer.jsp"/>
</body>
</html>