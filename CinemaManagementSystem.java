
package oop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Class representing a user in the system.
 */
public class User {
    private String username;
    private String password;
    private String email;

    /**
     * Constructor to initialize the User object.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @param email    The email of the user.
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * Simulates user login.
     */
    public void login() {
        if (this.username.isEmpty() || this.password.isEmpty()) {
            throw new IllegalArgumentException("Username or password cannot be empty.");
        }
        System.out.println("Login successful!");
    }

    /**
     * Gets the email of the user.
     *
     * @return the email of the user.
     */
    public String getEmail() {
        return email;
    }
}

/**
 * Represents a Premium User.
 */
class PremiumUser extends User {

    /**
     * Constructor to initialize PremiumUser object.
     *
     * @param username The username of the premium user.
     * @param password The password of the premium user.
     * @param email    The email of the premium user.
     */
    public PremiumUser(String username, String password, String email) {
        super(username, password, email);
    }

    /**
     * Check if the user is a premium customer.
     *
     * @return true if the user is premium.
     */
    public boolean isPremiumCustomer() {
        return true;
    }
}

/**
 * Class representing a movie in the cinema.
 */
class Movie {
    private String title;
    private String genre;
    private String showDate;
    private double rating;

    /**
     * Constructor to initialize Movie object.
     *
     * @param title    The title of the movie.
     * @param genre    The genre of the movie.
     * @param showDate The show date of the movie.
     * @param rating   The rating of the movie.
     */
    public Movie(String title, String genre, String showDate, double rating) {
        this.title = title;
        this.genre = genre;
        this.showDate = showDate;
        this.rating = rating;
    }

    /**
     * Get the details of the movie.
     *
     * @return A string containing the movie details.
     */
    public String getDetails() {
        return "Title: " + title + ", Genre: " + genre + ", Show Date: " + showDate + ", Rating: " + rating;
    }

    /**
     * Check if the movie is suitable for kids.
     *
     * @return true if the movie is suitable for kids.
     */
    public boolean isSuitableForKids() {
        return !genre.equalsIgnoreCase("horror") && rating >= 5.0;
    }

    /**
     * Static method to find a movie by title.
     *
     * @param movies The list of movies.
     * @param title  The title of the movie to find.
     * @return The movie object if found, otherwise null.
     */
    public static Movie findMovieByTitle(ArrayList<Movie> movies, String title) {
        return movies.stream().filter(movie -> movie.title.equalsIgnoreCase(title)).findFirst().orElse(null);
    }

    /**
     * Static method to sort movies by title.
     *
     * @param movies The list of movies to sort.
     */
    public static void sortMoviesByTitle(ArrayList<Movie> movies) {
        movies.sort(Comparator.comparing(movie -> movie.title));
    }

    /**
     * Static method to sort movies by rating.
     *
     * @param movies The list of movies to sort.
     */
    public static void sortMoviesByRating(ArrayList<Movie> movies) {
        movies.sort(Comparator.comparingDouble(movie -> movie.rating));
    }

    /**
     * Static method to sort movies by show date.
     *
     * @param movies The list of movies to sort.
     */
    public static void sortMoviesByShowDate(ArrayList<Movie> movies) {
        movies.sort(Comparator.comparing(movie -> movie.showDate));
    }
}

/**
 * Class representing a cinema hall.
 */
class CinemaHall {
    private int hallNumber;
    private int capacity;
    private boolean isAvailable;

    /**
     * Constructor to initialize CinemaHall object.
     *
     * @param hallNumber  The hall number.
     * @param capacity    The capacity of the hall.
     * @param isAvailable The availability of the hall.
     */
    public CinemaHall(int hallNumber, int capacity, boolean isAvailable) {
        this.hallNumber = hallNumber;
        this.capacity = capacity;
        this.isAvailable = isAvailable;
    }

    /**
     * Reserve seats in the cinema hall.
     *
     * @param numSeat The number of seats to reserve.
     * @return true if the reservation is successful.
     */
    public boolean reserveSeat(int numSeat) {
        if (isAvailable && capacity >= numSeat) {
            capacity -= numSeat;
            if (capacity == 0) {
                isAvailable = false;
            }
            return true;
        }
        return false;
    }

    /**
     * Get hall information.
     *
     * @return A string with hall details.
     */
    public String getHallInfo() {
        return "Hall Number: " + hallNumber + ", Capacity: " + capacity + ", Available: " + isAvailable;
    }
}

/**
 * Cinema Management System class.
 */
public class CinemaManagementSystem {
    private ArrayList<Movie> movies;
    private ArrayList<CinemaHall> halls;
    private double ticketPrice = 10.0;
    private double snackPrice = 5.0;

    /**
     * Constructor to initialize the cinema system.
     */
    public CinemaManagementSystem() {
        movies = new ArrayList<>();
        halls = new ArrayList<>();
        initializeData();
    }

    /**
     * Method to initialize sample data for movies and halls.
     */
    private void initializeData() {
        movies.add(new Movie("Inside Out 2", "Animation", "2024-10-11", 10));
        movies.add(new Movie("Deadpool & Wolverine", "Action", "2024-10-12", 8.3));

        halls.add(new CinemaHall(1, 100, true));
        halls.add(new CinemaHall(2, 0, false));
        halls.add(new CinemaHall(3, 50, true));
    }

    /**
     * Show the main menu of the system.
     */
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\nChoose an option:");
            System.out.println("1. View available movies");
            System.out.println("2. Exit");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    viewAvailableMovies();
                    break;
                case 2:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        } while (option != 2);
        scanner.close();
    }

    /**
     * View the available movies in the cinema.
     */
    private void viewAvailableMovies() {
        System.out.println("Available Movies:");
        movies.forEach(movie -> System.out.println(movie.getDetails()));
        bookTickets();
    }

    /**
     * Book tickets for a movie.
     */
    private void bookTickets() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the movie title you want to book: ");
        String movieTitle = scanner.nextLine();
        Movie movie = Movie.findMovieByTitle(movies, movieTitle);

        if (movie != null) {
            System.out.print("Enter number of tickets to book: ");
            int numberOfTickets = scanner.nextInt();
            if (halls.get(0).reserveSeat(numberOfTickets)) {
                System.out.println("Tickets booked successfully!");
                bookSnacks(numberOfTickets);
            } else {
                System.out.println("Booking failed! Not enough seats available.");
            }
        } else {
            System.out.println("Movie not found.");
        }
    }

    /**
     * Book snacks for the user.
     *
     * @param numberOfTickets The number of tickets purchased.
     */
    private void bookSnacks(int numberOfTickets) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to add snacks? (yes/no): ");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            System.out.print("Enter snack name: ");
            String snackName = scanner.nextLine();
            System.out.println("Snack " + snackName + " added to your order.");
        } else {
            System.out.println("No snacks added.");
        }
    }
}
