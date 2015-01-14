package fr.ensisa.hassenforder.game.server;

import java.io.OutputStream;
import java.util.Collection;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;

public class Writer extends BasicAbstractWriter {

	public Writer(OutputStream outputStream) {
		super (outputStream);
	}

	public void writeOK(long id) {
		// TODO Auto-generated method stub
		writeLong(id);
	}

	public void writeKO() {
		// TODO Auto-generated method stub
		writeString("Service failed");
	}
	

}
