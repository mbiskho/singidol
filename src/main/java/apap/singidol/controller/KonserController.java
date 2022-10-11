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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import apap.singidol.dto.PenampilanKonserDto;
import apap.singidol.dto.SearchKonserDto;
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
 
        ctx.addAttribute("konser", konser);
        ctx.addAttribute("waktu", konser.parsingWaktu());
        ctx.addAttribute("penampilanKonser", penampilanKonser);
        return "/konser/detail";
    }


    @GetMapping("/konser/ubah/{idKonser}")
    public String ubahKonserForm(
        @PathVariable(value = "idKonser") String idKonser,
        Model ctx
    ){
        KonserModel konser = konserService.findById(Long.parseLong(idKonser));

        ctx.addAttribute("konser", konser);
        ctx.addAttribute("idKonser", idKonser);

        // Defining Penampilan
        List<PenampilanKonserModel> listKonserPenampilan = penampilanService.findPenampilanBelongToKonser(Long.parseLong(idKonser));
        konser.setKonserIdol(listKonserPenampilan);

        // Giving List of All Idol
        List<IdolModel> listIdol = idolService.findAllIdol();
        ctx.addAttribute("listIdol", listIdol);

        return "/konser/update-form";
    }


    @PostMapping(value = "/konser/ubah/{idKonser}", params={"addRowIdol"})
    public String addUpdateRowIdol(
        @ModelAttribute KonserModel konser,
        @PathVariable(value = "idKonser") String idKonser,
        Model ctx
    ){
        if(konser.getKonserIdol() == null || konser.getKonserIdol().size() == 0){
            konser.setKonserIdol(new ArrayList<>());
        }

        // Giving List of All Idol
        List<IdolModel> listIdol = idolService.findAllIdol();
        ctx.addAttribute("listIdol", listIdol);


        ctx.addAttribute("idKonser", idKonser);

        PenampilanKonserModel newPenampilan = new PenampilanKonserModel();

        konser.getKonserIdol().add(newPenampilan);

        ctx.addAttribute("konser", konser);

        return "/konser/update-form";
    }

    @PostMapping(value = "/konser/ubah/{idKonser}", params = {"deleteRowIdol"})
    public String deleteUpdateRowIdol(
        @ModelAttribute KonserModel konser,
        @PathVariable(value = "idKonser") String idKonser,
        @RequestParam("deleteRowIdol") Integer row,
        Model ctx
    ){  
        final Integer rowId = Integer.valueOf(row);
        konser.getKonserIdol().remove(rowId.intValue());
        ctx.addAttribute("konser", konser);


        ctx.addAttribute("idKonser", idKonser);

        // Giving List of All Idol
        List<IdolModel> listIdol = idolService.findAllIdol();
        ctx.addAttribute("listIdol", listIdol);
        return "/konser/update-form";
    }


    @PostMapping(value = "/konser/ubah/{idKonser}", params={"save"})
    public String ubahKonserSubmit(
        @PathVariable(value = "idKonser") String idKonser,
        @ModelAttribute KonserModel konser,
        Model ctx
    ){      
        Long konserId = Long.parseLong(idKonser);

        konser.setId(konserId);

        // Updating Konser attributes
        konserService.updateKonser(konser);

        KonserModel previousKonser = konserService.findById(konserId);

        System.out.println(previousKonser);

        // Updating Penampilan
        List<PenampilanKonserModel> listPenampilan = konser.getKonserIdol();
        
        penampilanService.deleteIdolFromKonser(konserId);

        penampilanService.addIdolToKonser(previousKonser, listPenampilan);

        ctx.addAttribute("namaKonser", konser.getNamaKonser());
        return "/konser/update-submit";
    }

    @GetMapping("/carikonser")
    public String searchKonserPage(
        @RequestParam(value = "pendapatan") Long pendapatan,
        @RequestParam(value = "idol") Long idIdol,
        Model ctx
    ){

        SearchKonserDto searchKonserDto = new SearchKonserDto(pendapatan, idIdol);
        ctx.addAttribute("searchKonserDto", searchKonserDto);

        List<IdolModel> listIdol = idolService.findAllIdol();
        ctx.addAttribute("listIdol", listIdol);

        List<KonserModel> listKonser = konserService.findKonserByPendapatanAndIdol(pendapatan, idIdol);
        ctx.addAttribute("listKonser", listKonser);

        return "/konser/search-show";
    }

    @PostMapping("/konser/cari")
    public ModelAndView searchKonserRedirect(
        @ModelAttribute SearchKonserDto searchKonserDto,
        Model ctx
    ){

        String redirectURL = "/carikonser?pendapatan=" + searchKonserDto.pendapatan + "&idol=" + searchKonserDto.idIdol;
        return new ModelAndView("redirect:"+redirectURL);
    }

    @GetMapping("/konser/cari")
    public String searchKonserForm(
        Model ctx
    ){
        SearchKonserDto searchKonserDto = new SearchKonserDto();
        ctx.addAttribute("searchKonserDto", searchKonserDto);

        List<IdolModel> listIdol = idolService.findAllIdol();
        ctx.addAttribute("listIdol", listIdol);

        return "/konser/search-form";
    }
    
}
