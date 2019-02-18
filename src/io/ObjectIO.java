package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

public class ObjectIO {
	@SuppressWarnings("unchecked")
	public static LinkedList<String> inputDateList(String folderPath) throws Exception {
		FileInputStream fis = new FileInputStream(folderPath + File.separator + ".properties");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object cache = ois.readObject();
		LinkedList<String> dateList = (LinkedList<String>) cache;
		ois.close();
		return dateList;
	}
	
	public static void outputDateList(LinkedList<String> dateList, String folderPath) throws Exception {
		File filePath = new File(folderPath + File.separator + ".properties");
		FileOutputStream fos = new FileOutputStream(filePath);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(dateList);
		oos.close();
	}
}
