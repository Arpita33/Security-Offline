/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transpositioncipher;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class helper {
    int[] key_order;
    int key_length;
    String plaintext;
    String original_cipher;
    String encoded_cipher;
    double percentage_difference;
    FileWriter outFile;
    FileOutputStream out;
    
    public void print_mat(char[][] arr)
    {   
        int r= arr.length;
        int c=arr[0].length;
        
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                System.out.print(arr[i][j]);
            }
            System.out.println("");
        }
    }
    public char[][] row_to_column(String str, int col)
    {
        int len=str.length();
        int m=len%col;
        int r=len/col;
        char[][] ret=new char[r][col];
        int count=0;
        for(int j=0;j<col;j++)
        {
            for(int i=0;i<r;i++)
            {
                ret[i][j]=str.charAt(count++);
            }
        }
        return ret;
        
    }
    public String block_to_line(char[][] arr, int c)
    {
        int r=arr.length;
        String str="";
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                str+=arr[i][j];
            }
        }
        return str;
    }
    public void print_int_mat(int[][] mat)
    {
        int row=mat.length;
        int col=mat[0].length;
        System.out.println("");
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                System.out.print(mat[i][j]);
            }
            System.out.println("");
        }
    }
    public char[][] column_changing(char[][] input_matrix,  int col, int[] position)
    {
        int row=input_matrix.length;
        char[][] new_matrix=new char[row][col];
        for(int i=0;i<col;i++)
        {
            int c=position[i];
            for(int j=0;j<row;j++)
            {
                new_matrix[j][i]=input_matrix[j][c];
            }
        }
        return new_matrix;
    }
    public void print_array(int[] arr)
    {
        for(int i=0;i<arr.length;i++)
        {
            System.out.print(arr[i]+", ");
        }
        System.out.println("");
    }
    
    public void permutation(int[] array, int start, int end, List<int[]> mylist)
    {
        if(start==end)
        {
            int[] a= new int[array.length];
            for(int i=0;i<array.length;i++)
            {
                a[i]=array[i];
            }
            mylist.add(a);
        }
        else
        {
            for (int i = start; i <= end; i++) 
            { 
                exchange(array,start,i); 
                
                permutation(array, start+1, end,mylist);
                exchange(array,start,i); 
            }
        }
    }
    
    public void exchange(int[] array, int a, int b)
    {
        int temp=array[b];
        array[b]=array[a];
        array[a]=temp;
    }
    
    public List<int[]> generate_key_permutations(int key_len)
    {
        int[] arr =new int[key_len];
        for(int i=0;i<key_len;i++)
        {
            arr[i]=i;
        }
        int p=1;
        for(int i=2;i<=key_len;i++)
        {
            p=p*i;
        }

        List<int[]> myList = new ArrayList<int[]>();
        permutation(arr,0,key_len-1,myList);
        return myList;

      
    }
    
    public void get_all_output(String str, String[] patterns)
    {
       decode(str, patterns);
       plain_to_cipher();
       try{
           outFile = new FileWriter("output.txt");
           if(outFile == null){
               System.out.println("File not opened");
           }
           String temp = "Key length = ";
           outFile.write( temp + Integer.toString(key_length));
           outFile.write("\n\n");
           temp="key order = ";
           outFile.write(temp);
           for(int i=0;i<key_length;i++)
           {
               outFile.write( Integer.toString(key_order[i])+", ");
           }
           outFile.write("\n\n");
           temp="Original Cipher:\n\n";
           outFile.write(temp+original_cipher);
           outFile.write("\n\n");
           temp="Plain text that has been found:\n\n";
           outFile.write(temp+plaintext);
           outFile.write("\n\n");
           outFile.write("Cipher text after decoding with the key: \n\n"+encoded_cipher);
           outFile.write("\n\n");
           outFile.write("Percentage difference = "+percentage_difference);
           outFile.flush();
       }catch(Exception e){
           e.printStackTrace();
       }
       
    }
    
    
    
    public boolean string_matching(String str, String pattern)
    {
        String s1=str.toLowerCase();
        String p1=pattern.toLowerCase();

        if(s1.contains(p1))
        {
            //System.out.println("Found "+ pattern);
            return true;
        }
        else
        {
            //System.out.println("Not found");
            return false;
        }
    }
    
    
    public boolean decode(String str, String[] pattern)
    {
        int len=str.length();
        original_cipher=str;
        for(int i=2;i<=len;i++)
        {
            if((len%i)==0)
            {
                List<int[]> all_permutations=generate_key_permutations(i);
                char[][] b1=row_to_column(str,i);

                for(int j=0;j<all_permutations.size();j++)
                {
                    int[] key=all_permutations.get(j);
                    char[][] b2=column_changing(b1,i,key);
                    String new_str=block_to_line(b2,i);
                    

                    boolean var=true;
                    for(int k=0;k<pattern.length;k++)
                    {
                        if(string_matching(new_str,pattern[k])==false)
                        {
                            var=false;
                        }
                    }
                    if(var==true)
                    {
                        key_order=key;
                        key_length=i;
                        plaintext=new_str;
                        return true;
                    }
                    
                }
            }
        }
        System.out.println("false");
        return false;
    }
    
    
    public void plain_to_cipher()
    {
        int len=plaintext.length();
        int row=len/key_length;
        char[][] block=new char[row][key_length];
        int count=0;
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<key_length;j++)
            {
                block[i][j]=plaintext.charAt(count++);
            }
        }

        char[][] recreated_block=new char[row][key_length];
        for(int i=0;i<key_length;i++)
        {
            int c=key_order[i];
            for(int j=0;j<row;j++)
            {
                recreated_block[j][c]=block[j][i];
            }
        }
        
        String cipher="";
        for(int j=0;j<key_length;j++)
        {
            for(int i=0;i<row;i++)
            {
                cipher+=recreated_block[i][j];
            }
        }
        encoded_cipher=cipher;
        double differences=0;
        for(int i=0;i<len;i++)
        {
            if(original_cipher.charAt(i)!=encoded_cipher.charAt(i))
            {
                differences++;
            }
        }
        percentage_difference=(differences/len)*100.0;
    }
    
    
}
