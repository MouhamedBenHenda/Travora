package org.example.travora.model;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Slf4j
public class FileUtils {
    private  FileUtils(){}

    public  static byte[] redFileFromlocation (String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return new byte[0] ;
        }
    try{
        Path file = Paths.get(filePath) ;
        return Files.readAllBytes(file) ;

    }catch (IOException e) {
        log.warn("no file found in url");
    }
 return new byte[0] ;

    }
}
