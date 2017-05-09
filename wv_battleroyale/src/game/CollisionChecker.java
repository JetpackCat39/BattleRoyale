package game;

public class CollisionChecker {
	private Fighter p1, p2;
	
	public CollisionChecker(Fighter fighter1, Fighter fighter2) {
		p1 = fighter1;
		p2 = fighter2;
	}
	
	public void fixCollisions() {
		if(collisionX()) {
			if(collisionY()) {
				if(p1.getX() - p1.getXSpeed() < p2.getX())
					p1.setX(p2.getX() - p1.getWidth());
				else
					p1.setX(p2.getX() + p2.getWidth());
			}
		}
	}
	
	private boolean collisionX() {
		return p1.getX() + p1.getWidth() > p2.getX() && p1.getX() < p2.getX() + p2.getWidth();
	}
	
	private boolean collisionY() {
		return p1.getY() <= p2.getY() + p2.getHeight() && p1.getY() >= p2.getY();
	}
}
