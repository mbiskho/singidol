package apap.singidol.service;

import apap.singidol.model.TipeModel;
import apap.singidol.model.TiketModel;
import java.util.*;

public interface TipeService {
    List<TipeModel> findAllTipe();
    TipeModel findTipeById(Long id);
    TipeModel findTipeByNama(String nama);
}
