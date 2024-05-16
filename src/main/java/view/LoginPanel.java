package view;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.components.FlatButton;
import com.formdev.flatlaf.extras.components.FlatButton.ButtonType;
import com.formdev.flatlaf.extras.components.FlatPasswordField;
import com.formdev.flatlaf.extras.components.FlatTextField;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import com.google.gson.Gson;

import models.Employee;
import others.MaHoa;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.basic.BasicTextFieldUI;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField usernameTextField;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */

	public LoginPanel(Socket socket) {

		setLayout(null);
		setBounds(new Rectangle(415, 338));

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Ebrima", Font.BOLD, 40));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(138, 32, 130, 52);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("USERNAME");
		lblNewLabel_1.setFont(new Font("Ebrima", Font.BOLD, 13));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(53, 103, 274, 14);
		add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("PASSWORD");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Ebrima", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(53, 179, 274, 14);
		add(lblNewLabel_1_1);

		usernameTextField = new FlatTextField();
		usernameTextField.setFont(new Font("Tahoma", Font.BOLD, 13));
		usernameTextField.setBounds(53, 123, 306, 42);
//		textField.setBackground(rgba);
		add(usernameTextField);
		usernameTextField.setColumns(10);

		passwordField = new FlatPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 13));
		passwordField.setBounds(53, 199, 306, 42);
//		passwordField.setBackground(new Color(255,255,255,75));
		add(passwordField);

		FlatButton btnNewButton = new FlatButton();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameTextField.getText();
				String password = MaHoa.mahoa(passwordField.getText());
				Employee employee = new Employee(username, password);
				Gson gson = new Gson();
				String employeegson = gson.toJson(employee);
				String[] control = { "login", employeegson };
				try {
					ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
//					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//					DataInputStream in = new DataInputStream(socket.getInputStream());
					BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
					out.writeObject(control);
					String stringEmployeeResult = in.readLine();
					if (stringEmployeeResult.equals("0")) {

						JOptionPane.showMessageDialog(btnNewButton, "Sai tên tài khoản hoặc mật khẩu!");

					}
					else {
						Employee employeeResult = gson.fromJson(stringEmployeeResult, Employee.class);
						new HomeView(employeeResult,socket);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBackground(Color.decode("#145DA0"));
		btnNewButton.setFont(new Font("Ebrima", Font.BOLD, 13));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(133, 265, 150, 38);
		btnNewButton.setText("Login");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(btnNewButton);
	}
}
