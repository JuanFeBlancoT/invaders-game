import processing.core.PApplet;
import processing.core.PImage;

public class EnemyFlash extends Enemy{

	int frameC;
	PImage e1;
	PImage e2;
	PImage e3;
	PImage e4;
	PImage e5;
	PImage e6;
	
	public EnemyFlash(PApplet app, int posX, int posY,int speed, PImage i1, PImage i2, PImage i3, PImage i4, PImage i5, PImage i6) {
		super(app, posX, posY,speed);
		points = 17;
		health = 1;
		enemySize = 50;
		speed += 10;
		damage = 2;
		visible = true;
		
		e1 = i1;
		e2 = i2;
		e3 = i3;
		e4 = i4;
		e5 = i5;
		e6 = i6;
		frameC = 0;
	}
	 
	public void drawEnemy(PApplet app) {
		frameC++;
		if(visible && health>0) {
			app.fill(220,130,20);
			app.circle(posX, posY, enemySize);
			if(frameC>0 && frameC<=8) {
				app.image(e1,posX, posY-75,5*enemySize-10, 3*enemySize-10);	
			}else if(frameC>8 && frameC<=16) {
				app.image(e2,posX, posY-75,5*enemySize-10, 3*enemySize-10);	
			}else if(frameC>16 && frameC<=24) {
				app.image(e3,posX, posY-75,5*enemySize-10, 3*enemySize-10);	
			}else if(frameC>24 && frameC<=32) {
				app.image(e4,posX, posY-75,5*enemySize-10, 3*enemySize-10);	
			}else if(frameC>32 && frameC<=40) {
				app.image(e5,posX, posY-75,5*enemySize-10, 3*enemySize-10);	
			}else if(frameC>40 && frameC<=48) {
				app.image(e6,posX, posY-75,5*enemySize-10, 3*enemySize-10);
				if(frameC==48) {
					frameC=0;
				}
			}
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
