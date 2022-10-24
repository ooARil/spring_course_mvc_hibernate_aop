package com.zaurtregulov.spring.mvc_hibernate_aop.controller;

import com.zaurtregulov.spring.mvc_hibernate_aop.entity.Employee;
import com.zaurtregulov.spring.mvc_hibernate_aop.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/send")
public class MyController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/")
    public String showAllEmployees(Model model) {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("allEmps", allEmployees);
        return "all-employees";
    }

    @RequestMapping(value = "/addNewEmployee")
    public String addNewEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee-info";
    }

    @RequestMapping(value = "/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/send/";
    }

    @RequestMapping(value = "/updateInfo")
    public String updateInfo(@RequestParam("empId") int id,
                             Model model) {
        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);
        return "employee-info";
    }

    @RequestMapping(value = "/deleteEmployee")
    public String deleteEmployee(@RequestParam("empId") int id) {
        employeeService.deleteEmployee(id);
        return "redirect:/send/";
    }
}
