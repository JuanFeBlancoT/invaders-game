import processing.core.PApplet;

public class Main extends PApplet {
	public static void main (String[] args) {
		PApplet.main("Main");
	}
	
	public int width = 1600;
	public int height = 800;
	private int screen;
	private boolean up, down, dash, shield, shoot;
	private PlayScreen playScreen;
	
	
	public void settings() {
		size(width,height);
	}
	
	public void setup() {
		screen = 1;
		playScreen = new PlayScreen(this, width, height);
		up = false;
		down = false;
		dash = false;
		shield = false;
		shoot = false;
		
	}
	
	public void draw() {
		background(40);
		if(screen==1) {
			playScreen.screenEvents(this);
			//key events
			keyEvents();
		}//end screen 1
	}
	
	public void keyPressed() {
				
		switch(key) {
			case 'w':
				up = true;
				System.out.println("UP");
				break;
			case 'd':
				down = true;
				System.out.println("DOWN");
				break;
			case 'a':
				dash = true;
				System.out.println("DASH");
				break;
			case 'i':
				shield = true;
				System.out.println("SHIELD");
				break;
			case ' ':
				shoot = true;
				System.out.println("SHOOT");
				break;
		}
			
	}
	
	public void keyReleased() {
		switch(key) {
		case 'w':
			up = false;
			break;
		case 'd':
			down = false;
			break;
		case 'a':
			dash = false;
			break;
		case 'i':
			dash = false;
			break;
		case ' ':
			shoot = false;
			break;
	}
	}
	
	public void keyEvents() {
		
		if(up) {
			playScreen.player.moveShip(height, false);
			playScreen.player.setDir(false);
		}
		if(down) {
			playScreen.player.moveShip(height, true);
			playScreen.player.setDir(true);
		}
		if(dash) {
			playScreen.player.shipDash(height);
		}
		if(shield) {
			playScreen.player.shield();
			shield = false;
		}
		if(shoot) {
			playScreen.player.generateBullet();
			shoot = false;
		}
	}

	
} 
