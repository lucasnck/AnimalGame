/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;

/**
 * 
 * Configuration class is the basics of the database to be used in DAOs and FileManipulator classes.
 * 
 * THE ROOT IS THE FOLDER THAT GAME IS RUNNING
 * 
 * A folder named db store all entities files like db/Type.db
 *
 * @author lucas
 */
public class Config {
    
    public static final File baseFile = new File("");

    public static final File dataBase = new File(baseFile.getAbsolutePath() + File.separator + "db");

    public static final File typeFile = new File(dataBase.getAbsolutePath() + File.separator + "Types.db");

    public static File animalFile = new File(dataBase.getAbsolutePath() + File.separator + "Animals.db");

    public static File attributeFile = new File(dataBase.getAbsolutePath() + File.separator + "Attributes.db");
    
}
