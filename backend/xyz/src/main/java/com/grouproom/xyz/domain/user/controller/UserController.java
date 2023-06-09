package com.grouproom.xyz.domain.user.controller;

import com.grouproom.xyz.domain.user.dto.request.ProfileRequest;
import com.grouproom.xyz.domain.user.dto.request.VisitorRequest;
import com.grouproom.xyz.domain.user.dto.response.ProfileResponse;
import com.grouproom.xyz.domain.user.service.UserService;
import com.grouproom.xyz.global.exception.ErrorResponse;
import com.grouproom.xyz.global.model.BaseResponse;
import com.grouproom.xyz.global.service.S3UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * packageName    : com.grouproom.xyz.domain.user.controller
 * fileName       : UserController
 * author         : SSAFY
 * date           : 2023-04-21
 * description    :
 * <p>
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * <p>
 * 2023-04-21        SSAFY       최초 생성
 */

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final S3UploadService s3UploadService;
    private final UserService userService;

    @DeleteMapping("")
    public BaseResponse removeUser() {
        Long userSequence = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        userService.removeUser(userSequence);

        return new BaseResponse("성공적으로 삭제되었습니다.");
    }

    @GetMapping("/modifier")
    public BaseResponse modifierList() {
        Long userSequence = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        return new BaseResponse(userService.findModifierByUserSequence(userSequence));
    }

    @GetMapping("/profile")
    public BaseResponse profileDetails(@RequestParam(value = "userSeq",required = false) Long userSeq){
        Long fromSeq = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        if(userSeq==null)
            return new BaseResponse(userService.findProfileByUserSeq(fromSeq));
        else
            return new BaseResponse(userService.findProfileByUserSeq(fromSeq,userSeq));
    }

    @PostMapping(value = "/profile",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public BaseResponse saveProfile(@RequestPart(required = false) ProfileRequest profileRequest, @RequestPart(required = false) MultipartFile profileImage,
                                    @RequestPart(required = false)MultipartFile backgroundImage){
        Long userSeq = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        String profileImagePath = null,backgroundImagePath = null;

        if(null!=profileImage && !profileImage.isEmpty())
            profileImagePath = s3UploadService.upload(profileImage, "user");
        if(null!=backgroundImage && !backgroundImage.isEmpty())
            backgroundImagePath = s3UploadService.upload(backgroundImage, "user");

        userService.modifyProfile(userSeq,profileRequest.getNickname(),profileImagePath,backgroundImagePath,profileRequest.getIntroduce(),profileRequest.getModifierSequence());

        return new BaseResponse(null);
    }

    @PostMapping("/visitor")
    public BaseResponse saveVisitor(@RequestBody  VisitorRequest visitorRequest){
        Long fromUserSeq = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        userService.addVisitor(fromUserSeq,visitorRequest.getUserSeq(),visitorRequest.getContent());
        return new BaseResponse(null);
    }

    @DeleteMapping("/visitor/{visitorSeq}")
    public BaseResponse removeVisitor(@PathVariable("visitorSeq") Long visitorSeq){
        Long userSeq = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        userService.removeVisitor(userSeq,visitorSeq);
        return new BaseResponse(null);
    }

    @GetMapping("/visitor")
    public BaseResponse visitorList(@RequestParam(value = "userSeq",required = false) Long userSeq) {
        if(null == userSeq)
            userSeq = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        return new BaseResponse(userService.findVisitorByUserSequence(userSeq));
    }

    @GetMapping("/access-token")
    public ResponseEntity getAccessToken(HttpSession httpSession) {

        String authorization = (String)httpSession.getAttribute("Authorization");
        if(null == authorization) throw new ErrorResponse(HttpStatus.BAD_REQUEST,"로그인 실패");
        Long sequence = (Long)httpSession.getAttribute("Sequence");
        ProfileResponse profileResponse = userService.findProfileByUserSeq(sequence);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",authorization);
        headers.add("Sequence",Long.toString(sequence));
        headers.add("Image",profileResponse.getProfileImage());
        httpSession.removeAttribute("Authorization");

        HashMap<String,String> body = new HashMap<String,String>();
        body.put("result","SUCCESS");
        body.put("nickname",profileResponse.getNickname());

        return ResponseEntity.ok()
                .headers(headers)
                .body(body);
    }

    @DeleteMapping("/logout")
    public BaseResponse logout() {
        Long userSeq = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        userService.logout(userSeq);
        return new BaseResponse(null);
    }
}
