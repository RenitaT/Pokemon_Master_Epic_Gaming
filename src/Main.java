import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String[] pokemon = inputPokemon();
        String[] type = inputType();

        Scanner scanUser = new Scanner(System.in);

        System.out.println("Are you a Pokemon Master? y/n");

        while (!scanUser.nextLine().equalsIgnoreCase("y")) {
            System.out.println("Sucks, play digimon or something, nerd.");
            System.out.println("Are you a Pokemon Master? y/n");

        }

        System.out.println("SLAYYY!!!\n" +
                "Press a for Epic Gamer Mode or \n" +
                "Press b for Noob Mode");

        String mode = scanUser.nextLine();
        if (mode.equalsIgnoreCase("b")) {
            collectPokemon();
        } else if (mode.equalsIgnoreCase("a")) {
            System.out.println("PS! Enter 's' to skip a Pokemon!");
            epicGame();
        } else {
            System.out.println("Invalid input. Exiting...");
        }
    }

    public static String[] inputPokemon() throws FileNotFoundException {
        String[] pokemon = new String[700];
        File notFile = new File("src/PokemonName");
        Scanner scan = new Scanner(notFile);
        for (int i = 0; i < 649; i++) {
            pokemon[i] = scan.nextLine();
        }
        return pokemon;
    }

    public static String[] inputType() throws FileNotFoundException {
        String[] type = new String[700];
        File yesFile = new File("src/PokemonTypes");
        Scanner scan = new Scanner(yesFile);
        for (int i = 0; i < 649; i++) {
            type[i] = scan.nextLine();
        }
        return type;
    }

    public static void collectPokemon() throws FileNotFoundException {
        String[] types = inputType();
        String[] names = inputPokemon();
        Scanner scanUser = new Scanner(System.in);
        Random random = new Random();
        int score = 0;
        for (int j = 0; j < 26; j++) {
            String[] a = new String[649];
            int randomIndex = random.nextInt(0, 649);
            String pokeTypes = types[randomIndex];
            int cnt = 0;
            char letter = (char) (j + 65);
            for (int i = 0; i < 649; i++) {
                if (names[i].charAt(0) == letter && types[i].equals(pokeTypes)) {
                    a[cnt] = names[i];
                    cnt++;
                }
            }
            if (cnt == 0) {
                j--;
                continue;
            }

            System.out.println("Name a Pokemon that starts with " + letter + " and is " + pokeTypes + " type");
            String answer = scanUser.nextLine();

            boolean cmp = false;
            for (int k = 0; k < a.length; k++) {
                if (a[k] != null && answer.equalsIgnoreCase(a[k].trim())) {
                    cmp = true;
                    System.out.println("Correct!");
                    score++;
                    break;
                }
            }
            if (!cmp) {
                System.out.println(gameLose(score));
                return;
            }
        }

        System.out.println(gameWin(score));
    }

    public static void epicGame() throws FileNotFoundException {
        String[] etypes = inputType();
        String[] enames = inputPokemon();
        Scanner escanUser = new Scanner(System.in);
        Random erandom = new Random();
        Random gamerRandom = new Random();
        // skip. so like j + 1 or continue
        int escore = 0;
        for (int j = 0; j < 649; j++) {
            int randomIndex = erandom.nextInt(0, 649);
            int randomLetter = gamerRandom.nextInt(0, 26);
            String pokeTypes = etypes[randomIndex];
            int cnt = 0;
            char letter = (char) (randomLetter + 65);
            for (int i = 0; i < 649; i++) {
               // System.out.println(enames[i]);
                if (enames[i].charAt(0) == letter && etypes[i].equals(pokeTypes)) {
                    cnt++;
                    break;
                }
            }
            if (cnt == 0) {
                j--;
                continue;
            }

            System.out.println("Name a Pokemon that starts with " + letter + " and is " + pokeTypes + " type");
            String answer = escanUser.nextLine();
            if (answer.equalsIgnoreCase("s")) {
                System.out.println("Skipped!");
                j--;
                continue;
            }
            boolean cmp = false;
            for (int k = 0; k < 649; k++) {
                if (enames[k] != " " && answer.equalsIgnoreCase(enames[k].trim())) {
                    cmp = true;
                    System.out.println("Correct!");
                    escore++;
                    enames[k] = " ";
                    break;
                }
            }
            if (!cmp) {
                System.out.println(epicGameLose(escore));
                return;
            }
        }

        System.out.println(epicGameWin(escore));
    }

    public static String gameWin(int score) {
        return "Congrats! Your final score was " + score + "/26, that's like 100%! " +
                "Go hardcore, brah!";
    }

    public static String gameLose(int score) {
        double percent = (double) score / 26;
        int percentReal = (int) (percent * 100);
        return "Congrats! Your final score was " + score + "/26, that's like " + percentReal + "%! " +
                "Sucks bro, get good";
    }

    public static String epicGameWin(int escore) {
        double epercent = (double) escore / 649;
        int epercentReal = (int) (epercent * 100);


        return "Congrats! Your final score was " + escore + "/649, that's like 100%! " +
                "OH MY GOD ALL HAIL LORD HELIX";
    }

    public static String epicGameLose(int escore) {
        double epercent = (double) escore / 649;
        int epercentReal = (int) (epercent * 100);
        if(escore >= 0 && escore < 29){
            return "Congrats! Your final score was " + escore + "/649, that's like " + epercentReal + "%! " +
                    "Sucks bro, get good";
        }

            return "Congrats! Your final score was " + escore + "/649, that's like " + epercentReal + "%! " +
                    "Wow I haven't actually gotten that far!!!";


    }
}