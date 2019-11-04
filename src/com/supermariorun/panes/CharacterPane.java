package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.MouseEvent;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GButton;

public class CharacterPane extends GraphicsPane {
	private mainSMR program;
	public static final String IMG_FOLDER = "menuScreen/";
	private GButton BackButton;
	private GImage BackPipe;
	private GButton LuigiButton;
	private GButton PrincessButton;
	
	public CharacterPane(mainSMR mainSMR) {
		super();
		program = mainSMR;
		BackButton = new GButton("Back", 100, 100, 80, 80);
		BackButton.setFillColor(Color.GREEN);
		BackPipe = new GImage(IMG_FOLDER + "gPipeR.png",mainSMR.getWidth()/55, mainSMR.getHeight()/6.7);
		BackPipe.setSize(mainSMR.getWidth()/14, mainSMR.getHeight()/8);
		PrincessButton = new GButton("Princess", 500, 500, 150, 100);
		PrincessButton.setFillColor(Color.RED);
		LuigiButton = new GButton("Luigi", 800, 500, 150, 100);
		LuigiButton.setFillColor(Color.GREEN);
	}
	@Override
	public void showContents() {
		program.add(BackButton);
		program.add(BackPipe);
		program.add(PrincessButton);
		program.add(LuigiButton);
	}

	@Override
	public void hideContents() {
		program.remove(BackButton);
		program.remove(BackPipe);
		program.remove(PrincessButton);
		program.remove(LuigiButton);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if (obj == BackButton) {

			program.switchToShop();
		}
	}
}
