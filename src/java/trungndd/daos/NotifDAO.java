/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import trungndd.db.MyConnection;

/**
 *
 * @author Admin
 */
public class NotifDAO {
    
    private static Connection con;
    private static PreparedStatement preStm;
    private static ResultSet rs;

    static void closeConnection() throws Exception {
        MyConnection.closeConnection(con, preStm, rs);
    }
    
    public static Vector<String> getAllNotif(String idUser) throws Exception{
        Vector<String> result = null;
        
        try {
            String sql = "Select Top 10 DescNotif, DateNotif From tblNotif Where IdUser = ? Order by DateNotif";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, idUser);
            rs = preStm.executeQuery();
            
            while (rs.next()) {                
                String notif = rs.getString("DescNotif") + " (";
                notif += rs.getString("DateNotif") + ")";
                
                if (result == null) {
                    result = new Vector<>();
                }
                result.add(notif);
            }
        } finally {
            closeConnection();
        }
        
        return result;
    }
}
