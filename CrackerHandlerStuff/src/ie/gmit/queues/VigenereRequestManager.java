package ie.gmit.queues;

import java.util.Map;
import java.util.concurrent.*;

public class VigenereRequestManager {
	
	private static final int maxCap = 10;
    private BlockingQueue queue = new ArrayBlockingQueue<Request>(maxCap);
    private Map <Long, String> out = new ConcurrentHashMap<Long,String>();
    private VigenereHandler vigHandler;
    private String result;
    
 
    public void add(final Request r){
        new Thread(new Runnable(){
        	public void run(){
	        	try{
		            queue.put(r); // Blocks if queue is full.
		            //Puts request to the out queue so it can be passed to the VigenereHandler
		            out.put(r.getJobNumber(), r.getCypherText());
		            vigHandler = new VigenereHandler(queue, out);
		            //Puts into the out map decoded message/text
		            out.put(r.getJobNumber(), vigHandler.getResult());
		        }
		        catch (Exception e){
		        	System.out.println("Error encountered: " + e.toString());
		        }
        	}
       }).start();
   }
   
    
    public String getResult(long jobNumber){
    	new Thread(new Runnable(){
    		public void run(){
		        try {
		           result = out.get(jobNumber);
		        }
		        catch (Exception e){
		        	System.out.println("Encountered error. " + e.toString());; // no result
		        }
    		}
    	}).start();
    	out.remove(jobNumber);
    	vigHandler.removeTheRequest(jobNumber);
    	return result;
    }
    
    public static void main(String[] args) {
		//Request newReq = new Request("a", 4, 1);
	
	}
}

