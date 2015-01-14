package fr.ensisa.hassenforder.game.server;


import java.io.InputStream;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;

public class Reader extends BasicAbstractReader {

	private String name;
	private String pwd;
	private long id;
	
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
			System.out.println("msg error");
			break;
		case Protocol.STAT :
			readStat();
			break;
		case Protocol.PROD :
			readProd();
			break;
		case Protocol.SHOP :
			readShop();
			break;
		case Protocol.ADD :
			readAdd();
			break;
		case Protocol.SUB : 
			readSub();
			break;
		case Protocol.CLEAR :
			readClear();
			break;
		case Protocol.CONSUME :
			readConsume();
			break;
		case Protocol.CONNECT : 
			readConnect();
			break;
		case Protocol.DISCONNECT :
			readDisconnect();
			break;
		}
	}

	private void readDisconnect() {
		// TODO Auto-generated method stub
		this.name = readString();
		this.id = readLong();
	}

	private void readConnect() {
		// TODO Auto-generated method stub
		this.name = readString();
		this.pwd = readString();		
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
		readTab(Protocol.SHOP);
	}

	private void readStat() {
		// TODO Auto-generated method stub
		
	}

	private void readProd() {
		// TODO Auto-generated method stub
		readTab(Protocol.PROD);
	}

	private void readImage() {
		// TODO Auto-generated method stub
		
	}

	private void readTab(int discr) {
		// TODO Auto-generated method stub
		switch (discr) {
			case Protocol.SHOP : 
				
				break;
			case Protocol.PROD :
				
				break;
			default : 
				
				break;
		}
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getPwd()
	{
		return this.pwd;
	}
	
	public long getId()
	{
		return this.id;
	}
}
