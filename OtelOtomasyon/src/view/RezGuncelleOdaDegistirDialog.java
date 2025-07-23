package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.LinkedList;
import model.Oda;
import service.OdaVeriKaydet;
import service.OdaVeriOku;

public class RezGuncelleOdaDegistirDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField newFiyatTextField;
	private JTextField odaTipiTextField;
	private JTextField odaNoTextField;
	private JTextField fiyatTextField;
	private JComboBox newOdaNoComboBox;
	private JComboBox newOdaTipiComboBox;
	private RezervasyonGuncelleDialog rezervasyonGuncelleDialog;
	

	public RezGuncelleOdaDegistirDialog(RezervasyonGuncelleDialog rezervasyonGuncelleDialog) {
		this.rezervasyonGuncelleDialog = rezervasyonGuncelleDialog;
		setTitle("Oda Değiştir");
		setBounds(100, 100, 507, 200);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel mevcutOdaText = new JLabel("Mevcut Oda:");
		mevcutOdaText.setFont(new Font("Tahoma", Font.BOLD, 12));
		mevcutOdaText.setBounds(10, 10, 96, 21);
		contentPanel.add(mevcutOdaText);
		
		JLabel odaTipiText = new JLabel("Oda Tipi:");
		odaTipiText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		odaTipiText.setBounds(10, 29, 65, 20);
		contentPanel.add(odaTipiText);

		odaTipiTextField = new JTextField();
		odaTipiTextField.setColumns(10);
		odaTipiTextField.setBounds(101, 29, 96, 21);
		odaTipiTextField.setEditable(false);
		contentPanel.add(odaTipiTextField);

		JLabel odaNoText = new JLabel("Oda Numarası:");
		odaNoText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		odaNoText.setBounds(10, 59, 86, 20);
		contentPanel.add(odaNoText);

		odaNoTextField = new JTextField();
		odaNoTextField.setColumns(10);
		odaNoTextField.setBounds(101, 61, 45, 20);
		odaNoTextField.setEditable(false);
		contentPanel.add(odaNoTextField);
	
		JLabel fiyatText = new JLabel("Fiyat:");
		fiyatText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fiyatText.setBounds(10, 89, 86, 20);
		contentPanel.add(fiyatText);

		fiyatTextField = new JTextField();
		fiyatTextField.setColumns(10);
		fiyatTextField.setBounds(101, 89, 96, 20);
		fiyatTextField.setEditable(false);
		contentPanel.add(fiyatTextField);
		
		
		JLabel newOdaText = new JLabel("Yeni Oda:");
		newOdaText.setFont(new Font("Tahoma", Font.BOLD, 12));
		newOdaText.setBounds(260, 10, 96, 21);
		contentPanel.add(newOdaText);
		
		JLabel newOdaTipiText = new JLabel("Oda Tipi:");
		newOdaTipiText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		newOdaTipiText.setBounds(260, 29, 81, 20);
		contentPanel.add(newOdaTipiText);

		newOdaTipiComboBox = new JComboBox();
		newOdaTipiComboBox.setModel(new DefaultComboBoxModel(new String[] {"1 Kişilik", "2 Kişilik", "3 Kişilik"}));
		newOdaTipiComboBox.setBounds(356, 30, 96, 20);
		contentPanel.add(newOdaTipiComboBox);

		JLabel newOdaNoText = new JLabel("Oda Numarası:");
		newOdaNoText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		newOdaNoText.setBounds(260, 60, 86, 20);
		
		contentPanel.add(newOdaNoText);

		newOdaNoComboBox = new JComboBox();
		newOdaNoComboBox.setModel(new DefaultComboBoxModel<>());
		newOdaNoComboBox.setBounds(356, 60, 65, 21);
		contentPanel.add(newOdaNoComboBox);

		JLabel newFiyatText = new JLabel("Fiyat:");
		newFiyatText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		newFiyatText.setBounds(260, 90, 86, 20);
		contentPanel.add(newFiyatText);

		newFiyatTextField = new JTextField();
		newFiyatTextField.setColumns(10);
		newFiyatTextField.setBounds(356, 91, 96, 20);
		newFiyatTextField.setEditable(false);
		contentPanel.add(newFiyatTextField);
		odalariDoldur();
		
		

			
			
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton degistirButton = new JButton("Değiştir");
			degistirButton.setActionCommand("Değiştir");
			buttonPane.add(degistirButton);
			degistirButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		String yeniOdaTipi = (String) newOdaTipiComboBox.getSelectedItem();
	                String yeniOdaNo = (String) newOdaNoComboBox.getSelectedItem();
	                String yeniFiyat = newFiyatTextField.getText();
	                
	                
	                String mevcutOdaNo = odaNoTextField.getText();
	                odaDurumunuGuncelle(mevcutOdaNo, false);
	                odaDurumunuGuncelle(yeniOdaNo, true);
	                
	                rezervasyonGuncelleDialog.setOdaBilgileri(yeniOdaTipi, yeniOdaNo, yeniFiyat);
	                
	                dispose();
	        		
	        	}
	        });
			getRootPane().setDefaultButton(degistirButton);
		}
		{
			JButton iptalButton = new JButton("İptal");
			iptalButton.setActionCommand("İptal");
			iptalButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {               
	                dispose();        		
	        	}
	        });
			buttonPane.add(iptalButton);
		}
	}
	
	
	
	public void setMevcutOdaBilgileri(String odaTipi, String odaNo, String fiyat) {
	    odaTipiTextField.setText(odaTipi);
	    odaNoTextField.setText(odaNo);
	    fiyatTextField.setText(fiyat);
	}
	
	protected void odalariDoldur() {
	    LinkedList<Oda> odalar = OdaVeriOku.odalariOku("src/data/odalar.hot"); 
	    LinkedList<Oda> bosOdalar = OdaVeriOku.getBosOdalar(odalar);

	    DefaultComboBoxModel<String> odaTipiModel = new DefaultComboBoxModel<>();
	    bosOdalar.stream()
	             .map(Oda::getOdaTipi)
	             .distinct()
	             .forEach(odaTipiModel::addElement);
	    newOdaTipiComboBox.setModel(odaTipiModel);

	    if (newOdaTipiComboBox.getItemCount() > 0) {
	        String varsayilanOdaTipi = (String) newOdaTipiComboBox.getSelectedItem();
	        updateOdaTipineGoreOdaNumaralari(varsayilanOdaTipi, bosOdalar);
	    }

	    newOdaTipiComboBox.addActionListener(e -> {
	        String secilenOdaTipi = (String) newOdaTipiComboBox.getSelectedItem();
	        updateOdaTipineGoreOdaNumaralari(secilenOdaTipi, bosOdalar);
	    });
	}

	protected void updateOdaTipineGoreOdaNumaralari(String secilenOdaTipi, LinkedList<Oda> bosOdalar) {
	    DefaultComboBoxModel<String> odaNumarasiModel = new DefaultComboBoxModel<>();

	    bosOdalar.stream()
	             .filter(oda -> oda.getOdaTipi().equals(secilenOdaTipi))
	             .map(Oda::getOdaNumarasi)
	             .forEach(odaNumarasiModel::addElement);

	    newOdaNoComboBox.setModel(odaNumarasiModel);

	    bosOdalar.stream()
	             .filter(oda -> oda.getOdaTipi().equals(secilenOdaTipi))
	             .findFirst()
	             .ifPresent(this::updateOdaFiyat);
	}

	protected void updateOdaFiyat(Oda oda) {
	    newFiyatTextField.setText(String.valueOf(oda.getFiyat()));
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


