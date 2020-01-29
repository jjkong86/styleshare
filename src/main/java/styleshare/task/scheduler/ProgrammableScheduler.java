package styleshare.task.scheduler;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;

public class ProgrammableScheduler {
	private ThreadPoolTaskScheduler scheduler;
	private Trigger trigger;

	ProgrammableScheduler() throws InterruptedException {
		this.trigger = getTrigger1();
		startScheduler();
		Thread.sleep(5000);
		stopScheduler();
		this.trigger = getTrigger2();
		startScheduler();
		Thread.sleep(5000);
		stopScheduler();
	}

	public void stopScheduler() {
		scheduler.shutdown();
	}

	public void startScheduler() {
		scheduler = new ThreadPoolTaskScheduler();
		scheduler.initialize();
		// 스케쥴러가 시작되는 부분
		scheduler.schedule(getRunnable(), this.trigger);
	}

	private Runnable getRunnable() {
		return () -> {
			// do something
			System.out.println(new Date());
		};
	}

	private Trigger getTrigger1() {
		// 작업 주기 설정
		return new PeriodicTrigger(1, TimeUnit.SECONDS);
	}

	private Trigger getTrigger2() {
		// 작업 주기 설정
		return new PeriodicTrigger(2, TimeUnit.SECONDS);
	}

	public static void main(String[] args) throws InterruptedException {
		new ProgrammableScheduler();
	}
}
