package com.mycompany.a1;

import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.a1.Commands.*;
import com.mycompany.a1.Objects.GameCollection;
import com.mycompany.a1.Views.MapView;
import com.mycompany.a1.Views.PointsView;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import java.util.Vector;
import com.codename1.ui.util.UITimer;

public class Game extends Form implements Runnable {
	private GameWorld gw;
	private MapView mv;
	private PointsView pv;
	private Toolbar myToolbar;
	private Container bottomContainer;
	private Container rightContainer;
	private UITimer timer;
	private Button btnInc, btnDec, btnAddShip, btnAddStation, btnAddAsteroid, 
					btnTurnRight, btnTurnLeft, btnFire, btnJump, btnPause, btnReload; 
	private IncreaseCommand incCmd;
	private DecreaseCommand decCmd;
	private TurnLeftCommand turnLeftCmd;
	private TurnRightCommand turnRightCmd;
	private AddAsteroidCommand addAsteroidCmd;
	private AddShipCommand addShipCmd;
	private AddStationCommand addStationCmd;
	private FireCommand fireCmd;
	private JumpCommand jumpCmd;
	private AboutCommand aboutCmd;
	private SoundCommand soundCmd;
	private HelpCommand helpCmd;
	private PauseCommand pauseCmd;
	private ExitCommand exitCmd;
	
	public Game() {
		
		
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBgColor(ColorUtil.BLACK);
		
		timer = new UITimer(this);
		timer.schedule(50, true, this);
		
			
		gw = new GameWorld();
		mv = new MapView(gw, this.getComponentForm());
		pv = new PointsView();
		gw.addObserver(mv);
		gw.addObserver(pv);
		this.setGameLayout();
		this.show();
		gw.setHeight(mv.getHeight());
		gw.setWidth(mv.getWidth());
		gw.observerUpdate();
		gw.init();
	
	}
	
	public void setGameLayout() {
		myToolbar = new Toolbar();
		this.setToolbar(myToolbar);
		
		myToolbar.setTitle("Asteroid");
		
		this.setLayout(new BorderLayout());
		
		this.add(BorderLayout.CENTER, mv);
		this.add(BorderLayout.NORTH, pv);
		
		rightContainer = new Container();
		bottomContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
		/////////////////////////////////////
		
		// ADD COMMANDS ///////////////////////
		
		incCmd = new IncreaseCommand(gw);
		decCmd = new DecreaseCommand(gw);
		turnLeftCmd = new TurnLeftCommand(gw);
		turnRightCmd = new TurnRightCommand(gw);
		addAsteroidCmd = new AddAsteroidCommand(gw);
		addShipCmd = new AddShipCommand(gw);
		addStationCmd = new AddStationCommand(gw);
		fireCmd = new FireCommand(gw);
		jumpCmd = new JumpCommand(gw);
		aboutCmd = new AboutCommand(gw);
		soundCmd = new SoundCommand(gw);
		helpCmd = new HelpCommand(gw);
		exitCmd = new ExitCommand(gw);
		pauseCmd = new PauseCommand(timer, gw, this);
		///////////////////////////////////////
		
		myToolbar.addCommandToRightBar(helpCmd);
		
		CheckBox soundCB = new CheckBox("Sound");
		soundCmd.putClientProperty("SideComponent", soundCB);
		soundCB.getAllStyles().setBgTransparency(255);
		soundCB.getAllStyles().setBgColor(ColorUtil.WHITE);
		Toolbar.setOnTopSideMenu(false);
	
		myToolbar.addCommandToSideMenu(aboutCmd);
		myToolbar.addCommandToSideMenu(helpCmd);
		myToolbar.addCommandToSideMenu(soundCmd);
		
		soundCB.addActionListener(soundCmd);
		Label checkStatus = new Label("Checkbox status: ");
		checkStatus.getAllStyles().setPadding(LEFT, 5);
		checkStatus.getAllStyles().setPadding(RIGHT, 5);
		
		myToolbar.addCommandToSideMenu(exitCmd);
		
		btnInc = new Button("Increase speed");
		btnInc.setCommand(incCmd);
		btnDec = new Button("Decrease speed");
		btnDec.setCommand(decCmd);
		btnAddShip = new Button("Add ship");
		btnAddShip.setCommand(addShipCmd);
		btnAddStation = new Button("Add Space Station");
		btnAddStation.setCommand(addStationCmd);
		btnAddAsteroid = new Button("Add Asteroid");
		btnAddAsteroid.setCommand(addAsteroidCmd);
		btnTurnRight = new Button("Turn Right");
		btnTurnRight.setCommand(turnRightCmd);
		btnTurnLeft = new Button("Turn Left");
		btnTurnLeft.setCommand(turnLeftCmd);
		btnFire = new Button("Fire!");
		btnFire.setCommand(fireCmd);
		btnJump = new Button("Jump");
		btnJump.setCommand(jumpCmd);
		btnPause = new Button("Pause");
		btnPause.setCommand(pauseCmd);

		
		addKeyListener('a', addAsteroidCmd);
		addKeyListener('b', addStationCmd);
		addKeyListener('s', addShipCmd);
		addKeyListener('i', incCmd);
		addKeyListener('d', decCmd);
		addKeyListener('l', turnLeftCmd);
		addKeyListener('r', turnRightCmd);
		addKeyListener('j', jumpCmd);
		addKeyListener('q', exitCmd);
		addKeyListener('f', fireCmd);
		addKeyListener('p', pauseCmd);
		
		btnTurnRight.getUnselectedStyle().setBgTransparency(255);
		btnTurnRight.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		btnTurnRight.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		btnInc.getUnselectedStyle().setBgTransparency(255);
		btnInc.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		btnInc.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		btnTurnLeft.getUnselectedStyle().setBgTransparency(255);
		btnTurnLeft.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		btnTurnLeft.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		btnDec.getUnselectedStyle().setBgTransparency(255);
		btnDec.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		btnDec.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		btnFire.getUnselectedStyle().setBgTransparency(255);
		btnFire.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		btnFire.getUnselectedStyle().setBgColor(ColorUtil.WHITE);
		btnJump.getUnselectedStyle().setBgTransparency(255);
		btnJump.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		btnJump.getUnselectedStyle().setBgColor(ColorUtil.WHITE);
		btnPause.getUnselectedStyle().setBgTransparency(255);
		btnPause.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		btnPause.getUnselectedStyle().setBgColor(ColorUtil.WHITE);
		
		btnTurnRight.getSelectedStyle().setBgTransparency(255);
		btnTurnRight.getSelectedStyle().setBgColor(ColorUtil.BLUE);
		btnTurnRight.getSelectedStyle().setFgColor(ColorUtil.WHITE);
		btnInc.getSelectedStyle().setBgTransparency(255);
		btnInc.getSelectedStyle().setBgColor(ColorUtil.BLUE);
		btnInc.getSelectedStyle().setFgColor(ColorUtil.WHITE);
		btnTurnLeft.getSelectedStyle().setBgTransparency(255);
		btnTurnLeft.getSelectedStyle().setBgColor(ColorUtil.BLUE);
		btnTurnLeft.getSelectedStyle().setFgColor(ColorUtil.WHITE);
		btnDec.getSelectedStyle().setBgTransparency(255);
		btnDec.getSelectedStyle().setBgColor(ColorUtil.BLUE);
		btnDec.getSelectedStyle().setFgColor(ColorUtil.WHITE);
		btnFire.getSelectedStyle().setBgTransparency(255);
		btnFire.getSelectedStyle().setBgColor(ColorUtil.BLUE);
		btnFire.getSelectedStyle().setBgColor(ColorUtil.WHITE);
		btnJump.getUnselectedStyle().setBgTransparency(255);
		btnJump.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		btnJump.getUnselectedStyle().setBgColor(ColorUtil.WHITE);
		btnPause.getSelectedStyle().setBgTransparency(255);
		btnPause.getSelectedStyle().setBgColor(ColorUtil.BLUE);
		btnPause.getSelectedStyle().setBgColor(ColorUtil.WHITE);
		
		rightContainer.add(btnTurnRight);
		rightContainer.add(btnInc);
		rightContainer.add(btnTurnLeft);
		rightContainer.add(btnDec);
		rightContainer.add(btnFire);
		rightContainer.add(btnJump);
		rightContainer.add(btnPause);
		rightContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		add(BorderLayout.WEST, rightContainer);
		
		btnAddShip.getUnselectedStyle().setBgTransparency(255);
		btnAddShip.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		btnAddShip.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		btnAddStation.getUnselectedStyle().setBgTransparency(255);
		btnAddStation.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		btnAddStation.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		btnAddAsteroid.getUnselectedStyle().setBgTransparency(255);
		btnAddAsteroid.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		btnAddAsteroid.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		bottomContainer.add(btnAddShip);
		bottomContainer.add(btnAddStation);
		bottomContainer.add(btnAddAsteroid);
		bottomContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		bottomContainer.getAllStyles().setAlignment(CENTER);
		add(BorderLayout.SOUTH, bottomContainer);
		
	}

	public void run() {
		gw.tick();
		
	}

	public void startTime(UITimer currentTime) {
		currentTime.schedule(50, true, this);
		
	}

	public void notPaused() {
		btnDec.setEnabled(true);
		btnInc.setEnabled(true);
		btnAddShip.setEnabled(true);
		btnAddStation.setEnabled(true);
		btnAddAsteroid.setEnabled(true);
		btnTurnRight.setEnabled(true);
		btnTurnLeft.setEnabled(true);
		btnFire.setEnabled(true);
		btnJump.setEnabled(true);
		btnPause.setEnabled(true);
		btnPause.setText("Pause");
		addKeyListener('a', addAsteroidCmd);
		addKeyListener('b', addStationCmd);
		addKeyListener('s', addShipCmd);
		addKeyListener('i', incCmd);
		addKeyListener('d', decCmd);
		addKeyListener('l', turnLeftCmd);
		addKeyListener('r', turnRightCmd);
		addKeyListener('j', jumpCmd);
		addKeyListener('q', exitCmd);
		addKeyListener('f', fireCmd);
		addKeyListener('p', pauseCmd);
		
	}
	
	public void isPaused() {
		
		btnDec.setEnabled(false);
		btnInc.setEnabled(false);
		btnTurnRight.setEnabled(false);
		btnTurnLeft.setEnabled(false);
		btnFire.setEnabled(false);
		btnJump.setEnabled(false);
		btnPause.setEnabled(true);
		bottomContainer.setEnabled(false);
		btnPause.setText("Play");
		removeKeyListener('a', addAsteroidCmd);
		removeKeyListener('b', addStationCmd);
		removeKeyListener('s', addShipCmd);
		removeKeyListener('i', incCmd);
		removeKeyListener('d', decCmd);
		removeKeyListener('l', turnLeftCmd);
		removeKeyListener('r', turnRightCmd);
		removeKeyListener('j', jumpCmd);
		removeKeyListener('q', exitCmd);
		removeKeyListener('f', fireCmd);
		removeKeyListener('p', pauseCmd);
	}

	public void stopTime(UITimer currentTime) {
		currentTime.cancel();
		
	}

	
	


}
