package sample.chatbox;

public class ShutDownHook extends Thread {
    private long timeWait;
    public ShutDownHook(long time) {
        this.timeWait = time;
    }
    @Override
    public void run() {
        try {
            // do whatever clean-up you need, wait for some time before giving up.
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
        }
    }

     public static void main(String[] args) throws Exception {
        // add runtime hook, runs as JVM exits
        Runtime.getRuntime().addShutdownHook(new ShutDownHook(60L));
     }
}