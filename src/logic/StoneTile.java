package logic;

import javafx.scene.canvas.GraphicsContext;
import render.RenderableHolder;

public class StoneTile extends CollidableEntity {
	public String[][] field;
	

	public StoneTile() {
		this.field = CSVParser.readCSV("DiamondMapCsv.csv");
	}

	public int getz() {

		return 2;
	}

	public void draw(GraphicsContext gc) {

		for (int j = 0; j < 20; j++) {
			for (int i = 0; i < 25; i++) {
				if (field[j][i].equals("8")) {
					gc.drawImage(RenderableHolder.TileImage, i * 32, j * 32);

				}
			}

		}

	}

	public boolean isVisible() {

		return false;
	}

	public boolean isDestroy() {
		return false;
	}

	public boolean isThereTileAt(int row, int column) {
		if (column < 0 || row < 0) {
			return false;
		}
		return field[row][column] != null && field[row][column].equals("8");

	}

	public boolean collideWithAboveStone(double playerX, double playerY) {
		int column = (int) Math.round((playerX - 5) / 32);
		int row = (int) (Math.ceil((playerY) / 32 - 1));
		if (isThereTileAt(row, column) && playerY - (row * 32 - 16) >= 0) {
			return true;
		}
		return false;
	}

	public boolean collideWithRightStone(double playerPosX, double playerPosY) {
		int column = (int) Math.floor((playerPosX - 12) / 32 + 1);
		int row = (int) Math.round((playerPosY) / 32);

		if (isThereTileAt(row, column) && playerPosX - (column * 32 + 16) <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean collideWithLeftStone(double playerPosX, double playerPosY) {
		int column = (int) (Math.ceil((playerPosX) / 32 - 1));
		int row = (int) (Math.round(playerPosY / 32));
		if (isThereTileAt(row, column) && playerPosX - (column * 32 + 16) >= 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean collideWithUnderStone(double playerPosX, double playerPosY) {
		int column = (int) Math.round((playerPosX - 6) / 32);
		int row = (int) (Math.floor((playerPosY + 20) / 32));
		if ((isThereTileAt(row, column) && playerPosY - (row * 32 - 16) <= 0)) {// || (playerPosY-(row*32)+16) {
			return true;
		} else {
			return false;
		}

	}

}