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
import models.Employee;
import models.FoodCategory;
import models.FoodItem;
import others.EmployeePermission;
import others.MaHoa;

public class addProductDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private ProductPanel productPanel;
	private ClientController clientController;
	private int idRestaurant;
	private final JPanel contentPanel = new JPanel();
	private JTextField nameTextField;
	private JTextField unitTextField;
	private JTextField priceTextField;
	private JComboBox typeComboBox;
	private ArrayList<FoodCategory> foodCategories;
	private JLabel imageLabel;
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
	public addProductDialog(ProductPanel productPanel,int idRestaurant, ClientController clientController, ArrayList<FoodCategory> foodCategories) {
		this.productPanel =  productPanel;
		this.clientController = clientController;
		this.idRestaurant = idRestaurant;
		this.foodCategories = foodCategories;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 321);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.decode("#145DA0"));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên Sản Phẩm");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(16, 189, 94, 31);
		contentPanel.add(lblNewLabel);
		
		nameTextField = new JTextField();
		nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameTextField.setBounds(120, 189, 354, 31);
		contentPanel.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Đơn Vị Tính");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(38, 77, 72, 31);
		contentPanel.add(lblNewLabel_1);
		
		unitTextField = new JTextField();
		unitTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		unitTextField.setColumns(10);
		unitTextField.setBounds(120, 76, 162, 31);
		contentPanel.add(unitTextField);
		
		JLabel lblNewLabel_2 = new JLabel("Giá");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(38, 132, 72, 31);
		contentPanel.add(lblNewLabel_2);
		
		priceTextField = new JTextField();
		priceTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		priceTextField.setColumns(10);
		priceTextField.setBounds(120, 131, 162, 31);
		contentPanel.add(priceTextField);
		
		JLabel lblNewLabel_4 = new JLabel("Loại");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(33, 22, 72, 31);
		contentPanel.add(lblNewLabel_4);
		
		typeComboBox = new JComboBox();
		typeComboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
		typeComboBox.setBounds(115, 21, 167, 31);
		for(FoodCategory foodCategory:this.foodCategories) {
			typeComboBox.addItem(foodCategory.getName());
		}

		
		contentPanel.add(typeComboBox);
		
		imageLabel = new JLabel("");
		imageLabel.setBounds(296, 22, 151, 141);
		contentPanel.add(imageLabel);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(449, 22, 35, 31);
		btnNewButton.setIcon(new FlatSVGIcon("svg/OpenFolder.svg"));
		btnNewButton.setBackground(Color.decode("#0F433D"));
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openFile();
			}
		});
		contentPanel.add(btnNewButton);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(nameTextField.getText().equals("")||
							unitTextField.getText().equals("")||
							priceTextField.getText().equals("")
							) {
							JOptionPane.showMessageDialog(contentPanel, "Vui lòng nhập đủ thông tin!");
						}
						else {
							addFoodItem();
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
		
		setLocationRelativeTo(this.productPanel);
		setVisible(true);
	}
	public void addFoodItem() {
		FoodItem foodItem = new FoodItem();
		foodItem.setName(nameTextField.getText());
		foodItem.setUnitName(unitTextField.getText());
		foodItem.setUnitPrice(Integer.parseInt(priceTextField.getText()));
		String temp = (String)typeComboBox.getSelectedItem();
		for(FoodCategory category : foodCategories) {
			if (temp.equals(category.getName())) {
				foodItem.setIdCategory(category.getId());
				break;
			}
		}
		if (imagePath != null) {
			foodItem.setImagePath(copyFile(imagePath));
		}
		Gson gson = new Gson();
		String foodJson = gson.toJson(foodItem);
		String[] control = {"addProduct",foodJson};
		String result = clientController.sendRequest(control);
			if(result.equals("0")) {
				JOptionPane.showMessageDialog(contentPanel, "Thêm thất bại!");
			}
			else {
				dispose();
				productPanel.getAllProduct();
				productPanel.getComboBox().setSelectedIndex(0);
			}	
	}
	
	private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif","gfif");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
			try {
				BufferedImage originalImage = ImageIO.read(selectedFile);
				Image image = originalImage.getScaledInstance(151, 141, Image.SCALE_SMOOTH);
				imageLabel.setIcon(new ImageIcon(image));
				imagePath = selectedFile.toPath();  									
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }
    }
	private String copyFile(Path sourcePath) {
		String fileName = sourcePath.getFileName().toString();
		Path destinationPath;
		
			try {
				String temp = (getClass().getResource("/image/")+fileName).substring(6);
				System.out.println(temp);
				destinationPath = 	Paths.get(temp);
				Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
				return destinationPath.toString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return null;
	}
}
