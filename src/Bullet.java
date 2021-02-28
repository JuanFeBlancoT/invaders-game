import processing.core.PApplet;

public class Bullet {
	
	private int posX, posY, bulletSize, speed, damage;
	private boolean visible;
	private PApplet app;
	
	public Bullet(int posX, int posY, PApplet app) {
		bulletSize = 20;
		speed = 7;
		this.posX = posX;
		this.posY = posY;
		this.app= app;
		visible = true;
	}
	
	public void drawBullet() {
		if(visible) {
			app.circle(posX, posY, bulletSize);
		}
		
	}
	
	//Getters and Setters
	
	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getBulletSize() {
		return bulletSize;
	}

	public void setBulletSize(int bulletSize) {
		this.bulletSize = bulletSize;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void move() {
		posX+=speed;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
}
