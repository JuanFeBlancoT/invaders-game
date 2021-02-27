import processing.core.PApplet;
public class Main extends PApplet{
	public static void main (String[] args) {
		PApplet.main("Main");
	}
	
	public int width = 1600;
	public int height = 800;
	private int screen;
	
	private PlayScreen playScreen;
	
	
	public void settings() {
		size(width,height);
	}
	
	public void setup() {
		screen = 1;
		playScreen = new PlayScreen(this, width, height);
	}
	
	public void draw() {
		background(40);
		if(screen==1) {
			playScreen.screenEvents(this);
			
		}//end screen 1
	}
	
	public void keyPressed() {
		switch(key) {
			case 'w':
					playScreen.player.setDir(false);				
				break;
			case 'd':
				playScreen.player.setDir(true);
				break;
			case 'a':
				playScreen.player.shipDash(height);
				break;
			case 'i':
				playScreen.player.shield();
				break;
		}
			
	}
} 
