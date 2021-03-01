import processing.core.PApplet;

public class EndScreen {

	private int width, height;
	
	public EndScreen(PApplet app, int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void loadScreen(PApplet app) {		
		app.rect((width/2)-90, (height/2)+60, 180, 60);
	}
}
