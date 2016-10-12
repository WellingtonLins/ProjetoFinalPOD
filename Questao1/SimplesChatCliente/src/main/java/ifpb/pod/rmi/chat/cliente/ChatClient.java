package ifpb.pod.rmi.chat.cliente;

/**
 * @Date 10/10/2016
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */

import ifpb.pod.rmi.chat.shared.Chat;
import ifpb.pod.rmi.chat.shared.ChatInterface;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args)  throws RemoteException,NotBoundException {
        try {
           Scanner scanner = new Scanner(System.in);
            System.out.println("Entre com o seu nome e pressione Enter:");
            String nome = scanner.nextLine().trim();
            ChatInterface cliente = new Chat(nome);

            Registry registry = LocateRegistry.getRegistry(10999);

            ChatInterface server = (ChatInterface) registry.lookup("__ChatServer__");
            String msg = "[" + cliente.getNome() + "] esta conectado";
            server.send(msg);
            System.out.println("[System] O Chat de Objeto Remoto está pronto:");
            server.setCliente(cliente);

            while (true) {
                msg = scanner.nextLine().trim();
                msg = "[" + cliente.getNome() + "] " + msg;
                server.send(msg);
            }

        } catch (Exception e) {
            System.out.println("[System] Sevidor falhou: " + e);
        }
    }
}
