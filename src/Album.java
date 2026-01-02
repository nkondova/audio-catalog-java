import java.util.ArrayList;
import java.util.List;

public class Album extends AudioItem {
    private final List<Song> songs;

    public Album(String title, String genre, int durationInSeconds, String category, String author, int year, String name){
        super(title, genre, durationInSeconds, category, author, year);
        this.songs = new ArrayList<Song>();
    }

    public List<Song> getSongs(){
        return songs;
    }
    public void addSong(Song song){
        songs.add(song);
    }

    @Override
    public String toString() {
        return String.format("Album: %s | Artist: %s | Tracks: %d | %s",
                getTitle(), getAuthor(), songs.size(), super.toString());
    }


}
