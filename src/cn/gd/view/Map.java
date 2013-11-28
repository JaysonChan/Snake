package cn.gd.view;


import java.awt.Dimension;

import javax.swing.JComponent;

public class Map extends JComponent {
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 20;
	public static final int HEIGHT = 20;
	public static final int NODE_SIZE = 10;
	public Map(){
		super();
		setPreferredSize(new Dimension(WIDTH * NODE_SIZE, HEIGHT * NODE_SIZE));
	}
}
