public class BankTransaction extends Thread {

    private static final TransactionControl control = new TransactionControl();

    private final long amount;

    public BankTransaction(String name, long amount, int priority) {
        super(name);
        this.amount = amount;
        setPriority(priority);
    }

    @Override
    public void run() {

        if (amount >= 50000) {
            control.processHighValue(amount);
        } else {
            control.processLowValue(amount);
        }
    }

    public static void main(String[] args) {

        BankTransaction lowValueTransaction =
                new BankTransaction("LowValueTransaction", 5000, Thread.MIN_PRIORITY);

        BankTransaction highValueTransaction =
                new BankTransaction("HighValueTransaction", 50000, Thread.MAX_PRIORITY);

        // Start low-value first
        lowValueTransaction.start();
        highValueTransaction.start();

        try {
            highValueTransaction.join();
            lowValueTransaction.join();
        } catch (InterruptedException e) {
            System.out.println("Transaction processing interrupted.");
        }
    }
}

class TransactionControl {

    private boolean highValueDone = false;

    public synchronized void processHighValue(long amount) {

        System.out.println("High-value transaction processed first: ₹" + amount);

        highValueDone = true;

        notifyAll();
    }

    public synchronized void processLowValue(long amount) {

        while (!highValueDone) {
            try {
                wait();
            } catch (InterruptedException e) {
                return;
            }
        }

        System.out.println("Low-value transaction processed later: ₹" + amount);
    }
}