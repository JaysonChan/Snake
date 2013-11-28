package cn.gd.snake;

import java.util.LinkedList;
import java.util.List;
import cn.gd.direction.Direction;
import cn.gd.view.Map;

public class Snake {
	private List<Node> body;
	private Direction direction;
	private boolean isDead;
	
	
	public Snake() {
		body = new LinkedList<Node>();
		isDead = false;
		body.add(new Node(0, 0));
		direction = Direction.RIGHT;
	}

	public Node move(Node node){
		addFirst();
		if(eat(node)){
			return null;
		}
		return deleteLast();
	}
	
	public boolean eat(Node node){
		return body.get(0).isEquals(node);
	}
	
	public boolean isContaint(Node node){
		for(Node _node : body){
			if(_node.isEquals(node)){
				return true;
			}
		}
		return false;
	}
	
	public Node getFirst(){
		return body.get(0);
	}
	
	private boolean isDead(Node node){
		if(node.getX() < 0 || node.getX() > Map.WIDTH){
			this.isDead = true;
			return true;
		}
		if(node.getY() < 0 || node.getY() > Map.HEIGHT){
			this.isDead = true;
			return true;
		}
		for(Node _node : body){
			if(node.isEquals(_node)){
				this.isDead = true;
				return true;
			}
		}
		return false;
	}
	
	private void addFirst(){
		Node first = body.get(0);
		int x = first.getX();
		int y = first.getY();
		switch (direction) {
		case UP:
			y--;
			break;
		case DOWN:
			y++;
			break;
		case LEFT:
			x--;
			break;
		case RIGHT:
			x++;
			break;
		}
		System.out.println("x="+x+"    y="+y);
		Node _first = new Node(x, y);
		if(!isDead(_first)){
			body.add(0, _first);
		}
	}
	private Node deleteLast(){
		return body.remove(body.size() - 1);
	}

	
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public boolean getIsDead() {
		return isDead;
	}
	public void setIsDead(boolean isDead) {
		this.isDead = isDead;
	}
	public int size(){
		return body.size();
	}
}
