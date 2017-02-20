<%-- 
    This jsp page displays all the current survey entries. A little more processing involved for 
    a jsp page, so will look up other ways so that this is just display page.
    data.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<jsp:useBean id="params" type="surveyutilities.SurveyParams" scope="session" />
<%
    ResultSet results = (ResultSet)request.getAttribute("results");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Airline Surveys</title>
        <link type="text/css" rel="stylesheet" href="res/airlinestyle.css" />
    </head>
    <body id="mainPage">
        <section id="sectionHead">            
            <h1>Here are the results of your query.</h1>            
        </section>
        <section id="sectionList">
            <table id="tableList">
                <tr>
                    <th></th>
                    <th class="thh" colspan="2">First, Last Name &#8212; Email</th>
                    <th colspan="3"></th>
                    <th class="thh"  colspan="5">Respondent Rating</th>
                </tr>
                <tr class="thc">
                    <th>Survey Date</th>
                    <th>NonRec Airline</th>
                    <th>Airline Name</th>
                    <th>Airline ID</th>
                    <th>Flight #</th>
                    <th>Destination</th>
                    <th>Friendliness</th>
                    <th>Luggage Space</th>
                    <th>Seat Comfort</th>
                    <th>Cleanliness</th>
                    <th>Noise Level</th>
                </tr>
                <tr>
                    <td><br></td>
                </tr>
                <% 
                while (results.next()) {
                    String airlineInput = (results.getString("airlineInput") == null) ? "" : 
                            results.getString("airlineInput");
                    String airlineName = (results.getString("airlineName") == null) ? "" : 
                            results.getString("airlineName");
                    String airlineID = (results.getString("airlineID") == null) ? "" : 
                            results.getString("airlineID");
                    String respFName = (results.getString("respFName") == null) ? "(no first)" : 
                            results.getString("respFName");
                    String respLName = (results.getString("respLName") == null) ? "(no last)" : 
                            results.getString("respLName");
                    String respEmail = (results.getString("respEmail") == null) ? "(no email)" : 
                            results.getString("respEmail");
                %>
                <tr>
                    <td></td>
                    <td class="ind" colspan="2"><%=respFName%> <%=respLName%> &#8212; <%=respEmail%></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td><%=airlineInput%></td>
                    <td><%=airlineName%></td>
                    <td><%=airlineID%></td>
                    <td><%=results.getString("flightNum")%></td>
                    <td><%=results.getString("flightDest")%></td>
                    <td class="bb"><%=params.getrateKey(results.getInt("rtFriend"))%></td>
                    <td class="bb"><%=params.getrateKey(results.getInt("rtSpace"))%></td>
                    <td class="bb"><%=params.getrateKey(results.getInt("rtComfort"))%></td>
                    <td class="bb"><%=params.getrateKey(results.getInt("rtClean"))%></td>
                    <td class="bb"><%=params.getrateKey(results.getInt("rtNoise"))%></td>
                </tr>
                <tr><td><br></td></tr>
               <% }; %>
            </table>
        </section>
        <h2>Now what would you like to do?</h2>
        <aside class="gotoBtn"><a href="doNewSurvey">Go Back to Home Page & Enter Another Survey</a></aside>
        <figure id="imgTopLeft"><img src="res/itclogo.png" alt="flight attendant 1"/></figure> 
        <figure id="imgTopRight"><img src="res/airplane.png" alt="Airplane"/></figure>
        <figure id="imgBtmCenter"><img src="res/flightattendants.png" alt="Flight Attendant Group"/></figure> 
    </body>
</html>
