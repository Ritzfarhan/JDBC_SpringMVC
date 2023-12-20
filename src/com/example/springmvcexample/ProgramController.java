package com.example.springmvcexample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/program")
public class ProgramController {

    @GetMapping("/getAll")
    public String getAllPrograms() {
        return "program/getAll";
    }

    @GetMapping("/getById")
    public String getProgramById() {
        return "program/getById";
    }

    @PostMapping("/add")
    public String addProgram() {
        return "program/add";
    }

    @PostMapping("/delete")
    public String deleteProgram() {
        return "program/delete";
    }

    @PostMapping("/update")
    public String updateProgram() {
        return "program/update";
    }
}
