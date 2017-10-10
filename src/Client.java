import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Collection;
import java.util.Random;
import java.util.Vector;

public class Client implements Runnable {
	private Socket client;
	private int id;
	private Vector<Integer> guess;
	public static boolean win = false;
	
	public Client(Vector<Integer> guess) {
		this.guess = guess;
	}
	
	public static void main(String[] args) {
		Vector<Integer> guess = new Vector<Integer>();
		for (int id = 0; id < 5; id++){
			Thread a = new Thread(new Client(guess));
			a.start();
		}
	}

	@Override
	public void run() {
		Vector<Integer> guess = new Vector<Integer>();
		this.guess = guess;
		try {
			Socket sox = new Socket("127.0.0.1", 5555);
			InputStream input = sox.getInputStream();
			BufferedReader buff = new BufferedReader(new InputStreamReader(input));
			
			Thread.sleep(1000);
			while(true) {
				Random r = new Random();
				int Low = 1;
				int High = 101;
				int guessNum = r.nextInt(High-Low) + Low;
				
				guess.add(new Integer(guessNum));
				System.out.println("Guessing: " + guessNum);
				Thread.sleep(2000);
			}
		}
		catch(IOException ioe){
			System.out.println("Sorry -- connection was interrupted or could not be made");
			System.err.println(ioe);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
