import processing.core.PApplet;
import processing.core.PImage;

public class EnemyBasic extends Enemy{
	
	PImage b1;
	PImage b2;
	PImage b3;
	PImage b4;
	PImage b5;
	int frameC;
	
	public EnemyBasic(PApplet app, int posX, int posY,int speed, PImage b1, PImage b2, PImage b3, PImage b4, PImage b5) {
		super(app, posX, posY, speed);
		points = 10;
		health = 1;
		enemySize = 60;
		speed+=3;
		damage = 1;
		visible = true;
		
		this.b1 = b1;
		this.b2 = b2;
		this.b3 = b3;
		this.b4 = b4;
		this.b5 = b5;
		frameC = 0;
	}
	 
	public void drawEnemy(PApplet app) {
		if(visible && health>0) {
			app.fill(200,100,100);
			
			frameC++;
			//app.circle(posX, posY, enemySize);
			if(frameC>0 && frameC<=8) {
				app.image(b1,posX-enemySize, posY-enemySize, 2*enemySize, 2*enemySize);	
			}else if(frameC>8 && frameC<=16) {
				app.image(b2,posX-enemySize, posY-enemySize, 2*enemySize, 2*enemySize);	
			}else if(frameC>16 && frameC<=24) {
				app.image(b3,posX-enemySize, posY-enemySize, 2*enemySize, 2*enemySize);	
			}else if(frameC>24 && frameC<=32) {
				app.image(b4,posX-enemySize, posY-enemySize, 2*enemySize, 2*enemySize);	
			}else if(frameC>32 && frameC<=39) {
				app.image(b5,posX-enemySize, posY-enemySize, 2*enemySize, 2*enemySize);	
			}else if(frameC>39 && frameC<=46) {
				app.image(b4,posX-enemySize, posY-enemySize, 2*enemySize, 2*enemySize);	
			}else if(frameC>46 && frameC<=53) {
				app.image(b3,posX-enemySize, posY-enemySize, 2*enemySize, 2*enemySize);	
			}else if(frameC>53 && frameC<=60) {
				app.image(b2,posX-enemySize, posY-enemySize, 2*enemySize, 2*enemySize);	
				if(frameC==60) {
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
