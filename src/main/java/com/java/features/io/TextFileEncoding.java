package com.java.features.io;
import org.antlr.runtime.UnwantedTokenException;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
/***************************************************************************
 * Java internally stores variables of type char in UCS-2 (UTF-16)
 * Write some text to a file using OutputStreamWriter specifying its
 * character encoding
 * Read byte-by-byte  using BufferedInputStream(FileInputStream)
 * Read char-by-char using InpputStreamReader, specifying a charset encoding
 ****************************************************************************/
public class TextFileEncoding {
    private static Logger logger =LoggerFactory.getLogger(TextFileEncoding.class.getName());
    public static void encodeDecode(){
        String fnameIn = "/home/vangelis/Desktop/Java-Concepts/src/main/resources/-out.txt";
        Path path = Paths.get(fnameIn);
        String fnameOutExt = "/home/vangelis/Desktop/Java-Concepts/src/main/resources";
        String message = "Hi,您好!";   // with non-ASCII chars
        //Print the characters in Hex
        for (int i = 0; i < message.length(); i++) {
            char aChar = message.charAt(i);
            System.out.printf("[%d]'%c'(%04X)",(i+1),aChar,(int)aChar);

        }
        System.out.println();
        String [] charsetArr = new String[7];
        charsetArr= new String[]{"UTF-8", "UTF-16BE", "UTF-16LE", "UTF-16", "GB2312", "GBK", "BIG5"};
        for (int i = 0; i < charsetArr.length; i++) {
            try(OutputStreamWriter writer = new OutputStreamWriter(
                    new BufferedOutputStream(charsetArr[i]+fnameOutExt,charsetArr[i]));
                    InputStreamReader reader = new InputStreamReader(
                            new BufferedInputStream(
                                    new FileInputStream(fname))){


                    }



            ))
        }


    }
}
