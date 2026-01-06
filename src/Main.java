import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

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
                    filterByYear(scanner, catalog);
                    break;

                case 7:
                    catalog.sortByTitle();
                    System.out.println("Catalog sorted by title.");
                    break;

                case 8:
                    catalog.saveToFile("catalog.txt");
                    System.out.println("Catalog saved.");
                    break;

                case 9:
                    catalog.loadFromFile("catalog.txt");
                    System.out.println("Catalog loaded.");
                    break;

                case 10:
                    createPlaylist(scanner, catalog);
                    break;

                case 11:
                    addItemToPlaylist(scanner, catalog);
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


    private static void printMenu() {
        System.out.println("\n--- AUDIO CATALOG ---");
        System.out.println("1. Add audio item");
        System.out.println("2. Remove audio item by title");
        System.out.println("3. Search by title");
        System.out.println("4. Search by author");
        System.out.println("5. Filter by genre");
        System.out.println("6. Filter by year");
        System.out.println("7. Sort by title");
        System.out.println("8. Save catalog to file");
        System.out.println("9. Load catalog from file");
        System.out.println("10. Create a playlist");
        System.out.println("11. Add to playlist");
        System.out.println("0. Exit");
    }



    private static void addAudioItem(Scanner scanner, AudioCatalog catalog) {
        try{

        System.out.println("Choose type:");
        System.out.println("1. Song");
        System.out.println("2. Podcast");
        System.out.println("3. Audiobook");
        System.out.println("4. Album");

        int type = Integer.parseInt(scanner.nextLine());


        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.println("Available Genres: " + Arrays.toString(Genre.values()));
        System.out.print("Genre: ");
        Genre genre = Genre.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Duration (mm:ss or seconds): ");
        String durationInput = scanner.nextLine();
        int duration = parseDuration(durationInput);

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

            case 4:
                System.out.print("Number of songs: ");
                int songs = Integer.parseInt(scanner.nextLine());
                item = new Album(title, genre, duration, category, author, year, songs);
                break;

            default:
                System.out.println("Invalid type.");
                return;
        }

        catalog.addItem(item);
        System.out.println("Audio item added successfully.");

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format! Please enter numeric values where required.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }


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
        try {
            System.out.print("Enter genre: ");
            Genre genre = Genre.valueOf(scanner.nextLine().toUpperCase().trim());

            for (AudioItem item : catalog.filterByGenre(genre)) {
                System.out.println(item);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid genre! Please try again.");
        }
    }

    private static void filterByYear(Scanner scanner, AudioCatalog catalog){
        System.out.println("Enter year: ");
        int year = Integer.parseInt(scanner.nextLine());

        for(AudioItem item : catalog.filterByYear(year)){
            System.out.println(item);
        }

    }

    private static void createPlaylist(Scanner scanner, AudioCatalog catalog) {
        System.out.print("Enter playlist name: ");
        String playlistName = scanner.nextLine();

        catalog.createPlaylist(new Playlist(playlistName));
        System.out.println("Playlist '" + playlistName + "' created successfully.");
    }

    private static void addItemToPlaylist(Scanner scanner, AudioCatalog catalog) {
        System.out.print("Enter playlist name: ");
        String pName = scanner.nextLine();

        Playlist pl = catalog.findPlaylistByName(pName);

        if (pl != null) {
            System.out.print("Enter item title to add to playlist: ");
            String itemTitle = scanner.nextLine();

            List<AudioItem> foundItems = catalog.searchByTitle(itemTitle);

            if (!foundItems.isEmpty()) {
                pl.addItem(foundItems.get(0));
                System.out.println("Item added to playlist '" + pName + "'.");
            } else {
                System.out.println("Audio item not found in catalog.");
            }
        } else {
            System.out.println("Playlist not found.");
        }
    }

    private static int parseDuration(String input) {
        try {
            if (input.contains(":")) {
                String[] parts = input.split(":");
                int minutes = Integer.parseInt(parts[0]);
                int seconds = Integer.parseInt(parts[1]);
                return (minutes * 60) + seconds;
            }
        return Integer.parseInt(input);
    }catch(Exception e) {
            System.out.println("Invalid duration format! Defaulting to 0.");
            return 0;
        }
    }
}
