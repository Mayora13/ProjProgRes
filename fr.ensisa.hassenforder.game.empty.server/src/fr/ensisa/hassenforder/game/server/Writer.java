package fr.ensisa.hassenforder.game.server;

import java.io.OutputStream;
import java.util.Collection;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;

public class Writer extends BasicAbstractWriter {

	public Writer(OutputStream outputStream) {
		super (outputStream);
	}

	public void writeConnected(long id) {
		// TODO Auto-generated method stub
		writeOK();
		writeLong(id);
	}

	public void writeOK() {
		// TODO Auto-generated method stub
		writeInt(Protocol.OK);
	}

	public void writeKO() {
		// TODO Auto-generated method stub
		writeInt(Protocol.KO);
	}

	public void writeError(String msg) {
		// TODO Auto-generated method stub
		writeKO();
		writeString(msg);
	}


}
