package apap.singidol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.singidol.model.TipeModel;

@Repository
public interface TipeRepository extends JpaRepository<TipeModel, Long> {
    
}
