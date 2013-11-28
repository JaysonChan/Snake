package cn.gd;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

import cn.gd.direction.Direction;
import cn.gd.snake.Node;
import cn.gd.snake.Snake;
import cn.gd.view.Map;

public class Game extends KeyAdapter implements ActionListener{
	
	private JFrame frame;
	private JComponent map;
	private Timer timer;
	private Snake snake;
	private int speed;
	private Graphics2D graphics;
	private Random random;
	private Node food;
	private boolean firstTime = true;
	public static void main(String args[]){
		Game game = new Game();
		game.init();
		game.start();
	}
	private void init(){
		map = new Map();
		frame = new JFrame("贪吃蛇v0.1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().add(map);
		snake = new Snake();
		speed = 300;
		random = new Random(System.currentTimeMillis());
		frame.pack();
		frame.setVisible(true);
		graphics = (Graphics2D)map.getGraphics();
		food = createNode();
		frame.setFocusable(true);
		frame.addKeyListener(this);
		timer = new Timer(speed, this);
	}
	/**
	 * 时钟事件
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(firstTime){
			drawRandomNode();
			drawSnake();
			firstTime = false;
		}
		Node last;
		if(e.getSource() == timer){
			last = snake.move(food);
			if(snake.getIsDead()){
				stop();
				System.out.println("snake was dead");
			}else{
				if(snake.eat(food)){
					drawRandomNode();
				}else{
					drawSnake();
					deleteNode(last);
				}
			}
		}
		System.out.println("蛇长：" + snake.size());
	}
	private void drawSnake(){
		Node first = snake.getFirst();
		drawNode(first);
		
	}
	private void drawNode(Node node){
		Rectangle2D ract = new Rectangle2D.Double(node.getX() * Map.NODE_SIZE, node.getY() * Map.NODE_SIZE , Map.NODE_SIZE, Map.NODE_SIZE);
		graphics.fill(ract);
	}
	private void deleteNode(Node node){
		graphics.setPaint(map.getBackground());
		drawNode(node);
		graphics.setPaint(Color.BLACK);
	}
	private void drawRandomNode(){
		food = createNode();
		graphics.setPaint(Color.BLACK);
		drawNode(food);
	}
	private Node createNode(){
		while(true){
			int x = random.nextInt(Map.WIDTH);
			int y = random.nextInt(Map.HEIGHT);
			Node node = new Node(x, y);
			if(!snake.isContaint(node)){
				return node;
			}
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if(snake.getDirection() != Direction.DOWN)
				snake.setDirection(Direction.UP);
			break;
		case KeyEvent.VK_DOWN:
			if(snake.getDirection() != Direction.UP)
				snake.setDirection(Direction.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			if(snake.getDirection() != Direction.RIGHT)
				snake.setDirection(Direction.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			if(snake.getDirection() != Direction.LEFT)
				snake.setDirection(Direction.RIGHT);
			break;
		}
	}
	private void stop(){
		timer.stop();
	}
	private void start(){
		timer.start();
	}
}
