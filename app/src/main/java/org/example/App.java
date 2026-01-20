package org.example;

import org.example.entities.Train;
import org.example.entities.User;
import org.example.services.UserBookingService;
import org.example.utils.PasswordHashUtil;

import java.io.IOException;
import java.util.*;

public class App {

    public static void main(String[] args) {

        System.out.println("\n========================================");
        System.out.println("  Welcome to Railway Ticket Booking App ");
        System.out.println("========================================\n");

        Scanner scan = new Scanner(System.in);
        int option = 0;

        UserBookingService userBookingServiceObject;
        try {
            userBookingServiceObject = new UserBookingService();
        } catch (IOException e) {
            System.out.println("Application failed to start.");
            return;
        }

        Train trainSelectedForBooking = null;

        while (option != 7) {

            System.out.println("\n------------ MENU ------------");
            System.out.println("1. Sign up");
            System.out.println("2. Login");
            System.out.println("3. Fetch Bookings");
            System.out.println("4. Search Trains");
            System.out.println("5. Book a Seat");
            System.out.println("6. Cancel Booking");
            System.out.println("7. Exit");
            System.out.println("------------------------------");
            System.out.print("Choose an option: ");

            if (!scan.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scan.nextLine();
                continue;
            }

            option = scan.nextInt();
            scan.nextLine();

            switch (option) {

                case 1 -> { // To Sign up
                    System.out.print("\nEnter username: ");
                    String username = scan.nextLine();

                    System.out.print("Enter password: ");
                    String password = scan.nextLine();

                    User newUser = new User(
                            UUID.randomUUID().toString(),
                            username,
                            password,
                            PasswordHashUtil.hashPassword(password),
                            new ArrayList<>()
                    );

                    userBookingServiceObject.signUpUser(newUser);
                }

                case 2 -> { // To Login
                    System.out.print("\nEnter username: ");
                    String username = scan.nextLine();

                    System.out.print("Enter password: ");
                    String password = scan.nextLine();

                    User loginUser = new User(
                            UUID.randomUUID().toString(),
                            username,
                            password,
                            PasswordHashUtil.hashPassword(password),
                            new ArrayList<>()
                    );

                    try {
                        userBookingServiceObject = new UserBookingService(loginUser);
                        System.out.println("Login successful.");
                    } catch (IOException e) {
                        System.out.println("Login failed.");
                    }
                }

                case 3 -> { // To Fetch Bookings
                    System.out.println("\nFetching your bookings...\n");
                    userBookingServiceObject.fetchBookings();
                }

                case 4 -> { // To Search Trains
                    System.out.print("\nEnter source station: ");
                    String source = scan.nextLine();

                    System.out.print("Enter destination station: ");
                    String destination = scan.nextLine();

                    List<Train> trains = userBookingServiceObject.getTrains(source, destination);

                    if (trains.isEmpty()) {
                        System.out.println("No trains found.");
                        break;
                    }

                    System.out.println("\nAvailable Trains:");
                    int index = 1;
                    for (Train t : trains) {
                        System.out.println(index + ". Train ID: " + t.getTrainId());
                        t.getStationTimes().forEach(
                                (station, time) ->
                                        System.out.println("   " + station + " - " + time)
                        );
                        index++;
                        System.out.println();
                    }

                    System.out.print("Select train number: ");
                    if (!scan.hasNextInt()) {
                        System.out.println("Invalid selection.");
                        scan.nextLine();
                        break;
                    }

                    int selected = scan.nextInt();
                    scan.nextLine();

                    if (selected < 1 || selected > trains.size()) {
                        System.out.println("Invalid train number.");
                        break;
                    }

                    trainSelectedForBooking = trains.get(selected - 1);
                }

                case 5 -> { // To Book a Seat
                    if (trainSelectedForBooking == null) {
                        System.out.println("Please select a train first.");
                        break;
                    }

                    List<List<Integer>> seats = userBookingServiceObject.fetchSeats(trainSelectedForBooking);

                    System.out.println("\nSeat Layout:");
                    for (int i = 0; i < seats.size(); i++) {
                        System.out.print("Row " + i + ": ");
                        for (Integer seat : seats.get(i)) {
                            System.out.print(seat + " ");
                        }
                        System.out.println();
                    }

                    System.out.print("\nEnter row: ");
                    if (!scan.hasNextInt()) {
                        System.out.println("Invalid input.");
                        scan.nextLine();
                        break;
                    }
                    int row = scan.nextInt();

                    System.out.print("Enter column: ");
                    if (!scan.hasNextInt()) {
                        System.out.println("Invalid input.");
                        scan.nextLine();
                        break;
                    }
                    int col = scan.nextInt();
                    scan.nextLine();

                    boolean booked = userBookingServiceObject.bookTrainSeat(trainSelectedForBooking, row, col);

                    System.out.println(booked
                            ? "Seat booked successfully."
                            : "Unable to book the seat."
                    );
                }

                case 6 -> { // To Cancel Booking
                    System.out.print("\nEnter Ticket ID: ");
                    String ticketId = scan.nextLine();

                    boolean cancelled = userBookingServiceObject.cancelBooking(ticketId);

                    System.out.println(cancelled
                            ? "Booking cancelled."
                            : "Cancellation failed."
                    );
                }

                case 7 -> { // To Exit from App
                    System.out.println("\nExiting application...");
                    System.out.println("Thank you for using Railway Ticket Booking App 👋");
                }

                default -> System.out.println("Invalid option. Try again.");
            }
        }

        scan.close();
    }
}
