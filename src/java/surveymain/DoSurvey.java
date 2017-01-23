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
import surveyutilities.SurveyParams;

@WebServlet("/doSurvey")
public class DoSurvey extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        
        MakeDBConnection myConnect = new MakeDBConnection();
        Statement myStat = myConnect.getStatement();
        //redirect to error page if database connection lost
        if (myStat == null)
            response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Not able to connect to DB");  
        SurveyParams params = new SurveyParams();
        
        HttpSession session = request.getSession();
        session.setAttribute("myStat", myStat);
        session.setAttribute("params", params);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/surveyUser/createsurvey.jsp");
	dispatcher.forward(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        doGet(request, response);
    }
}
