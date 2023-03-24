package org.aeterna;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

import javax.swing.JFileChooser;

public class MD5Checker {
	
	private JFileChooser fileChooser;

	public void prompt() {
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		fileChooser.setDialogTitle("Select a Directory");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);

		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			File file2 = fileChooser.getCurrentDirectory();

			Main.getConsoleWindow().showFrame();
			
			if (file.isDirectory()) {
				getLocalMD5(file.listFiles());
			} else {
				getLocalMD5(file2.listFiles());
			}
		} else {
			System.exit(0);
		}
	}

	public static void getLocalMD5(File[] files) {
		for (File file : files) {
			if (file.isDirectory()) {
				getLocalMD5(file.listFiles());
			} else {
				System.out.println(file.getName() + ":");
				System.out.println("\t- MD5: " + getMD5(file.getPath()) + "\n");
			}
		}
	}

	public static String getMD5(String file) {
		try {
			InputStream in = new FileInputStream(file);
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			byte[] fileBytes = new byte[in.available()];
			in.read(fileBytes);
			algorithm.update(fileBytes, 0, fileBytes.length);
			String md5 = new BigInteger(1, algorithm.digest()).toString(16);
			in.close();
			return md5;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}