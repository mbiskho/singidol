package apap.singidol.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.singidol.dto.PenampilanKonserDto;
import apap.singidol.model.IdolModel;
import apap.singidol.model.KonserModel;
import apap.singidol.model.PenampilanKonserModel;
import apap.singidol.service.IdolService;
import apap.singidol.service.KonserService;
import apap.singidol.service.PenampilanService;

@Controller
public class KonserController {

    @Autowired
    KonserService konserService;

    @Autowired
    IdolService idolService;

    @Autowired
    PenampilanService penampilanService;
    
    @RequestMapping("/konser")
    public String viewAllKonser(
        Model ctx
    ){
        List<KonserModel> konser = konserService.findAllKonser();
        System.out.println(konser.size());
        ctx.addAttribute("listKonser", konser);
        return "/konser/view";
    }

    
    @GetMapping("/konser/tambah")
    public String addKonserForm(
        Model ctx
    ){  

        // Defining Penampilan
        List<PenampilanKonserModel> listKonserPenampilan = new ArrayList<>();
        listKonserPenampilan.add(new PenampilanKonserModel());

        // Defining Konser
        KonserModel konser = new KonserModel();
        konser.setKonserIdol(listKonserPenampilan);
        ctx.addAttribute("konser", konser);

        // Giving List of All Idol
        List<IdolModel> listIdol = idolService.findAllIdol();
        ctx.addAttribute("listIdol", listIdol);

        return "/konser/add-form";
    }


    @PostMapping(value = "/konser/tambah", params={"addRowIdol"})
    public String addRowIdol(
        @ModelAttribute KonserModel konser,
        Model ctx
    ){
        if(konser.getKonserIdol() == null || konser.getKonserIdol().size() == 0){
            konser.setKonserIdol(new ArrayList<>());
        }

        // Giving List of All Idol
        List<IdolModel> listIdol = idolService.findAllIdol();
        ctx.addAttribute("listIdol", listIdol);

        PenampilanKonserModel newPenampilan = new PenampilanKonserModel();

        konser.getKonserIdol().add(newPenampilan);

        ctx.addAttribute("konser", konser);

        return "/konser/add-form";
    }

    @PostMapping(value = "/konser/tambah", params = {"deleteRowIdol"})
    public String deleteRowIdol(
        @ModelAttribute KonserModel konser,
        @RequestParam("deleteRowIdol") Integer row,
        Model ctx
    ){  
        final Integer rowId = Integer.valueOf(row);
        konser.getKonserIdol().remove(rowId.intValue());
        ctx.addAttribute("konser", konser);

        // Giving List of All Idol
        List<IdolModel> listIdol = idolService.findAllIdol();
        ctx.addAttribute("listIdol", listIdol);
        return "/konser/add-form";
    }

    @PostMapping(value = "/konser/tambah", params={"save"})
    public String addKonserSubmit(
        @ModelAttribute KonserModel konser,
        Model ctx
    ){  

        List<PenampilanKonserModel> listPenampilan = konser.getKonserIdol();

        konser.setKonserIdol(null);

        KonserModel savedKonser = konserService.saveKonser(konser);

        penampilanService.addIdolToKonser(konser, listPenampilan);

        if(savedKonser == null){
            return "/konser/add-error";
        }
        
        ctx.addAttribute("namaKonser", savedKonser.getNamaKonser());
        return "/konser/add-submit";
    }

  

    
    @RequestMapping("/konser/detail/{idKonser}")
    public String detailKonser(
        @PathVariable(value = "idKonser") String idKonser,
        Model ctx
    ){
        KonserModel konser = konserService.findById(Long.parseLong(idKonser));
        
        List<PenampilanKonserDto> penampilanKonser = penampilanService.findKonserIdols(konser.getId());


        for(PenampilanKonserDto x : penampilanKonser){
            System.out.println(x);
        }        

        ctx.addAttribute("konser", konser);
        ctx.addAttribute("waktu", konser.parsingWaktu());
        ctx.addAttribute("penampilanKonser", penampilanKonser);
        return "/konser/detail";
    }
    
}
