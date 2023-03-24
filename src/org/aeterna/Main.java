package org.aeterna;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.aeterna.ui.ConsoleWindow;

public class Main {
	
	private static MD5Checker md5Checker;
	
	private static ConsoleWindow consoleWindow;
	
	public static void main(String[] arguments) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				consoleWindow = new ConsoleWindow();
				consoleWindow.constructFrame();
				
				md5Checker = new MD5Checker();
				md5Checker.prompt();
			}
			
		});
	}
	
	public static ConsoleWindow getConsoleWindow() {
		return consoleWindow;
	}

}