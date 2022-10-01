package apap.singidol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import apap.singidol.model.KonserModel;
import apap.singidol.repository.KonserRepository;

@Service
public class KonserServiceImpl implements KonserService {
  @Autowired
  KonserRepository konserRepository;


    @Override
    public List<KonserModel> findAllKonser(){
        List<KonserModel> lst = konserRepository.findAll();
        return lst;
    }

    @Override
    public KonserModel saveKonser(KonserModel konser){
        KonserModel savedKonser = konserRepository.save(konser);
        return savedKonser;
    }


}