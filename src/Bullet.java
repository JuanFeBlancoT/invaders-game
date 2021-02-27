import processing.core.PApplet;

public class Bullet {
	
	private int posX, posY, bulletSize, speed;
	private PApplet app;
	
	public Bullet(int posX, int posY, PApplet app) {
		bulletSize = 15;
		speed = 5;
		this.posX = posX;
		this.posY = posY;
		this.app= app;
	}
	
	public void drawBullet() {
		app.circle(posX, posY, bulletSize);
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
}
