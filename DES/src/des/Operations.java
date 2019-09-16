/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package des;

/**
 *
 * @author Asus
 */
public class Operations {
    public String pad_data(String str)
    {
        int len=str.length();
        int extra =len%8;
        if(extra>0)
        {
            extra=8-extra;
        }

        String padded_str=new String(str);
        for(int i=0;i<extra;i++)
        {
            padded_str+='~';
        }
         
       return  padded_str;  

        
    }
    public int[] dec_to_bin(char a)
    {
        int num=a;
        //System.out.println(num);
        int[] binary=new int[8];
        int pointer=7;
        while(num>0)
        {
            int remainder=num%2;
            num=num/2;
            binary[pointer--]=remainder;
            
        }
        /*for(int i=0;i<8;i++)
        {
            System.out.print(binary[i]);
        }*/
        return binary;
    }
    
    public int[][] make_blocks(String str)
    {
        int[][] arr=new int[8][8];
        for(int i=0;i<str.length();i++)
        {
            arr[i]=dec_to_bin(str.charAt(i));
            
        }
        return arr;        
    }
    
    public int[][] initial_transposition(int[][] input_matrix_64)
    {

        int[][] ip_vector={{58,50,42,34,26,18,10,2},{60,52,44,36,28,20,12,4},
                        {62,54,46,38,30,22,14,6},{64,56,48,40,32,24,16,8},
                        {57,49,41,33,25,17,9,1},{59,51,43,35,27,19,11,3},
                        {61,53,45,37,29,21,13,5},{63,55,47,39,31,23,15,7}};
        
        int[][] output_matrix=new int[8][8];
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                int r=(ip_vector[i][j]-1)/8;
                int c=(ip_vector[i][j]-1)%8;
                //System.out.println(ip_vector[i][j]+" "+r+" "+c);
                output_matrix[i][j]=input_matrix_64[r][c];
            }
        }
        return output_matrix;
        
    }
    
    public void print_matrix(int[][] mat, int r, int c)
    {

        System.out.println("");
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                System.out.print(mat[i][j]+" ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    public int[][] generate_tester(int r, int c)
    {
        int[][] input_matrix=new int[r][c];
        int count=1;
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                input_matrix[i][j]=count++;
            }
        }
        return input_matrix;
    }
    
    
    
    public int[][] permutate_choice_1(int[][] input_key_64)
    {
        //int[][] input_key_64=generate_tester(8,8);
        
        int[][] modified_key_56=new int[8][7];//8 rows, 7 columns
        int[][] CP_1={{57,49,41,33,25,17,9},{1,58,50,42,34,26,18},
                               {10,2,59,51,43,35,27},{19,11,3,60,52,44,36},
                               {63,55,47,39,31,23,15},{7,62,54,46,38,30,22},
                               {14,6,61,53,45,37,29},{21,13,5,28,20,12,4}};
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<7;j++)
            {
                int r=(CP_1[i][j]-1)/8;
                int c=(CP_1[i][j]-1)%8;
                modified_key_56[i][j]=input_key_64[r][c];
            }
        }
    return modified_key_56;
        //System.out.println("64 to 56 bit transformation of key matrix:");
        //print_matrix(modified_key_56,8,7);   
    }
    
    public int[][] split_left(int[][] mat, int r, int c)
    {
        int[][] left=new int[r/2][c];
        for(int i=0;i<r/2;i++)
        {
            for(int j=0;j<c;j++)
            {
                left[i][j]=mat[i][j];
            }
        }
        return left;
    }
    public int[][] split_right(int[][] mat, int r, int c)
    {
        int[][] right=new int[r/2][c];
        for(int i=r/2;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                right[i-(r/2)][j]=mat[i][j];
            }
        }
        return right;
    }
    
    public int[][] left_circular_shift(int[][] matrix, int r, int c, int round)
    {
        int[] SHIFT = {1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};
        int bits=SHIFT[round];
       // int[][] matrix=generate_tester(r,c);
        int elements=r*c;
        int[] array=new int[elements];
        int pos=0;
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                array[pos++]=matrix[i][j];
            }
        }
        for(int x=0;x<bits;x++)
        {
            int start=array[0];
            for(int i=1;i<elements;i++)
            {
                array[i-1]=array[i];
            }
            array[elements-1]=start;
        }
        int[][] output_mat=new int[r][c];
        pos=0;
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                output_mat[i][j]=array[pos++];
            }
        }
        return output_mat;
    }
    
    
    public int[][] merge(int[][] L, int[][] R, int r, int c)
    {
        int[][] merged_matrix=new int[r*2][c];
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                merged_matrix[i][j]=L[i][j];
            }
        }
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                merged_matrix[i+r][j]=R[i][j];
            }
        }
        //print_matrix(merged_matrix,2*r,c);
        return merged_matrix;
        
    }
    public int[][] permutate_choice_2(int[][] input_key_56/*has 7 columns, 8 rows*/)
    {
        int[][] output_key_48=new int[6][8];//6 rows, 8 columns
        int[][] CP_2 = {{14, 17, 11, 24, 1, 5, 3, 28},
            {15, 6, 21, 10, 23, 19, 12, 4},
            {26, 8, 16, 7, 27, 20, 13, 2},
            {41, 52, 31, 37, 47, 55, 30, 40},
            {51, 45, 33, 48, 44, 49, 39, 56},
            {34, 53, 46, 42, 50, 36, 29, 32}};
        
        for(int i=0;i<6;i++)
        {
            for(int j=0;j<8;j++)
            {
                int r=(CP_2[i][j]-1)/7;
                int c=(CP_2[i][j]-1)%7;
                output_key_48[i][j]=input_key_56[r][c];
            }
        }
        return output_key_48;
       // print_matrix(output_key_48,6,8);
    }
    
   public int[][] expansion_permutation(int[][] input_data_32 /*4 rows, 8 columns*/)
   {
      int[][] E = {{32, 1, 2, 3, 4, 5, 4, 5},
                   {6, 7, 8, 9, 8, 9, 10, 11},
                   {12, 13, 12, 13, 14, 15, 16, 17},
                   {16, 17, 18, 19, 20, 21, 20, 21},
                   {22, 23, 24, 25, 24, 25, 26, 27},
                   {28, 29, 28, 29, 30, 31, 32, 1},
                   };
     int[][] output_data_48=new int[6][8];//6 rows, 8 columns

        for(int i=0;i<6;i++)
        {
            for(int j=0;j<8;j++)
            {
                int r=(E[i][j]-1)/8;
                int c=(E[i][j]-1)%8;
                output_data_48[i][j]=input_data_32[r][c];
            }
        }
        
        return output_data_48; 
   }
    
   public int[][] function_generator(int[][] expanded_R_prev, int[][] key)
   {
       int[][] result_48=new int[6][8];
       for(int i=0;i<6;i++)
       {
            for(int j=0;j<8;j++)
            {
                result_48[i][j]=expanded_R_prev[i][j] ^ key[i][j];
            }
       }
       
       int[][] PI_2 = {{35, 38, 46, 6, 43, 40, 14, 45},
               {33, 19, 26, 15, 23, 8, 22, 10}, 
               {12, 11, 5, 25, 27, 21, 16, 31},
               {28, 32, 34, 24, 9, 37, 2, 1}};
       
       int[][] result_32=new int[4][8];
       for(int i=0;i<4;i++)
       {
           for(int j=0;j<8;j++)
           {
                int r=(PI_2[i][j]-1)/8;
                int c=(PI_2[i][j]-1)%8;
                result_32[i][j]=result_48[r][c];
           }
       }
       
      int[][] P = {{16, 7, 20, 21, 29, 12, 28, 17},
                   {1, 15, 23, 26, 5, 18, 31, 10},
                   {2, 8, 24, 14, 32, 27, 3, 9},
                   {19, 13, 30, 6, 22, 11, 4, 25}};
      
      int[][] final_result_32=new int[4][8];
       for(int i=0;i<4;i++)
       {
           for(int j=0;j<8;j++)
           {
                int r=(P[i][j]-1)/8;
                int c=(P[i][j]-1)%8;
                final_result_32[i][j]=result_32[r][c];
           }
       }
    return final_result_32;   
   }
   
   public int[][] xor_left_func(int[][] left, int[][] func)
   {
       int[][] result=new int[4][8];
       for(int i=0;i<4;i++)
       {
           for(int j=0;j<8;j++)
           {
               result[i][j]=left[i][j]^func[i][j];
           }
       }
       return result;
   }
   public void copy(int[][] a, int[][] b, int r, int c)
   {
       for(int i=0;i<r;i++)
       {
           for(int j=0;j<c;j++)
           {
               a[i][j]=b[i][j];
           }
       }
       
   }
   public int[][] final_transposition(int[][] mat_64)
   {
       int[][] PI_1 = {{40, 8, 48, 16, 56, 24, 64, 32},
                       {39, 7, 47, 15, 55, 23, 63, 31},
                       {38, 6, 46, 14, 54, 22, 62, 30},
                       {37, 5, 45, 13, 53, 21, 61, 29},
                       {36, 4, 44, 12, 52, 20, 60, 28},
                       {35, 3, 43, 11, 51, 19, 59, 27},
                       {34, 2, 42, 10, 50, 18, 58, 26},
                       {33, 1, 41, 9, 49, 17, 57, 25}};
       int[][] output_mat=new int[8][8];
       for(int i=0;i<8;i++)
       {
           for(int j=0;j<8;j++)
           {
                int r=(PI_1[i][j]-1)/8;
                int c=(PI_1[i][j]-1)%8;
                output_mat[i][j]=mat_64[r][c];
           }
       }
       return output_mat;
   }
   

   public int[][][] generate_keys(String key)
   {
       int[][] key_64=make_blocks(key);
       int[][] key_CP1_56=permutate_choice_1(key_64);
       int[][] k_l=split_left(key_CP1_56,8,7);
       int[][] k_r=split_right(key_CP1_56,8,7);
       int[][][] ret=new int[16][6][8]; 
       for(int i=0;i<16;i++)
       {
           //int[][] k_l=split_left(key_CP1_56,8,7);
           //int[][] k_r=split_right(key_CP1_56,8,7);
           k_l=left_circular_shift(k_l,4,7,i);
           k_r=left_circular_shift(k_r,4,7,i);
           int[][] key_56=merge(k_l,k_r,4,7);
           int[][] key_48=permutate_choice_2(key_56);
           ret[i]=key_48;
       }
       return ret;
   }
   
   
   public int[][] iteration_steps(int[][] transposed_data_64, int[][] key_64)
   {
       int[][] key_CP1_56=permutate_choice_1(key_64);
       int[][] left=split_left(transposed_data_64,8,8);
       int[][] right=split_right(transposed_data_64,8,8);
       
       int[][] k_l=split_left(key_CP1_56,8,7);
       int[][] k_r=split_right(key_CP1_56,8,7);
       for(int i=0;i<16;i++)
       {
           //int[][] k_l=split_left(key_CP1_56,8,7);
           //int[][] k_r=split_right(key_CP1_56,8,7);
           k_l=left_circular_shift(k_l,4,7,i);
           k_r=left_circular_shift(k_r,4,7,i);
           int[][] key_56=merge(k_l,k_r,4,7);
           int[][] key_48=permutate_choice_2(key_56);
           
           int[][] expanded_r=expansion_permutation(right);
           int[][] function=function_generator(expanded_r,key_48);
           
           int[][] temp=xor_left_func(left,function);
           //left=right;
           copy(left,right,4,8);
           copy(right,temp,4,8);
             
       }
       //steps 5,6 left
       
       int[][] t=new int[4][8];
       copy(t,left,4,8);
       copy(left,right,4,8);
       copy(right,t,4,8);
       int[][] mat_64=merge(left,right,4,8);
       int[][] final_encrypted_matrix= final_transposition(mat_64);
       return final_encrypted_matrix;
       
   }
   
   public String make_str_snippet(String str, int start)
   {
       //System.out.println(str.length()+", "+start);
       String snippet="";
       for(int i=start;i<start+8;i++)
       {
           snippet+=str.charAt(i);
       }
       return snippet;
   }
   
   public String encryption(String data, String key)
   {
       String padded_data=pad_data(data);
       int[][] key_mat_64=make_blocks(key);
       int len=padded_data.length();
       int number_of_goes=len/8;
       String ciphered="";
       for(int i=0;i<number_of_goes;i++)
       {
           String data_part=make_str_snippet(padded_data,i*8);
           int[][] data_block=make_blocks(data_part);
           int[][] transposed_data_block=initial_transposition(data_block);
           int[][] encrypted_data_block=iteration_steps(transposed_data_block,key_mat_64);
           //print_matrix(encrypted_data_block,8,8);
           //System.out.println("");
           ciphered+=block_to_string(encrypted_data_block,8,8);
           
       } 
       //System.out.println("Ciphered: "+ciphered);
       return ciphered;
       
   }
   
   public void Decryption(String ciphered_text, String key)
   {   
       int[][][] keys=generate_keys(key);
       
       int len=ciphered_text.length();
       int number_of_goes=len/8;
       String deciphered="";
       for(int i=0;i<number_of_goes;i++)
       {
           String t1=make_str_snippet(ciphered_text,i*8);
           int[][] b1=make_blocks(t1);
           int[][] transposed_ciphered_block=initial_transposition(b1);
           int[][] left=split_left(transposed_ciphered_block,8,8);
           int[][] right=split_right(transposed_ciphered_block,8,8);
           for(int j=15;j>=0;j--)
           {
                int[][] key_48=keys[j];
           
                int[][] expanded_r=expansion_permutation(right);
                int[][] function=function_generator(expanded_r,key_48);

                int[][] temp=xor_left_func(left,function);
                //left=right;
                copy(left,right,4,8);
                copy(right,temp,4,8);
           }
           
            int[][] t=new int[4][8];
            copy(t,left,4,8);
            copy(left,right,4,8);
            copy(right,t,4,8);
            int[][] mat_64=merge(left,right,4,8);
            int[][] final_decrypted_matrix= final_transposition(mat_64);
            deciphered+=block_to_string(final_decrypted_matrix,8,8);
           
       }
       System.out.println("Deciphered: "+deciphered);
       
       
   }
   
   
   public String block_to_string(int[][] block, int r, int c)
   {
       String str="";
       for(int i=0;i<r;i++)
       {
           int pow=0;
           int num=0;
           for(int j=block[i].length-1; j>=0;j--)
           {
               num=(int)(num+block[i][j]*Math.pow(2, pow));
               pow++;
           }
           char a=(char)num;
           str+=a;
           
       }
       return str;
   }
   
   
    
}
