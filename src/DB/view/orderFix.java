package DB.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import DB.Dao.AddressDao;
import DB.Dao.FoodOrderDao;
import DB.model.Address;
import DB.model.FoodOrder;
import DB.util.DbUtil;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class orderFix extends JInternalFrame {

	private DbUtil dbUtil = new DbUtil();
	
	private JComboBox orderList = new JComboBox();
	private AddressDao addressDao = new AddressDao();
	
	private FoodOrderDao foodOrderDao = new FoodOrderDao();
	private JTextField addresstext;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					orderFix frame = new orderFix();
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
	public orderFix() {
		getContentPane().setFont(new Font("����", Font.BOLD, 24));
		setFrameIcon(new ImageIcon(orderFix.class.getResource("/images/\u4FEE\u6539_\u586B\u5145.png")));
		setClosable(true);
		setIconifiable(true);
		setTitle("\u8BA2\u5355\u4FEE\u6539");
		setBounds(100, 100, 450, 300);
		
		JButton btnNewButton = new JButton("\u914D\u9001\u5B8C\u6210");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("����", Font.BOLD, 23));
		btnNewButton.setIcon(new ImageIcon(orderFix.class.getResource("/images/_\u4FEE\u6539\u8BA2\u5355.png")));
		
		
		
		JLabel lblNewLabel = new JLabel("\u987E\u5BA2\u59D3\u540D\uFF1A");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("\u76EE\u6807\u5730\u5740\uFF1A");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 20));
		
		addresstext = new JTextField();
		addresstext.setEditable(false);
		addresstext.setColumns(10);
		
		JButton confirmbutton = new JButton("\u786E\u5B9A");
		confirmbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				confirmActionPerformed(arg0);
			}
		});
		confirmbutton.setFont(new Font("����", Font.PLAIN, 15));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(147, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(138))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addGap(18)
							.addComponent(addresstext, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(orderList, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addComponent(confirmbutton, 0, 0, Short.MAX_VALUE)))
					.addContainerGap(83, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(62)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(orderList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(confirmbutton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(addresstext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addComponent(btnNewButton)
					.addContainerGap(85, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		//�������ʼ��
		this.fillOrderList();
		//Ŀ���ַ��ʼ��
		this.filladdress();
	}
	
	
	/**
	 * ȷ����ťʵ��
	 * @param arg0
	 */
	private void confirmActionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.filladdress();
	}

	/**
	 * Ŀ���ַ�ı����ʼ��
	 */
	private void filladdress() {
		FoodOrder foodorder = (FoodOrder)orderList.getSelectedItem();
		Connection con = null;
		Address address = new Address(foodorder.getUserName());
		try {
			con = dbUtil.getCon();
			ResultSet rs = addressDao.list(con, address);
			if(rs.next()) {
				this.addresstext.setText(rs.getString("address"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ʵ�ֶ�����ɰ�ť
	 * @param e
	 */
	protected void finishActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		FoodOrder foodorder = (FoodOrder)orderList.getSelectedItem();
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int fixnum = foodOrderDao.finish(con, foodorder);
			if(fixnum==1) {
				JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
			}
			else {
				JOptionPane.showMessageDialog(null, "�޸�ʧ��");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * ����������ʵ��
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
				// ��foodorder��ͬ�û����ֽ���ȥ�ز���
				foodorder = new FoodOrder();
				foodorder.setFoodName(rs.getString("foodName"));
				foodorder.setPrice(rs.getInt("Price"));
				foodorder.setId(rs.getInt("id"));
				foodorder.setUserName(rs.getString("userName"));
				foodorder.setDone(rs.getInt("done"));
				
				if(!list.contains(foodorder.getUserName())&&foodorder.getDone()==0) {
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
