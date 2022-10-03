package apap.singidol.service;

import apap.singidol.model.TiketModel;
import java.util.*;

public interface TiketService {
    List<TiketModel> findAllTiket();
    TiketModel findTiketById(Long id);
    void deleteTiketById(Long id);
    TiketModel saveTiket(TiketModel tiket);
    void deleteTiket(TiketModel tiket);
}
