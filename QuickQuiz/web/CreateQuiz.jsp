<%-- 
    Document   : CreateQuiz
    Created on : 19-Feb-2017, 12:50:06
    Author     : carstencheyne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create a Quiz</title>
    </head>
    <body>
        <h1>This is the Create Quiz Page</h1>
        <p> </p>
        
        Quiz Name
        <input type ="text" name="Text1" >
        
        <br/> <br/>
        
        Quiz Description
        <input type ="text" name="Text2" >
        
        <br/> <br/>

        Module
        <select name ="module">
            <option>AC31007</option>
            <option>AC32007</option>
        </select>
        
        <br/> <br/>

        <button type="button" onclick="">Submit</button>
        
        <p> </p>

    </body>
</html>
