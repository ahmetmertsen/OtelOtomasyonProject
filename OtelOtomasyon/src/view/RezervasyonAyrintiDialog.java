package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.KisiBilgileri;
import model.LinkedList;
import model.Rezervasyon;

import java.awt.*;

public class RezervasyonAyrintiDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JPanel musteriBilgilerPanel;
    private JTextField rezAyrintiIdTextField;
    private JTextField rezAyrintiKisiSayisiTextField;
    private JTextField rezAyrintiOdaTipiTextField;
    private JTextField rezAyrintiOdaNumarasiTextField;
    private JTextField rezAyrintiGirisTextField;
    private JTextField rezAyrintiCikisTextField;
    private JTextField rezAyrintiFiyatTextField;


    public RezervasyonAyrintiDialog() {
    	setTitle("Rezervasyon Ayrıntıları");
        setBounds(100, 100, 539, 498);
        setResizable(false);
        getContentPane().setLayout(null);

        contentPanel.setBounds(0, 0, 525, 212);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel);
        contentPanel.setLayout(null);

        JLabel rezAyrintiIdText = new JLabel("Rezervasyon ID:");
        rezAyrintiIdText.setHorizontalAlignment(SwingConstants.RIGHT);
        rezAyrintiIdText.setFont(new Font("Tahoma", Font.BOLD, 12));
        rezAyrintiIdText.setBounds(100, 8, 120, 20);
        contentPanel.add(rezAyrintiIdText);

        rezAyrintiIdTextField = new JTextField();
        rezAyrintiIdTextField.setBounds(230, 10, 150, 20);
        rezAyrintiIdTextField.setEditable(false);
        contentPanel.add(rezAyrintiIdTextField);

        JLabel rezAyrintiKisiSayisiText = new JLabel("Kişi Sayısı:");
        rezAyrintiKisiSayisiText.setHorizontalAlignment(SwingConstants.RIGHT);
        rezAyrintiKisiSayisiText.setFont(new Font("Tahoma", Font.BOLD, 12));
        rezAyrintiKisiSayisiText.setBounds(100, 38, 120, 20);
        contentPanel.add(rezAyrintiKisiSayisiText);

        rezAyrintiKisiSayisiTextField = new JTextField();
        rezAyrintiKisiSayisiTextField.setBounds(230, 40, 150, 20);
        rezAyrintiKisiSayisiTextField.setEditable(false);
        contentPanel.add(rezAyrintiKisiSayisiTextField);

        JLabel rezAyrintiOdaTipiText = new JLabel("Oda Tipi:");
        rezAyrintiOdaTipiText.setHorizontalAlignment(SwingConstants.RIGHT);
        rezAyrintiOdaTipiText.setFont(new Font("Tahoma", Font.BOLD, 12));
        rezAyrintiOdaTipiText.setBounds(100, 68, 120, 20);
        contentPanel.add(rezAyrintiOdaTipiText);

        rezAyrintiOdaTipiTextField = new JTextField();
        rezAyrintiOdaTipiTextField.setEditable(false);
        rezAyrintiOdaTipiTextField.setBounds(230, 70, 150, 20);
        contentPanel.add(rezAyrintiOdaTipiTextField);

        JLabel rezAyrintiOdaNumarasiText = new JLabel("Oda Numarası:");
        rezAyrintiOdaNumarasiText.setHorizontalAlignment(SwingConstants.RIGHT);
        rezAyrintiOdaNumarasiText.setFont(new Font("Tahoma", Font.BOLD, 12));
        rezAyrintiOdaNumarasiText.setBounds(100, 96, 120, 20);
        contentPanel.add(rezAyrintiOdaNumarasiText);

        rezAyrintiOdaNumarasiTextField = new JTextField();
        rezAyrintiOdaNumarasiTextField.setEditable(false);
        rezAyrintiOdaNumarasiTextField.setBounds(230, 98, 150, 20);
        contentPanel.add(rezAyrintiOdaNumarasiTextField);

        JLabel rezAyrintiGirisText = new JLabel("Giriş Tarihi:");
        rezAyrintiGirisText.setHorizontalAlignment(SwingConstants.RIGHT);
        rezAyrintiGirisText.setFont(new Font("Tahoma", Font.BOLD, 12));
        rezAyrintiGirisText.setBounds(100, 161, 120, 20);
        contentPanel.add(rezAyrintiGirisText);

        rezAyrintiGirisTextField = new JTextField();
        rezAyrintiGirisTextField.setBounds(230, 163, 150, 20);
        rezAyrintiGirisTextField.setEditable(false);
        contentPanel.add(rezAyrintiGirisTextField);

        JLabel rezAyrintiCikisText = new JLabel("Çıkış Tarihi:");
        rezAyrintiCikisText.setHorizontalAlignment(SwingConstants.RIGHT);
        rezAyrintiCikisText.setFont(new Font("Tahoma", Font.BOLD, 12));
        rezAyrintiCikisText.setBounds(100, 190, 120, 20);
        contentPanel.add(rezAyrintiCikisText);

        rezAyrintiCikisTextField = new JTextField();
        rezAyrintiCikisTextField.setEditable(false);
        rezAyrintiCikisTextField.setBounds(230, 192, 150, 20);
        contentPanel.add(rezAyrintiCikisTextField);

        JLabel rezAyrintiFiyatText = new JLabel("Fiyat:");
        rezAyrintiFiyatText.setHorizontalAlignment(SwingConstants.RIGHT);
        rezAyrintiFiyatText.setFont(new Font("Tahoma", Font.BOLD, 12));
        rezAyrintiFiyatText.setBounds(100, 129, 120, 20);
        contentPanel.add(rezAyrintiFiyatText);

        rezAyrintiFiyatTextField = new JTextField();
        rezAyrintiFiyatTextField.setEditable(false);
        rezAyrintiFiyatTextField.setBounds(230, 131, 150, 20);
        contentPanel.add(rezAyrintiFiyatTextField);
    
        
        

        musteriBilgilerPanel = new JPanel();
        musteriBilgilerPanel.setBounds(10, 215, 520, 205);
        musteriBilgilerPanel.setLayout(null);
        musteriBilgilerPanel.setBorder(BorderFactory.createTitledBorder("Müşteri Bilgileri"));
        getContentPane().add(musteriBilgilerPanel);


        JPanel buttonPane = new JPanel();
        buttonPane.setBounds(10, 420, 505, 31);
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane);

        JButton rezAyrintiKapatButton = new JButton("Kapat");
        rezAyrintiKapatButton.addActionListener(e -> dispose());
        buttonPane.add(rezAyrintiKapatButton);
    }


    protected void updateMusteriBilgilerPanel(int kisiSayisi) {
        musteriBilgilerPanel.removeAll();

        for (int i = 0; i < kisiSayisi; i++) {
            int yOffset = 20 + i * 40; 

            JLabel lblTcKimlik = new JLabel("TC Kimlik:");
            lblTcKimlik.setBounds(10, yOffset, 80, 20);
            musteriBilgilerPanel.add(lblTcKimlik);

            JTextField tcTextField = new JTextField("12345678901");
            tcTextField.setBounds(70, yOffset, 90, 20);
            tcTextField.setEditable(false);
            musteriBilgilerPanel.add(tcTextField);

            JLabel lblAdSoyad = new JLabel("Ad Soyad:");
            lblAdSoyad.setBounds(180, yOffset, 80, 20);
            musteriBilgilerPanel.add(lblAdSoyad);

            JTextField adSoyadTextField = new JTextField("Ad Soyad " + (i + 1));
            adSoyadTextField.setBounds(240, yOffset, 95, 20);
            adSoyadTextField.setEditable(false);
            musteriBilgilerPanel.add(adSoyadTextField);

            JLabel lblDogumTarihi = new JLabel("Doğum Tarihi:");
            lblDogumTarihi.setBounds(350, yOffset, 80, 20);
            musteriBilgilerPanel.add(lblDogumTarihi);

            JTextField dogumTarihiTextField = new JTextField("01/01/2000");
            dogumTarihiTextField.setBounds(430, yOffset, 80, 20);
            dogumTarihiTextField.setEditable(false);
            musteriBilgilerPanel.add(dogumTarihiTextField);
        }

        musteriBilgilerPanel.revalidate();
        musteriBilgilerPanel.repaint();
    }
    
    protected void setRezervasyonBilgileri(Rezervasyon rezervasyon) {
        rezAyrintiIdTextField.setText(String.valueOf(rezervasyon.getRezervasyonId()));
        rezAyrintiKisiSayisiTextField.setText(String.valueOf(rezervasyon.getKisiSayisi()));
        rezAyrintiOdaTipiTextField.setText(rezervasyon.getOdaTipi());
        rezAyrintiOdaNumarasiTextField.setText(rezervasyon.getOdaNumarasi());
        rezAyrintiGirisTextField.setText(rezervasyon.getFormattedGirisTarihi().toString());
        rezAyrintiCikisTextField.setText(rezervasyon.getFormattedCikisTarihi().toString());
        rezAyrintiFiyatTextField.setText(String.valueOf(rezervasyon.getFiyat()));

        LinkedList<KisiBilgileri> kisiBilgileri = rezervasyon.getKisiBilgileri();
        updateMusteriBilgilerPanel(kisiBilgileri.size());
        

        for (int i = 0; i < kisiBilgileri.size(); i++) {
            KisiBilgileri kisi = kisiBilgileri.get(i);


            int baseIndex = i * 6; 
            JTextField tcTextField = (JTextField) musteriBilgilerPanel.getComponent(baseIndex + 1);
            JTextField adSoyadTextField = (JTextField) musteriBilgilerPanel.getComponent(baseIndex + 3);
            JTextField dogumTarihiTextField = (JTextField) musteriBilgilerPanel.getComponent(baseIndex + 5);

            tcTextField.setText(kisi.getTcKimlik());
            adSoyadTextField.setText(kisi.getAdSoyad());
            dogumTarihiTextField.setText(kisi.getDogumTarihi());
            

        }
    }
}
