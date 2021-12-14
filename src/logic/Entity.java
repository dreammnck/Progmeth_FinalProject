package logic;

import javafx.scene.canvas.GraphicsContext;
import render.IRenderable;

public abstract class Entity implements IRenderable {
	protected double x, y;
	protected int z;
	protected boolean visible;

	protected Entity() {
		// TODO Auto-generated constructor stub
		this.visible = true;
	}

	@Override
	public int getz() {
		// TODO Auto-generated method stub
		return this.z;
	}

	@Override
	public abstract void draw(GraphicsContext gc);
	

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return this.visible;
	}

}
