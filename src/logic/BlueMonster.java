package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import render.RenderableHolder;

public class BlueMonster extends CollidableEntity implements Walkable {

	private final int speed;

	public BlueMonster() {
		// TODO Auto-generated constructor stub
		this.x = 200;
		this.y = 192;
		this.speed = 3;

	}

	public int getz() {
		// TODO Auto-generated method stub
		return 1000;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.MonsterImage, x, y);
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isDestroy() {
		return false;
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

		if (this.x >= 195 && this.x <= 605 && this.y < 475) {
			turnRight();
		} else if (this.x >= 600 && this.y >= 192 && this.y < 480) {
			forward();

		} else if (this.x <= 610 && this.y <= 480 && this.x >= 195) {
			turnLeft();
		} else if (this.y <= 480 && this.y >= 192 && this.x < 602) {
			backWard();

			if (this.x == 194 && this.y == 192) {
				this.x = 200;
				this.y = 192;
			}
		}
	}

}
