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
					writer.writeKO(Protocol.CONNECT);
				}
				break;
			case Protocol.CONSUME :
				break;
			case Protocol.PROD : 
				break;
			case Protocol.SHOP : 
				break;
			case Protocol.STAT : 
				break;
			case Protocol.SUB :
				break;
			case Protocol.DISCONNECT :
				name = reader.getName();
				long id = reader.getId();
				if(document.disconnect(name, id))
				{
					writer.writeDisconnected();
				}
				else
				{
					writer.writeKO(Protocol.DISCONNECT);
				}
				break;
			case -1 :
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
