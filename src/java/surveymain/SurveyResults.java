/*
 * This class initializes a surveyBeansParam obj, associates formBean input data with the Beans and then adds to 
 *  the database. I was going to use that jakarta bean utility to automatically load the form input data to the 
 *  beans, but I would rather set up my own verification process and error messaging. 
 *  Thinking of a more automatic way to associate inputs with beans and then beans with db, but that's taking too 
 *  long. Will just stick to the basics for now and rethink later.
 */
package surveymain;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import dbconnect.MakeDBConnection;
import surveyutilities.*;
import java.util.ArrayList;

@WebServlet("/surveyResults")
public class SurveyResults extends HttpServlet {
    
    Statement myStat;
    SurveyParams params;
    SurveyBeans survey;
    ArrayList<Integer> errorList = new ArrayList<>();       // if any empty spaces at any req field, wont go to db
        
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        checkSession(request, response);
        if (!errorList.isEmpty())
            errorList.clear();
        boolean errorFree = true;
        String[] inputReq = new String[params.getReqField().length];
        String[] inputNon = new String[params.getNonReqField().length];
        for (int i = 0; i < inputReq.length; i++) {
            inputReq[i] = request.getParameter(params.getReqField(i));
            if (inputReq[i].trim().equals("")) errorList.add(i);
        }
        for (int i = 0; i < inputNon.length; i++) {
            inputNon[i] = request.getParameter(params.getNonReqField(i));
            if (inputNon[i].trim().equals("")) errorList.add(i+10);
        }
        if (!errorList.contains(0)) survey.setRtFriend(Integer.parseInt(inputReq[0]));
        if (!errorList.contains(1)) survey.setRtSpace(Integer.parseInt(inputReq[1]));
        if (!errorList.contains(2)) survey.setRtComfort(Integer.parseInt(inputReq[2]));
        if (!errorList.contains(3)) survey.setRtClean(Integer.parseInt(inputReq[3]));
        if (!errorList.contains(4)) survey.setRtNoise(Integer.parseInt(inputReq[4]));
        if (!errorList.contains(5)) survey.setAirlineInput(inputReq[5]);
        if (!errorList.contains(6)) survey.setFlightNum(inputReq[6]);
        if (!errorList.contains(7)) survey.setFlightDest(inputReq[7]);
        if (!errorList.contains(10)) {
            survey.setRespFName(inputNon[0]);
            survey.setNonReqInsert(0, true);
        } else {
            errorList.remove((Integer)(10));
            survey.setNonReqInsert(0, false);
        }
        if (!errorList.contains(11)) {
            survey.setRespLName(inputNon[1]);
            survey.setNonReqInsert(1, true);
        } else {
            errorList.remove((Integer)(11));
            survey.setNonReqInsert(1, false);
        }
        if (!errorList.contains(12)) {
            survey.setRespEmail(inputNon[2]);
            survey.setNonReqInsert(2, true);
            
        } else {
            errorList.remove((Integer)(12));
            survey.setNonReqInsert(2, false);
        }
        errorFree = errorList.isEmpty();
        if (errorFree) {
            try {
                beans2DB(request, response);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/surveyUser/finishsurvey.jsp");
                dispatcher.forward(request, response);
            } catch(IOException | ServletException | SQLException e) {
            }
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("error", errorList);
            // forward to error jsp page.  to do later - hopefully javascript will catch errors.
        }
    }
    public void checkSession(HttpServletRequest request, HttpServletResponse response) 
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
        else {
            this.params = new SurveyParams();
            session.setAttribute("params", this.params);
        }
        if (session.getAttribute("survey") != null)
            this.survey = (SurveyBeans)session.getAttribute("survey");
        else {
            this.survey = new SurveyBeans();
            session.setAttribute("survey", this.survey);
        }
    }
    public void beans2DB(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException, SQLException {
        StringBuilder stmtStart = new StringBuilder("insert into airline_survey (");
        StringBuilder stmtEnd = new StringBuilder(") values (");
        
        String sqlQuery = "select airlineID from airline_codes where lower(airlineName) = '" 
                + survey.getAirlineInput().toLowerCase() + "'";
        try {
            ResultSet results = myStat.executeQuery(sqlQuery);
            if (results.next()) {
                String airlineID = results.getString("airlineID");
                stmtStart.append("airlineID, ");
                stmtEnd.append(airlineID).append(", ");
            } else {
                stmtStart.append("airlineInput, ");
                stmtEnd.append("'").append(survey.getAirlineInput()).append("', ");
            }
        } catch(Exception e) {
        }
        String respHost = request.getRemoteHost();
        survey.setRespHost(respHost);
        stmtStart.append("flightNum, flightDest, rtFriend, rtSpace, rtComfort, rtClean, rtNoise, respHost");
        stmtEnd.append("'").append(survey.getFlightNum()).append("', ").
                append("'").append(survey.getFlightDest()).append("', ").
                append(survey.getRtFriend()).append(", ").append(survey.getRtSpace()).append(", ").
                append(survey.getRtComfort()).append(", ").append(survey.getRtClean()).append(", ").
                append(survey.getRtNoise()).append(", '").append(survey.getRespHost()).append("'");
        if (survey.getNonReqInsert(0)) {
            stmtStart.append(", respFName");
            stmtEnd.append(", '").append(survey.getRespFName()).append("'");
        }
        if (survey.getNonReqInsert(1)) {
            stmtStart.append(", respLName");
            stmtEnd.append(", '").append(survey.getRespLName()).append("'");
        }
        if (survey.getNonReqInsert(2)) {
            stmtStart.append(", respEmail");
            stmtEnd.append(", '").append(survey.getRespEmail()).append("'");
        }
        stmtEnd.append(")");
        myStat.executeUpdate(stmtStart.append(stmtEnd).toString());
    }
}
