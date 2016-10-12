package ifpb.dev.rmi.chatcliente;

import ifpb.dev.rmi.chatshared.ChatClienteInterface;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
/**
 * @Date  11/10/2016 
 * @author Wellington Lins Claudino Duarte   
 * @mail wellingtonlins2013@gmail.com
 */ 
public class ChatCliente extends UnicastRemoteObject implements ChatClienteInterface {

    private final String nome;
    private ChatUI chatUI;

    public ChatCliente(String nome) throws RemoteException {
        this.nome = nome;
    }

    @Override
    public void contar(String mensagem) throws RemoteException {
        System.out.println(mensagem);
        chatUI.escreverMensagem(mensagem);
    }

    @Override
    public String getNome() throws RemoteException {
        return nome;
    }

    public void setGUI(ChatUI chatUI) {
        this.chatUI = chatUI;
    }
}
