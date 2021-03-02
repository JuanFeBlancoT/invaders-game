import processing.core.PApplet;

public class EnemyTank extends Enemy{
	int max, posXNow;
	boolean moveLeft, dirY;
	
	public EnemyTank(PApplet app, int posX, int posY, int max) {
		super(app, posX, posY);
		points = 40;
		health = 9;
		enemySize = 80;
		speed = 1;
		damage = 4;
		posXNow = posX;
		visible = true;
		dirY = false;
		moveLeft = true;
		this.max = max;
	}
	 
	public void drawEnemy(PApplet app) {
		if(visible && health>0) {
			app.fill(80,160,60);
			app.circle(posX, posY, enemySize);
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
		
		
		if((posY>(max-(enemySize/2)) || posY<enemySize/2) && !moveLeft){
			dirY = !dirY;
			posXNow = posX;
			moveLeft = true;
		}
		if(moveLeft && posX<posXNow-100) {
			moveLeft = false;
		}
	}

}
