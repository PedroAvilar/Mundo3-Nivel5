package cadastroclient;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import model.Produtos;

/**
 *
 * @author pedro
 */
public class CadastroClient {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 4321);
             ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream())) {
             
            String login = "op1";
            String senha = "op1";
            saida.writeObject(login);
            saida.writeObject(senha);
            
            String resposta = (String) entrada.readObject();
            System.out.println(resposta);
            if (!"Usuario autenticado.".equals(resposta)) {
                System.out.println("Conexao encerrada.");
                return;
            }
            
            saida.writeObject("L");
            
            List<Produtos> produtos = (List<Produtos>) entrada.readObject();
            
            System.out.println("Produtos recebidos:");
            for (Produtos produto : produtos) {
                System.out.println(produto.getNomeProduto());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
