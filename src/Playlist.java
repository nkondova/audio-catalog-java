import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String name;
    private List<AudioItem> items;

    public Playlist(String name){
        this.name = name;
        this.items = new ArrayList<>();
    }

    public List<AudioItem> getItems() {
        return items;
    }

    public String getName() {
        return name;
    }

    public void addItem(AudioItem item){
        items.add(item);
    }

    public void removeItem(AudioItem item){
        items.remove(item);
    }

    @Override
    public String toString() {
        return "Playlist: " + name + " (Items: " + items.size() + ")";
    }
}
