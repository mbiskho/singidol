package apap.singidol.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apap.singidol.dto.FrequentKonserDto;
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

    @Query(
        value = "SELECT k.konser_id, k.nama_konser, k.tempat, k.total_pendapatan, k.waktu from konser k JOIN penampilan_konser_model pkm ON k.konser_id = pkm.id_konser AND pkm.id_idol=:idIdol AND k.total_pendapatan >= :pendapatan ;",
        nativeQuery = true
    )
    public List<KonserModel> searchKonserByPendapatanAndIdol(
        @Param("idIdol") Long idIdol,
        @Param("pendapatan") Long pendapatan
    );

    @Query(
        value = "SELECT k.konser_id,k.nama_konser,k.tempat,k.total_pendapatan,k.waktu from konser k JOIN tiket t ON t.konser_id = k.konser_id AND t.tipe_id =:idTipe GROUP BY k.konser_id ORDER BY k.nama_konser ASC LIMIT 1 ;",
        nativeQuery = true
    )
    public KonserModel findMostFrequentConcertByTipeId(
        @Param("idTipe") Long idTipe
    );
    
}
