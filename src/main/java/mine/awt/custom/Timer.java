/**
 * A Timer extended from Counter
 * Update display every "sleepTime" seconds.
 * Wait until "isRunning" is set to true to count up
 *
 * @author Yuhong Guo
 * @version 3.5
 */

package mine.awt.custom;

public class Timer extends Counter implements Runnable {

  //Private Instance Variable
  private boolean isRunning;
  private final Thread timerThread;

  //Private Constant
  private static final int sleepTime = 1000;

  //Public Constructor
  public Timer(int min, int max, int start, int digit) {
    super(min, max, start, digit);
    isRunning = false;
    timerThread = new Thread(this);
    timerThread.setPriority(Thread.MIN_PRIORITY + 1);
    timerThread.start();
  }

  //Public Instance Method
  public void run() {
    try {
      while (true) {
        synchronized (this) {
          while (!isRunning)
            wait();
          increment();
        }
        Thread.sleep(sleepTime);
      }
    } catch (InterruptedException e) {
    }
  }

  //change state of isRunning
  public synchronized void setRunning(boolean r) {
    isRunning = r;
    notify();
  }

  //return the running Thread
  public Thread getTimerThread() {
    return timerThread;
  }
}
