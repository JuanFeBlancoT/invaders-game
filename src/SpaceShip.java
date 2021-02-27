import java.util.ArrayList;

import processing.core.PApplet;

public class SpaceShip {
	
	private int posX, posY, shipSize, lifes, dashColdDown, shieldColdDown, shieldTime;
	private boolean dir, shieldLoose;
	private PApplet app;
	private ArrayList<Bullet> bullets;
	
	public SpaceShip(PApplet app) {
		posX = 50;
		posY = 400;
		shipSize = 50;
		lifes = 20;
		this.app = app;
		dashColdDown = 0;
		shieldColdDown = 0;
		shieldTime = 40;
		shieldLoose = false;
		bullets = new ArrayList<>();
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

	public void drawShip() {
		//System.out.println(shieldLoose +"***"+ shieldTime+"****"+shieldColdDown);
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
			app.fill(120,120,200);
		}else{
			app.fill(255);
			shieldLoose = false;
		}	
		if(!shieldLoose) {
			shieldTime=40;
		}
		//end shield?
	}
	
	public void moveShip(int min, int max) {
		
		if(dir && posY<max-shipSize/2 ) {
			posY+=4;
		}else if(!dir && posY>shipSize/2){
			posY-=4;
		}
	}
	
	public void shipDash(int max) {
		if(dashColdDown==0) {
			if(!dir && posY>125) {
				posY-=100;
				dashColdDown = 120;
			}else if(!dir && posY<125) {
				posY = 26;
				dashColdDown = 120;
			}else if(dir && posY<max-125) {
				posY += 100;
				dashColdDown = 120;
			}else if(dir && posY>max-125) {
				posY = max-25;
				dashColdDown = 120;
			}
		}
	}
	
	public void generateBullet() {
		Bullet bullet = new Bullet(posX, posY, app);
		bullets.add(bullet);
	}
	
	public void shoot() {
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).drawBullet();
			bullets.get(i).move();
		}
	}
	
	public void eliminateBullet(int max) {
		for (int i = 0; i < bullets.size(); i++) {
			if(bullets.get(i).getPosX()>max) {
				bullets.remove(i);
			}
		}
	}
	
	public int getShipSize() {
		return shipSize;
	}

	public void setShipSize(int shipSize) {
		this.shipSize = shipSize;
	}

	public void shield() {
		
		if(shieldColdDown == 0) {
			shieldLoose = true;
			shieldColdDown = 600;
		}
	}
	

	
}

