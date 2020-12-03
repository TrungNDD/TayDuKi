/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.dtos;

import java.util.HashMap;

/**
 *
 * @author ADMIN
 */
public class ActorDTO extends UserDTO{
    private HashMap<String, ActorRoleDTO> rolesMap;
    
    public ActorDTO() {
    }

    public ActorDTO(String id, String img, String desc, String phone, String username, 
            String email, String firstName, String lastName) {
        super(id, img, desc, phone, username, email, "actor", firstName, lastName);
    }

    public HashMap<String, ActorRoleDTO> getRolesMap() {
        return rolesMap;
    }

    public void setRolesMap(HashMap<String, ActorRoleDTO> rolesMap) {
        this.rolesMap = rolesMap;
    }
    
    
}
