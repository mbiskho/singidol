package apap.singidol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.singidol.model.IdolModel;

@Repository
public interface IdolRepository extends JpaRepository<IdolModel, Long> {
    
}
