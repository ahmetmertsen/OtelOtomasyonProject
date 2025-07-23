package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.LinkedList;
import model.LoginAdmin;
import model.Rezervasyon;
import model.RezervasyonLog;
import service.RezervasyonLogVeriOkuKaydet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RezervasyonAraDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField rezAraIdTextField;
	

	public RezervasyonAraDialog(RezervasyonMenu rezervasyonMenu) {
		setTitle("Rezervasyon Ara");
		setBounds(100, 100, 350, 107);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel rezAraIdText = new JLabel("Rezervasyon ID:");
		rezAraIdText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rezAraIdText.setBounds(32, 10, 106, 19);
		contentPanel.add(rezAraIdText);
		
		rezAraIdTextField = new JTextField();
		rezAraIdTextField.setBounds(126, 10, 128, 19);
		contentPanel.add(rezAraIdTextField);
		rezAraIdTextField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton rezAraAraButton = new JButton("Ara");
				rezAraAraButton.setActionCommand("Ara");
				rezAraAraButton.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		try {
		                    int rezervasyonId = Integer.parseInt(rezAraIdTextField.getText());
		                    Rezervasyon arananRezervasyon = rezervasyonMenu.rezervasyonAra(rezervasyonId);
		                    
		                    if (arananRezervasyon != null) {
		                    RezervasyonLog yeniLog = new RezervasyonLog(LoginAdmin.getGirisYapanAdmin(), arananRezervasyon , "Arama İşlemi");                   
		                    LinkedList<String> yeniLogListesi = new LinkedList<>();
		                    yeniLogListesi.insertLast(yeniLog.toString());
		                    RezervasyonLogVeriOkuKaydet.rezervasyonLogKaydet("src/data/rezervasyonLog.hot", yeniLogListesi, true);
		                    } else {
			                    JOptionPane.showMessageDialog(null, "Rezervasyon bulunamadı!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
			                }
		                    
		                    dispose();
		                } catch (NumberFormatException ex) {
		                    JOptionPane.showMessageDialog(null, "Geçerli bir ID giriniz!", "Hata", JOptionPane.ERROR_MESSAGE);
		                }
		        	}
		        });
				buttonPane.add(rezAraAraButton);
				getRootPane().setDefaultButton(rezAraAraButton);
			}
			{
				JButton rezAraİptalButton = new JButton("İptal");
				rezAraİptalButton.setActionCommand("İptal");
				rezAraİptalButton.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		dispose();
		        	}
		        });
				buttonPane.add(rezAraİptalButton);
			}
		}
	}
}
