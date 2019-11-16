package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import com.supermariorun.characters.Character;
import com.supermariorun.levels.ILevel;
import com.supermariorun.levels.LevelOne;
import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import starter.GButton;

public class LevelPane extends GraphicsPane implements ActionListener{
	private mainSMR program;
	private GImage Background;
	private GImage pauseButton;
	private GImage pauseBubble;
	private GImage retryButton;
	private GImage resumeButton;
	private GImage pausePane;
	private GImage quitButton;
	private GImage greyBack;
	private GImage levelClear;
	private GImage continueButton;
	
	private ArrayList <GImage> Environment;
	private ArrayList <GImage> GrassStrips;
	private ArrayList <GImage> Blocks;
	private ArrayList <GImage> qBlocks;
	
	public boolean jumpState;
	private Character Mario;
	private ILevel level;
	private Timer timer;
	
	private int spaceWidth = 1150/30;
	private int spaceHeight = 650/18;
	public static final int MS = 70;
	public static final String IMG_FOLDER = "LevelPane/";

	public LevelPane(mainSMR mainSMR, int levelNum) {
		super();
		this.program = mainSMR;
		
		final double mainWidth = program.getWidth();
		final double mainHeight = program.getHeight();
		
		program = mainSMR;
		timer = new Timer (MS, this);
		level = new LevelOne();
		Environment = level.getEnvironment();
		Mario = new Character(mainSMR, this);
		
		pauseBubble = new GImage(IMG_FOLDER + "bubble.png",30, 10);
		pauseBubble.setSize(100, 100);
		
		pauseButton = new GImage(IMG_FOLDER + "pause.png", 55, 27);
		pauseButton.setSize(50,70);
		
		pausePane = new GImage(IMG_FOLDER + "pausePane.png", 400, 100);
		pausePane.setSize(300, 400);
		
		quitButton = new GImage(IMG_FOLDER + "quitButton.png", 428, 437);
		quitButton.setSize(250,50);
		
		greyBack = new GImage(IMG_FOLDER + "pauseBack.png", 0, 0);
		greyBack.setSize(mainWidth, mainHeight);
		
		resumeButton = new GImage(IMG_FOLDER + "continueButton.png", 450, 500);
		resumeButton.setSize(190,100);
		
		retryButton = new GImage(IMG_FOLDER + "retryButton.png", 415, 387);
		retryButton.setSize(280, 50);
		
		levelClear = new GImage(IMG_FOLDER + "courseClear.png", 380, 260);
		levelClear.setSize(400, 150);
		
		continueButton = new GImage(IMG_FOLDER + "continueButton.png", 460, 425);
		continueButton.setSize(200, 120);

		DrawLevel();
	}
	
	public void DrawLevel() {
		Background = level.getBackground();
	}
	
	public void Play() {
		timer.start();
		Mario.run();
		program.playLvlOneTrack();
	}
	
	public void Pause() {
		timer.stop();
		Mario.stand();
		program.playPauseSound();
		program.pauseLvlOneTrack();
	}
	
	public void Restart() {
		program.stopLvlOneTrack();
		program.switchToLevel(1);
	}
	
	public void moveEnvironment() {
		Background.move(-10, 0);
		for (GImage move : Environment) {
			move.move(-10, 0);
		}
	}
	
	public void isGameOver() {
		if (Background.getX() == -4840) { //4840
			timer.stop();
			Mario.stand();
			program.add(greyBack);
			program.add(levelClear);
			program.add(continueButton);
			program.stopLvlOneTrack();
			program.playCourseClearedTrack();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		moveEnvironment();
		isGameOver();
	
		if (jumpState == true) {
			Mario.jump();
		}
	}

	@Override
	public void showContents() {
		Play();
		program.add(Background);
		program.add(Mario.getMario());
		program.add(pauseButton);
		program.add(pauseBubble);

		for (GImage e: Environment) {
			program.add(e);
		}
	}

	@Override
	public void hideContents() {
		program.remove(Background);
		program.remove(Mario.getMario());
		program.remove(pauseButton);
		program.remove(pauseBubble);
		program.remove(quitButton);
		
		for (GImage e: Environment) {
			program.remove(e);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		GObject obj = program.getElementAt(e.getX(), e.getY());
	    
		if(obj == pauseButton || obj == pauseBubble) {
			Pause();
			program.add(greyBack);
			program.add(pausePane);	
			program.add(quitButton);
			program.add(resumeButton);
			program.add(retryButton);
		}
		
		else if(obj == resumeButton) {
			Play();
			program.playResumeSound();
			program.remove(greyBack);
			program.remove(pausePane);
			program.remove(quitButton);
			program.remove(resumeButton);
			program.remove(retryButton);
		}
		
		else if(obj == retryButton){
			Restart();
			program.remove(greyBack);
			program.remove(pausePane);
			program.remove(quitButton);
			program.remove(resumeButton);
			program.remove(retryButton);
		}
		
		else if(obj == quitButton) {
			program.stopLvlOneTrack();
			program.playTourSound();
			program.switchToTour();
		}
		
		else if(obj == continueButton) {
			program.stopLvlOneTrack();
			program.playPipeSound();
			program.switchToEndPane();
		}
		
		else {
			if (!jumpState) {
				jumpState = true;
				Mario.setJumpCount(0);
				Mario.jump();
			}
		}
	}
	
	public ArrayList<GImage> getEnvironment() {
		return Environment;
	}
}