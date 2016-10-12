package ifpb.dev.rmi.testechat;


import ifpb.dev.rmi.chatshared.ChatClienteInterface;
import ifpb.dev.rmi.chatshared.ChatServidorInterface;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * @Date  11/10/2016 
 * @author Wellington Lins Claudino Duarte   
 * @mail wellingtonlins2013@gmail.com
 */ 

public class ChatServidor extends UnicastRemoteObject implements ChatServidorInterface {

    private Vector vector = new Vector();

    public ChatServidor() throws RemoteException {
    }

    public Vector getConectado() throws RemoteException {
        return vector;
    }
    
    public boolean login(ChatClienteInterface chatClienteInterface) throws RemoteException {
        System.out.println(chatClienteInterface.getNome()+ "  esta conectado...");
        chatClienteInterface.contar("Você foi conectado com sucesso!");
        publicar(chatClienteInterface.getNome() + " acaba de conectar.");
        vector.add(chatClienteInterface);
        return true;
    }

    public void publicar(String mensagem) throws RemoteException {
        System.out.println(mensagem);
        for (int i = 0; i < vector.size(); i++) {
            try {
                ChatClienteInterface chatClienteInterface = (ChatClienteInterface) vector.get(i);
                chatClienteInterface.contar(mensagem);
            } catch (Exception e) { }
        }
    }
}