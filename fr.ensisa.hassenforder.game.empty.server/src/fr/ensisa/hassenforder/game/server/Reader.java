package fr.ensisa.hassenforder.game.server;


import java.io.InputStream;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;

/**
 * Reads the InputStream from network
 * @author joris
 * extends @class BasicABstractReader for methods for reading basic types
 * implements methods to read more complex messages such as Arrays or specific messages for the protocol
 */
public class Reader extends BasicAbstractReader {

	private String name;
	private String pwd;
	private long id;
	private long idDisc;
	private long idTest;
	
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

	private void readDisconnect() 
	{
		this.name = readString();
		this.idDisc = readLong();
	}

	private void readConnect() 
	{
		this.name = readString();
		this.pwd = readString();		
	}

	private void readConsume() 
	{
		
	}

	private void readClear() 
	{
		
	}

	private void readSub() 
	{
		
	}

	private void readAdd() 
	{
		
	}

	private void readShop() 
	{
		readTab(Protocol.SHOP);
	}

	private void readStat() 
	{
		this.name = readString();
		this.idTest = readInt();
	}

	private void readProd() 
	{
		readTab(Protocol.PROD);
	}

	private void readImage() 
	{
	
	}

	private void readTab(int discr) {
		int size;
		switch (discr) {
			case Protocol.SHOP : 
				break;
			case Protocol.PROD :
				size = readInt();
				for(int i = 0; i < size; i++)
				{
					
				}
				break;
			default : 
				size = readInt();
				for(int i = 0; i < size; i++)
				{
					
				}
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

	public long getIdDisc() 
	{
		return this.idDisc;
	}

	public long getIdTest() {
		// TODO Auto-generated method stub
		return this.idTest;
	}
}