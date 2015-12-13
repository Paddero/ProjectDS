package ie.gmit.queues;

import java.rmi.Naming;
import java.util.Map;
import java.util.concurrent.*;
import ie.gmit.sw.VigenereBreaker;

public class VigenereHandler implements Runnable{
	
    private BlockingQueue <Request> queue;
    private Map <Long, String> out = new ConcurrentHashMap<Long, String>();
    private Request theReq = null; // declaring theReq on top so I can access it later
    
    
    public VigenereHandler (BlockingQueue<Request> q, Map <Long, String> out){
        this.queue = q;
        this.out = out;
        run();
    }
    
    public void run (){
        try{         
            VigenereBreaker vb = (VigenereBreaker) Naming.lookup("//127.0.0.1/cypher-service");
            String result = vb.decrypt(theReq.getCypherText(), theReq.getMaxKeyLength());
            out.put(theReq.getJobNumber(), result);
        }
        catch(Exception e){
        	System.out.println("Encountered an error: " + e.toString());
        }
    }
    
    public String getResult(){
    	return out.get(theReq.getJobNumber());
    }
    
    public void  removeTheRequest(long jobNumber){
    	out.remove(jobNumber);
    }
}
