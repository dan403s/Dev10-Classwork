/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corbos.fieldagent.service;

import corbos.fieldagent.data.AgencyRepository;
import corbos.fieldagent.data.AgentRepository;
import corbos.fieldagent.data.AssignmentRepository;
import corbos.fieldagent.data.CountryRepository;
import corbos.fieldagent.data.SecurityClearanceRepository;
import corbos.fieldagent.entities.Agent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniel Bart
 */
@Service
public class FieldAgentServiceImpl implements FieldAgentService {

    @Autowired
    AgencyRepository agencyRepository;

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    AssignmentRepository assignmentRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    SecurityClearanceRepository securityClearanceRepository;

    @Override
    public Agent findById(String identifier) {
        return agentRepository.findById(identifier).orElse(null);
    }

    @Override
    public List<Agent> findAll() {
        return agentRepository.findAll();
    }

    @Override
    public Agent save(Agent agent) {
        return agentRepository.save(agent);
    }

    @Override
    public void deleteById(String identifier) {
        agentRepository.deleteById(identifier);
    }

    @Override
    public long count() {
        return agentRepository.count();
    }

    @Override
    public boolean existsById(String identifier) {
        return agentRepository.existsById(identifier);
    }

    @Override
    public Set<String> validateRules(Agent agent) {
        LocalDate beginningDateValidation = LocalDate.parse("1900-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dateNow = LocalDate.now();
        LocalDate endingDateValidation = dateNow.minusYears(10);

        Set<String> customViolations = new HashSet<>();

        if (agent.getBirthDate() != null) {
            if (agent.getBirthDate().compareTo(beginningDateValidation) < 0 || agent.getBirthDate().compareTo(endingDateValidation) > 0) {
                customViolations.add("Birthdate must be between 1/1/1900 and ten years from the current day.");
            }
        }
        
        if (agent.getActivationDate()!= null && agent.getBirthDate() != null) {
            if (agent.getActivationDate().compareTo(agent.getBirthDate()) < 1) {
                customViolations.add("Activation date must be after birthdate.");
            }
        }

        List<Agent> agentList = findAll();

        for (Agent existingAgent : agentList) {
            String agentIdentifier = existingAgent.getIdentifier();

            if (agentIdentifier.equals(agent.getIdentifier())) {
                customViolations.add("Agent identifier already exists. Please choose a different identifier.");
            }
        }

        return customViolations;
    }

    @Override
    public Set<String> validateRules(Agent agent, String oldIdentifier) {
        LocalDate beginningDateValidation = LocalDate.parse("1900-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dateNow = LocalDate.now();
        LocalDate endingDateValidation = dateNow.minusYears(10);

        Set<String> customViolations = new HashSet<>();

        if (agent.getBirthDate() != null) {
            if (agent.getBirthDate().isBefore(beginningDateValidation) || agent.getBirthDate().isAfter(endingDateValidation)) {
                customViolations.add("Birthdate must be between 1/1/1900 and ten years from the current day.");
            }
        }
        
        if (agent.getActivationDate()!= null && agent.getBirthDate() != null) {
            if (agent.getActivationDate().isBefore(agent.getBirthDate())) {
                customViolations.add("Activation date must not be before birthdate.");
            }
        }

        List<Agent> agentList = findAll();

        for (Agent existingAgent : agentList) {
            String agentIdentifier = existingAgent.getIdentifier();

            if (agentIdentifier.equals(agent.getIdentifier()) && !agentIdentifier.equals(oldIdentifier)) {
                customViolations.add("Agent identifier already exists. Please choose a different identifier.");
            }
        }

        return customViolations;
    }

}
