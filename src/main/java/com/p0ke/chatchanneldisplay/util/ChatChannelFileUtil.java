package com.p0ke.chatchanneldisplay.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ChatChannelFileUtil {
	private static final String FILE_NAME = "chatchannel.cfg";
	private static File modeFile;

	public static void loadFile(String filepath) {
		try {
			modeFile = new File(filepath + File.separator + "chatchannel.cfg");

			if (!modeFile.exists()) {
				modeFile.createNewFile();
				saveMode("NULL");
			}

		} catch (IOException e) {
			System.out.println("Error loading mode file");
			e.printStackTrace();
		}
	}

	public static String getMode() {
		try {
			Scanner in = new Scanner(modeFile);
			String mode = in.nextLine();
			in.close();
			return mode;
		} catch (Exception e) {
			System.out.println("Error reading mode file");
			e.printStackTrace();

			return "";
		}
	}

	public static void saveMode(String mode) {
		try {
			PrintWriter pw = new PrintWriter(modeFile);
			pw.println(mode);
			pw.close();
		} catch (Exception e) {
			System.out.println("Error writing to mode file");
			e.printStackTrace();
		}
	}
}
