import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AudioCatalog {
    private List<AudioItem> allItems;
    private List<Playlist> playlists;

    public AudioCatalog(){
        this.allItems = new ArrayList<>();
        this.playlists = new ArrayList<>();
    }

    public void addItem(AudioItem item){
        allItems.add(item);
    }

    public void removeItem(AudioItem item){
        allItems.remove(item);
    }

    public List<AudioItem> searchByTitle(String title){
        List<AudioItem> foundItems = new ArrayList<>();

        for( AudioItem item:allItems){
            if(item.getTitle().equalsIgnoreCase(title)){
                foundItems.add(item);
            }
        }

        return foundItems;
    }

    public List<AudioItem> searchByAuthor(String author){
        List<AudioItem> foundItems = new ArrayList<>();

        for(AudioItem item: allItems){
            if(item.getAuthor().equalsIgnoreCase(author)){
                foundItems.add(item);
            }
        }

        return foundItems;
    }

    public List<AudioItem> filterByGenre(String genre){
        List<AudioItem> foundItems = new ArrayList<>();

        for(AudioItem item: allItems){
            if(item.getGenre().equalsIgnoreCase(genre)){
                foundItems.add(item);
            }
        }

        return foundItems;
    }

    public List<AudioItem> filterByYear(int year){
        List<AudioItem> foundItems = new ArrayList<>();

        for(AudioItem item: allItems){
            if(item.getYear() == year){
                foundItems.add(item);
            }
        }

        return foundItems;
    }

    public Album findAlbumByTitle(String title) {
        for (AudioItem item : allItems) {
            if (item instanceof Album && item.getTitle().equalsIgnoreCase(title)) {
                return (Album) item;
            }
        }
        return null;
    }

    public void createPlaylist(Playlist playlist){
        playlists.add(playlist);
    }

    public Playlist findPlaylistByName(String name){
        for(Playlist playlist : playlists){
            if(playlist.getName().equalsIgnoreCase(name)){
                return playlist;
            }
        }
        return null;
    }


    public void sortByTitle(){
        allItems.sort(new Comparator<AudioItem>() {
            @Override
            public int compare(AudioItem o1, AudioItem o2) {
                return o1.getTitle().compareToIgnoreCase(o2.getTitle());
            }
        });
    }

    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

            for (AudioItem item : allItems) {

                if (item instanceof Song) {
                    Song song = (Song) item;
                    writer.write(
                            "SONG;" +
                                    song.getTitle() + ";" +
                                    song.getGenre() + ";" +
                                    song.getDurationInSeconds() + ";" +
                                    song.getCategory() + ";" +
                                    song.getAuthor() + ";" +
                                    song.getYear() + ";" +
                                    song.getAlbumName()
                    );
                }
                else if (item instanceof Podcast) {
                    Podcast podcast = (Podcast) item;
                    writer.write(
                            "PODCAST;" +
                                    podcast.getTitle() + ";" +
                                    podcast.getGenre() + ";" +
                                    podcast.getDurationInSeconds() + ";" +
                                    podcast.getCategory() + ";" +
                                    podcast.getAuthor() + ";" +
                                    podcast.getYear() + ";" +
                                    podcast.getEpisodeNumber()
                    );
                }
                else if (item instanceof AudioBook) {
                    AudioBook book = (AudioBook) item;
                    writer.write(
                            "AUDIOBOOK;" +
                                    book.getTitle() + ";" +
                                    book.getGenre() + ";" +
                                    book.getDurationInSeconds() + ";" +
                                    book.getCategory() + ";" +
                                    book.getAuthor() + ";" +
                                    book.getYear() + ";" +
                                    book.getNarrator()
                    );
                }

                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error while saving file: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        allItems.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                String type = parts[0];
                String title = parts[1];
                String genre = parts[2];
                int duration = Integer.parseInt(parts[3]);
                String category = parts[4];
                String author = parts[5];
                int year = Integer.parseInt(parts[6]);

                switch (type) {
                    case "SONG":
                        String albumName = parts[7];
                        allItems.add(new Song(
                                title, genre, duration, category, author, year, albumName
                        ));
                        break;

                    case "PODCAST":
                        int episode = Integer.parseInt(parts[7]);
                        allItems.add(new Podcast(
                                title, genre, duration, category, author, year, episode
                        ));
                        break;

                    case "AUDIOBOOK":
                        String narrator = parts[7];
                        allItems.add(new AudioBook(
                                title, genre, duration, category, author, year, narrator
                        ));
                        break;
                }
            }

        } catch (IOException e) {
            System.out.println("Error while reading file: " + e.getMessage());
        }
    }





}
