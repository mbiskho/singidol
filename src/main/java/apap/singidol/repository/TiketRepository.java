package apap.singidol.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import apap.singidol.model.TiketModel;

@Repository
public interface TiketRepository extends JpaRepository<TiketModel, Long> {

    @Modifying
    @Transactional
    @Query(
        value = "DELETE from tiket  WHERE tiket_id = :idTiket ;",
        nativeQuery = true
    )
    public void deleteById(
        @Param("idTiket") Long idTiket
    );
    
}
