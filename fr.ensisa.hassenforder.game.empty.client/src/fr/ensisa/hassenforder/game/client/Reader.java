package fr.ensisa.hassenforder.game.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;

public class Reader extends BasicAbstractReader {

	public Reader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {
		type = readInt ();
		switch (type) {
		case 0:
			break;
		case 1: 
			readBoolean();
			break;
		case 2:
			readLong();
			break;
		case 3:
			readShort();
			break;
		case 4:
			readString();
			break;
		case 5:
			readTab();
			break;
		case 6:
			readImg();
			break;
		
	}

	
	}

	private ArrayList readTab() {
		return null;
		
		
	}

	private String readImg() {
		try {
			return inputStream.readUTF();
		} catch (IOException e) {
			return "";
		}
		
	}
}
