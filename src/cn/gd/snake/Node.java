package cn.gd.snake;

public class Node {
	private int x;
	private int y;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean isEquals(Node _node){
		if(this.x == _node.getX() && this.y == _node.getY()){
			return true;
		}else{
			return false;
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
