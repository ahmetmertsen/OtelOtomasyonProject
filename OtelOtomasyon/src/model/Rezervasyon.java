package model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import model.LinkedList;

public class Rezervasyon {

    private static int idCounter = 1; 
    private int rezervasyonId;        
    private int kisiSayisi;
    private Oda oda;                  
    private Date girisTarihi;
    private Date cikisTarihi;
    private LinkedList<KisiBilgileri> kisiBilgileri;
    {kisiBilgileri = new LinkedList<>();}

    private static final int MAX_KISI_SAYISI = 5; 
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
    
    public Rezervasyon() {
        this.rezervasyonId = idCounter++; 
        this.kisiSayisi = 0; 
        this.oda = null; 
        this.girisTarihi = null; 
        this.cikisTarihi = null; 
    }
    
    public Rezervasyon(int kisiSayisi, Oda oda, Date girisTarihi, Date cikisTarihi, LinkedList<KisiBilgileri> kisiBilgileri) {
        this.rezervasyonId = idCounter++; 
        this.kisiSayisi = kisiSayisi;
        this.oda = oda; 
        this.girisTarihi = girisTarihi;
        this.cikisTarihi = cikisTarihi;
        setKisiBilgileri(kisiBilgileri);
    }
    
    public Rezervasyon(int rezervasyonId, int kisiSayisi, Oda oda, Date girisTarihi, Date cikisTarihi, LinkedList<KisiBilgileri> kisiBilgileri) {
    	this(kisiSayisi, oda, girisTarihi, cikisTarihi , kisiBilgileri);
    	this.rezervasyonId = rezervasyonId;
    }
    
    public Rezervasyon(int rezervasyonId) {
        this.rezervasyonId = rezervasyonId;
    }
    
    

    
    public int getRezervasyonId() {
        return rezervasyonId;
    }
    public void setRezervasyonId(int rezervasyonId) {
        this.rezervasyonId = rezervasyonId;
    }

    public int getKisiSayisi() {
        return kisiBilgileri.size();
    }

    public void setKisiSayisi(int kisiSayisi) {
        if (kisiSayisi > MAX_KISI_SAYISI) {
            throw new IllegalArgumentException("Kişi sayısı en fazla " + MAX_KISI_SAYISI + " olabilir.");
        }
        this.kisiSayisi = kisiSayisi;
    }

    public Oda getOda() {
        return oda; 
    }

    public void setOda(Oda oda) {
        this.oda = oda; 
    }
    
    public String getOdaNumarasi() {
        return oda != null ? oda.getOdaNumarasi() : null; 
    }
    
    public void setOdaNumarasi(String odaNumarasi) {
        if (oda != null) {
            oda.setOdaNumarasi(odaNumarasi);
        } else {
            throw new IllegalStateException("Oda bilgisi atanmadı.");
        }
    }
    
    public String getOdaTipi() {
        return oda != null ? oda.getOdaTipi() : null; 
    }
    
    public void setOdaTipi(String odaTipi) {
        if (oda != null) {
            oda.setOdaTipi(odaTipi);
        } else {
            throw new IllegalStateException("Oda bilgisi atanmadı.");
        }
    }
    
    public double getFiyat() {
        return oda != null ? oda.getFiyat() : 0.0; 
    }
    
    public void setFiyat(double fiyat) {
        if (oda != null) {
            oda.setFiyat(fiyat);
        }
    }

    public Date getGirisTarihi() {
        return girisTarihi;
    }

    public void setGirisTarihi(Date girisTarihi) {
        this.girisTarihi = girisTarihi;
    }

    public Date getCikisTarihi() {
        return cikisTarihi;
    }

    public void setCikisTarihi(Date cikisTarihi) {
        this.cikisTarihi = cikisTarihi;
    }

    public LinkedList<KisiBilgileri> getKisiBilgileri() {
        return kisiBilgileri;
    }

    public void setKisiBilgileri(LinkedList<KisiBilgileri> kisiBilgileri) {
        if (kisiBilgileri.size() > MAX_KISI_SAYISI) {
            throw new IllegalArgumentException("Kişi bilgisi en fazla " + MAX_KISI_SAYISI + " kişi için girilebilir.");
        }
        this.kisiBilgileri = kisiBilgileri;
        setKisiSayisi(kisiBilgileri.size());
    }

    public void addKisiBilgisi(KisiBilgileri kisi) {
        if (kisiBilgileri == null) {
            kisiBilgileri = new LinkedList<>();
        }
        kisiBilgileri.insertLast(kisi);  
    }
    

    public String getFormattedGirisTarihi() {
        return girisTarihi != null ? FORMATTER.format(girisTarihi) : null;
    }

    public String getFormattedCikisTarihi() {
        return cikisTarihi != null ? FORMATTER.format(cikisTarihi) : null;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Rezervasyon that = (Rezervasyon) obj;
        return rezervasyonId == that.rezervasyonId;
    }

    @Override
    public String toString() {
        return "Rezervasyon{" +
                "rezervasyonId=" + rezervasyonId +
                ", kisiSayisi=" + kisiSayisi +
                ", oda=" + oda + 
                ", girisTarihi=" + getFormattedGirisTarihi() +
                ", cikisTarihi=" + getFormattedCikisTarihi() +
                ", kisiBilgileri=" + kisiBilgileri +
                '}';
    }
}
