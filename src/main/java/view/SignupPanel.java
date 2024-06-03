package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.formdev.flatlaf.extras.components.FlatButton;
import com.formdev.flatlaf.extras.components.FlatPasswordField;
import com.formdev.flatlaf.extras.components.FlatTextField;
import com.google.gson.Gson;

import controller.ClientController;
import models.Employee;
import others.EmployeePermission;
import others.MaHoa;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class SignupPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public FlatButton btnNewButton;

	public SignupPanel(ClientController clientController) {
		setLayout(null);
		setBounds(new Rectangle(415, 500));

		JLabel lblNewLabel = new JLabel("Signup");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Ebrima", Font.BOLD, 40));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(126, 31, 160, 52);
		add(lblNewLabel);

		JLabel lblNewLabel_1_2 = new JLabel("NAME");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Ebrima", Font.BOLD, 13));
		lblNewLabel_1_2.setBounds(56, 108, 274, 14);
		add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_1_1 = new JLabel("PHONE NUMBER");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Ebrima", Font.BOLD, 13));
		lblNewLabel_1_1_1.setBounds(56, 170, 274, 14);
		add(lblNewLabel_1_1_1);

		FlatTextField nameTextField = new FlatTextField();
		nameTextField.setFont(new Font("Tahoma", Font.BOLD, 13));
		nameTextField.setColumns(10);
		nameTextField.setBounds(56, 128, 306, 31);
		add(nameTextField);

		FlatTextField phoneTextField = new FlatTextField();
		phoneTextField.setFont(new Font("Tahoma", Font.BOLD, 13));
		phoneTextField.setColumns(10);
		phoneTextField.setBounds(56, 189, 306, 31);
		add(phoneTextField);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("USERNAME");
		lblNewLabel_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1.setFont(new Font("Ebrima", Font.BOLD, 13));
		lblNewLabel_1_1_1_1.setBounds(56, 231, 274, 14);
		add(lblNewLabel_1_1_1_1);

		FlatTextField userNameTextField = new FlatTextField();
		userNameTextField.setFont(new Font("Tahoma", Font.BOLD, 13));
		userNameTextField.setColumns(10);
		userNameTextField.setBounds(56, 250, 306, 31);
		add(userNameTextField);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("PASSWORD");
		lblNewLabel_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Ebrima", Font.BOLD, 13));
		lblNewLabel_1_1_1_1_1.setBounds(56, 292, 274, 14);
		add(lblNewLabel_1_1_1_1_1);

		FlatTextField passTextField = new FlatTextField();
		passTextField.setFont(new Font("Tahoma", Font.BOLD, 13));
		passTextField.setColumns(10);
		passTextField.setBounds(56, 311, 306, 31);
		add(passTextField);

		btnNewButton = new FlatButton();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameTextField.getText();
				String phone = phoneTextField.getText();
				String username = userNameTextField.getText();
				String password = MaHoa.mahoa(passTextField.getText());
				if (name.equals("") || phone.equals("") || username.equals("") || password.equals("")) {

					JOptionPane.showMessageDialog(btnNewButton, "Vui Lòng điền đủ thông tin!");
				} else {
					Employee employee = new Employee(username, password, name, phone, EmployeePermission.MANAGER);
					employee.setIdRestaurant(1);
					Gson gson = new Gson();
					String employeegson = gson.toJson(employee);
					String[] control = { "signup", employeegson };
					String result = clientController.sendRequest(control);
					
						if (result.equals("0"))
							JOptionPane.showMessageDialog(btnNewButton, "Username đã tồn tại!");

						else {
							JOptionPane.showMessageDialog(btnNewButton, "Đăng Ký Thành Công!");
							nameTextField.setText("");
							phoneTextField.setText("");
							userNameTextField.setText("");
							passTextField.setText("");
						}

				}

			}
		});
		btnNewButton.setBackground(Color.decode("#145DA0"));
		btnNewButton.setFont(new Font("Ebrima", Font.BOLD, 13));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(136, 387, 150, 38);
		btnNewButton.setText("Signup");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(btnNewButton);
	}

}
