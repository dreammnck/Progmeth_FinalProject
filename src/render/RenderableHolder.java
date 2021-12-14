package render;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import logic.Heart;
import logic.Diamond;
//import logic.NormalTile;
import logic.Player;
import logic.StoneTile;


public class RenderableHolder {
	private List<IRenderable> entities ;
	private static final RenderableHolder instance = new RenderableHolder();
	private Comparator<IRenderable> comparator ;
	public static Image TileImage ;
	public static Image MonsterImage ;
	public static Image MonsterAlienImage;
	public static Image MonsterAlienImage2;
	public static Image AxeImage ;
	public static Image DiamondImage ;
	public static Image playerForward;
	public static Image playerBackward;
	public static Image playerTurnLeft;
	public static Image playerTurnRight;
	public static Image bigPlayerForward ;
	public static Image bigPlayerBackward;
	public static Image bigPlayerTurnLeft ;
	public static Image bigPlayerTurnRight;
	public static Image floor;
	public static AudioClip ding ;
	public static AudioClip click ;
	public static AudioClip gameOver ;
	public static AudioClip start;
	public static AudioClip gameSound;
	public static AudioClip heartSound;
	public static AudioClip winsound;
	public static Font font;
	public static Font font2;
	
	static {
		loadResource();
	}
	
	public  RenderableHolder() {
		// TODO Auto-generated constructor stub
		
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getz() > o2.getz())
				return 1;
			return -1;
		};
		
		
	}
	public void add(IRenderable entity) {
		System.out.println("add");
		entities.add(entity);
		Collections.sort(entities, comparator);
		for(IRenderable x: entities){
			if(x instanceof Player) System.out.println("player");
			if(x instanceof Diamond) System.out.println("Diamond");
			if(x instanceof StoneTile) System.out.println("StoneTile");
			if(x instanceof Heart) System.out.println("Axe");
			
			
		}
	
	}
	
	public static void loadResource() {
		TileImage = new Image(ClassLoader.getSystemResource("8.png").toString());
		MonsterImage = new Image(ClassLoader.getSystemResource("slimeBlue2.png").toString());
		MonsterAlienImage = new Image(ClassLoader.getSystemResource("monsterAlien.png").toString());
		MonsterAlienImage2 = new Image(ClassLoader.getSystemResource("monsterAlien2.png").toString());
		AxeImage = new Image(ClassLoader.getSystemResource("heart.png").toString());
		DiamondImage = new Image(ClassLoader.getSystemResource("5.png").toString());
		playerForward = new Image(ClassLoader.getSystemResource("girl3.png").toString());
		playerBackward = new Image(ClassLoader.getSystemResource("girl2.png").toString());
		playerTurnLeft = new Image(ClassLoader.getSystemResource("girl4.png").toString());
		playerTurnRight = new Image(ClassLoader.getSystemResource("girl1.png").toString());
		bigPlayerForward = new Image(ClassLoader.getSystemResource("girl3Big.png").toString());
		bigPlayerBackward = new Image(ClassLoader.getSystemResource("girl2Big.png").toString());
		bigPlayerTurnLeft = new Image(ClassLoader.getSystemResource("girl4Big.png").toString());
		bigPlayerTurnRight = new Image(ClassLoader.getSystemResource("girl1Big.png").toString());
		floor = new Image(ClassLoader.getSystemResource("Blackground.png").toString());
		//MenuBackground = new Image(ClassLoader.getSystemResource("Background.png").toString());
		ding = new AudioClip(ClassLoader.getSystemResource("Ding.wav").toString());
		click = new AudioClip(ClassLoader.getSystemResource("Click.wav").toString());
		gameOver = new AudioClip(ClassLoader.getSystemResource("GameOver.wav").toString());
		start = new AudioClip(ClassLoader.getSystemResource("startSound.wav").toString());
		gameSound = new AudioClip(ClassLoader.getSystemResource("game.wav").toString());
		heartSound = new AudioClip(ClassLoader.getSystemResource("AxeSound.wav").toString());
		winsound = new AudioClip(ClassLoader.getSystemResource("winsound.wav").toString());
		font = Font.loadFont(ClassLoader.getSystemResource("Minecrafter.Alt.ttf").toExternalForm(), 23);
		font2 = Font.loadFont(ClassLoader.getSystemResource("FaceOff.ttf").toExternalForm(), 40);
	}
	
	public static RenderableHolder getInstance() {
		return instance;
		}
	public List<IRenderable> getEntities() {
		return entities;
	}
	
	
		
	

}

