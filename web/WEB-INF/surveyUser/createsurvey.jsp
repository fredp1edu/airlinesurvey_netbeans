<%-- 
    This jsp page creates the survey form and is populated by the beans from doSurvey.java
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="params" type="surveyutilities.SurveyParams" scope="session" />
<!DOCTYPE html>
<html>
    <head>
        <title>International Airline Survey Form</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link type="text/css" rel="stylesheet" href="res/airlinestyle.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script type="text/javascript" src="res/airlinescript.js"></script>
    </head>
    <body id="mainPage">
        <section id="sectionHead">            
            <h1>Your International Airline Survey Form</h1>            
        </section>
        <h2>Please provide your views on your last airline flight experience.</h2>
        <section id="sectionForm">
            <form id="frmAirline" name="frmAirline" onsubmit="return checkForm()" action="surveyResults" method="post">
                <table id="tableForm">
                    <tr>
                        <td>Airline Name</td><td>Flight #</td><td>Destination</td>
                    </tr>
                    <tr>
                        <td>
                            <input name="airlineInput" id="airlineInput" type="text" size="25" 
                                   onkeyup="searchAirlines()" autofocus/>
                        </td>
                        <td>
                            <input name="flightNum" id="flightNum" type="text" size="15"
                                   onkeyup="killSearch()"/>
                        </td>
                        <td>
                            <input name="flightDest" id="flightDest" type="text" size="25"/>
                        </td>
                    </tr>
                </table>
                <div id="searchResults">
                    <select name="resultBox" id="resultBox"></select>
                </div>
                <p>Please rate your airline experience using the criteria below:</p>
                <table id="tableRadio">
                    <tr>
                        <td></td>
                        <%
                            for (int i = 0; i < params.getRateKey().length; i++) {
                                out.println("<td>" + params.getrateKey(i) + "</td>\n");
                            }
                        %>        
                    </tr>
                    <%
                        for (int i = 0; i < params.getCatTxt().length; i++) {
                            out.println("<tr>\n<td class=\"txt\">" + params.getCatTxt(i) + 
                                    "</td>\n");
                            for (int j = 0; j < params.getCatCode().length; j++) {
                                out.println("<td><input type=\"radio\" name=\"" +
                                        params.getCatCode(i) + "\" value=\"" + j + "\" /></td>\n");
                            }
                            out.println("<tr>\n");
                        }
                    %>
                </table>
                <div id="respInput">
                    <p>Please provide a name for better processing. Add your email and be entered in to weekly chances
                        to receive free trips!</p>
                    <table>
                        <tr>
                            <td>
                                <input name="respFName" id="respFName" type="text" size="25" 
                                placeholder="First"/>
                            </td>
                            <td>
                                <input name="respLName" id="respLName" type="text" size="25" 
                                placeholder="Last"/>
                            </td>
                            <td>
                                <input name="respEmail" id="respEmail" type="text" size="25" 
                                placeholder="Email"/>
                            </td>
                        </tr>
                    </table>
                </div>
                <button id='btn' type="submit">Submit</button>
            </form>
           <span id='errorMsg'>Error Message Here</span>     
        </section>
         <figure id="imgTopLeft"><img src="res/flightattendant.png" alt="flight attendant 1"/></figure> 
         <figure id="imgTopRight"><img src="res/airplane.png" alt="Airplane"/></figure>
         <figure id="imgBtmLeft"><img src="res/pilotmale1.png" alt="Pilot"/></figure> 
         <figure id="imgBtmRight"><img src="res/flightattendant2.png" alt="flight attendant 2"/></figure>  
    </body>
</html>
