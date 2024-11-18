package cadastroclientv2;

import java.io.ObjectInputStream;
import java.util.List;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *
 * @author pedro
 */
public class ThreadClient extends Thread {
    private final ObjectInputStream entrada;
    private final JTextArea textArea;

    public ThreadClient(ObjectInputStream entrada, JTextArea textArea) {
        this.entrada = entrada;
        this.textArea = textArea;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object obj = entrada.readObject();

                if (obj instanceof String) {
                    String mensagem = (String) obj;
                    SwingUtilities.invokeLater(() -> textArea.append(mensagem + "\n"));
                } else if (obj instanceof List) {
                    List<?> lista = (List<?>) obj;
                    SwingUtilities.invokeLater(() -> {
                        for (Object item : lista) {
                            if (item instanceof model.Produtos) {
                                model.Produtos produto = (model.Produtos) item;
                                textArea.append("Produto: " + produto.getNomeProduto() +
                                        ", Quantidade: " + produto.getQuantidadeProduto() + "\n");
                            }
                        }
                    });
                }
            }
        } catch (Exception e) {
            SwingUtilities.invokeLater(() -> textArea.append("Conexao encerrada: " + e.getMessage() + "\n"));
        }
    }
}
