package ldt.springframework.springmvc.controller;

import ldt.springframework.tigibusiness.domain.Course;
import ldt.springframework.tigibusiness.services.CourseService;
import ldt.springframework.tigibusiness.services.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/*
 * author: Luu Duc Trung
 * https://github.com/luuductrung1234
 * ---
 * 7/15/18
 */


@Controller
public class IndexController {

    // =======================================
    // =           Injection Point           =
    // =======================================

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;


    // =======================================
    // =          Attribute Section          =
    // =======================================

//    private boolean isInit = true;


    // =======================================
    // =           Request Mapping            =
    // =======================================

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request){
        List<Course> courses = (List<Course>) courseService.listAll();
        model.addAttribute("currentUser", request.getSession().getAttribute("curUser"));
        model.addAttribute("courses", courses);

        return "index";
    }

    @RequestMapping("/access_denied")
    public String noAuth(){
        return "access_denied";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model){
        return "login";
    }

}
