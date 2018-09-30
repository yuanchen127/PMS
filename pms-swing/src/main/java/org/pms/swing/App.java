package org.pms.swing;

import javax.swing.JFrame;

import org.pms.swing.Component.Login;

public class App {
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		new Login();
	}
}