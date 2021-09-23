package jpabook.jpashop.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @GetMapping("/api/v1/members")
    public List<Member> memberV1(){
        return memberService.findMembers();
    }

    @GetMapping("/api/v2/members")
    public Result memberV2(){
        List<Member> members = memberService.findMembers();
        List<MemberDto> collect = members.stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());

        return new Result(collect);
    }
    @GetMapping("/api/v3/members")
    public Result memberV3(){
        List<Member> members = memberService.findMembers();
        List<MemberDto> collect = members.stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @GetMapping("/api/v4/members")
    public void memberV4() {
        List<Member> member = memberService.findMembers();
        List<MemberDto> collect = member.stream().map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());
        new Result(collect);


    }
        @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String name;
    }
    //apI통
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    //api를 받을떄는 Entity를 parameter로 받지마라 그리고 외부로 노출 x.
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }
    @PutMapping("api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id,
                                               @RequestBody @Valid UpdateMemberRequest request){
        memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }
    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse{
        private Long id;
        private String name;
    }
    @Data
    static class UpdateMemberRequest{
        private String name;
    }
    @Data
    static class CreateMemberRequest{
        private String name;

    }
    @Data
    static class CreateMemberResponse{
        private Long id;
        CreateMemberResponse(Long id){
            this.id = id;
        }
    }
}
