import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
	
	private Socket client;
	private Vector<Integer> guess;
	
	public Server(Socket client, Vector<Integer> guess) {
		this.client = client;
		this.guess = guess;
	}

	public static void main(String[] args) {
		ExecutorService pool = Executors.newCachedThreadPool();
		Vector<Integer> guess = new Vector<Integer>();
		try {
			// open the socket
			ServerSocket sox = new ServerSocket(5555);
			pool.execute(new Server(sox.accept(), guess));
			// now listen for connections
			while(guess.isEmpty() == false)
				pool.execute(new Server(sox.accept(), guess));
		}
		catch (IOException ioe) {
			System.out.println("Service disconnected.");
			System.err.println(ioe);
		}
		finally{
			pool.shutdown();
	}
}


	@Override
	public void run() {
		try {
			PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
			Vector<Integer> guess = new Vector<Integer>();
			guess = this.guess;
			Random r = new Random();
			//Making random number between 1 - 100
			int Low = 1;
			int High = 101;
			int answer = r.nextInt(High-Low) + Low;
			Thread.sleep(1000);
			while(guess.isEmpty() == false) {
				System.out.println("Waiting for guesses..");
				Thread.sleep(5000);
			}
				
				/*if(client.equals(answer)) {
					pout.println(answer + " is correct!");
				}
				else 
					System.out.println(client.toString() + " was incorrect.");
					*/
			
		}
		catch (java.io.IOException ioe) {
			System.out.println("Sorry, connection was lost or interrupted");
			System.err.println(ioe);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
