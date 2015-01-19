package fr.ensisa.hassenforder.game.server;

import java.io.File;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;

import fr.ensisa.hassenforder.game.model.Product;
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

	public void writeImage(String img)
	{
		File image = new File(img);
		long len = image.length();
	}

	public void writeCash(int cash) 
	{
		writeInt(cash);
	}

	public void writeProtocol(int discr) 
	{
		writeInt(discr);
	}
	
	public void writeProduct(int discr, Collection<Product> prod) {
		switch (discr) {
			case Protocol.SHOP : 
				writeInt(Protocol.SHOP);
				Iterator<Product> it = prod.iterator();
				while(it.hasNext())
				{
					
				}
				break;
			case Protocol.PROD :
				
				break;
			default : 
				
				break;
		}
	}


}