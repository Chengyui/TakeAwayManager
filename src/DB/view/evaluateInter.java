package DB.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;

import DB.model.FoodOrder;
import DB.util.DbUtil;
import DB.Dao.FoodOrderDao;
import DB.model.FoodList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class evaluateInter extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	private DbUtil dbUtil = new DbUtil();
	private FoodOrderDao foodOrderDao = new FoodOrderDao();
	private JComboBox OrderList = new JComboBox();
	private JComboBox foodWithUser = new JComboBox();
	private JTable FoodOrderTable;
	private JTable EvaluationTable;
	private String username;
	private String foodname;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					evaluateInter frame = new evaluateInter();
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
	public evaluateInter() {
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setFrameIcon(new ImageIcon(evaluateInter.class.getResource("/images/classrepair.png")));
		setTitle("\u987E\u5BA2\u8BC4\u4EF7");
		setBounds(100, 100, 595, 468);
		
		JLabel lblNewLabel = new JLabel("\u987E\u5BA2\u8D26\u6237");
		lblNewLabel.setFont(new Font("黑体", Font.BOLD, 20));
		
		//JComboBox OrderList = new JComboBox();
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getUsernamePerformed(e);
			}
		});
		btnNewButton.setFont(new Font("黑体", Font.BOLD, 20));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel_1 = new JLabel("\u83DC\u54C1\u540D\u79F0");
		lblNewLabel_1.setFont(new Font("黑体", Font.BOLD, 20));
		
		JButton btnNewButton_1 = new JButton("\u67E5\u8BE2");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				getEvaluationPerformed(evt);
			}
		});
		btnNewButton_1.setFont(new Font("黑体", Font.BOLD, 20));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(80)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(51)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addGap(18)
									.addComponent(foodWithUser, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(OrderList, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)))
							.addGap(57)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(91, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(OrderList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(foodWithUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1))
					.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
					.addGap(79))
		);
		
		EvaluationTable = new JTable();
		EvaluationTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7528\u6237", "\u83DC\u54C1\u540D\u79F0", "\u8BC4\u4EF7"
			}
		));
		scrollPane.setViewportView(EvaluationTable);
		getContentPane().setLayout(groupLayout);

		this.fillOrderList();
		//this.fillFoodList();
		this.fillTable(new FoodOrder());
	}
	
	protected void getEvaluationPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		FoodOrder foodOrder = new FoodOrder();
		foodOrder.setFoodName((String)this.foodWithUser.getSelectedItem());
		foodname = foodOrder.getFoodName();
		this.fillTable_1(foodOrder);
	}

	protected void getUsernamePerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		FoodOrder foodOrder = new FoodOrder();
		foodOrder = (FoodOrder)this.OrderList.getSelectedItem();
		username = foodOrder.getUserName();
		//System.out.println(foodOrder.getUserName());
		this.fillFoodList(username);
	}

	/**
	 * 初始化表格
	 */
	private void fillTable(FoodOrder foodorder) {
		
		DefaultTableModel dtm=(DefaultTableModel)EvaluationTable.getModel();
		dtm.setRowCount(0);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			ResultSet rs=foodOrderDao.list(con,foodorder );
			while(rs.next()){
				Vector v=new Vector();
				v.add(rs.getString("UserName"));
				v.add(rs.getString("FoodName"));
				v.add(rs.getString("evaluation"));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				DbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void fillTable_1(FoodOrder foodorder) {
		
		DefaultTableModel dtm=(DefaultTableModel)EvaluationTable.getModel();
		dtm.setRowCount(0);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			ResultSet rs=foodOrderDao.list_3(con, foodorder, username, foodname);
			while(rs.next()){
				Vector v=new Vector();
				v.add(rs.getString("UserName"));
				v.add(rs.getString("FoodName"));
				v.add(rs.getString("evaluation"));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				DbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 顾客订单下拉框实现
	 */
	
	private void fillOrderList() {
		Connection con  = null;
		FoodOrder foodorder = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = foodOrderDao.list(con,new FoodOrder());
			String prestring = new String();
			while(rs.next()) {
				// 对foodorder相同用户部分进行去重操作
				foodorder = new FoodOrder();
				foodorder.setFoodName(rs.getString("foodName"));
				foodorder.setPrice(rs.getInt("Price"));
				foodorder.setId(rs.getInt("id"));
				foodorder.setUserName(rs.getString("userName"));
				foodorder.setEvaluation(rs.getString("evaluation"));
				
				if(prestring.equals(foodorder.getUserName())==false) {
					this.OrderList.addItem(foodorder);
					prestring = new String();
					prestring = foodorder.getUserName();
				}		
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
	
	/**
	 * 菜品下拉框实现
	 */
	private void fillFoodList(String userName) {
		Connection con  = null;
		FoodOrder foodorder = null;
		this.foodWithUser.removeAllItems();
		try {
			con = dbUtil.getCon();
			ResultSet rs = foodOrderDao.list(con,new FoodOrder());
			String prestring = new String();
			while(rs.next()) {
				if(rs.getString("userName").equals(userName)==false) {
					continue;
				}
				// 对foodorder相同用户部分进行去重操作
				foodorder = new FoodOrder();
				foodorder.setFoodName(rs.getString("foodName"));
				//foodorder.setPrice(rs.getInt("Price"));
				foodorder.setId(rs.getInt("id"));
				//foodorder.setUserName(rs.getString("userName"));
				//foodorder.setEvaluation(rs.getString("evaluation"));
				
				if(prestring.equals(foodorder.getFoodName())==false) {
					this.foodWithUser.addItem(foodorder.getFoodName());
					prestring = new String();
					prestring = foodorder.getFoodName();
				}		
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
