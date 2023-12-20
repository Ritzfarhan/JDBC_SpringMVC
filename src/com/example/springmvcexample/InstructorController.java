package com.example.springmvcexample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/instructor")
public class InstructorController {

    @GetMapping("/getAll")
    public String getAllInstructors() {
        return "instructor/getAll";
    }

    @GetMapping("/getById")
    public String getInstructorById() {
        return "instructor/getById";
    }

    @PostMapping("/add")
    public String addInstructor() {
        return "instructor/add";
    }

    @PostMapping("/delete")
    public String deleteInstructor() {
        return "instructor/delete";
    }

    @PostMapping("/update")
    public String updateInstructor() {
        return "instructor/update";
    }
}
