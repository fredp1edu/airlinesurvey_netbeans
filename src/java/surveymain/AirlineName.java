/*
 * This is the servlet that receives the airline name input from the main airline form, accesses the airline database 
 *  and gives autocomplete results for the input field. currently the form just uses a hidden select box to list the 
 *  names but I may switch it to another utility i found online or use JQuery UI
 */
package surveymain;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import dbconnect.*;

@WebServlet("/getAirline")
public class AirlineName extends HttpServlet {
    
    Statement myStat;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session.getAttribute("myStat") != null)
            this.myStat = (Statement)session.getAttribute("myStat");
        else {
            MakeDBConnection myConnect = new MakeDBConnection();
            this.myStat = myConnect.getStatement();
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String inputText = request.getParameter("aChar");
        int numChars = inputText.length();
        String sqlQuery = "select airlineName from airline_codes where substr(airlineName,1," +numChars+ ") = '" + 
                inputText + "' order by airlineName";
        try {
            ResultSet results = myStat.executeQuery(sqlQuery);
            while (results.next()) {
                String name = results.getString("airlineName");
                out.println(name + ",");
            }
        } catch (Exception e) {
            out.println("No can do!");
        }
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        doGet(request, response);
    }
}
