package Main;

import java.awt.EventQueue;

import javax.swing.*;

import Interface.Log;

public class Demo extends JFrame{
	/**
	 * ����Ӧ�ó���
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Log RLJ=new Log();
					RLJ.Log();	
				} catch (Exception e) {
					e.printStackTrace(); 
				}
			}
		});
	}
	
}
