package ifpb.dev.rmi.testechat;

import ifpb.dev.rmi.chatshared.ChatServidorInterface;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;

/**
 * @Date 11/10/2016
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
public class StartServer {
//Aqui eu quiz usar o naming para testar
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);

            ChatServidorInterface chatServidor = new ChatServidor();
            Naming.rebind("10.0.0.1.110", chatServidor);
            System.out.println("[Sistema] O Servidor de Chat esta pronto.");
        } catch (Exception e) {
            System.out.println("Servidor de Chat falhou: " + e);
        }
    }
}
