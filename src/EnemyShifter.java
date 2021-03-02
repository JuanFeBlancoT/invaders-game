import processing.core.PApplet;

public class EnemyShifter extends Enemy{

	int max;
	boolean dir;
	
	public EnemyShifter(PApplet app, int posX, int posY, int max) {
		super(app, posX, posY);
		points = 20;
		health = 2;
		enemySize = 60;
		speed = 1;
		damage = 2;
		visible = true;
		dir = true;
		this.max = max;
	}
	 
	public void drawEnemy(PApplet app) {
		if(visible && health>0) {
			app.fill(100,80,160);
			app.circle(posX, posY, enemySize);
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
		if(posY>(max-(enemySize/2)) || posY<enemySize/2){
			dir=!dir;
		}
		setPosX(posX-speed);
	}

}
