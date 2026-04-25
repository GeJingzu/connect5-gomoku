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
// Host a game server
public boolean hostGame(int port) {
    try {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Waiting for connection on port: " + port);
        socket = serverSocket.accept();
        isHost = true;
        setupStreams();
        // startListenerThread(); // 先注释掉，下一部分才写
        return true;
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}

// Join an existing game
public boolean joinGame(String ip, int port) {
    try {
        socket = new Socket(ip, port);
        isHost = false;
        setupStreams();
        // startListenerThread(); // 先注释掉，下一部分才写
        return true;
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}