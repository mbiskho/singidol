package apap.singidol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.singidol.model.IdolModel;
import apap.singidol.repository.IdolRepository;

@Service
public class IdolServiceImpl implements IdolService {
    @Autowired
    IdolRepository idolRepository;

    @Override
    public IdolModel saveIdol(IdolModel idol){
        IdolModel savedIdol = idolRepository.save(idol);
        return savedIdol;
    }

    @Override
    public IdolModel findIdolById(Long id){
        IdolModel idol = idolRepository.getReferenceById(id);

        return idol;
    }

    @Override
    public List<IdolModel> findAllIdol(){
        List<IdolModel> lstIdol = idolRepository.findAll();
        return lstIdol;
    }
}
