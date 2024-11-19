package cadastroclientv2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author pedro
 */
public class CadastroClientV2 {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 4321);
             ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Enviando login e senha");
            saida.writeObject("op1");
            saida.writeObject("op1");

            String resposta = (String) entrada.readObject();
            System.out.println("Servidor: " + resposta);
            if (!resposta.contains("autenticado")) {
                System.out.println("Conexao encerrada pelo servidor.");
                return;
            }

            SaidaFrame janela = new SaidaFrame();
            janela.setVisible(true);

            ThreadClient threadClient = new ThreadClient(entrada, janela.texto);
            threadClient.start();

            String comando;
            do {
                System.out.println("Menu:");
                System.out.println("L - Listar produtos");
                System.out.println("E - Entrada de produto");
                System.out.println("S - Saida de produto");
                System.out.println("X - Finalizar");
                System.out.print("Escolha uma opcao: ");
                comando = teclado.readLine().toUpperCase();

                switch (comando) {
                    case "L":
                        saida.writeObject("L");
                        break;
                    case "E":
                    case "S":
                        saida.writeObject(comando);
                        System.out.print("Digite o ID da pessoa: ");
                        saida.writeObject(Integer.parseInt(teclado.readLine()));

                        System.out.print("Digite o ID do produto: ");
                        saida.writeObject(Integer.parseInt(teclado.readLine()));

                        System.out.print("Digite a quantidade: ");
                        saida.writeObject(Integer.parseInt(teclado.readLine()));

                        System.out.print("Digite o valor unitario: ");
                        saida.writeObject(Double.parseDouble(teclado.readLine()));
                        break;
                    case "X":
                        System.out.println("Finalizando conexao.");
                        break;
                    default:
                        System.out.println("Comando invalido.");
                        break;
                }
            } while (!"X".equals(comando));

        } catch (Exception e) {
            System.out.println("Erro no cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
