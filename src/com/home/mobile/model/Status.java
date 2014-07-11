package com.home.mobile.model;

import java.io.Serializable;

public class Status implements Serializable {

	private static final long serialVersionUID = 2307122253674817342L;
	
	private Boolean frontDoor;
	private Boolean frontWindow;
	private Boolean roomDoor;
	private Boolean roomWindow;
	
	public boolean getFrontDoor() {
		return this.frontDoor;
	}
	
	public void setFrontDoor(boolean frontDoor) {
		this.frontDoor = frontDoor;
	}
	
	public boolean getFrontWindow() {
		return this.frontWindow;
	}
	
	public void setFrontWindow(boolean frontWindow) {
		this.frontWindow = frontWindow;
	}
	
	public boolean getRoomDoor() {
		return this.roomDoor;
	}
	
	public void setRoomDoor(boolean roomDoor) {
		this.roomDoor = roomDoor;
	}
	
	public boolean getRoomWindow() {
		return this.roomWindow;
	}
	
	public void setRoomWindow(boolean roomWindow) {
		this.roomWindow = roomWindow;
	}
	
	public boolean isEmpty() {
		if ((this.frontDoor == null) || (this.frontWindow == null)
				|| (this.roomDoor == null) || (this.roomWindow == null)) {
			return true;
		} else {
			return false;
		}
	}
}