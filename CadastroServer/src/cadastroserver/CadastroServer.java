package cadastroserver;

import controller.ProdutosJpaController;
import controller.UsuariosJpaController;
import java.net.ServerSocket;
import java.net.Socket;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author pedro
 */
public class CadastroServer {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CadastroServerPU");
        ProdutosJpaController ctrl = new ProdutosJpaController(emf);
        UsuariosJpaController ctrlUsu = new UsuariosJpaController(emf);

        try (ServerSocket serverSocket = new ServerSocket(4321)) {
            System.out.println("Servidor iniciado.");
            while (true) {
                System.out.println("Aguardando conexoes de clientes...");
                Socket clientSocket = serverSocket.accept(); 
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                CadastroThread clienteThread = new CadastroThread(ctrl, ctrlUsu, clientSocket);
                clienteThread.start();
            }
        } catch (Exception e) {
            System.out.println("Erro no servidor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (emf != null) {
                emf.close();
            }
        }
    }
}
