package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.LinkedList;
import model.LoginAdmin;
import model.Oda;
import model.Rezervasyon;
import model.RezervasyonLog;
import service.RezervasyonLogVeriOkuKaydet;
import service.RezervasyonVeriKaydet;
import service.RezervasyonVeriOku;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class RezervasyonMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable rezervasyonTable;
	private JLabel kullaniciGoster;
    private DefaultTableModel tableModel;
    
    private MainMenu mainMenu;
	private LinkedList<Oda> odalar;
    
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	
	
	public RezervasyonMenu(MainMenu mainMenu) {		
		this.mainMenu = mainMenu;
		this.odalar = OdaMenu.getOdalar();
		
		setTitle("Rezervasyon İşlemleri");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel rezMenuTitle = new JLabel("Rezervasyon İşlemleri");
        rezMenuTitle.setHorizontalAlignment(SwingConstants.CENTER);
        rezMenuTitle.setFont(new Font("Tahoma", Font.BOLD, 28));
        rezMenuTitle.setBounds(10, 10, 760, 40);
        contentPane.add(rezMenuTitle);
        
        JButton verileriYenileButton = new JButton("Tüm Verileri Göster");
        verileriYenileButton.setBounds(619, 77, 151, 21);
        verileriYenileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rezervasyonlariTabloyaYukle();
            }
        });
        contentPane.add(verileriYenileButton);
        
        String kullaniciAdi = mainMenu.getKullaniciAdi();
        kullaniciGoster = new JLabel(kullaniciAdi);
		kullaniciGoster.setFont(new Font("Tahoma", Font.BOLD, 13));
		kullaniciGoster.setHorizontalAlignment(SwingConstants.CENTER);
		kullaniciGoster.setBounds(608, 0, 178, 30);
		ImageIcon loginIcon = new ImageIcon(MainMenu.class.getResource("/resources/login.png"));
		Image resizedImage = loginIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(resizedImage);
		kullaniciGoster.setIcon(resizedIcon);
		kullaniciGoster.setIconTextGap(5);
		contentPane.add(kullaniciGoster);
    


        String[] columnNames = {"Rezervasyon ID","Oda Numarası","Kişi Sayısı", "Fiyat", "Giriş Tarihi", "Çıkış Tarihi"};
        tableModel = new DefaultTableModel(columnNames, 0); 
        rezervasyonTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(rezervasyonTable);
        scrollPane.setBounds(10, 101, 760, 300);
        contentPane.add(scrollPane);
        
        

        JButton rezMenuAyrintiButton = new JButton("Ayrıntılar");
        rezMenuAyrintiButton.setBounds(10, 421, 120, 30);
        rezMenuAyrintiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int selectedRow = rezervasyonTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Lütfen bir rezervasyon seçin!", "Uyarı", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int rezervasyonId = (int) tableModel.getValueAt(selectedRow, 0); 
                LinkedList<Rezervasyon> rezervasyonlar = RezervasyonVeriOku.rezervasyonlariOku("src/data/rezervasyonlar.hot");
                Rezervasyon seciliRezervasyon = rezervasyonlar.search(new Rezervasyon(rezervasyonId));

                if (seciliRezervasyon != null) {
                    RezervasyonAyrintiDialog rezAyrintiDialog = new RezervasyonAyrintiDialog();
                    rezAyrintiDialog.setRezervasyonBilgileri(seciliRezervasyon);
                    rezAyrintiDialog.setLocationRelativeTo(RezervasyonMenu.this); 
                    rezAyrintiDialog.setVisible(true);
                                     
                    RezervasyonLog yeniLog = new RezervasyonLog(LoginAdmin.getGirisYapanAdmin(), seciliRezervasyon, "Ayrıntılar");                   
                    LinkedList<String> yeniLogListesi = new LinkedList<>();
                    yeniLogListesi.insertLast(yeniLog.toString());
                    RezervasyonLogVeriOkuKaydet.rezervasyonLogKaydet("src/data/rezervasyonLog.hot", yeniLogListesi, true);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Seçili rezervasyon bulunamadı!", "Hata", JOptionPane.ERROR_MESSAGE);
                }            
            }
        });
        contentPane.add(rezMenuAyrintiButton);
        
        
        
        
        JButton rezMenuAraButton = new JButton("Ara");
        rezMenuAraButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		RezervasyonAraDialog rezAraDialog = new RezervasyonAraDialog(RezervasyonMenu.this);
        		rezAraDialog.setLocationRelativeTo(RezervasyonMenu.this); 
        		 rezAraDialog.setVisible(true);
        		 rezervasyonlariTabloyaYukle();
        	}
        });
        rezMenuAraButton.setBounds(150, 421, 120, 30);
        contentPane.add(rezMenuAraButton);


        JButton rezMenuEkleButton = new JButton("Ekle");
        rezMenuEkleButton.setBounds(290, 421, 120, 30);
        rezMenuEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	RezervasyonEkleDialog rezEkleDialog = new RezervasyonEkleDialog(RezervasyonMenu.this);
            	rezEkleDialog.setLocationRelativeTo(RezervasyonMenu.this); 
                rezEkleDialog.setVisible(true);
                rezervasyonlariTabloyaYukle();
            }
        });
        contentPane.add(rezMenuEkleButton);


        JButton rezMenuSilButton = new JButton("Sil");
        rezMenuSilButton.setBounds(430, 421, 120, 30);
        rezMenuSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int selectedRow = rezervasyonTable.getSelectedRow(); 
                if (selectedRow == -1) { 
                    JOptionPane.showMessageDialog(null, "Lütfen bir rezervasyon seçin!", "Uyarı", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int rezervasyonId = (int) tableModel.getValueAt(selectedRow, 0);
                RezervasyonSilDialog rezSilDialog = new RezervasyonSilDialog(rezervasyonId, odalar, RezervasyonMenu.this);
                rezSilDialog.setLocationRelativeTo(RezervasyonMenu.this); 
                rezSilDialog.setVisible(true);            
            }
        });
        contentPane.add(rezMenuSilButton);


        JButton rezMenuGuncelleButton = new JButton("Güncelle");
        rezMenuGuncelleButton.setBounds(570, 421, 120, 30);
        rezMenuGuncelleButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	int selectedRow = rezervasyonTable.getSelectedRow(); 
            if (selectedRow == -1) { 
                JOptionPane.showMessageDialog(null, "Lütfen bir rezervasyon seçin!", "Uyarı", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int rezervasyonId = (int) tableModel.getValueAt(selectedRow, 0); // Rezervasyon ID
            LinkedList<Rezervasyon> rezervasyonlar = RezervasyonVeriOku.rezervasyonlariOku("src/data/rezervasyonlar.hot");
            Rezervasyon seciliRezervasyon = rezervasyonlar.search(new Rezervasyon(rezervasyonId));

            if (seciliRezervasyon != null) {
                RezervasyonGuncelleDialog rezGuncelleDialog = new RezervasyonGuncelleDialog(RezervasyonMenu.this, rezervasyonId);
                rezGuncelleDialog.setRezervasyonBilgileri(seciliRezervasyon);
                rezGuncelleDialog.setLocationRelativeTo(RezervasyonMenu.this); 
                rezGuncelleDialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Seçili rezervasyon bulunamadı!", "Hata", JOptionPane.ERROR_MESSAGE);
            } 
        	}
        });
        contentPane.add(rezMenuGuncelleButton);


        JButton rezMenuGeriButton = new JButton("Geri Dön");
        rezMenuGeriButton.setBounds(670, 523, 100, 30);
        rezMenuGeriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenu.setVisible(true);
                dispose();
            }
        });
        contentPane.add(rezMenuGeriButton);
        
        
        
      rezervasyonlariTabloyaYukle();
        
        
    }
	protected void rezervasyonlariTabloyaYukle() {
	    LinkedList<Rezervasyon> rezervasyonlar = RezervasyonVeriOku.rezervasyonlariOku("src/data/rezervasyonlar.hot");
	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    tableModel.setRowCount(0); 

	    for (Rezervasyon rezervasyon : rezervasyonlar) {
	        Oda oda = odalar.search(new Oda(rezervasyon.getOdaNumarasi()));
	        double fiyat = (oda != null) ? oda.getFiyat() : 0.0;
	        rezervasyon.setFiyat(fiyat);

	        Object[] row = {
	            rezervasyon.getRezervasyonId(),
	            rezervasyon.getOdaNumarasi(),
	            rezervasyon.getKisiSayisi(),
	            fiyat,
	            DATE_FORMAT.format(rezervasyon.getGirisTarihi()),
	            DATE_FORMAT.format(rezervasyon.getCikisTarihi())
	        };
	        tableModel.addRow(row);
	    }
	}
	
	public Rezervasyon rezervasyonAra(int rezervasyonId) {
	    LinkedList<Rezervasyon> rezervasyonlar = RezervasyonVeriOku.rezervasyonlariOku("src/data/rezervasyonlar.hot");
	    tabloyuTemizle();
	    for (Rezervasyon rezervasyon : rezervasyonlar) {
	        if (rezervasyon.getRezervasyonId() == rezervasyonId) {
	            tabloyaRezervasyonEkle(rezervasyon);
	            return rezervasyon; 
	        }
	    }
	    return null; 
	}
	
	private void tabloyuTemizle() {
	    DefaultTableModel model = (DefaultTableModel) rezervasyonTable.getModel();
	    model.setRowCount(0);
	}
	
	private void tabloyaRezervasyonEkle(Rezervasyon rezervasyon) {
	    DefaultTableModel model = (DefaultTableModel) rezervasyonTable.getModel();
	    model.addRow(new Object[]{
	        rezervasyon.getRezervasyonId(),
	        rezervasyon.getOdaTipi(),
	        rezervasyon.getOdaNumarasi(),
	        rezervasyon.getKisiSayisi(),
	        rezervasyon.getFormattedGirisTarihi(),
	        rezervasyon.getFormattedCikisTarihi(),
	        rezervasyon.getOda().getFiyat()
	    });
	}
}
