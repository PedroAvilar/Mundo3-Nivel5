package cadastroserver;

import controller.MovimentosJpaController;
import controller.PessoasJpaController;
import controller.ProdutosJpaController;
import controller.UsuariosJpaController;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.Socket;
import model.Movimentos;
import model.Pessoas;
import model.Produtos;
import model.Usuarios;

/**
 *
 * @author pedro
 */
public class CadastroThreadSegunda extends Thread {
    
    private final ProdutosJpaController ctrlProd;
    private final UsuariosJpaController ctrlUsu;
    private final MovimentosJpaController ctrlMov;
    private final PessoasJpaController ctrlPessoa;
    private final Socket s1;

    public CadastroThreadSegunda(ProdutosJpaController ctrlProd, 
            UsuariosJpaController ctrlUsu, MovimentosJpaController ctrlMov, 
            PessoasJpaController ctrlPessoa, Socket s1) {
        this.ctrlProd = ctrlProd;
        this.ctrlUsu = ctrlUsu;
        this.ctrlMov = ctrlMov;
        this.ctrlPessoa = ctrlPessoa;
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

            // Autenticação do usuário
            Usuarios usuario = ctrlUsu.findUsuario(login, senha);
            if (usuario == null) {
                saida.writeObject("Usurio ou senha invalidos. Conexao terminada.");
                return;
            }

            saida.writeObject("Usuario autenticado.");
            String comando;

            while ((comando = (String) entrada.readObject()) != null) {
                switch (comando.toUpperCase()) {
                    case "L":
                        saida.writeObject(ctrlProd.findAllProdutos());
                        break;
                    
                    case "E":
                    case "S":

                        Movimentos movimento = new Movimentos();
                        movimento.setUsuariosIDUsuario(usuario);
                        movimento.setTipo(comando.charAt(0));

                        Integer idPessoa = (Integer) entrada.readObject();
                        Pessoas pessoa = ctrlPessoa.findPessoas(idPessoa);
                        movimento.setPessoasIDPessoa(pessoa);

                        Integer idProduto = (Integer) entrada.readObject();
                        Produtos produto = ctrlProd.findProdutoById(idProduto);
                        movimento.setProdutosIDProduto(produto);

                        Integer quantidade = (Integer) entrada.readObject();
                        movimento.setQuantidadeMovimentado(quantidade);

                        Double precoUnitario = (Double) entrada.readObject();
                        movimento.setPrecoUnitario(BigDecimal.valueOf(precoUnitario));

                        ctrlMov.create(movimento);

                        if ("E".equals(comando)) {
                            produto.setQuantidadeProduto(produto.getQuantidadeProduto() + quantidade);
                        } else if ("S".equals(comando)) {
                            produto.setQuantidadeProduto(produto.getQuantidadeProduto() - quantidade);
                        }
                        
                        try {
                            ctrlProd.edit(produto);
                            saida.writeObject("Movimento registrado com sucesso.");
                        } catch (Exception e) {
                            saida.writeObject("Erro ao atualizar produto: " + e.getMessage());
                        }
                        break;

                    default:
                        saida.writeObject("Comando invalido.");
                        break;
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