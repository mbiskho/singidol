package apap.singidol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import apap.singidol.model.IdolModel;
import apap.singidol.service.IdolService;

@Controller
public class IdolController {

    @Autowired
    IdolService idolService;

    @RequestMapping("/idol")
    public String viewIdol(
        Model ctx
    ){
        List<IdolModel> lstIdol = idolService.findAllIdol();
        ctx.addAttribute("listIdol", lstIdol);
        ctx.addAttribute(lstIdol);
        return "/idol/view";
    }

    @GetMapping("/idol/tambah")
    public String addIdolForm(
        Model ctx
    ){  
        IdolModel idol = new IdolModel();
        ctx.addAttribute("idol", idol);
        return "/idol/add-form";
    }


    @PostMapping("/idol/tambah")
    public String addIdolSubmit(
        @ModelAttribute IdolModel idol,
        Model ctx
    ){
        IdolModel savedIdol = idolService.saveIdol(idol);
        if(savedIdol == null){
            return "add-error";
        }

        ctx.addAttribute("namaIdol", idol.getNamaIdol());
        return "/idol/add-submit";
    }


    @RequestMapping("/idol/{idIdol}")
    public String detailIdol(
        Model ctx,
        @PathVariable(value = "idIdol") String id
    ){
        IdolModel idol = idolService.findIdolById(Long.parseLong(id));

        ctx.addAttribute("idol", idol);

        return "/idol/detail";
    }
}
