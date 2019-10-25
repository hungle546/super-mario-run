package com.supermariorun.soundeffects;
import acm.graphics.GLabel;

public interface Playable {
	public void play(AudioPlayer player, GLabel statusLabel);
	public String getName();
}