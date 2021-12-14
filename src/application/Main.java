package application;

import drawing.GameScreen;
import DiamondHunterButton.MenuButton;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import logic.Diamond;
import logic.GameLogic;
import logic.Player;
import render.RenderableHolder;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Main extends Application {
	public Image MenuImage;
	public BackgroundImage MenuBackground;
	public Image HelpImage;
	public BackgroundImage HelpBackground;
	public Image EndImage;
	public BackgroundImage EndBackground;
	public AnchorPane root;
	public MenuButton button1;
	public MenuButton button2;
	public MenuButton button3;

	private Canvas canvas;
	private int currentTime;
	private Thread timerThread;

	@Override
	public void start(Stage primaryStage) {
		MenuImage = new Image("Background.png", 600, 600, false, true);
		MenuBackground = new BackgroundImage(MenuImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
				BackgroundPosition.DEFAULT, null);
		root = new AnchorPane();
		root.setPrefSize(600, 600);
		root.setBackground(new Background(MenuBackground));
		button1 = new MenuButton("start");
		button2 = new MenuButton("Help");
		button3 = new MenuButton("Exit");
		button1.setLayoutX(30);
		button1.setLayoutY(300);
		button2.setLayoutX(220);
		button2.setLayoutY(300);
		button3.setLayoutX(410);
		button3.setLayoutY(300);
		button1.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent arg0) {
				/// TODO Auto-generated method stub
				RenderableHolder.click.play();
				showGameScreen();
				RenderableHolder.start.stop();
				primaryStage.close();
			}
		});
		button2.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				RenderableHolder.click.play();
				showHowToPlay();
				primaryStage.close();
			}
		});
		button3.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				RenderableHolder.click.play();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Platform.exit();

			}
		});
		root.getChildren().addAll(button1, button2, button3);
		Scene scene = new Scene(root, 600, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("DiamondHunter");
		RenderableHolder.start.play();
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

	public void showGameScreen() {

		Stage stage = new Stage();
		AnchorPane root = new AnchorPane();
		GameLogic logic = new GameLogic();
		GameScreen gameScreen = new GameScreen(800, 600);
		Scene scene2 = new Scene(root, 800, 640);
		stage.setScene(scene2);
		root.getChildren().add(gameScreen);
		gameScreen.requestFocus();
		stage.setTitle("DiamondHunter");
		stage.show();

		this.canvas = new Canvas(200, 100);
		canvas.setLayoutX(550);
		canvas.setLayoutY(0);
		root.getChildren().add(canvas);
		this.currentTime = 0;
		GraphicsContext gc = canvas.getGraphicsContext2D();
		this.timerThread = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(1000);
					currentTime++;
					drawCurrentTimeString(gc);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Stop Timer Thread");
					Platform.exit();
					break;
				}
			}
		});
		timerThread.setDaemon(true);
		this.timerThread.start();

		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				gameScreen.paintComponent();
				logic.logicUpdate();
				if (!RenderableHolder.gameSound.isPlaying()) {
					RenderableHolder.gameSound.play();
				}
				if (Player.isCollideBlueMonster() || Player.collidePinkMonster || Player.collideYellowMonster) {
					RenderableHolder.gameSound.stop();
					RenderableHolder.gameOver.play();
					try {
						Thread.sleep(2000);
						RenderableHolder.gameOver.stop();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					stage.close();
					showLoseScreen();
					stop();
				} else if (Diamond.getScore() == 25) {
					RenderableHolder.gameSound.stop();
					RenderableHolder.winsound.play();
//					RenderableHolder.winSound.start();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					stage.close();
					showWinScreen();
					stop();
				}
			}

		};
		animation.start();
		stage.setResizable(false);
	}

	public void showHomeScreen() {
		Stage stage = new Stage();
		MenuImage = new Image("Background.png", 600, 600, false, true);
		MenuBackground = new BackgroundImage(MenuImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
				BackgroundPosition.DEFAULT, null);
		root = new AnchorPane();
		root.setPrefSize(600, 600);
		root.setBackground(new Background(MenuBackground));
		button1 = new MenuButton("start");
		button2 = new MenuButton("Help");
		button3 = new MenuButton("Exit");
		button1.setLayoutX(30);
		button1.setLayoutY(300);
		button2.setLayoutX(220);
		button2.setLayoutY(300);
		button3.setLayoutX(410);
		button3.setLayoutY(300);
		button1.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent arg0) {
				/// TODO Auto-generated method stub
				RenderableHolder.click.play();
				showGameScreen();
				RenderableHolder.start.stop();
				// logic.Player.collideMonster = false;
				stage.close();
			}
		});

		button2.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				RenderableHolder.click.play();
				showHowToPlay();
				stage.close();
			}
		});

		button3.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				RenderableHolder.click.play();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stage.close();

			}
		});
		root.getChildren().addAll(button1, button2, button3);
		Scene scene = new Scene(root, 600, 600);
		stage.setScene(scene);
		stage.setTitle("DiamondHunter");

		stage.setResizable(false);
		stage.show();
	}

	public void showHowToPlay() {

		HelpImage = new Image("howtoplayBackground.png", 600, 600, false, true);
		HelpBackground = new BackgroundImage(HelpImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
				BackgroundPosition.DEFAULT, null);

		Stage stage = new Stage();
		AnchorPane root = new AnchorPane();
		root.setBackground(new Background(HelpBackground));
		root.setPrefSize(600, 600);

		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.setTitle("DiamondHunter");
		stage.setResizable(false);

		button2 = new MenuButton("BACK");
		button1 = new MenuButton("START");

		button2.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				RenderableHolder.click.play();
				showHomeScreen();
				stage.close();
			}
		});

		button1.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				RenderableHolder.click.play();
				showGameScreen();
				RenderableHolder.start.stop();
				stage.close();
			}
		});

		button2.setLayoutX(20);
		button2.setLayoutY(450);
		button1.setLayoutX(420);
		button1.setLayoutY(450);

		root.getChildren().addAll(button2, button1);
		stage.setResizable(false);
		stage.show();

	}

	public void showLoseScreen() {
		Font font = Font.font("Minecrafter Alt", FontWeight.LIGHT, 30);
		EndImage = new Image("GameOverBackground.png", 600, 600, false, true);
		EndBackground = new BackgroundImage(EndImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
				BackgroundPosition.DEFAULT, null);

		Stage stage = new Stage();
		AnchorPane root = new AnchorPane();

		root.setBackground(new Background(EndBackground));
		root.setPrefSize(600, 600);

		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.setTitle("DiamondHunter");
		stage.setResizable(false);

		Label score = new Label();
		score.setText(Integer.toString(Diamond.getScore()));
		score.setFont(font);
		score.setLayoutX(290);
		score.setLayoutY(245);

		button3.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				RenderableHolder.click.play();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stage.close();

			}
		});
		button3.setLayoutY(525);
		button3.setLayoutX(430);

		root.getChildren().addAll(button3, score);
		stage.show();

	}

	public void showWinScreen() {

		EndImage = new Image("winBackground.png", 600, 600, false, true);
		EndBackground = new BackgroundImage(EndImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
				BackgroundPosition.DEFAULT, null);

		Stage stage = new Stage();
		AnchorPane root = new AnchorPane();

		root.setBackground(new Background(EndBackground));
		root.setPrefSize(600, 600);

		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.setTitle("DiamondHunter");
		stage.setResizable(false);

		Label timeUsed = new Label();
		timeUsed.setText("" + this.currentTime + " s");
		timeUsed.setLayoutX(270);
		timeUsed.setLayoutY(285);
		timeUsed.setFont(RenderableHolder.font2);

		button3.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				RenderableHolder.click.play();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Platform.exit();

			}
		});

		button3.setLayoutY(525);
		button3.setLayoutX(430);

		root.getChildren().addAll(button3, timeUsed);
		stage.show();

	}

	public void drawCurrentTimeString(GraphicsContext gc) {

		gc.setFill(Color.BLACK);
		gc.setFont(RenderableHolder.font2);
		gc.clearRect(0, 0, 200, 100);
		gc.fillText("" + this.currentTime, 30 , 65);
	}

}