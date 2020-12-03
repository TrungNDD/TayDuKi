/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.resources;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

/**
 *
 * @author Admin
 */
public class MyProperties extends Properties implements Serializable {

    private static final String FILE = "trungndd/resources/Resources.properties";
    private static final String DIR_FILE = "trungndd/resources/Dir_Resources.properties";
    private static final String ACTOR_FILE = "trungndd/resources/Actor_Resources.properties";

    public MyProperties(String role) throws IOException {
        super();
        if (role.matches("director")) {
            this.load(getClass().getClassLoader().getResourceAsStream(DIR_FILE));
        } else if (role.matches("actor")) {
            this.load(getClass().getClassLoader().getResourceAsStream(ACTOR_FILE));
        } else {
            this.load(getClass().getClassLoader().getResourceAsStream(FILE));
        }
        
    }

    public String getMyProperty(String key){
        return this.getProperty(key);
    }
}
