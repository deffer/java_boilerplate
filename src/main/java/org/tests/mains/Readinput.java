package org.tests;

import java.io.*;

/**
 * author: Irina Benediktovich - http://plus.google.com/+IrinaBenediktovich
 */
public class Readinput {

	public static final String SYS_SEPARATOR = System.getProperty("line.separator");

	public static void main(String[] args) throws IOException {

		String currentLine, filename = "";
		BufferedReader inputFile, br;

		System.out.println("Enter File Name:");
		br = new BufferedReader(new InputStreamReader(System.in));
		filename = br.readLine();

		System.out.println("Opening file " + filename);
		inputFile = new BufferedReader(new FileReader(filename));


		BufferedWriter bw = new BufferedWriter(new FileWriter("c:/output"+System.currentTimeMillis()+".txt", false));

		currentLine = inputFile.readLine();
		while(currentLine != null){
			currentLine = inputFile.readLine();
			// do stuff
			bw.write("currentLine"+SYS_SEPARATOR); // sneak write into file :)
		}

		System.out.println("Closing file " + filename);
		inputFile.close();
		bw.close();
	}
}
