/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import trungndd.db.MyConnection;
import trungndd.dtos.EquipDetailsDTO;
import trungndd.dtos.EquipmentDTO;

/**
 *
 * @author Admin
 */
public class EquipDetailsDAO implements Serializable{
    
    private static Connection con;
    private static PreparedStatement preStm;
    private static ResultSet rs;

    public static void closeConnection() throws Exception {
        MyConnection.closeConnection(con, preStm, rs);
    }
    
    public static boolean addListEquips(String idLife, HashMap<String, EquipmentDTO> listEquips) throws Exception{
        boolean check = false;
        
        try {
            String sql = "EXEC updateEquipDetails @idLife = ?, @idEquip = ?, @quantity = ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            con.setAutoCommit(false);
            
            preStm.setString(1, idLife);
            for (EquipmentDTO dto : listEquips.values()) {
                preStm.setString(2, dto.getIdEquip());
                preStm.setInt(3, dto.getEquipCount());
                preStm.executeUpdate();
            }
            
            con.commit();
            con.setAutoCommit(true);
            check = true;
        } finally {
            closeConnection();
        }
        
        return check;
    }
    
    public static boolean addEquipDetails(EquipDetailsDTO dto) throws Exception{
        boolean check = false;
        
        try {
            String sql = "EXEC updateEquipDetails @idLife = ?, @idEquip = ?, @quantity = ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, dto.getIdLife());
            preStm.setString(2, dto.getIdEquip());
            preStm.setInt(3, dto.getQuantity());
            
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        
        return check;
    }

    public static boolean deleteEquipDetails(EquipDetailsDTO dto) throws Exception{
        boolean check = false;
        
        try {
            String sql = "Delete From EquipDetails Where IdLife = ? AND IdEquip = ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, dto.getIdLife());
            preStm.setString(2, dto.getIdEquip());
            
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        
        return check;
    }
}
