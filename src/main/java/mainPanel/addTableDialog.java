package mainPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.google.gson.Gson;

import controller.ClientController;
import models.Area;
import models.Employee;
import models.FoodCategory;
import models.FoodItem;
import models.Table;
import others.EmployeePermission;
import others.MaHoa;

public class addTableDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private AreaManagerPanel areaManagerPanel;
	private ClientController clientController;
	private int idRestaurant;
	private final JPanel contentPanel = new JPanel();
	private JTextField nameTextField;
	private JComboBox areaComboBox;
	private ArrayList<Area> areas;
	private Path imagePath = null;
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
	public addTableDialog(AreaManagerPanel areaManagerPanel,int idRestaurant, ClientController clientController, ArrayList<Area> areas) {
		this.areaManagerPanel =  areaManagerPanel;
		this.clientController = clientController;
		this.idRestaurant = idRestaurant;
		this.areas = areas;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 375, 279);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.decode("#145DA0"));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Khu vực");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(16, 131, 94, 31);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tên Bàn");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(38, 77, 72, 31);
		contentPanel.add(lblNewLabel_1);
		
		nameTextField = new JTextField();
		nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameTextField.setColumns(10);
		nameTextField.setBounds(120, 76, 162, 31);
		contentPanel.add(nameTextField);
		
		areaComboBox = new JComboBox();
		areaComboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
		areaComboBox.setBounds(119, 130, 167, 31);
		for(Area area : this.areas) {
			areaComboBox.addItem(area.getName());
		}

		
		contentPanel.add(areaComboBox);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(nameTextField.getText().equals("")) {
							JOptionPane.showMessageDialog(contentPanel, "Vui lòng nhập đủ thông tin!");
						}
						else {
							if(!exist())
								addTable();
							else JOptionPane.showMessageDialog(contentPanel, "Tên bàn đã tồn tại!");
						}
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
		
		setLocationRelativeTo(this.areaManagerPanel);
		setVisible(true);
	}
	public void addTable() {
		Table table = new Table();
		table.setName(nameTextField.getText());
		String temp = (String)areaComboBox.getSelectedItem();
		for(Area area : areas) {
			if (temp.equals(area.getName())) {
				table.setArea(area);
				break;
			}
		}
		Gson gson = new Gson();
		String tableJson = gson.toJson(table);
		String[] control = {"addTable",tableJson};
		String result = clientController.sendRequest(control);
			if(result.equals("0")) {
				JOptionPane.showMessageDialog(contentPanel, "Thêm thất bại!");
			}
			else {
				dispose();
				areaManagerPanel.getAllTable();
				areaManagerPanel.getComboBox().setSelectedIndex(0);
			}	
	}
	public boolean exist() {
		String[] control = {"checkTableExist", nameTextField.getText(),idRestaurant+""};
		String result = clientController.sendRequest(control);
		if(result.equals("0"))
			return false;
		else 
			return true;
	}
}
