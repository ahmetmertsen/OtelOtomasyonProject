package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OdaLog {
	private LoginAdmin loginAdmin;
	private Oda oda;
	private String yapilanIslem;  
    private String saatVeTarih; 
    
    public OdaLog(LoginAdmin loginAdmin, Oda oda, String yapilanIslem) {
        this.loginAdmin = loginAdmin;
        this.oda = oda;
        this.yapilanIslem = yapilanIslem;
        this.saatVeTarih = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy"));
    }
    
    public LoginAdmin getLoginAdmin() {
        return loginAdmin;
    }

    public void setLoginAdmin(LoginAdmin loginAdmin) {
        this.loginAdmin = loginAdmin;
    }

    public Oda getOda() {
        return oda;
    }

    public void setOda(Oda oda) {
        this.oda = oda;
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
               oda.getOdaNumarasi() + ";" +
               yapilanIslem + ";" +
               saatVeTarih + ";";
    }

}
