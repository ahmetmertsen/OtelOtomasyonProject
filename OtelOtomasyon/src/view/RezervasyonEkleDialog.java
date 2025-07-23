package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.KisiBilgileri;
import model.LinkedList;
import model.LoginAdmin;
import model.Oda;
import model.Rezervasyon;
import model.RezervasyonLog;
import service.OdaVeriKaydet;
import service.OdaVeriOku;
import service.RezervasyonLogVeriOkuKaydet;
import service.RezervasyonVeriKaydet;
import service.RezervasyonVeriOku;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.IOException;

import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class RezervasyonEkleDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel musteriBilgilerPanel;
	private JTextField rezEkleGirisTextField;
	private JTextField rezEkleCikisTextField;
	private JTextField tcKimlikTextField;
	private JTextField adSoyadTextField;
	private JTextField dogumTarihiTextField;
	private JTextField rezEkleFiyatTextField;
	private JSpinner rezEkleKisiSayisiSpinner;
	private JComboBox<String> rezEkleOdaNumarasiComboBox;
	private JComboBox<String> rezEkleOdaTipiComboBox;
	private RezervasyonMenu rezervasyonMenu;
	
	private LinkedList<Rezervasyon> rezervasyonListesi = new LinkedList<>();


	public RezervasyonEkleDialog(RezervasyonMenu rezervasyonMenu) {
		this.rezervasyonMenu = rezervasyonMenu;
		rezervasyonListesi = RezervasyonVeriOku.rezervasyonlariOku("src/data/rezervasyonlar.hot");
		
		setTitle("Rezervasyon Ekle");
		setBounds(100, 100, 600, 480);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel rezEkleKisiSayisiText = new JLabel("Kişi Sayısı:");
			rezEkleKisiSayisiText.setFont(new Font("Tahoma", Font.PLAIN, 13));
			rezEkleKisiSayisiText.setBounds(56, 10, 65, 20);
			contentPanel.add(rezEkleKisiSayisiText);
		}
		
		rezEkleKisiSayisiSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 5, 1));
		rezEkleKisiSayisiSpinner.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rezEkleKisiSayisiSpinner.setBounds(147, 10, 45, 20);
		contentPanel.add(rezEkleKisiSayisiSpinner);
		
		JLabel rezEkleOdaTipiText = new JLabel("Oda Tipi:");
        rezEkleOdaTipiText.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rezEkleOdaTipiText.setBounds(56, 39, 65, 20);
        contentPanel.add(rezEkleOdaTipiText);
        
        rezEkleOdaTipiComboBox = new JComboBox<>();
        rezEkleOdaTipiComboBox.setBounds(147, 39, 96, 21);
        contentPanel.add(rezEkleOdaTipiComboBox);
        
		
		JLabel rezEkleOdaNumarasiText = new JLabel("Oda Numarası:");
		rezEkleOdaNumarasiText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rezEkleOdaNumarasiText.setBounds(56, 69, 86, 20);
		contentPanel.add(rezEkleOdaNumarasiText);
		
		rezEkleOdaNumarasiComboBox = new JComboBox();
		rezEkleOdaNumarasiComboBox.setModel(new DefaultComboBoxModel<>());
		rezEkleOdaNumarasiComboBox.setBounds(147, 69, 65, 21);
		contentPanel.add(rezEkleOdaNumarasiComboBox);
		
		
		JLabel rezEkleFiyatText = new JLabel("Fiyat:");
        rezEkleFiyatText.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rezEkleFiyatText.setBounds(56, 99, 86, 20);
        contentPanel.add(rezEkleFiyatText);
        
        rezEkleFiyatTextField = new JTextField();
        rezEkleFiyatTextField.setBounds(147, 99, 96, 21);
        rezEkleFiyatTextField.setEditable(false);
        contentPanel.add(rezEkleFiyatTextField);;
        odalariDoldur();
		
		JLabel rezEkleGirisText = new JLabel("Giriş Tarihi:");
		rezEkleGirisText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rezEkleGirisText.setBounds(352, 10, 86, 20);
		contentPanel.add(rezEkleGirisText);
		
		rezEkleGirisTextField = new JTextField();
		rezEkleGirisTextField.setColumns(10);
		rezEkleGirisTextField.setBounds(443, 10, 96, 21);
		contentPanel.add(rezEkleGirisTextField);
		
		JLabel rezEkleCikisText = new JLabel("Çıkış Tarihi:");
		rezEkleCikisText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rezEkleCikisText.setBounds(352, 40, 86, 20);
		contentPanel.add(rezEkleCikisText);
		
		rezEkleCikisTextField = new JTextField();
		rezEkleCikisTextField.setColumns(10);
		rezEkleCikisTextField.setBounds(443, 40, 96, 21);
		contentPanel.add(rezEkleCikisTextField);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new Date();
		rezEkleGirisTextField.setText(sdf.format(today));
		
		Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
		rezEkleCikisTextField.setText(sdf.format(tomorrow));
	
		
		musteriBilgilerPanel = new JPanel();
		musteriBilgilerPanel.setBounds(10, 147, 560, 263);
        contentPanel.add(musteriBilgilerPanel);
        musteriBilgilerPanel.setBorder(BorderFactory.createTitledBorder("Müşteri Bilgileri"));
        musteriBilgilerPanel.setLayout(null);      
        
        rezEkleKisiSayisiSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int kisiSayisi = (Integer) rezEkleKisiSayisiSpinner.getValue();
                updateMusteriBilgilerPanel(kisiSayisi);
            }
        });


        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton ekleButton = new JButton("Ekle");
        ekleButton.setActionCommand("Ekle");
        buttonPane.add(ekleButton);
        ekleButton.addActionListener(e -> {
            try {
                LinkedList<Rezervasyon> mevcutRezervasyonlar = RezervasyonVeriOku.rezervasyonlariOku("src/data/rezervasyonlar.hot");
                Rezervasyon yeniRezervasyon = yeniRezervasyonOlustur();
                if (yeniRezervasyon == null) {
                    JOptionPane.showMessageDialog(null, "Rezervasyon oluşturulamadı. Lütfen tekrar deneyiniz!", "Uyarı", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int maxId = RezervasyonVeriOku.getMaxRezervasyonId("src/data/rezervasyonlar.hot");
                yeniRezervasyon.setRezervasyonId(maxId + 1);
                mevcutRezervasyonlar.insertLast(yeniRezervasyon);
                RezervasyonVeriKaydet.rezervasyonKaydet("src/data/rezervasyonlar.hot", mevcutRezervasyonlar, false);

                yeniRezervasyon.getOda().setDoluluk(true);
                odaDurumunuGuncelle(yeniRezervasyon.getOda().getOdaNumarasi(), true);

                JOptionPane.showMessageDialog(null, "Rezervasyon başarıyla kaydedildi!", "Mesaj", JOptionPane.INFORMATION_MESSAGE);
                rezervasyonMenu.rezervasyonlariTabloyaYukle();
                odalariDoldur();

                RezervasyonLog yeniLog = new RezervasyonLog(LoginAdmin.getGirisYapanAdmin(), yeniRezervasyon, "Ekleme İşlemi");                   
                LinkedList<String> yeniLogListesi = new LinkedList<>();
                yeniLogListesi.insertLast(yeniLog.toString());
                RezervasyonLogVeriOkuKaydet.rezervasyonLogKaydet("src/data/rezervasyonLog.hot", yeniLogListesi, true);

                dispose();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Rezervasyon kaydedilirken bir hata oluştu!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });
        getRootPane().setDefaultButton(ekleButton);

        JButton iptalButton = new JButton("İptal");
        iptalButton.setActionCommand("İptal");
        iptalButton.addActionListener(e -> {
        	dispose();
        });
        buttonPane.add(iptalButton);
    }
	
	private Rezervasyon yeniRezervasyonOlustur() throws Exception {	
	    String girisTarihi = rezEkleGirisTextField.getText().trim();
	    String cikisTarihi = rezEkleCikisTextField.getText().trim();
	    String odaNumarasi = (String) rezEkleOdaNumarasiComboBox.getSelectedItem();
	    String odaTipi = (String) rezEkleOdaTipiComboBox.getSelectedItem();
	    double fiyat = Double.parseDouble(rezEkleFiyatTextField.getText());
	    int kisiSayisi = (Integer) rezEkleKisiSayisiSpinner.getValue();

	    Oda oda = new Oda(odaNumarasi, odaTipi, fiyat);

	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(false);
	    Date girisTarihiDate = dateFormat.parse(girisTarihi);
	    Date cikisTarihiDate = dateFormat.parse(cikisTarihi);

	    Rezervasyon rezervasyon = new Rezervasyon();
	    rezervasyon.setOda(oda);
	    rezervasyon.setGirisTarihi(girisTarihiDate);
	    rezervasyon.setCikisTarihi(cikisTarihiDate);
	    
	    try {
	        rezervasyon.setKisiBilgileri(kisiselBilgileriTopla(kisiSayisi));
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
	        return null;
	    }

	    return rezervasyon;
	}
	
	protected LinkedList<KisiBilgileri> kisiselBilgileriTopla(int kisiSayisi) throws Exception {
	    LinkedList<KisiBilgileri> kisiListesi = new LinkedList<>();

	    for (int i = 0; i < kisiSayisi; i++) {
	        int baseIndex = i * 6;

	        JTextField tcTextField = (JTextField) musteriBilgilerPanel.getComponent(baseIndex + 1);
	        JTextField adSoyadTextField = (JTextField) musteriBilgilerPanel.getComponent(baseIndex + 3);
	        JTextField dogumTarihiTextField = (JTextField) musteriBilgilerPanel.getComponent(baseIndex + 5);

	        String tcKimlik = tcTextField.getText().trim();
	        String adSoyad = adSoyadTextField.getText().trim();
	        String dogumTarihi = dogumTarihiTextField.getText().trim();

	        if (!kontrolTcKimlik(tcKimlik)) {
	            throw new Exception("TC Kimlik 11 karakter uzunluğunda ve sadece rakamlardan oluşmalıdır!");
	        }

	        if (!kontrolAdSoyad(adSoyad)) {
	            throw new Exception("Ad Soyad sadece harflerden oluşmalıdır!");
	        }

	        if (!kontrolDogumTarihi(dogumTarihi)) {
	            throw new Exception("Doğum Tarihi dd/MM/yyyy formatında olmalıdır!");
	        }

	        KisiBilgileri kisi = new KisiBilgileri(tcKimlik, adSoyad, dogumTarihi);
	        kisiListesi.insertLast(kisi);
	    }

	    return kisiListesi;
	}
	
	protected static int getMaxRezervasyonId(String dosyaAdi) {
	    int maxId = 0;
	    LinkedList<Rezervasyon> rezervasyonlar = RezervasyonVeriOku.rezervasyonlariOku(dosyaAdi);
	    for (Rezervasyon rezervasyon : rezervasyonlar) {
	        if (rezervasyon.getRezervasyonId() > maxId) {
	            maxId = rezervasyon.getRezervasyonId();
	        }
	    }
	    return maxId;
	}
	
	private static void rezervasyonEkle(String dosyaAdi, Rezervasyon yeniRezervasyon) {
	    int maxId = RezervasyonVeriOku.getMaxRezervasyonId(dosyaAdi);
	    yeniRezervasyon.setRezervasyonId(maxId + 1);

	    LinkedList<Rezervasyon> yeniListe = new LinkedList<>();
	    yeniListe.insertLast(yeniRezervasyon);
	    RezervasyonVeriKaydet.rezervasyonKaydet(dosyaAdi, yeniListe, true);
	}
	
		protected void odalariDoldur() {
		    LinkedList<Oda> odalar = OdaVeriOku.odalariOku("src/data/odalar.hot");
		    LinkedList<Oda> bosOdalar = OdaVeriOku.getBosOdalar(odalar);
	
		    DefaultComboBoxModel<String> odaTipiModel = new DefaultComboBoxModel<>();
		    bosOdalar.stream()
		             .map(Oda::getOdaTipi)
		             .distinct()
		             .forEach(odaTipiModel::addElement);
		    rezEkleOdaTipiComboBox.setModel(odaTipiModel);
	
		    if (rezEkleOdaTipiComboBox.getItemCount() > 0) {
		        String varsayilanOdaTipi = (String) rezEkleOdaTipiComboBox.getSelectedItem();
		        updateOdaTipineGoreOdaNumaralari(varsayilanOdaTipi, bosOdalar);
		    }
	
		    rezEkleOdaTipiComboBox.addActionListener(e -> {
		        String secilenOdaTipi = (String) rezEkleOdaTipiComboBox.getSelectedItem();
		        updateOdaTipineGoreOdaNumaralari(secilenOdaTipi, bosOdalar);
		    });
		}
		protected void odaDurumunuGuncelle(String odaNumarasi, boolean dolulukDurumu) {
		    LinkedList<Oda> odalar = OdaVeriOku.odalariOku("src/data/odalar.hot");
		    for (Oda oda : odalar) {
		        if (oda.getOdaNumarasi().equals(odaNumarasi)) {
		            oda.setDoluluk(dolulukDurumu);
		            break;
		        }
		    }
		    OdaVeriKaydet.odaKaydet("src/data/odalar.hot", odalar,false);
		}
		
		protected void updateOdaTipineGoreOdaNumaralari(String secilenOdaTipi, LinkedList<Oda> bosOdalar) {
		    DefaultComboBoxModel<String> odaNumarasiModel = new DefaultComboBoxModel<>();
	
		    bosOdalar.stream()
		             .filter(oda -> oda.getOdaTipi().equals(secilenOdaTipi))
		             .map(Oda::getOdaNumarasi)
		             .forEach(odaNumarasiModel::addElement);
	
		    rezEkleOdaNumarasiComboBox.setModel(odaNumarasiModel);
	
		    bosOdalar.stream()
		             .filter(oda -> oda.getOdaTipi().equals(secilenOdaTipi))
		             .findFirst()
		             .ifPresent(this::updateOdaTipiVeFiyat);
		}
		
		protected void updateOdaNumaralariComboBox(LinkedList<Oda> bosOdalar) {
		    DefaultComboBoxModel<String> odaModel = new DefaultComboBoxModel<>();
		    bosOdalar.forEach(oda -> odaModel.addElement(oda.getOdaNumarasi()));
	
		    rezEkleOdaNumarasiComboBox.setModel(odaModel);
	
		    if (bosOdalar.size() > 0) {
		        updateOdaTipiVeFiyat(bosOdalar.get(0));
		    }
	
		    rezEkleOdaNumarasiComboBox.addActionListener(e -> {
		        String secilenOdaNumarasi = (String) rezEkleOdaNumarasiComboBox.getSelectedItem();
		        bosOdalar.stream()
		                .filter(oda -> oda.getOdaNumarasi().equals(secilenOdaNumarasi))
		                .findFirst()
		                .ifPresent(this::updateOdaTipiVeFiyat);
		    });
		}
	
		protected void updateOdaTipiVeFiyat(Oda oda) {
		    if (rezEkleFiyatTextField != null) {
		        rezEkleFiyatTextField.setText(String.valueOf(oda.getFiyat()));
		    }
		}
	
	
	

    protected void updateMusteriBilgilerPanel(int kisiSayisi) {
    	musteriBilgilerPanel.removeAll();
        
        for (int i = 0; i < kisiSayisi; i++) {
            int yOffset = 40 + i * 45 ;

            JLabel lblTcKimlik = new JLabel("TC Kimlik:");
            lblTcKimlik.setFont(new Font("Tahoma", Font.PLAIN, 13));
            lblTcKimlik.setBounds(10, yOffset, 65, 20);
            musteriBilgilerPanel.add(lblTcKimlik);

            JTextField tcTextField = new JTextField();
            tcTextField.setBounds(80, yOffset, 96, 20);
            musteriBilgilerPanel.add(tcTextField);

            JLabel lblAdSoyad = new JLabel("Ad Soyad:");
            lblAdSoyad.setFont(new Font("Tahoma", Font.PLAIN, 13));
            lblAdSoyad.setBounds(190, yOffset, 65, 20);
            musteriBilgilerPanel.add(lblAdSoyad);

            JTextField adSoyadTextField = new JTextField();
            adSoyadTextField.setBounds(260, yOffset, 96, 20);
            musteriBilgilerPanel.add(adSoyadTextField);

            JLabel lblDogumTarihi = new JLabel("Doğum Tarihi:");
            lblDogumTarihi.setFont(new Font("Tahoma", Font.PLAIN, 13));
            lblDogumTarihi.setBounds(370, yOffset, 85, 20);
            musteriBilgilerPanel.add(lblDogumTarihi);

            JTextField dogumTarihiTextField = new JTextField();
            dogumTarihiTextField.setBounds(460, yOffset, 96, 20);
            musteriBilgilerPanel.add(dogumTarihiTextField);
        }

        musteriBilgilerPanel.revalidate();
        musteriBilgilerPanel.repaint();
        
    }
    private boolean kontrolTcKimlik(String tcKimlik) {
        return tcKimlik.matches("\\d{11}");
    }
    private boolean kontrolAdSoyad(String adSoyad) {
        return adSoyad.matches("[a-zA-ZğüşöçıİĞÜŞÖÇ\\s]+");
    }
    private boolean kontrolDogumTarihi(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
