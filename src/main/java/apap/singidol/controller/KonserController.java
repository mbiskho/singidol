package apap.singidol.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import apap.singidol.model.KonserModel;

@Controller
public class KonserController {

    
    @RequestMapping("/konser")
    public String viewAllKonser(
        Model ctx
    ){
        return "/konser/index";
    }

    
    @GetMapping("/konser/tambah")
    public String formAddKonser(
        Model ctx
    ){

        KonserModel konser = new KonserModel();
        ctx.addAttribute("konser", konser);
        return "/konser/form-add";
    }


    @PostMapping("/konser/tambah")
    public String addKonser(
        Model ctx
    ){
        ctx.addAttribute("page", "konser");
        return "/konser/fadd";
    }
}
