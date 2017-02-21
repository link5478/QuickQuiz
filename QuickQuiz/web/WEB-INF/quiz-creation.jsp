<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create a quiz</title>
    </head>
    <body>
        <h1>Create a quiz</h1>
        <table>
            <tr>
                <!-- to set to required (but later -->
                <td><label form="quiz-form" for="quiz-name">Name: </label></td>
                <td><input form="quiz-form" id="quiz-name" name="quiz-name" type="text" /></td>
            </tr>
            <tr>
                <td><label form="quiz-form" for="quiz-module-id">Module Id: </label></td>
                <td><input form="quiz-form" id="quiz-module-id" name="quiz-module-id" type="text" /></td>
            </tr>
            <tr>
                <td><label form="quiz-form" for="quiz-description">Description: </label></td>
                <td><textarea form="quiz-form" id="quiz-description" name="quiz-description"></textarea></td>
            </tr>
        </table>
        <form action="quiz-creation" id="quiz-form" method="post">
            <button type="reset">Reset</button>
            <button type="submit">Create quiz</button>
        </form>
    </body>
</html>
