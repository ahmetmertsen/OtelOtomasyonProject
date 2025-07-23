package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.LinkedList;
import model.Rezervasyon;
import service.RezervasyonVeriOku;

public class GenelMusteriArama extends JFrame {
	private final JPanel contentPanel = new JPanel();
	private static final long serialVersionUID = 1L;
	private JTextField tcVeyaAdSoyadTextField;


	public GenelMusteriArama() {
		setTitle("Müşteri Ara");
		setBounds(100, 100, 377, 107);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel tcVeyaAdSoyadText = new JLabel("Müşteri TC veya AD SOYAD :");
		tcVeyaAdSoyadText.setFont(new Font("Tahoma", Font.BOLD, 12));
		tcVeyaAdSoyadText.setBounds(10, 10, 180, 19);
		contentPanel.add(tcVeyaAdSoyadText);
		
		tcVeyaAdSoyadTextField = new JTextField();
		tcVeyaAdSoyadTextField.setBounds(200, 11, 153, 19);
		contentPanel.add(tcVeyaAdSoyadTextField);
		tcVeyaAdSoyadTextField.setColumns(10);
		{
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton musteriAraButton = new JButton("Ara");
			musteriAraButton.setActionCommand("Ara");
			musteriAraButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	String aramaGirisi = tcVeyaAdSoyadTextField.getText().trim();

		            if (aramaGirisi.isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Lütfen bir değer girin!", "Uyarı", JOptionPane.WARNING_MESSAGE);
		                return;
		            }

		            LinkedList<Rezervasyon> rezervasyonlar = RezervasyonVeriOku.rezervasyonlariOku("src/data/rezervasyonlar.hot");

		            Rezervasyon bulunanRezervasyon = null;
		            for (Rezervasyon rezervasyon : rezervasyonlar) {
		                if (rezervasyon.getKisiBilgileri().stream().anyMatch(kisi ->
		                        kisi.getTcKimlik().equalsIgnoreCase(aramaGirisi) ||
		                        kisi.getAdSoyad().equalsIgnoreCase(aramaGirisi))) {
		                    bulunanRezervasyon = rezervasyon;
		                    break;
		                }
		            }

		            if (bulunanRezervasyon != null) {
		                RezervasyonAyrintiDialog rezAyrintiDialog = new RezervasyonAyrintiDialog();
		                rezAyrintiDialog.setRezervasyonBilgileri(bulunanRezervasyon);
		                rezAyrintiDialog.setLocationRelativeTo(null);
		                dispose();
		                rezAyrintiDialog.setVisible(true);
		            } else {
		                JOptionPane.showMessageDialog(null, "Müşteri bulunamadı!", "Hata", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		       });
			getRootPane().setDefaultButton(musteriAraButton);
			buttonPane.add(musteriAraButton);
			
			
			JButton musteriİptalButton = new JButton("İptal");
			musteriİptalButton.setActionCommand("İptal");
			musteriİptalButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	dispose();
		        }
		       });
			buttonPane.add(musteriİptalButton);
	   }
	}
}
