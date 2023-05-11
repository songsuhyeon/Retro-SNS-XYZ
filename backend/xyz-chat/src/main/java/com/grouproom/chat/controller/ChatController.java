package com.grouproom.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.SecureRandom;

@Controller
public class ChatController {

    @GetMapping("/")
    public String index() {
        return "error";
    }

    @GetMapping("/room")
    public String chattingRoom(@RequestParam(name = "number") String roomName, Model model) {

        model.addAttribute("room", roomName);
        model.addAttribute("name", Integer.toString(new SecureRandom().nextInt(10000) * 100));
        return "chattingRoom2";
    }
}