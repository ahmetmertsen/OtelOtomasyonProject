package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputVerifier;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.LinkedList;
import model.LoginAdmin;
import model.Oda;
import model.OdaLog;
import service.OdaLogVeriOkuKaydet;
import service.OdaVeriKaydet;
import service.OdaVeriOku;

public class OdaEkleDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField odaNoTextField;
    private JTextField odaFiyatTextField;
    private JComboBox<String> odaTipiComboBox;
    private JComboBox<String> odaDolulukComboBox;
    private JComboBox<String> odaDurumComboBox;
    
    private OdaMenu odaMenu;


    public OdaEkleDialog(OdaMenu odaMenu) {
    	this.odaMenu = odaMenu;
        setTitle("Oda Ekle");
        setBounds(100, 100, 240, 240);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel odaTipiText = new JLabel("Oda Tipi:");
        odaTipiText.setFont(new Font("Tahoma", Font.PLAIN, 13));
        odaTipiText.setBounds(10, 10, 81, 20);
        contentPanel.add(odaTipiText);

        odaTipiComboBox = new JComboBox<>();
        odaTipiComboBox.setModel(new DefaultComboBoxModel<>(new String[] {"1 Kişilik", "2 Kişilik", "3 Kişilik"}));
        odaTipiComboBox.setBounds(106, 11, 96, 21);
        contentPanel.add(odaTipiComboBox);

        JLabel odaNoText = new JLabel("Oda Numarası:");
        odaNoText.setFont(new Font("Tahoma", Font.PLAIN, 13));
        odaNoText.setBounds(10, 41, 86, 20);
        contentPanel.add(odaNoText);

        odaNoTextField = new JTextField();
        odaNoTextField.setBounds(106, 42, 96, 19);
        contentPanel.add(odaNoTextField);
        odaNoTextField.setColumns(10);

        JLabel odaFiyatText = new JLabel("Fiyat:");
        odaFiyatText.setFont(new Font("Tahoma", Font.PLAIN, 13));
        odaFiyatText.setBounds(10, 71, 86, 20);
        contentPanel.add(odaFiyatText);

        odaFiyatTextField = new JTextField();
        odaFiyatTextField.setColumns(10);
        odaFiyatTextField.setBounds(106, 72, 96, 19);
        contentPanel.add(odaFiyatTextField);

        JLabel odaDolulukText = new JLabel("Doluluk:");
        odaDolulukText.setFont(new Font("Tahoma", Font.PLAIN, 13));
        odaDolulukText.setBounds(10, 101, 86, 20);
        contentPanel.add(odaDolulukText);

        odaDolulukComboBox = new JComboBox<>();
        odaDolulukComboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Boş", "Dolu"}));
        odaDolulukComboBox.setBounds(106, 102, 96, 21);
        contentPanel.add(odaDolulukComboBox);

        JLabel odaDurumText = new JLabel("Durum:");
        odaDurumText.setFont(new Font("Tahoma", Font.PLAIN, 13));
        odaDurumText.setBounds(10, 131, 86, 20);
        contentPanel.add(odaDurumText);

        odaDurumComboBox = new JComboBox<>();
        odaDurumComboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Temiz", "Kirli"}));
        odaDurumComboBox.setBounds(106, 132, 96, 21);
        contentPanel.add(odaDurumComboBox);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton ekleButton = new JButton("Ekle");
        ekleButton.setActionCommand("OK");
        ekleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String odaNumarasi = odaNoTextField.getText().trim();
                String fiyatText = odaFiyatTextField.getText().trim();

                if (!odaNumarasi.matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "Oda numarası sadece rakamlardan oluşmalı!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!fiyatText.matches("\\d+(\\.\\d{1,2})?")) {  
                    JOptionPane.showMessageDialog(null, "Fiyat sadece rakamlardan oluşmalı!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    String odaTipi = (String) odaTipiComboBox.getSelectedItem();
                    double fiyat = Double.parseDouble(fiyatText); 
                    boolean dolulukDurumu = "Dolu".equals(odaDolulukComboBox.getSelectedItem());
                    boolean durum = "Temiz".equals(odaDurumComboBox.getSelectedItem());

                    LinkedList<Oda> mevcutOdalar = OdaVeriOku.odalariOku("src/data/odalar.hot");

                    boolean odaVarMi = false;
                    for (Oda oda : mevcutOdalar) {
                        if (oda.getOdaNumarasi().equals(odaNumarasi)) {
                            odaVarMi = true;
                            break;
                        }
                    }

                    if (odaVarMi) {
                        JOptionPane.showMessageDialog(null, "Bu oda numarası zaten mevcut!", "Uyarı", JOptionPane.WARNING_MESSAGE);
                    } else {
                        Oda yeniOda = new Oda(odaNumarasi, odaTipi, fiyat, dolulukDurumu, durum);
                        mevcutOdalar.insertLast(yeniOda);
                        OdaVeriKaydet.odaKaydet("src/data/odalar.hot", mevcutOdalar, false);
                        odaMenu.odalariTabloyaYukle();

                        JOptionPane.showMessageDialog(null, "Oda başarıyla kaydedildi!", "Mesaj", JOptionPane.INFORMATION_MESSAGE);

                        OdaLog yeniLog = new OdaLog(LoginAdmin.getGirisYapanAdmin(), yeniOda, "Ekleme İşlemi");
                        LinkedList<String> yeniLogListesi = new LinkedList<>();
                        yeniLogListesi.insertLast(yeniLog.toString());
                        OdaLogVeriOkuKaydet.odaLogKaydet("src/data/odaLog.hot", yeniLogListesi, true);

                        dispose();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Oda kaydedilirken bir hata oluştu!", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPane.add(ekleButton);
        getRootPane().setDefaultButton(ekleButton);

        JButton iptalButton = new JButton("İptal");
        iptalButton.setActionCommand("İptal");
        iptalButton.addActionListener(e -> dispose());
        buttonPane.add(iptalButton);
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

}