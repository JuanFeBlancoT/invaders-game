import processing.core.PApplet;
import processing.core.PImage;

public class EndScreen {

	private int width, seconds, minutes, points;
	private boolean btnP;
	PImage bg, bp1, bp2;
	
	public EndScreen(PApplet app, int width) {
		this.width = width;
		btnP = false;
		
		bg = app.loadImage("img/interfaces/gameO.png");
		bp1 = app.loadImage("img/interfaces/bp1.png");
		bp2 = app.loadImage("img/interfaces/bp2.png");
	}
	
	public void loadScreen(PApplet app) {
		app.image(bg, 0, 0);
		
		if(!btnP) {
			app.image(bp1, (width/2)-110, 700);
		}else {
			app.image(bp2, (width/2)-110, 700);
		}
		
		//time
		app.fill(255);
		app.textSize(40);
		app.text("TIME:", 755, 420);
		if(minutes<10 && seconds<10) {
			app.text("0"+minutes+":0"+seconds, 755, 470);
		}else if(minutes<10 && seconds>10){
			app.text("0"+minutes+":"+seconds, 755, 470);
		}else {
			app.text(minutes+":"+seconds, 755, 470);
		}
		//points
		app.text("SCORE:", 735, 580);
		app.text(points, 785, 630);
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void setBtnP(boolean btnP) {
		this.btnP = btnP;
	}
	
	
	
	
}
