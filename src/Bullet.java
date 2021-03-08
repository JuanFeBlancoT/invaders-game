import processing.core.PApplet;
import processing.core.PImage;

public class Bullet {
	
	private int posX, posY, bulletSize, speed, damage;
	private boolean visible;
	private PApplet app;
	PImage b;
	
	public Bullet(int posX, int posY, int damage, int speed, PApplet app, PImage b) {
		bulletSize = 20;
		this.speed = speed;
		this.posX = posX;
		this.posY = posY;
		this.app= app;
		visible = true;
		this.damage = damage;
		this.b = b;
	}
	
	public void drawBullet() {
		if(visible) {
			app.image(b, posX-18, posY-18);
			//app.circle(posX, posY, bulletSize);
		}	
	}
	
	public void move() {
		posX+=speed;
	}
	
	public void moveBackwards() {
		posX-=speed;
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
