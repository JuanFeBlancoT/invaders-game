import processing.core.PApplet;
import processing.core.PFont;

public class Main extends PApplet {
	public static void main (String[] args) {
		PApplet.main("Main");
	}
	
	//Attributes
	private int width = 1600;
	private int height = 900;
	private int screen;
	private boolean up, down, dash, shield, shoot, opShoot, shockWave;
	private PFont font;
	//relations
	private PlayScreen playScreen;
	private MenuScreen menuScreen;
	private EndScreen endScreen;
	private InstrucScreen instructionScreen;
	private MusicSounds musicB;
	
	
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
		opShoot = false;
		shockWave = false;
		font = createFont("data/COPRGTB.TTF", 35);
		//relations
		playScreen = new PlayScreen(this, width, height);
		menuScreen = new MenuScreen(this, width, height);
		endScreen = new EndScreen(this, width);
		instructionScreen = new InstrucScreen(this, width);
		musicB = new MusicSounds();
		
	}
	
	public void draw() {
		textFont(font);
		if(screen==1) {
			menuScreen.loadScreen(this);
		}else if(screen == 2) {
			instructionScreen.loadScreen(this);
		}else if(screen==3) {			
			if(playScreen.getPlayer().getLifes()>0) {
				playScreen.screenEvents(this);
				keyEvents();
				if(!up && !down) {
					playScreen.getPlayer().setStage(0);
				}
			}else {
				int minutes = playScreen.getMinutes();
				int seconds = playScreen.getSeconds();
				int score = playScreen.getPoints();
				
				endScreen.setSeconds(seconds);
				endScreen.setMinutes(minutes);
				endScreen.setPoints(score);
				musicB.playMusic("data/deathP.wav");
				screen = 4;
				
			}
		}else {
			endScreen.loadScreen(this);
		}
	}
	
	public void keyPressed() {
				
		switch(key) {
			case 'w':
				up = true;
				down = false;
				break;
			case 'd':
				down = true;
				up = false;
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
			case 'o':
				opShoot = true;
				break;
			case 'p':
				shockWave = true;
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
		case 'o':
			opShoot = false;
			break;
		case 'p':
			shockWave = false;
			break;
		}
	}
	
	public void keyEvents() {
		
		if(screen == 3) {
			if(up) {
				playScreen.getPlayer().moveShip(height, false,this);
				playScreen.getPlayer().setDir(false);
				playScreen.getPlayer().setStage(2);
			}
			if(down) {
				playScreen.getPlayer().moveShip(height, true,this);
				playScreen.getPlayer().setDir(true);
				playScreen.getPlayer().setStage(1);
			}
			if(dash && playScreen.getPlayer().getDashColdDown()==0) {
				playScreen.getPlayer().shipDash(height);
				musicB.playMusic("data/dash3.wav");
			}
			if(shield && playScreen.getPlayer().getShieldColdDown()==0) {
				playScreen.getPlayer().shield();
				musicB.playMusic("data/shieldS.wav");
				shield = false;
			}
			if(shoot && playScreen.getPlayer().getShootColdDown()==0) {
				playScreen.getPlayer().generateBullet(this);
				musicB.playMusic("data/shotB.wav");
				shoot = false;
			}
			if(opShoot && playScreen.getPlayer().getOpBulletColdDown()==0) {
				playScreen.getPlayer().generateOpBullet(this);
				musicB.playMusic("data/explo.wav");
			}
			if(shockWave) {
				playScreen.lateralShockWave(this);
			}
		}
	}
	
	public void mouseMoved() {
		if(screen ==1 && mouseX>(width/2)-185 && mouseX<(width/2)+150 && mouseY>(height/2)+20 && mouseY<(height/2)+105) {
			menuScreen.setBtnIns(true);
		}else {
			menuScreen.setBtnIns(false);
		}
		
		if(screen ==1 && mouseX>(width/2)-120 && mouseX<(width/2)+85 && mouseY>(height/2)+150 && mouseY<(height/2)+215) {
			menuScreen.setBtnPlay(true);
		}else {
			menuScreen.setBtnPlay(false);
		}
		
		if(screen==2 && mouseX>1120 && mouseX<1310 && mouseY>800 && mouseY<870) {
			instructionScreen.setBtnB(true);
		}else {
			instructionScreen.setBtnB(false);
		}
		
		if(screen==2 && mouseX>width-250 && mouseX<width-50 && mouseY>800 && mouseY<870) {
			instructionScreen.setBtnP(true);
		}else {
			instructionScreen.setBtnP(false);
		}
		
		if(screen==4 && mouseX>715 && mouseX<895 && mouseY>740 && mouseY<775) {
			endScreen.setBtnP(true);
		}else {
			endScreen.setBtnP(false);
		}
		
	}
	
	public void mouseClicked() {
		if(screen==1 && mouseX>(width/2)-90 && mouseX<(width/2)+90 && mouseY>(height/2)+30 && mouseY<(height/2)+90) {
			screen = 2;
		}
		
		if(screen==1 && mouseX>(width/2)-90 && mouseX<(width/2)+90 && mouseY>(height/2)+170 && mouseY<(height/2)+230) {
			screen = 3;
		}
		
		if(screen==2 && mouseX>1120 && mouseX<1310 && mouseY>800 && mouseY<870) {
			screen=1;
		}
		
		if(screen==2 && mouseX>width-250 && mouseX<width-50 && mouseY>800 && mouseY<870) {
			screen = 3;
		}
		
		if(screen==4 && mouseX>715 && mouseX<895 && mouseY>740 && mouseY<775) {
			resetPlayScreen();
			screen = 3;
			System.out.println("RESET");
		}
	
	}
	
	public void resetPlayScreen() {
		up = false;
		down = false;
		dash = false;
		shield = false;
		shoot = false;
		opShoot = false;
		shockWave = false;
		//relations
		playScreen = new PlayScreen(this, width, height);
	}
} 
