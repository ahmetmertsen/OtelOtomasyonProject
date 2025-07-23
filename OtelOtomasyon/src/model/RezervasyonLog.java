package model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RezervasyonLog {
    private LoginAdmin loginAdmin;
    private Rezervasyon rezervasyon; 
    private String yapilanIslem;  
    private String saatVeTarih;  

    public RezervasyonLog(LoginAdmin loginAdmin, Rezervasyon rezervasyon, String yapilanIslem) {
        this.loginAdmin = loginAdmin;
        this.rezervasyon = rezervasyon;
        this.yapilanIslem = yapilanIslem;
        this.saatVeTarih = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy"));
    }
    
    

    public LoginAdmin getLoginAdmin() {
        return loginAdmin;
    }

    public void setLoginAdmin(LoginAdmin loginAdmin) {
        this.loginAdmin = loginAdmin;
    }

    public Rezervasyon getRezervasyon() {
        return rezervasyon;
    }

    public void setRezervasyon(Rezervasyon rezervasyon) {
        this.rezervasyon = rezervasyon;
    }

    public String getYapilanIslem() {
        return yapilanIslem;
    }

    public void setYapilanIslem(String yapilanIslem) {
        this.yapilanIslem = yapilanIslem;
    }

    public String getSaatVeTarih() {
        return saatVeTarih;
    }

    @Override
    public String toString() {
        return loginAdmin.getAdSoyad() + ";" +
               rezervasyon.getRezervasyonId() + ";" +
               yapilanIslem + ";" +
               saatVeTarih + ";";
    }
}
