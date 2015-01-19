package fr.ensisa.hassenforder.game.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;

public class Reader extends BasicAbstractReader {
	
	private String msg;
	private long id;
	private int err;

	public Reader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {
		type = readInt ();
		switch (type) {
		case 0:
			System.out.println("msg error");
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
			readImg();
			break;
		case 6:
			readTab();
			break;
		
	}

	
	}
	

	private void readTab() {
		// TODO Auto-generated method stub
		
	}

	private String readImg() {
		try {
			return inputStream.readUTF();
		} catch (IOException e) {
			return "";
		}
	}
		
		public void readConnected() {
			readOK();
			this.id=readLong();
		}

		public void readOK() {
			this.err=readInt();
		}

		public void readKO() {
			this.err=readInt();
		}

		public void readError() {
			readKO();
			this.msg=readString();
		}
		
	
}
