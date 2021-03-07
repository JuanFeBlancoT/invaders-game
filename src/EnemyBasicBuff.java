import processing.core.PApplet;
import processing.core.PImage;

public class EnemyBasicBuff extends Enemy{

	PImage hand;
	public EnemyBasicBuff(PApplet app, int posX, int posY,int speed) {
		super(app, posX, posY, speed);
		points = 15;
		health = 2;
		enemySize = 60;
		speed += 4;
		damage = 1;
		visible = true;
		
	
		hand = app.loadImage("img/buff.png");
	}
	 
	public void drawEnemy(PApplet app) {
		if(visible && health>0) {
			app.fill(180,180,140);
			//app.circle(posX, posY, enemySize);
			app.image(hand,posX-enemySize, posY-enemySize,enemySize+50,enemySize +50);	
			
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
