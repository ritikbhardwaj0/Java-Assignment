// Shared Resource Class
public class TicketBooking {

    private int availableSeats;

    public TicketBooking(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public synchronized void bookSeat(int requestedSeats) {

        System.out.println(Thread.currentThread().getName()
                 + " seat(s).");

        if (requestedSeats <= availableSeats) {

            System.out.println("Booking Successful for "
                    + Thread.currentThread().getName());

            availableSeats -= requestedSeats;

            System.out.println("Remaining Seats : "
                    + availableSeats);

        } else {

            System.out.println("Booking Failed for "
                    + Thread.currentThread().getName());

            System.out.println("Reason : Not enough seats available.");
        }

    }

    public static void main(String[] args) {

        // Shared Ticket Booking Object
        TicketBooking ticketBooking = new TicketBooking(2);

        // Thread 1
        Thread user1 = new Thread(() -> {
            ticketBooking.bookSeat(1);
        });

        user1.setName("User1");

        // Thread 2
        Thread user2 = new Thread(() -> {
            ticketBooking.bookSeat(2);
        });

        user2.setName("User2");

        // Start Threads
        user1.start();
        user2.start();
    }
}