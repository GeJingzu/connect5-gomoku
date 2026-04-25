import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkManager {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    public boolean isHost;

    private Gomokulogic logic;
    private Runnable updateUI;

    // Constructor
    public NetworkManager(Gomokulogic logic, Runnable updateUI) {
        this.logic = logic;
        this.updateUI = updateUI;
    }

    // Initialize IO streams
    private void setupStreams() throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }
}