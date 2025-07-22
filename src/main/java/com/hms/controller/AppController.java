package com.hms.controller;

import com.hms.Constants;
import com.hms.repository.dao.RoomRepository;
import com.hms.repository.dao.UserRepository;
import com.hms.repository.dmo.Room;
import com.hms.repository.dmo.User;
import com.hms.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @GetMapping("/adminLogin")
    public String showAdminLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "admin_login";
    }


    @PostMapping("/adminLogin")
    public String showAdminLoginForm(User user, Model model) {
        if(user.getEmail() != null && user.getEmail().length() > 0) {
            String uname = user.getEmail();
            if(Constants.adminUsers.containsKey(uname)) {
                String pwd = user.getPassword();
                if(Constants.adminUsers.get(uname).equals(pwd)) {
                    return "admin_home";
                }
            }
        }
        model.addAttribute("errorMessage", "invalid credentials provided");
        return "admin_login";
    }

    @PostMapping("/goAdminHome")
    public String showAdminHome(User user, Model model) {
        return "admin_home";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        String pwd = user.getPassword();
        user.setPassword(new BCryptPasswordEncoder().encode(pwd));
        userRepository.save(user);
        return "register_success";
    }

    @GetMapping("/dashboard")
    public String showHomePage(Model model) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("greeting", "Welcome Home " + customUserDetails.getFullName());
        List<Room> roomList = roomRepository.findAll();
        model.addAttribute("roomsList", roomList);
        return "home_page";
    }

    @PostMapping("/editUser")
    public String editUserDetails(Model model, @RequestParam("userId") Long userId) {
        model.addAttribute("user", (User) userRepository.findById(userId).get());
        return "edit_user";
    }

    @PostMapping("/updateUser")
    @Transactional
    public String updateMember(@RequestParam(value = "roomId", required = false) Long roomId, @RequestParam("role") String role, Model model, User user) {
        user.setRoomNo(roomId);
        userRepository.save(user);
        if( null != role && "user".equals(role)) {
            CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("greeting", "Welcome Home " + customUserDetails.getFullName());
            model.addAttribute("alert", "Your Room is now successfully reserved.!");
            List<Room> roomList = roomRepository.findAll();
            model.addAttribute("roomsList", roomList);
            return "home_page";
        }
        List<User> usersList = userRepository.findAll();
        model.addAttribute("usersList", usersList);
        model.addAttribute("updatedUser", user);
        return "manage_room_member";
    }

    @PostMapping("/deleteUser")
    public String deleteMember(@RequestParam("userId") Long id, Model model) {
        userRepository.deleteById(id);
        List<User> usersList = userRepository.findAll();
        model.addAttribute("usersList", usersList);
        return "manage_room_member";
    }

    @GetMapping("/manageRoom")
    public String showManageRoomPage(Model model) {
        List<Room> roomsList = roomRepository.findAll();
        model.addAttribute("roomsList", roomsList);
        return "manage_room";
    }

    @GetMapping("/manageMember")
    public String showManageRoomMemberPage(Model model) {
        List<User> usersList = userRepository.findAll();
        model.addAttribute("usersList", usersList);
        return "manage_room_member";
    }

    @PostMapping("/addRoom")
    public String addRoom(Room room, Model model) {
        roomRepository.save(room);
        List<Room> roomsList = roomRepository.findAll();
        model.addAttribute("roomsList", roomsList);
        return "manage_room";
    }

    @PostMapping("/deleteRoom")
    public String deleteRoom(@RequestParam("roomId") Long id, Model model) {
        roomRepository.deleteById(id);
        List<Room> roomsList = roomRepository.findAll();
        model.addAttribute("roomsList", roomsList);
        return "manage_room";
    }

    @GetMapping("bookRoom")
    public String bookRoom(@RequestParam("roomId") Long id, @RequestParam("type") Long type, @RequestParam("cost") Long cost, Model model) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findUserByEmail(customUserDetails.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("roomType", type);
        model.addAttribute("roomId", id);
        model.addAttribute("cost", cost);
        return "book_room";
    }

}
