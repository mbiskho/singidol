package apap.singidol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.singidol.model.IdolModel;
import apap.singidol.repository.IdolRepository;
import java.util.List;

public interface IdolService {
    IdolModel saveIdol(IdolModel idol);
    IdolModel findIdolById(Long id);
    List<IdolModel> findAllIdol(); 
}
