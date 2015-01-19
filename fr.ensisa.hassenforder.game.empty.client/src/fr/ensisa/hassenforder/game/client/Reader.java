package fr.ensisa.hassenforder.game.client;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import fr.ensisa.hassenforder.game.model.Category;
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
	private String productName;
	private int duration;
	private boolean isStackable;
	private int count;
	private long rTime;

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
			readCategory();
			break;
		case Protocol.STAT :
			readStat();
			break;
		case Protocol.PROD:
			readProd();
			break;
		case Protocol.SHOP:
			readShop();
			break;
	}

	
	}
	
	private void readStat() {
		readCash();
		readImg();
		
	}

	private void readShop() {
		readCategory();
		this.productName=readString();
		this.duration=readInt();
		this.isStackable=readBoolean();
		this.count=readInt();
		//this.imgs=readImg();
		
	}
	

	private void readProd() {
		readCategory();
		this.productName=readString();
		this.rTime=readLong();
		this.isStackable=readBoolean();
		this.count=readInt();
		//this.imgs=readImg();
		
	}

	private void readCategory() {
		this.discrCat= readInt();
	}

	private void readImg() {
		this.img=readString();
		byte [] imgB = null;
		FileHelper.writeContent(img, imgB);
		this.imgs=FileHelper.readContent(img);
		
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
		
		public void readCash(){
			this.cash=readInt();
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
		
	
}
