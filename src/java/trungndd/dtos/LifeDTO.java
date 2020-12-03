/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.dtos;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;
import trungndd.daos.EquipmentDAO;

/**
 *
 * @author ADMIN
 */
public class LifeDTO implements Serializable {

    private String idLife, idDirector, nameLife, descLife, location, nameDirector;
    private int noOfShoot;
    private Date dateStart, dateFinish;
    private HashMap<String, ActorRoleDTO> listRoles;
    private HashMap<String, EquipmentDTO> listEquips;
    private String nameRole;

    public LifeDTO() {
    }

    public LifeDTO(String idLife, String idDirector, String nameLife, String descLife,
            String location, int noOfShoot, Date dateStart, Date dateFinish) {
        this.idLife = idLife;
        this.idDirector = idDirector;
        this.nameLife = nameLife;
        this.descLife = descLife;
        this.location = location;
        this.noOfShoot = noOfShoot;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }

    public String getIdLife() {
        return idLife;
    }

    public void setIdLife(String idLife) {
        this.idLife = idLife;
    }

    public String getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(String idDirector) {
        this.idDirector = idDirector;
    }

    public String getNameLife() {
        return nameLife;
    }

    public void setNameLife(String nameLife) {
        this.nameLife = nameLife;
    }

    public String getDescLife() {
        return descLife;
    }

    public void setDescLife(String descLife) {
        this.descLife = descLife;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNoOfShoot() {
        return noOfShoot;
    }

    public void setNoOfShoot(int noOfShoot) {
        this.noOfShoot = noOfShoot;
    }

    public HashMap<String, ActorRoleDTO> getListRoles() {
        return listRoles;
    }

    public void setListRoles(HashMap<String, ActorRoleDTO> listRoles) {
        this.listRoles = listRoles;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public String getNameDirector() {
        return nameDirector;
    }

    public void setNameDirector(String nameDirector) {
        this.nameDirector = nameDirector;
    }

    public HashMap<String, EquipmentDTO> getListEquips() {
        return listEquips;
    }

    public void setListEquips(HashMap<String, EquipmentDTO> listEquips) {
        this.listEquips = listEquips;
    }

    public void addEquip(String idEquip, int quantity) throws Exception {
        EquipmentDTO dto = listEquips.get(idEquip);
        if (dto != null) {
            quantity += dto.getEquipCount();
        } else {
            dto = EquipmentDAO.getEquipById(idEquip);
        }

        dto.setEquipCount(quantity);
        listEquips.put(idEquip, dto);
    }

    public void updateEquipCount(String idEquip, int quantity) throws Exception {
        EquipmentDTO dto = listEquips.get(idEquip);
        if (dto == null) {
            dto = EquipmentDAO.getEquipById(idEquip);
        }
        dto.setEquipCount(quantity);
        listEquips.put(idEquip, dto);
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }
    
    
}
