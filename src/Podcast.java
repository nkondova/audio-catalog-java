public class Podcast extends AudioItem{
    private int episodeNumber;

    public Podcast(String title, Genre genre, int durationInSeconds, String category, String author, int year, int episodeNumber){
        super(title, genre, durationInSeconds, category, author, year);
        this.episodeNumber = episodeNumber;
    }

    public int getEpisodeNumber(){
        return episodeNumber;
    }

    @Override
    public String toString() {
        return super.toString()+ "|Episode number: " + episodeNumber;
    }
}
