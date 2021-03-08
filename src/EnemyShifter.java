import processing.core.PApplet;
import processing.core.PImage;

public class EnemyShifter extends Enemy{

	int max, frameC;
	boolean dir;
	PImage r1;
	PImage r2;
	
	public EnemyShifter(PApplet app, int posX, int posY,int speed, int max, PImage r1, PImage r2) {
		super(app, posX, posY, speed);
		points = 20;
		health = 2;
		enemySize = 60;
		speed +=1;
		damage = 2;
		visible = true;
		dir = true;
		this.max = max;
		this.r1=r1;
		this.r2=r2;
		frameC=0;
	}
	 
	public void drawEnemy(PApplet app) {
		frameC++;
		if(visible && health>0) {
			app.fill(100,80,160);
			if(frameC>0 && frameC<=9) {
				app.image(r1,posX-enemySize, posY-enemySize, 2*enemySize-10,2*enemySize-10);	
			}else if(frameC>9 && frameC<=18) {
				app.image(r2,posX-enemySize, posY-enemySize,2*enemySize-10,2*enemySize-10);	
				if(frameC==18) {
					frameC=0;
				}
			}
			//app.circle(posX, posY, enemySize);
		}
		//should this be here ?
		if(health==0) {
			setVisible(false);
		}
	}
	
	public void move() {
		if(dir) {
			setPosY(posY+(speed+1));
		}else {
			setPosY(posY-(speed+1));
		}
		if(posY>(max-(enemySize/2)) || posY<130){
			dir=!dir;
		}
		setPosX(posX-speed);
	}

}
