package apap.singidol.service;

import java.util.List;

import org.springframework.stereotype.Service;
import apap.singidol.model.KonserModel;

public interface KonserService {
    List<KonserModel> findAllKonser();
    // KonserModel findById(Long id);
    KonserModel saveKonser(KonserModel konser);
    // KonserModel deleteKonserById(Long id);
}
