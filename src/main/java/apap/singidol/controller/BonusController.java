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
import apap.singidol.dto.TopSearchDto;
import apap.singidol.model.IdolModel;
import apap.singidol.model.KonserModel;
import apap.singidol.model.PenampilanKonserModel;
import apap.singidol.model.TiketModel;
import apap.singidol.model.TipeModel;
import apap.singidol.service.IdolService;
import apap.singidol.service.KonserService;
import apap.singidol.service.PenampilanService;
import apap.singidol.service.TiketService;
import apap.singidol.service.TipeService;

@Controller
public class BonusController {

    @Autowired
    KonserService konserService;

    @Autowired
    IdolService idolService;

    @Autowired
    PenampilanService penampilanService;

    @Autowired
    TiketService tiketService;

    @Autowired
    TipeService tipeService;

    @GetMapping("/bonus")
    public String bonusForm(
        Model ctx
    ){
        List<TipeModel> listTipe = tipeService.findAllTipe();
        System.out.println(listTipe.size());
        ctx.addAttribute("listTipe", listTipe);

        TopSearchDto topSearchDto = new TopSearchDto();
        ctx.addAttribute("topSearchDto", topSearchDto);
        return "/bonus/form";
    }

    @PostMapping("/bonus")
    public ModelAndView bonusRedirect(
        @ModelAttribute TopSearchDto topSearchDto,
        Model ctx
    ){  
        String redirectURL = "/bonus/konser/top/" + topSearchDto.namaTipe;
        return new ModelAndView("redirect:"+redirectURL);
    }
    
    @GetMapping("/bonus/konser/top/{tipe}")
    public String bonusPage(
        @PathVariable(value = "tipe") String namaTipe,
        Model ctx
    ){  
        List<TipeModel> listTipe = tipeService.findAllTipe();
        System.out.println(listTipe.size());
        ctx.addAttribute("listTipe", listTipe);
        
        TopSearchDto topSearchDto = new TopSearchDto(namaTipe);
        ctx.addAttribute("topSearchDto", topSearchDto);

        TipeModel tipe = tipeService.findTipeByNama(namaTipe);

        KonserModel konser = konserService.findMostFrequent(tipe.getId());
        ctx.addAttribute("konser", konser);
        
        return "/bonus/page";
    }
}
