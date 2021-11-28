package DB.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import DB.Dao.FoodListDao;
import DB.Dao.FoodOrderDao;
import DB.model.FoodList;
import DB.model.FoodOrder;
import DB.model.User;
import DB.util.DbUtil;
import DB.util.StringUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class MenuInter extends JInternalFrame {
	private JTextField AccountText;
	private JTextField PriceText = new JTextField();
	private DbUtil dbUtil = new DbUtil();
	private FoodListDao foodListDao = new FoodListDao();
	private JComboBox FoodListSelect = new JComboBox();
	private FoodOrderDao foodOrderDao = new FoodOrderDao();
	private User user = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuInter frame = new MenuInter(new User());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuInter(User user) {
		this.user = user;
		setFrameIcon(new ImageIcon(MenuInter.class.getResource("/images/\u5217\u8868-\u76EE\u5F55.png")));
		setClosable(true);
		setIconifiable(true);
		setTitle("\u83DC\u5355");
		setBounds(100, 100, 450, 300);
		
		JLabel lblNewLabel_1 = new JLabel("\u987E\u5BA2\u5E10\u6237");
		lblNewLabel_1.setFont(new Font("黑体", Font.BOLD, 17));
		
		AccountText = new JTextField();
		AccountText.setEditable(false);
		AccountText.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u83DC\u54C1\u4FE1\u606F");
		lblNewLabel.setFont(new Font("黑体", Font.BOLD, 17));
		
		
		
		JLabel lblNewLabel_2 = new JLabel("\u83DC\u54C1\u4EF7\u683C");
		lblNewLabel_2.setFont(new Font("黑体", Font.BOLD, 17));
		
		
		PriceText.setEditable(false);
		PriceText.setColumns(10);
		
		JButton GenerateOrderButton = new JButton("\u751F\u6210\u8BA2\u5355");
		GenerateOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerateOrderActionPerformed(e);
			}
		});
		GenerateOrderButton.setIcon(new ImageIcon(MenuInter.class.getResource("/images/\u5B8C\u6210 (1).png")));
		GenerateOrderButton.setFont(new Font("黑体", Font.BOLD, 17));
		
		JButton GetPriceButton = new JButton("\u786E\u5B9A");
		GetPriceButton.setIcon(new ImageIcon(MenuInter.class.getResource("/images/add.png")));
		GetPriceButton.setFont(new Font("黑体", Font.PLAIN, 16));
		GetPriceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPriceActionPerformed(e);
			}
		});
		
		JButton btnNewButton = new JButton("\u7EE7\u7EED\u70B9\u83DC");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResetActionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(MenuInter.class.getResource("/images/\u518D\u6B21.png")));
		btnNewButton.setFont(new Font("黑体", Font.BOLD, 17));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(25)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(FoodListSelect, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel_1)
											.addGap(18)
											.addComponent(AccountText, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addGap(18)
									.addComponent(PriceText, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnNewButton)
										.addComponent(GetPriceButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(GenerateOrderButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(159)))
					.addGap(120))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(AccountText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(FoodListSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(PriceText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(GetPriceButton))
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(GenerateOrderButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(24))
		);
		getContentPane().setLayout(groupLayout);
		//菜单下拉框初始化
		this.fillFoodListSelect();
		FoodList foodlist =(FoodList)FoodListSelect.getSelectedItem();
		this.PriceText.setText(String.valueOf(foodlist.getPrice()));
		
	}
	
	/**
	 * 重置按钮 继续点菜
	 * @param e
	 */
	protected void ResetActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.PriceText.setText("");
	}

	/*
	 * 显示价格按钮
	 */
	private void getPriceActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		FoodList foodlist =(FoodList)FoodListSelect.getSelectedItem();
		this.PriceText.setText(String.valueOf(foodlist.getPrice()));		
	}

	/**
	 * 订单生成功能实现
	 * 及价格显示
	 * @param e
	 */
	private void GenerateOrderActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String pricetext = this.PriceText.getText();
		if(StringUtil.isEmpty(pricetext)) {
			JOptionPane.showMessageDialog(null, "价格不能为空");
			return;
		}
		FoodOrder foodOrder = new FoodOrder();
		foodOrder.setUserName(user.getUserName());
		FoodList foodlist =(FoodList)FoodListSelect.getSelectedItem();
		foodOrder.setFoodName(foodlist.getFoodName());
		foodOrder.setPrice(foodlist.getPrice());
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int flag = foodOrderDao.add(con, foodOrder);
			if(flag==1){
				JOptionPane.showMessageDialog(null, "点菜成功");
				
			}else{
				JOptionPane.showMessageDialog(null, "点菜失败");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	
	}

	

	/**
	 * 菜单下拉框实现
	 * 以及顾客帐户显示
	 */
	private void fillFoodListSelect() {
		this.AccountText.setText(user.getUserName());
		Connection con  = null;
		FoodList foodList = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = foodListDao.list(con,new FoodList());
			while(rs.next()) {
				foodList = new FoodList();
				foodList.setFoodName(rs.getString("FoodName"));
				foodList.setPrice(rs.getInt("Price"));
				foodList.setId(rs.getInt("id"));
				this.FoodListSelect.addItem(foodList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				DbUtil.closeCon(con);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
