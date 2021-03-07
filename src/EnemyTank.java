import processing.core.PApplet;
import processing.core.PImage;

public class EnemyTank extends Enemy{
	int max, posXNow;
	boolean moveLeft, dirY;
	
	PImage t1;
	PImage t2;
	PImage t3;
	int frameC;
	
	public EnemyTank(PApplet app, int posX, int posY,int speed, int max, PImage t1, PImage t2, PImage t3) {
		super(app, posX, posY,speed);
		points = 40;
		health = 9;
		enemySize = 90;
		speed += 1;
		damage = 4;
		posXNow = posX;
		visible = true;
		dirY = false;
		moveLeft = true;
		this.max = max;
		
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
		frameC = 0;	
	}
	 
	public void drawEnemy(PApplet app) {
		if(visible && health>0) {
			
			frameC++;
			app.fill(80,160,60);
			app.circle(posX, posY, enemySize);
			if(frameC>0 && frameC <=10) {
				app.image(t1,posX-enemySize, posY-enemySize,enemySize+70,enemySize+70);	
			}else if(frameC>10 && frameC<=20) {
				app.image(t2,posX-enemySize, posY-enemySize,enemySize+70,enemySize+70);	
			}else if(frameC>20 && frameC<=30) {
				app.image(t3,posX-enemySize, posY-enemySize,enemySize+70,enemySize+70);
				if(frameC==30) {
					frameC = 0;
				}
			}
			
		}
		//should this be here ?
		if(health==0) {
			setVisible(false);
		}
	}
	
	public void move() {
		if(moveLeft) {
			posX-=speed;
		}else if(dirY && !moveLeft) {
			posY+=(speed+1);
		}else if(!dirY && !moveLeft) {
			posY-=(speed+1);
		}
		
		
		if((posY>(max-(enemySize/2)) || posY<160) && !moveLeft){
			dirY = !dirY;
			posXNow = posX;
			moveLeft = true;
		}
		if(moveLeft && posX<posXNow-100) {
			moveLeft = false;
		}
	}

}
