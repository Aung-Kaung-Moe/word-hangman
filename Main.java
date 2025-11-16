import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class Main {
    public static void main(String[] args) {

        String filepath = "words.txt";
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size()));

        Scanner scanner = new Scanner(System.in);
        ArrayList<Character> wordState = new ArrayList<>();

        int wrongGuesses = 0;
        for (int i = 0; i < word.length(); i++) {
            wordState.add('_');
        }
        String welcome = """
                        ************************
                        Welcome to Java Hangman!
                        ************************
                """;
        System.out.println(welcome);
        while(wrongGuesses < 6) {
            System.out.print("Word: ");
            for (char c : wordState) {
                System.out.print(c + " ");
            }
            System.out.println();
            System.out.print("Guess A letter: ");
            char guess = scanner.next().toLowerCase().charAt(0);
            if (word.indexOf(guess) >= 0){
                System.out.println("Correct!\n");
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess) {
                        wordState.set(i, guess);
                    }
                }
                if (!wordState.contains('_')) {
                    System.out.println("Congratulations! You've guessed the word: " + word);
                    break;
                }
            } else {
                System.out.println("Wrong!");
                wrongGuesses++;
                System.out.println(getHangmanArt(wrongGuesses));
            }
        }
        if (wordState.contains('_'))
            System.out.println("Game Over! The word was: " + word);
        scanner.close();
    }

    static String getHangmanArt(int wrongGuesses) {
        return switch(wrongGuesses) {
            case 0 -> """
                    


                      """;
            case 1 -> """
                       ●

             
                      """;
            case 2 -> """
                       ●
                       |

                      """;
            case 3 -> """
                       ●
                      /|

                      """;
            case 4 -> """
                       ●
                      /|\\

                      """;
            case 5 -> """
                       ●
                      /|\\
                      /
                      """;
            case 6 -> """
                       ●
                      /|\\
                      / \\

                      """;
            default -> "";
        };
    }
}
