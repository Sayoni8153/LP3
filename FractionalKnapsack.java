import java.util.Arrays;             // Import Arrays utility for sorting
import java.util.Comparator;         // Import Comparator interface for custom sorting

// Class to represent an item with value and weight
class Item {
    int value, weight;               // Declare variables for value and weight

    // Constructor to initialize an item with given value and weight
    public Item(int value, int weight) {
        this.value = value;          // Set the value
        this.weight = weight;        // Set the weight
    }

    // Method to calculate and return the value-to-weight ratio
    public double getRatio() {
        return (double) value / weight;  // Return the ratio as a double
    }
}

public class FractionalKnapsack {

    // Method to calculate the maximum value for the knapsack of given capacity
    public static double fractionalKnapsack(int capacity, Item[] items) {
        // Sort the items array in descending order based on value-to-weight ratio
        Arrays.sort(items, Comparator.comparingDouble(Item::getRatio).reversed());

        double totalValue = 0.0;           // Initialize total value accumulated
        int remainingCapacity = capacity; // Initialize remaining capacity of the knapsack

        // Loop through each item in sorted order
        for (Item item : items) {
            // If the entire item fits in the remaining capacity
            if (item.weight <= remainingCapacity) {
                remainingCapacity -= item.weight;  // Decrease remaining capacity
                totalValue += item.value;           // Add full item value to total
            } else {
                // Item does not fit completely, take fractional part
                double fraction = (double) remainingCapacity / item.weight; // Calculate fraction
                totalValue += item.value * fraction;                        // Add fractional value
                break;   // Knapsack is full, stop processing further items
            }
        }

        return totalValue;  // Return the maximum total value accumulated
    }

    // Main method - program execution starts here
    public static void main(String[] args) {
        // Define an array of items with specific values and weights
        Item[] items = {
            new Item(60, 10),   // Item with value 60 and weight 10
            new Item(100, 20),  // Item with value 100 and weight 20
            new Item(120, 30)   // Item with value 120 and weight 30
        };

        int capacity = 50;  // Define maximum capacity of knapsack

        // Call the fractionalKnapsack method and store the result
        double maxValue = fractionalKnapsack(capacity, items);

        // Print the maximum value that can be carried in the knapsack
        System.out.println("Maximum value in Knapsack = " + maxValue);
    }
}

