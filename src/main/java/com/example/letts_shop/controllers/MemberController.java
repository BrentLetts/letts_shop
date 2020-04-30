package com.example.letts_shop.controllers;

import com.example.letts_shop.dto.AddressAutoCompleteDto;
import com.example.letts_shop.dto.MemberDto;
import com.example.letts_shop.models.geocode.AddressComponent;
import com.example.letts_shop.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("")
    public String displayMembers(Model model){
        model.addAttribute("title", "All Members");
        model.addAttribute(memberRepository.findAll());
        model.addAttribute(new AddressAutoCompleteDto());
        return "/member/index";
    }

    @GetMapping("/addMemberInformation")
    public String displayAddMemberInformation(Model model){
        model.addAttribute("title", "Add Your Information");
        model.addAttribute("title", "Add Your Information");
        model.addAttribute(new MemberDto());
        model.addAttribute(new AddressComponent());
        model.addAttribute(new AddressAutoCompleteDto());
        return "/member/addMemberInformation";
    }

    // TODO: Process the member info
    @PostMapping("/addMemberInformation")
    public String processAddMemberInformation(@ModelAttribute("addressAutoCompleteDto") AddressAutoCompleteDto addressDto,
                                              @ModelAttribute("memberDto") MemberDto memberDto, Model model){
        model.addAttribute("title", "This is the new Title");
        return "/member/addMemberInformation";
    }

}
