package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.MouseEvent;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GButton;

public class MenuPane extends GraphicsPane {
	private mainSMR program; 
	public static final String IMG_FOLDER = "menuScreen/";
	private GImage menuBackground;
	private GImage menuMario;
	private GImage shopPipe;
	private GImage tourPipe;
	private GImage instrucPipe;
	private GImage menuLogo;
	
	private GImage ShopBubble;
	private GImage TourBubble;
	private GImage InstructBubble;

	public MenuPane(mainSMR mainSMR) {
		super();
		program = mainSMR;
		shopPipe = new GImage(IMG_FOLDER + "pipe.png",mainSMR.getWidth()/3, mainSMR.getHeight()/1.3);
		shopPipe.setSize(mainSMR.getWidth()/7, mainSMR.getHeight()/3);
		ShopBubble = new GImage(IMG_FOLDER + "shopBubble.png",mainSMR.getWidth()/2.9, mainSMR.getHeight()/1.625);
		tourPipe = new GImage(IMG_FOLDER + "pipe.png",mainSMR.getWidth()/6.57, mainSMR.getHeight()/1.3);
		tourPipe.setSize(mainSMR.getWidth()/7, mainSMR.getHeight()/3);
		TourBubble = new GImage(IMG_FOLDER + "TourBubble.png",mainSMR.getWidth()/6.388, mainSMR.getHeight()/1.625);
		instrucPipe = new GImage(IMG_FOLDER + "pipe.png",mainSMR.getWidth()/2,mainSMR.getHeight()/1.3);
		instrucPipe.setSize(mainSMR.getWidth()/7, mainSMR.getHeight()/3);
		InstructBubble = new GImage(IMG_FOLDER + "instrucBubble.png",mainSMR.getWidth()/1.98, mainSMR.getHeight()/1.625);
		menuBackground = new GImage(IMG_FOLDER + "menuBack.jpg", 0, 0);
		menuBackground.setSize(mainSMR.getWidth(), mainSMR.getHeight());
		menuMario = new GImage(IMG_FOLDER + "menuMario.png",mainSMR.getWidth()/1.8, mainSMR.getHeight()/50);
		menuLogo =  new GImage(IMG_FOLDER + "menuLogo.png",mainSMR.getWidth()/8, mainSMR.getHeight()/50);
	}

	@Override
	public void showContents() {
		program.add(menuBackground);
		program.add(shopPipe);
		program.add(ShopBubble);
		program.add(tourPipe);
		program.add(instrucPipe);
		program.add(TourBubble);
		program.add(InstructBubble);
		program.add(menuMario);
		program.add(menuLogo);
	}

	@Override
	public void hideContents() {
		program.remove(ShopBubble);
		program.remove(TourBubble);
		program.remove(menuBackground);
		program.remove(menuMario);
		program.remove(tourPipe);
		program.remove(shopPipe);
		program.remove(instrucPipe);
		program.remove(InstructBubble);
		program.remove(menuLogo);
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == ShopBubble ) {
			program.switchToShop();
		}
		else if(obj == InstructBubble)
		{
			program.switchToInstructions();
		}
		else if(obj == TourBubble){
			program.switchToTour();
		}
		
	}
}
