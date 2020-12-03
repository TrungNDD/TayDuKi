/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.dtos;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class EquipmentDTO implements Serializable{
    private String idEquip, nameEquip, descEquip, imgEquip;
    int equipCount;
    boolean available;

    public EquipmentDTO() {
    }

    public EquipmentDTO(String idEquip, String nameEquip, String descEquip, 
            String imgEquip, int equipCount, boolean available) {
        this.idEquip = idEquip;
        this.nameEquip = nameEquip;
        this.descEquip = descEquip;
        this.imgEquip = imgEquip;
        this.equipCount = equipCount;
        this.available = available;
    }

    public String getIdEquip() {
        return idEquip;
    }

    public void setIdEquip(String idEquip) {
        this.idEquip = idEquip;
    }

    public String getNameEquip() {
        return nameEquip;
    }

    public void setNameEquip(String nameEquip) {
        this.nameEquip = nameEquip;
    }

    public String getDescEquip() {
        return descEquip;
    }

    public void setDescEquip(String descEquip) {
        this.descEquip = descEquip;
    }

    public String getImgEquip() {
        return imgEquip;
    }

    public void setImgEquip(String imgEquip) {
        this.imgEquip = imgEquip;
    }

    public int getEquipCount() {
        return equipCount;
    }

    public void setEquipCount(int equipCount) {
        this.equipCount = equipCount;
    }


    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    
}
