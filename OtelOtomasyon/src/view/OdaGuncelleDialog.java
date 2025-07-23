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
import model.OdaLog;
import model.Rezervasyon;
import service.OdaLogVeriOkuKaydet;
import service.OdaVeriKaydet;
import service.OdaVeriOku;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

public class OdaGuncelleDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox odaTipiComboBox;
	private JTextField odaNoTextField;
	private JTextField fiyatTextField;
	private JComboBox dolulukComboBox;
	private JComboBox durumComboBox;
	
	private OdaMenu odaMenu;
	String odaNumarasi;
	
	
	/**
	 * Create the dialog.
	 */
	public OdaGuncelleDialog(OdaMenu odaMenu, String odaNumarasi) {
		this.odaMenu = odaMenu;
		this.odaNumarasi = odaNumarasi;
		
		setTitle("Oda Güncelle");
		setBounds(100, 100, 240, 240);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel OdaTipiText = new JLabel("Oda Tipi:");
		OdaTipiText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		OdaTipiText.setBounds(22, 10, 81, 20);
		contentPanel.add(OdaTipiText);

		odaTipiComboBox = new JComboBox();
		odaTipiComboBox.setModel(new DefaultComboBoxModel(new String[] {"1 Kişilik", "2 Kişilik", "3 Kişilik"}));
		odaTipiComboBox.setBounds(118, 11, 96, 21);
		contentPanel.add(odaTipiComboBox);

		JLabel odaNoText = new JLabel("Oda Numarası:");
		odaNoText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		odaNoText.setBounds(22, 41, 86, 20);
		contentPanel.add(odaNoText);

		odaNoTextField = new JTextField();
		odaNoTextField.setColumns(10);
		odaNoTextField.setBounds(118, 42, 96, 19);
		contentPanel.add(odaNoTextField);
		
		JLabel fiyatText = new JLabel("Fiyat:");
		fiyatText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fiyatText.setBounds(22, 71, 86, 20);
		contentPanel.add(fiyatText);

		fiyatTextField = new JTextField();
		fiyatTextField.setColumns(10);
		fiyatTextField.setBounds(118, 72, 96, 19);
		contentPanel.add(fiyatTextField);

		JLabel dolulukText = new JLabel("Doluluk:");
		dolulukText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		dolulukText.setBounds(22, 101, 86, 20);
		contentPanel.add(dolulukText);

		JLabel durumText = new JLabel("Durum:");
		durumText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		durumText.setBounds(22, 131, 86, 20);
		contentPanel.add(durumText);
	
		dolulukComboBox = new JComboBox();
		dolulukComboBox.setModel(new DefaultComboBoxModel(new String[] {"Boş", "Dolu"}));
		dolulukComboBox.setBounds(118, 101, 96, 21);
		contentPanel.add(dolulukComboBox);
		
		durumComboBox = new JComboBox();
		durumComboBox.setModel(new DefaultComboBoxModel(new String[] {"Temiz", "Kirli"}));
		durumComboBox.setBounds(118, 132, 96, 21);
		contentPanel.add(durumComboBox);

		

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton guncelleButton = new JButton("Güncelle");
			guncelleButton.setActionCommand("Güncelle");
			guncelleButton.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        String yeniOdaNo = odaNoTextField.getText().trim();
			        String yeniFiyatText = fiyatTextField.getText().trim();
			        if (!yeniOdaNo.matches("\\d+")) {
			            JOptionPane.showMessageDialog(null, "Oda numarası sadece rakamlardan oluşmalı!", "Hata", JOptionPane.ERROR_MESSAGE);
			            return;
			        }
			        if (!yeniFiyatText.matches("\\d+(\\.\\d{1,2})?")) {
			            JOptionPane.showMessageDialog(null, "Fiyat sadece rakamlardan oluşmalı!", "Hata", JOptionPane.ERROR_MESSAGE);
			            return;
			        }

			        try {
			            String yeniOdaTipi = odaTipiComboBox.getSelectedItem().toString();
			            double yeniFiyat = Double.parseDouble(yeniFiyatText);
			            boolean yeniDoluluk = "Dolu".equals(dolulukComboBox.getSelectedItem());
			            boolean yeniDurum = "Temiz".equals(durumComboBox.getSelectedItem());

			            LinkedList<Oda> odalar = OdaVeriOku.odalariOku("src/data/odalar.hot");
			            Oda guncellenenOda = null;

			            for (Oda oda : odalar) {
			                if (oda.getOdaNumarasi().equals(odaNumarasi)) {
			                    oda.setOdaNumarasi(yeniOdaNo);
			                    oda.setOdaTipi(yeniOdaTipi);
			                    oda.setFiyat(yeniFiyat);
			                    oda.setDoluluk(yeniDoluluk);
			                    oda.setDurum(yeniDurum);
			                    guncellenenOda = oda;
			                    break;
			                }
			            }

			            if (guncellenenOda != null) {
			                OdaVeriKaydet.odaKaydet("src/data/odalar.hot", odalar, false);
			                odaMenu.odalariTabloyaYukle();
			                JOptionPane.showMessageDialog(null, "Oda başarıyla güncellendi!", "Mesaj", JOptionPane.INFORMATION_MESSAGE);

			                OdaLog yeniLog = new OdaLog(LoginAdmin.getGirisYapanAdmin(), guncellenenOda, "Güncelleme İşlemi");
			                LinkedList<String> yeniLogListesi = new LinkedList<>();
			                yeniLogListesi.insertLast(yeniLog.toString());
			                OdaLogVeriOkuKaydet.odaLogKaydet("src/data/odaLog.hot", yeniLogListesi, true);
			                dispose();
			            } else {
			                JOptionPane.showMessageDialog(null, "Güncellenecek oda bulunamadı!", "Hata", JOptionPane.ERROR_MESSAGE);
			            }
			        } catch (Exception ex) {
			            ex.printStackTrace();
			            JOptionPane.showMessageDialog(null, "Bir hata oluştu: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
			        }
			    }
			});

			buttonPane.add(guncelleButton);
			getRootPane().setDefaultButton(guncelleButton);
		}
		{
			JButton iptalButton = new JButton("İptal");
			iptalButton.setActionCommand("İptal");
			iptalButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	dispose();
		        }
		    });
			buttonPane.add(iptalButton);
			
		}
	}
	
	public void setOdaBilgileri(Oda oda) {
		odaTipiComboBox.setSelectedItem(oda.getOdaTipi());
	    odaNoTextField.setText(oda.getOdaNumarasi());
	    fiyatTextField.setText(String.valueOf(oda.getFiyat()));
	    dolulukComboBox.setSelectedItem(oda.getDoluluk() ? "Dolu" : "Boş");
	    durumComboBox.setSelectedItem(oda.getDurum() ? "Temiz" : "Kirli");
	}
	
}
