public class Player {
    static int idVal;

    private int numCorrectGuesses;
    private int id;
    private String username;
    private double accuracy;
    private int totalGuesses;
    private int cryptogramsPlayed;
    private int cryptogramsCompleted;

    public Player(String username){
        try{
            idVal++;
        }
        catch(NullPointerException e){
            idVal = 1;
        }
        this.id = idVal;
        this.username = username;
        this.totalGuesses = 0;
        this.cryptogramsPlayed = 0;
        this.cryptogramsCompleted = 0;
    }

    //for hard coding player
    public Player(int id, String username, double accuracy, int totalGuesses, int cryptogramsPlayed, int cryptogramsCompleted, int numCorrectGuesses) {
        this.id = id;
        this.username = username;
        this.accuracy = accuracy;
        this.totalGuesses = totalGuesses;
        this.cryptogramsPlayed = cryptogramsPlayed;
        this.cryptogramsCompleted = cryptogramsCompleted;
        this.numCorrectGuesses = numCorrectGuesses;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getAccuracy() {

        return this.accuracy;
    }

    public void updateAccuracy(double accuracy) {
        this.accuracy = (double) numCorrectGuesses / (double) totalGuesses * 100;
    }

    public void updatenumCorrectGuesses(int numCorrectGuesses) {
        this.numCorrectGuesses = numCorrectGuesses;

    }

    public int getnumCorrectGuesses() {
        return this.numCorrectGuesses;
    }

    public int getTotalGuesses() {
        return this.totalGuesses;
    }

    public void updateTotalGuesses(int totalGuesses) {
        this.totalGuesses = totalGuesses;
    }

    public int getCryptogramsPlayed() {
        return this.cryptogramsPlayed;
    }

    public void incrementCryptogramsPlayed(int cryptogramsPlayed) {
        this.cryptogramsPlayed = cryptogramsPlayed + 1;
    }

    public int getCryptogramsCompleted() {
        return this.cryptogramsCompleted;
    }

    public void incrementCryptogramsCompleted(int cryptogramsCompleted) {
        this.cryptogramsCompleted = cryptogramsCompleted + 1;
    }

    public void printDetails() {
        System.out.println("Username " + getUsername());
        System.out.println("Number of total guesses: " + getTotalGuesses());
        System.out.println("Number of right guesses: " + getnumCorrectGuesses());
        System.out.println("Accuracy: " + getAccuracy());

    }


}