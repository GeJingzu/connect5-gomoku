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

    // Host a game server
    public boolean hostGame(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Waiting for connection on port: " + port);
            socket = serverSocket.accept();
            isHost = true;
            setupStreams();
            startListenerThread();
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
            startListenerThread();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Send local move to opponent
    public void sendMove(int r, int c, int color) {
        if (out != null) {
            out.println("MOVE," + r + "," + c + "," + color);
        }
    }

    // Background thread to listen for incoming messages
    private void startListenerThread() {
        Thread listener = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String msg;
                    // Keep reading lines from the network
                    while ((msg = in.readLine()) != null) {
                        handleMessage(msg);
                    }
                } catch (IOException e) {
                    System.out.println("Network disconnected.");
                }
            }
        });
        listener.start();
    }

    // Process incoming strings
    private void handleMessage(String msg) {
        System.out.println("[Network Rx] " + msg);

        String[] parts = msg.split(",");

        if (parts[0].equals("MOVE") && parts.length == 4) {
            int r = Integer.parseInt(parts[1]);
            int c = Integer.parseInt(parts[2]);
            int color = Integer.parseInt(parts[3]);

            // Apply move
            logic.placePiece(r, c, color);

        } else if (parts[0].equals("UNDO")) {
            logic.undo();
        } else if (parts[0].equals("RESTART")) {
            logic.resetBoard();
        }

        // Refresh UI
        if (updateUI != null) {
            updateUI.run();
        }
    }
}