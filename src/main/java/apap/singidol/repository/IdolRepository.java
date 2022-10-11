package apap.singidol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import apap.singidol.model.IdolModel;
import apap.singidol.model.TiketModel;

@Repository
public interface IdolRepository extends JpaRepository<IdolModel, Long> {
}
