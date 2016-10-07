package com.cee.ljr.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class FileUtil {

	public static String getAbsolutePath(String filePath) {
		String cleanFilePath = filePath.replace("\\", "/");
		URI uri = null;
		
		try {
			uri = new URI(cleanFilePath);
		} 
		catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		if (uri.isAbsolute()) {
			return cleanFilePath;
		}
		
		return System.getProperty("user.dir") + cleanFilePath;
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
