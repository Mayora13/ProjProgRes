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
		writeOK(Protocol.CONNECT);
		writeLong(id);
	}

	public void writeOK(int service) {
		// TODO Auto-generated method stub
		writeInt(service);
		writeInt(Protocol.OK);
	}

	public void writeKO(int err) {
		// TODO Auto-generated method stub
		writeInt(err);
		writeInt(Protocol.KO);
	}


}
