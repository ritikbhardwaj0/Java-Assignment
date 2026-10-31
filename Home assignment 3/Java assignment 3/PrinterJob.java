public class PrinterJob implements Runnable {

    private int jobId;
    private String studentName;

    public PrinterJob(int jobId, String studentName) {
        this.jobId = jobId;
        this.studentName = studentName;
    }

    @Override
    public void run() {
        System.out.println("Printing job " + jobId + " by " + studentName);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Printing interrupted.");
        }

        System.out.println("Job " + jobId + " completed by " + studentName);
    }

    // Main method
    public static void main(String[] args) {

        Thread t1 = new Thread(new PrinterJob(1, "Student A"));
        Thread t2 = new Thread(new PrinterJob(2, "Student B"));
        Thread t3 = new Thread(new PrinterJob(3, "Student C"));

        t1.start();
        t2.start();
        t3.start();
    }
}