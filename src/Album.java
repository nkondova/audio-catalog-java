public class Album extends AudioItem {
    int numberOfSongs;

    public Album(String title, Genre genre, int durationInSeconds, String category, String author, int year, int numberOfSongs){
        super(title, genre, durationInSeconds, category, author, year);
        this.numberOfSongs = numberOfSongs;
    }

    public int getNumberOfSongs(){
        return numberOfSongs;
    }


    @Override
    public String toString() {
        return "Album: " + super.toString() + "Number of songs: " + numberOfSongs;
    }
}
