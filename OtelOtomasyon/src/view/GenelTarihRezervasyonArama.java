package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.LinkedList;
import model.Oda;
import model.Rezervasyon;
import service.RezervasyonVeriOku;

public class GenelTarihRezervasyonArama extends JFrame {
    private JPanel contentPane;
    private JTable rezervasyonTable;
    private DefaultTableModel tableModel;
    private JTextField tarihTextField;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private LinkedList<Rezervasyon> rezervasyonListesi = new LinkedList<>();
    private LinkedList<Oda> odalar;


    public GenelTarihRezervasyonArama() {
        this.odalar = OdaMenu.getOdalar();
        setTitle("Tarihe Göre Rezervasyon Arama");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 500);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel tarihAramaTitle = new JLabel("Tarihe Göre Rezervasyon Arama");
        tarihAramaTitle.setHorizontalAlignment(SwingConstants.CENTER);
        tarihAramaTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
        tarihAramaTitle.setBounds(10, 10, 676, 40);
        contentPane.add(tarihAramaTitle);

        String[] columnNames = {"Rezervasyon ID", "Oda Numarası", "Kişi Sayısı", "Fiyat", "Giriş Tarihi", "Çıkış Tarihi"};
        tableModel = new DefaultTableModel(columnNames, 0);
        rezervasyonTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(rezervasyonTable);
        scrollPane.setBounds(10, 134, 659, 272);
        contentPane.add(scrollPane);

        JLabel tarihText = new JLabel("Giriş Tarih Giriniz:");
        tarihText.setHorizontalAlignment(SwingConstants.RIGHT);
        tarihText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tarihText.setBounds(20, 60, 120, 20);
        contentPane.add(tarihText);

        tarihTextField = new JTextField();
        tarihTextField.setBounds(150, 60, 120, 20);
        contentPane.add(tarihTextField);
        tarihTextField.setColumns(10);

        JRadioButton oncesiRadioButton = new JRadioButton("Tarih Öncesi");
        oncesiRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        oncesiRadioButton.setBounds(278, 61, 110, 21);
        contentPane.add(oncesiRadioButton);

        JRadioButton sonrasiRadioButton = new JRadioButton("Tarih Sonrası");
        sonrasiRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        sonrasiRadioButton.setBounds(390, 61, 110, 21);
        contentPane.add(sonrasiRadioButton);

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(oncesiRadioButton);
        radioGroup.add(sonrasiRadioButton);

        JButton araButton = new JButton("Ara");
        araButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        araButton.setBounds(185, 90, 85, 23);
        araButton.addActionListener(e -> {
            String tarihStr = tarihTextField.getText().trim();
            if (tarihStr.isEmpty()) {
                showError("Lütfen geçerli bir tarih giriniz!");
                return;
            }

            try {
                Date girilenTarih = DATE_FORMAT.parse(tarihStr);
                boolean oncesi = oncesiRadioButton.isSelected();
                boolean sonrasi = sonrasiRadioButton.isSelected();

                if (!oncesi && !sonrasi) {
                    showError("Lütfen bir tarih filtresi seçiniz!");
                    return;
                }

                rezervasyonlariFiltrele(girilenTarih, oncesi);
            } catch (ParseException ex) {
                showError("Tarih formatı geçersiz! Lütfen dd/MM/yyyy formatında bir tarih giriniz.");
            }
        });
        contentPane.add(araButton);

        JButton kapatButton = new JButton("Kapat");
        kapatButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        kapatButton.setBounds(584, 416, 85, 21);
        kapatButton.addActionListener(e -> dispose());
        contentPane.add(kapatButton);   

        rezervasyonlariTabloyuTemizle();
    }

    private void rezervasyonlariFiltrele(Date girilenTarih, boolean oncesi) {
        LinkedList<Rezervasyon> rezervasyonlar = RezervasyonVeriOku.rezervasyonlariOku("src/data/rezervasyonlar.hot");
        tableModel.setRowCount(0);

        for (Rezervasyon rezervasyon : rezervasyonlar) {
            Date girisTarihi = rezervasyon.getGirisTarihi();

            if ((oncesi && girisTarihi.before(girilenTarih)) || (!oncesi && girisTarihi.after(girilenTarih))) {
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
    }

    private void rezervasyonlariTabloyuTemizle() {
        tableModel.setRowCount(0); // Tabloyu temizle
    }

    private void showError(String message) {
        javax.swing.JOptionPane.showMessageDialog(this, message, "Hata", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
}
