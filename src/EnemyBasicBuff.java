import processing.core.PApplet;

public class EnemyBasicBuff extends Enemy{

	public EnemyBasicBuff(PApplet app, int posX, int posY) {
		super(app, posX, posY);
		points = 15;
		health = 2;
		enemySize = 60;
		speed = 4;
		damage = 1;
		visible = true;
	}
	 
	public void drawEnemy(PApplet app) {
		if(visible && health>0) {
			app.fill(180,180,140);
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
