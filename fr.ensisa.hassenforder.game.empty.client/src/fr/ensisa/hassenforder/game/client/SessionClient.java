package fr.ensisa.hassenforder.game.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Collection;
import java.util.Map;

import fr.ensisa.hassenforder.game.model.Category;
import fr.ensisa.hassenforder.game.model.Player;
import fr.ensisa.hassenforder.game.model.Product;
import fr.ensisa.hassenforder.game.model.Shop;
import fr.ensisa.hassenforder.network.FileHelper;
import fr.ensisa.hassenforder.network.Protocol;

public class SessionClient {

	private Socket connection;
    private String name;
    private String pwd;
    private long id;
    private Player p;
    private Collection <Product> shop;
    private Product pr;
	private Category AMMO ;
	private Collection <Product> products;
	private int cash;
	
	public SessionClient (Socket connection) {
		this.connection = connection;
		this.name = null;
		this.id = 0;
	}

	public boolean connect (String username, String userpassword) {
		try {
			Writer w = new Writer(connection.getOutputStream());
			Reader r = new Reader(connection.getInputStream());
			w.writeConnect(username, userpassword);
			w.send();
			r.receive();
			if (r.getProtocol()==Protocol.OK) {
				this.id=r.getId();
				this.name=username;
				return true;
			}
			else return false;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean disconnect () {
		try {
			Writer w = new Writer(connection.getOutputStream());
			Reader r = new Reader(connection.getInputStream());
			w.writeDisconnect(name, id);
			w.send();
			r.receive();
			if (r.getProtocol()==Protocol.OK)
				return true;
			else return false;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean addCash (int amount) {
		try {
			Writer w = new Writer(connection.getOutputStream());
			Reader r = new Reader(connection.getInputStream());
			w.writeAdd(name, id, amount);
			w.send();
			r.receive();
			if (r.getProtocol()==Protocol.OK){
				this.cash=r.getCash();
			return true;
			}
			else return false;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean clearProducts () {
		try {
			Writer w = new Writer(connection.getOutputStream());
			Reader r = new Reader(connection.getInputStream());
			w.writeClear(name, id);
			w.send();
			r.receive();
			if (r.getProtocol()==Protocol.OK){
			return true;
			}
			else return false;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean consumeProducts () {
		try {
			Writer w = new Writer(connection.getOutputStream());
			Reader r = new Reader(connection.getInputStream());
			w.writeConsume(name, id);
			w.send();
			r.receive();
			if (r.getProtocol()==Protocol.OK){
			return true;}
			else return false;
		} catch (IOException e) {
			return false;
		}
	}

	public Player getStatistics () {
		try {
			Writer w = new Writer(connection.getOutputStream());
			Reader r = new Reader(connection.getInputStream());
			w.writeStat(name, id);
			w.send();
			r.receive();
			if (r.getProtocol()==Protocol.OK){
			return new Player(name,r.getImage(),r.getCash());
			}
			else{return null;}
		} catch (IOException e) {
			return null;
		}
	}

	public Collection<Product> getProducts () {
		try {
			Writer w = new Writer(connection.getOutputStream());
			Reader r = new Reader(connection.getInputStream());
			w.writeProd(name, id);
			w.send();
			r.receive();
			if (r.getProtocol()==Protocol.OK){
				for(int i=0; i<r.getSize();i++)
					products.add(r.getProduct());
			return products;
			}
			else return null;
		} catch (IOException e) {
			return null;
		}
	}

	public Collection<Product> getShop () {
		try {
			Writer w = new Writer(connection.getOutputStream());
			Reader r = new Reader(connection.getInputStream());
			w.writeShop(name, id);
			w.send();
			r.receive();
			if (r.getProtocol()==Protocol.OK){
				for(int i=0; i<r.getSize();i++)
					shop.add(r.getProduct());
			return shop;
			}
			else return null;
		} catch (IOException e) {
			return null;
		}
	}

	public boolean refreshShop () {
		try {
			Writer w = new Writer(connection.getOutputStream());
			Reader r = new Reader(connection.getInputStream());
			w.writeRefresh(name, id);
			w.send();
			r.receive();
			if (r.getProtocol()==Protocol.OK){
			for(int i=0; i<r.getSize();i++)
				shop.add(r.getProduct());
			return true;
			}
			else return false;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean buyProduct (String productName) {
		try {
			Writer w = new Writer(connection.getOutputStream());
			Reader r = new Reader(connection.getInputStream());
			w.writeBuy(name, id, productName);
			w.send();
			r.receive();
			if (r.getProtocol()==Protocol.OK){
			return true;
			}
			else return false;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean sellProduct (String productName) {
		try {
			Writer w = new Writer(connection.getOutputStream());
			Reader r = new Reader(connection.getInputStream());
			w.writeSell(name, id, productName);
			w.send();
			r.receive();
			if (r.getProtocol()==Protocol.OK){
			return true;
			}
			else return false;
		} catch (IOException e) {
			return false;
		}
	}

	public String getImage (String imageName) {
		try {
			if (true) throw new IOException ("not yet implemented");
			return imageName;
		} catch (IOException e) {
			return null;
		}
	}
}
