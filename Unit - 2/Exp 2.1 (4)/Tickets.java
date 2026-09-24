/*Develop a ticket booking system with synchronized threads to 
ensure no double booking of seats. Use thread priorities to 
simulate VIP bookings being processed first. */
import java.util.*;
public class Tickets {
    private static final int TOTAL_SEATS = 10;
    private static final int VIP_PRIORITY = Thread.MAX_PRIORITY;
    private static int nextSeat = 1;

    private static synchronized int bookSeat(String customerType) {
        if (nextSeat > TOTAL_SEATS) {
            return -1;
        }

        int seatNumber = nextSeat++;
        System.out.println(customerType + " booking confirmed for seat " + seatNumber);
        return seatNumber;
    }

    private static class BookingTask implements Runnable {
        private final String customerType;
        private final int priority;

        BookingTask(String customerType, int priority) {
            this.customerType = customerType;
            this.priority = priority;
        }

        @Override
        public void run() {
            Thread.currentThread().setPriority(priority);
            int seatNumber = bookSeat(customerType);
            if (seatNumber == -1) {
                System.out.println(customerType + " booking failed: no seats available");
            }
        }
    }

    public static void main(String[] args){
        // Create a collection of threads
        List<Thread> threads = new ArrayList<>();
        // Create and start threads for VIP bookings
        for (int i = 0; i < 5; i++){
            Thread vipThread = new Thread(new BookingTask("VIP", VIP_PRIORITY));
            vipThread.setPriority(Thread.MAX_PRIORITY);
            threads.add(vipThread);
            vipThread.start();
        }
        // Create and start threads for regular bookings
        for (int i = 0; i < 5; i++){
            Thread regularThread = new Thread(new BookingTask("Regular", Thread.NORM_PRIORITY));
            threads.add(regularThread);
            regularThread.start();
        }
        // Wait for all threads to finish
        for (Thread thread : threads){  
            try{
                thread.join();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        // Print the number of available seats
        int bookedSeats = nextSeat - 1;
        System.out.println("Available Seats: " + (TOTAL_SEATS - bookedSeats));
        // Print the number of booked seats
        System.out.println("Booked Seats: " + bookedSeats);
    }
}
