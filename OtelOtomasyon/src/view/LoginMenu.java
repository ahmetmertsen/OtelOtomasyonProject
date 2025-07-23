package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.LinkedList;
import model.LoginAdmin;
import service.LoginAdminVeriOku;

public class LoginMenu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField kullaniciAdiTextField;
    private LinkedList<LoginAdmin> adminList;
    private JPasswordField sifreTextField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginMenu frame = new LoginMenu();
                frame.setLocationRelativeTo(null); 
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginMenu() {
        setTitle("Kullanıcı Girişi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 464, 461);
        setResizable(false);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        ImageIcon loginIcon = new ImageIcon(LoginMenu.class.getResource("/resources/login.png"));
        Image originalImage = loginIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(216, 160, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setBounds(156, 39, 167, 133);
        contentPane.add(imageLabel);
        
        JLabel kullaniciAdiText = new JLabel("Kullanıcı Adı:");
        kullaniciAdiText.setHorizontalAlignment(SwingConstants.RIGHT);
        kullaniciAdiText.setFont(new Font("Tahoma", Font.BOLD, 15));
        kullaniciAdiText.setBounds(56, 190, 112, 22);
        contentPane.add(kullaniciAdiText);

        kullaniciAdiTextField = new JTextField();
        kullaniciAdiTextField.setBounds(173, 190, 150, 22);
        contentPane.add(kullaniciAdiTextField);
        kullaniciAdiTextField.setColumns(10);

        JLabel sifreText = new JLabel("Şifre:");
        sifreText.setHorizontalAlignment(SwingConstants.RIGHT);
        sifreText.setFont(new Font("Tahoma", Font.BOLD, 15));
        sifreText.setBounds(69, 226, 99, 22);
        contentPane.add(sifreText);
        
        sifreTextField = new JPasswordField();
        sifreTextField.setBounds(173, 226, 150, 22);
        contentPane.add(sifreTextField);


        JButton girisButton = new JButton("Giriş");
        girisButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        girisButton.setBounds(231, 259, 92, 22);
        girisButton.addActionListener(e -> kontrolLogin());
        contentPane.add(girisButton);
        
        
        initializeAdmins();
    }

    private void initializeAdmins() {
        adminList = LoginAdminVeriOku.adminVerileriOku("src/data/loginAdminler.hot");
    }

    private void kontrolLogin() {
        String kullaniciAdi = kullaniciAdiTextField.getText();
        String sifre = new String(sifreTextField.getPassword());

        for (LoginAdmin admin : adminList) {
            if (admin.getKullaniciAdi().equals(kullaniciAdi) && admin.getSifre().equals(sifre)) {
            	LoginAdmin.setGirisYapanAdmin(admin);
                MainMenu mainMenu = new MainMenu(admin.getAdSoyad());
                mainMenu.setLocationRelativeTo(this);
                mainMenu.setVisible(true);
                this.dispose();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Hatalı kullanıcı adı veya şifre!", "Hata", JOptionPane.ERROR_MESSAGE);
    }
    
    protected void adminAdSoyad() {
    	
    }
    
}
