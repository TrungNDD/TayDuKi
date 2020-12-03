/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.dtos;

import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class DirectorDTO extends UserDTO{
    private HashMap<String, LifeDTO> livesMap;

    public DirectorDTO() {
    }

    public DirectorDTO(String id, String img, String desc, String phone, String username, String email,
            String firstName, String lastName) {
        super(id, img, desc, phone, username, email, "director", firstName, lastName);
    }

    public HashMap<String, LifeDTO> getLivesMap() {
        return livesMap;
    }

    public void setLivesMap(HashMap<String, LifeDTO> livesMap) {
        this.livesMap = livesMap;
    }
}
