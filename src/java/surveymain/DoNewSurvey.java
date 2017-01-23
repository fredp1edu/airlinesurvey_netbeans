/*
 * This class initializes a surveyParam obj and passes it to the surveyconstructor createSurvey jsp page
 *
 */
package surveymain;

import dbconnect.MakeDBConnection;
import java.io.*;
import java.sql.Statement;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import surveyutilities.*;

@WebServlet("/doNewSurvey")
public class DoNewSurvey extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        Statement myStat;
        SurveyParams params;
        HttpSession session = request.getSession();
        if (session.getAttribute("myStat") != null)
            myStat = (Statement)session.getAttribute("myStat");
        else {
            MakeDBConnection myConnect = new MakeDBConnection();
            myStat = myConnect.getStatement();
            session.setAttribute("myStat", myStat);
        }
        if (session.getAttribute("survey") != null)
            session.removeAttribute("survey");
        SurveyBeans survey = new SurveyBeans();
            session.setAttribute("survey", survey);
        if (session.getAttribute("params") != null)
            params = (SurveyParams)session.getAttribute("params");
        else {
            params = new SurveyParams();
            session.setAttribute("params", params);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/surveyUser/createsurvey.jsp");
	dispatcher.forward(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        doGet(request, response);
    }
}
