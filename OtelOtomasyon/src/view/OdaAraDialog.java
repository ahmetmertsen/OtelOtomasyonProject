package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.LinkedList;
import model.LoginAdmin;
import model.Oda;
import model.OdaLog;
import model.Rezervasyon;
import model.RezervasyonLog;
import service.OdaLogVeriOkuKaydet;
import service.RezervasyonLogVeriOkuKaydet;

import javax.swing.SwingConstants;

public class OdaAraDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField odaAraTextField;
	
	private OdaMenu odaMenu;


	public OdaAraDialog(OdaMenu odaMenu) {
		this.odaMenu = odaMenu;
		setTitle("Rezervasyon Ara");
		setBounds(100, 100, 350, 107);
		setResizable(false);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel odaNoText = new JLabel("Oda Numarası:");
		odaNoText.setHorizontalAlignment(SwingConstants.RIGHT);
		odaNoText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		odaNoText.setBounds(32, 10, 93, 19);
		contentPanel.add(odaNoText);
		{
			odaAraTextField = new JTextField();
			odaAraTextField.setBounds(135, 11, 128, 19);
			contentPanel.add(odaAraTextField);
			odaAraTextField.setColumns(10);
		}
		

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton araButton = new JButton("Ara");
				araButton.setActionCommand("Ara");
				araButton.addActionListener(e -> {
					try {
			            String odaNumarasi = odaAraTextField.getText().trim();
			            
			            if (!odaNumarasi.isEmpty()) {
			                Oda arananOda = odaMenu.odaAra(odaNumarasi);
			                
			                OdaLog yeniLog = new OdaLog(LoginAdmin.getGirisYapanAdmin(), arananOda, "Arama İşlemi");
			                LinkedList<String> yeniLogListesi = new LinkedList<>();
			                yeniLogListesi.insertLast(yeniLog.toString());
			                OdaLogVeriOkuKaydet.odaLogKaydet("src/data/odaLog.hot", yeniLogListesi, true);
			             
			                dispose();
			            } else {
			                JOptionPane.showMessageDialog(null, "Geçerli bir oda numarası giriniz!", "Hata", JOptionPane.ERROR_MESSAGE);
			            }
			        } catch (Exception ex) {
			            JOptionPane.showMessageDialog(null, "Oda arama sırasında bir hata oluştu!", "Hata", JOptionPane.ERROR_MESSAGE);
			        }
				});
				buttonPane.add(araButton);
				getRootPane().setDefaultButton(araButton);
			}
			{
				JButton iptalButton = new JButton("İptal");
				iptalButton.setActionCommand("İptal");
				buttonPane.add(iptalButton);
			}
		}
	}

}
