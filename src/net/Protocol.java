package net;

import java.io.Serializable;

import om.Life;

public class Protocol implements Serializable {
	public static final int ADD_LIFE = 1; //��������� Life � �����������

	public static final int DELETE_LIFE = 2; //������� Life

	public static final int HUNT = 3; // Predator ���������
	
	public static final int EAT = 4; // Life ����

	public static final int LOAD_WORLD = 5; // ��������� ��� message = path;

	public static final int SAVE_WORLD = 6; // ��������� ��� message = path;
	
	
	int command;
	String message;
	Life data;
	
	
	public Life getData() {
		return data;
	}
	
	public String getMessage() {
		return message;
	}
	
	public int getCommand() {
		return command;
	}
	
	public Protocol(int command, String message, Life data) {
		this.message = message;
		this.command = command;
		this.data = data;
	}
	
}
