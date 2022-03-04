public class Player {

    private int id;
    private String username;
    private double accuracy;
    private int totalGuesses;
    private int cryptogramsPlayed;
    private int cryptogramsCompleted;

    public Player(int id,String username, double accuracy, int totalGuesses, int cryptogramsPlayed, int cryptogramsCompleted) {
        setUsername(username);
        updateAccuracy(accuracy);
        incrementTotalGuesses(totalGuesses);
        incrementCryptogramsCompleted(cryptogramsCompleted);
        incrementCryptogramsPlayed(cryptogramsPlayed);
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }

    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username=username;
    }

    public double getAccuracy(){
        return this.accuracy;
    }
    public void updateAccuracy(double accuracy){
        this.accuracy=accuracy;
    }

    public int getTotalGuesses(){
        return this.totalGuesses;
    }
    public void incrementTotalGuesses(int totalGuesses){
        this.totalGuesses =totalGuesses;
    }

    public int getCryptogramsPlayed(){
        return this.cryptogramsPlayed;
    }
    public void incrementCryptogramsPlayed(int cryptogramsPlayed){
        this.cryptogramsPlayed =cryptogramsPlayed;
    }

    public int getCryptogramsCompleted(){
        return this.cryptogramsCompleted;
    }
    public void incrementCryptogramsCompleted(int cryptogramsCompleted){
        this.cryptogramsCompleted =cryptogramsCompleted;
    }



}