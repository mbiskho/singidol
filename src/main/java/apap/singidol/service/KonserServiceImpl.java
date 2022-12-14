package apap.singidol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import apap.singidol.dto.FrequentKonserDto;
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
    public KonserModel findById(Long id){
        KonserModel konser = konserRepository.getReferenceById(id);
        return konser;
    }

    @Override
    public KonserModel saveKonser(KonserModel konser){
        KonserModel savedKonser = konserRepository.save(konser);
        return savedKonser;
    }

    @Override
    public void updatePendapatan(
        Long pendapatan,
        Long idKonser
    ){
        konserRepository.updatePendapatanKonser(pendapatan, idKonser);
    }

    @Override
    public void updateKonser(KonserModel konser){
        konserRepository.updateNamaKonser(konser.getNamaKonser(), konser.getId());
        konserRepository.updateTempatKonser(konser.getTempat(), konser.getId());
        konserRepository.updateWaktuKonser(konser.getWaktu(), konser.getId());
    }

    @Override
    public List<KonserModel> findKonserByPendapatanAndIdol(Long pendapatan, Long idIdol){
        List<KonserModel> listKonser = konserRepository.searchKonserByPendapatanAndIdol(idIdol, pendapatan);

        return listKonser;
    }

    @Override
    public KonserModel findMostFrequent(Long idTipe){
        
        KonserModel konser = konserRepository.findMostFrequentConcertByTipeId(idTipe);
        
        return konser;
    }

}
