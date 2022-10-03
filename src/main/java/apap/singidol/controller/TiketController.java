package apap.singidol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import apap.singidol.service.KonserService;
import apap.singidol.service.TiketService;

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
import apap.singidol.model.TiketModel;
import apap.singidol.model.TipeModel;
import apap.singidol.service.IdolService;
import apap.singidol.service.KonserService;
import apap.singidol.service.PenampilanService;

@Controller
public class TiketController {

    @Autowired
    TiketService tiketService;

    @Autowired
    KonserService konserService;

    @RequestMapping("/tiket")
    public String viewAllTiket(
        Model ctx
    ){
        List<TiketModel> tiket = tiketService.findAllTiket();
        System.out.println(tiket.size());
        ctx.addAttribute("listTiket", tiket);
        return "/tiket/view";
    }

    @GetMapping("/tiket/tambah")
    public String addTiketForm(
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
        // List<IdolModel> listIdol = idolService.findAllIdol();
        // ctx.addAttribute("listIdol", listIdol);

        return "/tiket/add-form";
    }



    @PostMapping(value = "/tiket/tambah", params={"save"})
    public String addTiketSubmit(
        @ModelAttribute KonserModel konser,
        Model ctx
    ){  

        List<PenampilanKonserModel> listPenampilan = konser.getKonserIdol();

        // konser.setKonserIdol(null);

        // KonserModel savedKonser = konserService.saveKonser(konser);

        // penampilanService.addIdolToKonser(konser, listPenampilan);

        // if(savedKonser == null){
        //     return "/konser/add-error";
        // }
        
        // ctx.addAttribute("namaKonser", savedKonser.getNamaKonser());
        return "/tiket/add-submit";
    }


    @RequestMapping("/tiket/delete/{idTiket}")
    public String deleteKonser(
        @PathVariable(value = "idTiket") String idTiket,
        Model ctx
    ){
        TiketModel tiket = tiketService.findTiketById(Long.parseLong(idTiket));
        KonserModel konser = tiket.getKonser();

        tiketService.deleteTiket(tiket);

        ctx.addAttribute("tiket", tiket);
        ctx.addAttribute("konser", konser);
        return "/tiket/delete";
    }
    
    @RequestMapping("/tiket/detail/{idTiket}")
    public String detailKonser(
        @PathVariable(value = "idTiket") String idTiket,
        Model ctx
    ){
        TiketModel tiket = tiketService.findTiketById(Long.parseLong(idTiket));
        KonserModel konser = tiket.getKonser();
        TipeModel tipe = tiket.getTipe();

        System.out.println(konser);
        System.out.println(tipe);
        ctx.addAttribute("tiket", tiket);
        ctx.addAttribute("konser", konser);
        ctx.addAttribute("tipe", tipe);
        ctx.addAttribute("waktu", konser.parsingWaktu());
        ctx.addAttribute("tanggalPembelian", tiket.parsingTanggalPembelian());
        return "/tiket/detail";
    }

    
    
}
