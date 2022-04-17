package ui;

import model.Event;
import model.EventLog;
import model.Flight;
import model.ListOfFlights;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Representing Class FlightManager for managing flights

public class FlightManager implements ActionListener, ListSelectionListener, WindowListener {

    Scanner sc;
    ListOfFlights listOfFlights = new ListOfFlights();
    String successMessage;
    Flight selectedFlight;

    private static final String JSON_STORE = "./data/listOfFlights.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    JFrame frame;
    JPanel panel;

    JScrollPane listScrollPane;
    JList<Flight> list;
    DefaultListModel<Flight> model;

    JPanel buttonPane;
    JButton saveButton;
    JButton loadButton;
    JButton addFlightButton;
    JButton addThisFlightButton;
    JButton removeFlightButton;
    JButton selectFlightButton;
    JButton removeSelectedFlightButton;
    JButton mainMenuButton;

    JButton changeStatusButton;
    JButton scheduleFlightButton;
    JButton deactivateButton;
    JButton flyButton;
    JButton landButton;
    JButton setOccupancyButton;
    JButton setThisOccupancyButton;

    ImageIcon activeFlightImage = new ImageIcon("data/FlightImage.png");
    ImageIcon takingOffImage = new ImageIcon("data/FlightTakingOff.png");
    ImageIcon landingImage = new ImageIcon("data/FlightLanding.png");

    JLabel flightImage;
    JLabel takeOffOrLand;

    JLabel successLabel;

    JTextField flightNumberField;
    JTextField flightCapacityField;

    JTextField departureDestinationField;
    JTextField arrivalDestinationField;
    JTextField departureDateField;
    JTextField arrivalDateField;
    JTextField departureTimeField;
    JTextField arrivalTimeField;

    JTextField occupancyField;

    JLabel flightNumberLabel;
    JLabel flightCapacityLabel;
    JLabel flightStatusLabel;
    JLabel departureDestinationLabel;
    JLabel arrivalDestinationLabel;
    JLabel departureDateLabel;
    JLabel arrivalDateLabel;
    JLabel departureTimeLabel;
    JLabel arrivalTimeLabel;
    JLabel occupancyLabel;
    JLabel flyingLabel;

    JPanel flightLabels;

    //EventLog theLog;

    /*
    EFFECTS: Creates new list of flights, sets up file reader and writer, starts up the GUI
     */
    public FlightManager() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        //theLog.getInstance();
        sc = new Scanner(System.in);
        displayMenu();
    }

    /*
    MODIFIES: this
    EFFECTS: Sets up the frame in the GUI
     */
    private void guiSetUp() {

        frame = new JFrame("Flight Manager");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(this);
        frame.setSize(550, 800);
        panel = new JPanel();

        listScrollPaneSetUp();
        buttonPaneSetUp();
        successLabel = new JLabel();
        flightImage = new JLabel();
        takeOffOrLand = new JLabel();
        flightImage.setIcon(activeFlightImage);
        fieldsSetUp();

        panel.add(listScrollPane);
        panel.add(buttonPane);
        panel.add(successLabel);
        panel.add(flightImage);
        frame.add(panel);
        frame.setVisible(true);

        //buttonPane.setBackground(Color.BLACK);
        //panel.setBackground(Color.);

    }

    /*
    MODIFIES: this
    EFFECTS: Sets up the list scroll pane in the GUI
     */
    private void listScrollPaneSetUp() {
        list = new JList<>();
        model = new DefaultListModel<>();
        list.setModel(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(20);
        listScrollPane = new JScrollPane(list);
        list.addListSelectionListener(this);
        updateGuiListModel();
        selectedFlight = null;
    }

    /*
    MODIFIES: this
    EFFECTS: Sets up the button pane in the GUI
     */
    private void buttonPaneSetUp() {
        createButtons();
        buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(addFlightButton);
        buttonPane.add(removeFlightButton);
        buttonPane.add(saveButton);
        buttonPane.add(loadButton);
        buttonPane.add(selectFlightButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    /*
    MODIFIES: this
    EFFECTS: Creates all the buttons in the GUI
     */
    private void createButtons() {
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        addFlightButton = new JButton("Add Flight");
        addThisFlightButton = new JButton("Add This Flight");
        removeFlightButton = new JButton("Remove Flight");
        removeSelectedFlightButton = new JButton("Remove This Flight");
        selectFlightButton = new JButton("Select Flight");
        mainMenuButton = new JButton("Main Menu");
        changeStatusButton = new JButton("Change Status");
        scheduleFlightButton = new JButton("Schedule Flight");
        deactivateButton = new JButton("Deactivate Flight");
        flyButton = new JButton("Fly Flight");
        landButton = new JButton("Land Flight");
        setOccupancyButton = new JButton("Set Occupancy");
        setThisOccupancyButton = new JButton("Set This Occupancy");

        actionsSetUp();
        actionListenersSetUp();
    }

    /*
    MODIFIES: this
    EFFECTS: Sets action commands for all the buttons in the GUI
     */
    private void actionsSetUp() {
        saveButton.setActionCommand("Save");
        loadButton.setActionCommand("Load");
        addFlightButton.setActionCommand("Add Flight");
        addThisFlightButton.setActionCommand("Add This Flight");
        removeFlightButton.setActionCommand("Remove Flight");
        removeSelectedFlightButton.setActionCommand("Remove This Flight");
        selectFlightButton.setActionCommand("Select Flight");
        mainMenuButton.setActionCommand("Main Menu");
        changeStatusButton.setActionCommand("Change Status");
        scheduleFlightButton.setActionCommand("Schedule Flight");
        deactivateButton.setActionCommand("Deactivate Flight");
        flyButton.setActionCommand("Fly Flight");
        landButton.setActionCommand("Land Flight");
        setOccupancyButton.setActionCommand("Set Occupancy");
        setThisOccupancyButton.setActionCommand("Set This Occupancy");
    }

    /*
    MODIFIES: this
    EFFECTS: Sets action listeners for all the buttons in the GUI
     */
    private void actionListenersSetUp() {
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        addFlightButton.addActionListener(this);
        addThisFlightButton.addActionListener(this);
        removeFlightButton.addActionListener(this);
        removeSelectedFlightButton.addActionListener(this);
        selectFlightButton.addActionListener(this);
        mainMenuButton.addActionListener(this);
        changeStatusButton.addActionListener(this);
        scheduleFlightButton.addActionListener(this);
        deactivateButton.addActionListener(this);
        flyButton.addActionListener(this);
        landButton.addActionListener(this);
        setOccupancyButton.addActionListener(this);
        setThisOccupancyButton.addActionListener(this);
    }

    /*
    MODIFIES: this
    EFFECTS: Sets up all the labels in the GUI
     */
    public void flightLabelsSetUp() {
        flightLabels.setLayout(new GridLayout(11,1));
        flightNumberLabel = new JLabel();
        flightCapacityLabel = new JLabel();
        flightStatusLabel = new JLabel();
        departureDestinationLabel = new JLabel();
        arrivalDestinationLabel = new JLabel();
        departureDateLabel = new JLabel();
        arrivalDateLabel = new JLabel();
        departureTimeLabel = new JLabel();
        arrivalTimeLabel = new JLabel();
        occupancyLabel = new JLabel();
        flyingLabel = new JLabel();
        flightLabels.add(flightNumberLabel);
        flightLabels.add(flightCapacityLabel);
        flightLabels.add(flightStatusLabel);
        flightLabels.add(departureDestinationLabel);
        flightLabels.add(arrivalDestinationLabel);
        flightLabels.add(departureDateLabel);
        flightLabels.add(arrivalDateLabel);
        flightLabels.add(departureTimeLabel);
        flightLabels.add(arrivalTimeLabel);
        flightLabels.add(occupancyLabel);
        flightLabels.add(flyingLabel);
    }



    /*
    MODIFIES: this
    EFFECTS: Displays the starting page and allows user to open the menu by entering password

    private void startApp() {
        System.out.println("HELLO, FLIGHT MANAGER!!");
        System.out.println("Enter password to open menu with list of flights");
        System.out.println("[Hint: password is 1234]");
        int input = sc.nextInt();
        if (input == 1234) {
            displayMenu();
        } else {
            System.out.println("Incorrect password");
            startApp();
        }
    }

     */

    /*
    MODIFIES: this
    EFFECTS: Sets up all the fields in the GUI
     */
    private void fieldsSetUp() {
        flightNumberField = new JTextField();
        flightCapacityField = new JTextField();
        flightNumberField.setPreferredSize(new Dimension(300, 40));
        flightCapacityField.setPreferredSize(new Dimension(300, 40));

        departureDestinationField = new JTextField("Enter Departure Destination: ");
        arrivalDestinationField = new JTextField("Enter Arrival Destination: ");
        departureDateField = new JTextField("Enter Departure Date: ");
        arrivalDateField = new JTextField("Enter Arrival Date: ");
        departureTimeField = new JTextField("Enter Departure Time: ");
        arrivalTimeField = new JTextField("Enter Arrival Time: ");
        occupancyField = new JTextField("Enter occupancy (integer)");

        departureDestinationField.setPreferredSize(new Dimension(300, 40));
        arrivalDestinationField.setPreferredSize(new Dimension(300, 40));
        departureDateField.setPreferredSize(new Dimension(300, 40));
        arrivalDateField.setPreferredSize(new Dimension(300, 40));
        departureTimeField.setPreferredSize(new Dimension(300, 40));
        arrivalTimeField.setPreferredSize(new Dimension(300, 40));
        occupancyField.setPreferredSize(new Dimension(300, 40));
    }

    /*
    MODIFIES: this
    EFFECTS: Displays the GUI menu with the list of flights.
     */
    public void displayMenu() {

        guiSetUp();
        /*
        System.out.println("\f");
        System.out.println("MENU:");

        displayAllFlights();

        System.out.println("Enter a choice between 1 to " + listOfFlights.size()
                + " to view a flight's details or change its status");
        System.out.println("Enter -1 to add a flight");
        System.out.println("Enter -2 to remove a flight");
        System.out.println("Enter -3 to save list of flights");
        System.out.println("Enter -4 to load list of flights");
        System.out.println("Enter -5 to end the program");
        operateMenu();
         */
    }

    /*
    MODIFIES: this
    EFFECTS: Operates on user input and calls appropriate method to do task

    private void operateMenu() {

        int input = sc.nextInt();

        if (input == -1) {
            addFlight();
        } else if (input == -2) {
            removeFlight();
        } else if (input == -3) {
            saveListOfFlights();
        } else if (input == -4) {
            loadListOfFlights();
        } else if (input == -5) {
            endProgram();
        } else if (input >= 1 && input <= listOfFlights.size()) {
            flightDetails(listOfFlights.getFlight(input));
        } else {
            System.out.println("Invalid input. Please enter again.");
            displayMenu();
        }
    }
     */



    /*
    EFFECTS: Displays the list of all flights with their status

    private void displayAllFlights() {
        if (listOfFlights.size() == 0) {
            System.out.println("There are no flights to display");
        } else {
            Flight flightObj;
            int i;
            for (i = 1; i <= listOfFlights.size(); i++) {
                flightObj = listOfFlights.getFlight(i);
                System.out.println(i + ". " + flightObj.toString());

            }
        }
    }

     */

    /*
    REQUIRES: the flight's number should not match with another flight from the list
    MODIFIES: this
    EFFECTS: creates a new flight using strings entered into the fields and adds it to the list
     */
    private void addThisFlight() {

        String flightNumber = flightNumberField.getText();
        int capacity = Integer.parseInt(flightCapacityField.getText());

        Flight flight = new Flight(flightNumber, capacity);
        listOfFlights.addFlight(flight);
        updateGuiListModel();
        successMessage = "The Flight has been added successfully.";
        frame.dispose();
        displayMenu();
    }

    /*
    MODIFIES: this
    EFFECTS: Displays GUI for flight addition
     */
    private void addFlight() {
        frame.setSize(350, 800);
        panel.remove(flightImage);
        buttonPane.removeAll();
        buttonPane.add(addThisFlightButton);
        buttonPane.add(mainMenuButton);
        panel.add(flightNumberField);
        panel.add(flightCapacityField);
        successMessage = "Enter flight number and capacity in the fields below";
    }

    /*
    MODIFIES: this
    EFFECTS: Displays GUI for flight removal
     */
    private void removeFlight() {
        frame.setSize(350, 800);
        panel.remove(flightImage);
        buttonPane.removeAll();
        buttonPane.add(removeSelectedFlightButton);
        buttonPane.add(mainMenuButton);
        successMessage = "Select a flight and press button to remove it";

    }

    /*
    MODIFIES: this
    EFFECTS: Removes this flight from the list
     */
    private void removeThisFlight(Flight flight) {
        listOfFlights.removeFlight(flight);
        updateGuiListModel();
        successMessage = "The Flight has been removed if it was present.";
        frame.dispose();
    }



    /*
    EFFECTS: Prints a message and terminates the program

    private void endProgram() {
        System.out.println("The program has ended. Thank You!");
    }
     */


    /*
    REQUIRES: the flight object should not be null
    MODIFIES: this
    EFFECTS: Displays all flight details in a GUI. Allows user to either change flight status or go back to main menu.
     */
    private void flightDetails(Flight flight) {
        frame.dispose();
        frame = new JFrame("Flight Details");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(this);
        frame.setSize(300, 800);
        panel = new JPanel();
        flightLabels = new JPanel();
        buttonPane = new JPanel();

        flightLabelsSetUp();
        updateFlightLabels(flight);
        buttonPane.add(mainMenuButton);
        buttonPane.add(changeStatusButton);

        panel.add(flightLabels);
        panel.add(buttonPane);
        frame.add(panel);
        frame.setVisible(true);
    }

    /*
    REQUIRES: the flight object should not be null
    MODIFIES: this
    EFFECTS: Sets all flight detail labels according to this flight's details
     */
    public void updateFlightLabels(Flight flight) {
        flightNumberLabel.setText("Flight Number: " + flight.getFlightNumber());
        flightCapacityLabel.setText("Flight Capacity: " + flight.getCapacity());
        if (flight.isActive()) {
            flightStatusLabel.setText("Flight Status: SCHEDULED \n");
            departureDestinationLabel.setText("Departure Destination: " + flight.getDestinationFrom() + "\n");
            arrivalDestinationLabel.setText("Arrival Destination: " + flight.getDestinationTo() + "\n");
            departureDateLabel.setText("Departure Date: " + flight.getDepartureDate() + "\n");
            arrivalDateLabel.setText("Arrival Date: " + flight.getArrivalDate() + "\n");
            departureTimeLabel.setText("Departure Time: " + flight.getDepartureTime() + "\n");
            arrivalTimeLabel.setText("Arrival Time: " + flight.getArrivalTime() + "\n");
            occupancyLabel.setText("Occupancy: " + flight.getOccupancy() + "\n");
            flyingLabel.setText("Currently flying?: " + flight.isFlying() + "\n");
        } else {
            flightStatusLabel.setText("Flight Status: INACTIVE");
            departureDestinationLabel.setText("");
            arrivalDestinationLabel.setText("");
            departureDateLabel.setText("");
            arrivalDateLabel.setText("");
            departureTimeLabel.setText("");
            arrivalTimeLabel.setText("");
            occupancyLabel.setText("");
            flyingLabel.setText("");
        }
    }

    /*
    REQUIRES: the flight object should not be null
    MODIFIES: this
    EFFECTS: Changes the flight's status differently depending on whether it was active or not.
     */
    private void changeFlightStatus(Flight flight) {
        if (flight.isActive()) {
            editActiveFlightStatus();
        } else {
            activateFlight();
        }
        /*
        System.out.println("The given task has been completed");
        System.out.println("Enter 0 to go back to main menu or any other integer to go back to flight details.");
        if (sc.nextInt() == 0) {
            displayMenu();
        } else {
            flightDetails(flight);
        }

         */
    }

    /*
    REQUIRES: the flight object is not null and the flight is inactive
    MODIFIES: this
    EFFECTS: GUI which allows user to activate flight and set schedule and destinations.
     */
    private void activateFlight() {
        frame.dispose();

        frame = new JFrame("Flight Scheduler");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(this);
        frame.setSize(400, 600);
        panel = new JPanel();
        buttonPane = new JPanel();

        panel.add(departureDestinationField);
        panel.add(arrivalDestinationField);
        panel.add(departureDateField);
        panel.add(arrivalDateField);
        panel.add(departureTimeField);
        panel.add(arrivalTimeField);

        buttonPane.add(scheduleFlightButton);
        buttonPane.add(mainMenuButton);

        panel.add(buttonPane);
        frame.add(panel);
        frame.setVisible(true);
    }

    /*
    REQUIRES: the flight object is not null and the flight is inactive
    MODIFIES: this
    EFFECTS: Gets data from the fields to schedule the flight
     */
    public void scheduleFlight(Flight flight) {
        String destinationFrom = departureDestinationField.getText();
        String destinationTo = arrivalDestinationField.getText();
        String departureDate = departureDateField.getText();
        String arrivalDate = arrivalDateField.getText();
        String departureTime = departureTimeField.getText();
        String arrivalTime = arrivalTimeField.getText();

        flight.scheduleFlight(destinationFrom, destinationTo, departureTime, arrivalTime,
                departureDate, arrivalDate);
        successMessage = "That flight has now been scheduled";
        frame.dispose();
        displayMenu();
    }

    /*
    MODIFIES: this
    EFFECTS: Displays GUI to allow changing some details about the active flight, deactivate it or return to Main Menu
     */
    private void editActiveFlightStatus() {
        frame.dispose();

        frame = new JFrame("Active Flight Editor");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(this);
        frame.setSize(600, 400);
        panel = new JPanel();
        buttonPane = new JPanel();

        buttonPane.add(flyButton);
        buttonPane.add(landButton);
        buttonPane.add(deactivateButton);
        buttonPane.add(setOccupancyButton);
        buttonPane.add(mainMenuButton);

        if (selectedFlight.isFlying()) {
            flyButton.setVisible(false);
            landButton.setVisible(true);
        } else {
            flyButton.setVisible(true);
            landButton.setVisible(false);
        }
        panel.add(buttonPane);
        panel.add(takeOffOrLand);
        frame.add(panel);
        frame.setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: saves the list of flights to file
    private void saveListOfFlights() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfFlights);
            jsonWriter.close();
            successMessage = "Successfully saved to " + JSON_STORE;
        } catch (FileNotFoundException e) {
            successMessage = "Unable to write to file: " + JSON_STORE;
        }
    }


    // MODIFIES: this
    // EFFECTS: loads the list of flights from file
    private void loadListOfFlights() {
        try {
            listOfFlights = jsonReader.read();
            updateGuiListModel();
            successMessage = "Successfully loaded from " + JSON_STORE;
        } catch (IOException e) {
            successMessage = "Unable to read from file: " + JSON_STORE;
        }
    }

    /*
    MODIFIES: this
    EFFECTS: Action listener for buttons, calls the right functions when buttons are pressed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Save")) {
            saveListOfFlights();
        } else if (e.getActionCommand().equals("Load")) {
            loadListOfFlights();
        } else if (e.getActionCommand().equals("Add Flight")) {
            addFlight();
        } else if (e.getActionCommand().equals("Add This Flight")) {
            addThisFlight();
        } else if (e.getActionCommand().equals("Remove Flight")) {
            removeFlight();
        } else if (e.getActionCommand().equals("Remove This Flight")) {
            if (selectedFlight != null) {
                removeThisFlight(selectedFlight);
            } else {
                successMessage = "No flight was removed";
            }
            displayMenu();
        } else if (e.getActionCommand().equals("Main Menu")) {
            successMessage = "";
            frame.dispose();
            displayMenu();
        }
        actionPerformedForFlightStatus(e);
    }

    /*
    MODIFIES: this
    EFFECTS: Action listener for buttons involving viewing or editing details
     */
    public void actionPerformedForFlightStatus(ActionEvent e) {
        if (e.getActionCommand().equals("Select Flight")) {
            if (selectedFlight != null) {
                flightDetails(selectedFlight);
            } else {
                successMessage = "No flight selected";
            }
        } else if (e.getActionCommand().equals("Change Status")) {
            changeFlightStatus(selectedFlight);
        } else if (e.getActionCommand().equals("Schedule Flight")) {
            scheduleFlight(selectedFlight);
        } else if (e.getActionCommand().equals("Set Occupancy")) {
            occupancySetter();
        } else if (e.getActionCommand().equals("Set This Occupancy")) {
            setThisOccupancy();
        }

        moreActionsPerformed(e);
    }

    /*
    MODIFIES: this
    EFFECTS: Action listener for some more buttons
     */
    public void moreActionsPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Deactivate Flight")) {
            successMessage = "That flight has now been deactivated";
            selectedFlight.deactivateFlight();
            frame.dispose();
            displayMenu();
        } else if (e.getActionCommand().equals("Fly Flight")) {
            successMessage = "That flight has now taken off";
            selectedFlight.fly();
            takeOffOrLand.setText("Take Off!");
            takeOffOrLand.setIcon(takingOffImage);
            //panel.add(takeOffOrLand);
            //frame.dispose();
            //displayMenu();
            editActiveFlightStatus();
        } else if (e.getActionCommand().equals("Land Flight")) {
            successMessage = "That flight has now landed";
            selectedFlight.land();
            takeOffOrLand.setText("Landing!");
            takeOffOrLand.setIcon(landingImage);
            //panel.add(takeOffOrLand);
            //frame.dispose();
            //displayMenu();
            editActiveFlightStatus();
        }
        successLabel.setText(successMessage);
    }

    /*
    MODIFIES: this
    EFFECTS: Displays GUI to set occupancy
     */
    private void occupancySetter() {
        frame.dispose();

        frame = new JFrame("Occupancy Setter");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(this);
        frame.setSize(400, 600);
        panel = new JPanel();
        buttonPane = new JPanel();

        panel.add(occupancyField);
        buttonPane.add(setThisOccupancyButton);
        buttonPane.add(mainMenuButton);

        panel.add(buttonPane);
        frame.add(panel);
        frame.setVisible(true);
    }

    /*
    MODIFIES: this
    EFFECTS: Gets data from the fields and sets occupancy
     */
    private void setThisOccupancy() {
        selectedFlight.setOccupancy(Integer.parseInt(occupancyField.getText()));
        successMessage = "Occupancy was set successfully";
        frame.dispose();
        displayMenu();
    }

    /*
    MODIFIES: this
    EFFECTS: Action listener for flight selection, sets selectedFlight to the flight currently selected
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        selectedFlight = list.getSelectedValue();
        //flightDetails(flight);
        //return flight;
    }

    /*
    MODIFIES: this
    EFFECTS: Updates the JList model to represent the changed list of flights
     */
    private void updateGuiListModel() {
        for (int i = model.size() - 1; i >= 0; i--) {
            model.removeElementAt(0);
        }
        for (Flight flight: listOfFlights.getListOfFlights()) {
            model.addElement(flight);
        }
    }


    /*
    EFFECTS: Prints event log and quits program
     */
    public void printLog() {
        System.out.println("Event Log: ");
        for (Event e : EventLog.getInstance()) {
            System.out.println(e.toString());
        }
        System.out.println("Thank You!");
        System.exit(0);
    }


    /*
    EFFECTS: When the window is closed, calls function to print event log and quit program
     */
    @Override
    public void windowClosing(WindowEvent e) {
        printLog();
    }

    /*
    EFFECTS: None. Just here because compulsory to include
     */
    @Override
    public void windowOpened(WindowEvent e) {
    }

    /*
    EFFECTS: None. Just here because compulsory to include
     */
    @Override
    public void windowClosed(WindowEvent e) {
    }

    /*
    EFFECTS: None. Just here because compulsory to include
     */
    @Override
    public void windowIconified(WindowEvent e) {
    }

    /*
    EFFECTS: None. Just here because compulsory to include
     */
    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    /*
    EFFECTS: None. Just here because compulsory to include
     */
    @Override
    public void windowActivated(WindowEvent e) {
    }

    /*
    EFFECTS: None. Just here because compulsory to include
    */
    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}

