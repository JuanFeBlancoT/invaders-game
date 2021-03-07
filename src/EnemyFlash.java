import processing.core.PApplet;
import processing.core.PImage;

public class EnemyFlash extends Enemy{

	PImage flash;
	public EnemyFlash(PApplet app, int posX, int posY,int speed) {
		super(app, posX, posY,speed);
		points = 17;
		health = 1;
		enemySize = 40;
		speed += 10;
		damage = 2;
		visible = true;
		
		flash = app.loadImage("img/flash.png");
	}
	 
	public void drawEnemy(PApplet app) {
		if(visible && health>0) {
			app.fill(220,130,20);
			//app.circle(posX, posY, enemySize);
			app.image(flash,posX-enemySize, posY-enemySize/2,5*enemySize, 3*enemySize);	
		}
		//should this be here ?
		if(health==0) {
			setVisible(false);
		}
	}
	
	public void move() {
		setPosX(posX-speed);
	}
}
