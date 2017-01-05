/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * File manipulator was implemented for write or read files of the game.
 * 
 * This class with the entity DAO is the DataBase layer
 *
 * @author lucas
 */
public class FileManipulator {

    /**
     * 
     * Get the content in String of the file
     * 
     * @param file
     * @return 
     */
    public String readFile(File file) {
        String everything = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                everything = sb.toString();
            } catch (IOException ex) {
                Logger.getLogger(FileManipulator.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(FileManipulator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManipulator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(FileManipulator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return everything;
    }
    
    /**
     * 
     * Store the datas in the file
     * 
     * @param file
     * @param content 
     */
    public void saveStringToFile(File file, String content) {
        PrintWriter out;
        try {
            out = new PrintWriter(file);
            out.println(content);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BaseBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
