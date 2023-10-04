package javaproject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Medication {
    private String name;
    private double price;
    private int quantity;

    public Medication(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity(int amount) {
        if (amount <= quantity) {
            quantity -= amount;
        }
    }
}

public class OnlinePharmacyOrderingSystem {
    private static List<Medication> availableMedications = new ArrayList<>();
    private static Map<Medication, Integer> cart = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeMedications();

        while (true) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    displayMedications();
                    break;
                case 2:
                    addToCart();
                    break;
                case 3:
                    viewCart();
                    break;
                case 4:
                    completeOrder();
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static void initializeMedications() {
        availableMedications.add(new Medication("Medicine A", 10.99, 50));
        availableMedications.add(new Medication("Medicine B", 15.49, 30));
        availableMedications.add(new Medication("Medicine C", 7.99, 100));
        availableMedications.add(new Medication("Medicine D", 8.79, 20));
        availableMedications.add(new Medication("Medicine E", 12.99, 40));
        // Add more medications as needed
    }

    private static void displayMenu() {
        System.out.println("Online Pharmacy Ordering System");
        System.out.println("1. Browse Medications");
        System.out.println("2. Add to Cart");
        System.out.println("3. View Cart");
        System.out.println("4. Complete Order");
    }

    private static int getUserChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private static void displayMedications() {
        System.out.println("Available Medications:");
        for (int i = 0; i < availableMedications.size(); i++) {
            Medication medication = availableMedications.get(i);
            System.out.println((i + 1) + ". " + medication.getName() +
                    " - Price: $" + medication.getPrice() +
                    " - Quantity: " + medication.getQuantity());
        }
    }

    private static void addToCart() {
        displayMedications();
        System.out.print("Enter the number of the medication to add to the cart: ");
        int choice = scanner.nextInt();
        if (choice >= 1 && choice <= availableMedications.size()) {
            Medication selectedMedication = availableMedications.get(choice - 1);
            System.out.print("Enter quantity to add to the cart: ");
            int quantity = scanner.nextInt();
            if (quantity >= 1 && quantity <= selectedMedication.getQuantity()) {
                cart.put(selectedMedication, quantity);
                selectedMedication.reduceQuantity(quantity);
                System.out.println(quantity + " " + selectedMedication.getName() + "(s) added to the cart.");
            } else {
                System.out.println("Invalid quantity. Please enter a valid quantity.");
            }
        } else {
            System.out.println("Invalid medication selection.");
        }
    }

    private static void viewCart() {
        System.out.println("Shopping Cart:");
        for (Map.Entry<Medication, Integer> entry : cart.entrySet()) {
            Medication medication = entry.getKey();
            int quantity = entry.getValue();
            double cost = medication.getPrice() * quantity;
            System.out.println(medication.getName() + " - Quantity: " + quantity + " - Cost: $" + cost);
        }
    }

    private static void completeOrder() {
        System.out.println("Order Summary:");
        double totalCost = 0;
        for (Map.Entry<Medication, Integer> entry : cart.entrySet()) {
            Medication medication = entry.getKey();
            int quantity = entry.getValue();
            double cost = medication.getPrice() * quantity;
            System.out.println(medication.getName() + " - Quantity: " + quantity + " - Cost: $" + cost);
            totalCost += cost;
        }
        System.out.println("Total Cost: $" + totalCost);
        System.out.println("Thank you for your order!");
    }
}
