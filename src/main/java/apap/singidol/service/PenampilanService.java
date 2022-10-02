package apap.singidol.service;

import java.util.List;

import apap.singidol.dto.PenampilanKonserDto;
import apap.singidol.model.IdolModel;
import apap.singidol.model.KonserModel;
import apap.singidol.model.PenampilanKonserModel;

public interface PenampilanService {
    boolean addIdolToKonser(KonserModel konser,  List<PenampilanKonserModel> konserIdol);
    List<PenampilanKonserDto> findKonserIdols(Long idKonser);
    // void deleteIdolFromKonser();   
}
