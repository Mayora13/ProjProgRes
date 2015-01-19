package fr.ensisa.hassenforder.game.server;

import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;

import fr.ensisa.hassenforder.game.model.Category;
import fr.ensisa.hassenforder.game.model.Product;
import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.network.FileHelper;

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
		byte[] imgB = FileHelper.readContent(img);
		FileHelper.writeContent(img, imgB);
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
		Iterator<Product> it = prod.iterator();
		Product p;
		switch (discr) 
		{
			case Protocol.SHOP : 
				writeInt(Protocol.SHOP);
				while(it.hasNext())
				{
					p = it.next();
					String img = p.getImage();
					String name = p.getName();
					Category category = p.getCategory();
					int duration = p.getDuration();
					int count = p.getCount();
					writeCategory(category);
					writeString(name);
					writeInt(duration);
					writeBoolean(count > 1);
					writeInt(count);
					writeImage(img);		
				}
				break;
			case Protocol.PROD :
				writeInt(Protocol.PROD);
				while(it.hasNext())
				{
					p = it.next();
					long rTime = p.getRemainingTime();
					String img = p.getImage();
					String name = p.getName();
					int count = p.getCount();
					Category category = p.getCategory();
					writeCategory(category);
					writeString(name);
					writeLong(rTime);
					writeBoolean(count > 1);
					writeInt(count);
					writeImage(img);
				}
				break;
		}
	}

	private void writeCategory(Category category) 
	{
		switch(category)
		{
			case WEAPON :
				writeInt(1);
				break;
			case AMMO :
				writeInt(2);
				break;
			case FOOD :
				writeInt(3);
				break;
		}
	}


}