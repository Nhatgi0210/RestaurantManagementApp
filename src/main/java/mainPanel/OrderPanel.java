package mainPanel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import controller.ClientController;
import models.Area;
import models.Employee;
import models.FoodCategory;
import models.FoodItem;
import models.Order;
import models.OrderItem;
import models.Restaurant;
import models.Table;
import others.TableStatus;
import view.OrderFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Label;

public class OrderPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton buttonSelected;
	private ArrayList<FoodItem> products;
	private ArrayList<FoodCategory> types;
	private ClientController clientController;
	private Restaurant restaurant;
	private OrderFrame orderFrame;
	private Table table = new Table();
	private JPanel productPanel;
	private Order order ;
	private ArrayList<OrderItem> orderItemList = new ArrayList<>();
	private ArrayList<OrderItem> tempOrderItemList = new ArrayList<>();
	private Employee employee;
	private JPanel tempOrderPanel;
	private JPanel OrderPanel;
	private JLabel totalNewLabel_1;
	/**
	 * Create the panel.
	 */
	public OrderPanel(OrderFrame orderFrame, ClientController clientController, Restaurant restaurant,Employee employee) {
		this.clientController = clientController;
		this.restaurant = restaurant;
		this.orderFrame = orderFrame;
		this.employee = employee;
		
		UIManager.put("ScrollBar.width", 5);

		setLayout(null);

		getOrder();
//		displayOrder();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 48, 136, 539);
		add(scrollPane);

		JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		typePanel.setBackground(Color.decode("#0F433D"));
		typePanel.setPreferredSize(new Dimension(136, 800));
		scrollPane.setViewportView(typePanel);

		getAllType();
		for (FoodCategory type : types) {
			JButton button = new JButton(type.getName());

			button.setPreferredSize(new Dimension(136, 40));
			button.setBackground(Color.decode("#0F433D"));
			button.setForeground(Color.white);
			button.setBorderPainted(false);
			if (type == types.get(0)) {
				buttonSelected = button;
				buttonSelected.setBackground(Color.decode("#145DA0"));
			}
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonSelected.setBackground(new Color(15, 67, 61));
					buttonSelected = button;
					buttonSelected.setBackground(Color.decode("#145DA0"));
					filter(type.getId());
				}
			});
			typePanel.add(button);
		}

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(135, 48, 623, 539);
		add(scrollPane_1);

		productPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) productPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		scrollPane_1.setViewportView(productPanel);

		filter(types.get(0).getId());
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(757, 48, 272, 484);
		add(scrollPane_2);

		tempOrderPanel = new JPanel();
		tempOrderPanel.setPreferredSize(new Dimension(272,800));
		scrollPane_2.setViewportView(tempOrderPanel);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(1028, 48, 272, 484);
		add(scrollPane_3);

		OrderPanel = new JPanel();
		OrderPanel.setPreferredSize(new Dimension(272, 800));
		scrollPane_3.setViewportView(OrderPanel);

		JButton ordButton = new JButton("Xác nhận");
		ordButton.setForeground(Color.WHITE);
		ordButton.setBackground(Color.decode("#145DA0"));
		ordButton.setBounds(795, 534, 189, 53);
		ordButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(order == null) {
					newOrder();
				}
				addOrderItem();
				updateTable();
			}
		});
		add(ordButton);
		
		JLabel lblNewLabel = new JLabel("Danh sách món");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(101, 11, 124, 28);
		add(lblNewLabel);
		
		JLabel lblMnan = new JLabel("Món đang gọi");
		lblMnan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMnan.setBounds(757, 11, 124, 28);
		add(lblMnan);
		
		JLabel lblMnGi = new JLabel("Món đã gọi");
		lblMnGi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMnGi.setBounds(1028, 9, 124, 28);
		add(lblMnGi);
		
		JButton payButton = new JButton("Thanh toán");
		payButton.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
				pay();
				JOptionPane.showMessageDialog(OrderPanel,"Thanh toán thành công" );
				OrderPanel.this.orderFrame.showlistTable();
			}
		});
		payButton.setBackground(Color.decode("#145DA0"));
		payButton.setForeground(Color.WHITE);
		payButton.setBounds(1028, 534, 136, 53);
		
		add(payButton);
		
		totalNewLabel_1 = new JLabel("");
		totalNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		totalNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		totalNewLabel_1.setBounds(1167, 531, 133, 56);
		add(totalNewLabel_1);
		
		JButton returnButton = new JButton("");
		returnButton.setBounds(2, 11, 50, 28);
		returnButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				OrderPanel.this.orderFrame.showlistTable();
			}
		});
		add(returnButton);

	}

	public void getAllType() {

		Gson gson = new Gson();
		String[] control = { "getAllType", Integer.toString(restaurant.getId()) };
		types = gson.fromJson(clientController.sendRequest(control), new TypeToken<ArrayList<FoodCategory>>() {
		}.getType());
//		loadTypeTable();
	}

	public void filter(int idType) {

		Gson gson = new Gson();
		String[] control = { "getProductByType", Integer.toString(idType) };
		products = gson.fromJson(clientController.sendRequest(control), new TypeToken<ArrayList<FoodItem>>() {
		}.getType());
		loadProduct();

	}
	public void loadProduct() {
		productPanel.removeAll();
		
		for(FoodItem product : products) {
			JButton button = new JButton(product.getName());
			button.setPreferredSize(new Dimension(150, 180));
			button.setVerticalTextPosition(SwingConstants.BOTTOM);
		    button.setHorizontalTextPosition(SwingConstants.CENTER);
		    button.setFont(new Font("Tahoma", Font.BOLD, 14));
		    ImageIcon c = new ImageIcon(new ImageIcon(product.getImagePath()).getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH));
			button.setIcon(c);
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (!checkExistFood(product)) {
						OrderItem temp = new OrderItem();
						temp.setFoodItem(product);
						temp.setQuantity(1);
						if (order != null)
							temp.setIdOrder(order.getId());
						tempOrderItemList.add(temp);
					}
					tempOrderPanel.removeAll();
					for(OrderItem orderItem : tempOrderItemList) {
						JLabel temp2 = new JLabel(orderItem.getFoodName());
						temp2.setPreferredSize(new Dimension(180,30));
						tempOrderPanel.add(temp2);
						JLabel temp3 = new JLabel(orderItem.getFoodPrice()+"x"+orderItem.getQuantity());
						temp3.setPreferredSize(new Dimension(70,30));
						tempOrderPanel.add(temp3);
						temp2.setFont(new Font("Tahoma", Font.BOLD, 13));
						temp3.setFont(new Font("Tahoma", Font.BOLD, 13));
						
					}
					revalidate();
					repaint();
					
				}
			});
			productPanel.add(button);
		}
		revalidate();
		repaint();
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}
	public boolean checkExistFood(FoodItem product) {
		if (tempOrderItemList== null||tempOrderItemList.isEmpty())
			return false;
		for(OrderItem orderItem : tempOrderItemList) {
			if(orderItem.getFoodItem()==product) {
				orderItem.setQuantity(orderItem.getQuantity()+1);
				return true;
			}
		}
		return false;
	}
	public void getOrder() {
		String[] control = {"searchOrder",table.getId()+""};
		String result = clientController.sendRequest(control);
		Gson gson = new  Gson();
		order = gson.fromJson(result, Order.class);
	}
	public void newOrder() {
		Gson gson = new  Gson();
		order = new Order();
		order.setEmployee(employee);
		order.setIdTable(table.getId());
		
		String[] control2 = {"newOrder",gson.toJson(order)};
		String result2 = clientController.sendRequest(control2);
		order.setId(Integer.parseInt(result2));
	}
	public void addOrderItem() {
		Gson gson = new  Gson();
		for(OrderItem orderItem : tempOrderItemList) {
			orderItem.setIdOrder(order.getId());
		}
		String[] control = {"addOrderItem", gson.toJson(tempOrderItemList)};
		clientController.sendRequest(control);
		tempOrderPanel.removeAll();
		for(OrderItem orderItem : tempOrderItemList) {
			orderItemList.add(orderItem);
		}
		for(int i = tempOrderItemList.size()-1; i>=0; i-- ) {
			tempOrderItemList.remove(i);
		}
		loadOrderItem();
	}
	public void loadOrderItem() {
		tempOrderPanel.removeAll();
		OrderPanel.removeAll();
		int total  = 0;
		for(OrderItem orderItem : orderItemList) {
			JLabel temp2 = new JLabel(orderItem.getFoodName());
			temp2.setPreferredSize(new Dimension(180,30));
			OrderPanel.add(temp2);
			JLabel temp3 = new JLabel(orderItem.getFoodPrice()+"x"+orderItem.getQuantity());
			temp3.setPreferredSize(new Dimension(70,30));
			OrderPanel.add(temp3);
			temp2.setFont(new Font("Tahoma", Font.BOLD, 13));
			temp3.setFont(new Font("Tahoma", Font.BOLD, 13));
			total += orderItem.getAmount();
		}
		totalNewLabel_1.setText(total+"");
		revalidate();
		repaint();
	}
	public void updateTable() {
		if (table.getStatus()==TableStatus.FREE) {
			table.setStatus(TableStatus.SERVING);
			String[] control = {"activeTable", table.getId()+""};
			clientController.sendRequest(control);
		}
	}
	public void pay() {
		String[] control = {"pay", order.getId()+"",table.getId()+""};
		clientController.sendRequest(control);
	}
}
