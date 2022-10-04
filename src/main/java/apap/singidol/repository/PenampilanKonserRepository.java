package apap.singidol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;

import apap.singidol.model.IdolModel;
import apap.singidol.model.PenampilanKonserModel;

@Repository
public interface PenampilanKonserRepository extends JpaRepository<PenampilanKonserModel, Long>  {
    
    @Query(
        value = "SELECT * from idol i JOIN penampilan_konser_model pkm ON i.idol_id = pkm.id_idol AND pkm.id_konser = :idKonser",
        nativeQuery = true
    )
    List<IdolModel> findIdolKonserByKonserId(
        @Param("idKonser") Long idKonser
    );

    @Transactional
    @Modifying
    @Query(
        value = "DELETE from penampilan_konser_model WHERE konser_id = :idKonser ;",
        nativeQuery =  true
    )
    void deleteKonserIdol(
        @Param("idKonser") Long idKonser
    );
}
