package logic;

import javafx.scene.canvas.GraphicsContext;
import render.RenderableHolder;

public class PinkMonster extends CollidableEntity implements Walkable {

	private final int speed;

	public PinkMonster() {
		this.speed = 2;
		this.x = 392;
		this.y = 350;
	}

	@Override
	public boolean isDestroy() {
		// TODO Auto-generated method stub
		if (Diamond.score < 15) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isVisible() {
		// TODO Auto-generated method stub
		if (Diamond.score < 15) {
			return true;
		} else {
			return false;
		}

	}

	public int getz() {
		// TODO Auto-generated method stub
		return 1000;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.MonsterAlienImage2, x, y);

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

	public void walk() {
		if (x <= 392 && this.y == 351) {
			turnRight();
			if (x >= 390) {
				this.y = 350;
			}
		} else if (x <= 392 && this.y == 350) {
			turnLeft();
			if (this.x == 352) {
				this.y = 351;
			}
		}
	}

}
