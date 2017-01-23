/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surveyadmin;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import dbconnect.MakeDBConnection;
import surveyutilities.*;

@WebServlet("/displayAll")
public class DisplayAll extends HttpServlet {

    Statement myStat;
    SurveyParams params;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        
        checkSession(request);
        String sqlQuery = "select surveyDate, airline_survey.airlineID, airlineInput, " +
                "airline_codes.airlineName, flightNum, flightDest, rtFriend, rtSpace, " +
                "rtComfort, rtClean, rtNoise, respFName, respLName, respEmail from airline_survey " +
                "left join airline_codes on airline_survey.airlineID=airline_codes.airlineID; ";
        ResultSet results;
        try {
            results = myStat.executeQuery(sqlQuery);
            request.setAttribute("results", results);
        } catch(SQLException e) {
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/surveyAdmin/displayall.jsp");
	dispatcher.forward(request, response); 
    }
    public void checkSession(HttpServletRequest request) 
            throws IOException, ServletException {
            HttpSession session = request.getSession();
        if (session.getAttribute("myStat") != null)
            this.myStat = (Statement)session.getAttribute("myStat");
        else {
            MakeDBConnection myConnect = new MakeDBConnection();
            this.myStat = myConnect.getStatement();
        }
        if (session.getAttribute("params") != null)
            this.params = (SurveyParams)session.getAttribute("params");
        else 
            this.params = new SurveyParams();
    }
}
