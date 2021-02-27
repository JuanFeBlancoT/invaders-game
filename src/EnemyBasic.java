import processing.core.PApplet;

public class EnemyBasic extends Enemy{
	
	public EnemyBasic(PApplet app) {
		super(app);
		points = 10;
		health = 2;
		enemySize = 60;
		speed = 4;
		damage = 1;
		visible = true;
	}
	 
	public void drawEnemy(PApplet app) {
		if(visible && health>0) {
			app.fill(200,100,100);
			app.circle(posX, posY, enemySize);
		}
	}
	
	public void move() {
		setPosX(posX-speed);
	}
}
