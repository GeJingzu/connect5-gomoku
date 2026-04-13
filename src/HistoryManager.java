public class HistoryManager {
    private Gomokulogic logic;

    public HistoryManager(Gomokulogic logic){
        this.logic = logic;
    }

    public void undo(){
        logic.undo;
    }
}
