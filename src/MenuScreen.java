import processing.core.PApplet;
import processing.core.PImage;

public class MenuScreen {

	private int width, height;
	private int pyr1, pyr2, pyr3, pyr4, pyr5, pxt;
	private boolean dir1, btnPlay, btnIns;
	int frameC;
	PImage tot;
	PImage bg;
	PImage ti;
	PImage r1;
	PImage r2;
	PImage r3;
	PImage r4;
	PImage r5;
	
	PImage bp1;
	PImage bp2;
	
	PImage bi1;
	PImage bi2;
	
	public MenuScreen(PApplet app, int width, int height) {
		
		this.width = width;
		this.height = height;
		
		dir1 = true;
		btnPlay = false;
		btnIns = false;
		frameC = 0;
		
		pyr1 = 460;
		pyr2 = 600;
		pyr3 = 270;
		pyr4 = 150;
		pyr5 = 100;
		pxt = 230;
		
		tot = app.loadImage("img/interfaces/alct.png");
		bg = app.loadImage("img/interfaces/homeSSP.png");
		ti = app.loadImage("img/interfaces/anle.png");
		r1 = app.loadImage("img/interfaces/r1.png");
		r2 = app.loadImage("img/interfaces/r2.png");
		r3 = app.loadImage("img/interfaces/r3.png");
		r4 = app.loadImage("img/interfaces/r4.png");
		r5 = app.loadImage("img/interfaces/r5.png");
		bp1 = app.loadImage("img/interfaces/bp1.png");
		bp2 = app.loadImage("img/interfaces/bp2.png");
		bi1 = app.loadImage("img/interfaces/bi1.png");
		bi2 = app.loadImage("img/interfaces/bi2.png");
	}
	
	public void loadScreen(PApplet app) {
		frameC++;
		//app.image(tot,0,0);
		app.image(bg,0,0);
		app.image(ti,pxt,0);

		app.image(r1,1380,pyr1);
		app.image(r2,1244,pyr2);
		app.image(r3,1150,pyr3);
		app.image(r4,1235,pyr4);
		app.image(r5,1340,pyr5);
		
		if(!btnIns) {
			app.image(bi1, (width/2)-195, (height/2)+10);	
		}else {
			app.image(bi2, (width/2)-195, (height/2)+10);	
		}
		
		if(!btnPlay) {
			app.image(bp1, (width/2)-120, (height/2)+130);	
		}else {
			app.image(bp2, (width/2)-120, (height/2)+130);	
		}
		
		
		if(pyr1>470) {
			dir1 = false;
		}else if(pyr1<450){
			dir1=true;
		}
		
		if(dir1 && frameC % 5 == 0){
			pyr1++;
			pyr2+=2;
			pyr4++;
			pyr5-=2;
			pyr3--;
		}else if(!dir1 && frameC % 5 == 0){
			pyr1--;
			pyr2-=2;
			pyr4--;
			pyr5+=2;
			pyr3++;
		}
		
		if(frameC==5) {
			frameC=0;
		}

	}//end loadScreen
	
	public void setBtnPlay(boolean btnPlay) {
		this.btnPlay = btnPlay;
	}

	public void setBtnIns(boolean btnIns) {
		this.btnIns = btnIns;
	}
	
	
}
