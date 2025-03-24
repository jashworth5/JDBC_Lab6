public class ArcadeGame {
    private int gameId;
    private String gameName;
    private int releaseYear;
    private String manufacturer;
    
    /**
     * Constructor for ArcadeGame objects.
     * 
     * @param gameId unique identifier for the game
     * @param gameName name of the arcade game
     * @param releaseYear year the game was released
     * @param manufacturer company that made the game
     */
    public ArcadeGame(int gameId, String gameName, int releaseYear, String manufacturer) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.releaseYear = releaseYear;
        this.manufacturer = manufacturer;
    }
    
    public int getGameId() {
        return gameId;
    }
    
    public String getGameName() {
        return gameName;
    }
    
    public int getReleaseYear() {
        return releaseYear;
    }
    
    public String getManufacturer() {
        return manufacturer;
    }
    
    @Override
    public String toString() {
        return "Game ID: " + gameId + 
               ", Name: " + gameName + 
               ", Year: " + releaseYear +
               ", Manufacturer: " + manufacturer;
    }
}