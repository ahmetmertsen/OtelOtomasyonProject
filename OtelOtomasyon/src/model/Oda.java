package model;

public class Oda {

    private String odaNumarasi;
    private String odaTipi;
    private double fiyat;
    private boolean doluluk;
    private boolean durum;

    public Oda(String odaNumarasi, String odaTipi, double fiyat, boolean doluluk, boolean durum) {
    	this(odaNumarasi,odaTipi,fiyat);
        this.doluluk = doluluk;
        this.durum = durum;
    }
    public Oda(String odaNumarasi, String odaTipi, double fiyat) {
        this.odaNumarasi = odaNumarasi;
        this.odaTipi = odaTipi;
        this.fiyat = fiyat;
        this.doluluk = false;
    }
    
    public Oda(String odaNumarasi) {
        this.odaNumarasi = odaNumarasi;
    }

    public String getOdaNumarasi() {
        return odaNumarasi;
    }

    public void setOdaNumarasi(String odaNumarasi) {
        this.odaNumarasi = odaNumarasi;
    }

    public String getOdaTipi() {
        return odaTipi;
    }

    public void setOdaTipi(String odaTipi) {
        this.odaTipi = odaTipi;
    }

    public double getFiyat() {
        return fiyat;
    }

    public void setFiyat(double fiyat) {
        this.fiyat = fiyat;
    }

    public boolean getDoluluk() {
        return doluluk;
    }

    public void setDoluluk(boolean doluluk) {
        this.doluluk = doluluk;
    }

    public boolean getDurum() {
        return durum;
    }

    public void setDurum(boolean durum) {
        this.durum = durum;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Oda that = (Oda) obj;
        return odaNumarasi.equals(that.odaNumarasi);
    }

    @Override
    public String toString() {
        return "Oda{" +
                "odaNumarasi='" + odaNumarasi + '\'' +
                ", odaTipi='" + odaTipi + '\'' +
                ", fiyat=" + fiyat +
                ", doluluk=" + doluluk +
                ", durum='" + durum + '\'' +
                '}';
    }
}
