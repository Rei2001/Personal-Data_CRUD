package com.assessment.datapribadi.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "data_pribadi")
public class DataPribadi {

    @Id
    @Column(name = "nik", nullable = false, unique = true)
    private Long nik; // Menggunakan Long karena NIK berupa numeric panjang

    @Column(name = "nama_lengkap", nullable = false)
    private String namaLengkap;

    @Column(name = "jenis_kelamin")
    private String jenisKelamin;

    @Column(name = "tanggal_lahir")
    private LocalDate tanggalLahir;

    @Column(name = "alamat", columnDefinition = "TEXT")
    private String alamat;

    @Column(name = "negara")
    private String negara;

    @Transient // Tidak akan dibuatkan kolom di DB
    private Integer umur;

    // Getter untuk Umur (Hitung otomatis)
    public Integer getUmur() {
        if (this.tanggalLahir != null) {
            return Period.between(this.tanggalLahir, LocalDate.now()).getYears();
        }
        return 0;
    }

    // Boilerplate Code: Getter & Setter Manual atau gunakan Lombok (@Data)
    public Long getNik() { return nik; }
    public void setNik(Long nik) { this.nik = nik; }

    public String getNamaLengkap() { return namaLengkap; }
    public void setNamaLengkap(String namaLengkap) { this.namaLengkap = namaLengkap; }

    public String getJenisKelamin() { return jenisKelamin; }
    public void setJenisKelamin(String jenisKelamin) { this.jenisKelamin = jenisKelamin; }

    public LocalDate getTanggalLahir() { return tanggalLahir; }
    public void setTanggalLahir(LocalDate tanggalLahir) { this.tanggalLahir = tanggalLahir; }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }

    public String getNegara() { return negara; }
    public void setNegara(String negara) { this.negara = negara; }
}