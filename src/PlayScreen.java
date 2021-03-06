import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

public class PlayScreen {
	
	//Attributes
	private int width, height;
	private int enemiesTimeGen, playTime, minutes, seconds, points;
	//relations
	private ArrayList<Enemy>enemies;
	private SpaceShip player;
	
	//images
	//shooter
	PImage s1;
	PImage s2;
	PImage s3;
	PImage s4;
	PImage s5;
	PImage s6;
	PImage s7;
	PImage s8;
	//basic
	PImage b1;
	PImage b2;
	PImage b3;
	PImage b4;
	PImage b5;
	//tank
	PImage t1;
	PImage t2;
	PImage t3;
	
	
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
		
		loadImages(app);
	
	}
	
	private void loadImages(PApplet app) {
		//shooter
		s1 = app.loadImage("img/shooterImg/sh1.png");
		s2 = app.loadImage("img/shooterImg/sh2.png");
		s3 = app.loadImage("img/shooterImg/sh3.png");
		s4 = app.loadImage("img/shooterImg/sh4.png");
		s5 = app.loadImage("img/shooterImg/sh5.png");
		s6 = app.loadImage("img/shooterImg/sh6.png");
		s7 = app.loadImage("img/shooterImg/sh7.png");
		s8 = app.loadImage("img/shooterImg/sh8.png");
		//basic
		b1 = app.loadImage("img/basicImg/ba1.png");
		b2 = app.loadImage("img/basicImg/ba2.png");
		b3 = app.loadImage("img/basicImg/ba3.png");
		b4 = app.loadImage("img/basicImg/ba4.png");
		b5 = app.loadImage("img/basicImg/ba5.png");
		//tank
		t1 = app.loadImage("img/tankImg/t1.png");
		t2 = app.loadImage("img/tankImg/t2.png");
		t3 = app.loadImage("img/tankImg/t3.png");
		
	}

	public void screenEvents(PApplet app) {
			
		for (int i = 0; i < player.getLifes(); i++) {
			app.fill(80,180,60);
			app.circle((i*20)+15, 20, 10);
		}
			player.drawShip(app);
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
				
				if(player.isVulnerable()) {
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
			
			//player shooted
			if(enemies.get(j) instanceof EnemyShooter) {
				for (int k = 0; k < ((EnemyShooter)enemies.get(j)).getEnemyBullets().size() ; k++) {
										
					if(((EnemyShooter)enemies.get(j)).getEnemyBullets().get(k).isVisible()==true &&
							Math.sqrt((Math.pow((player.getPosX()-((EnemyShooter)enemies.get(j)).getEnemyBullets().get(k).getPosX()), 2)))+
							Math.sqrt((Math.pow((player.getPosY()-((EnemyShooter)enemies.get(j)).getEnemyBullets().get(k).getPosY()), 2)))<
							player.getShipSize()/2) {
						
						if(player.isVulnerable()) {
							player.setLifes(player.getLifes()-enemies.get(j).getDamage());
						}
						((EnemyShooter)enemies.get(j)).getEnemyBullets().get(k).setVisible(false);
						}
					}
				}//end get shot
			}	
		}
	}//end handleImpacts
	
	public void generateEnemies(PApplet app) {
		int posX = (int) (Math.random()*400)+1650;
		int posY = (int) (Math.random()*730)+30;
		
		Enemy enemieX;
		int randomFactor = (int) (Math.random()*10);
		
		if(randomFactor==0 || randomFactor ==1) {
			enemieX = new EnemyBasic(app, posX, posY, b1, b2, b3, b4, b5);
		}else if(randomFactor == 2 || randomFactor == 3){
			enemieX = new EnemyBasicBuff(app, posX, posY);
		}else if(randomFactor == 4 || randomFactor == 5){
			enemieX = new EnemyShifter(app, posX, posY, height);
		}else if(randomFactor == 6 ){
			enemieX = new EnemyFlash(app, posX, posY);
		}else if(randomFactor == 7 || randomFactor == 8 ){
			enemieX = new EnemyTank(app, posX, posY,height, t1, t2, t3);
		}else {
			enemieX = new EnemyShooter(app, posX, posY, s1, s2, s3, s4, s5, s6, s7, s8);
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
