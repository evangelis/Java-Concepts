package com.java.features.io;
import java.io.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
public class ObjectSerialization {
    private static final Logger logger =LoggerFactory.getLogger(ObjectSerialization.class);
    public static void writeReadObjects(){
        String fname = "~/Desktop/Java-Concepts/src/main/resources/object.ser";
        try(ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(fname)));
           ObjectInputStream in = new ObjectInputStream(
                   new BufferedInputStream(new FileInputStream(fname)))){
            /**************************************************************************
             *Write an array of (serializable )objects from MySerializedObject class
             * Write these objects to a file 1 by one and the entire array in a go
             * Read raw bytes and print in Hex;then read the objects
              **************************************************************************/
            MySerializedObject[] objectsArr = new MySerializedObject[10];
            for (int i = 0; i < objectsArr.length ; i++) {
                objectsArr[i] = new MySerializedObject(0xAA+i);
            }
            for (int i = 0; i < objectsArr.length ; i++) {
                out.writeObject(objectsArr[i]);
            }
            out.writeObject(objectsArr);
            out.flush();
            int readByte;
            while((readByte =in.read())!=-1)
                System.out.printf("%02X",readByte);
            System.out.printf("%n");
            MySerializedObject serObj;
            MySerializedObject[] serObjArr;
            for (int i = 0; i < objectsArr.length ; i++){
                serObj = (MySerializedObject) in.readObject();
            }
            serObjArr =(MySerializedObject[]) serObj;
        }
        catch (IOException |ClassCastException |ClassNotFoundException ex){
            logger.error("Error",ex);
        }



    }
}
