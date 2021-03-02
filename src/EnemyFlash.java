import processing.core.PApplet;

public class EnemyFlash extends Enemy{

	public EnemyFlash(PApplet app, int posX, int posY) {
		super(app, posX, posY);
		points = 17;
		health = 1;
		enemySize = 40;
		speed = 8;
		damage = 2;
		visible = true;
	}
	 
	public void drawEnemy(PApplet app) {
		if(visible && health>0) {
			app.fill(220,130,20);
			app.circle(posX, posY, enemySize);
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
