import processing.core.PApplet;

public class MenuScreen {

	private int width, height;
	
	public MenuScreen(PApplet app, int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void loadScreen(PApplet app) {
		
		app.rect((width/2)-90, (height/2)-80, 180, 60);
		
		app.rect((width/2)-90, (height/2)+40, 180, 60);
	}
}
