import java.util.ArrayList;
import processing.core.PApplet;

public class PlayScreen {
	
	//Attributes
	private int width, height;
	private int enemiesTimeGen, playTime, minutes, seconds, points;
	//relations
	private ArrayList<Enemy>enemies;
	private SpaceShip player;
	
	
	public PlayScreen(PApplet app, int width, int height) {
		player = new SpaceShip(app);
		this.width = width;
		this.height = height;
		enemies = new ArrayList<>();
		enemiesTimeGen = 0;
		playTime = 0;
		seconds = 0;
		minutes = 0;
		points = 0;
	}
	
	public void screenEvents(PApplet app) {
					
			player.drawShip();
			player.shoot();
			enemiesTimeGen++;
			player.eliminateBullet(width);
			//enemies generation
			if(enemiesTimeGen==110) {
				generateEnemies(app);
				enemiesTimeGen = 0;
			}
			//draw and move enemies
			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).drawEnemy(app);
				enemies.get(i).move();
			}
			
			eliminateEnemies();
			handleImpacts();
			
			timer(app);
			//test
			app.textSize(30);
			app.text("Points: "+points, 1400, 30);
		
	}
	
	
	private void timer(PApplet app) {
		playTime++;
		if(playTime %60 == 0) {
			seconds++;
			playTime = 0;
		}if(seconds==60) {
			seconds = 0;
			minutes++;
		}
		app.fill(255);
		if(minutes<10 && seconds<10) {
			app.text("0"+minutes+":0"+seconds, 1250, 30);
		}else if(minutes<10 && seconds>10){
			app.text("0"+minutes+":"+seconds, 1250, 30);
		}else {
			app.text(minutes+":"+seconds, 1250, 30);
		}
	}

	public void handleImpacts() {
		
		if(enemies.size()>0) {
			
			for (int j = 0; j < enemies.size() && enemies.get(j)!=null; j++) {
					
				//enemy colliding with 
				if(enemies.get(j).isVisible()==true && 
						Math.sqrt((Math.pow((player.getPosX()-enemies.get(j).getPosX()), 2))+
						(Math.pow((player.getPosY()-enemies.get(j).getPosY()), 2)))<player.getShipSize()) {
					
					if(!player.isVulnerable()) {
						player.setLifes(player.getLifes()-enemies.get(j).getDamage());
					}
					enemies.get(j).setVisible(false);
				}
			
				//enemy trespasses the frontier
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
						
						enemies.get(j).setHealth(enemies.get(j).getHealth()-(player.getBullets().get(i).getDamage()));
						player.getBullets().get(i).setVisible(false);
						if(enemies.get(j).getHealth()==0) {
							points+=enemies.get(j).getPoints();
						}
						
					}
				}
			}	
				
			
		}
	}//end handleImpacts
	
	public void generateEnemies(PApplet app) {
		int posX = (int) (Math.random()*400)+1650;
		int posY = (int) (Math.random()*730)+30;
		
		Enemy enemieX;
		int randomFactor = (int) (Math.random()*8);
		
		if(randomFactor==0 || randomFactor ==1) {
			enemieX = new EnemyBasic(app, posX, posY);
		}else if(randomFactor == 2 || randomFactor == 3){
			enemieX = new EnemyBasicBuff(app, posX, posY);
		}else if(randomFactor == 4 || randomFactor == 5){
			enemieX = new EnemyShifter(app, posX, posY, height);
		}else if(randomFactor == 6 ){
			enemieX = new EnemyFlash(app, posX, posY);
		}else {
			enemieX = new EnemyTank(app, posX, posY,height);
		}
		enemies.add(enemieX);
	}
	
	public void eliminateEnemies() {
		for (int i = 0; i < enemies.size() && enemies.get(i).isVisible() == false; i++) {
			enemies.remove(i);
		}
	}
	
	public void lateralShockWave(PApplet app) {
		if(player.lateralShockWave(app)) {
			for (int i = 0; i < enemies.size(); i++) {
				if(enemies.get(i).getPosX()<100) {
					enemies.get(i).setHealth(enemies.get(i).getHealth()-2);
					if(enemies.get(i).getHealth()==0) {
						points+=enemies.get(i).getPoints();
					}
				}
			}
		}
			
	}
	
	//Getters and Setters
	
	public SpaceShip getPlayer() {
		return player;
	}

	public void setPlayer(SpaceShip player) {
		this.player = player;
	}
	
}
