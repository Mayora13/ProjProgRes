package fr.ensisa.hassenforder.game.server;


import java.io.InputStream;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;

public class Reader extends BasicAbstractReader {

	public Reader(InputStream inputStream) {
		super (inputStream);
	}

	/**
	 * @author joris
	 * select which type of data we read
	 * 0 is error
	 */
	public void receive() {
		type = readInt ();
		switch (type) {
		case 0 :
			break;
		case 10 :
			readStat();
			break;
		case 20 :
			readProd();
			break;
		case 30 :
			readShop();
			break;
		case 40 :
			readAdd();
			break;
		case 50 : 
			readSub();
			break;
		case 60 :
			readClear();
			break;
		case 70 :
			readConsume();
			break;
		}
	}

	private void readConsume() {
		// TODO Auto-generated method stub
		
	}

	private void readClear() {
		// TODO Auto-generated method stub
		
	}

	private void readSub() {
		// TODO Auto-generated method stub
		
	}

	private void readAdd() {
		// TODO Auto-generated method stub
		
	}

	private void readShop() {
		// TODO Auto-generated method stub
		
	}

	private void readProd() {
		// TODO Auto-generated method stub
		
	}

	private void readStat() {
		// TODO Auto-generated method stub
		
	}

	private void readProduct() {
		// TODO Auto-generated method stub
		
	}

	private void readImage() {
		// TODO Auto-generated method stub
		
	}

	private void readTab() {
		// TODO Auto-generated method stub
		
	}
	
}
