package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import tools.RegularMatch;

public class fileIO {
	public static String fileInputAll(String filePath) {
		File file = new File(filePath);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        try {
            return new String(filecontent, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public static String[] fileInputList(String filePath) {
		File file = new File(filePath);
		ArrayList<String> arrayList = new ArrayList<String>();
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String currentLine = null;
			while((currentLine = bufferedReader.readLine()) != null && !Thread.currentThread().isInterrupted()) {
				arrayList.add(currentLine);
			}
			bufferedReader.close();
			fileReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return arrayList.toArray(new String[0]);
	}
	
	@SuppressWarnings("static-access")
	public static void copyDir(String sourcePath, String newPath) throws Exception {
		File file = new File(sourcePath);
		String[] filePath = file.list();

		if (!(new File(newPath)).exists()) {
			(new File(newPath)).mkdir();
		}

		for (int i = 0; i < filePath.length; i++) {
			if ((new File(sourcePath + file.separator + filePath[i])).isDirectory()) {
				copyDir(sourcePath  + file.separator  + filePath[i], newPath + file.separator + filePath[i]);
			}
			if (new File(sourcePath  + file.separator + filePath[i]).isFile()) {
				copyFile(sourcePath + file.separator + filePath[i], newPath + file.separator + filePath[i]);
			}
		}
	}
	
	public static void copyFile(String oldPath, String newPath) throws Exception {
		File oldFile = new File(oldPath);
		File file = new File(RegularMatch.dat2jpgFile(newPath));
		FileInputStream in = new FileInputStream(oldFile);
		FileOutputStream out = new FileOutputStream(file);;

		byte[] buffer=new byte[2097152];
		int readByte = 0;
		while((readByte = in.read(buffer)) != -1){
			out.write(buffer, 0, readByte);
		}

		in.close();
		out.close();
	}
}
