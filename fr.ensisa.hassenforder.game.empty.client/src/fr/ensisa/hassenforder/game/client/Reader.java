package fr.ensisa.hassenforder.game.client;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import fr.ensisa.hassenforder.game.model.Category;
import fr.ensisa.hassenforder.game.model.Product;
import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.FileHelper;
import fr.ensisa.hassenforder.network.Protocol;

public class Reader extends BasicAbstractReader {
	
	private String msg;
	private long id;
	private int err;
	private int cash;
	private String img;
	private byte[] imgs;
	private int discrCat;
	private Category cat;
	private String productName;
	private int duration;
	private boolean stackable;
	private int count;
	private int rTime;
	private int protocol;
	private int size;
	private Product prod;
	

	public Reader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {
		type = readInt ();
		switch (type) {
		case 0:
			System.out.println("msg error");
			break;
		case Protocol.OK: 
			readOK();
			break;
		case Protocol.KO:
			readKO();
			break;
		case Protocol.CONNECT:
			readConnected();
			break;
		case Protocol.STAT :
			readStat();
			break;
		case Protocol.PROD:
			readProds();
			break;
		case Protocol.SHOP:
			readShop();
			break;
		case Protocol.ADD:
			readAdd();
			break;
		case Protocol.CLEAR:
			readClear();
			break;
		case Protocol.BUY:
			readBuy();
			break;
		case Protocol.SELL:
			readSell();
			break;
	}

	
	}
	private void readSell() {
		this.protocol=readOK();
		this.cash=readCash();
		
	}

	private void readBuy() {
		this.protocol=readOK();
		this.cash=readCash();
		
	}

	private void readClear() {
		this.protocol=readOK();
	}

	private void readAdd() {
		this.protocol=readOK();
		this.cash=readCash();
		
	}

	private void readStat() {
		this.protocol=readOK();
		this.img=readImg();
		this.cash=readCash();
	
		
	}

	private void readShop() {
		this.protocol=readOK();
		this.size=readInt();
		readCategory();
		this.productName=readString();
		this.duration=readInt();
		this.stackable=readBoolean();
		this.count=readInt();
		//this.imgs=readImg();
		
	}
	

	private void readProds() {
		this.protocol=readOK();
		this.size=readInt();
		readCategory();
		this.productName=readString();
		this.rTime=readInt();
		this.stackable=readBoolean();
		this.count=readInt();
		//this.imgs=readImg();
		
	}

	private void readCategory() {
		this.discrCat= readInt();
		switch (discrCat){
			case 0:
				System.out.println("msg error");
				break;
			case 1:
				this.cat= Category.WEAPON;
				break;
			case 2:
				this.cat = Category.AMMO;
				break;
			case 3:	
				this.cat = Category.FOOD;
				break;
		}
	}

	private String readImg() {
		byte[] imgB = FileHelper.readContent(img);
		return img;
		
	}
		
		public void readConnected() {
			this.protocol=readOK();
			this.id=readLong();
			
		}

		public int readOK() {
			return readInt();
		}

		public int readKO() {
			return readInt();
		}

		public void readError() {
			readKO();
			this.msg=readString();
		}
		
		public int readCash(){
			return readInt();
		}
		
		public int getCash(){
			return this.cash;
		}
		
		public long getId(){
			return this.id;
		}
		
		public String getMsg(){
			return this.msg;
		}
		
		public int getErr(){
			return this.err;
		}
		
		public int getProtocol(){
			return this.protocol;
		}
		
		public String getImage(){
			return null;
		}

		public Product getProduct() {
			return new Product(cat, productName, img, rTime, stackable, count );
			
		}
		public int getSize(){
			return this.size;
		}
	
}
