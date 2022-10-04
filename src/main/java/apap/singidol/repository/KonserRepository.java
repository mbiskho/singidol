package apap.singidol.repository;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apap.singidol.model.KonserModel;

import org.springframework.data.repository.query.Param;


@Repository
public interface KonserRepository extends JpaRepository<KonserModel, Long> {

    @Modifying
    @Transactional
    @Query(
        value = "UPDATE konser SET total_pendapatan = :pendapatan WHERE konser_id = :idKonser ;",
        nativeQuery = true
    )
    public void updatePendapatanKonser(
        @Param("pendapatan") Long pendapatan,
        @Param("idKonser") Long idKonser
    );



    @Modifying
    @Transactional
    @Query(
        value = "UPDATE konser SET nama_konser = :namaKonser WHERE konser_id = :idKonser ;",
        nativeQuery = true
    )
    public void updateNamaKonser(
        @Param("namaKonser") String namaKonser,
        @Param("idKonser") Long idKonser
    );


    @Modifying
    @Transactional
    @Query(
        value = "UPDATE konser SET tempat = :tempat WHERE konser_id = :idKonser ;",
        nativeQuery = true
    )
    public void updateTempatKonser(
        @Param("tempat") String tempat,
        @Param("idKonser") Long idKonser
    );

    @Modifying
    @Transactional
    @Query(
        value = "UPDATE konser SET waktu = :waktu WHERE konser_id = :idKonser ;",
        nativeQuery = true
    )
    public void updateWaktuKonser(
        @Param("waktu") LocalDateTime waktu,
        @Param("idKonser") Long idKonser
    );

}
