//ϵͳ������
package DB.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DB.Dao.FoodListDao;
import DB.model.FoodList;
import DB.model.User;
import DB.util.DbUtil;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;

public class MainFrm extends JFrame {

	private JPanel table;
	private JTable MenuTable;
	private DbUtil dbUtil = new DbUtil();
	private FoodListDao foodListDao = new FoodListDao(); 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrm frame = new MainFrm(new User());
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
	public MainFrm(User user) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrm.class.getResource("/images/\u7F8E\u56E2\u5916\u5356.png")));
		setTitle("\u5916\u5356\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 798, 472);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("΢���ź�", Font.BOLD, 16));
		menuBar.setBackground(SystemColor.activeCaption);
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("  \u83DC\u5355     |  ");
		mnNewMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u83DC\u5355 (2).png")));
		menuBar.add(mnNewMenu);
		
		JMenuItem menuF = new JMenuItem("\u83DC\u5355\u4FEE\u6539");
		menuF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuFixInter menufix = new menuFixInter();
				menufix.setVisible(true);
				table.add(menufix);
			}
		});
		
		JMenuItem menu_1 = new JMenuItem("\u70B9\u83DC");
		mnNewMenu.add(menu_1);
		menu_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuInter menu=new MenuInter(user);
				menu.setVisible(true);
				table.add(menu);
			}
		});
		menu_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u67E5\u770B.png")));
		menuF.setIcon(new ImageIcon(MainFrm.class.getResource("/images/_\u4FEE\u6539\u8BA2\u5355.png")));
		mnNewMenu.add(menuF);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u83DC\u5355\u6DFB\u52A0");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuAddFrm menuadd = new menuAddFrm();
				menuadd.setVisible(true);
				table.add(menuadd);
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u6DFB\u52A0.png")));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("  \u8BA2\u5355    |  ");
		mnNewMenu_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u83DC\u5355 (3).png")));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem order = new JMenuItem("\u67E5\u770B\u8BA2\u5355");
		order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				orderCheck ordercheck = new orderCheck();
				ordercheck.setVisible(true);
				table.add(ordercheck);
			}
		});
		order.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u67E5\u770B.png")));
		mnNewMenu_1.add(order);
		
		JMenuItem orderFix = new JMenuItem("\u4FEE\u6539\u8BA2\u5355");
		orderFix.setIcon(new ImageIcon(MainFrm.class.getResource("/images/_\u4FEE\u6539\u8BA2\u5355.png")));
		mnNewMenu_1.add(orderFix);
		
		JMenu mnNewMenu_2 = new JMenu("\u65E5\u6536\u5165\u67E5\u770B");
		mnNewMenu_2.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u67E5\u770B.png")));
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u67E5\u770B");
		mnNewMenu_2.add(mntmNewMenuItem_1);
		
		JMenuBar menuBar_1 = new JMenuBar();
		mnNewMenu_2.add(menuBar_1);
		table = new JPanel();
		table.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(table);
		
		JDesktopPane desktopPane = new JDesktopPane();
		
		JLabel lblNewLabel = new JLabel("\u4ECA\u65E5\u83DC\u5355");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 23));
		lblNewLabel.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u83DC\u5355 (4).png")));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_table = new GroupLayout(table);
		gl_table.setHorizontalGroup(
			gl_table.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_table.createSequentialGroup()
					.addGroup(gl_table.createParallelGroup(Alignment.LEADING)
						.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 645, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_table.createSequentialGroup()
							.addGap(297)
							.addComponent(lblNewLabel))
						.addGroup(gl_table.createSequentialGroup()
							.addGap(196)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(125, Short.MAX_VALUE))
		);
		gl_table.setVerticalGroup(
			gl_table.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_table.createSequentialGroup()
					.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(226)
					.addComponent(lblNewLabel)
					.addGap(39)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		MenuTable = new JTable();
		MenuTable.setFont(new Font("����", Font.PLAIN, 18));
		MenuTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u83DC\u54C1\u540D\u79F0", "\u4EF7\u683C"
			}
		));
		scrollPane.setViewportView(MenuTable);
		table.setLayout(gl_table);
		//����Ĭ����󻯴���
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//ִ�в˵���ѯ
		this.fillTable(new FoodList());
	}
	
	/**
	 * ��ʼ���˵�����
	 */
	private void fillTable(FoodList foodList) {
		DefaultTableModel dtm=(DefaultTableModel)MenuTable.getModel();
		dtm.setRowCount(0);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = foodListDao.list(con,foodList);
			while(rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("FoodName"));
				v.add(rs.getString("Price"));
				dtm.addRow(v);
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