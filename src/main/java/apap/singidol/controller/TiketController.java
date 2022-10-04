package apap.singidol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import apap.singidol.service.KonserService;
import apap.singidol.service.TiketService;
import apap.singidol.service.TipeService;

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

    @Autowired
    TipeService tipeService;

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

        // Defining Konser
        List<KonserModel> listKonser = konserService.findAllKonser();
        ctx.addAttribute("listKonser", listKonser);

        System.out.println(listKonser.size());

        // Defining Tipe
        List<TipeModel> listTipe  = tipeService.findAllTipe();
        ctx.addAttribute("listTipe", listTipe);

        // Defining new Tiket
        TiketModel tiket = new TiketModel();
        ctx.addAttribute("tiket", tiket);

        return "/tiket/add-form";
    }



    @PostMapping(value = "/tiket/tambah", params={"save"})
    public String addTiketSubmit(
        @ModelAttribute TiketModel tiket,
        Model ctx
    ){  


        System.out.println(tiket);

    
        TipeModel tipe  = tipeService.findTipeById(tiket.getTipe().getId());
        KonserModel konser = konserService.findById(tiket.getKonser().getId());


        // Assigning to object to tiket
        tiket.setTipe(tipe);
        tiket.setKonser(konser);

        
        // Generating nomor tiket
        tiket.generateNomorTiket();


        // Updating Konser Pendapatan
        Long currentPendapatan = konser.getTotalPendapatan() + tipe.getHarga();
        konserService.updatePendapatan(currentPendapatan, konser.getId());


        System.out.println(tiket);

        TiketModel savedTiket = tiketService.saveTiket(tiket);

        if(savedTiket == null){
            return "/tiket/add-error";
        }

        ctx.addAttribute("nomorTiket", tiket.getNomorTiket());
        ctx.addAttribute("namaKonser", konser.getNamaKonser());
        return "/tiket/add-submit";
    }


    @RequestMapping("/tiket/delete/{idTiket}")
    public String deleteKonser(
        @PathVariable(value = "idTiket") String idTiket,
        Model ctx
    ){
        TiketModel tiket = tiketService.findTiketById(Long.parseLong(idTiket));
        KonserModel konser = tiket.getKonser();
        TipeModel tipe = tiket.getTipe();

        // Recalculate Pendapatan
        Long adjustedPendapatan = konser.getTotalPendapatan() - tipe.getHarga();
        System.out.println(konser.getTotalPendapatan());
        System.out.println(adjustedPendapatan);
        konserService.updatePendapatan(adjustedPendapatan, konser.getId());

        tiketService.deleteTiketById(tiket.getId());

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
