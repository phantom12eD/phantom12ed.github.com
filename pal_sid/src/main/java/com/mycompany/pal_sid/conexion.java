package com.mycompany.pal_sid;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author danie
 */
class conexion {
     //MySQLTest conn = new MySQLTest();
   
    Connection conectar = null;
    DefaultTableModel modelo = new DefaultTableModel();
    String username = "root";
    String password = "javer123";
    String bd = "Veterinaria";
    String ip = "localhost";
    String puerto = "3308";
    String dbURL = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;

    public Connection conn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar = DriverManager.getConnection(dbURL, username, password);

        } catch (Exception e) {

        }
        return conectar;
    }
   
}

