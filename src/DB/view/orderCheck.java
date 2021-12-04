package DB.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import DB.Dao.FoodOrderDao;
import DB.model.FoodList;
import DB.model.FoodOrder;
import DB.util.DbUtil;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class orderCheck extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	private DbUtil dbUtil = new DbUtil();
	private FoodOrderDao foodOrderDao = new FoodOrderDao();
	private JComboBox orderList = new JComboBox();
	private JTable FoodOrderTable;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					orderCheck frame = new orderCheck();
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
	public orderCheck() {
		setFrameIcon(new ImageIcon(orderCheck.class.getResource("/images/\u67E5\u770B.png")));
		setTitle("\u8BA2\u5355\u67E5\u770B");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 769, 504);
		
		JLabel lblNewLabel = new JLabel("\u987E\u5BA2\u5E10\u6237\uFF1A");
		lblNewLabel.setFont(new Font("黑体", Font.BOLD, 20));
		
		
		
		JButton orderCheck = new JButton("\u786E\u5B9A");
		orderCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfirmActionPerformed(e);
			}
		});
		orderCheck.setFont(new Font("黑体", Font.BOLD, 23));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(44)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(orderList, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
							.addGap(82)
							.addComponent(orderCheck))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(63)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(266, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(orderList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(orderCheck, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(169, Short.MAX_VALUE))
		);
		
		FoodOrderTable = new JTable();
		FoodOrderTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u987E\u5BA2\u5E10\u6237", "\u83DC\u54C1\u540D\u79F0", "\u83DC\u54C1\u4EF7\u683C"
			}
		));
		scrollPane.setViewportView(FoodOrderTable);
		getContentPane().setLayout(groupLayout);
		//订单下拉框初始化
		this.fillOrderList();
		//订单表格初始化
		this.fillTable(new FoodOrder());
	}
	/**
	 * 初始化表格
	 */
	private void fillTable(FoodOrder foodorder) {
		
		DefaultTableModel dtm=(DefaultTableModel)FoodOrderTable.getModel();
		dtm.setRowCount(0);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			ResultSet rs=foodOrderDao.list(con,foodorder );
			while(rs.next()){
				Vector v=new Vector();
				v.add(rs.getString("UserName"));
				v.add(rs.getString("FoodName"));
				v.add(rs.getInt("Price"));
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
	 * 确定按钮 显示选中的用户的订单
	 * @param e
	 */
	private void ConfirmActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		FoodOrder foodOrder = new FoodOrder();
		foodOrder = (FoodOrder)this.orderList.getSelectedItem();
		this.fillTable(foodOrder);
	}

	/**
	 * 订单下拉框实现
	 */
	
	private void fillOrderList() {
		Connection con  = null;
		FoodOrder foodorder = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = foodOrderDao.list(con,new FoodOrder());
			String prestring = new String();
			List<String> list = new ArrayList<String>();
			while(rs.next()) {
				// 对foodorder相同用户部分进行去重操作
				foodorder = new FoodOrder();
				foodorder.setFoodName(rs.getString("foodName"));
				foodorder.setPrice(rs.getInt("Price"));
				foodorder.setId(rs.getInt("id"));
				foodorder.setUserName(rs.getString("userName"));
				
				if(!list.contains(foodorder.getUserName())) {
					list.add(foodorder.getUserName());
					this.orderList.addItem(foodorder);
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
