package view;

import javax.swing.JOptionPane;

import model.Animal;
import model.AnimalDAO;
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
	private JTextField txtPeso;
	private JComboBox<TipoAnimal> cbxAnimal;
	private JButton btnSalvar;

	/**
	 * Launch the application.
	 */
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
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Cadastro de Animais");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(110, 11, 220, 25);
		contentPane.add(lblTitulo);

		JLabel lblNome = new JLabel("Nome Completo");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome.setBounds(30, 60, 150, 20);
		contentPane.add(lblNome);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNome.setColumns(10);
		txtNome.setBounds(180, 60, 200, 22);
		contentPane.add(txtNome);

		JLabel lblPeso = new JLabel("Peso(Kg)");
		lblPeso.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPeso.setBounds(30, 100, 150, 20);
		contentPane.add(lblPeso);

		txtPeso = new JTextField();
		txtPeso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPeso.setColumns(10);
		txtPeso.setBounds(180, 100, 200, 22);
		contentPane.add(txtPeso);

		JLabel lblAnimal = new JLabel("Selecione o Animal:");
		lblAnimal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAnimal.setBounds(30, 140, 150, 20);
		contentPane.add(lblAnimal);

		cbxAnimal = new JComboBox<TipoAnimal>();
		cbxAnimal.setFont(new Font("Tahoma", Font.BOLD, 10));
		cbxAnimal.setToolTipText("");
		cbxAnimal.setModel(new DefaultComboBoxModel<TipoAnimal>(TipoAnimal.values()));
		cbxAnimal.setSelectedIndex(-1);
		cbxAnimal.setBounds(180, 140, 100, 20);
		contentPane.add(cbxAnimal);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalvar.setBounds(150, 240, 120, 30);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					if (txtNome.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Informe o nome do animal.");
						return;
					}

					if (cbxAnimal.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(null, "Selecione a espécie.");
						return;
					}

					String nome = txtNome.getText();

					float peso = Float.parseFloat(txtPeso.getText());

					TipoAnimal especie = (TipoAnimal) cbxAnimal.getSelectedItem();

					Animal animal = new Animal(nome, especie, peso);

					AnimalDAO dao = new AnimalDAO();
					dao.setAnimal(animal);

					String mensagem = dao.atualizar(model.TipoOperacaoBD.INCLUSAO);

					JOptionPane.showMessageDialog(null, mensagem);

					txtNome.setText("");
					txtPeso.setText("");
					cbxAnimal.setSelectedIndex(-1);

				} catch (NumberFormatException ex) {

					JOptionPane.showMessageDialog(null, "Peso inválido.");

				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		contentPane.add(btnSalvar);
		// É só trocar o final pelo nome da nova foto (ex: "foto_cliente.png")
		java.net.URL url = getClass().getResource("/Pictures/banana cat.jpg");

		if (url != null) {
			java.awt.Image icone = java.awt.Toolkit.getDefaultToolkit().getImage(url);
			this.setIconImage(icone);
		} else {
			System.out.println("Erro: Não encontrei a nova imagem!");
		}
	}
}
