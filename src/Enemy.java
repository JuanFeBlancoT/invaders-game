import processing.core.PApplet;

public abstract class Enemy {
	
	protected int posX, posY, enemySize, health, speed, points, damage;
	protected boolean visible;
	
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getEnemySize() {
		return enemySize;
	}

	public void setEnemySize(int enemySize) {
		this.enemySize = enemySize;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Enemy(PApplet app) {
		posX = posY = ((int) Math.random()*50)+1225;
		posY = ((int) Math.random()*750)+25;
	}
	
	public abstract void drawEnemy(PApplet app);
	
	public abstract void move();
}
