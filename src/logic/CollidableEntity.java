package logic;

public abstract  class CollidableEntity extends Entity {

	protected boolean collideWith(Entity other) {
		return Math.hypot(this.x - other.x, this.y - other.y) <= 16;
	}
}
