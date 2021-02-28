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
	private MenuScreen menuScreen;
	
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
		menuScreen = new MenuScreen(this, width, height);
	}
	
	public void draw() {
		background(40);
		if(screen==1) {
			menuScreen.loadScreen(this);
		}else if(screen==3) {
			playScreen.screenEvents(this);
			keyEvents();
		}//end screen 1
	}
	
	public void keyPressed() {
				
		switch(key) {
			case 'w':
				up = true;
				break;
			case 'd':
				down = true;
				break;
			case 'a':
				dash = true;
				break;
			case 'i':
				shield = true;
				break;
			case ' ':
				shoot = true;
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
	
	public void mouseClicked() {
		if(screen==1 && mouseX>(width/2)-90 && mouseX<(width/2)+90 && mouseY>(height/2)-80 && mouseY<(height/2)+20) {
			screen = 2;
		}
		
		if(screen==1 && mouseX>(width/2)-90 && mouseX<(width/2)+90 && mouseY>(height/2)+40 && mouseY<(height/2)+100) {
			screen = 3;
		}
	}
} 
