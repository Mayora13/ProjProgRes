package fr.ensisa.hassenforder.game.server;

import java.io.IOException;
import java.net.Socket;
import java.util.Collection;

import fr.ensisa.hassenforder.game.model.Account;
import fr.ensisa.hassenforder.game.model.Product;
import fr.ensisa.hassenforder.game.model.User;
import fr.ensisa.hassenforder.network.Protocol;

public class SessionServer {

	private Socket connection;
	private Document document;
	
	public SessionServer (Document document, Socket connection) {
		this.document = document;
		this.connection = connection;
	}
	
	public boolean operate() {
		try {
			Writer writer = new Writer (connection.getOutputStream());
			Reader reader = new Reader (connection.getInputStream());
			reader.receive ();
			switch (reader.getType ()) {
			case 0 : 
				return false; // socket closed
			case Protocol.CONNECT :
				String pwd = reader.getPwd();
				String name = reader.getName();
				User tryConnect = this.document.connect(name, pwd);
				if(tryConnect != null)
				{
					writer.writeConnected(tryConnect.getId());
				}
				else
				{
					writer.writeError("Connect failed");
				}
				break;
			case Protocol.CONSUME :
				name = reader.getName();
				long id = reader.getId();
				long idTest = reader.getIdTest();
				if(id == idTest)
				{
					if(this.document.consumeProducts(name, idTest))
					{
						writer.writeProtocol(Protocol.CONSUME);
						writer.writeOK();
					}
				}
				break;
			case Protocol.PROD : 
				name = reader.getName();
				id = reader.getId();
				idTest = reader.getIdTest();
				Collection<Product> p = this.document.getProducts(name, idTest);
				if(p != null)
				{
					writer.writeProduct(Protocol.PROD, p);
				}
				else
				{
					writer.writeError("Bad ID");
				}
				break;
			case Protocol.SHOP : 
				name = reader.getName();
				id = reader.getId();
				idTest = reader.getIdTest();
				Collection<Product> prod = this.document.getShop(name, idTest);
				if(prod != null)
				{
					writer.writeProduct(Protocol.SHOP, prod);
				}
				else
				{
					writer.writeError("Bad ID");
				}
				break;
			case Protocol.STAT : 
				name = reader.getName();
				id = reader.getId();
				idTest = reader.getIdTest();
				writer.writeProtocol(Protocol.STAT);
				Account a = this.document.getStatistics(name, idTest);
				if(a != null)
				{
					String img = a.getImage();
					int cash = a.getCash();
					writer.writeImage("./res/" + img);
					writer.writeCash(cash);
				}
				else
				{
					writer.writeError("Bad ID");
				}
				break;
			case Protocol.SUB :
				name = reader.getName();
				idTest = reader.getIdTest();
				id = reader.getId();
				//subtraction
				break;
			case Protocol.DISCONNECT :
				name = reader.getName();
				id = reader.getId();
				long idDisc = reader.getIdDisc();
				if(id == idDisc)
				{
					if(document.disconnect(name, idDisc))
					{
						writer.writeProtocol(Protocol.DISCONNECT);
						writer.writeOK();
					}
					else
					{
						writer.writeError("Bad User ye hacker !");
					}
				}
				else
				{
					writer.writeError("Disconnect failed");
				}
				break;
			case Protocol.ADD :
				name = reader.getName();
				id = reader.getId();
				idTest = reader.getIdTest();
				if(this.document.addCash(name, idTest, 100))
				{
					writer.writeProtocol(Protocol.ADD);
					writer.writeOK();
				}
				break;
			case Protocol.CLEAR :
				name = reader.getName();
				id = reader.getId();
				idTest = reader.getIdTest();
				if(this.document.clearProducts(name, idTest))
				{
					writer.writeProtocol(Protocol.CLEAR);
					writer.writeOK();
				}
				break;
			case -1 :
				writer.writeError("Bad message");
				break;
			default: return false; // connection jammed
			}
			writer.send ();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

}