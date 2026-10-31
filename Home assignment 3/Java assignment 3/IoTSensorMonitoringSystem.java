import java.util.Scanner;

public class IoTSensorMonitoringSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== IoT Sensor Monitoring System =====");
            System.out.println("1. Start Simulation");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    Data data = new Data();

                    RandomNumberGenerator rng = new RandomNumberGenerator(data);
                    SquareCalculator square = new SquareCalculator(data);
                    CubeCalculator cube = new CubeCalculator(data);

                    square.start();
                    cube.start();
                    rng.start();

                    try {
                        rng.join();
                        square.join();
                        cube.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    break;

                case 2:
                    System.out.println("Program Terminated.");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}

class Data {

    int number;
    boolean available = false;
    boolean finished = false;

    public synchronized void setNumber(int num) {

        while (available) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        number = num;
        available = true;
        notifyAll();
    }

    public synchronized int getEven() {

        while ((!available || number % 2 != 0) && !finished) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        if (finished)
            return -1;

        int temp = number;
        available = false;
        notifyAll();
        return temp;
    }

    public synchronized int getOdd() {

        while ((!available || number % 2 == 0) && !finished) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        if (finished)
            return -1;

        int temp = number;
        available = false;
        notifyAll();
        return temp;
    }

    public synchronized void finish() {
        finished = true;
        notifyAll();
    }
}

class RandomNumberGenerator extends Thread {

    Data data;

    RandomNumberGenerator(Data data) {
        this.data = data;
    }

    public void run() {

        for (int i = 1; i <= 10; i++) {

            int num = (int) (Math.random() * 100) + 1;

            System.out.println("Generated Number : " + num);

            data.setNumber(num);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }

        data.finish();
    }
}

class SquareCalculator extends Thread {

    Data data;

    SquareCalculator(Data data) {
        this.data = data;
    }

    public void run() {

        while (true) {

            int num = data.getEven();

            if (num == -1)
                break;

            System.out.println("Square of " + num + " = " + (num * num));
        }
    }
}

class CubeCalculator extends Thread {

    Data data;

    CubeCalculator(Data data) {
        this.data = data;
    }

    public void run() {

        while (true) {

            int num = data.getOdd();

            if (num == -1)
                break;

            System.out.println("Cube of " + num + " = " + (num * num * num));
        }
    }
}