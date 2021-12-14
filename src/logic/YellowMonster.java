package logic;

import javafx.scene.canvas.GraphicsContext;
import render.RenderableHolder;

public class YellowMonster extends CollidableEntity implements Walkable {
	private final int speed;

	public YellowMonster() {
		this.speed = 1;
		this.x = 352;
		this.y = 220;
	}

	@Override
	public boolean isDestroy() {
		// TODO Auto-generated method stub
		if(Diamond.score<10) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isVisible() {
		// TODO Auto-generated method stub
		if(Diamond.score<10) {
			return true;
		}
		else {
			return false;
		}
	
	}

	public int getz() {
		// TODO Auto-generated method stub
		return 1000;
	}

	public void forward() {
		this.y += speed;
	}

	public void turnLeft() {
		this.x -= speed;
	}

	public void turnRight() {
		this.x += speed;
	}

	public void backWard() {
		this.y -= speed;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.MonsterAlienImage, x, y);

	}

	public void walk() {
		if (x < 392 && this.y == 220) {
			turnRight();
			if (x >= 390) {
				this.y = 221;
			}
		} else if (x < 392 && this.y == 221) {
			turnLeft();
			if (this.x == 352) {
				this.y = 220;
			}
		}
	}

}
