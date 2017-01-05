/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dao.AnimalDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Animal is an entity that persists the animals of real world.
 * Is animals fish, dog, cat, elephant, and so on.
 * 
 * @author lucas
 * 
 */
public class Animal extends GenericEntity<Animal> implements Serializable {

    private String name;

    private Long typeID;

    private List<Long> attributesID;

    public Animal() {
    }

    public Animal(String name, Long typeID) {
        setId(new GenericEntity(AnimalDAO.class).getLastID());
        this.name = name;
        this.typeID = typeID;
    }

    public Animal(Long id, String name, Long typeID) {
        setId(id);
        this.name = name;
        this.typeID = typeID;
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + getId() + ", name=" + name + ", type=" + typeID + '}';
    }

    public void add(Long attributeID) {
        if (attributesID == null) {
            attributesID = new ArrayList<>();
        }
        attributesID.add(attributeID);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLong() {
        return typeID;
    }

    public void setLong(Long type) {
        this.typeID = type;
    }

    public List<Long> getAttributesID() {
        return attributesID;
    }

    public void setAttributesID(List<Long> attributesID) {
        this.attributesID = attributesID;
    }

    public Long getTypeID() {
        return typeID;
    }

    public void setTypeID(Long typeID) {
        this.typeID = typeID;
    }

}
