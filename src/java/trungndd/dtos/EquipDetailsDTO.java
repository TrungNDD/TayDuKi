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
public class EquipDetailsDTO implements Serializable{
    private String idLife, idEquip;
    private int quantity;

    public EquipDetailsDTO() {
    }

    public EquipDetailsDTO(String idLife, String idEquip, int quantity) {
        this.idLife = idLife;
        this.idEquip = idEquip;
        this.quantity = quantity;
    }

    public String getIdLife() {
        return idLife;
    }

    public void setIdLife(String idLife) {
        this.idLife = idLife;
    }

    public String getIdEquip() {
        return idEquip;
    }

    public void setIdEquip(String idEquip) {
        this.idEquip = idEquip;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
