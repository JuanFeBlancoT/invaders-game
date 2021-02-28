import java.util.ArrayList;
import processing.core.PApplet;

public class PlayScreen {
	
	SpaceShip player;
	private int frameCount, enemiesTimeGen;
	
	private ArrayList<Enemy>enemies;
	
	private int width, height;
	
	public PlayScreen(PApplet app, int width, int height) {
		player = new SpaceShip(app);
		frameCount = 0;
	
		this.width = width;
		this.height = height;
		enemies = new ArrayList<>();
		enemiesTimeGen = 0;
	}
	
	public void screenEvents(PApplet app) {
		
		player.drawShip();
		player.moveShip(0, height);
		player.shoot();
		frameCount++;
		enemiesTimeGen++;
		player.eliminateBullet(width);
		if(frameCount == 40) {
			player.generateBullet();
			frameCount = 0;				
		}
		
		if(enemiesTimeGen==110) {
			generateEnemies(app);
			enemiesTimeGen = 0;
		}
		//other method ?
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).drawEnemy(app);
			enemies.get(i).move();
		}
		
		handleImpacts();
	}
	
	public void handleImpacts() {
		
		
		if(enemies.size()>0) {
			
			for (int j = 0; j < enemies.size() && enemies.get(j)!=null; j++) {
					
				//enemy colliding with 
				if( enemies.get(j).isVisible()==true && Math.sqrt((Math.pow((player.getPosX()-enemies.get(j).getPosX()), 2))+
						(Math.pow((player.getPosY()-enemies.get(j).getPosY()), 2)))<player.getShipSize()) {
					
					player.setLifes(player.getLifes()-enemies.get(j).getDamage());
					enemies.get(j).setVisible(false);
				}
			
				//enemy traspases the frontier
				if( enemies.get(j).isVisible() && enemies.get(j).getPosX()<-enemies.get(j).getEnemySize()) {
					player.setLifes(player.getLifes()-enemies.get(j).getDamage());
					enemies.get(j).setVisible(false);
				}
				
				//enemy gets shot
				for (int i = 0; i < player.getBullets().size(); i++) {
					if(player.getBullets().get(i).isVisible()==true && enemies.get(j).isVisible()==true && 
							Math.sqrt((Math.pow((player.getBullets().get(i).getPosX()-enemies.get(j).getPosX()), 2))+
							(Math.pow((player.getBullets().get(i).getPosY()-enemies.get(j).getPosY()), 2)))<
							enemies.get(j).getEnemySize()/2) {
						
						enemies.get(j).setHealth(enemies.get(j).getHealth()-1);
						player.getBullets().get(i).setVisible(false);
					}
				}
			}	
				
			
		}
	}//end handleImpacts
	
	public void generateEnemies(PApplet app) {
		int posX = (int) (Math.random()*400)+1650;
		int posY = (int) (Math.random()*730)+30;
		
		Enemy enemieX= new EnemyBasic(app, posX, posY);
		enemies.add(enemieX);
	}
	
}
