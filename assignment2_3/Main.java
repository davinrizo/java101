public class Main {
    public static void main(String[] args) {
        Clock clock = new Clock();

        // Create a thread to run the clock display
        ClockThread clockThread = new ClockThread(clock);

        // Set the priority for the clock display thread
        clockThread.setPriority(Thread.MAX_PRIORITY);

        // Start the clock display thread
        clockThread.start();

        // Simulate background processing with a lower priority
        Thread backgroundThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Background thread interrupted.");
                }
            }
        });

        // Set the priority for the background thread
        backgroundThread.setPriority(Thread.MIN_PRIORITY);

        // Start the background thread
        backgroundThread.start();
    }
}

