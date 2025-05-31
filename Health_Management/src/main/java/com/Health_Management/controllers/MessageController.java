package com.Health_Management.controllers;

import com.Health_Management.pojo.Message;
import com.Health_Management.pojo.User;
import com.Health_Management.services.MessageService;
import com.Health_Management.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class MessageController {

    private final MessageService messageService = new MessageService();
    private final UserService userService = new UserService();

    // Khi chưa chọn người trò chuyện
    @GetMapping
    public String chatPage(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null)
            return "redirect:/login";

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("newMessage", new Message());
        model.addAttribute("partner", null);
        model.addAttribute("messages", null);

        if ("User".equals(currentUser.getRole()))
            model.addAttribute("partners", messageService.getExperts());
        else
            model.addAttribute("partners", messageService.getUsers());

        return "User".equals(currentUser.getRole()) ? "chat_user" : "chat_expert";
    }

    // Khi đã chọn người cụ thể để trò chuyện
    @GetMapping("/{partnerId}")
    public String chatWith(@PathVariable("partnerId") int partnerId,
                           HttpSession session,
                           Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null)
            return "redirect:/login";

        User partner = userService.getUserById(partnerId);
        if (partner == null)
            return "redirect:/chat";

        List<Message> messages = messageService.getConversationBetween(currentUser, partner);

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("partner", partner);
        model.addAttribute("messages", messages);
        model.addAttribute("newMessage", new Message());

        if ("User".equals(currentUser.getRole()))
            model.addAttribute("partners", messageService.getExperts());
        else
            model.addAttribute("partners", messageService.getUsers());

        return "User".equals(currentUser.getRole()) ? "chat_user" : "chat_expert";
    }

    // Gửi tin nhắn
    @PostMapping("/send/{receiverId}")
    public String sendMessage(@PathVariable("receiverId") int receiverId,
                              @ModelAttribute("newMessage") Message msg,
                              HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser == null || receiverId <= 0 || msg.getContent() == null || msg.getContent().isBlank())
            return "redirect:/chat";

        User receiver = userService.getUserById(receiverId);
        if (receiver == null)
            return "redirect:/chat";

        msg.setSender(currentUser);
        msg.setReceiver(receiver);
        msg.setSentTime(new Timestamp(System.currentTimeMillis()));

        messageService.sendMessage(msg);
        return "redirect:/chat/" + receiverId;
    }
}
