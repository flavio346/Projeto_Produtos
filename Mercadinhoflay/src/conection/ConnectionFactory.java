/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author flavio
 */
public class ConnectionFactory {
    private static final String DRIVE = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/db_Mercadinho_flav";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection getConnection(){
        try {
            Class.forName(DRIVE);
            
            return DriverManager.getConnection(URL, USER, PASS);
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na Conexao:",ex);
        }
    }
    
    public static void closedConnection(Connection con){
        
            try {
                if(con != null){
                con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
     public static void closedConnection(Connection con,PreparedStatement stmt){
            closedConnection(con);
            try {
                if(stmt != null){
                stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
     public static void closedConnection(Connection con,PreparedStatement stmt,ResultSet rs){
            closedConnection(con,stmt);
            try {
                if(rs != null){
                   rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
      } 
}

    

     
