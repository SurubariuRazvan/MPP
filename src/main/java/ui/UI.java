package ui;

import domain.User;
import service.AppService;
import service.AppServiceException;
import service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UI {
    private UserService userService;
    private AppService appService;

    public UI(UserService userService, AppService appService) {

        this.userService = userService;
        this.appService = appService;
    }

    private String input(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.println(prompt);
        return input.nextLine();
    }

    private void printMenu() {
        System.out.println("menu - prints this menu.\n" +
                "search - shows all the seats from a given trip, found by entering the destination and departure time.\n" +
                "reserve - reserves a trip, given the trip id, client name and seat number.\n" +
                "logout - logs you out of the application.\n");
    }

    private User login() {
        System.out.println("Type your credentials.");
        String username = input("Username:");
        String password = input("Password:");
        return userService.login(username, password);
    }

    private Map<String, Runnable> getCommands() {
        Map<String, Runnable> commands = new HashMap<>();
        commands.put("menu", this::printMenu);
        commands.put("search", this::search);
        commands.put("reserve", this::reserve);
        return commands;
    }


    public void run() {
        do {
            User user = login();
            while(user == null) {
                String command = input("Type exit to exit or anything to try again");
                if (command.equals("exit"))
                    break;
                user = login();
            }

            if (user != null) {
                Map<String, Runnable> commands = getCommands();
                showTrips();
                printMenu();
                String command = input("Type command:");
                while(!command.equals("logout")) {
                    if (commands.containsKey(command))
                        commands.get(command).run();
                    command = input("Type command:");
                }
            }
        } while(!input("Type exit to exit or anything to log in").equals("exit"));
    }

    private void search() {
        String destinationName = input("Destination name:");
        LocalDateTime departure = null;
        try {
            departure = LocalDateTime.parse(input("Departure time(dd/MM/yyyy HH:mm):"), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        } catch (DateTimeParseException e) {
            System.out.println("Wrong date format");
        }
        if (departure != null)
            for(var a : appService.search(destinationName, departure))
                System.out.println(a.toString());
    }

    private void reserve() {
        int tripID = Integer.parseInt(input("Trip ID:"));
        String clientName = input("Client name:");
        int seatNumber = Integer.parseInt(input("Reserved seat:"));

        try {
            appService.reserve(tripID, clientName, seatNumber);
            System.out.println("Booked successfully");
        } catch (AppServiceException e) {
            System.out.println("Error when booking: " + e.getMessage());
        }
    }

    private void showTrips() {
        for(var a:appService.showTrips())
            System.out.println(a);
    }
}
