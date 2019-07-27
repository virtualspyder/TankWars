package core_logic;

import general_logic.GameSprites;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main
{
	public static GameSprites gameSprites = new GameSprites();

	public static void ErrorLog(String error_msg) {
		System.out.println(error_msg);
		JOptionPane.showMessageDialog(null, error_msg,  "Fatal Error", JOptionPane.INFORMATION_MESSAGE);
		System.exit(150);
	}

	public static void main(String[] args)
	{
		Tank tank = new Tank();

		JFrame main_window = new JFrame("TankWars");
		main_window.addWindowListener(new WindowAdapter(){});

		main_window.getContentPane().add("Center", tank);
		main_window.pack();

		main_window.setSize(new Dimension(1024, 768));

		main_window.setVisible(true);
		main_window.setResizable(false);
		main_window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

		tank.start();
	}
}