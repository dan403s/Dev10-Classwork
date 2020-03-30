/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corbos.fieldagent.controller;

import corbos.fieldagent.entities.Agent;
import corbos.fieldagent.entities.Assignment;
import corbos.fieldagent.entities.Country;
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
public class AssignmentController {

    @Autowired
    FieldAgentService fieldAgentService;

    @Autowired
    AssignmentService assignmentService;

    @Autowired
    LookupService lookupService;

    Set<ConstraintViolation<Assignment>> assignmentViolations = new HashSet<>();
    Set<String> assignmentCustomViolations = new HashSet<>();

    @GetMapping("/assignment")
    public String displayAssignmentPage(String assignmentId, String identifier, Model model) {
        List<Country> countryList = lookupService.findAllCountries();
        List<Agent> agentList = fieldAgentService.findAll();
        Agent agent = fieldAgentService.findById(identifier);

        if (assignmentId != null) {
            int assignmentIdInt = Integer.parseInt(assignmentId);
            Assignment assignment = assignmentService.findById(assignmentIdInt);
            model.addAttribute("assignment", assignment);
        }

        model.addAttribute("countries", countryList);
        model.addAttribute("agents", agentList);
        model.addAttribute("agent", agent);
        model.addAttribute("errors", assignmentViolations);
        model.addAttribute("customErrors", assignmentCustomViolations);

        return "assignment";
    }

    @PostMapping("/assignment")
    public String editAssignment(Assignment assignment, HttpServletRequest request) {
        assignmentViolations.clear();
        assignmentCustomViolations.clear();

        String identifier = request.getParameter("identifier");
        String countryCode = request.getParameter("countryCode");
        String assignmentStartDate = request.getParameter("assignmentStartDate");
        String assignmentProjectedEndDate = request.getParameter("assignmentProjectedEndDate");
        String assignmentActualEndDate = request.getParameter("assignmentActualEndDate");
        String originalAgentIdentifier = request.getParameter("originalAgentIdentifier");
        String originalStartDateString = request.getParameter("originalStartDate");
        String originalProjectedEndDateString = request.getParameter("originalProjectedEndDate");
        String originalActualEndDateString = request.getParameter("originalActualEndDate");
        LocalDate originalStartDate = null;
        LocalDate originalProjectedEndDate = null;
        LocalDate originalActualEndDate = null;

        if (!assignmentStartDate.equals("")) {
            LocalDate assignmentStartDateConverted = LocalDate.parse(assignmentStartDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            assignment.setStartDate(assignmentStartDateConverted);
        }

        if (!assignmentProjectedEndDate.equals("")) {
            LocalDate assignmentProjectedEndDateConverted = LocalDate.parse(assignmentProjectedEndDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            assignment.setProjectedEndDate(assignmentProjectedEndDateConverted);
        }

        if (!assignmentActualEndDate.equals("")) {
            LocalDate assignmentActualEndDateConverted = LocalDate.parse(assignmentActualEndDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            assignment.setActualEndDate(assignmentActualEndDateConverted);
        }

        if (identifier != null) {
            Agent agent = fieldAgentService.findById(identifier);
            assignment.setAgent(agent);
        }

        if (countryCode != null) {
            Country country = lookupService.findCountryByCode(countryCode);
            assignment.setCountry(country);
        }

        if (!originalStartDateString.equals("")) {
            originalStartDate = LocalDate.parse(originalStartDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        if (!originalProjectedEndDateString.equals("")) {
            originalProjectedEndDate = LocalDate.parse(originalProjectedEndDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        if (!originalActualEndDateString.equals("")) {
            originalActualEndDate = LocalDate.parse(originalActualEndDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        Assignment oldAssignmentBeforeEdit = null;

        if (assignment.getAssignmentId() != 0) {
            oldAssignmentBeforeEdit = assignmentService.findById(assignment.getAssignmentId());
            assignmentService.deleteById(assignment.getAssignmentId());
        }

        Set<String> assignmentCustomViolationsToBeAdded = assignmentService.validateRules(assignment, originalStartDate, originalProjectedEndDate, originalActualEndDate, originalAgentIdentifier);

        assignmentCustomViolations.addAll(assignmentCustomViolationsToBeAdded);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        assignmentViolations = validate.validate(assignment);

        if (assignmentViolations.isEmpty() && assignmentCustomViolations.isEmpty()) {
            assignment = assignmentService.save(assignment);
        } else {
            if (assignment.getAssignmentId() != 0) {
                assignment = assignmentService.save(oldAssignmentBeforeEdit);
            }
        }

        if (assignment.getAssignmentId() == 0) {
            return "redirect:/assignment?identifier=" + originalAgentIdentifier;
        } else {
            return "redirect:/assignment?assignmentId=" + assignment.getAssignmentId() + "&identifier=" + originalAgentIdentifier;
        }

    }

    @PostMapping("/delete-assignment")
    public String deleteAssignment(String assignmentId, String identifier, Model model) {
        assignmentViolations.clear();
        assignmentCustomViolations.clear();

        int assignmentIdInt = Integer.parseInt(assignmentId);

        if (assignmentService.findById(assignmentIdInt) != null) {
            assignmentService.deleteById(assignmentIdInt);
        }

        return "redirect:/field-agent?identifier=" + identifier;
    }

}
