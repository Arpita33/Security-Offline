/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package des;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class DES {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
        Operations op=new Operations();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Key:");
        String key=sc.nextLine();
        System.out.println("Enter text to be encoded:");
        String str=sc.nextLine();
        String c=op.encryption(str, key );
        System.out.println("Ciphered: "+c);
        op.Decryption(c, key);


    }
    
}
