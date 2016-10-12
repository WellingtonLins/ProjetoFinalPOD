package ifpb.dev.rmi.chatshared;

import java.rmi.*;
import java.util.*;

/**
 * @Date 11/10/2016
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
public interface ChatServidorInterface extends Remote {

    public Vector getConectado() throws RemoteException;

    public boolean login(ChatClienteInterface chatClienteInterface) throws RemoteException;

    public void publicar(String mensagem) throws RemoteException;

}
