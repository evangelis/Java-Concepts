package com.java.features.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
public class ScannerFormatter {
    private static Logger logger = LoggerFactory.getLogger(ScannerFormatter.class);
    public static void scannerKeyboard(){
        Scanner sc = new Scanner(System.in);
        int anInt;
        System.out.println("Enter an integer");
        anInt = sc.nextInt();
        System.out.println("You entered :"+anInt);
        double aDouble;
        System.out.println("Enter a floating-point number");
        aDouble = sc.nextDouble();
        System.out.println("Enter 2 words:");
        var word1 = sc.next();
        var word2 = sc.next();
        System.out.println("You entered :"+word1 +","+word2);
        sc.nextLine();//flush
        System.out.println("Enter a line :" );
        System.out.println("You entered :"+sc.nextLine());
    }
    public static void scannerFile() throws IOException {
        String file ="/home/vangelis/Desktop/Java-Concepts/src/resources/in.txt";
        Path path = Paths.get(file);
        Scanner sc = new Scanner(path);
        System.out.println("Enter an integer :");
        int anInt;
        System.out.println("You entered :"+sc.nextInt());
        float aFloat;
        System.out.println("Enter a floating-point number :");
        System.out.println("You entered :"+ sc.nextFloat());
        System.out.println("Enter 2 words");
        String word1 = sc.next();
        String word2 =sc.next();
        System.out.println("You entered : "+word1+","+word2);
        sc.nextLine();
        System.out.println("Enter a line :");
        String aLine =sc.nextLine();
        System.out.printf("You entered %s",aLine);
    }
    public static void scannerDelim(){
        Scanner in = new Scanner("one apple 2 apple red apple big apple 5.5 apple");
        /*********************************************************************************
         * Specify delimiter:
         Zero or more whitespace followed by apple followed by zero or more whitespace
         Input:one apple 2 apple red apple big apple 5.5 apple
         Tokens {one,2,red,big,5.5}
         *******************************************************************************/
        in.useDelimiter("\\S*apple\\S*");
        System.out.print(in.next()+","+in.nextInt()+","+in.next()+","+in.next()+","+
                in.nextDouble());//parse text into double
        System.out.println();
    }
    public static void scannerTextFile(){
        String filename ="/home/vangelis/Desktop/Java-Concepts/src/resources/scanner_text.txt";
        String message = "Hi,您好!\n";   // with non-ASCII chars
        try(BufferedWriter bw = new BufferedWriter(
                new FileWriter(filename));
            BufferedInputStream bin = new BufferedInputStream(
                    new FileInputStream(filename))){
            bw.write("12345 55.66\n");
            bw.write(message);
            bw.flush();
            //read raw bytes and print in hex
            int inByte;
            while ((inByte =bin.read())!=-1)
                System.out.printf("%02X",inByte);
            System.out.println();
        }
        catch (IOException ex){
            logger.error("Error ",ex);
        }
        //Open the text file ,specifying the character set
        try(Scanner scanner = new Scanner(Paths.get(filename),"UTF_8");){
            System.out.println(scanner.nextInt());
            System.out.println(scanner.nextDouble());
            scanner.nextLine(); //flush a new line
            System.out.println(scanner.nextLine());
        }
        catch (IOException ex){
            logger.error("Scanner error",ex);
        }
    }
    public static void  formatterScanner() throws IOException{
        String filename ="/home/vangelis/Desktop/Java-Concepts/src/resources/formatter_out_text.txt";
        String message = "Hi,您好!";
        /***** Create a text file in UTF-8 ,write some formatted text (output)
         *  Read the raw bytes and print them in hex
         *  Use Scanner to read the above created text file
         */
         try(Formatter fmt = new Formatter(filename,"UTF_8");
             Scanner sc = new Scanner(Paths.get(filename),"UTF_8");
             BufferedInputStream bin = new BufferedInputStream(
                     new FileInputStream(filename))) { //throws IOException

             fmt.format("%4d %6.2f %s%n",0xAA,55.66,message);
             fmt.flush();
             int inByte;
             while ((inByte =bin.read())!=-1)
                 System.out.printf("%02X",inByte);
             System.out.println();
             System.out.println(sc.nextInt());
             System.out.println(sc.nextDouble());
             System.out.println(sc.next());
             System.out.println();
         }
         catch (FileNotFoundException| UnsupportedEncodingException ex){
             logger.error("Error ",ex);
         }
    }

}
