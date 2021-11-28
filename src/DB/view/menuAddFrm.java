package DB.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

import DB.Dao.FoodListDao;
import DB.model.FoodList;
import DB.util.DbUtil;
import DB.util.StringUtil;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class menuAddFrm extends JInternalFrame {
	private JTextField addName;
	private JTextField addPrice;
	private DbUtil dbUtil = new DbUtil();
	private FoodListDao foodListDao = new FoodListDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menuAddFrm frame = new menuAddFrm();
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
	public menuAddFrm() {
		setTitle("\u6DFB\u52A0\u83DC\u5355");
		setFrameIcon(new ImageIcon(menuAddFrm.class.getResource("/images/\u6DFB\u52A0.png")));
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 450, 300);
		
		JLabel lblNewLabel = new JLabel("\u83DC\u54C1\u540D\u79F0");
		lblNewLabel.setFont(new Font("黑体", Font.BOLD, 17));
		
		JLabel lblNewLabel_1 = new JLabel("\u83DC\u54C1\u4EF7\u683C");
		lblNewLabel_1.setFont(new Font("黑体", Font.BOLD, 17));
		
		addName = new JTextField();
		addName.setColumns(10);
		
		addPrice = new JTextField();
		addPrice.setColumns(10);
		
		JButton addBut = new JButton("\u6DFB\u52A0");
		addBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddActionPerformed(e);
			}
		});
		addBut.setIcon(new ImageIcon(menuAddFrm.class.getResource("/images/\u6DFB\u52A0.png")));
		addBut.setFont(new Font("黑体", Font.BOLD, 23));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(70)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1, Alignment.TRAILING)
						.addComponent(lblNewLabel, Alignment.TRAILING))
					.addGap(81)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(addName, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
						.addComponent(addPrice, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
					.addGap(87))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(176)
					.addComponent(addBut)
					.addContainerGap(183, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(57)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(addName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(addPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(40)
					.addComponent(addBut)
					.addContainerGap(60, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}

	/**
	 * 添加菜单操作
	 * @param e
	 */
	private void AddActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(StringUtil.isEmpty(this.addPrice.getText())) {
			JOptionPane.showMessageDialog(null,"价格不能为空");
			return;
		}
		if(StringUtil.isEmpty(this.addName.getText())) {
			JOptionPane.showMessageDialog(null,"菜品名不能为空");
			return;
		}
		FoodList foodlist = new FoodList();
		foodlist.setPrice(Integer.parseInt(this.addPrice.getText()));
		foodlist.setFoodName(this.addName.getText());
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int addnum = foodListDao.add(con, foodlist);
			if(addnum == 1) {
				JOptionPane.showMessageDialog(null,"添加成功");
			}
			else {
				JOptionPane.showMessageDialog(null,"添加失败");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
