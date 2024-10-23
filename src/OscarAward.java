public class OscarAward {
    private int yearFilm;
    private int yearCeremony;
    private int ceremony;
    private String category;
    private String nominee;
    private String filmTitle;
    private boolean winner;

    // Getters and Setters
    public int getYearFilm() { return yearFilm; }
    public void setYearFilm(int yearFilm) { this.yearFilm = yearFilm; }

    public int getYearCeremony() { return yearCeremony; }
    public void setYearCeremony(int yearCeremony) { this.yearCeremony = yearCeremony; }

    public int getCeremony() { return ceremony; }
    public void setCeremony(int ceremony) { this.ceremony = ceremony; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getNominee() { return nominee; }
    public void setNominee(String nominee) { this.nominee = nominee; }

    public String getFilmTitle() { return filmTitle; }
    public void setFilmTitle(String filmTitle) { this.filmTitle = filmTitle; }

    public boolean isWinner() { return winner; }
    public void setWinner(boolean winner) { this.winner = winner; }
}
