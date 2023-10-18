/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Asus
 */
public class MyBD {
        //
    String url = "jdbc:mysql://localhost:3306/pidev4se1";
    String user = "root";
    String pwd = "";
    
    
    Connection con;
    
    //3 
    static MyBD instance;
     //1 rendre le constructeur prive
    private MyBD() {
        
        try {
            con = DriverManager.getConnection(url, user, pwd);
            
            System.out.println("connexion etablie");
        } catch (SQLException ex) {
            System.out.println("Probeleme de connexion");
        }
    }
    
    // 2 etape: de creer une methode static pour utiliser le const 
    public static MyBD getinstance(){
        if(instance == null){
            instance =  new MyBD();
        }
        return instance;
        
    }

    public Connection getCon() {
        return con;
    }
    
    
}
