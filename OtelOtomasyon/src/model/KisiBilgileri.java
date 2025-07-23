package model;


import model.LinkedList;

public class KisiBilgileri {

    private String tcKimlik;
    private String adSoyad;
    private String dogumTarihi;

    public KisiBilgileri(String tcKimlik, String adSoyad, String dogumTarihi) {
        this.tcKimlik = tcKimlik;
        this.adSoyad = adSoyad;
        this.dogumTarihi = dogumTarihi;
    }
    public KisiBilgileri() {

    }

    public String getTcKimlik() {
        return tcKimlik;
    }

    public void setTcKimlik(String tcKimlik) {
        this.tcKimlik = tcKimlik;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getDogumTarihi() {
        return dogumTarihi;
    }

    public void setDogumTarihi(String dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }

    @Override
    public String toString() {
        return "KisiBilgileri{" +
                "tcKimlik='" + tcKimlik + '\'' +
                ", adSoyad='" + adSoyad + '\'' +
                ", dogumTarihi='" + dogumTarihi + '\'' +
                '}';
    }


    public static LinkedList<KisiBilgileri> parseKisiBilgileri(String kisiBilgileriString) {
    	LinkedList<KisiBilgileri> kisiListesi = new LinkedList<>();

    	if (kisiBilgileriString == null || kisiBilgileriString.isEmpty()) {
    		return kisiListesi;
    	}

    	if (kisiBilgileriString.startsWith("[") && kisiBilgileriString.endsWith("]")) {
    		kisiBilgileriString = kisiBilgileriString.substring(1, kisiBilgileriString.length() - 1); // Köşeli parantezleri kaldır
    	}

    	String[] kisiArray = kisiBilgileriString.split("},");
    	for (String kisi : kisiArray) {
    		kisi = kisi.trim(); 
    		if (!kisi.endsWith("}")) {
    			kisi += "}"; 
    		}

    		try {
    			String tcKimlik = extractField(kisi, "tcKimlik");
    			String adSoyad = extractField(kisi, "adSoyad");
    			String dogumTarihi = extractField(kisi, "dogumTarihi");

    			if (!tcKimlik.isEmpty() && !adSoyad.isEmpty() && !dogumTarihi.isEmpty()) {
    				KisiBilgileri kisiBilgisi = new KisiBilgileri(tcKimlik, adSoyad, dogumTarihi);
    				kisiListesi.insertLast(kisiBilgisi);
    			}
    		} catch (Exception e) {
    			System.err.println("Kişi bilgisi ayrıştırılamadı: " + kisi);
    		}
    	}

    	return kisiListesi;
    }
    

    private static String extractField(String kisiBilgisi, String fieldName) {
        String field = fieldName + "='";
        int startIndex = kisiBilgisi.indexOf(field);
        if (startIndex == -1) {
            return ""; 
        }

        startIndex += field.length(); 
        int endIndex = kisiBilgisi.indexOf("'", startIndex);

        if (endIndex == -1) {
            return ""; 
        }

        return kisiBilgisi.substring(startIndex, endIndex);
    }
}
