package apap.singidol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.singidol.repository.TipeRepository;
import apap.singidol.model.TipeModel;
import apap.singidol.model.TiketModel;
import java.util.*;

@Service
public class TipeServiceImpl implements TipeService {

    @Autowired
    TipeRepository tipeRepository;
    
    @Override
    public List<TipeModel> findAllTipe(){
        return tipeRepository.findAll();
    }

    @Override
    public TipeModel findTipeById(Long id){
        return tipeRepository.getReferenceById(id);
    }
}
