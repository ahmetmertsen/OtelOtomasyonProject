package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.LinkedList;
import model.LoginAdmin;
import model.Oda;
import model.Rezervasyon;
import model.RezervasyonLog;
import service.OdaVeriKaydet;
import service.RezervasyonLogVeriOkuKaydet;
import service.RezervasyonVeriKaydet;
import service.RezervasyonVeriOku;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class RezervasyonSilDialog extends JDialog {
	private RezervasyonMenu rezervasyonMenu;
	private LinkedList<Oda> odalar;

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public RezervasyonSilDialog(int rezervasyonId, LinkedList<Oda> odalar, RezervasyonMenu rezervasyonMenu) {
		setTitle("Rezervasyon Sil");
		setBounds(100, 100, 340, 120);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Rezervasyonu Silmek İstediğinize Emin misiniz?");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 10, 306, 32);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton silButton = new JButton("Sil");
				silButton.setActionCommand("Sil");
				buttonPane.add(silButton);
				silButton.addActionListener(e -> {
					
					LinkedList<Rezervasyon> rezervasyonlar = RezervasyonVeriOku.rezervasyonlariOku("src/data/rezervasyonlar.hot");

		            Rezervasyon silinecekRezervasyon = null;
		            for (Rezervasyon rezervasyon : rezervasyonlar) {
		                if (rezervasyon.getRezervasyonId() == rezervasyonId) {
		                    silinecekRezervasyon = rezervasyon;
		                    break;
		                }
		            }

		            if (silinecekRezervasyon != null) {
		                odaDurumunuGuncelle(odalar, silinecekRezervasyon.getOda().getOdaNumarasi(), false);
		                rezervasyonlar.remove(silinecekRezervasyon);
		                RezervasyonVeriKaydet.rezervasyonKaydet("src/data/rezervasyonlar.hot", rezervasyonlar, false);
		                
		                OdaVeriKaydet.odaKaydet("src/data/odalar.hot", odalar,false);
		                rezervasyonMenu.rezervasyonlariTabloyaYukle();		           
		                JOptionPane.showMessageDialog(this, "Rezervasyon başarıyla silindi!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
		                
		             // Log kaydetme işlemi                  
	                    RezervasyonLog yeniLog = new RezervasyonLog(LoginAdmin.getGirisYapanAdmin(), silinecekRezervasyon, "Silme İşlemi");	         
	                    LinkedList<String> yeniLogListesi = new LinkedList<>();
	                    yeniLogListesi.insertLast(yeniLog.toString());
	                    RezervasyonLogVeriOkuKaydet.rezervasyonLogKaydet("src/data/rezervasyonLog.hot", yeniLogListesi, true);
	                    
		                dispose();
		                
		            } else {
		                JOptionPane.showMessageDialog(this, "Seçilen rezervasyon bulunamadı!", "Hata", JOptionPane.ERROR_MESSAGE);
		            }
		        });
				
		        
				getRootPane().setDefaultButton(silButton);
			}
			{
				JButton iptalButton = new JButton("İptal");
				iptalButton.setActionCommand("İptal");
				iptalButton.addActionListener(e -> {
		        	dispose();
		        });
				buttonPane.add(iptalButton);
			}
		}
	
	}
	private void odaDurumunuGuncelle(LinkedList<Oda> odalar, String odaNumarasi, boolean dolulukDurumu) {
        for (Oda oda : odalar) {
            if (oda.getOdaNumarasi().equals(odaNumarasi)) {
                oda.setDoluluk(dolulukDurumu);
                break;
            }
        }
    }
	
}
