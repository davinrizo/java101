import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User Input: Ask the user to input a paragraph or a lengthy text.
        System.out.println("Please enter a paragraph of text:");
        String inputText = scanner.nextLine();

        // Character Count
        int characterCount = inputText.length();
        System.out.println("Total number of characters: " + characterCount);

        // Word Count
        String[] words = inputText.split("\\s+");
        int wordCount = words.length;
        System.out.println("Total number of words: " + wordCount);

        // Most Common Character
        char mostCommonChar = findMostCommonCharacter(inputText);
        System.out.println("Most common character: " + mostCommonChar);

        // Character Frequency
        System.out.println("Please enter a character to find its frequency:");
        char charToFind = scanner.next().charAt(0);
        int charFrequency = findCharacterFrequency(inputText, charToFind);
        System.out.println("Frequency of '" + charToFind + "': " + charFrequency);

        // Word Frequency
        scanner.nextLine(); // Consume newline
        System.out.println("Please enter a word to find its frequency:");
        String wordToFind = scanner.nextLine();
        int wordFrequency = findWordFrequency(inputText, wordToFind);
        System.out.println("Frequency of \"" + wordToFind + "\": " + wordFrequency);

        // Unique Words
        int uniqueWordCount = findUniqueWordCount(inputText);
        System.out.println("Number of unique words: " + uniqueWordCount);

        scanner.close();
    }

    // Find and display the most common character in the text.
    public static char findMostCommonCharacter(String text) {
        text = text.toLowerCase();
        Map<Character, Integer> charCountMap = new HashMap<>();

        for (char ch : text.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
            }
        }

        char mostCommonChar = ' ';
        int maxCount = 0;

        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostCommonChar = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return mostCommonChar;
    }

    // Check and display the frequency of occurrences of a character in the text.
    public static int findCharacterFrequency(String text, char charToFind) {
        text = text.toLowerCase();
        charToFind = Character.toLowerCase(charToFind);
        int count = 0;

        for (char ch : text.toCharArray()) {
            if (ch == charToFind) {
                count++;
            }
        }

        return count;
    }

    // Check and display the frequency of occurrences of a word in the text.
    public static int findWordFrequency(String text, String wordToFind) {
        text = text.toLowerCase();
        wordToFind = wordToFind.toLowerCase();
        String[] words = text.split("\\s+");
        int count = 0;

        for (String word : words) {
            if (word.equals(wordToFind)) {
                count++;
            }
        }

        return count;
    }

    // Calculate and display the number of unique words in the text.
    public static int findUniqueWordCount(String text) {
        text = text.toLowerCase();
        String[] words = text.split("\\s+");
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
        return uniqueWords.size();
    }
}

