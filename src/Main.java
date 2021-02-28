import processing.core.PApplet;

public class Main extends PApplet {
	public static void main (String[] args) {
		PApplet.main("Main");
	}
	
	//Attributes
	public int width = 1600;
	public int height = 800;
	private int screen;
	private boolean up, down, dash, shield, shoot;
	//relations
	private PlayScreen playScreen;
	
	
	public void settings() {
		size(width,height);
	}
	
	public void setup() {
		
		//Attributes
		screen = 1;
		up = false;
		down = false;
		dash = false;
		shield = false;
		shoot = false;
		//relations
		playScreen = new PlayScreen(this, width, height);
		
	}
	
	public void draw() {
		background(40);
		if(screen==1) {
			playScreen.screenEvents(this);
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
			playScreen.getPlayer().moveShip(height, false);
			playScreen.getPlayer().setDir(false);
		}
		if(down) {
			playScreen.getPlayer().moveShip(height, true);
			playScreen.getPlayer().setDir(true);
		}
		if(dash) {
			playScreen.getPlayer().shipDash(height);
		}
		if(shield) {
			playScreen.getPlayer().shield();
			shield = false;
		}
		if(shoot) {
			playScreen.getPlayer().generateBullet();
			shoot = false;
		}
	}

	
} 
