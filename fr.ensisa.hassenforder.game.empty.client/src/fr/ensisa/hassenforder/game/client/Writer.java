package fr.ensisa.hassenforder.game.client;

import java.io.IOException;
import java.io.OutputStream;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;

public class Writer extends BasicAbstractWriter {
	
	
	public Writer(OutputStream outputStream) {
		super (outputStream);
	}
	
	
	public void writeConnect(String name, String pwd){
		writeInt(Protocol.CONNECT);
		writeString(name);
		writeString(pwd);
	}
	
	public void writeDisconnect(String name, long idDisc){
		writeInt(Protocol.DISCONNECT);
		writeString(name);
		writeLong(idDisc);
	}
	
	public void writeConsume(String name, long id){
		writeInt(Protocol.CONSUME);
		writeString(name);
		writeLong(id);
	}
	
	public void writeClear(String name, long id) {
		writeInt(Protocol.CLEAR);
		writeString(name);
		writeLong(id);
	}

	public void writeSub(String name, long id) {
		writeInt(Protocol.SUB);	
		writeString(name);
		writeLong(id);
	}

	public void writeAdd(String name, long id) {
		writeInt(Protocol.ADD);
		writeString(name);
		writeLong(id);
	}

	public void writeShop(String name, long id) {
		writeTab(Protocol.SHOP);
		writeString(name);
		writeLong(id);
	}

	public void writeStat(String name, long id) {
		writeTab(Protocol.STAT);
		writeString(name);
		writeLong(id);
	}

	public void writeProd(String name, long id) {
		writeTab(Protocol.PROD);
		writeString(name);
		writeLong(id);
		
	}
	
	public void writeTab(int discr) {
		switch (discr) {
			case Protocol.SHOP : 
				
				break;
			case Protocol.PROD :
				
				break;
			default : 
				
				break;
		}
	}

}

