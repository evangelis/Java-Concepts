package com.java.features.io;
import java.io.*;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.FileVisitResult;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class RecursiveWalkDirectory  extends SimpleFileVisitor<Path>{
    private static Logger logger = LoggerFactory.getLogger(RecursiveWalkDirectory.class.getName());

    //Print the directory visited
    @Override
    public FileVisitResult preVisitDirectory(Path dir,BasicFileAttributes attrs){
        System.out.printf("Begin directory :%s %n",dir);
        return FileVisitResult.CONTINUE;
    }
    //Print the directory visited
    @Override
    public FileVisitResult postVisitDirector(Path dir,IOException ex){
        System.out.printf("End directory :%s %n",dir);
        return FileVisitResult.CONTINUE;
    }
    //Print info about each file/symbolic link
    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs){
        if (attrs.isSymbolicLink()){
            System.out.printf("Symbolic Link :%s",path);
        } else if (attrs.isRegularFile()) {
            System.out.printf("File :%s",path);
        }
        else System.out.printf("Other :%s",path);
        System.out.println("("+attrs.size() +" bytes)");
        return FileVisitResult.CONTINUE;
    }
    //If there is an error while visiting a file ,print a message and continue
    @Override
    public FileVisitResult visitFileFailed(Path path ,IOException ex){
        logger.error("Error visiting file :",ex);
        return FileVisitResult.CONTINUE;
    }
    public static void walk(Path startPath){
        try{
            Paths.get(startPath.toString());
        }
        catch (FileNotFoundException ex){
            logger.error("Error",ex);
        }
    }
}
