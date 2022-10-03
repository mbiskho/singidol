package apap.singidol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.singidol.model.TiketModel;
import apap.singidol.repository.TiketRepository;

@Service
public class TiketServiceImpl implements TiketService {
    
    @Autowired
    TiketRepository tiketRepository;
    
    @Autowired
    TipeService tipeService;

    @Override
    public List<TiketModel> findAllTiket(){
        return tiketRepository.findAll();
    }

    @Override
    public TiketModel findTiketById(Long id){
        return tiketRepository.getReferenceById(id);
    }

    @Override
    public TiketModel saveTiket(TiketModel tiket){
        TiketModel savedTiket = tiketRepository.save(tiket);
        return savedTiket;
    }

    @Override
    public void deleteTiketById(Long id){
        tiketRepository.deleteById(id);
    }
    
    @Override
    public void deleteTiket(TiketModel tiket){
        tiketRepository.delete(tiket);
    }
}
