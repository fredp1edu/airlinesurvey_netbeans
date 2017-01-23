/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconnect;

import java.sql.*;

public class MakeDBConnection {
    
    private final String dbName = "jdbc:mysql://localhost:8898/airline_dbs";
    private final String userName = "root";
    private final String password = "root";
    private Connection myConnect;
    private Statement mystat;
    
    public MakeDBConnection() {
        setup();
    }
    private void setup() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            myConnect = DriverManager.getConnection(dbName, userName, password);
            mystat = myConnect.createStatement();
            
        } catch (ClassNotFoundException | SQLException e) {
            mystat = null;
        }
    }
    public Statement getStatement() {
        return mystat;
    }        
}
