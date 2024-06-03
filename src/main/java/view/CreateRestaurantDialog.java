package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import DAO.RestaurenDAO;
import controller.ClientController;
import models.Restaurant;

import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class CreateRestaurantDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private HomeView homeView;
	private Restaurant restaurant;
	private ClientController clientController;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
//			CreateRestaurantDialog dialog = new CreateRestaurantDialog();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateRestaurantDialog(HomeView homeView, Restaurant restaurant,ClientController clientController) {
		this.homeView = homeView;
		this.restaurant = restaurant;
		this.clientController = clientController;
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.decode("#145DA0"));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thông tin nhà hàng");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(81, 11, 270, 37);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tên Nhà Hàng");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 73, 88, 24);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Địa Chỉ");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 149, 88, 24);
		contentPanel.add(lblNewLabel_2);
		
		JTextArea nameTextArea = new JTextArea();
		nameTextArea.setLineWrap(true);
		nameTextArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameTextArea.setBounds(108, 73, 316, 49);
		contentPanel.add(nameTextArea);
		
		JTextArea addressTextArea = new JTextArea();
		addressTextArea.setLineWrap(true);
		addressTextArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addressTextArea.setBounds(108, 146, 316, 71);
		contentPanel.add(addressTextArea);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Submit");
				okButton.setForeground(Color.WHITE);
				okButton.setBackground(Color.decode("#0F433D"));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String name = nameTextArea.getText();
						String address = addressTextArea.getText();
						if(name.equals("") || address.equals("")) {
							JOptionPane.showMessageDialog(okButton, "Vui lòng điền đủ thông tin!");
						}
						else {
//							RestaurenDAO.getDao().add(new Restaurant(name,address));
							int id = addRestaurant(name, address);
//							int id = RestaurenDAO.getDao().getID(new Restaurant(name,address));
							
							restaurant.setId(id);
							restaurant.setName(name);
							restaurant.setAddress(address);
							JOptionPane.showMessageDialog(lblNewLabel_2, "Tạo nhà hàng thành công!");
							dispose();
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		setLocationRelativeTo(homeView.getContentPane());
		setVisible(true);
		
	}
	public int addRestaurant(String name, String address) {
		
			String[] control = {"addRestaurantAndGetId", name,address};
			String id = clientController.sendRequest(control);
	
			return Integer.parseInt(id);
	}
	
}
