package com.example.user.bhaktivedantdietapp;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionClass {

    public static Connection CONN() {
        String ip = "192.168.5.22:53532";
         //   String ip = "192.168.5.22\\ApexUAT";
        String classs =  "net.sourceforge.jtds.jdbc.Driver";
        String db = "DietApp";
        String un = "DietApp";
        String pw = "DietApp@123";
        Connection conn = null;
        String ConnURL = null;
        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            Log.w("class ok","abc");
            ConnURL = "jdbc:jtds:sqlserver://"+ ip + ";databaseName=" + db + ";user=" + un + ";password=" + pw + ";";
            Log.w("Connection URL;",ConnURL);
            conn = DriverManager.getConnection(ConnURL);
        }
        catch (SQLException se){
            Log.e("Error1", se.getMessage());
        }
        catch (ClassNotFoundException e){
            Log.e("Error2", e.getMessage());
        }
        catch (Exception e){
            Log.e("Error3", e.getMessage());
        }
        return conn;
    }

}

