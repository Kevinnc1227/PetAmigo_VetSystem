// Autor: Kevin
package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import model.Cliente;
import model.ClienteDAO;
import model.TipoOperacaoBD;

public class TelaCadastroCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtEmail;
	private JTextField txtEndereco;
	private JTextField txtTelefone;
	private JButton btnSalvar;

	/**
	 * Inicializa a aplicação (Método Main para testes da tela).
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				TelaCadastroCliente dialog = new TelaCadastroCliente();
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public TelaCadastroCliente() {
		super((Frame) null, true);
		initGUI();
	}

	public TelaCadastroCliente(Frame parent, boolean modal) {
		super(parent, modal);
		initGUI();
	}

	/**
	 * Cria e configura o Dialog.
	 */
	private void initGUI() {
		setTitle("PetAmigo - Cadastro de Clientes");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Evita fechar o programa inteiro ao fechar só a tela
		setBounds(100, 100, 450, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(getOwner()); // Centraliza em relação ao pai

		JLabel lblTitulo = new JLabel("Cadastro de Clientes");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(110, 11, 220, 25);
		contentPane.add(lblTitulo);

		// Campo Nome
		JLabel lblNome = new JLabel("Nome Completo:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome.setBounds(30, 60, 150, 20);
		contentPane.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(180, 60, 200, 22);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		// Campo CPF
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCpf.setBounds(30, 100, 150, 20);
		contentPane.add(lblCpf);

		txtCpf = new JTextField();
		txtCpf.setBounds(180, 100, 200, 22);
		contentPane.add(txtCpf);
		txtCpf.setColumns(10);

		// Campo Email
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(30, 140, 150, 20);
		contentPane.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(180, 140, 200, 22);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		// Campo Endereço
		JLabel lblEndereco = new JLabel("Endereço:");
		lblEndereco.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEndereco.setBounds(30, 180, 150, 20);
		contentPane.add(lblEndereco);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(180, 180, 200, 22);
		contentPane.add(txtEndereco);
		txtEndereco.setColumns(10);

		// Campo Telefone
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTelefone.setBounds(30, 220, 150, 20);
		contentPane.add(lblTelefone);

		txtTelefone = new JTextField();
		txtTelefone.setBounds(180, 220, 200, 22);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);

		// Botão Salvar (Com os Eventos Conectados)
		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalvar.setBounds(150, 270, 120, 30);
		contentPane.add(btnSalvar);

		btnSalvar.addActionListener(e -> {
			// Validar se os campos não estão vazios
			if (txtNome.getText().trim().isEmpty() || txtCpf.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Nome e CPF são obrigatórios!", "Aviso",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			// Capturar os dados digitados na tela e montar o Modelo
			Cliente cliente = new Cliente();
			cliente.setNome(txtNome.getText());
			cliente.setCpf(txtCpf.getText());
			cliente.setEmail(txtEmail.getText());
			cliente.setEndereco(txtEndereco.getText());
			cliente.setTelefone(txtTelefone.getText());

			ClienteDAO clienteDao = new ClienteDAO();
			clienteDao.setCliente(cliente);

			// Executa a inclusão utilizando a nossa classe TipoOperacaoBD
			String mensagemRetorno = clienteDao.atualizar(TipoOperacaoBD.INCLUSAO);

			// Exibe o veredito para o usuário
			JOptionPane.showMessageDialog(null, mensagemRetorno, "Resultado", JOptionPane.INFORMATION_MESSAGE);

			// Limpa os campos da tela se der certo
			if (mensagemRetorno.contains("sucesso")) {
				limparCampos();
			}
		});

		java.net.URL url = getClass().getResource("/Pictures/download.jpg");

		if (url != null) {
			java.awt.Image icone = java.awt.Toolkit.getDefaultToolkit().getImage(url);
			this.setIconImage(icone);
		} else {
			System.out.println("Erro: Não encontrei a nova imagem!");
		}
	}

	private void limparCampos() {
		txtNome.setText("");
		txtCpf.setText("");
		txtEmail.setText("");
		txtEndereco.setText("");
		txtTelefone.setText("");
	}
}