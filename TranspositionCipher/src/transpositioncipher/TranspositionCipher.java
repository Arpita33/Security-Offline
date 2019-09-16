/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transpositioncipher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class TranspositionCipher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        
        File file = new File("D:\\4-1\\CS-lab\\transposition-33.txt"); 
        try{
            Scanner sc=new Scanner(file);
            
            String str,str2;
            str=sc.nextLine();
            String[] pat=new String[4];
            for(int i = 0; i < 4; i++){
                str2 = sc.next();
                str2 = str2.trim();
                str2 = str2.replace(',', ' ');
                str2 = str2.trim();
                pat[i]=str2;
            }
          
            helper h=new helper();
            h.get_all_output(str, pat);

        
        }
        catch(FileNotFoundException f ){
            System.out.println("File not found, exception thrown");
        }
        
        

    }
    
}
