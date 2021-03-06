import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class EnemyShooter extends Enemy{
	
	private int bulletsC;
	private ArrayList<Bullet>enemyBullets;
	
	//test
	PImage s1;
	PImage s2;
	PImage s3;
	PImage s4;
	PImage s5;
	PImage s6;
	PImage s7;
	PImage s8;
	int frameC;
	
	
	public EnemyShooter(PApplet app, int posX, int posY, PImage s1, PImage s2,
			PImage s3, PImage s4, PImage s5, PImage s6, PImage s7, PImage s8) {
		super(app, posX, posY);
		points = 24;
		health = 2;
		enemySize = 60;
		speed = 2;
		damage = 1;
		visible = true;
		bulletsC = 0;
		enemyBullets = new ArrayList<>();
		
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
		this.s4 = s4;
		this.s5 = s5;
		this.s6 = s6;
		this.s7 = s7;
		this.s8 = s8;
		frameC = 20;
	}
	 
	public void drawEnemy(PApplet app) {
		
		//test
		frameC++;
		if(visible && health>0) {
			app.fill(180,180,140);
			//app.circle(posX, posY, enemySize);
			//test
			if(frameC>0 && frameC<=8) {
				app.image(s1,posX-enemySize, posY-2*enemySize, 3*enemySize, 2*enemySize);	
			}else if(frameC>8 && frameC<=16) {
				app.image(s2,posX-enemySize, posY-2*enemySize, 3*enemySize, 2*enemySize);	
			}else if(frameC>16 && frameC<=24) {
				app.image(s3,posX-enemySize, posY-2*enemySize, 3*enemySize, 2*enemySize);	
			}else if(frameC>24 && frameC<=32) {
				app.image(s4,posX-enemySize, posY-2*enemySize, 3*enemySize, 2*enemySize);	
			}else if(frameC>32 && frameC<=39) {
				app.image(s5,posX-enemySize, posY-2*enemySize, 3*enemySize, 2*enemySize);	
			}else if(frameC>39 && frameC<=46) {
				app.image(s6,posX-enemySize, posY-2*enemySize, 3*enemySize, 2*enemySize);	
			}else if(frameC>46 && frameC<=53) {
				app.image(s7,posX-enemySize, posY-2*enemySize, 3*enemySize, 2*enemySize);	
			}else if(frameC>53 && frameC<=60) {
				app.image(s8,posX-enemySize, posY-2*enemySize, 3*enemySize, 2*enemySize);	
				if(frameC==60) {
					frameC=0;
				}
			}
			//end test
			
		}
		//should this be here ?
		if(health==0) {
			setVisible(false);
		}
		
		//enemyBullets
		if(bulletsC == 0 && visible) {
			generateBullet(app);
			bulletsC = 120;
		}
		
		if(bulletsC>0) {
			bulletsC--;
		}
		
		shoot();
		eliminateBullet();
	}

	private void generateBullet(PApplet app) {
				Bullet bullet = new Bullet(posX, posY, 5, 6, app);
				enemyBullets.add(bullet);
	}
	
	public void move() {
		setPosX(posX-speed);
	}
	
	public void shoot() {
		for (int i = 0; i < enemyBullets.size() && visible; i++) {
			enemyBullets.get(i).drawBullet();
			enemyBullets.get(i).moveBackwards();
		}
	}
	
	public void eliminateBullet() {
		for (int i = 0; i < enemyBullets.size(); i++) {
			if(enemyBullets.get(i).getPosX()<0 || enemyBullets.get(i).isVisible()==false) {
				enemyBullets.remove(i);
			}
		}
	}
	
	//Getters and setters
	public ArrayList<Bullet> getEnemyBullets() {
		return enemyBullets;
	}

	public void setEnemyBullets(ArrayList<Bullet> enemyBullets) {
		this.enemyBullets = enemyBullets;
	}
}
