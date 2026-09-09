package com.assessment.datapribadi.controller;

import com.assessment.datapribadi.model.DataPribadi;
import com.assessment.datapribadi.repository.DataPribadiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/data-pribadi")
@CrossOrigin(origins = "*") 
public class DataPribadiController {

    @Autowired
    private DataPribadiRepository repository;

    // Get All atau Search
    @GetMapping
    public List<DataPribadi> getAllData(@RequestParam(required = false) Long nik, 
                                        @RequestParam(required = false) String nama) {
        if (nik != null || (nama != null && !nama.isEmpty())) {
            return repository.searchData(nik, nama);
        }
        return repository.findAll();
    }

    // Get Detail by NIK
    @GetMapping("/{nik}")
    public ResponseEntity<DataPribadi> getByNik(@PathVariable Long nik) {
        // Menggunakan Objects.requireNonNull untuk menjamin ke compiler bahwa nik tidak null
        return repository.findById(Objects.requireNonNull(nik))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create Data Baru
    @PostMapping
    public ResponseEntity<?> createData(@RequestBody DataPribadi data) {
        Objects.requireNonNull(data);

        if (repository.existsById(Objects.requireNonNull(data.getNik()))) {
            return ResponseEntity.badRequest().body("NIK sudah terdaftar!");
        }
        return ResponseEntity.ok(repository.save(data));
    }

    // Update Data
    @PutMapping("/{nik}")
    public ResponseEntity<?> updateData(@PathVariable Long nik, @RequestBody DataPribadi dataDetails) {
        Objects.requireNonNull(dataDetails);
        return repository.findById(Objects.requireNonNull(nik)).map(data -> {
            data.setNamaLengkap(dataDetails.getNamaLengkap());
            data.setJenisKelamin(dataDetails.getJenisKelamin());
            data.setTanggalLahir(dataDetails.getTanggalLahir());
            data.setAlamat(dataDetails.getAlamat());
            data.setNegara(dataDetails.getNegara());
            return ResponseEntity.ok(repository.save(data));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Delete Data
    @DeleteMapping("/{nik}")
    public ResponseEntity<?> deleteData(@PathVariable Long nik) {
        return repository.findById(Objects.requireNonNull(nik)).map(data -> {
            repository.delete(Objects.requireNonNull(data));
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}