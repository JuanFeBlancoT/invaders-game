import processing.core.PApplet;

public class PlayScreen {
	
	SpaceShip player;
	private int frameCount;
	Enemy a;
	private int width, height;
	
	public PlayScreen(PApplet app, int width, int height) {
		player = new SpaceShip(app);
		frameCount = 0;
		a = new EnemyBasic(app);
		this.width = width;
		this.height = height;
	}
	
	public void screenEvents(PApplet app) {
		
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
		a.drawEnemy(app);
		a.move();
		
		handleImpacts();
	}
	
	public void handleImpacts() {
		
		//enemy colliding with 
		if( a.isVisible()==true && Math.sqrt((Math.pow((player.getPosX()-a.getPosX()), 2))+
				(Math.pow((player.getPosY()-a.getPosY()), 2)))<player.getShipSize()) {
			
			player.setLifes(player.getLifes()-a.getDamage());
			a.setVisible(false);
		}
		
		//enemy traspases the frontier
	}
	
}
