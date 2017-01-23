<%-- 
    This jsp page says thank you, lists the user data successfully added to db, gives option for new survey, or display
    data.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="survey" type="surveyutilities.SurveyBeans" scope="session" />
<jsp:useBean id="params" type="surveyutilities.SurveyParams" scope="session" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="res/airlinestyle.css" />
        <title>Thank you for your time!</title>
    </head>
    <body id="mainPage">
        <section id="sectionHead">            
            <h1>Thank you for your time and answers!</h1>            
        </section>
        <h2>Here are your answers.</h2>
        <section id="sectionForm">
            <div id="table1">
                <table>
                    <tr>
                        <td>Airline:</td>
                        <td><jsp:getProperty name="survey" property="airlineInput" /></td>
                    </tr>
                    <tr>
                        <td>Flight#:</td>
                        <td><jsp:getProperty name="survey" property="flightNum" /></td>
                    </tr>
                    <tr>
                        <td>Destination:</td>
                        <td><jsp:getProperty name="survey" property="flightDest" /></td>
                    </tr>
                    </table>
            </div>
            <div id="table2">
                <table>
                    <tr>
                        <th></th>
                        <th>Rate</th>
                    </tr>
                    <tr>
                        <td><%= params.getCatTxt(0) %></td>
                        <td><%= params.getrateKey(survey.getRtFriend()) %></td>
                    </tr>
                    <tr>
                        <td><%= params.getCatTxt(1) %></td>
                        <td><%= params.getrateKey(survey.getRtSpace()) %></td>
                    </tr>
                     <tr>
                        <td><%= params.getCatTxt(2) %></td>
                        <td><%= params.getrateKey(survey.getRtComfort()) %></td>
                    </tr>
                    <tr>
                        <td><%= params.getCatTxt(3) %></td>
                        <td><%= params.getrateKey(survey.getRtClean()) %></td>
                    </tr>
                    <tr>
                        <td><%= params.getCatTxt(4) %></td>
                        <td><%= params.getrateKey(survey.getRtNoise()) %></td>
                    </tr>
                </table>
            </div>
            <% 
                String none = "<em>(none provided)</em>";
                String fName = (survey.getNonReqInsert(0)) ? survey.getRespFName() : none; 
                String lName = (survey.getNonReqInsert(1)) ? survey.getRespLName() : none; 
                String email = (survey.getNonReqInsert(2)) ? survey.getRespEmail() : none;
            %>        
            <div id="table3">
                <table>
                    <tr>
                        <td>First Name:</td>
                        <td><%= fName %></td>
                    </tr>
                    <tr>
                        <td>Last Name:</td>
                        <td><%= lName %></td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td><%= email %></td>
                    </tr>
                </table>
            </div>    
        </section>
        <h2>What would you like to do?</h2>
        <aside class="gotoBtn"><a href="doNewSurvey">Go Back to Home Page & Enter Another Survey</a></aside>
        <aside class="gotoBtn"><a href="displayAll">View All Airline Survey Listings</a></aside>
        <figure id="imgTopLeft"><img src="res/flightattendant.png" alt="flight attendant 1"/></figure> 
        <figure id="imgTopRight"><img src="res/airplane.png" alt="Airplane"/></figure>
        <figure id="imgBtmCenter"><img src="res/flightattendants.png" alt="Flight Attendant Group"/></figure>  
    </body>
</html>
