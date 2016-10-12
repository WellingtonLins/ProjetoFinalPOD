package ifpb.pod.rmi.chat.shared;



/**
 * @Date  10/10/2016 
 * @author Wellington Lins Claudino Duarte   
 * @mail wellingtonlins2013@gmail.com
 */ 
import java.rmi.*;
 
 public interface ChatInterface extends Remote{
	public String getNome() throws RemoteException;
	public void send(String msg) throws RemoteException;
	public void setCliente(ChatInterface c)throws RemoteException;
	public ChatInterface getCliente() throws RemoteException;
}