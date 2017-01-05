/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dao.AttributeDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Attributes is an entity that persists features or characteristics of animals.
 * The attributes can be in portuguese Morde, Rosna, Pula, Cheira, Canta, Voa, etc.
 * 
 * @author lucas
 * 
 */
public class Attribute extends GenericEntity<Attribute> implements Serializable {
    
    private String name;
    
    private List<Long> animalsID;

    public Attribute() {
    }

    public Attribute(String name) {
        setId(new GenericEntity(AttributeDAO.class).getLastID());
        this.name = name;
    }

    public Attribute(Long id, String name) {
        setId(id);
        this.name = name;
    }
    
    public void add(Long animalID) {
        if(animalsID == null) {
            animalsID = new ArrayList<>();
        }
        animalsID.add(animalID);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getAnimalsID() {
        return animalsID;
    }

    public void setAnimalsID(List<Long> animalsID) {
        this.animalsID = animalsID;
    }

}
