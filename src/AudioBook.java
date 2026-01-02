public class AudioBook extends AudioItem {
    private String narrator;

    public AudioBook(String title, String genre, int durationInSeconds, String category, String author, int year, String narrator){
        super(title, genre, durationInSeconds, category, author, year);
        this.narrator = narrator;
    }
     public String getNarrator(){
        return narrator;
     }

    @Override
    public String toString() {
        return super.toString() + "| Narrator: " + narrator;
    }
}
