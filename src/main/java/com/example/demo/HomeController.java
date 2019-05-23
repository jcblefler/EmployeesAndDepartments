package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());

        return "index";
    }

    @GetMapping("/adddepartment")
    public String departmentForm(Model model){
        model.addAttribute("department", new Department());
        return "departmentform";
    }

    @PostMapping("/process_department")
    public String processDepartmentForm(@Valid Department department, BindingResult result){
        if (result.hasErrors()){
            return "departmentform";
        }
        departmentRepository.save(department);

        return "redirect:/departmentlist";
    }

    @RequestMapping("/departmentlist")
    public String departmentList(Model model){
        model.addAttribute("departments", departmentRepository.findAll());

        return "departmentlist";
    }

    @GetMapping("/addemployee")
    public String employeeForm(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("employee", new Employee());
        return "employeeform";
    }

    @PostMapping("/process_employee")
    public String processEmployeeForm(@Valid Employee employee, BindingResult result){
        if (result.hasErrors()){
            return "employeeform";
        }
        employeeRepository.save(employee);

        return "redirect:/employeelist";
    }

    @RequestMapping("/employeelist")
    public String employeeList(Model model){
        model.addAttribute("employees", employeeRepository.findAll());

        return "employeelist";
    }

    @RequestMapping("/detail/{id}")
    public String showDepartment(@PathVariable("id") long id, Model model){
        model.addAttribute("department", departmentRepository.findById(id).get());
        return "showdepartment";
    }

    @RequestMapping("/update/{id}")
    public String updateDepartment(@PathVariable("id") long id, Model model){
        model.addAttribute("department", departmentRepository.findById(id).get());
        return "departmentform";
    }

    @RequestMapping("/delete/{id}")
    public String delDepartment(@PathVariable("id") long id){
        departmentRepository.deleteById(id);
        return "index";
    }

    @RequestMapping("/detail_employee/{id}")
    public String showEmployee(@PathVariable("id") long id, Model model){
        model.addAttribute("employee", employeeRepository.findById(id).get());
        return "showemployee";
    }

    @RequestMapping("/update_employee/{id}")
    public String updateEmployee(@PathVariable("id") long id, Model model){
        model.addAttribute("employee", employeeRepository.findById(id).get());
        model.addAttribute("departments",departmentRepository.findAll());
        return "employeeform";
    }

    @RequestMapping("/delete_employee/{id}")
    public String delEmployee(@PathVariable("id") long id){
        employeeRepository.deleteById(id);
        return "index";
    }
}
