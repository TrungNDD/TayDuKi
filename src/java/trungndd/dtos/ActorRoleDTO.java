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
public class ActorRoleDTO implements Serializable{
    private String idActor, idLife, nameRole, descRole;
    private UserDTO actor;
    private LifeDTO life;

    public ActorRoleDTO() {
    }

    public ActorRoleDTO(String idActor, String idLife, String nameRole, String descRole) {
        this.idActor = idActor;
        this.idLife = idLife;
        this.nameRole = nameRole;
        this.descRole = descRole;
    }

    public String getIdActor() {
        return idActor;
    }

    public void setIdActor(String idActor) {
        this.idActor = idActor;
    }

    public String getIdLife() {
        return idLife;
    }

    public void setIdLife(String idLife) {
        this.idLife = idLife;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public String getDescRole() {
        return descRole;
    }

    public void setDescRole(String descRole) {
        this.descRole = descRole;
    }

    public UserDTO getActor() {
        return actor;
    }

    public void setActor(UserDTO actor) {
        this.actor = actor;
    }

    public LifeDTO getLife() {
        return life;
    }

    public void setLife(LifeDTO life) {
        this.life = life;
    }
    
    
}
