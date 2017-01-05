/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import util.Config;
import controller.FileManipulator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * GenericDAO is the father of all DAOs.
 * 
 * This class receive in parameter the type of the Entity.
 * 
 * A complete CRUD can be implemented in this method.
 * 
 * The dbFile is the persistence file that store all data about an entity.
 *
 * @author lucas
 * @param <T>
 */
public abstract class GenericDAO<T> {
    
    /**
     * DB FILE IN CONFIG CLASS
     */
    private final java.io.File dbFile;
    
    /**
     * THE CLASS OF ENTITY THAT THE DAO PERSIST
     */
    private final Class<?> entity;
    
    /**
     * 
     * This construct force the DAO to pass the DBFIle that stores the datas and a class o the entity like Type.class
     * 
     * @param dbFile
     * @param entity 
     */
    public GenericDAO(File dbFile, Class<?> entity) {
        this.dbFile = dbFile;
        this.entity = entity;
        checkDB();
    }
    
    private void checkDB() {
        if (!Config.dataBase.exists()) {
            Config.dataBase.mkdir();
        }
        if(!dbFile.exists()) {
            try {
                dbFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * 
     * Using GSON the dao convert the list of objects in JSON String and using the writer method of FileManipulator storage the datas.
     * 
     * @param objs 
     */
    public void save(List<T> objs) {
        //instantiate the Gson
        Gson gson = new Gson();
        //convert the list of objects in an array of the entity
        String json = gson.toJson(objs);
        //call FileManipulator to write the JSON text to the File
        new FileManipulator().saveStringToFile(dbFile, json);
    }
    
    public void update(T objs) {
        List<T> list = list();
        boolean updated = false;
        if(list != null) {
            int index = 0;
            for(Object o : list) {
                if(o.equals(objs)) {
                    list.remove(index);
                    list.add(objs);
                    save(list);
                    updated = true;
                    break;
                }
                index++;
            }
        }
        if(!updated) {
            list.add(objs);
            save(list);
        }
    }
    
    public void delete(T object) {}
    
    public void read() {}
    
    public List list() {
        Gson gson = new Gson();
        List list = null;
        try {
            list = gson.fromJson(new FileReader(getDbFile()), new TypeToken<List>(){}.getType());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public File getDbFile() {
        return dbFile;
    }

    public Class<?> getEntity() {
        return entity;
    }
    
}
