package client.util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * log per minute in a thread
 * @author Dai
 *
 */
public class TimerAndLogger implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				TimeUnit.MINUTES.sleep(1);
				ClientLogger.writePerMinute();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
