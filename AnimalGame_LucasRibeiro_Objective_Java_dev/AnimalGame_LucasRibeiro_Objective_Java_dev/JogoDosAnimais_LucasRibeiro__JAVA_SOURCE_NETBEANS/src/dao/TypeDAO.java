/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import util.Config;
import entity.Type;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * DAO for Type entity.
 *
 * @author lucas
 */
public class TypeDAO extends GenericDAO<Type>{

    public TypeDAO() {
        super(Config.typeFile, Type.class);
    }
    
    public Type getByName(String name) {
        if(list() != null) {
            for(Type type : list()) {
                if(type.getName().equals(name)) {
                    return type;
                }
            }
        }
        return null;
    }
    
    public Type getById(Long id) {
        if(list() != null) {
            for(Type type : list()) {
                if(type.getId().equals(id)) {
                    return type;
                }
            }
        }
        return null;
    }
    
    @Override
    public List<Type> list() {
        Gson gson = new Gson();
        List list = null;
        try {
            list = gson.fromJson(new FileReader(getDbFile()), new TypeToken<List<Type>>(){}.getType());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
