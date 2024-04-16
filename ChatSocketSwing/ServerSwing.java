import javax.swing.*;

import java.awt.BorderLayout;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerSwing extends JFrame {

    /**
     * Log du serveur.
     */
    private JTextArea serverLog;
    public static ArrayList<Socket> clients = new ArrayList<>();

    /**
     * Représente un serveur de chat basé sur Swing.
     */
    public ServerSwing() {
        super("Chat Server");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        serverLog = new JTextArea();
        serverLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(serverLog);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        try (ServerSocket server = new ServerSocket(9999)) {
            serverLog.append("Serveur en attente de connexions...\n");

            while (true) {
                Socket socket = server.accept();
                clients.add(socket);
                serverLog.append("Un client s'est connecté\n");

                // Lancer un thread pour gérer les messages du client
                ThreadMessageSwing threadMessage = new ThreadMessageSwing(socket, this);
                threadMessage.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Envoie un message à tous les clients connectés.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ServerSwing().setVisible(true);
            }
        });
    }

    /**
     * Ajoute un message au log du serveur.
     */
    public void appendLog(String log) {
        serverLog.append(log + "\n");
    }
}
