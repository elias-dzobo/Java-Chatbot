// JAVA implementation of tree using array
// numbering starting from 0 to n-1.
import java.util.*;
import java.lang.*;
import java.io.*;
import java.io.*;
import java.util.Scanner;
 
class Tree {
    public static void main(String[] args)
    {

        Array_imp user_tree = new Array_imp();

        user_tree.Root("tomato ");

        /**
         * user ask for discount on item - 
         * bot offers a discount passage - 
         * if user accepts 
         *      conclude user satisfaction 
         * else
         *      conclude dissatisfaction
         * propose counter discount
         * if user accepts 
         *      conclude mild satisfaction 
         * else 
         *      conclude gross dissatisfaction 
         */

         // demo 


          try {
            File file = new File("data.txt");

            Scanner myReader = new Scanner(file);

            int count = 0;
            while (myReader.hasNextLine()){
                String data = myReader.nextLine();
                if ((data.contains("discount") || data.contains("reduce")) && data.contains("human")){
                    user_tree.set_Left("PS4", 0);
                } else if (data.contains("okay") || data.contains("Thank You")) {
                    user_tree.set_Left("accepted initial discount", 1);
                } else if ((data.contains("reduce") || data.contains("willing")) && data.contains("human")){
                    user_tree.set_Right("rejected initial discount", 1);
                } else if (data.contains("sorry") && data.contains("bot")){
                    user_tree.set_Left("mild dissatisafaction", 2);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }

        user_tree.print_Tree();
          
       
    }
}
 
class Array_imp {
    static int root = 0;
    static String[] str = new String[10];
 
    /*create root*/
    public static void Root(String key)
    {
        str[0] = key;
    }
 
    /*create left son of root*/
    public static void set_Left(String key, int root)
    {
        int t = (root * 2) + 1;
 
        if (str[root] == null) {
            System.out.printf("Can't set child at %d, no parent found\n", t);
        }
        else {
            str[t] = key;
        }
    }
 
    /*create right son of root*/
    public static void set_Right(String key, int root)
    {
        int t = (root * 2) + 2;
 
        if (str[root] == null) {
            System.out.printf("Can't set child at %d, no parent found\n", t);
        }
        else {
            str[t] = key;
        }
    }
 
    public void print_Tree()
    {
        for (int i = 0; i < 10; i++) {
            if (str[i] != null)
                System.out.print(str[i]);
            else
                System.out.print("-");
        }
    }
}