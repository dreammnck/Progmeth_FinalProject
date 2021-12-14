package logic;

import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import logic.Entity;
import render.RenderableHolder;

public class Player extends CollidableEntity implements Walkable {
	public static int speed;
	public static boolean collideDiamond;
	public static boolean collideHeart;
	public static boolean collideBlueMonster;
	public static boolean collideYellowMonster;
	public static boolean collidePinkMonster;
	public int slot;

	public Player(double x, double y) {
		// TODO Auto-generated constructor stub
		setCollideHeart(false);
		setCollideDiamond(false);
		setCollideBlueMonster(false);
		speed = 1;
		this.slot = 3;
		this.x = x;
		this.y = y;
	}

	public void forward() {
		this.y += speed;
		this.slot = 3;
	}

	public void turnLeft() {
		this.x -= speed;
		this.slot = 4;
	}

	public void turnRight() {
		this.x += speed;
		this.slot = 1;
	}

	public void backWard() {
		this.y -= speed;
		this.slot = 2;
	}

	@Override
	public int getz() {
		// TODO Auto-generated method stub
		return 999;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (this.slot == 1) {
			if (Heart.hasHeart) {
				gc.drawImage(RenderableHolder.bigPlayerTurnRight, x, y);
			} else {
				gc.drawImage(RenderableHolder.playerTurnRight, x, y);
			}

		} else if (this.slot == 2) {
			if (Heart.hasHeart) {
				gc.drawImage(RenderableHolder.bigPlayerBackward, x, y);
			} else {
				gc.drawImage(RenderableHolder.playerBackward, x, y);
			}

		} else if (this.slot == 3) {
			if (Heart.hasHeart) {
				gc.drawImage(RenderableHolder.bigPlayerForward, x, y);
			} else {
				gc.drawImage(RenderableHolder.playerForward, x, y);
			}

		} else if (this.slot == 4) {
			if (Heart.hasHeart) {
				gc.drawImage(RenderableHolder.bigPlayerTurnLeft, x, y);
			} else {
				gc.drawImage(RenderableHolder.playerTurnLeft, x, y);
			}
		}

	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	public void update(Entity other) {

		if (this.collideWith(other) && other instanceof Diamond) {
			setCollideDiamond(false);
			Diamond other1 = (Diamond) other;
			other1.visible = false;
			other1.update();

		} else if ((this.collideWith(other) && other instanceof Heart)) {
			Heart.faster();
			setCollideHeart(true);
			Heart.hasHeart = true;
			Heart other1 = (Heart) other;
			other1.update();
			RenderableHolder.heartSound.play();

		} else if (this.collideWith(other) && other instanceof BlueMonster) {
			setCollideBlueMonster(true);
		}

		else if (this.collideWith(other) && other instanceof YellowMonster) {
			setCollideYellowMonster(true);
		} else if (this.collideWith(other) && other instanceof PinkMonster) {
			setCollidePinkMonster(true);
		}

		if (Diamond.score != 0 && Diamond.score % 4 == 0 && isCollideHeart() == true) {
			if (!Heart.hasHeart) {
				setCollideHeart(false);
			}

		}

	}

	public void walk(StoneTile tile) {

		if (InputUtility.getKeyPressed(KeyCode.W) && !tile.collideWithAboveStone(this.x, this.y)) {
			backWard();
		} else if (InputUtility.getKeyPressed(KeyCode.S) && !tile.collideWithUnderStone(this.x, this.y)) {
			forward();
		} else if (InputUtility.getKeyPressed(KeyCode.A) && !tile.collideWithLeftStone(this.x, this.y)) {
			turnLeft();
		} else if (InputUtility.getKeyPressed(KeyCode.D) && !tile.collideWithRightStone(this.x, this.y)) {
			turnRight();
		}
	}
	
	@Override
	public void walk() {
		// TODO Auto-generated method stub

	}


	@Override
	public boolean isDestroy() {
		// TODO Auto-generated method stub
		return (isCollideBlueMonster() || isCollidePinkMonster() || isCollideYellowMonster());
	}

	public static void smaller() {
		Heart.hasHeart = false;
		speed = 1;
	}

	public static boolean isCollideDiamond() {
		return collideDiamond;
	}

	public static boolean isCollideHeart() {
		return collideHeart;
	}

	public static boolean isCollideBlueMonster() {
		return collideBlueMonster;
	}

	public static boolean isCollideYellowMonster() {
		return collideYellowMonster;
	}

	public static boolean isCollidePinkMonster() {
		return collidePinkMonster;
	}

	public static void setCollideDiamond(boolean collideDiamond) {
		Player.collideDiamond = collideDiamond;
	}

	public static void setCollideHeart(boolean collideHeart) {
		Player.collideHeart = collideHeart;
	}

	public static void setCollideBlueMonster(boolean collideBlueMonster) {
		Player.collideBlueMonster = collideBlueMonster;
	}

	public static void setCollideYellowMonster(boolean collideYellowMonster) {
		Player.collideYellowMonster = collideYellowMonster;
	}

	public static void setCollidePinkMonster(boolean collidePinkMonster) {
		Player.collidePinkMonster = collidePinkMonster;
	}


}