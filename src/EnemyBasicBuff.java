import processing.core.PApplet;
import processing.core.PImage;

public class EnemyBasicBuff extends Enemy{

	private int frameC;
	PImage df1,df2,df3,df4;
	
	public EnemyBasicBuff(PApplet app, int posX, int posY,int speed, PImage d1, PImage d2, PImage d3, PImage d4) {
		super(app, posX, posY, speed);
		points = 15;
		health = 2;
		enemySize = 75;
		speed += 4;
		damage = 1;
		visible = true;
		
		df1 = d1;
		df2 = d2;
		df3 = d3;
		df4 = d4;
		frameC = 0;
	}
	 
	public void drawEnemy(PApplet app) {
		frameC++;
		if(visible && health>0) {
			app.fill(180,180,140);
			//app.circle(posX, posY, enemySize);
			
			if(frameC>0 && frameC<=8) {
				app.image(df2,posX-enemySize, posY-50,enemySize+55, enemySize+25);	
			}else if(frameC>8 && frameC<=16) {
				app.image(df3,posX-enemySize, posY-50,enemySize+55, enemySize+25);	
			}else if(frameC>16 && frameC<=24) {
				app.image(df4,posX-enemySize, posY-50,enemySize+55, enemySize+25);	
			}else if(frameC>24 && frameC<=32) {
				app.image(df3,posX-enemySize, posY-50,enemySize+55, enemySize+25);	
			}else if(frameC>32 && frameC<=40) {
				app.image(df2,posX-enemySize, posY-50,enemySize+55, enemySize+25);	
			}else if(frameC>40 && frameC<=48) {
				app.image(df1,posX-enemySize, posY-50,enemySize+55, enemySize+25);
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
