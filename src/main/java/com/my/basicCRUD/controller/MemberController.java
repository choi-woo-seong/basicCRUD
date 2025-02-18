package com.my.basicCRUD.controller;

import com.my.basicCRUD.dto.MemberDto;
import com.my.basicCRUD.entity.Member;
import com.my.basicCRUD.service.MemberService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("insertForm")
    public String insertFormView(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "insertMember";
    }

    @PostMapping("insert")
    public String insert(@Valid MemberDto dto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            return "insertMember";
        }
        memberService.saveMember(dto);
        redirectAttributes.addFlashAttribute("msg","신규데이터가 입력되었습니다~");
        return "redirect:/member/view";
    }

    @GetMapping("view")
    public String showMember(Model model) {
        List<MemberDto> memberList = memberService.findAllMembers();
        model.addAttribute("list",memberList);
        return "showMember";
    }

    @GetMapping("delete/{id}")
    public String deleteMember(@PathVariable("id")Long id, RedirectAttributes redirectAttributes){
        memberService.deleteById(id);
        redirectAttributes.addFlashAttribute("msg","선택한 자료가 선택되었습니다.");
        return "redirect:/member/view";
    }

    @GetMapping("update/{id}")
    public String updateFormView(@PathVariable("id")Long id, Model model) {
        MemberDto dto = memberService.findById(id);
        log.info("=== dto : " + dto);
        model.addAttribute("member",dto);
        return "updateMember";
    }

    @PostMapping("update")
    public String update(MemberDto dto, RedirectAttributes redirectAttributes) {
        log.info("####### dto : " + dto);
        memberService.updateMember(dto);
        redirectAttributes.addFlashAttribute("msg","선택한 데이터가 수정되었습니다.");
        return "redirect:/member/view";
    }

}
