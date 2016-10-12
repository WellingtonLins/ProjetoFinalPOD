package ifpb.pod.rmi.chat.shared;

/**
 * @Date 10/10/2016
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
import java.rmi.*;
import java.rmi.server.*;

public class Chat extends UnicastRemoteObject implements ChatInterface {

    public String nome;
    public ChatInterface cliente = null;

    public Chat() throws RemoteException {

    }

    public Chat(String nome) throws RemoteException {
        this.nome = nome;
    }

    public String getNome() throws RemoteException {
        return this.nome;
    }

    public void setCliente(ChatInterface cliente) {
        cliente = cliente;
    }

    public ChatInterface getCliente() {
        return cliente;
    }

    public void send(String s) throws RemoteException {
        System.out.println(s);
    }
}
