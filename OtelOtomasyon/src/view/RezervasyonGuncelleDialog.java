package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.KisiBilgileri;
import model.LinkedList;
import model.LoginAdmin;
import model.Rezervasyon;
import model.RezervasyonLog;
import service.RezervasyonLogVeriOkuKaydet;
import service.RezervasyonVeriKaydet;
import service.RezervasyonVeriOku;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RezervasyonGuncelleDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JSpinner rezGuncelleKisiSayisiSpinner;
	private JTextField rezGuncelleOdaTipiTextField;
	private JTextField rezGuncelleOdaNoTextField;
	private JTextField rezGuncelleFiyatTextField;
	private JTextField rezGuncelleGirisTextField;
	private JTextField rezGuncelleCikisTextField;
	
	private JPanel kisiselBilgilerpanel;
	private JTextField tcKimlikTextField;
	private JTextField adSoyadTextField;
	private JTextField dogumTarihiTextField;
	
	Rezervasyon rezervasyon;
	private int rezervasyonId;
	private RezervasyonMenu rezervasyonMenu;


	public RezervasyonGuncelleDialog(RezervasyonMenu rezervasyonMenu,int rezervasyonId) {
		this.rezervasyonMenu = rezervasyonMenu;
		this.rezervasyonId = rezervasyonId;
		setTitle("Rezervasyon Güncelle");
		setBounds(100, 100, 600, 480);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel rezGuncelleKisiSayisiText = new JLabel("Kişi Sayısı:");
		rezGuncelleKisiSayisiText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rezGuncelleKisiSayisiText.setBounds(25, 10, 65, 20);
		contentPanel.add(rezGuncelleKisiSayisiText);
		
		rezGuncelleKisiSayisiSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 5, 1));
		rezGuncelleKisiSayisiSpinner.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rezGuncelleKisiSayisiSpinner.setBounds(116, 10, 45, 20);
		contentPanel.add(rezGuncelleKisiSayisiSpinner);
		
		JLabel rezGuncelleOdaTipiText = new JLabel("Oda Tipi:");
        rezGuncelleOdaTipiText.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rezGuncelleOdaTipiText.setBounds(25, 40, 65, 20);
        contentPanel.add(rezGuncelleOdaTipiText);
        
        rezGuncelleOdaTipiTextField = new JTextField();
        rezGuncelleOdaTipiTextField.setColumns(10);
        rezGuncelleOdaTipiTextField.setBounds(116, 40, 96, 21);
        rezGuncelleOdaTipiTextField.setEditable(false);
        contentPanel.add(rezGuncelleOdaTipiTextField);
        
        JLabel rezGuncelleOdaNumarasiText = new JLabel("Oda Numarası:");
		rezGuncelleOdaNumarasiText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rezGuncelleOdaNumarasiText.setBounds(25, 70, 86, 20);
		contentPanel.add(rezGuncelleOdaNumarasiText);
		
		rezGuncelleOdaNoTextField = new JTextField();
        rezGuncelleOdaNoTextField.setColumns(10);
        rezGuncelleOdaNoTextField.setBounds(116, 72, 45, 21);
        rezGuncelleOdaNoTextField.setEditable(false);
        contentPanel.add(rezGuncelleOdaNoTextField);
        
        JButton odaDegistirButton = new JButton("Oda Değiştir");
        odaDegistirButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String odaTipi = rezGuncelleOdaTipiTextField.getText();
                String odaNo = rezGuncelleOdaNoTextField.getText();
                String fiyat = rezGuncelleFiyatTextField.getText();
                
                RezGuncelleOdaDegistirDialog odaDegistirDialog = new RezGuncelleOdaDegistirDialog(RezervasyonGuncelleDialog.this);
                odaDegistirDialog.setMevcutOdaBilgileri(odaTipi, odaNo, fiyat);
                odaDegistirDialog.setLocationRelativeTo(RezervasyonGuncelleDialog.this); 
                odaDegistirDialog.setVisible(true);
        	}
        });
        odaDegistirButton.setBounds(171, 71, 110, 19);
        contentPanel.add(odaDegistirButton);
        

        
        JLabel rezGuncelleFiyatText = new JLabel("Fiyat:");
        rezGuncelleFiyatText.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rezGuncelleFiyatText.setBounds(25, 100, 86, 20);
        contentPanel.add(rezGuncelleFiyatText);
        
        rezGuncelleFiyatTextField = new JTextField();
        rezGuncelleFiyatTextField.setColumns(10);
        rezGuncelleFiyatTextField.setBounds(116, 100, 96, 21);
        rezGuncelleFiyatTextField.setEditable(false);
        contentPanel.add(rezGuncelleFiyatTextField);
        	
		JLabel rezGuncelleGirisText = new JLabel("Giriş Tarihi:");
		rezGuncelleGirisText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rezGuncelleGirisText.setBounds(301, 10, 86, 20);
		contentPanel.add(rezGuncelleGirisText);
		
		rezGuncelleGirisTextField = new JTextField();
		rezGuncelleGirisTextField.setColumns(10);
		rezGuncelleGirisTextField.setBounds(392, 10, 96, 21);
		contentPanel.add(rezGuncelleGirisTextField);
		
		JLabel rezGuncelleCikisText = new JLabel("Çıkış Tarihi:");
		rezGuncelleCikisText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rezGuncelleCikisText.setBounds(301, 40, 86, 20);
		contentPanel.add(rezGuncelleCikisText);
		
		rezGuncelleCikisTextField = new JTextField();
		rezGuncelleCikisTextField.setColumns(10);
		rezGuncelleCikisTextField.setBounds(392, 40, 96, 21);
		contentPanel.add(rezGuncelleCikisTextField);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new Date();
		rezGuncelleGirisTextField.setText(sdf.format(today));
		
		Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
		rezGuncelleCikisTextField.setText(sdf.format(tomorrow));
	
		      
        kisiselBilgilerpanel = new JPanel();
        kisiselBilgilerpanel.setBounds(10, 147, 560, 263);
        contentPanel.add(kisiselBilgilerpanel);
        kisiselBilgilerpanel.setBorder(BorderFactory.createTitledBorder("Müşteri Bilgileri"));
        kisiselBilgilerpanel.setLayout(null); 
        
        
        

        rezGuncelleKisiSayisiSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int kisiSayisi = (Integer) rezGuncelleKisiSayisiSpinner.getValue();
                updateKisiselBilgilerPanel(kisiSayisi);
            }
        });

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton guncelleButton = new JButton("Güncelle");
        guncelleButton.setActionCommand("OK");
        guncelleButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
                    String yeniOdaTipi = rezGuncelleOdaTipiTextField.getText();
                    String yeniOdaNo = rezGuncelleOdaNoTextField.getText();
                    String yeniFiyat = rezGuncelleFiyatTextField.getText();
                    int yeniKisiSayisi = (Integer) rezGuncelleKisiSayisiSpinner.getValue();

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date yeniGirisTarihi = sdf.parse(rezGuncelleGirisTextField.getText());
                    Date yeniCikisTarihi = sdf.parse(rezGuncelleCikisTextField.getText());
                    
                    LinkedList<KisiBilgileri> yeniKisiBilgileri = new LinkedList<>();

                    for (int i = 0; i < yeniKisiSayisi; i++) {
                        int baseIndex = i * 6;

                        JTextField tcField = (JTextField) kisiselBilgilerpanel.getComponent(baseIndex + 1);
                        JTextField adSoyadField = (JTextField) kisiselBilgilerpanel.getComponent(baseIndex + 3);
                        JTextField dogumTarihiField = (JTextField) kisiselBilgilerpanel.getComponent(baseIndex + 5);

                        KisiBilgileri kisi = new KisiBilgileri();
                        kisi.setTcKimlik(tcField.getText());
                        kisi.setAdSoyad(adSoyadField.getText());
                        kisi.setDogumTarihi(dogumTarihiField.getText());
                        yeniKisiBilgileri.insertLast(kisi);
                    }
                   
                    LinkedList<Rezervasyon> rezervasyonlar = RezervasyonVeriOku.rezervasyonlariOku("src/data/rezervasyonlar.hot");
                    
                    
                    Rezervasyon guncellenenRezervasyon = null;
                    for (Rezervasyon rezervasyon : rezervasyonlar) {
                        if (rezervasyon.getRezervasyonId() == rezervasyonId) {
                            rezervasyon.getOda().setOdaNumarasi(yeniOdaNo);
                            rezervasyon.getOda().setOdaTipi(yeniOdaTipi);
                            rezervasyon.getOda().setFiyat(Double.parseDouble(yeniFiyat));
                            rezervasyon.setKisiSayisi(yeniKisiSayisi);
                            rezervasyon.setGirisTarihi(yeniGirisTarihi);
                            rezervasyon.setCikisTarihi(yeniCikisTarihi);
                            
                            rezervasyon.setKisiBilgileri(yeniKisiBilgileri);
                            guncellenenRezervasyon = rezervasyon;
                            break;
                        }
                    }

                    RezervasyonVeriKaydet.rezervasyonKaydet("src/data/rezervasyonlar.hot", rezervasyonlar, false);

                    rezervasyonMenu.rezervasyonlariTabloyaYukle();

                    JOptionPane.showMessageDialog(null, "Rezervasyon başarıyla güncellendi!");
                    
                    RezervasyonLog yeniLog = new RezervasyonLog(LoginAdmin.getGirisYapanAdmin(), guncellenenRezervasyon , "Güncelleme İşlemi");                   
                    LinkedList<String> yeniLogListesi = new LinkedList<>();
                    yeniLogListesi.insertLast(yeniLog.toString());
                    RezervasyonLogVeriOkuKaydet.rezervasyonLogKaydet("src/data/rezervasyonLog.hot", yeniLogListesi, true);
                    
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Bir hata oluştu: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                }
        	}
        });
        buttonPane.add(guncelleButton);
        getRootPane().setDefaultButton(guncelleButton);

        JButton cancelButton = new JButton("İptal");
        cancelButton.setActionCommand("Cancel");
        cancelButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        buttonPane.add(cancelButton);
    }

	private void updateKisiselBilgilerPanel(int kisiSayisi) {
	    Map<Integer, KisiBilgileri> mevcutBilgiler = new HashMap<>();
	    for (int i = 0; i < kisiselBilgilerpanel.getComponentCount() / 6; i++) {
	        JTextField tcField = (JTextField) kisiselBilgilerpanel.getComponent(i * 6 + 1);
	        JTextField adSoyadField = (JTextField) kisiselBilgilerpanel.getComponent(i * 6 + 3);
	        JTextField dogumTarihiField = (JTextField) kisiselBilgilerpanel.getComponent(i * 6 + 5);

	        KisiBilgileri kisi = new KisiBilgileri();
	        kisi.setTcKimlik(tcField.getText());
	        kisi.setAdSoyad(adSoyadField.getText());
	        kisi.setDogumTarihi(dogumTarihiField.getText());
	        mevcutBilgiler.put(i, kisi);
	    }

	    kisiselBilgilerpanel.removeAll();

	    for (int i = 0; i < kisiSayisi; i++) {
	        int yOffset = 40 + i * 45;

	        JLabel lblTcKimlik = new JLabel("TC Kimlik:");
	        lblTcKimlik.setFont(new Font("Tahoma", Font.PLAIN, 13));
	        lblTcKimlik.setBounds(10, yOffset, 65, 20);
	        kisiselBilgilerpanel.add(lblTcKimlik);

	        JTextField tcTextField = new JTextField();
	        tcTextField.setBounds(80, yOffset, 96, 20);
	        kisiselBilgilerpanel.add(tcTextField);

	        JLabel lblAdSoyad = new JLabel("Ad Soyad:");
	        lblAdSoyad.setFont(new Font("Tahoma", Font.PLAIN, 13));
	        lblAdSoyad.setBounds(190, yOffset, 65, 20);
	        kisiselBilgilerpanel.add(lblAdSoyad);

	        JTextField adSoyadTextField = new JTextField();
	        adSoyadTextField.setBounds(260, yOffset, 96, 20);
	        kisiselBilgilerpanel.add(adSoyadTextField);

	        JLabel lblDogumTarihi = new JLabel("Doğum Tarihi:");
	        lblDogumTarihi.setFont(new Font("Tahoma", Font.PLAIN, 13));
	        lblDogumTarihi.setBounds(370, yOffset, 85, 20);
	        kisiselBilgilerpanel.add(lblDogumTarihi);

	        JTextField dogumTarihiTextField = new JTextField();
	        dogumTarihiTextField.setBounds(460, yOffset, 96, 20);
	        kisiselBilgilerpanel.add(dogumTarihiTextField);

	        if (mevcutBilgiler.containsKey(i)) {
	            KisiBilgileri kisi = mevcutBilgiler.get(i);
	            tcTextField.setText(kisi.getTcKimlik());
	            adSoyadTextField.setText(kisi.getAdSoyad());
	            dogumTarihiTextField.setText(kisi.getDogumTarihi());
	        }
	    }

	    kisiselBilgilerpanel.revalidate();
	    kisiselBilgilerpanel.repaint();
	}

	public void setRezervasyonBilgileri(Rezervasyon rezervasyon) {
	    rezGuncelleKisiSayisiSpinner.setValue(rezervasyon.getKisiSayisi());
	    rezGuncelleOdaTipiTextField.setText(rezervasyon.getOdaTipi());
	    rezGuncelleOdaNoTextField.setText(rezervasyon.getOdaNumarasi());
	    rezGuncelleGirisTextField.setText(rezervasyon.getFormattedGirisTarihi());
	    rezGuncelleCikisTextField.setText(rezervasyon.getFormattedCikisTarihi());
	    rezGuncelleFiyatTextField.setText(String.valueOf(rezervasyon.getFiyat()));

	    LinkedList<KisiBilgileri> kisiBilgileri = rezervasyon.getKisiBilgileri();
	    updateKisiselBilgilerPanel(kisiBilgileri.size());

	    for (int i = 0; i < kisiBilgileri.size(); i++) {
	        KisiBilgileri kisi = kisiBilgileri.get(i);

	        int baseIndex = i * 6;
	        JTextField tcField = (JTextField) kisiselBilgilerpanel.getComponent(baseIndex + 1);
	        JTextField adSoyadField = (JTextField) kisiselBilgilerpanel.getComponent(baseIndex + 3);
	        JTextField dogumTarihiField = (JTextField) kisiselBilgilerpanel.getComponent(baseIndex + 5);

	        tcField.setText(kisi.getTcKimlik());
	        adSoyadField.setText(kisi.getAdSoyad());
	        dogumTarihiField.setText(kisi.getDogumTarihi());
	        
	        
	    }
	}
	public void setOdaBilgileri(String odaTipi, String odaNumarasi, String fiyat) {
	    rezGuncelleOdaTipiTextField.setText(odaTipi);
	    rezGuncelleOdaNoTextField.setText(odaNumarasi);
	    rezGuncelleFiyatTextField.setText(fiyat);
	}
	
}
