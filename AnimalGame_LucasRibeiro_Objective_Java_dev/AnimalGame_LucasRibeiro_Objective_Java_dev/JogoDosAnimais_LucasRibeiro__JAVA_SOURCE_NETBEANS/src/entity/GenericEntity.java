/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dao.GenericDAO;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * The generic type persist and generate the entities id.
 * All entities have GenericEntity as father.
 *
 * @author lucas
 * @param <T>
 */
public class GenericEntity<T extends GenericEntity> {

    private Long id;

    private GenericDAO objectDAO;

    private Class<?> classDAO;

    public GenericEntity() {
    }

    /**
     * 
     * This constructor forces the entity to pass his respective DAO class.
     * In the constructor a new instance of DAO is created to enable the method getLastID to get a list of respective entities.
     * 
     * @param classDAO 
     */
    public GenericEntity(Class<?> classDAO) {
        this.classDAO = classDAO;
        try {
            objectDAO = (GenericDAO) this.classDAO.newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(GenericEntity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GenericEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        getLastID();
    }

    /**
     * The method check if the data base has entities. With this information generate an ID for entity.
     * 
     * @return 
     */
    public Long getLastID() {
        //List all entities of the parsed dao
        List<GenericEntity> entitys = objectDAO.list();
        //if the database doesn't have entities, set the id of new entity with 1
        if (entitys == null || entitys.isEmpty()) {
            id = Long.parseLong("1");
        } else {
            //if the database have entities, generate a new id with the last entity
            id = entitys.get(entitys.size() - 1).getId();
        }
        //get the id and a random integer to create a new id that doesn't have used before
        id = id + random(0, (int) (id + 100));
        return id;
    }

    /**
     * Random method.
     * 
     * The random method receive the start id like 0 and 
     * the end that is the id of the last entity more 100 diferent possibles
     * 
     * @param start
     * @param end
     * @return 
     */
    private static Integer random(int start, int end) {
        Random r = new Random();
        long range = (long) end - (long) start + 1;
        long fraction = (long) (range * r.nextDouble());
        int randomNumber = (int) (fraction + start);
        return randomNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
