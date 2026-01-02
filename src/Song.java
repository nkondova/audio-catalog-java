public class Song extends AudioItem{
    private String albumName;

    public Song(String title, String genre, int durationInSeconds, String category, String author, int year, String albumName){
        super(title, genre, durationInSeconds, category, author, year);
        this.albumName = albumName;
    }

    public String getAlbumName(){
        return albumName;
    }

    @Override
    public String toString(){
        return super.toString() +" | Album" + albumName;
    }


}
