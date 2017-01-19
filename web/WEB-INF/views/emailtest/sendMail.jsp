<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mail Sender</title>
    <link href="css/style.css" rel="stylesheet">
</head>
<head>
    <title>Mail Sender</title>
</head>
<body>
<ul>
    <li><a href="/sendMail">Mail Sender</a></li>
</ul>
<hr>
<div class="sendMail">
    <h1>Send simple e-mail by form (Denis Guzikov)</h1>
    <form class="mailForm" method="post" action="sendMail">
        <table border="0" width="80%" align="center">
            <tr>
                <td>To:</td>
                <td><input type="text" name="recipient" size="65" /></td>
            </tr>
            <tr>
                <td>Subject:</td>
                <td><input type="text" name="subject" size="65" /></td>
            </tr>
            <tr>
                <td>Message:</td>
                <td><textarea cols="50" rows="10" name="message"></textarea></td>
            </tr>
        </table>
        <input class="submitMail" type="submit" value="Send Mail" />
    </form>
</div>
<div class="sendMail">
    <h1>Send simple e-mail (Alexander Pyankov)</h1>
    <form method="post" action="sendMailToUser">
        <input class="submitMail" type="submit" value="Send Mail" />
    </form>
</div>
<div class="sendMail">
    <h1>Send multiple e-mail (Alexander Pyankov)</h1>
    <form method="post" action="sendMailToSomeUsers">
        <input class="submitMail" type="submit" value="Send Mail" />
    </form>
</div>
</body>
</html>