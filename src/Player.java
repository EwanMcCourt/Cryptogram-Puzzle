public class Player {
    private int numCorrectGuesses;
    private String username;
    private double accuracy;             //Declarations of all required variables to be stored on the player
    private int totalGuesses;
    private int cryptogramsPlayed;
    private int cryptogramsCompleted;

    public Player(String username){

        this.username = username;
        this.totalGuesses = 0;                  //Initial assignment of player variables
        this.cryptogramsPlayed = 0;
        this.cryptogramsCompleted = 0;
    }

    //for hard coding player
    public Player( String username, double accuracy, int totalGuesses, int cryptogramsPlayed, int cryptogramsCompleted, int numCorrectGuesses) {

        this.username = username;
        this.accuracy = accuracy;
        this.totalGuesses = totalGuesses;
        this.cryptogramsPlayed = cryptogramsPlayed;
        this.cryptogramsCompleted = cryptogramsCompleted;
        this.numCorrectGuesses = numCorrectGuesses;
    }

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }    //Updating the username

    public double getAccuracy() {
        return Math.round(this.accuracy * 100.0) / 100.0;
    }
    public void updateAccuracy() {
        this.accuracy = (double) numCorrectGuesses / (double) totalGuesses * 100;
    }

    public void updatenumCorrectGuesses(int numCorrectGuesses) {
        this.numCorrectGuesses = numCorrectGuesses;
    }
    public int getnumCorrectGuesses() {
        return this.numCorrectGuesses;                        //Updating the number of correct guesses
    }

    public int getTotalGuesses() {
        return this.totalGuesses;                       //Updating the total number of guesses
    }
    public void updateTotalGuesses(int totalGuesses) {
        this.totalGuesses = totalGuesses;
    }

    public int getCryptogramsPlayed() {
        return this.cryptogramsPlayed;                   //Returns the number of cryptograms that have been played
    }
    public void incrementCryptogramsPlayed() {
        this.cryptogramsPlayed++;
    }

    public int getCryptogramsCompleted() {
        return this.cryptogramsCompleted;             //Returns the number of cryptograms that have been completed
    }
    public void incrementCryptogramsCompleted() {
        this.cryptogramsCompleted++;
    }

    public void printDetails() {
        System.out.println("Username " + getUsername());
        System.out.println("Number of total guesses: " + getTotalGuesses());
        System.out.println("Number of right guesses: " + getnumCorrectGuesses());
        System.out.println("Accuracy: " + getAccuracy());                     //Outputs the details stores on the player
        System.out.println("Cryptograms played: "+ getCryptogramsPlayed());
        System.out.println("Cryptograms completed: "+ getCryptogramsCompleted());
    }

    @Override
    public String toString() {
        return  username + " " +  accuracy + " " + totalGuesses+" "+ cryptogramsPlayed+" "+ cryptogramsCompleted+" " +numCorrectGuesses;
    }   //User all time details
}