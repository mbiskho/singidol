package apap.singidol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.singidol.model.TiketModel;

@Repository
public interface TiketRepository extends JpaRepository<TiketModel, Long> {
    
}
