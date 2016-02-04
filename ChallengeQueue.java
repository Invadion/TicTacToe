// We use https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/BlockingQueue.html

import java.util.concurrent.*;

public class ChallengeQueue {

  // We choose the LinkedBlockingQueue implementation of BlockingQueue:
  private BlockingQueue<GameChallenge> queue = new LinkedBlockingQueue<GameChallenge>();

  // Inserts the specified message into this queue.
  public void offer(GameChallenge challenge) {
    queue.offer(challenge);
  }


  public GameChallenge take() {

    while (true) {
      try {
        return(queue.take());
      }
      catch (InterruptedException e) {
        // This can in principle be triggered by queue.take().
        // But this would only happen if we had interrupt() in our code,
        // which we don't.
        // In any case, if it could happen, we should do nothing here
        // and try again until we succeed without interruption.
      }

    }
  }
}
