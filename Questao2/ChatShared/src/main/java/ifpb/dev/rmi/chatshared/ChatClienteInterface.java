package ifpb.dev.rmi.chatshared;


import java.rmi.*;
/**
 * @Date  11/10/2016 
 * @author Wellington Lins Claudino Duarte   
 * @mail wellingtonlins2013@gmail.com
 */ 
public interface ChatClienteInterface extends Remote {

    public void contar(String mensagem) throws RemoteException;

    public String getNome() throws RemoteException;
}
