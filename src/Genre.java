public enum Genre {
    ROCK, POP, JAZZ, CLASSICAL, PODCAST, AUDIOBOOK, METAL, OTHER;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
