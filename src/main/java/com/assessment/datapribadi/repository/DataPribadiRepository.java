package com.assessment.datapribadi.repository;

import com.assessment.datapribadi.model.DataPribadi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataPribadiRepository extends JpaRepository<DataPribadi, Long> {

    // Fitur pencarian fleksibel (bisa isi NIK saja, Nama saja, atau keduanya)
    @Query("SELECT d FROM DataPribadi d WHERE " +
           "(:nik IS NULL OR d.nik = :nik) AND " +
           "(:nama IS NULL OR LOWER(d.namaLengkap) LIKE LOWER(CONCAT('%', :nama, '%')))")
    List<DataPribadi> searchData(@Param("nik") Long nik, @Param("nama") String nama);
}