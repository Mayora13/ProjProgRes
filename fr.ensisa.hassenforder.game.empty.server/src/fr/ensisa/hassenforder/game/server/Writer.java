package fr.ensisa.hassenforder.game.server;

import java.io.OutputStream;
import java.util.Collection;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;

public class Writer extends BasicAbstractWriter {

	public Writer(OutputStream outputStream) 
	{
		super (outputStream);
	}

	public void writeConnected(long id)
	{
		writeOK();
		writeLong(id);
	}

	public void writeOK() 
	{
		writeInt(Protocol.OK);
	}

	public void writeKO() 
	{
		writeInt(Protocol.KO);
	}

	public void writeError(String msg)
	{
		writeKO();
		writeString(msg);
	}
	
	public void writeTab(int discr)
	{
		switch(discr)
		{
			case Protocol.SHOP :
				break;
			case Protocol.PROD :
				break;
		}
	}


}
