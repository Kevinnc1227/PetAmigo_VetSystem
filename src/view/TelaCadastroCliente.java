package view;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.BD;
import model.Cliente;
import model.ClienteDAO;
import model.TipoOperacaoBD;

public class TelaCadastroCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtEmail;
	private JButton btnSalvar;

	/**
	 * Inicializa a aplicação (Método Main para testes da tela).
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroCliente frame = new TelaCadastroCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Cria e configura o Frame (Construtor).
	 */
	public TelaCadastroCliente() {
		setTitle("Cadastro de Clientes - PetAmigo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Evita fechar o programa inteiro ao fechar só a tela
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Campo Nome
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 10, 44, 12);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(64, 7, 250, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		//Campo CPF 
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(10, 50, 44, 12);
		contentPane.add(lblCpf);
		
		txtCpf = new JTextField();
		txtCpf.setBounds(64, 47, 150, 20);
		contentPane.add(txtCpf);
		txtCpf.setColumns(10);
		
		//Campo Email 
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 98, 44, 12);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(64, 95, 250, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		//Botão Salvar (Com os Eventos Conectados) 
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(64, 142, 100, 25);
		contentPane.add(btnSalvar);
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Validar se os campos não estão vazios
				if (txtNome.getText().trim().isEmpty() || txtCpf.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Nome e CPF são obrigatórios!", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}

				//Capturar os dados digitados na tela e montar o Modelo
				Cliente cliente = new Cliente();
				cliente.setNome(txtNome.getText());
				cliente.setCpf(txtCpf.getText());
				cliente.setEmail(txtEmail.getText());

				BD bd = new BD();
				
				//Tentar conectar
				if (bd.connect()) {
					ClienteDAO clienteDao = new ClienteDAO();
					clienteDao.setBd(bd);
					clienteDao.setCliente(cliente);
					
					//Executa a inclusão utilizando a nossa classe TipoOperacaoBD
					String mensagemRetorno = clienteDao.atualizar(TipoOperacaoBD.INCLUSAO);
					
					//Fecha a conexão com o banco de dados de forma segura
					bd.close();
					
					//Exibe o veredito para o usuário
					JOptionPane.showMessageDialog(null, mensagemRetorno, "Resultado", JOptionPane.INFORMATION_MESSAGE);
					
					//Limpa os campos da tela se der certo
					if (mensagemRetorno.contains("sucesso")) {
						limparCampos();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados!", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	/**
	 * Método auxiliar para limpar o formulário após salvar.
	 */
	private void limparCampos() {
		txtNome.setText("");
		txtCpf.setText("");
		txtEmail.setText("");
	}
}