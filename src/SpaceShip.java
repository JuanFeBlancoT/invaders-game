import java.util.ArrayList;

import processing.core.PApplet;

public class SpaceShip {
	
	//Attributes
	private int posX, posY, shipSize, lifes, dashColdDown, shieldColdDown, shieldTime, speed, shootColdDown;
	private int opBulletColdDown, shockColdDown;
	private boolean dir, shieldLoose, vulnerable, shockWave;
	//relations
	private PApplet app;
	private ArrayList<Bullet> bullets;
	
	public SpaceShip(PApplet app) {
		
		posX = 50;
		posY = 400;
		shipSize = 50;
		lifes = 5;
		dashColdDown = 0;
		shieldColdDown = 0;
		shieldTime = 40;
		speed = 6;
		shootColdDown = 0;
		opBulletColdDown = 0;
		
		shieldLoose = false;
		vulnerable = true;
		shockWave = false;
		
		this.app = app;
		
		bullets = new ArrayList<>();
	}

	public void drawShip() {
		app.fill(255);
		app.circle(posX, posY, shipSize);
		//other method?
		if(dashColdDown>0) {
			dashColdDown--;
		}
		//shield????
		if(shieldColdDown>0) {
			shieldColdDown--;
		}
		if(shieldLoose && shieldTime>0) {
			shieldTime--;
			vulnerable = false;
			app.fill(120,120,200);
		}else{
			app.fill(255);
			shieldLoose = false;
		}	
		if(!shieldLoose) {
			shieldTime=40;
			vulnerable=true;
		}
		//end shield?
		//shooting coldD?
		if(shootColdDown>0) {
			shootColdDown--;
		}
		
		if(opBulletColdDown>0) {
			opBulletColdDown--;
		}
		
		if(shockColdDown > 0) {
			shockColdDown--;
		}
	}//end drawShip
	
	public void moveShip(int max, boolean dir) {
		
		if(dir && posY<max-shipSize/2 ) {
			posY+=speed;
		}else if(!dir && posY>shipSize/2){
			posY-=speed;
		}
	}
	
	public void shipDash(int max) {
		if(dashColdDown==0) {
			if(!dir && posY>145) {
				posY-=120;
				dashColdDown = 120;
			}else if(!dir && posY<145) {
				posY = 26;
				dashColdDown = 120;
			}else if(dir && posY<max-145) {
				posY += 120;
				dashColdDown = 120;
			}else if(dir && posY>max-145) {
				posY = max-25;
				dashColdDown = 120;
			}
		}
	}//end shipDash
	
	public void generateBullet() {
		if(shootColdDown==0) {
			Bullet bullet = new Bullet(posX, posY, 1, 7, app);
			bullets.add(bullet);
			shootColdDown = 15;
		}
	}
	
	public void generateOpBullet(){
		if(opBulletColdDown==0) {
			Bullet bullet = new Bullet(posX, posY, 5, 7, app);
			bullets.add(bullet);
			opBulletColdDown = 900;
		}
	}
	
	public boolean lateralShockWave(PApplet app) {
		if(shockColdDown == 0) {
			shockWave = true;
			app.rect(0, 0, 100, 800);
			shockColdDown = 960;
		}else {
			shockWave = false;
		}
		return shockWave;
	}
	
	public void shoot() {
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).drawBullet();
			bullets.get(i).move();
		}
	}
	
	public void eliminateBullet(int max) {
		for (int i = 0; i < bullets.size(); i++) {
			if(bullets.get(i).getPosX()>max || bullets.get(i).isVisible()==false) {
				bullets.remove(i);
			}
		}
	}
	
	public void shield() {
		
		if(shieldColdDown == 0) {
			shieldLoose = true;
			shieldColdDown = 600;
		}
	}
	//Getters and Setters
	
	public int getShipSize() {
		return shipSize;
	}

	public void setShipSize(int shipSize) {
		this.shipSize = shipSize;
	}
	
	public boolean isVulnerable() {
		return vulnerable;
	}

	public void setVulnerable(boolean vulnerable) {
		this.vulnerable = vulnerable;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}
	
	public boolean isDir() {
		return dir;
	}

	public void setDir(boolean dir) {
		this.dir = dir;
	}
	
	public boolean isShockWave() {
		return shockWave;
	}

	public void setShockWave(boolean shockWave) {
		this.shockWave = shockWave;
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}
	
}

