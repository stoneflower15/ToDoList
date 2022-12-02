package main.controllers;

import main.model.Business;
import main.model.BusinessRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ToDoController {

    private BusinessRepository businessRepository;

    public ToDoController(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    @GetMapping("/todo")
    public String todoMain(Model model) {
        Iterable<Business> businesses = businessRepository.findAll();
        model.addAttribute("todo", businesses);
        return "todo-main";
    }

    @GetMapping("/todo/add")
    public String todoAdd(Model model) {
        return "todo-add";
    }

    @PostMapping("/todo/add")
    public String todoPostAdd(@RequestParam String title, @RequestParam String fullTextOfBusiness, Model model) {
        Business business = new Business(title, fullTextOfBusiness);
        businessRepository.save(business);
        return "redirect:/todo";
    }

    @GetMapping("/todo/{id}")
    public String todoDetails(@PathVariable(value = "id") long id, Model model) {
        if (!businessRepository.existsById(id)) {
            return "redirect:/todo";
        }
        Optional<Business> optional = businessRepository.findById(id);
        ArrayList<Business> result = new ArrayList<>();
        optional.ifPresent(result::add);
        model.addAttribute("business", result);
        return "todo-details";
    }

    @GetMapping("/todo/{id}/edit")
    public String todoEdit(@PathVariable(value = "id") long id, Model model) {
        if (!businessRepository.existsById(id)) {
            return "redirect:/todo";
        }
        Optional<Business> optional = businessRepository.findById(id);
        ArrayList<Business> result = new ArrayList<>();
        optional.ifPresent(result::add);
        model.addAttribute("business", result);
        return "todo-edit";
    }

    @PostMapping("/todo/{id}/edit")
    public String todoPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String fullTextOfBusiness, Model model) {
        Business business = businessRepository.findById(id).orElseThrow();
        business.setTitle(title);
        business.setFullTextOfBusiness(fullTextOfBusiness);
        businessRepository.save(business);
        return "redirect:/todo";
    }

    @PostMapping("/todo/{id}/remove")
    public String todoPostDelete(@PathVariable(value = "id") long id, Model model) {
        Business business = businessRepository.findById(id).orElseThrow();
        businessRepository.delete(business);
        return "redirect:/todo";
    }
    @PostMapping("/todo/remove")
    public String todoPostDeleteAllBusinesses( Model model) {
        Iterable<Business> businesses = businessRepository.findAll();
        businessRepository.deleteAll(businesses);

        return "redirect:/";
    }
}
