// 5.3
import java.io.*;
import java.util.*;

public class HashCollisions {
    private static final int TABLE_SIZE = 10007; // use prime number for the table size
    private static final int DOUBLE_HASH_PRIME = 9973; // prime number less than TABLE_SIZE

    // linear probing
    private static int linearProbing(int[] table, int key) {
        int hash = key % TABLE_SIZE;
        int collisions = 0;

        while (table[hash] != -1) {
            collisions++;
            hash = (hash + 1) % TABLE_SIZE;
        }

        table[hash] = key;
        return collisions;
    }

    // quadratic probing
    private static int quadraticProbing(int[] table, int key) {
        int hash = key % TABLE_SIZE;
        int collisions = 0;
        int i = 1;

        while (table[hash] != -1) {
            collisions++;
            hash = (hash + i * i) % TABLE_SIZE;
            i++;
        }

        table[hash] = key;
        return collisions;
    }

    // Double hashing
    private static int doubleHashing(int[] table, int key) {
        int hash1 = key % TABLE_SIZE;
        int hash2 = DOUBLE_HASH_PRIME - (key % DOUBLE_HASH_PRIME);
        int collisions = 0;
        int hash = hash1;

        while (table[hash] != -1) {
            collisions++;
            hash = (hash + hash2) % TABLE_SIZE;
        }

        table[hash] = key;
        return collisions;
    }

    public static void main(String[] args) throws IOException {
        // Generate a large input file with random numbers
        Random rand = new Random();
        int numberOfElements = 5000;
        Set<Integer> uniqueNumbers = new HashSet<>();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("random_numbers.txt"))) {
            while (uniqueNumbers.size() < numberOfElements) {
                int number = rand.nextInt();
                if (uniqueNumbers.add(number)) {
                    writer.write(number + "\n");
                }
            }
        }

        // Read the input file and measure collisions
        int[] linearTable = new int[TABLE_SIZE];
        int[] quadraticTable = new int[TABLE_SIZE];
        int[] doubleHashTable = new int[TABLE_SIZE];
        Arrays.fill(linearTable, -1);
        Arrays.fill(quadraticTable, -1);
        Arrays.fill(doubleHashTable, -1);

        int linearCollisions = 0;
        int quadraticCollisions = 0;
        int doubleHashCollisions = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("random_numbers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int key = Integer.parseInt(line);
                linearCollisions += linearProbing(linearTable, key);
                quadraticCollisions += quadraticProbing(quadraticTable, key);
                doubleHashCollisions += doubleHashing(doubleHashTable, key);
            }
        }

        // Print the report
        System.out.println("Collisions Report:");
        System.out.println("Linear Probing: " + linearCollisions);
        System.out.println("Quadratic Probing: " + quadraticCollisions);
        System.out.println("Double Hashing: " + doubleHashCollisions);
    }
}
