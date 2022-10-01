package apap.singidol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apap.singidol.model.KonserModel;

import org.springframework.data.repository.query.Param;


@Repository
public interface KonserRepository extends JpaRepository<KonserModel, Long> {
    
}
