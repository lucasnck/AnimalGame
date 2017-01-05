/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dao.TypeDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 * 
 * Types is an entity that persists habitats of animals
 * 
 */
public class Type extends GenericEntity<Type> implements Serializable {
    
    private String name;
    
    private List<Long> animalsID;

    public Type() {
    }
    
    public Type(String typeName) {
        setId(new GenericEntity(TypeDAO.class).getLastID());
        this.name = typeName;
    }

    public Type(Long id, String typeName) {
        setId(id);
        this.name = typeName;
    }
    
    @Override
    public String toString() {
        return "Type{" + "id=" + getId() + ", name=" + name + ", animalsID=" + animalsID + '}';
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
