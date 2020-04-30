package com.example.letts_shop.validators;

import com.example.letts_shop.models.Member;
import com.example.letts_shop.repositories.MemberRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private MemberRepository memberRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return username != null && memberRepository.findByUserName(username) == null;
    }
}
