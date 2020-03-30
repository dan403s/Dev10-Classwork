/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corbos.fieldagent.controller;

import corbos.fieldagent.entities.Agency;
import corbos.fieldagent.entities.Agent;
import corbos.fieldagent.entities.Assignment;
import corbos.fieldagent.entities.SecurityClearance;
import corbos.fieldagent.service.AssignmentService;
import corbos.fieldagent.service.FieldAgentService;
import corbos.fieldagent.service.LookupService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Daniel Bart
 */
@Controller
public class FieldAgentController {

    @Autowired
    FieldAgentService fieldAgentService;

    @Autowired
    AssignmentService assignmentService;

    @Autowired
    LookupService lookupService;

    Set<ConstraintViolation<Agent>> addAgentViolations = new HashSet<>();
    Set<String> addAgentCustomViolations = new HashSet<>();

    Set<ConstraintViolation<Agent>> editAgentViolations = new HashSet<>();
    Set<String> editAgentCustomViolations = new HashSet<>();

    @GetMapping("/add-field-agent")
    public String displayAddFieldAgentPage(Model model) {
        editAgentViolations.clear();
        editAgentCustomViolations.clear();
        
        List<Agency> agencyList = lookupService.findAllAgencies();
        List<SecurityClearance> securityClearanceList = lookupService.findAllSecurityClearances();
        model.addAttribute("agencies", agencyList);
        model.addAttribute("securityClearances", securityClearanceList);
        model.addAttribute("errors", addAgentViolations);
        model.addAttribute("customErrors", addAgentCustomViolations);
        return "add-field-agent";
    }

    @PostMapping("/add-field-agent")
    public String addAgent(Agent agent, HttpServletRequest request, Model model) {
        addAgentViolations.clear();
        addAgentCustomViolations.clear();
        editAgentViolations.clear();
        editAgentCustomViolations.clear();

        String agencyId = request.getParameter("agencyId");
        String securityClearanceId = request.getParameter("securityClearanceId");
        String agentBirthDate = request.getParameter("agentBirthDate");
        String agentActivationDate = request.getParameter("agentActivationDate");
        String agentHeightString = request.getParameter("agentHeight");

        Boolean isActiveCheckbox;

        if (request.getParameter("isActiveCheckbox") != null) {
            isActiveCheckbox = true;
        } else {
            isActiveCheckbox = false;
        }

        if (agencyId != null) {
            agent.setAgency(lookupService.findAgencyById(Integer.parseInt(agencyId)));
        }

        if (securityClearanceId != null) {
            agent.setSecurityClearance(lookupService.findSecurityClearanceById(Integer.parseInt(securityClearanceId)));
        }

        if (!agentBirthDate.equals("")) {
            LocalDate agentBirthDateConverted = LocalDate.parse(agentBirthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            agent.setBirthDate(agentBirthDateConverted);
        }

        if (!agentActivationDate.equals("")) {
            LocalDate agentActivationDateConverted = LocalDate.parse(agentActivationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            agent.setActivationDate(agentActivationDateConverted);
        }

        if (!agentHeightString.equals("")) {
            int agentHeight = Integer.parseInt(agentHeightString);
            agent.setHeight(agentHeight);
        }

        agent.setActive(isActiveCheckbox);

        Set<String> addAgentCustomViolationsToBeAdded = fieldAgentService.validateRules(agent);

        addAgentCustomViolations.addAll(addAgentCustomViolationsToBeAdded);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        addAgentViolations = validate.validate(agent);

        if (addAgentViolations.isEmpty() && addAgentCustomViolations.isEmpty()) {
            fieldAgentService.save(agent);
            return "redirect:/home";
        }

        return "redirect:/add-field-agent";

    }

    @GetMapping("/delete-field-agent")
    public String displayDeleteFieldAgentPage(String identifier, Model model) {
        addAgentViolations.clear();
        addAgentCustomViolations.clear();
        editAgentViolations.clear();
        editAgentCustomViolations.clear();
        
        Agent agent = fieldAgentService.findById(identifier);
        List<Assignment> assignmentList = assignmentService.findByAgentIdentifier(identifier);
        model.addAttribute("agent", agent);
        model.addAttribute("assignments", assignmentList);
        return "delete-field-agent";
    }

    @PostMapping("/delete-field-agent")
    public String deleteAgent(String identifier) {
        addAgentViolations.clear();
        addAgentCustomViolations.clear();
        editAgentViolations.clear();
        editAgentCustomViolations.clear();
        
        List<Assignment> assignmentList = assignmentService.findByAgentIdentifier(identifier);

        for (Assignment assignment : assignmentList) {
            assignmentService.deleteById(assignment.getAssignmentId());
        }
        fieldAgentService.deleteById(identifier);
        return "redirect:/home";
    }

    @GetMapping("/field-agent")
    public String displayFieldAgentPage(String identifier, Model model) {
        addAgentViolations.clear();
        addAgentCustomViolations.clear();
        
        Agent agent = fieldAgentService.findById(identifier);
        List<Agency> agencyList = lookupService.findAllAgencies();
        List<SecurityClearance> securityClearanceList = lookupService.findAllSecurityClearances();
        List<Assignment> assignmentList = assignmentService.findByAgentIdentifier(identifier);
        boolean isActiveBoolean = agent.isActive();

        model.addAttribute("agent", agent);
        model.addAttribute("agencies", agencyList);
        model.addAttribute("securityClearances", securityClearanceList);
        model.addAttribute("assignments", assignmentList);
        model.addAttribute("isActiveBoolean", isActiveBoolean);
        model.addAttribute("errors", editAgentViolations);
        model.addAttribute("customErrors", editAgentCustomViolations);
        return "field-agent";
    }

    @PostMapping("/field-agent")
    public String editFieldAgentPage(Agent agent, HttpServletRequest request, Model model) {
        addAgentViolations.clear();
        addAgentCustomViolations.clear();
        editAgentViolations.clear();
        editAgentCustomViolations.clear();

        String agencyId = request.getParameter("agencyId");
        String securityClearanceId = request.getParameter("securityClearanceId");
        String agentBirthDate = request.getParameter("agentBirthDate");
        String agentActivationDate = request.getParameter("agentActivationDate");
        String agentHeightString = request.getParameter("editAgentHeight");
        String oldIdentifier = request.getParameter("oldIdentifier");

        Boolean isActiveCheckbox;

        if (request.getParameter("isActiveCheckbox") != null) {
            isActiveCheckbox = true;
        } else {
            isActiveCheckbox = false;
        }

        if (agencyId != null) {
            agent.setAgency(lookupService.findAgencyById(Integer.parseInt(agencyId)));
        }

        if (securityClearanceId != null) {
            agent.setSecurityClearance(lookupService.findSecurityClearanceById(Integer.parseInt(securityClearanceId)));
        }

        if (!agentBirthDate.equals("")) {
            LocalDate agentBirthDateConverted = LocalDate.parse(agentBirthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            agent.setBirthDate(agentBirthDateConverted);
        }

        if (!agentActivationDate.equals("")) {
            LocalDate agentActivationDateConverted = LocalDate.parse(agentActivationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            agent.setActivationDate(agentActivationDateConverted);
        }

        if (!agentHeightString.equals("")) {
            int agentHeight = Integer.parseInt(agentHeightString);
            agent.setHeight(agentHeight);
        }

        agent.setActive(isActiveCheckbox);

        List<Assignment> assignmentList = assignmentService.findByAgentIdentifier(oldIdentifier);

        Set<String> editAgentCustomViolationsToBeAdded = fieldAgentService.validateRules(agent, oldIdentifier);

        editAgentCustomViolations.addAll(editAgentCustomViolationsToBeAdded);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        editAgentViolations = validate.validate(agent);

        if (editAgentViolations.isEmpty() && editAgentCustomViolations.isEmpty()) {
            fieldAgentService.save(agent);

            for (Assignment assignment : assignmentList) {
                assignment.setAgent(agent);
                assignmentService.save(assignment);
            }

            if (!oldIdentifier.equals(agent.getIdentifier())) {
                fieldAgentService.deleteById(oldIdentifier);
            }
            
            oldIdentifier = agent.getIdentifier();
        }

        List<Agency> agencyList = lookupService.findAllAgencies();
        List<SecurityClearance> securityClearanceList = lookupService.findAllSecurityClearances();
        boolean isActiveBoolean = agent.isActive();

        model.addAttribute("agent", agent);
        model.addAttribute("agencies", agencyList);
        model.addAttribute("securityClearances", securityClearanceList);
        model.addAttribute("assignments", assignmentList);
        model.addAttribute("isActiveBoolean", isActiveBoolean);

        return "redirect:/field-agent?identifier=" + oldIdentifier;
    }

}
