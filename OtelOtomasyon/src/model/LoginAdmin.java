package model;

public class LoginAdmin {
	private static LoginAdmin girisYapanAdmin;
    private String kullaniciAdi;
    private String sifre;
    private String adSoyad;

    public LoginAdmin(String kullaniciAdi, String sifre, String adSoyad) {
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
        this.adSoyad = adSoyad;
    }
    
    public static void setGirisYapanAdmin(LoginAdmin admin) {
    	girisYapanAdmin = admin;
    }

    public static LoginAdmin getGirisYapanAdmin() {
        return girisYapanAdmin;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    @Override
    public String toString() {
        return "Ad Soyad: " + adSoyad + ", Kullanıcı Adı: " + kullaniciAdi;
    }
}
