package ifpb.dev.rmi.chatcliente;

import ifpb.dev.rmi.chatshared.ChatClienteInterface;
import ifpb.dev.rmi.chatshared.ChatServidorInterface;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;
import java.util.*;

/**
 * @Date  11/10/2016 
 * @author Wellington Lins Claudino Duarte   
 * @mail wellingtonlins2013@gmail.com
 */ 
public class ChatUI {

    private JTextArea texto;
    private JTextField tf, ip, nome;
    private JButton conecta;
    private JList jLista;
    private JFrame frame;

    private ChatCliente chatCliente;
    private ChatServidorInterface chatServidorInterface;

    //Criando o frame da interface do usuario
    public ChatUI() {
        frame = new JFrame("ChatGroup");
        JPanel main = new JPanel();
        JPanel top = new JPanel();
        JPanel cn = new JPanel();
        JPanel bottom = new JPanel();
        ip = new JTextField();
        tf = new JTextField();
        nome = new JTextField();
        texto = new JTextArea();
        conecta = new JButton("Conectar");
        JButton bt = new JButton("Enviar");
        jLista = new JList();
        main.setLayout(new BorderLayout(5, 5));
        top.setLayout(new GridLayout(1, 0, 5, 5));
        cn.setLayout(new BorderLayout(5, 5));
        bottom.setLayout(new BorderLayout(5, 5));
        top.add(new JLabel("Seu nome: "));
        top.add(nome);
        top.add(new JLabel("IP do Servidor: "));
        top.add(ip);
        top.add(conecta);
        cn.add(new JScrollPane(texto), BorderLayout.CENTER);
        cn.add(jLista, BorderLayout.EAST);
        bottom.add(tf, BorderLayout.CENTER);
        bottom.add(bt, BorderLayout.EAST);
        main.add(top, BorderLayout.NORTH);
        main.add(cn, BorderLayout.CENTER);
        main.add(bottom, BorderLayout.SOUTH);
        main.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Eventos com o ActionListener
        conecta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fazerConectar();
            }
        });
        bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enviarTexto();
            }
        });
        tf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enviarTexto();
            }
        });

        frame.setContentPane(main);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    public void fazerConectar() {
        if (conecta.getText().equals("Conectar")) {
            if (nome.getText().length() < 2) {
                JOptionPane.showMessageDialog(frame, "Informe seu nome!");
                return;
            }
            if (ip.getText().length() < 2) {
                JOptionPane.showMessageDialog(frame, "O campo IP é necessario.");
                return;
            }
            try {
                chatCliente = new ChatCliente(nome.getText());
                chatCliente.setGUI(this);
                chatServidorInterface = (ChatServidorInterface) Naming.lookup(ip.getText());
                chatServidorInterface.login(chatCliente);
                updateUsuarios(chatServidorInterface.getConectado());
                conecta.setText("Desconectar");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "ERRO, nós não estamos conectados...");
            }
        } else {
            updateUsuarios(null);
            conecta.setText("Conectar"); //melhor fazer desconectar aqui
//            System.exit(0);
//            frame.dispose(); 
//            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
         
        }
    }


    public void enviarTexto() {
        if (conecta.getText().equals("Conectar")) {
            JOptionPane.showMessageDialog(frame, "Você precisa conectar primeiro.");
            return;
        }
        String st = tf.getText();
        st = "[" + nome.getText() + "] " + st;
        tf.setText("");

        try {
            chatServidorInterface.publicar(st);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void escreverMensagem(String mensagem) {
        texto.setText(texto.getText() + "\n" + mensagem);
    }

    public void updateUsuarios(Vector vector) {
        DefaultListModel listModel = new DefaultListModel();
        if (vector != null) {
            for (int i = 0; i < vector.size(); i++) {
                try {
                    String tmp = ((ChatClienteInterface) vector.get(i)).getNome();
                    listModel.addElement(tmp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        jLista.setModel(listModel);
    }

    public static void main(String[] args) {
        System.out.println("Hello World !");
        ChatUI chatUI = new ChatUI();
    }

}
