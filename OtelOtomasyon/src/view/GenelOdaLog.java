package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.LinkedList;
import service.OdaLogVeriOkuKaydet;
import service.RezervasyonLogVeriOkuKaydet;

public class GenelOdaLog extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable rezervasyonTable;
	private DefaultTableModel tableModel;

	
	public GenelOdaLog() {
		setTitle("Oda Log");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 450);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel odaLogTitle = new JLabel("Oda Log");
        odaLogTitle.setHorizontalAlignment(SwingConstants.CENTER);
        odaLogTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        odaLogTitle.setBounds(10, 10, 576, 40);
        contentPane.add(odaLogTitle);
        
        
        String[] columnNames = {"Yetkili","Oda Numarası","Yapılan İşlem","Saat ve Tarih"};
        tableModel = new DefaultTableModel(columnNames, 0); 
        rezervasyonTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(rezervasyonTable);
        scrollPane.setBounds(30, 60, 540, 300);
        contentPane.add(scrollPane);
        
        JButton kapatButton = new JButton("Kapat");
        kapatButton.setBounds(470, 375, 90, 25);
        kapatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
        contentPane.add(kapatButton);
        
        odaLoglariTabloyaYukle();
	}
	
	private void odaLoglariTabloyaYukle() {
        String dosyaAdi = "src/data/odaLog.hot";
        LinkedList<String> odaLoglar = OdaLogVeriOkuKaydet.odaLogOku(dosyaAdi);
        tableModel.setRowCount(0);

        for (String odaLog : odaLoglar) {
            String[] detaylar = odaLog.split(";");

            if (detaylar.length == 4) {
                Object[] row = {
                    detaylar[0], 
                    detaylar[1], 
                    detaylar[2], 
                    detaylar[3]
                };
                tableModel.addRow(row);
            } else {
                System.out.println("Hatalı veri formatı: " + odaLog);
            }
        }
    }

}
