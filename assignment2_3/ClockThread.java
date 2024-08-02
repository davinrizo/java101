public class ClockThread extends Thread {
    private Clock clock;

    public ClockThread(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void run() {
        clock.displayCurrentTime();
    }
}

