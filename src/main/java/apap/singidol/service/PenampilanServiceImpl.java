package apap.singidol.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.singidol.dto.PenampilanKonserDto;
import apap.singidol.model.IdolModel;
import apap.singidol.model.KonserModel;
import apap.singidol.model.PenampilanKonserModel;
import apap.singidol.repository.KonserRepository;
import apap.singidol.repository.PenampilanKonserRepository;

@Service
public class PenampilanServiceImpl implements PenampilanService{

    @Autowired
    IdolService idolService;

    @Autowired
    PenampilanKonserRepository penampilanKonserRepository;

    @Autowired
    KonserRepository konserRepository;

    @Override
    public boolean addIdolToKonser(
        KonserModel konser,
        List<PenampilanKonserModel> konserIdol
    ){
        List<IdolModel> listIdol = idolService.findAllIdol();

        for(PenampilanKonserModel penampilan : konserIdol){
            penampilan.setKonser(konser);
            penampilan.setIdKonser(konser.getId());
            for(IdolModel x : listIdol){
                if(x.getId() == penampilan.idIdol){
                    penampilan.setIdol(x);
                }
            }
        }
        List<PenampilanKonserModel> saved = penampilanKonserRepository.saveAll(konserIdol);

        if(saved == null || saved.size() == 0)
            return false;
        return true;
    }

    @Override
    public List<PenampilanKonserDto> findKonserIdols(Long idKonser){
        KonserModel konser = konserRepository.getReferenceById(idKonser);
        List<IdolModel> listIdol  = idolService.findAllIdol();
        List<PenampilanKonserModel> listPenampilan = penampilanKonserRepository.findAll();

        List<PenampilanKonserDto> penampilanKonserList = new ArrayList<>();

        for(IdolModel idol : listIdol){
            for(PenampilanKonserModel penampilan : listPenampilan){
                if(konser.getId() == penampilan.getIdKonser() && penampilan.getIdIdol() == idol.getId()){
                    penampilanKonserList.add(
                        new PenampilanKonserDto(
                            konser.getId(),
                            penampilan.getId(),
                            idol.getId(),
                            penampilan.getJamMulaiTampil(),
                            idol.getNamaIdol(),
                            idol.getTanggalDebut(),
                            idol.getJumlahAnggota()
                        )
                    );
                }
            }
        }


        return penampilanKonserList;
    }


    
    public void savePenampilan(PenampilanKonserModel penampilan){
        penampilanKonserRepository.save(penampilan);
    }
}
