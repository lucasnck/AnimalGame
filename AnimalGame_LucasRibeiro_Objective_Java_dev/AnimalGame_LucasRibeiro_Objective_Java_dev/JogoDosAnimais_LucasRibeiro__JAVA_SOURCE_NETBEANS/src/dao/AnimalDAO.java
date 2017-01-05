/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import util.Config;
import entity.Animal;
import entity.Type;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * DAO for the entity ANIMAL
 *
 * @author lucas
 */
public class AnimalDAO extends GenericDAO<Animal> {

    public AnimalDAO() {
        super(Config.animalFile, Animal.class);
    }

    public Animal getByName(String name) {
        if (list() != null) {
            for (Animal item : list()) {
                if (item.getName().equals(name)) {
                    return item;
                }
            }
        }
        return null;
    }

    public List<Animal> listByType(Type type) {
        List<Animal> animals = new ArrayList<>();
        if (list() != null) {
            for (Animal item : list()) {
                if (item.getTypeID().equals(type.getId())) {
                    animals.add(item);
                }
            }
        }
        return animals;
    }

    public Animal getById(Long id) {
        if (list() != null) {
            for (Animal item : list()) {
                if (item.getId().equals(id)) {
                    return item;
                }
            }
        }
        return null;
    }

    public List<Animal> listAnimalByAttribute(List<Animal> list, Long attributeID) {
        List<Animal> animals = new ArrayList<>();
        if (list != null) {
            for (Animal item : list) {
                if (item.getAttributesID() != null) {
                    if (item.getAttributesID().size() > 0) {
                        for (Long id : item.getAttributesID()) {
                            if (id.equals(attributeID)) {
                                animals.add(item);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return animals;
    }

    public List<Animal> listAnimalDontHaveThisAttribute(List<Animal> list, Long attributeID) {
        List<Animal> animals = new ArrayList<>();
        if (list != null) {
            for (Animal item : list) {
                boolean has = false;
                if (item.getAttributesID() != null) {
                    for (Long id : item.getAttributesID()) {
                        if (id.equals(attributeID)) {
                            has = true;
                            break;
                        }
                    }
                    if (!has) {
                        animals.add(item);
                    }
                } else {
                    animals.add(item);
                }
            }
        }
        return animals;
    }

    @Override
    public List<Animal> list() {
        Gson gson = new Gson();
        List list = null;
        try {
            list = gson.fromJson(new FileReader(getDbFile()), new TypeToken<List<Animal>>() {
            }.getType());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
