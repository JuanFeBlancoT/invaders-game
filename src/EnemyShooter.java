import java.util.ArrayList;

import processing.core.PApplet;

public class EnemyShooter extends Enemy{
	
	private int bulletsC;
	private ArrayList<Bullet>enemyBullets;
	
	public EnemyShooter(PApplet app, int posX, int posY) {
		super(app, posX, posY);
		points = 24;
		health = 2;
		enemySize = 60;
		speed = 2;
		damage = 1;
		visible = true;
		bulletsC = 0;
		enemyBullets = new ArrayList<>();
	}
	 
	public void drawEnemy(PApplet app) {
		System.out.println(enemyBullets.size());
		
		if(visible && health>0) {
			app.fill(180,180,140);
			app.circle(posX, posY, enemySize);
		}
		//should this be here ?
		if(health==0) {
			setVisible(false);
		}
		
		//enemyBullets
		if(bulletsC == 0) {
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
		for (int i = 0; i < enemyBullets.size(); i++) {
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
