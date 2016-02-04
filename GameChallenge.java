public class GameChallenge {

  private final String challenger;
  private final String challenged;

  GameChallenge(String challenger, String challenged) {
    this.challenger = challenger;
    this.challenged = challenged;
  }

  public String getChallenger() {
    return challenger;
  }

  public String getChallenged() {
    return challenged;
  }

  public String toString() {
    return "You have been challenged to a game of Tic-Tac-Toe by " + challenger + "\n";
  }
}
