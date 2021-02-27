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
		System.out.println(a.getHealth());
		
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
		if( a.isVisible() && a.getPosX()<-a.getEnemySize()) {
			player.setLifes(player.getLifes()-a.getDamage());
			a.setVisible(false);
		}
		
		//enemy gets shot
		for (int i = 0; i < player.getBullets().size(); i++) {
		
			if(player.getBullets().get(i).isVisible()==true && a.isVisible()==true && 
					Math.sqrt((Math.pow((player.getBullets().get(i).getPosX()-a.getPosX()), 2))+
					(Math.pow((player.getBullets().get(i).getPosY()-a.getPosY()), 2)))<
					player.getBullets().get(i).getBulletSize()) {
				
				a.setHealth(a.getHealth()-1);
				player.getBullets().get(i).setVisible(false);
			}
		}
	}//end handleImpacts
	
}
