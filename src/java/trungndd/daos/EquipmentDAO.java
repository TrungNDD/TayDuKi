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
import trungndd.dtos.EquipmentDTO;

/**
 *
 * @author Admin
 */
public class EquipmentDAO implements Serializable {

    private static Connection con;
    private static PreparedStatement preStm;
    private static ResultSet rs;

    static void closeConnection() throws Exception {
        MyConnection.closeConnection(con, preStm, rs);
    }

    public static boolean add(EquipmentDTO dto) throws Exception {
        boolean check = false;

        try {
            String sql = "Insert Into Equipment Values(?,?,?,?,?,?)";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, dto.getIdEquip());
            preStm.setString(2, dto.getNameEquip());
            preStm.setString(3, dto.getDescEquip());
            preStm.setString(4, dto.getImgEquip());
            preStm.setInt(5, dto.getEquipCount());
            preStm.setBoolean(6, dto.isAvailable());

            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    public static Vector<EquipmentDTO> searchByName(String search) throws Exception {
        Vector<EquipmentDTO> result = new Vector<>();

        try {
            String sql = "Select IdEquip, NameEquip, DescEquip, "
                    + "ImageEquip, EquipCount From Equipment Where NameEquip LIKE ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");

            rs = preStm.executeQuery();

            while (rs.next()) {
                EquipmentDTO dto = new EquipmentDTO();
                String idEquip = rs.getString("IdEquip");
                dto.setIdEquip(idEquip);
                String nameEquip = rs.getString("NameEquip");
                dto.setNameEquip(nameEquip);
                String descEquip = rs.getString("DescEquip");
                dto.setDescEquip(descEquip);
                String imgEquip = rs.getString("ImageEquip");
                dto.setImgEquip(imgEquip);
                int equipCount = rs.getInt("EquipCount");
                dto.setEquipCount(equipCount);

                result.add(dto);
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public static EquipmentDTO getEquipById(String idEquip) throws Exception {
        EquipmentDTO dto = null;

        try {
            String sql = "Select IdEquip, NameEquip, DescEquip, ImageEquip, EquipCount, Available"
                    + " From Equipment Where IdEquip = ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, idEquip);

            rs = preStm.executeQuery();

            if (rs.next()) {
                String nameEquip = rs.getString("NameEquip");
                String descEquip = rs.getString("DescEquip");
                String imgEquip = rs.getString("ImageEquip");
                int equipCount = rs.getInt("EquipCount");
                boolean available = rs.getBoolean("Available");

                dto = new EquipmentDTO(idEquip, nameEquip, descEquip, imgEquip, equipCount, available);
            }
        } finally {
            closeConnection();
        }

        return dto;
    }

    public static boolean update(EquipmentDTO dto) throws Exception {
        boolean check = false;

        try {
            String sql = "Update Equipment Set NameEquip = ?, DescEquip = ?, EquipCount = ?, Available = ? "
                    + "Where IdEquip = ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, dto.getNameEquip());
            preStm.setString(2, dto.getDescEquip());
            preStm.setInt(3, dto.getEquipCount());
            preStm.setBoolean(4, dto.isAvailable());
            preStm.setString(5, dto.getIdEquip());

            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    public static boolean delete(String idEquip) throws Exception {
        boolean check = false;

        try {
            String sql = "Delete From Equipment Where IdEquip = ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, idEquip);

            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

//    public static Vector<EquipmentDTO> getStatistics(boolean available) throws Exception {
//        Vector<EquipmentDTO> result = null;
//
//        try {
//            String sql = "SELECT Equipment.IdEquip, NameEquip, DescEquip, ImageEquip, Available, DateStart, DateFinish, UsedEquipCount "
//                    + "FROM dbo.Equipment, dbo.Life, dbo.EquipDetails "
//                    + "WHERE Equipment.IdEquip = EquipDetails.IdEquip AND EquipDetails.IdLife = Life.IdLife And Available = ? "
//                    + "ORDER BY DateStart, DateFinish";
//            con = MyConnection.getConnection();
//            preStm = con.prepareStatement(sql);
//            preStm.setBoolean(1, available);
//            rs = preStm.executeQuery();
//            
//            while(rs.next()){
//                String 
//            }
//        } finally {
//        }
//
//        return result;
//    }
}
