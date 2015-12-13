package ie.gmit.sw.client;

import java.rmi.Naming;

import ie.gmit.sw.VigenereBreaker;

public class Client {
	
	
	public static void main(String[] args) throws Exception {
		/*
		 * This stuff needs to be in your tomcat app
		 */
		VigenereBreaker vb = (VigenereBreaker) Naming.lookup("//127.0.0.1/cypher-service");
		
		String result = vb.decrypt("MABLBLMAXNEMBFTMXMXLMHYYTMX", 4);
		System.out.println(result);
	}
}
