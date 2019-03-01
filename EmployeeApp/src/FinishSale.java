import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;

public class FinishSale extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField customer;
	private JTextField IMEI;
	private JTextField totalcash;
	private JTextField cash;
	private JTextField txtChange;
	private JButton btnCalculate;
	private JButton btnPrint;


	/**
	 * Create the dialog.
	 */
	Connection con;
	String userWhoSold, total;
	boolean clearCart = false;
	public FinishSale(JFrame frame, final String userWhoSold, final String total) {
		super(frame, true);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
		this.userWhoSold = userWhoSold;
		this.total = total;

		con = DbConnect.getDbConnect();
		setBounds(100, 100, 511, 599);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new CardLayout(0, 0));
		setLocationRelativeTo(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			contentPanel.add(panel, "name_2050145482316");
			panel.setLayout(null);
			{
				JDesktopPane desktopPane = new JDesktopPane();
				desktopPane.setBackground(new Color(100, 149, 237));
				desktopPane.setBounds(10, 11, 465, 40);
				panel.add(desktopPane);
				{
					JLabel label = new JLabel("PAYMENT");
					label.setHorizontalAlignment(SwingConstants.CENTER);
					label.setFont(new Font("Tahoma", Font.BOLD, 14));
					label.setBounds(0, 0, 465, 37);
					desktopPane.add(label);
				}
				{
					JLabel label = new JLabel("");
					label.setBounds(1, 3, 46, 37);
					desktopPane.add(label);
				}
			}
			
			JLabel label = new JLabel("Customer:");
			label.setHorizontalAlignment(SwingConstants.TRAILING);
			label.setFont(new Font("Tahoma", Font.BOLD, 13));
			label.setBounds(10, 71, 73, 31);
			panel.add(label);
			
			customer = new JTextField();
			customer.setHorizontalAlignment(SwingConstants.LEFT);
			customer.setFont(new Font("Tahoma", Font.PLAIN, 18));
			customer.setColumns(10);
			customer.setBounds(110, 71, 365, 31);
			panel.add(customer);
			
			JLabel label_1 = new JLabel("IMEI:");
			label_1.setHorizontalAlignment(SwingConstants.TRAILING);
			label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
			label_1.setBounds(10, 127, 73, 31);
			panel.add(label_1);
			
			IMEI = new JTextField();
			IMEI.setHorizontalAlignment(SwingConstants.LEFT);
			IMEI.setFont(new Font("Tahoma", Font.PLAIN, 18));
			IMEI.setColumns(10);
			IMEI.setBounds(110, 127, 365, 31);
			panel.add(IMEI);
			
			JLabel label_2 = new JLabel("Total:");
			label_2.setHorizontalAlignment(SwingConstants.TRAILING);
			label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
			label_2.setBounds(10, 183, 73, 31);
			panel.add(label_2);
			
			totalcash = new JTextField();
			totalcash.setText("0.00");
			totalcash.setHorizontalAlignment(SwingConstants.LEFT);
			totalcash.setFont(new Font("Tahoma", Font.PLAIN, 18));
			totalcash.setEditable(false);
			totalcash.setColumns(10);
			totalcash.setBounds(110, 183, 365, 31);
			panel.add(totalcash);
			
			JLabel label_3 = new JLabel("Cash:");
			label_3.setHorizontalAlignment(SwingConstants.TRAILING);
			label_3.setFont(new Font("Tahoma", Font.BOLD, 13));
			label_3.setBounds(16, 231, 67, 31);
			panel.add(label_3);
			
			cash = new JTextField();
			cash.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					try {
						float total = Float.parseFloat(totalcash.getText());
						if (!cash.getText().trim().equals("")) {
							float cashg = Float.parseFloat(cash.getText());
							if (total <= cashg) {
						          btnCalculate.setEnabled(true);
					       } else {
					    	   btnCalculate.setEnabled(false);
						       btnPrint.setVisible(false);
						       txtChange.setVisible(false);
					       }
						} else {
							btnCalculate.setEnabled(false);
							btnPrint.setVisible(false);
							txtChange.setVisible(false);
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage()+" Please enter valid cash");
						btnCalculate.setEnabled(false);
						btnPrint.setVisible(false);
						txtChange.setVisible(false);
					}
			      }
				
			});
			cash.setHorizontalAlignment(SwingConstants.LEFT);
			cash.setFont(new Font("Tahoma", Font.PLAIN, 18));
			cash.setColumns(10);
			cash.setBounds(110, 231, 365, 31);
			panel.add(cash);
			
			btnCalculate = new JButton("Calculate");
			btnCalculate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					float total = Float.parseFloat(totalcash.getText());
/* 1025 */         float cashg = Float.parseFloat(cash.getText());
/* 1026 */         int Total = (int)total;
/* 1027 */         int Cash = (int)cashg;
/* 1028 */         int bal = Cash - Total;
/* 1029 */         txtChange.setVisible(true);
/* 1030 */         txtChange.setText("Change: = " + bal);
/* 1031 */         btnPrint.setVisible(true);
				}
			});
			btnCalculate.setFont(new Font("Tahoma", Font.BOLD, 18));
			btnCalculate.setFocusPainted(false);
			btnCalculate.setEnabled(false);
			btnCalculate.setBorderPainted(false);
			btnCalculate.setBackground(SystemColor.activeCaption);
			btnCalculate.setBounds(110, 273, 365, 64);
			panel.add(btnCalculate);
			
			txtChange = new JTextField();
			txtChange.setHorizontalAlignment(SwingConstants.LEFT);
			txtChange.setFont(new Font("Tahoma", Font.PLAIN, 18));
			txtChange.setColumns(10);
			txtChange.setBounds(110, 370, 365, 31);
			panel.add(txtChange);
			
			btnPrint = new JButton("Print Receipt");
			btnPrint.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnPrint.setEnabled(false);


				}
			});

			btnPrint.setFont(new Font("Tahoma", Font.BOLD, 18));
			btnPrint.setFocusPainted(false);
			btnPrint.setBorderPainted(false);
			btnPrint.setBackground(SystemColor.activeCaption);
			btnPrint.setBounds(110, 412, 365, 64);
			panel.add(btnPrint);
			
			totalcash.setText(total);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						clearCart = false;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
