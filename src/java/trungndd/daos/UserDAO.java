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
import trungndd.dtos.ActorDTO;
import trungndd.dtos.DirectorDTO;
import trungndd.dtos.UserDTO;

/**
 *
 * @author Admin
 */
public class UserDAO implements Serializable {

    private static Connection con;
    private static PreparedStatement preStm;
    private static ResultSet rs;

    public static void closeConnection() throws Exception {
        MyConnection.closeConnection(con, preStm, rs);
    }

    public static UserDTO checkLogin(String username, String password) throws Exception {
        UserDTO dto = null;

        try {
            String sql = "Select Role, IdUser From tblUser where Username = ? and Password = ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            rs = preStm.executeQuery();

            if (rs.next()) {
                String role = rs.getString("Role");
                String id = rs.getString("IdUser");
                dto = new UserDTO();
                dto.setRole(role);
                dto.setId(id);
            }
        } finally {
            closeConnection();
        }

        return dto;
    }

    public static boolean add(UserDTO dto) throws Exception {
        boolean check = false;

        try {
            String sql = "Insert into tblUser Values(?,?,?,?,?,?,?,?,?,?)";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, dto.getId());
            preStm.setString(2, dto.getImg());
            preStm.setString(3, dto.getDesc());
            preStm.setString(4, dto.getPhone());
            preStm.setString(5, dto.getEmail());
            preStm.setString(6, dto.getUsername());
            preStm.setString(7, dto.getPassword());
            preStm.setString(8, dto.getRole());
            preStm.setString(9, dto.getFirstName());
            preStm.setString(10, dto.getLastName());

            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    public static Vector<UserDTO> searchByName(String name) throws Exception {
        Vector<UserDTO> result = new Vector<>();

        try {
            String sql = "Select IdUser, DescUser, ImageUser, Role, "
                    + "FirstName, LastName From tblUser Where Username LIKE ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, "%" + name.trim() + "%");
            rs = preStm.executeQuery();

            while (rs.next()) {
                String idUser = rs.getString("IdUser");
                String role = rs.getString("Role");
                String imgUser = rs.getString("ImageUser");
                String desc = rs.getString("DescUser");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");

                UserDTO dto = new UserDTO(idUser, imgUser, desc, "", "", "", role, firstName, lastName);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public static UserDTO getUserById(String id) throws Exception {
        UserDTO dto = null;

        try {
            String sql = "Select IdUser, Username, DescUser, Phone, Email, Role, ImageUser, "
                    + "FirstName, LastName From tblUser Where IdUser = ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();

            if (rs.next()) {
                String idUser = rs.getString("IdUser");
                String username = rs.getString("Username");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String role = rs.getString("Role");
                String imgUser = rs.getString("ImageUser");
                String desc = rs.getString("DescUser");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");

                dto = new UserDTO(idUser, imgUser, desc, phone, username, email, role, firstName, lastName);
            }
        } finally {
            closeConnection();
        }

        return dto;
    }

    public static boolean update(UserDTO dto) throws Exception {
        boolean check = false;

        try {
            String sql = "UPDATE dbo.tblUser SET ImageUser = ?, DescUser = ?, Phone = ?, "
                    + "Email = ?, Username = ?, Role = ?, FirstName = ?, LastName = ? WHERE IdUser = ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, dto.getImg());
            preStm.setString(2, dto.getDesc());
            preStm.setString(3, dto.getPhone());
            preStm.setString(4, dto.getEmail());
            preStm.setString(5, dto.getUsername());
            preStm.setString(6, dto.getRole());
            preStm.setString(7, dto.getFirstName());
            preStm.setString(8, dto.getLastName());
            preStm.setString(9, dto.getId());

            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    public static boolean delete(String id) throws Exception {
        boolean check = false;

        try {
            String sql = "Delete From tblUser Where IdUser = ?";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, id);
            check = preStm.executeUpdate() > 0;

        } finally {
            closeConnection();
        }

        return check;
    }

    public static Vector<UserDTO> searchActorByName(String name) throws Exception {
        Vector<UserDTO> result = new Vector<>();

        try {
            String sql = "Select IdUser, DescUser, ImageUser, "
                    + "FirstName, LastName From tblUser Where Username LIKE ? And Role = 'actor'";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, "%" + name.trim() + "%");
            rs = preStm.executeQuery();

            while (rs.next()) {
                String idUser = rs.getString("IdUser");
                String imgUser = rs.getString("ImageUser");
                String desc = rs.getString("DescUser");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");

                UserDTO dto = new UserDTO(idUser, imgUser, desc, "", "", "", "actor", firstName, lastName);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public static Vector<UserDTO> getAllDirectors() throws Exception{
        Vector<UserDTO> result = null;
        
        try {
            String sql = "Select IdUser, FirstName, LastName From tblUser Where Role = 'director'";
            con = MyConnection.getConnection();
            preStm = con.prepareStatement(sql);
            
            rs = preStm.executeQuery();
            
            while(rs.next()){
                String idUser = rs.getString("IdUser");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                
                UserDTO dto = new UserDTO();
                dto.setId(idUser);
                dto.setFirstName(firstName);
                dto.setLastName(lastName);
                
                if (result == null) {
                    result = new Vector<>();
                }
                result.add(dto);
            }
        } finally {
        }
        
        return result;
    }
}
