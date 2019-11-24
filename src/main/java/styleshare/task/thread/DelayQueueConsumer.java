package styleshare.task.thread;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DelayQueueConsumer implements Runnable {
	private BlockingQueue<DelayObject> queue;
	private Integer numberOfElementsToTake;
	public AtomicInteger numberOfConsumedElements = new AtomicInteger();

	// standard constructors

	@Override
	public void run() {
		for (int i = 0; i < numberOfElementsToTake; i++) {
			try {
				DelayObject object = queue.take();
				numberOfConsumedElements.incrementAndGet();
				System.out.println("Consumer take: " + object);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static class TestClass {

		@Test
		public void givenDelayQueue_whenProduceElement_thenShouldConsumeAfterGivenDelay() throws InterruptedException {
			// given
			ExecutorService executor = Executors.newFixedThreadPool(2);

			BlockingQueue<DelayObject> queue = new DelayQueue<>();
			int numberOfElementsToProduce = 2;
			int delayOfEachProducedMessageMilliseconds = 500;
//			DelayQueueConsumer consumer = new DelayQueueConsumer(queue, numberOfElementsToProduce);
			DelayQueueProducer producer = new DelayQueueProducer(queue, numberOfElementsToProduce,
					delayOfEachProducedMessageMilliseconds);

			// when
			executor.submit(producer);
//			executor.submit(consumer);

			// then
			executor.awaitTermination(5, TimeUnit.SECONDS);
			executor.shutdown();

//			assertEquals(consumer.numberOfConsumedElements.get(), numberOfElementsToProduce);
		}

	}
}
