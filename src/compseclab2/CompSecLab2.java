/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compseclab2;

import java.io.BufferedReader;
import java.io.File;
import java.security.*;
import java.math.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Keinan Balsam Matthew Wentz
 */
public class CompSecLab2 {
    public static void main(String[] args) {       
        String[] hashValues= {"6f047ccaa1ed3e8e05cde1c7ebc7d958", "275a5602cd91a468a0e10c226a03a39c", "b4ba93170358df216e8648734ac2d539", "dc1c6ca00763a1821c5af993e0b6f60a", "8cd9f1b962128bd3d3ede2f5f101f4fc", "554532464e066aba23aee72b95f18ba2"};
        readFile(hashValues);
    }
    /**
     * Use MD5 encryption algorithm on a dictionary
     * of passwords, then compare to given hash values to find unencrypted
     * password.
     */
    public static void readFile(String[] hashValues) {
        File file = new File(".\\src\\compseclab2\\john.txt");
        FileReader fr = null;
        try{
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            Logger.getLogger(CompSecLab2.class.getName()).log(Level.SEVERE, null, e);
        }
        BufferedReader br = new BufferedReader(fr);
        String line;
        
        //Read file line by line
        try{
            while((line = br.readLine()) != null) {
                String cipherText = md5(line);
                for(String val : hashValues) {if(cipherText.equals(val)){ System.out.println("Password: "+br.readLine()+" Hash value: "+cipherText); }};
            }
        } catch (IOException e) {
            Logger.getLogger(CompSecLab2.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public static String md5(String plainText) {
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CompSecLab2.class.getName()).log(Level.SEVERE, null, ex);
        }
        m.update(plainText.getBytes(), 0, plainText.length());
        return (new BigInteger(1, m.digest()).toString(16));
    }
}
