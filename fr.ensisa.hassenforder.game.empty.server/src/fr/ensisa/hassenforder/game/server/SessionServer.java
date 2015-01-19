package fr.ensisa.hassenforder.game.server;

import java.io.IOException;
import java.net.Socket;
import java.util.Collection;

import fr.ensisa.hassenforder.game.model.Account;
import fr.ensisa.hassenforder.game.model.Product;
import fr.ensisa.hassenforder.game.model.User;
import fr.ensisa.hassenforder.network.FileHelper;
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
			case Protocol.ADD :
				break;
			case Protocol.CLEAR :
				break;
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
				break;
			case Protocol.PROD : 
				break;
			case Protocol.SHOP : 
				
				break;
			case Protocol.STAT : 
				name = reader.getName();
				long id = reader.getId();
				long idTest = reader.getIdTest();
				if(id == idTest)
				{
					Account a = this.document.getStatistics(name, idTest);
					String img = a.getImage();
					int cash = a.getCash();
					writer.writeProtocol(Protocol.STAT);
					writer.writeImage(img);
					writer.writeCash(cash);
				}
				else
				{
					writer.writeError("Bad ID");
				}
				break;
			case Protocol.SUB :
				break;
			case Protocol.DISCONNECT :
				name = reader.getName();
				id = reader.getId();
				long idDisc = reader.getIdDisc();
				if(id == idDisc)
				{
					if(document.disconnect(name, idDisc))
					{
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