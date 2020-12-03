/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import trungndd.db.MyConnection;
import trungndd.dtos.ActorRoleDTO;
import trungndd.dtos.EquipmentDTO;
import trungndd.dtos.LifeDTO;

/**
 *
 * @author Admin
 */
public class LifeDAO implements Serializable {

    private static Connection con;
    private static PreparedStatement preStm;
    private static ResultSet rs;

    static void closeConnection() throws Exception {
        MyConnection.closeConnection(con, preStm, rs);
    }

    public static LifeDTO getLifeById(String id) throws Exception {
        LifeDTO dto = null;

        try {
            String sql = "Select IdLife, IdDirector, "
                    + "NameLife, DescLife, LocationLife, DateStart, DateFinish, NoOfShoot"
                    + " from Life Where IdLife = ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();

            if (rs.next()) {
                String idLife = rs.getString("IdLife");
                String nameLife = rs.getString("NameLife");
                String descLife = rs.getString("DescLife");
                String locationLife = rs.getString("LocationLife");
                Date dateStart = rs.getDate("DateStart");
                Date dateFinish = rs.getDate("DateFinish");
                int noOfShoot = rs.getInt("NoOfShoot");
                String idDirector = rs.getString("IdDirector");

                dto = new LifeDTO(idLife, idDirector, nameLife, descLife, 
                        locationLife, noOfShoot, dateStart, dateFinish);
            }
        } finally {
            closeConnection();
        }

        return dto;
    }

    public static boolean add(LifeDTO dto) throws Exception {
        boolean check = false;

        try {
            String sql = "Insert into Life values(?,?,?,?,?,?,?,?)";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, dto.getIdLife());
            preStm.setString(2, dto.getIdDirector());
            preStm.setString(3, dto.getNameLife());
            preStm.setString(4, dto.getDescLife());
            preStm.setString(5, dto.getLocation());
            preStm.setDate(6, dto.getDateStart());
            preStm.setDate(7, dto.getDateFinish());
            preStm.setInt(8, dto.getNoOfShoot());

            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    public static Vector<LifeDTO> searchLifeByName(String search) throws Exception {
        Vector<LifeDTO> result = new Vector<>();
        try {
            String sql = "Select IdLife, NameLife, DescLife, FirstName, LastName "
                    + "FROM Life, tblUser "
                    + "WHERE Life.IdDirector = tblUser.IdUser AND NameLife LIKE ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, "%" + search.trim() + "%");
            rs = preStm.executeQuery();

            while (rs.next()) {
                LifeDTO dto = new LifeDTO();
                dto.setIdLife(rs.getString("IdLife"));
                dto.setNameLife(rs.getString("NameLife"));
                dto.setDescLife(rs.getString("DescLife"));
                dto.setNameDirector(rs.getString("FirstName") + rs.getString("LastName"));
                
                result.add(dto);
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public static boolean update(LifeDTO dto) throws Exception{
        boolean check = false;

        try {
            String sql = "Update Life Set IdDirector = ?, NameLife = ?, "
                    + "DescLife = ?, LocationLife = ?, DateStart = ?, "
                    + "DateFinish = ?, NoOfShoot = ? Where IdLife = ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, dto.getIdDirector());
            preStm.setString(2, dto.getNameLife());
            preStm.setString(3, dto.getDescLife());
            preStm.setString(4, dto.getLocation());
            preStm.setDate(5, dto.getDateStart());
            preStm.setDate(6, dto.getDateFinish());
            preStm.setInt(7, dto.getNoOfShoot());
            preStm.setString(8, dto.getIdLife());

            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }
    
    public static boolean delete(String id) throws Exception{
        boolean check = false;
        
        try {
            String sql = "Delete From Life Where IdLife = ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, id);
            
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        
        return check;
    }
    
    public static Vector<LifeDTO> searchLifeDirector(String search, String idDirector) throws Exception {
        Vector<LifeDTO> result = new Vector<>();
        try {
            String sql = "Select IdLife, NameLife, DescLife "
                    + "FROM Life "
                    + "WHERE NameLife LIKE ? AND IdDirector = ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, "%" + search.trim() + "%");
            preStm.setString(2, idDirector);
            rs = preStm.executeQuery();

            while (rs.next()) {
                LifeDTO dto = new LifeDTO();
                dto.setIdLife(rs.getString("IdLife"));
                dto.setNameLife(rs.getString("NameLife"));
                dto.setDescLife(rs.getString("DescLife"));
                
                result.add(dto);
            }
        } finally {
            closeConnection();
        }

        return result;
    }
    
    public static HashMap<String, EquipmentDTO> getListEquips(String idLife) throws Exception{
        HashMap<String, EquipmentDTO> result = null;
        
        try {
            String sql = "Select EquipDetails.IdEquip, NameEquip, UsedEquipCount, ImageEquip "
                    + "From EquipDetails, Equipment "
                    + "Where EquipDetails.IdEquip = Equipment.IdEquip AND  IdLife = ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, idLife);
            rs = preStm.executeQuery();
            
            while (rs.next()){
                String idEquip = rs.getString("IdEquip");
                int quantity = rs.getInt("UsedEquipCount");
                String name = rs.getString("NameEquip");
                String imgEquip = rs.getString("ImageEquip");
                
                if (result == null) {
                    result = new HashMap<>();
                }
                
                EquipmentDTO dto = new EquipmentDTO();
                dto.setIdEquip(idEquip);
                dto.setNameEquip(name);
                dto.setEquipCount(quantity);
                dto.setImgEquip(imgEquip);
                result.put(idEquip, dto);
            }
        } finally {
            closeConnection();
        }
        
        return result;
    }

    public static Vector<LifeDTO> searchLifeActor(String idActor) throws Exception{
        Vector<LifeDTO> result = new Vector<>();
        
        try {
            String sql = "Select Life.IdLife, NameLife, DescLife, NameRole "
                    + "FROM Life, ActorRole "
                    + "WHERE IdActor = ? AND Life.IdLife = ActorRole.IdLife";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, idActor);
            rs = preStm.executeQuery();

            while (rs.next()) {
                LifeDTO dto = new LifeDTO();
                dto.setIdLife(rs.getString("IdLife"));
                dto.setNameLife(rs.getString("NameLife"));
                dto.setDescLife(rs.getString("DescLife"));
                dto.setNameRole(rs.getString("NameRole"));
                
                result.add(dto);
            }
        } finally {
            closeConnection();
        }

        return result;
    }
    
    public static Vector<LifeDTO> searchLifeHistory(String idActor) throws Exception{
        Vector<LifeDTO> result = new Vector<>();
        
        try {
            String sql = "Select Life.IdLife, NameLife, DescLife, NameRole "
                    + "FROM Life, ActorRole "
                    + "WHERE IdActor = ? AND Life.IdLife = ActorRole.IdLife AND DateFinish < GETDATE()";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, idActor);
            rs = preStm.executeQuery();

            while (rs.next()) {
                LifeDTO dto = new LifeDTO();
                dto.setIdLife(rs.getString("IdLife"));
                dto.setNameLife(rs.getString("NameLife"));
                dto.setDescLife(rs.getString("DescLife"));
                dto.setNameRole(rs.getString("NameRole"));
                
                result.add(dto);
            }
        } finally {
            closeConnection();
        }

        return result;
    }
    
    public static Vector<LifeDTO> searchUpcomingLife(String idActor) throws Exception{
        Vector<LifeDTO> result = new Vector<>();
        
        try {
            String sql = "Select Life.IdLife, NameLife, DescLife, NameRole "
                    + "FROM Life, ActorRole "
                    + "WHERE IdActor = ? AND Life.IdLife = ActorRole.IdLife AND DateStart >= GETDATE()";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, idActor);
            rs = preStm.executeQuery();

            while (rs.next()) {
                LifeDTO dto = new LifeDTO();
                dto.setIdLife(rs.getString("IdLife"));
                dto.setNameLife(rs.getString("NameLife"));
                dto.setDescLife(rs.getString("DescLife"));
                dto.setNameRole(rs.getString("NameRole"));
                
                result.add(dto);
            }
        } finally {
            closeConnection();
        }

        return result;
    }
}
