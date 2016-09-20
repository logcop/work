package com.cee.ljr.utils;

import java.net.URI;
import java.net.URISyntaxException;

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
}
