import processing.core.PApplet;
public class Main extends PApplet{
	public static void main (String[] args) {
		PApplet.main("Main");
	}
	
	public int width = 1600;
	public int height = 800;
	private int screen = 1;
	//screen play
	SpaceShip player;
	private int frameCount;
	
	//test
	Enemy a;
	
	public void settings() {
		size(width,height);
	}
	
	public void setup() {
		
		player = new SpaceShip(this);
		frameCount = 0;
		a = new EnemyBasic(this);
	}
	
	public void draw() {
		background(40);
		if(screen==1) {
			player.drawShip();
			player.moveShip(0, height);
			player.shoot();
			frameCount++;
			player.eliminateBullet(width);
			if(frameCount == 40) {
				player.generateBullet();
				frameCount = 0;				
			}
			
			//test enemy
			a.drawEnemy(this);
			a.move();
			
		}//end screen 1
	}
	
	public void keyPressed() {
		switch(key) {
			case 'w':
					player.setDir(false);				
				break;
			case 'd':
					player.setDir(true);
				break;
			case 'a':
				player.shipDash(height);
				break;
			case 'i':
				player.shield();
				break;
		}
			
	}
} 
