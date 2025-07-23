package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.LinkedList;
import model.LoginAdmin;
import model.Oda;
import model.OdaLog;
import model.Rezervasyon;
import service.OdaLogVeriOkuKaydet;
import service.OdaVeriKaydet;
import service.OdaVeriOku;
import service.RezervasyonVeriKaydet;
import service.RezervasyonVeriOku;

public class OdaSilDialog extends JDialog {
	private OdaMenu odaMenu;

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();


	public OdaSilDialog(String odaNumarasi, LinkedList<Oda> odalar, OdaMenu odaMenu) {
		setTitle("Oda Sil");
		setBounds(100, 100, 350, 107);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel odaSilText = new JLabel("Odayı Silmek İstediğinize Emin  misiniz?");
			odaSilText.setFont(new Font("Tahoma", Font.BOLD, 12));
			odaSilText.setBounds(10, 10, 306, 32);
			contentPanel.add(odaSilText);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton odaSilSilButton = new JButton("Sil");
				odaSilSilButton.setActionCommand("Sil");				
				odaSilSilButton.addActionListener(e -> {
					LinkedList<Oda> mevcutOdalar = OdaVeriOku.odalariOku("src/data/odalar.hot");

				    Oda silinecekOda = null;
				    for (Oda oda : mevcutOdalar) {
				        if (oda.getOdaNumarasi().equals(odaNumarasi)) {
				            silinecekOda = oda;
				            break;
				        }
				    }

				    if (silinecekOda != null) {
				        mevcutOdalar.remove(silinecekOda);
				        OdaVeriKaydet.odaKaydet("src/data/odalar.hot", mevcutOdalar, false);
				        odaMenu.odalariTabloyaYukle(); 

				        JOptionPane.showMessageDialog(this, "Oda başarıyla silindi!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
				        OdaLog yeniLog = new OdaLog(LoginAdmin.getGirisYapanAdmin(), silinecekOda, "Silme İşlemi");
	                    LinkedList<String> yeniLogListesi = new LinkedList<>();
	                    yeniLogListesi.insertLast(yeniLog.toString());
	                    OdaLogVeriOkuKaydet.odaLogKaydet("src/data/odaLog.hot", yeniLogListesi, true);
				        dispose();
				    } else {
				        JOptionPane.showMessageDialog(this, "Seçilen oda bulunamadı!", "Hata", JOptionPane.ERROR_MESSAGE);
				    }
				});

				buttonPane.add(odaSilSilButton);
				getRootPane().setDefaultButton(odaSilSilButton);
			}
			{
				JButton odaSilIptalButton = new JButton("İptal");
				odaSilIptalButton.setActionCommand("İptal");
				odaSilIptalButton.addActionListener(e -> {
					dispose();			
		        });
				buttonPane.add(odaSilIptalButton);
				
		}
	}
	}

}
