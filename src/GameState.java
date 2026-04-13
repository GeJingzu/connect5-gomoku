public class GameState {
private boolean canPlay = false;
public void startGame(){
    canPlay = true;
}
public void stopGame(){
    canPlay = false;
}
public boolean isPlaying(){
    return canPlay;
}
}
