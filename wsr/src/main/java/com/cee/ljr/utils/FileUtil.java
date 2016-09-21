package com.cee.ljr.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class FileUtil {

	public static String getAbsolutePath(String filePath) {
		URI uri = null;
		
		try {
			uri = new URI(filePath);
		} 
		catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		if (uri.isAbsolute()) {
			return filePath;
		}
		
		return System.getProperty("user.dir") + filePath;
	}
	
	
	public static boolean isValidPath(String path) {

        try {
            Paths.get(path);
        }catch (InvalidPathException |  NullPointerException ex) {
            return false;
        }

        return true;
    }
}
