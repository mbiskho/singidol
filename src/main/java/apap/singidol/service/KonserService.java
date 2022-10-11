package apap.singidol.service;

import java.util.List;

import org.springframework.stereotype.Service;

import apap.singidol.dto.FrequentKonserDto;
import apap.singidol.model.KonserModel;

public interface KonserService {
    List<KonserModel> findAllKonser();
    KonserModel findById(Long id);
    KonserModel saveKonser(KonserModel konser);
    void updatePendapatan(Long pendapatan, Long idKonser);
    void updateKonser(KonserModel konser);
    List<KonserModel> findKonserByPendapatanAndIdol(Long pendapatan, Long idIdol);
    KonserModel findMostFrequent(Long idTipe);
}
