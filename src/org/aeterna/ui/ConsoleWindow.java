package org.aeterna.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ConsoleWindow extends JFrame {

	private JTextArea textArea;
	
	private JScrollPane textAreaScrollPane;
	
	public ConsoleWindow() {
		super("MD5 Results");
	}

	public void constructFrame() {
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(Color.decode("#292929"));
		textArea.setForeground(Color.decode("#228B22"));
		
		PrintStream printStream = new PrintStream(new OutputStream() {

			@Override
			public void write(int b) throws IOException {
				textArea.append(String.valueOf((char) b));
				textArea.setCaretPosition(textArea.getDocument().getLength());
			}

		});
		
		System.setOut(printStream);
		System.setErr(printStream);
		
		textAreaScrollPane = new JScrollPane(textArea);
		textAreaScrollPane.setPreferredSize(new Dimension(425, 500));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.decode("#292929"));
		add(textAreaScrollPane);
		pack();
	}
	
	public void showFrame() {
		GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = graphicsDevice.getDisplayMode().getWidth();
		int height = graphicsDevice.getDisplayMode().getHeight();

		int x = (int) ((width - getWidth()) / 2);
		int y = (int) ((height - getHeight()) / 2);
		
		setLocation(x, y);
		setVisible(true);
	}

}