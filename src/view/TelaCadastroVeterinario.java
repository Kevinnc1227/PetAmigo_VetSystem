package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.BD;
import model.Veterinario;
import model.VeterinarioDAO;
import model.TipoOperacaoBD;

public class TelaCadastroVeterinario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTextField txtNome;
	private JTextField txtCrmv;
	private JTextField txtEmail;
	private JButton btnSalvar;

	/**
	 * Inicializa a aplicação (Método Main para testes isolados da tela).
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroVeterinario frame = new TelaCadastroVeterinario();
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
	public TelaCadastroVeterinario() {
		setTitle("PetAmigo - Cadastro de Veterinários");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Título da Janela
		JLabel lblTitulo = new JLabel("Cadastro de Veterinários");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(110, 11, 220, 25);
		contentPane.add(lblTitulo);

		// Campo Nome Completo
		JLabel lblNome = new JLabel("Nome Completo:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome.setBounds(30, 60, 150, 20);
		contentPane.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(180, 60, 200, 22);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		// Campo CRMV (Diferencial do Veterinário no UML)
		JLabel lblCrmv = new JLabel("CRMV:");
		lblCrmv.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCrmv.setBounds(30, 100, 150, 20);
		contentPane.add(lblCrmv);

		txtCrmv = new JTextField();
		txtCrmv.setBounds(180, 100, 200, 22);
		contentPane.add(txtCrmv);
		txtCrmv.setColumns(10);

		// Campo Email
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(30, 140, 150, 20);
		contentPane.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(180, 140, 200, 22);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		// Botão Salvar
		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalvar.setBounds(150, 240, 120, 30);
		contentPane.add(btnSalvar);

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 1. Etapa de Validação: Nome e CRMV são obrigatórios
				if (txtNome.getText().trim().isEmpty() || txtCrmv.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Nome e CRMV são obrigatórios!", "Aviso",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				// 2. Etapa de Captura: Coleta as Strings dos campos e monta o Modelo
				Veterinario veterinario = new Veterinario();
				veterinario.setNome(txtNome.getText());
				veterinario.setCrmv(txtCrmv.getText());
				veterinario.setEmail(txtEmail.getText());

				// 3. Gerenciamento de Conexão: Instancia o controlador de banco de dados
				BD bd = new BD();

				// 4. Etapa de Persistência: Abre a conexão de forma segura
				if (bd.getConnection()) {
					VeterinarioDAO veterinarioDao = new VeterinarioDAO();
					veterinarioDao.setBd(bd);
					veterinarioDao.setVeterinario(veterinario);

					// Dispara a query através da estrutura Enum que limpamos
					String mensagemRetorno = veterinarioDao.atualizar(TipoOperacaoBD.INCLUSAO);

					// 5. Etapa de Encerramento: Garante o fechamento da conexão aberta pela View
					bd.close();

					// 6. Veredito: Exibe o JOptionPane de retorno para o usuário
					JOptionPane.showMessageDialog(null, mensagemRetorno, "Resultado", JOptionPane.INFORMATION_MESSAGE);

					// Limpa os campos se gravou perfeitamente
					if (mensagemRetorno.contains("sucesso")) {
						limparCampos();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados!", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// É só trocar o final pelo nome da nova foto (ex: "foto_cliente.png")
		java.net.URL url = getClass().getResource("/Pictures/download (1).jpg");

		if (url != null) {
			java.awt.Image icone = java.awt.Toolkit.getDefaultToolkit().getImage(url);
			this.setIconImage(icone);
		} else {
			System.out.println("Erro: Não encontrei a nova imagem!");
		}
	}

	/**
	 * Método auxiliar para limpar o formulário após a gravação com sucesso.
	 */
	private void limparCampos() {
		txtNome.setText("");
		txtCrmv.setText("");
		txtEmail.setText("");
	}

}