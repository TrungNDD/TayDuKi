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
import java.util.Vector;
import trungndd.db.MyConnection;
import trungndd.dtos.ActorRoleDTO;
import trungndd.dtos.UserDTO;

/**
 *
 * @author Admin
 */
public class ActorRoleDAO implements Serializable {

    private static Connection con;
    private static PreparedStatement preStm;
    private static ResultSet rs;

    public static void closeConnection() throws Exception {
        MyConnection.closeConnection(con, preStm, rs);
    }

    public static boolean add(ActorRoleDTO dto) throws Exception {
        boolean check = false;

        try {
            String sql = "Insert into ActorRole values(?,?,?,?)";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, dto.getIdActor());
            preStm.setString(2, dto.getIdLife());
            preStm.setString(3, dto.getNameRole());
            preStm.setString(4, dto.getDescRole());

            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    public static Vector<ActorRoleDTO> getAllRole(String idLife) throws Exception {
        Vector<ActorRoleDTO> result = null;

        try {
            String sql = "SELECT IdActor, FirstName, LastName, NameRole, DescRole, ImageUser "
                    + "FROM ActorRole, tblUser "
                    + "WHERE IdActor = IdUser and IdLife = ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, idLife);
            
            rs = preStm.executeQuery();
            
            while(rs.next()){
                String idActor = rs.getString("IdActor");
                String nameRole = rs.getString("NameRole");
                String descRole = rs.getString("DescRole");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String imgUser = rs.getString("ImageUser");
                
                if (result == null) {
                    result = new Vector<>();
                }
                
                UserDTO actor = new UserDTO();
                actor.setFirstName(firstName);
                actor.setLastName(lastName);
                actor.setImg(imgUser);
                ActorRoleDTO dto = new ActorRoleDTO(idActor, idLife, nameRole, descRole);
                dto.setActor(actor);
                result.add(dto);
            }
        } finally {
        }

        return result;
    }
    
    public static boolean delete(String idLife, String idActor) throws Exception{
        boolean check = false;
        
        try {
            String sql = "Delete From ActorRole Where IdLife = ? And IdActor = ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, idLife);
            preStm.setString(2, idActor);
            
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        
        return check;
    }

    public static boolean update(ActorRoleDTO dto) throws Exception{
        boolean check = false;
        
        try {
            String sql = "Update ActorRole Set NameRole = ?, DescRole = ? Where IdLife = ? AND IdActor = ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, dto.getNameRole());
            preStm.setString(2, dto.getDescRole());
            preStm.setString(3, dto.getIdLife());
            preStm.setString(4, dto.getIdActor());
            
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        
        return check;
    }

    public static ActorRoleDTO getActorRole(String idLife, String idActor) throws Exception{
        ActorRoleDTO dto = null;
        
        try {
            String sql = "Select NameRole, DescRole From ActorRole Where IdLife = ? AND IdActor = ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, idLife);
            preStm.setString(2, idActor);
            rs = preStm.executeQuery();
            
            if (rs.next()) {
                String nameRole = rs.getString("NameRole");
                String descRole = rs.getString("DescRole");
                
                dto = new ActorRoleDTO(idActor, idLife, nameRole, descRole);
            }
        } finally {
            closeConnection();
        }
        
        return dto;
    }
}
