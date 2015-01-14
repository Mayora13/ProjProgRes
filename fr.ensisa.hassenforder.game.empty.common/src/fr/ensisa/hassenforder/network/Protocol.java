package fr.ensisa.hassenforder.network;

public interface Protocol {

	static final public int GAME_PORT_ID = 12322;
	
	static final public int STAT = 10;
	static final public int PROD = 20;
	static final public int SHOP = 30;
	static final public int ADD = 40;
	static final public int SUB = 50;
	static final public int CLEAR = 60;
	static final public int CONSUME = 70;
	static final public int CONNECT = 80;
	static final public int DISCONNECT = 90;
	
	public static final int KO = -2;
	public static final int OK = 1;

	public static final Object EXIT_TEXT = "exit";

	

}
