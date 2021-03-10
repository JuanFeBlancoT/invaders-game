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
	PImage b;
	int frameC;
	
	
	public EnemyShooter(PApplet app, int posX, int posY,int speed, PImage s1, PImage s2,
			PImage s3, PImage s4, PImage s5, PImage s6, PImage s7, PImage s8, PImage b) {
		super(app, posX, posY,speed);
		points = 24;
		health = 3;
		enemySize = 70;
		speed += 2;
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
		this.b = b;
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
				app.image(s1,posX-enemySize, posY-enemySize, (3*enemySize)-10, (2*enemySize)-10);	
			}else if(frameC>8 && frameC<=16) {
				app.image(s2,posX-enemySize, posY-enemySize, (3*enemySize)-10, (2*enemySize)-10);	
			}else if(frameC>16 && frameC<=24) {
				app.image(s3,posX-enemySize, posY-enemySize, (3*enemySize)-10, (2*enemySize)-10);	
			}else if(frameC>24 && frameC<=32) {
				app.image(s4,posX-enemySize, posY-enemySize, (3*enemySize)-10, (2*enemySize)-10);	
			}else if(frameC>32 && frameC<=39) {
				app.image(s5,posX-enemySize, posY-enemySize, (3*enemySize)-10, (2*enemySize)-10);	
			}else if(frameC>39 && frameC<=46) {
				app.image(s6,posX-enemySize, posY-enemySize, (3*enemySize)-10, (2*enemySize)-10);	
			}else if(frameC>46 && frameC<=53) {
				app.image(s7,posX-enemySize, posY-enemySize, (3*enemySize)-10, (2*enemySize)-10);	
			}else if(frameC>53 && frameC<=60) {
				app.image(s8,posX-enemySize, posY-enemySize, (3*enemySize)-10, (2*enemySize)-10);	
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
		
		shoot(app);
		eliminateBullet();
	}

	private void generateBullet(PApplet app) {
		Bullet bullet = new Bullet(posX, posY+15, 5, 6, app, b);
		enemyBullets.add(bullet);
	}
	
	public void move() {
		setPosX(posX-speed);
	}
	
	public void shoot(PApplet app) {
		for (int i = 0; i < enemyBullets.size() && visible; i++) {
			enemyBullets.get(i).drawBullet(app);
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
