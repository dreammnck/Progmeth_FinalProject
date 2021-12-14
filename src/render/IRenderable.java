package render;

import javafx.scene.canvas.GraphicsContext;

public interface IRenderable {
	public int getz();
	public void draw(GraphicsContext gc);
	public boolean isVisible();
	public boolean isDestroy() ;

}
