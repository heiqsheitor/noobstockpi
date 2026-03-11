package view;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;

public class TelaAdicionarProduto extends JPanel {

    public TelaAdicionarProduto() {
        setBackground(Color.WHITE);
        setLayout(new MigLayout("insets 0, wrap 1, fillx", "[grow]", "[][grow]"));

        // --- 1. CABEÇALHO ---
        JPanel headerPanel = new JPanel(new MigLayout("insets 20 40 15 40, gapx 15", "[][grow]", "[]"));
        headerPanel.setBackground(Color.WHITE);
       
        JLabel backArrow = new JLabel("");
        backArrow.setIcon(new ImageIcon(TelaAdicionarProduto.class.getResource("/img/voltar.png")));
        backArrow.setCursor(new Cursor(Cursor.HAND_CURSOR));
       
        JLabel titleLabel = new JLabel("Adicionar Produto");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
       
        headerPanel.add(backArrow);
        headerPanel.add(titleLabel);
       
        add(headerPanel, "growx");

        // Linha divisória fina
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(235, 235, 235));
        add(separator, "growx, h 1!");

        // --- 2. ÁREA DO FORMULÁRIO ---
        JPanel formContainer = new JPanel(new MigLayout("wrap 1, insets 40 20 40 20, wmax 700, al center top", "[grow, fill]"));
        formContainer.setBackground(Color.WHITE);

        // =========================================================
        // NOVO: ÁREA DO TÍTULO COM ÍCONE (2 Colunas)
        // =========================================================
        // "[][]" significa duas colunas. "[][]" no final significa duas linhas (título em cima, subtítulo embaixo)
        JPanel titleArea = new JPanel(new MigLayout("insets 0, gapy 5", "[][]", "[][]"));
        titleArea.setBackground(Color.WHITE);

        // 1. A Label que vai receber o seu ícone
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(new ImageIcon(TelaAdicionarProduto.class.getResource("/img/caixa(1)1.png")));

        // 2. Textos
        JLabel boxTitleText = new JLabel("Adicionar Produto ao Estoque");
        boxTitleText.setFont(new Font("SansSerif", Font.BOLD, 14));
       
        JLabel subDesc = new JLabel("Preencha as informações abaixo para adicionar um novo produto ao seu estoque");
        subDesc.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subDesc.setForeground(new Color(150, 150, 150));

        // Montando a área do título
        // "spany 2" faz o ícone ocupar a altura das duas linhas de texto.
        // "aligny top" alinha o ícone no topo, perto do título principal.
        titleArea.add(iconLabel, "spany 2, aligny top, gapright 10");
        titleArea.add(boxTitleText, "wrap"); // "wrap" quebra a linha para o subtítulo cair pra baixo
        titleArea.add(subDesc);

        // Adiciona esse novo bloco no formulário principal
        formContainer.add(titleArea, "gapbottom 20");
        // =========================================================

        // Adicionando os campos
        addFormField(formContainer, "Nome do Produto");
        addFormField(formContainer, "SKU (Código do Produto)");
        addFormField(formContainer, "Quantidade");
        addFormField(formContainer, "Localização no Estoque");
        addFormField(formContainer, "Fornecedor");
        addFormField(formContainer, "Categoria do Produto");

        // --- 3. BOTÕES ---
        JPanel buttonPanel = new JPanel(new MigLayout("insets 20 0 0 0, gapx 15", "[grow, fill][grow, fill]", "[]"));
        buttonPanel.setBackground(Color.WHITE);

        JButton btnCancelar = createButton("Cancelar", Color.WHITE, Color.BLACK, true);
        JButton btnAdicionar = createButton("Adicionar Produto", Color.BLACK, Color.WHITE, false);

        buttonPanel.add(btnCancelar, "h 45!");
        buttonPanel.add(btnAdicionar, "h 45!");

        formContainer.add(buttonPanel, "gaptop 10");

        JScrollPane scrollPane = new JScrollPane(formContainer);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, "grow");
    }

    private void addFormField(JPanel panel, String labelText) {
        JLabel lblNomeProduto = new JLabel(labelText);
        lblNomeProduto.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblNomeProduto.setForeground(new Color(40, 40, 40));
        panel.add(lblNomeProduto, "gaptop 10");

        JTextField textField = new JTextField();
        textField.setBackground(Color.WHITE);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1, true),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
       
        panel.add(textField, "h 40!");
    }

    private JButton createButton(String text, Color bg, Color fg, boolean hasBorder) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(new Font("SansSerif", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);

        if (hasBorder) {
            btn.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(230, 230, 230), 1, true),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
            ));
        } else {
            btn.setBorderPainted(false);
        }
        return btn;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Visualização do Componente - Adicionar Produto");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 900);
            frame.setLocationRelativeTo(null);
           
            frame.getContentPane().add(new TelaAdicionarProduto());
            frame.setVisible(true);
        });
    }
}