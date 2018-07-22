package ldt.springframework.springmvc.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import ldt.springframework.springmvc.domain.Customer;
import ldt.springframework.springmvc.domain.User;
import ldt.springframework.springmvc.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;

/*
 * author: Luu Duc Trung
 * https://github.com/luuductrung1234
 * ---
 * 7/16/18
 */

@Controller
public class CustomerController {

    // =======================================
    // =           Injection Point           =
    // =======================================

    @Autowired
    CustomerService customerService;


    // =======================================
    // =          Attribute Section          =
    // =======================================

    private String msg = "";
    private boolean failure;


    // =======================================
    // =           Request Mappin            =
    // =======================================

    @RequestMapping("/customers")
    public String listCustomers(HttpServletRequest request, Model model){
        if(!failure){
            model.addAttribute("message", null);
        }else{
            model.addAttribute("message", msg);
        }
        model.addAttribute("customers", customerService.listAll());
        model.addAttribute("currentUser", request.getSession().getAttribute("curUser"));
        failure = false;

        return "view/customer/customers";
    }

    @RequestMapping("/customer/edit/{id}")
    public String editCustomer(@PathVariable Integer id, HttpServletRequest request, Model model){
        model.addAttribute("customer", customerService.getById(id));
        model.addAttribute("currentUser", (User) request.getSession().getAttribute("curUser"));

        return "customer/customerform";
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public String saveOrUpdateProduct(Customer customer){
        try{
            Customer savedCustomer = customerService.saveOrUpdate(customer);
            failure = false;

            return "redirect:customer/" + savedCustomer.getId();
        }catch (Exception ex){
            msg = "Save fail! Something went wrong!";
            failure = true;
        }

        return "redirect:/customers";
    }


    @RequestMapping("/customer/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id, Model model){
        try{
            customerService.delete(id);
            failure = false;
        }catch (Exception ex){
            msg = "Delete fail! Something went wrong!";
            failure = true;
        }

        return "redirect:/customers";
    }

}
