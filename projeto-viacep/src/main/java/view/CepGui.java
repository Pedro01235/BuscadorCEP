package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import entities.Endereco;
import entities.ViaCEPClient;
import java.awt.Color;

public class CepGui extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCep;
    private JButton btnBuscar;
    private JLabel lblLogradouro, lblBairro, lblCidade, lblEstado, lblDdd;

    public CepGui() {
    	getContentPane().setBackground(Color.LIGHT_GRAY);
        setTitle("Busca CEP");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel label = new JLabel("CEP:");
        label.setBounds(6, 9, 49, 14);
        getContentPane().add(label);
        txtCep = new JTextField(20);
        txtCep.setBounds(65, 6, 135, 20);
        getContentPane().add(txtCep);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(205, 5, 104, 23);
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCep(txtCep.getText());
            }
        });
        getContentPane().add(btnBuscar);

        lblLogradouro = new JLabel("Logradouro: ");
        lblLogradouro.setBounds(6, 87, 368, 14);
        getContentPane().add(lblLogradouro);

        lblBairro = new JLabel("Bairro: ");
        lblBairro.setBounds(6, 99, 368, 14);
        getContentPane().add(lblBairro);

        lblCidade = new JLabel("Cidade: ");
        lblCidade.setBounds(6, 115, 318, 14);
        getContentPane().add(lblCidade);

        lblEstado = new JLabel("Estado: ");
        lblEstado.setBounds(6, 129, 264, 14);
        getContentPane().add(lblEstado);
        
        lblDdd = new JLabel("DDD: ");
        lblDdd.setBounds(6, 144, 264, 14);
        getContentPane().add(lblDdd);

        setVisible(true);
    }

    private void buscarCep(String cep) {
        Endereco endereco = ViaCEPClient.buscaEnderecoPeloCEP(cep);
        if (endereco != null) {
            lblLogradouro.setText("Logradouro: " + endereco.getLogradouro());
            lblBairro.setText("Bairro: " + endereco.getBairro());
            lblCidade.setText("Cidade: " + endereco.getLocalidade());
            lblEstado.setText("Estado: " + endereco.getUf());
            lblDdd.setText("DDD: " + endereco.getDdd());
        } else {
            JOptionPane.showMessageDialog(this, "CEP não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
