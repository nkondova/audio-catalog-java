import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        AudioCatalog catalog = new AudioCatalog();

        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Choose option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    addAudioItem(scanner, catalog);
                    break;

                case 2:
                    removeAudioItem(scanner, catalog);
                    break;

                case 3:
                    searchByTitle(scanner, catalog);
                    break;

                case 4:
                    searchByAuthor(scanner, catalog);
                    break;

                case 5:
                    filterByGenre(scanner, catalog);
                    break;

                case 6:
                    catalog.sortByTitle();
                    System.out.println("Catalog sorted by title.");
                    break;

                case 7:
                    catalog.saveToFile("catalog.txt");
                    System.out.println("Catalog saved.");
                    break;

                case 8:
                    catalog.loadFromFile("catalog.txt");
                    System.out.println("Catalog loaded.");
                    break;

                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }

    // ---------------- MENU ----------------

    private static void printMenu() {
        System.out.println("\n--- AUDIO CATALOG ---");
        System.out.println("1. Add audio item");
        System.out.println("2. Remove audio item by title");
        System.out.println("3. Search by title");
        System.out.println("4. Search by author");
        System.out.println("5. Filter by genre");
        System.out.println("6. Sort by title");
        System.out.println("7. Save catalog to file");
        System.out.println("8. Load catalog from file");
        System.out.println("0. Exit");
    }

    // ---------------- ADD ITEM ----------------

    private static void addAudioItem(Scanner scanner, AudioCatalog catalog) {

        System.out.println("Choose type:");
        System.out.println("1. Song");
        System.out.println("2. Podcast");
        System.out.println("3. Audiobook");

        int type = Integer.parseInt(scanner.nextLine());

        // Общи полета
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Genre: ");
        String genre = scanner.nextLine();

        System.out.print("Duration (seconds): ");
        int duration = Integer.parseInt(scanner.nextLine());

        System.out.print("Category: ");
        String category = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("Year: ");
        int year = Integer.parseInt(scanner.nextLine());

        AudioItem item = null;

        switch (type) {
            case 1:
                System.out.print("Album name: ");
                String album = scanner.nextLine();
                item = new Song(title, genre, duration, category, author, year, album);
                break;

            case 2:
                System.out.print("Episode number: ");
                int episode = Integer.parseInt(scanner.nextLine());
                item = new Podcast(title, genre, duration, category, author, year, episode);
                break;

            case 3:
                System.out.print("Narrator: ");
                String narrator = scanner.nextLine();
                item = new AudioBook(title, genre, duration, category, author, year, narrator);
                break;

            default:
                System.out.println("Invalid type.");
                return;
        }

        catalog.addItem(item);
        System.out.println("Audio item added successfully.");
    }

    // ---------------- REMOVE ----------------

    private static void removeAudioItem(Scanner scanner, AudioCatalog catalog) {
        System.out.print("Enter title to remove: ");
        String title = scanner.nextLine();

        for (AudioItem item : catalog.searchByTitle(title)) {
            catalog.removeItem(item);
            System.out.println("Removed: " + item);
            return;
        }

        System.out.println("Item not found.");
    }

    // ---------------- SEARCH / FILTER ----------------

    private static void searchByTitle(Scanner scanner, AudioCatalog catalog) {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        for (AudioItem item : catalog.searchByTitle(title)) {
            System.out.println(item);
        }
    }

    private static void searchByAuthor(Scanner scanner, AudioCatalog catalog) {
        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        for (AudioItem item : catalog.searchByAuthor(author)) {
            System.out.println(item);
        }
    }

    private static void filterByGenre(Scanner scanner, AudioCatalog catalog) {
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();

        for (AudioItem item : catalog.filterByGenre(genre)) {
            System.out.println(item);
        }
    }
}
