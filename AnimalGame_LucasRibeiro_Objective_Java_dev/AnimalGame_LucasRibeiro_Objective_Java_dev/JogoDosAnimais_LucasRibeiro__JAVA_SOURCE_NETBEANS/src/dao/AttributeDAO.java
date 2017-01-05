/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import util.Config;
import entity.Attribute;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * DAO for the Attribute entity
 *
 * @author lucas
 */
public class AttributeDAO extends GenericDAO<Attribute>{

    public AttributeDAO() {
        super(Config.attributeFile, Attribute.class);
    }
    
    public Attribute getByName(String name) {
        if(list() != null) {
            for(Attribute item:  list()) {
                if(item.getName().equals(name)) {
                    return item;
                }
            }
        }
        return null;
    }
    
    public Attribute getById(Long id) {
        if(list() != null) {
            for(Attribute item : list()) {
                if(item.getId().equals(id)) {
                    return item;
                }
            }
        }
        return null;
    }
    
    @Override
    public List<Attribute> list() {
        Gson gson = new Gson();
        List list = null;
        try {
            list = gson.fromJson(new FileReader(getDbFile()), new TypeToken<List<Attribute>>(){}.getType());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
