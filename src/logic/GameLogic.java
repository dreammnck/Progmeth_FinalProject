package logic;

import java.util.ArrayList;
import java.util.List;
import logic.StoneTile;
import render.RenderableHolder;

public class GameLogic {
	public List<Entity> gameContainer;
	public Player player;
	public Diamond diamond;
	public BlueMonster blueMonster;
	public YellowMonster yellowMonster;
	public PinkMonster pinkMonster;
	public Heart heart;
	public StoneTile tile;
	public MapPoint mapPoint;

	public GameLogic() {
		this.gameContainer = new ArrayList<Entity>();
		player = new Player(375, 320);
		diamond = new Diamond();
		blueMonster = new BlueMonster();
		yellowMonster = new YellowMonster();
		pinkMonster = new PinkMonster();
		mapPoint = new MapPoint();
		heart = new Heart();
		tile = new StoneTile();
		addObject(player);
		addObject(heart);
		addObject(diamond);
		addObject(blueMonster);
		addObject(yellowMonster);
		addObject(pinkMonster);
		addObject(tile);
	}

	protected void addObject(Entity entity) {
		gameContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}

	public void logicUpdate() {
		for (Entity e : this.gameContainer) {
			if (!(e instanceof StoneTile) && !e.isDestroy()) {
				player.update(e);
			}
		}
		player.walk(tile);
		blueMonster.walk();
		yellowMonster.walk();
		pinkMonster.walk();

	}

}
