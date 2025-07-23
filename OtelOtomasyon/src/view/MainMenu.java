package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel kullaniciGoster;


	public MainMenu(String kullaniciAdi) {
		setTitle("Otel Otomasyon Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel mainMenuTitle = new JLabel("Otel Otomasyon Sistemi");
		mainMenuTitle.setFont(new Font("Tahoma", Font.ITALIC, 32));
		mainMenuTitle.setHorizontalAlignment(SwingConstants.CENTER);
		mainMenuTitle.setBounds(0, 20, 759, 33);
		contentPane.add(mainMenuTitle);
		
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
		
		JButton mainMenuRezIslemButton = new JButton("Rezervasyon İşlemleri");
		ImageIcon rezIcon = new ImageIcon(MainMenu.class.getResource("/resources/rezIcon.png"));
		Image rezImage = rezIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		mainMenuRezIslemButton.setIcon(new ImageIcon(rezImage));
        mainMenuRezIslemButton.setBounds(40, 150, 200, 175);
        mainMenuRezIslemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RezervasyonMenu rezervasyonMenu = new RezervasyonMenu(MainMenu.this);
				rezervasyonMenu.setLocationRelativeTo(MainMenu.this); 
				rezervasyonMenu.setVisible(true);
				setVisible(false);
			}
		});
        contentPane.add(mainMenuRezIslemButton);
		
        JButton mainMenuOdaIslemButton = new JButton("Oda İşlemleri");        
        ImageIcon odaIcon = new ImageIcon(MainMenu.class.getResource("/resources/odaIcon.png"));
        Image odaImage = odaIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        mainMenuOdaIslemButton.setIcon(new ImageIcon(odaImage));
        mainMenuOdaIslemButton.setBounds(300, 150, 200, 175);
        mainMenuOdaIslemButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		OdaMenu odaMenu = new OdaMenu(MainMenu.this);
        		odaMenu.setLocationRelativeTo(MainMenu.this); 
        		odaMenu.setVisible(true);
        		setVisible(false);
        	
        	}
        });
        contentPane.add(mainMenuOdaIslemButton);
		
        JButton mainMenuGenelIslemButton = new JButton("Genel İşlemler");
        ImageIcon generalIcon = new ImageIcon(MainMenu.class.getResource("/resources/generalIcon.png"));
		Image generalImage = generalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		mainMenuGenelIslemButton.setIcon(new ImageIcon(generalImage));
        mainMenuGenelIslemButton.setBounds(560, 150, 200, 175);
        mainMenuGenelIslemButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GenelIslemMenu genelIslemMenu = new GenelIslemMenu(MainMenu.this);
        		genelIslemMenu.setLocationRelativeTo(MainMenu.this); 
        		genelIslemMenu.setVisible(true);
        		setVisible(false);
        	}
        });
        contentPane.add(mainMenuGenelIslemButton);
		
		JButton mainMenuCikisButton = new JButton("Çıkış");
		mainMenuCikisButton.setBounds(653, 512, 107, 41);
		mainMenuCikisButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginMenu loginMenu = new LoginMenu();
				loginMenu.setLocationRelativeTo(MainMenu.this); 
				loginMenu.setVisible(true);
				dispose();
			}
		});
		contentPane.add(mainMenuCikisButton);
	}
	public String getKullaniciAdi() {
	    return kullaniciGoster.getText(); 
	}
}
