public abstract class AudioItem {
    private final String title;
    private final String genre;
    private final int durationInSeconds;
    private final String category;
    private final String author;
    private final int year;

    protected AudioItem(String title, String genre, int durationInSeconds, String category, String author, int year){
        if(title == null || title.isBlank()){
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if(author == null || author.isBlank()){
            throw new IllegalArgumentException("Author cannot be empty");
        }
        if(year<=0){
            throw new IllegalArgumentException("Invalid year!");
        }
        if(durationInSeconds<0){
            throw new IllegalArgumentException("The duration must be positive!");
        }

        this.title = title;
        this.genre = genre;
        this.durationInSeconds = durationInSeconds;
        this.category = category;
        this.author = author;
        this.year = year;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public String getFormattedDuration(){
        int minutes = durationInSeconds/60;
        int seconds = durationInSeconds%60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    @Override
    public String toString(){
        return String.format("%s | %s | %s | %d | %s", title, author, genre, year, getFormattedDuration()
        );
    }


}
