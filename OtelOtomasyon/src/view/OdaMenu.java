package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.LinkedList;
import model.Oda;
import service.OdaVeriOku;

public class OdaMenu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable odaTable;
    private DefaultTableModel tableModel;
    private JLabel kullaniciGoster;
    
    LinkedList<Oda> odalar = new LinkedList<>();
    private MainMenu mainMenu;

    public OdaMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        setTitle("Oda İşlemleri");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel odaText = new JLabel("Oda İşlemleri");
        odaText.setHorizontalAlignment(SwingConstants.CENTER);
        odaText.setFont(new Font("Tahoma", Font.BOLD, 28));
        odaText.setBounds(10, 10, 776, 35);
        contentPane.add(odaText);
        
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
        
        JButton verileriYenileButton = new JButton("Tüm Verileri Göster");
        verileriYenileButton.setBounds(619, 55, 151, 21);
        verileriYenileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	odalariTabloyaYukle();
            }
        });
        contentPane.add(verileriYenileButton);

        String[] columnNames = { "Oda Numarası", "Oda Tipi", "Fiyat", "Doluluk", "Durum" };
        tableModel = new DefaultTableModel(columnNames, 0);
        odaTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(odaTable);
        scrollPane.setBounds(10, 82, 760, 300);
        contentPane.add(scrollPane);

        JButton odaAraButton = new JButton("Oda Ara");
        odaAraButton.setBounds(20, 402, 150, 30);
        odaAraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	OdaAraDialog odaAraDialog = new OdaAraDialog(OdaMenu.this);
                odaAraDialog.setLocationRelativeTo(OdaMenu.this); 
                odaAraDialog.setVisible(true); 
                odalariTabloyaYukle(); 

            }
        });
        contentPane.add(odaAraButton);

        JButton odaEkleButton = new JButton("Oda Ekle");
        odaEkleButton.setBounds(198, 402, 150, 30);
        odaEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	OdaEkleDialog odaEkleDialog = new OdaEkleDialog(OdaMenu.this);
                odaEkleDialog.setLocationRelativeTo(OdaMenu.this); 
                odaEkleDialog.setVisible(true);
            }
        });
        contentPane.add(odaEkleButton);
        

        JButton odaSilButton = new JButton("Oda Sil");
        odaSilButton.setBounds(379, 402, 150, 30);
        odaSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int selectedRow = odaTable.getSelectedRow();
                if (selectedRow == -1) { 
                    JOptionPane.showMessageDialog(null, "Lütfen bir oda seçin!", "Uyarı", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                String odaNumarasi = (String) tableModel.getValueAt(selectedRow, 0);
                OdaSilDialog odaSilDialog = new OdaSilDialog(odaNumarasi, odalar, OdaMenu.this);
                odaSilDialog.setLocationRelativeTo(OdaMenu.this); 
                odaSilDialog.setVisible(true); 
            }
            
        });
        contentPane.add(odaSilButton);


        JButton odaGuncelleButton = new JButton("Oda Güncelle");
        odaGuncelleButton.setBounds(568, 402, 150, 30);
        odaGuncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int selectedRow = odaTable.getSelectedRow(); 
                if (selectedRow == -1) { 
                    JOptionPane.showMessageDialog(null, "Lütfen bir oda seçin!", "Uyarı", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                String odaNumarasi = (String) tableModel.getValueAt(selectedRow, 0); 
                LinkedList<Oda> odalar = OdaVeriOku.odalariOku("src/data/odalar.hot");
                Oda seciliOda = odalar.search(new Oda(odaNumarasi));
                
                if (seciliOda != null) {
                    OdaGuncelleDialog odaGuncelleDialog = new OdaGuncelleDialog(OdaMenu.this, odaNumarasi);
                    odaGuncelleDialog.setOdaBilgileri(seciliOda);
                    odaGuncelleDialog.setLocationRelativeTo(OdaMenu.this); 
                    odaGuncelleDialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Seçili oda bulunamadı!", "Hata", JOptionPane.ERROR_MESSAGE);
                } 
            }
        });
        contentPane.add(odaGuncelleButton);


        JButton geriButton = new JButton("Geri Dön");
        geriButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainMenu.setVisible(true);
                dispose();
            }
        });
        geriButton.setBounds(670, 500, 100, 30);
        contentPane.add(geriButton);
        
        
        
        odalariTabloyaYukle();
    }
    
    public static LinkedList<Oda> getOdalar() {
        return OdaVeriOku.odalariOku("src/data/odalar.hot");
    }
    
    public Oda odaAra(String odaNumarasi) {
        LinkedList<Oda> odalar = OdaVeriOku.odalariOku("src/data/odalar.hot");
        tabloyuTemizle();  // Tabloyu temizle
        
        for (Oda oda : odalar) {
            if (oda.getOdaNumarasi().equals(odaNumarasi)) {
                tabloyaOdaEkle(oda);  // Tabloya ekle
                return oda ;  // Bulduğunda döngüyü sonlandır
            }
        }
        return null;
    }
    
    
    
    protected void odalariTabloyaYukle() {
        LinkedList<Oda> odalar = OdaVeriOku.odalariOku("src/data/odalar.hot");
        
        tableModel.setRowCount(0); // Tablodaki eski veriyi temizle
        
        for (int i = 0; i < odalar.size(); i++) {
            Oda oda = odalar.get(i); 
            Object[] row = {
                oda.getOdaNumarasi(),
                oda.getOdaTipi(),
                oda.getFiyat(),
                oda.getDoluluk() ? "Dolu" : "Boş",
                oda.getDurum() ? "Temiz" : "Kirli"
            };
            tableModel.addRow(row);
        }
    }
    
    private void tabloyuTemizle() {
	    DefaultTableModel model = (DefaultTableModel) odaTable.getModel();
	    model.setRowCount(0);
	}
    
    private void tabloyaOdaEkle(Oda oda) {
        DefaultTableModel model = (DefaultTableModel) odaTable.getModel();
        model.addRow(new Object[]{
            oda.getOdaNumarasi(),      // Oda Numarası
            oda.getOdaTipi(),          // Oda Tipi
            oda.getFiyat(),            // Fiyat
            oda.getDoluluk() ? "Dolu" : "Boş",  // Doluluk Durumu
            oda.getDurum() ? "Temiz" : "Kirli"   // Durum
        });
    }
}
