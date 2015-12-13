package ie.gmit.sw;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Servant extends UnicastRemoteObject implements VigenereBreaker {

	private static final long serialVersionUID = 1L;
	private KeyEnumerator breaker;
	
	public Servant(String filename) throws Exception {
		breaker = new KeyEnumerator(filename);
	}
	
	public String decrypt(String cypherText, int maxKeyLength) throws RemoteException {
		return breaker.crackCypher(cypherText, maxKeyLength);
	}

	public static void main(String[] args) throws Exception {
		if(args.length==0){
			System.out.println("Please use: java ie.gmit.sw.Servant " + "../filename.txt");
		}
		
		System.out.println("starting remote service");
		
		LocateRegistry.createRegistry(1099);
		
		Naming.bind("cypher-service", new Servant(args[0]));
		
		System.out.println("service started...");
		
		
	}
	
}
