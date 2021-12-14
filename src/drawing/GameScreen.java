package drawing;

import java.util.ArrayList;

import input.InputUtility;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import render.IRenderable;
import render.RenderableHolder;
import logic.Heart;
import logic.CSVParser;
import logic.Diamond;
import logic.BlueMonster;
import logic.Player;
import logic.StoneTile;

public class GameScreen extends Canvas {

	Image backgroundScore;
	Font font;

	public GameScreen(int Width, int Height) {
		super(Width, Height);
		backgroundScore = new Image("score.png");
		font = Font.font("Minecrafter Alt", FontWeight.LIGHT, 18);
		this.setVisible(true);
		addListener();
	}

	public void addListener() {
		this.setOnKeyPressed((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode(), true);
		});
		this.setOnKeyReleased((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode(), false);
		});
	}

	public void paintComponent() {
		GraphicsContext gc = this.getGraphicsContext2D();
		// gc.clearRect(0, 0, this.getWidth(), this.getHeight());
		gc.drawImage(RenderableHolder.floor, 0, 0);

		gc.setFont(font);
		gc.drawImage(backgroundScore, 290, 110);
		gc.fillText("Score : " + Diamond.getScore(), 300, 140);

		for (IRenderable e : RenderableHolder.getInstance().getEntities()) {

			if (!e.isVisible() && !e.isDestroy()) {

				e.draw(gc);

			}

		}

	}

}
