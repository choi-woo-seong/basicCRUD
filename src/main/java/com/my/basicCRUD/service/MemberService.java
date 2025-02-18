package com.my.basicCRUD.service;

import com.my.basicCRUD.dto.MemberDto;
import com.my.basicCRUD.entity.Member;
import com.my.basicCRUD.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    public List<MemberDto> findAllMembers() {
        List<Member> memberList = memberRepository.findAll();
//        List<MemberDto> dtoList = new ArrayList<>();

        return memberList.stream().map(x -> MemberDto.fromEntity(x)).toList();

//        for (int i = 0; i < memberList.stream().count(); i++) {
//            MemberDto dto = new MemberDto();
//
//            dto.setId(memberList.get(i).getMemberId());
//            dto.setName(memberList.get(i).getName());
//            dto.setAge(memberList.get(i).getAge());
//            dto.setAddress(memberList.get(i).getAddress());
//
//            dtoList.add(dto);
//        }


//        return dtoList;
    }

    public void saveMember(MemberDto dto) {
        Member member = MemberDto.fromDto(dto);
        memberRepository.save(member);
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    public MemberDto findById(Long id) {
        Member member = memberRepository.findById(id).orElse(null);
        if(!ObjectUtils.isEmpty(member)) {
            return MemberDto.fromEntity(member);
        }else{
            return null;
        }
    }

    public void updateMember(MemberDto dto) {
        Member member = MemberDto.fromDto(dto);
        //save() : 해당 ID가 존재하면 수정, 없으면 입력
        memberRepository.save(member);
    }
}
