package apap.singidol.repository;

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
}
