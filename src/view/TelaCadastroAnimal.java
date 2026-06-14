package view;

import javax.swing.JOptionPane;

import model.Animal;
import model.AnimalDAO;
import model.Cliente;
import model.ClienteDAO;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.TipoAnimal;
import javax.swing.JTextField;

public class TelaCadastroAnimal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCpfCliente;

	private JComboBox<TipoAnimal> cbxAnimal;
	private JButton btnSalvar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroAnimal frame = new TelaCadastroAnimal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCadastroAnimal() {
		setTitle("PetAmigo - Cadastro Animal");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 400);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Cadastro de Animais");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(110, 11, 220, 25);
		contentPane.add(lblTitulo);

		JLabel lblNome = new JLabel("Nome ");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome.setBounds(30, 74, 150, 20);
		contentPane.add(lblNome);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNome.setColumns(10);
		txtNome.setBounds(180, 74, 200, 22);
		contentPane.add(txtNome);

	

		// NOVO CAMPO
		JLabel lblCpfCliente = new JLabel("CPF do Dono");
		lblCpfCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCpfCliente.setBounds(30, 120, 150, 20);
		contentPane.add(lblCpfCliente);

		txtCpfCliente = new JTextField();
		txtCpfCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCpfCliente.setColumns(10);
		txtCpfCliente.setBounds(180, 120, 200, 22);
		contentPane.add(txtCpfCliente);

		JLabel lblAnimal = new JLabel("Selecione o Animal:");
		lblAnimal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAnimal.setBounds(30, 167, 150, 20);
		contentPane.add(lblAnimal);

		cbxAnimal = new JComboBox<TipoAnimal>();
		cbxAnimal.setFont(new Font("Tahoma", Font.BOLD, 10));
		cbxAnimal.setModel(new DefaultComboBoxModel<TipoAnimal>(TipoAnimal.values()));
		cbxAnimal.setSelectedIndex(-1);
		cbxAnimal.setBounds(180, 167, 200, 22);
		contentPane.add(cbxAnimal);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalvar.setBounds(147, 233, 120, 30);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    try {

			        if (txtNome.getText().trim().isEmpty()) {
			            JOptionPane.showMessageDialog(null, "Informe o nome do animal.");
			            return;
			        }

			        if (txtCpfCliente.getText().trim().isEmpty()) {
			            JOptionPane.showMessageDialog(null,"Informe o CPF do dono.");
			            return;
			        }

			        if (cbxAnimal.getSelectedItem() == null) {
			            JOptionPane.showMessageDialog(null,"Selecione a espécie.");
			            return;
			        }
			        String nome = txtNome.getText();
			        TipoAnimal especie =(TipoAnimal) cbxAnimal.getSelectedItem();

			        
			        Cliente cliente = new Cliente();
			        cliente.setCpf(txtCpfCliente.getText());
			        ClienteDAO clienteDAO = new ClienteDAO();
			        clienteDAO.setCliente(cliente);

			        if (!clienteDAO.localizar()) {
			            JOptionPane.showMessageDialog(
			                    null, "Cliente não encontrado."
			            );
			            return;
			        }
			        cliente = clienteDAO.getCliente();
			        Animal animal = new Animal(nome, especie);
			        animal.setCliente(cliente);
			        AnimalDAO dao = new AnimalDAO();
			        dao.setAnimal(animal);

			        String mensagem = dao.atualizar(model.TipoOperacaoBD.INCLUSAO);
			        JOptionPane.showMessageDialog(null, mensagem);
			        txtNome.setText("");
			        txtCpfCliente.setText("");
			        cbxAnimal.setSelectedIndex(-1);

			    }

			    catch (Exception ex) {
			        JOptionPane.showMessageDialog(
			                null,
			                "Erro: " + ex.getMessage()
			        );
			    }
			}
		});
		contentPane.add(btnSalvar);

		java.net.URL url =
				getClass().getResource("/Pictures/banana cat.jpg");

		if (url != null) {
			java.awt.Image icone =
					java.awt.Toolkit.getDefaultToolkit()
							.getImage(url);
			this.setIconImage(icone);

		} else {

			System.out.println(
					"Erro: Não encontrei a nova imagem!");
		}
	}
}
