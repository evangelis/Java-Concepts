package com.java.features.io;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import kotlin.text.Charsets;
import org.apache.commons.math3.stat.inference.OneWayAnova;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
public class FileCopy {
    private static Logger logger = LoggerFactory.getLogger(FileCopy.class.getName());
    private static String fnameIn = "/home/vangelis/Desktop/Java-Concepts/src/main/resources";
    public static void copyFileStream() {
        String fnameOut = "/home/vangelis/Desktop/Java-Concepts/src/main/resources";
        File file = new File(fnameIn);
        System.out.printf("File :%s ,File size is :%s",file.getName(),file.length()+" bytes");
        System.out.printf("Copying %s using file input & output streams (byte-based non-buffered ",file.getName());
        long startTime,elapsedTime=0;
        try(FileInputStream fin = new FileInputStream(fnameIn);
            FileOutputStream fout = new FileOutputStream(fnameOut)){
            startTime=System.nanoTime();
            int byteRead;
            while((byteRead=fin.read()) !=-1){
                fout.write(byteRead);
            }
            elapsedTime = System.nanoTime() -startTime;
            System.out.println("Elapsed time is "+(elapsedTime/1000000.0)+" msec");
        }
        catch(IOException ex){
            logger.error("Error in copying files",ex.getMessage());
        }

    }

    public static void copyBufferedStream(){
        String fnameOut = "/home/vangelis/Desktop/Java-Concepts/src/main/resources";
        long startTime ,elapsedTime;
        File file =new File(fnameIn);
        System.out.printf("Copying file s% using bufffered byte-based streams",file.getName());

        try(BufferedInputStream bin = new BufferedInputStream(
                new FileInputStream(fnameIn));
            BufferedOutputStream bout = new BufferedOutputStream(
                    new FileOutputStream(fnameOut))){
                startTime =System.nanoTime();
                int byteRead;
                while((byteRead =bin.read())!=-1){
                    bout.write(byteRead);
                }
                elapsedTime = System.nanoTime() -startTime;
            System.out.printf("Elapsed time is :%d %s",(elapsedTime/1000000.00),"msec");
        }
        catch(IOException ex){
            logger.error("Error in copying files ",ex.getMessage());
        }

    }
    public static void copyUserBuffer(){
        String fnameIn = "/home/vangelis/Desktop/Java-Concepts/src/main/resources";
        String fnameOut = "/home/vangelis/Desktop/Java-Concepts/src/main/resources";
        File file = new File(fnameIn);
        System.out.printf("Copying file s% with a user definded buffer",file.getName());
        long startTime,elapsedTime;
        try(FileInputStream fin = new FileInputStream(fnameIn);
            FileOutputStream fout = new FileOutputStream(fnameOut)){
            byte[] byteBuff = new byte[4096];
            startTime = System.nanoTime();
            int bytesRead ;
            while((bytesRead = fin.read())!=-1){
                fout.write(byteBuff,0,bytesRead);
            }
            elapsedTime = System.nanoTime() -startTime;
            System.out.println();
        }
        catch (IOException ex){
            logger.error("Error in copying files %s",ex.getMessage());
        }

    }
    public static void copyCharStream() throws IOException {
        String fnameIn = "/home/vangelis/Desktop/Java-Concepts/src/main/resources";
        String fnameOut = "/home/vangelis/Desktop/Java-Concepts/src/main/resources";
        File file = new File(fnameIn);
        logger.info("Copying file using buffered character-based streams", file.getName());
        long startTime, elapsedTime;
        BufferedWriter bw = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fnameIn));
            bw = new BufferedWriter(new FileWriter(fnameOut));
            startTime = System.nanoTime();
            String inLine;
            while ((inLine = br.readLine()) != null) {
                bw.write(inLine, 0, inLine.length());
            }
            elapsedTime = System.nanoTime() - startTime;
            System.out.println("Elapsed time :" + (elapsedTime / 1000000.00) + " msec");


        } catch (IOException ex) {
            logger.error("Error in copying file", ex.getMessage());
        } finally {
            if (br !=null) br.close();
            if (bw != null) {
                bw.flush();
                bw.close();
            }
        }
    }
    public static void transferBytes(){
        String fnameIn = "/home/vangelis/Desktop/Java-Concepts/src/main/resources";
        String fnameOut = "/home/vangelis/Desktop/Java-Concepts/src/main/resources";
        File file = new File(fnameIn);
        logger.info("Copying file by transferring byte  streams", file.getName());
        long startTime, elapsedTime;
        try(BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fnameIn));
            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(fnameOut))){
            startTime = System.nanoTime();
            long bytes = bin.transferTo(bout);
            elapsedTime = System.nanoTime() -startTime;
            System.out.println("Elapsed time is :"+(elapsedTime/1000000.00)+" msec");
        }
        catch (IOException exception){
            logger.error("Error copying file",exception.getLocalizedMessage());
        }
    }
    public static void transferChars() {
        String fnameIn = "/home/vangelis/Desktop/Java-Concepts/src/main/resources";
        String fnameOut = "/home/vangelis/Desktop/Java-Concepts/src/main/resources";
        File file = new File(fnameIn);
        System.out.printf("Copying file %s by transferring character streams", file.getName());
        long startTime, elapsedTime;
        try (BufferedReader br = new BufferedReader(new FileReader(fnameIn));
             BufferedWriter bw = new BufferedWriter(new FileWriter(fnameOut))) {
            br.transferTo(bw);
        } catch (IOException exception) {
            logger.error("Error copying file", exception.getCause());
        }
    }
    public static void copyByteChars(){
        String fnameIn = "/home/vangelis/Desktop/Java-Concepts/src/main/resources";
        String fnameOut = "/home/vangelis/Desktop/Java-Concepts/src/main/resources";
       // File file = new File(fnameIn);
        Path path = Paths.get(fnameIn);
        logger.info("Copying file using InputStreamReader, InputStreamWriter ", file.getName());
        long startTime, elapsedTime;
        try(InputStreamReader inr = new InputStreamReader(new FileInputStream(fnameIn));
            OutputStreamWriter outw = new OutputStreamWriter(new FileOutputStream(fnameOut), Charsets.UTF_8)) {
            startTime = System.nanoTime();
            char charRead;
            while ((charRead = inr.read()) != null)
                outw.write(charRead);
            elapsedTime = System.nanoTime();
            System.out.println("Elapsed time :" + (elapsedTime / 1000000.00) + " msec");
        }
        catch (IOException ex){
            logger.error("Error copying file",ex.getLocalizedMessage());
        }

    }
    public static void filesBytesCopy(){
        String fnameIn = "/home/vangelis/Desktop/Java-Concepts/src/main/resources";
        String fnameOut ="/home/vangelis/Desktop/Java-Concepts/src/main/resources";
        long startTime,elapsedTime;
        Path path = Paths.get(fnameIn);
        Path path2 = Paths.get(fnameOut);
        System.out.printf("Copying file %s: using Files.readAllBytes()",path.getFileName());
        try{
            startTime =System.nanoTime();
            byte[] bytes;
            bytes =Files.readAllBytes(path);
            Files.write(path2,bytes);
            elapsedTime =(long) (System.nanoTime() -startTime/1000000.00);
            System.out.println();
        }
        catch (IOException ex){
            logger.error("Error copying file",ex);
        }
    }
    public static void  filesCharCopy(){
        String fnameIn = "/home/vangelis/Desktop/Java-Concepts/src/main/resources";
        String fnameOut= "/home/vangelis/Desktop/Java-Concepts/src/main/resources";
        long startTime,elapsedTime;
        Path path = Paths.get(fnameIn);
        Path path2 =Paths.get(fnameOut);
        System.out.printf("Copying file %s: using Files.readAllLines()");
        List<String> lines = new ArrayList<>();
        String aLine;
        try{
            startTime = System.nanoTime();
            lines =Files.readAllLines(path,Charset.forName("UTF-8"));
            Files.write(path2,lines);
            elapsedTime = (long) ((System.nanoTime()-startTime)/1000000.00);
            System.out.println();
        }
        catch (IOException ex){
            logger.error("Error copying file",ex);
        }


    }
}
