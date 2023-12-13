package com.java.features.io;
import java.io.*;

import org.apache.hadoop.yarn.webapp.hamlet2.Hamlet;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
/***********************************************************
 * Program writes some primitives to a disk file &
 *  reads the raw bytes (checking storage) and reads data
 *  as primitives
 *
 ************************************************************/
public class DataIOStream {
    private static Logger logger = LoggerFactory.getLogger(DataIOStream.class.getName());

    public static void primitives(){
        String fnameIn = "/home/vangelis/Desktop/Java-Concepts/src/main/resources";
        String message = "Hi,您好!";
        PrintStream psOut = new PrintStream(new File());
        System.setOut(psOut);
        try(DataOutputStream dout = new DataOutputStream(
                new FileOutputStream(fnameIn))){
            dout.writeByte(127);
            dout.writeShort(-1);
            dout.writeInt(0xABCD);
            dout.writeLong(0x1234_5678);
            dout.writeFloat(11.22f);
            dout.writeDouble(55.66);
            dout.writeBoolean(true);
            dout.writeBoolean(false);
            //write the message
            dout.writeBytes(message);
            dout.writeChars(message);
            for (int i = 0; i < message.length(); i++) {
                dout.writeChar(message.charAt(i));
            }
        }
        catch (IOException ex){
            logger.error("Error writing primitive values to the file",ex.getMessage());
        }
        try(BufferedInputStream bin = new BufferedInputStream(
                new FileInputStream(fnameIn))){
            int byteRead;
            while((byteRead = bin.read())!=-1)
                System.out.printf("%02X",byteRead);
        }catch (IOException ex){
            logger.error("Error ",ex);
        }

        try(DataInputStream din = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(fnameIn)))){
            System.out.println("byte : "+din.readByte());
            System.out.println("short : "+din.readShort());
            System.out.println("int :"+din.readInt());
            System.out.println("long : "+din.readLong());
            System.out.println("float : "+din.readFloat());
            System.out.println("double : "+din.readDouble());
            System.out.println("boolean : "+din.readBoolean());
            System.out.println("boolean : "+din.readBoolean());
            System.out.print("bytes :");
            for (int i = 0; i <message.length() ; i++) {
                System.out.print((char) din.readByte());
            }

            System.out.print("chars");
            for (int i = 0; i < message.length(); i++) {
                System.out.print(din.readChar());
            }
            System.out.println();
        }
        catch (IOException ex){
            logger.error("Error",ex);
        }





    }

}
