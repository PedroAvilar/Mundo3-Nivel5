package cadastroclientv2;

import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

/**
 *
 * @author pedro
 */
public class SaidaFrame extends JDialog {
    public JTextArea texto;

    public SaidaFrame() {
        setBounds(100, 100, 400, 300);
        setModal(false);
        setTitle("Mensagens do Servidor");

        texto = new JTextArea();
        texto.setEditable(false);

        JScrollPane scroll = new JScrollPane(texto);
        add(scroll);
    }
}
