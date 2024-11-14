package cadastroserver;

import controller.ProdutosJpaController;
import controller.UsuariosJpaController;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;
import model.Usuarios;
import java.util.List;
import model.Produtos;

/**
 *
 * @author pedro
 */
public class CadastroThread extends Thread {
    
    private final ProdutosJpaController ctrl;
    private final UsuariosJpaController ctrlUsu;
    private final Socket s1;

    public CadastroThread(ProdutosJpaController ctrl, UsuariosJpaController ctrlUsu, Socket s1) {
        this.ctrl = ctrl;
        this.ctrlUsu = ctrlUsu;
        this.s1 = s1;
    }

    @Override
    public void run() {
        try (
            ObjectOutputStream saida = new ObjectOutputStream(s1.getOutputStream());
            ObjectInputStream entrada = new ObjectInputStream(s1.getInputStream())
        ) {
            String login = (String) entrada.readObject();
            String senha = (String) entrada.readObject();

            Usuarios usuario = ctrlUsu.findUsuario(login, senha);
            if (usuario == null) {
                saida.writeObject("Usuario ou senha invalidos. Conexao terminada.");
                return;
            }
            
            saida.writeObject("Usuario autenticado.");
            String comando;

            while ((comando = (String) entrada.readObject()) != null) {
                if ("L".equalsIgnoreCase(comando)) {
                    List<Produtos> produtos = ctrl.findAllProdutos();
                    saida.writeObject(produtos);
                } else {
                    saida.writeObject("Comando invalido.");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro na comunicacao com o cliente: " + e.getMessage());
        } finally {
            try {
                s1.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar o socket: " + e.getMessage());
            }
        }
    }
}
