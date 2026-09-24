import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/*
 * Program to collect and store cards to assist users in finding
 * all the cards of a given symbol using the Java Collection interface.
 */
class Card {
    private String symbol;
    private int number;

    // Constructor
    public Card(String symbol, int number) {
        this.symbol = symbol;
        this.number = number;
    }

    // Getters and Setters
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Symbol: " + symbol + ", Number: " + number;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return number == card.number && symbol.equalsIgnoreCase(card.symbol);
    }

    @Override
    public int hashCode() {
        return symbol.toLowerCase().hashCode() * 31 + Integer.hashCode(number);
    }
}

public class Cards {

    // Method to add a card to the collection
    private static void addCard(Collection<Card> cards, Scanner sc) {
        System.out.print("Enter Card Symbol (e.g., Hearts, Spades, Diamonds, Clubs or S, H, D, C): ");
        String symbol = sc.nextLine().trim();

        System.out.print("Enter Card Number (e.g., 1 to 13): ");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid input! Card number must be an integer.");
            sc.nextLine(); // clear buffer
            return;
        }
        int number = sc.nextInt();
        sc.nextLine(); // consume newline

        cards.add(new Card(symbol, number));
        System.out.println("Card added successfully!");
    }

    // Method to find all cards for a given symbol
    private static void findCardsBySymbol(Collection<Card> cards, Scanner sc) {
        if (cards.isEmpty()) {
            System.out.println("No cards in the collection yet. Please add some cards first.");
            return;
        }

        System.out.print("Enter the symbol to search: ");
        String searchSymbol = sc.nextLine().trim();

        Collection<Card> matchingCards = new ArrayList<>();
        int sum = 0;

        for (Card card : cards) {
            if (card.getSymbol().equalsIgnoreCase(searchSymbol)) {
                matchingCards.add(card);
                sum += card.getNumber();
            }
        }

        if (matchingCards.isEmpty()) {
            System.out.println("No cards found with symbol '" + searchSymbol + "'.");
        } else {
            System.out.println("\n--- Cards with Symbol '" + searchSymbol + "' ---");
            for (Card card : matchingCards) {
                System.out.println(card);
            }
            System.out.println("Number of cards : " + matchingCards.size());
            System.out.println("Sum of Numbers  : " + sum);
        }
    }

    // Method to display all distinct symbols and all cards
    private static void displayAllCards(Collection<Card> cards) {
        if (cards.isEmpty()) {
            System.out.println("No cards to display.");
            return;
        }

        // Using TreeSet to maintain distinct symbols in sorted order
        Set<String> distinctSymbols = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        for (Card card : cards) {
            distinctSymbols.add(card.getSymbol());
        }

        System.out.println("\nDistinct Symbols are: " + distinctSymbols);
        System.out.println("========== All Cards in Collection ==========");
        for (Card card : cards) {
            System.out.println(card);
        }
        System.out.println("Total cards in collection: " + cards.size());
    }

    public static void main(String[] args) {
        // Using Collection interface to store cards
        Collection<Card> cardCollection = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\n=== Card Collection System ===");
            System.out.println("1. Add Card");
            System.out.println("2. Find Cards by Symbol");
            System.out.println("3. Display All Cards & Distinct Symbols");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.nextLine(); // clear invalid input
                continue;
            }

            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addCard(cardCollection, sc);
                    break;
                case 2:
                    findCardsBySymbol(cardCollection, sc);
                    break;
                case 3:
                    displayAllCards(cardCollection);
                    break;
                case 4:
                    System.out.println("Exiting Card Collection System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select an option between 1 and 4.");
            }
        } while (choice != 4);

        sc.close();
    }
}
