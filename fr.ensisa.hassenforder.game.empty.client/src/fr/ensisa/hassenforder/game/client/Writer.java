package fr.ensisa.hassenforder.game.client;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;

public class Writer extends BasicAbstractWriter {
	
	
	public Writer(OutputStream outputStream) {
		super (outputStream);
	}
	
	public void writeTab(ArrayList tab){
		for (int i=0; i<tab.size();i++)
		writeString(tab.get(i).toString());
	}
	
	public void writeConnect(String name, String pwd){
		writeInt(Protocol.CONNECT)
	}

}
