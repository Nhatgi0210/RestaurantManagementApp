package mainPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import controller.ClientController;
import models.Employee;
import models.Restaurant;
import others.EmployeePermission;
import others.MaHoa;
import view.HomeView;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import java.net.Socket;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;

public class addEmployeeDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private EmployeeManagerPanel employeeManagerPanel;
	private ClientController clientController;
	private int idRestaurant;
	private final JPanel contentPanel = new JPanel();
	private JTextField nameTextField;
	private JTextField phoneTextField;
	private JTextField usernameTextField;
	private JTextField passtextField;
	private JComboBox posisionComboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
//			addEmployee dialog = new addEmployee();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public addEmployeeDialog(EmployeeManagerPanel employeeManagerPanel,int idRestaurant, ClientController clientController) {
		this.employeeManagerPanel = employeeManagerPanel;
		this.clientController = clientController;
		this.idRestaurant = idRestaurant;
	
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.decode("#145DA0"));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Họ Tên Nhân Viên");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 20, 141, 31);
		contentPanel.add(lblNewLabel);
		
		nameTextField = new JTextField();
		nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameTextField.setBounds(172, 21, 271, 31);
		contentPanel.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Số Điện Thoại");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 76, 141, 31);
		contentPanel.add(lblNewLabel_1);
		
		phoneTextField = new JTextField();
		phoneTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		phoneTextField.setColumns(10);
		phoneTextField.setBounds(172, 76, 271, 31);
		contentPanel.add(phoneTextField);
		
		JLabel lblNewLabel_2 = new JLabel("Tên Đăng Nhập");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 131, 141, 31);
		contentPanel.add(lblNewLabel_2);
		
		usernameTextField = new JTextField();
		usernameTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		usernameTextField.setColumns(10);
		usernameTextField.setBounds(172, 131, 271, 31);
		contentPanel.add(usernameTextField);
		
		JLabel lblNewLabel_3 = new JLabel("Mật Khẩu");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(10, 186, 141, 31);
		contentPanel.add(lblNewLabel_3);
		
		passtextField = new JTextField();
		passtextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passtextField.setColumns(10);
		passtextField.setBounds(172, 186, 271, 31);
		contentPanel.add(passtextField);
		
		JLabel lblNewLabel_4 = new JLabel("Chức Vụ");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(10, 241, 141, 31);
		contentPanel.add(lblNewLabel_4);
		
		posisionComboBox = new JComboBox();
		posisionComboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
		posisionComboBox.setBounds(172, 241, 271, 31);
		posisionComboBox.addItem("Quản Lý");
		posisionComboBox.addItem("Thu Ngân");
		posisionComboBox.addItem("Order");
		posisionComboBox.addItem("Đầu Bếp");

		
		contentPanel.add(posisionComboBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(nameTextField.getText().equals("")||
							phoneTextField.getText().equals("")||
							usernameTextField.getText().equals("")||
							passtextField.getText().equals("")
							) {
							JOptionPane.showMessageDialog(contentPanel, "Vui lòng nhập đủ thông tin!");
						}
						else
						addEmployee();
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		setLocationRelativeTo(this.employeeManagerPanel);
		setVisible(true);
	}
	public void addEmployee() {
		Employee employee = new Employee();
		employee.setName(nameTextField.getText());
		employee.setIdRestaurant(idRestaurant);
		
		employee.setPhoneNumber(phoneTextField.getText());
		employee.setUsername(usernameTextField.getText());
		employee.setPassword(MaHoa.mahoa(passtextField.getText()));
		employee.setPermission(EmployeePermission.getByName(posisionComboBox.getSelectedItem().toString()));
		Gson gson = new Gson();
		String employeeJson = gson.toJson(employee);
		String[] control = {"signup",employeeJson};
		
		
			String result = clientController.sendRequest(control);
			if(result.equals("0")) {
				JOptionPane.showMessageDialog(contentPanel, "Tên đăng nhập đã tồn tại");
			}
			else {
				dispose();
				employeeManagerPanel.getAll();
			}
		
		
		
	}
}
