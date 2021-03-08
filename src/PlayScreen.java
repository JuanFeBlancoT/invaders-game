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
	PImage bg;
	//shooter
	PImage s1,s2,s3,s4,s5,s6,s7,s8;
	//basic
	PImage b1,b2,b3,b4,b5;
	//tank
	PImage t1,t2,t3;
	//roto
	PImage ro1,ro2;
	//eye
	PImage e1,e2,e3,e4,e5,e6;
	//doomFist
	PImage df1,df2,df3,df4;
	
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
		bg = app.loadImage("img/interfaces/bgp.png");
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
		//roto
		ro1 = app.loadImage("img/rotoImg/ro1.png");
		ro2 = app.loadImage("img/rotoImg/ro2.png");
		//eye
		e1 = app.loadImage("img/eyeImg/e1.png");
		e2 = app.loadImage("img/eyeImg/e2.png");
		e3 = app.loadImage("img/eyeImg/e3.png");
		e4 = app.loadImage("img/eyeImg/e4.png");
		e5 = app.loadImage("img/eyeImg/e5.png");
		e6 = app.loadImage("img/eyeImg/e6.png");
		//doomFist
		df1 = app.loadImage("img/doomfImg/df1.png");
		df2 = app.loadImage("img/doomfImg/df2.png");
		df3 = app.loadImage("img/doomfImg/df3.png");
		df4 = app.loadImage("img/doomfImg/df4.png");
		
	}

	public void screenEvents(PApplet app) {
			 //app.rect(0, 0, 1600,112);
		app.image(bg, 0, 100);
		for (int i = 0; i < player.getLifes(); i++) {
			app.fill(80,180,60);
			app.circle((i*20)+15, 50, 10);
		}
			deleteEnemies();
			player.drawShip(app);
			player.shoot();
			enemiesTimeGen++;
			player.eliminateBullet(width);
			//enemies generation
			if(enemiesTimeGen==110 && enemies.size()<14) {
				generateEnemies(app);
				enemiesTimeGen = 0;
			}
			//draw and move enemies
			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).drawEnemy(app);
				enemies.get(i).move();
			}
			
			handleImpacts();
			timer(app);
			//test
			app.textSize(30);
			app.text("Points: "+points, 1350, 60);
			
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
			app.text("0"+minutes+":0"+seconds, 1200, 60);
		}else if(minutes<10 && seconds>10){
			app.text("0"+minutes+":"+seconds, 1200, 60);
		}else {
			app.text(minutes+":"+seconds, 1200, 30);
		}
	}

	public void handleImpacts() {
		
		if(enemies.size()>0) {
			
		for (int j = 0; j < enemies.size() && enemies.get(j)!=null; j++) {
				
			//enemy colliding with player
			if(enemies.get(j).isVisible()==true && getDistanceBetweenP(player.getPosX(), enemies.get(j).getPosX(),
					player.getPosY(),enemies.get(j).getPosY())<player.getShipSize()) {
				
				if(player.isVulnerable()) {
					player.setLifes(player.getLifes()-enemies.get(j).getDamage());
				}
				enemies.get(j).setVisible(false);
				//eliminateEnemies(j);
			}
		
			//enemy trespasses the frontier
			if( enemies.get(j).isVisible() && enemies.get(j).getPosX()<-enemies.get(j).getEnemySize()) {
				player.setLifes(player.getLifes()-enemies.get(j).getDamage());
				enemies.get(j).setVisible(false);
				//eliminateEnemies(j);
			}
			
			//enemy gets shot
			for (int i = 0; i < player.getBullets().size(); i++) {
				if(player.getBullets().get(i).isVisible()==true && enemies.get(j).isVisible()==true && 
						getDistanceBetweenP(player.getBullets().get(i).getPosX(), enemies.get(j).getPosX(),
							player.getBullets().get(i).getPosY(), enemies.get(j).getPosY())<enemies.get(j).getEnemySize()/2) {
					
					enemies.get(j).setHealth(enemies.get(j).getHealth()-(player.getBullets().get(i).getDamage()));
					player.getBullets().get(i).setVisible(false);
					if(enemies.get(j).getHealth()==0) {
						points+=enemies.get(j).getPoints();
						enemies.get(j).setVisible(false);
						//eliminateEnemies(j);
					}
					
				}
			}
			
			//player shooted
			if(enemies.get(j) instanceof EnemyShooter) {
				for (int k = 0; k < ((EnemyShooter)enemies.get(j)).getEnemyBullets().size() ; k++) {
										
					if(((EnemyShooter)enemies.get(j)).getEnemyBullets().get(k).isVisible()==true && 
							getDistanceBetweenP(player.getPosX(), ((EnemyShooter)enemies.get(j)).getEnemyBullets().get(k).getPosX(),
							player.getPosY(), ((EnemyShooter)enemies.get(j)).getEnemyBullets().get(k).getPosY()) <player.getShipSize()/2) {								
						
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
	
	public int getDistanceBetweenP(int x1, int x2, int y1, int y2) {
		int distanceB =	(int) (Math.sqrt(Math.pow(x1-x2, 2))+(Math.sqrt(Math.pow(y1-y2, 2))));
		return distanceB;
	}
	
	public void generateEnemies(PApplet app) {
		int posX = (int) (Math.random()*100)+1650;
		int posY = (int) (Math.random()*700)+150;
		
		Enemy enemieX;
		int randomFactor=0;
		
		if(minutes==0 && seconds>0 && seconds<=20) {
			randomFactor = (int) (Math.random()*3);
		}else if(minutes ==0 && seconds>20 && seconds<=40) {
			randomFactor = (int) (Math.random()*5);
		}else if(minutes ==0 && seconds>40 && seconds<=60) {
			randomFactor = (int) (Math.random()*7);
		}else if(minutes ==1 && seconds>0 && seconds<=20) {
			randomFactor = (int) (Math.random()*10);
		}else if(minutes ==1 && seconds>20 && seconds<=45){
			randomFactor = (int) (Math.random()*10);
		}else {
			randomFactor = (int) (Math.random()*11);
		}		
		
		if(randomFactor==0 || randomFactor ==1) {
			enemieX = new EnemyBasic(app, posX, posY,minutes+3, b1, b2, b3, b4, b5);
		}else if(randomFactor == 2 || randomFactor == 3){
			enemieX = new EnemyBasicBuff(app, posX, posY,minutes+4,df4,df1,df2,df3);
		}else if(randomFactor == 4 || randomFactor == 5){
			enemieX = new EnemyShifter(app, posX, posY,minutes+1, height,ro1,ro2);
		}else if(randomFactor == 6 ){
			enemieX = new EnemyFlash(app, posX, posY,minutes+10, e1,e2,e3,e4,e5,e6);
		}else if(randomFactor == 7 || randomFactor == 8 ){
			enemieX = new EnemyTank(app, posX, posY,minutes+1,height, t1, t2, t3);
		}else {
			enemieX = new EnemyShooter(app, posX, posY,minutes+2, s1, s2, s3, s4, s5, s6, s7, s8);
		}
		enemies.add(enemieX);
	}
	
	public void eliminateEnemies(int i) {
			enemies.remove(i);
	}
	
	public void deleteEnemies() {
		for (int i = 0; i < enemies.size(); i++) {
			if(enemies.get(i).isVisible()==false){
				enemies.remove(i);
			}			
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
