package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

public class GenelIslemMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;	
	private MainMenu mainMenu;
	private JLabel kullaniciGoster;
	


	public GenelIslemMenu(MainMenu mainMenu) {
		setTitle("Genel İşlemler");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel genelText = new JLabel("Genel İşlemler");
		genelText.setHorizontalAlignment(SwingConstants.CENTER);
		genelText.setFont(new Font("Tahoma", Font.BOLD, 28));
		genelText.setBounds(10, 10, 776, 35);
		contentPane.add(genelText);
		
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
		
		
		JButton musteriAraButton = new JButton("Müşteri Arama");
		ImageIcon musteriIcon = new ImageIcon(GenelIslemMenu.class.getResource("/resources/musteriArama.png"));
		Image musteriImage = musteriIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		musteriAraButton.setIcon(new ImageIcon(musteriImage));;
		musteriAraButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		musteriAraButton.setBounds(70, 60, 274, 154);
		musteriAraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenelMusteriArama genelMusteriArama = new GenelMusteriArama();
				genelMusteriArama.setLocationRelativeTo(GenelIslemMenu.this); 
				genelMusteriArama.setVisible(true);
			}
		});
		contentPane.add(musteriAraButton);
		
		JButton tarihAramaButton = new JButton("Tarih Aralığındaki Rezervasyonlar");
		ImageIcon tarihAralikIcon = new ImageIcon(GenelIslemMenu.class.getResource("/resources/tarihArama.png"));
		Image tarihAralikImage = tarihAralikIcon.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
		tarihAramaButton.setIcon(new ImageIcon(tarihAralikImage));;
		tarihAramaButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tarihAramaButton.setBounds(430, 60, 274, 154);
		tarihAramaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenelTarihRezervasyonArama genelTarihRezervasyonArama = new GenelTarihRezervasyonArama();
				genelTarihRezervasyonArama.setLocationRelativeTo(GenelIslemMenu.this); 
				genelTarihRezervasyonArama.setVisible(true);
			}
		});
		contentPane.add(tarihAramaButton);
		
		JButton rezervasyonLogButton = new JButton("Rezervasyon Log");
		ImageIcon rezervasyonLogIcon = new ImageIcon(GenelIslemMenu.class.getResource("/resources/log.png"));
		Image rezervasoynLogImage = rezervasyonLogIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		rezervasyonLogButton.setIcon(new ImageIcon(rezervasoynLogImage));;
		rezervasyonLogButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rezervasyonLogButton.setBounds(70, 270, 274, 154);
		rezervasyonLogButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenelRezervasyonLog genelRezervasyonLog = new GenelRezervasyonLog();
				genelRezervasyonLog.setLocationRelativeTo(GenelIslemMenu.this); 
				genelRezervasyonLog.setVisible(true);
			}
		});
		contentPane.add(rezervasyonLogButton);
		
		JButton odaLogButton = new JButton("Oda Log");
		ImageIcon odaLogIcon = new ImageIcon(GenelIslemMenu.class.getResource("/resources/log.png"));
		Image odaLogImage = odaLogIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		odaLogButton.setIcon(new ImageIcon(odaLogImage));;
		odaLogButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		odaLogButton.setBounds(430, 268, 274, 154);
		odaLogButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenelOdaLog genelOdaLog = new GenelOdaLog();
				genelOdaLog.setLocationRelativeTo(GenelIslemMenu.this); 
				genelOdaLog.setVisible(true);
			}
		});
		contentPane.add(odaLogButton);
		
		JButton geriButton = new JButton("Geri Dön");
		geriButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenu.setVisible(true);
				dispose();
			}
		});
		geriButton.setBounds(676, 523, 100, 30);
		contentPane.add(geriButton);
		
		
	}
}
