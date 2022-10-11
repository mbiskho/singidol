package apap.singidol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import apap.singidol.model.IdolModel;
import apap.singidol.model.TiketModel;
import apap.singidol.model.TipeModel;


@Repository
public interface TipeRepository extends JpaRepository<TipeModel, Long> {

    @Query(
        value = "SELECT * from tipe t where t.nama = :name ;",
        nativeQuery = true
    )
    public TipeModel findTipeByName(
        @Param("name") String name
    );
}
