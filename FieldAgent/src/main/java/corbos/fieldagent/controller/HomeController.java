/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corbos.fieldagent.controller;

import corbos.fieldagent.entities.Agent;
import corbos.fieldagent.service.AssignmentService;
import corbos.fieldagent.service.FieldAgentService;
import corbos.fieldagent.service.LookupService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Daniel Bart
 */
@Controller
public class HomeController {

    @Autowired
    FieldAgentService fieldAgentService;

    @Autowired
    AssignmentService assignmentService;

    @Autowired
    LookupService lookupService;

    @GetMapping({"/", "/home"})
    public String displayHomePage(Model model) {
        List<Agent> agentList = fieldAgentService.findAll();
        model.addAttribute("agents", agentList);
        return "home";
    }

}
