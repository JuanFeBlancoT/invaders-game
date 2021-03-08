import processing.core.PApplet;
import processing.core.PImage;

public class InstrucScreen {
	private int width, height;
	boolean btnP, btnB;
	PImage instructions;
	PImage bp1;
	PImage bp2;
	PImage bb1;
	PImage bb2;
	
	public InstrucScreen(PApplet app, int width, int height) {
		
		this.width = width;
		this.height = height;
		btnP = false;
		btnB = false;
		
		instructions = app.loadImage("img/interfaces/InstruC.png");
		bb1 = app.loadImage("img/interfaces/back1.png");
		bb2 = app.loadImage("img/interfaces/back2.png");
		bp1 = app.loadImage("img/interfaces/bp1.png");
		bp2 = app.loadImage("img/interfaces/bp2.png");
		
	}
	
	public void loadScreen(PApplet app) {
		
		app.image(instructions, 0, 0);
		if(!btnB) {
			app.image(bb1, width-500, 790);
		}else {
			app.image(bb2, width-500, 790);
		}
		
		if(!btnP) {
			app.image(bp1, width-270, 790);
		}else {
			app.image(bp2, width-270, 790);
		}
	}

	public void setBtnP(boolean btnP) {
		this.btnP = btnP;
	}

	public void setBtnB(boolean btnB) {
		this.btnB = btnB;
	}
	
		
}
