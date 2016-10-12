package ifpb.pod.rmi.chat.servidor;

/**
 * @Date 10/10/2016
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
import ifpb.pod.rmi.chat.shared.Chat;
import ifpb.pod.rmi.chat.shared.ChatInterface;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public class ChatServer {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            Chat server = new Chat();

            Registry registry = LocateRegistry.createRegistry(10999);
            registry.bind("__ChatServer__", server);
            System.out.println("[System] O Chat de Objeto Remoto está pronto:");

            while (true) {
                String msg = scanner.nextLine().trim();
                if (server.getCliente() != null) {
                    ChatInterface client = server.getCliente();
                    msg = "[" + server.getNome() + "] " + msg;
                    client.send(msg);
                }
            }

        } catch (Exception e) {
            System.out.println("[System] Sevidor falhou: " + e);

        }
    }
}
