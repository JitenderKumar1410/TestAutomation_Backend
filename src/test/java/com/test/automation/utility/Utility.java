package com.test.automation.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utility {

	
	public static String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString().trim();
	    } finally {
	        br.close();
	    }
	}
	
}
