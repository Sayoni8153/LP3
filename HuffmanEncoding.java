import java.util.*;

// Node class for the Huffman tree
class HuffmanNode {
    char character;              // Character stored in the node (only for leaves)
    int frequency;               // Frequency of the character or sum for internal nodes
    HuffmanNode left;            // Left child
    HuffmanNode right;           // Right child

    // Constructor for leaf nodes
    HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    // Constructor for internal nodes
    HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.character = '\0';  // Special value for internal nodes
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
}

// Comparator for priority queue (min-heap based on frequency)
class FrequencyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode a, HuffmanNode b) {
        return a.frequency - b.frequency;
    }
}

public class HuffmanEncoding {

    // Method to build the Huffman Tree from character frequencies
    public static HuffmanNode buildHuffmanTree(Map<Character, Integer> frequencyMap) {
        // Create a priority queue (min-heap) for Huffman nodes
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(new FrequencyComparator());

        // Create leaf nodes for each character and add to the queue
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Build the tree by combining the two lowest frequency nodes repeatedly
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();               // Remove lowest frequency node
            HuffmanNode right = pq.poll();              // Remove second lowest

            int sumFreq = left.frequency + right.frequency;  // Sum of their frequencies
            HuffmanNode parent = new HuffmanNode(sumFreq, left, right);  // Create parent node

            pq.add(parent);  // Add the parent node back into the priority queue
        }

        return pq.poll(); // Return the root node of the Huffman Tree
    }

    // Recursive method to generate Huffman codes from the tree
    public static void generateCodes(HuffmanNode root, String code, Map<Character, String> huffmanCodeMap) {
        if (root == null) return;  // Base case: empty node

        // If it's a leaf node, assign the code to the character
        if (root.left == null && root.right == null && root.character != '\0') {
            huffmanCodeMap.put(root.character, code);  // Save the code
        }

        // Traverse left with '0' and right with '1'
        generateCodes(root.left, code + "0", huffmanCodeMap);
        generateCodes(root.right, code + "1", huffmanCodeMap);
    }

    // Print the generated Huffman codes
    public static void printHuffmanCodes(Map<Character, String> huffmanCodeMap) {
        System.out.println("Character\tHuffman Code");
        for (Map.Entry<Character, String> entry : huffmanCodeMap.entrySet()) {
            System.out.println(entry.getKey() + "\t\t" + entry.getValue());
        }
    }

    public static void main(String[] args) {
        // Sample input string to encode
        String text = "huffman encoding in java";

        // Step 1: Count frequency of each character in the text
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            if (c == ' ') continue;  // Optional: skip spaces
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1); // Increment frequency
        }

        // Step 2: Build Huffman Tree using the frequency map
        HuffmanNode root = buildHuffmanTree(frequencyMap);

        // Step 3: Generate Huffman codes from the tree
        Map<Character, String> huffmanCodeMap = new HashMap<>();
        generateCodes(root, "", huffmanCodeMap); // Start with empty code

        // Step 4: Display the Huffman codes
        printHuffmanCodes(huffmanCodeMap);

        // Step 5 (Optional): Encode the original text using the Huffman codes
        StringBuilder encoded = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c == ' ') continue;  // Skip spaces
            encoded.append(huffmanCodeMap.get(c)); // Append Huffman code
        }

        // Print the encoded binary string
        System.out.println("\nEncoded Text:");
        System.out.println(encoded.toString());
    }
}
