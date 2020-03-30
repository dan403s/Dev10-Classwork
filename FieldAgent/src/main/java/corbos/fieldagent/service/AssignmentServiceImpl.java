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
import corbos.fieldagent.entities.Assignment;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniel Bart
 */
@Service
public class AssignmentServiceImpl implements AssignmentService {

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
    public Assignment findById(int assignmentId) {
        return assignmentRepository.findById(assignmentId).orElse(null);
    }

    @Override
    public List<Assignment> findAll() {
        return assignmentRepository.findAll();
    }

    @Override
    public Assignment save(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    @Override
    public void deleteById(int assignmentId) {
        assignmentRepository.deleteById(assignmentId);
    }

    @Override
    public long count() {
        return assignmentRepository.count();
    }

    @Override
    public boolean existsById(int assignmentId) {
        return assignmentRepository.existsById(assignmentId);
    }

    @Override
    public List<Assignment> findByAgentIdentifier(String indentifier) {
        List<Assignment> unsortedList = assignmentRepository.findByAgentIdentifier(indentifier);
        List<Assignment> sortedList = unsortedList
                .stream()
                .sorted(Comparator.comparing(Assignment::getStartDate))
                .collect(Collectors.toList());
        return sortedList;
    }

    @Override
    public Set<String> validateRules(Assignment assignment, LocalDate originalStartDate, LocalDate originalProjectedEndDate, LocalDate originalActualEndDate, String originalAgentIdentifier) {
        Set<String> customViolations = new HashSet<>();
//        boolean originalStartDateEqualsNewStartDate;
//        boolean originalProjectEndDateEqualsNewProjectEndDate;
//        boolean originalActualEndDateEqualsNewProjectEndDate;
//        boolean originalAgentIdentifierEqualsNewAgentIdentifier;
//
//        // boolean to determine if original dates populating the form are the same as the dates user submitted
//        try {
//            originalStartDateEqualsNewStartDate = originalStartDate.equals(assignment.getStartDate());
//        } catch (NullPointerException e) {
//            originalStartDateEqualsNewStartDate = false;
//        }
//
//        try {
//            originalProjectEndDateEqualsNewProjectEndDate = originalProjectedEndDate.equals(assignment.getProjectedEndDate());
//        } catch (NullPointerException e) {
//            originalProjectEndDateEqualsNewProjectEndDate = false;
//        }
//
//        try {
//            originalActualEndDateEqualsNewProjectEndDate = originalActualEndDate.equals(assignment.getActualEndDate());
//        } catch (NullPointerException e) {
//            originalActualEndDateEqualsNewProjectEndDate = false;
//        }
//
//        try {
//            originalAgentIdentifierEqualsNewAgentIdentifier = originalAgentIdentifier.equals(assignment.getAgent().getIdentifier());
//        } catch (NullPointerException e) {
//            originalAgentIdentifierEqualsNewAgentIdentifier = false;
//        }

        List<Assignment> assignmentList = findByAgentIdentifier(assignment.getAgent().getIdentifier());

        if (assignment.getStartDate() != null && assignment.getProjectedEndDate() != null) {
            if (assignment.getStartDate().compareTo(assignment.getProjectedEndDate()) > -1) {
                customViolations.add("Projected end date must be after start date.");
            }
        }

        if (assignment.getStartDate() != null && assignment.getActualEndDate() != null) {
            if (assignment.getStartDate().compareTo(assignment.getActualEndDate()) > -1) {
                customViolations.add("Actual end date must be after start date.");
            }
        }

        // compare start date and projected end date
        if (assignment.getStartDate() != null && assignment.getProjectedEndDate() != null) {
            LocalDate newStartDate = assignment.getStartDate();
            LocalDate newProjectedEndDate = assignment.getProjectedEndDate();

            for (Assignment existingAssignment : assignmentList) {
                LocalDate existingStartDate = existingAssignment.getStartDate();
                LocalDate existingProjectedEndDate = existingAssignment.getProjectedEndDate();

                // compare start and end dates
                if ((newStartDate.isBefore(existingStartDate) && newProjectedEndDate.isAfter(existingProjectedEndDate)
                        || newStartDate.isAfter(existingStartDate) && newStartDate.isBefore(existingProjectedEndDate)
                        || newProjectedEndDate.isAfter(existingStartDate) && newProjectedEndDate.isBefore(existingProjectedEndDate)
                        || newStartDate.compareTo(existingStartDate) == 0 && newProjectedEndDate.compareTo(existingProjectedEndDate) == 0)) {
                    customViolations.add("The start and projected end dates cannot conflict with other assignments.");
                }

                // if list assignment actual end date is not null, compare start and end dates to it
                if (existingAssignment.getActualEndDate() != null) {
                    LocalDate existingActualEndDate = existingAssignment.getActualEndDate();

                    if ((newStartDate.isBefore(existingStartDate) && newProjectedEndDate.isAfter(existingActualEndDate)
                            || newStartDate.isAfter(existingStartDate) && newStartDate.isBefore(existingActualEndDate)
                            || newProjectedEndDate.isAfter(existingStartDate) && newProjectedEndDate.isBefore(existingActualEndDate)
                            || newStartDate.compareTo(existingStartDate) == 0 && newProjectedEndDate.compareTo(existingActualEndDate) == 0)) {
                        customViolations.add("The start and projected end dates cannot conflict with other assignments.");
                    }
                }

            }
        }

        // compare start date and actual end date
        if (assignment.getStartDate() != null && assignment.getActualEndDate() != null) {
            LocalDate newStartDate = assignment.getStartDate();
            LocalDate newActualEndDate = assignment.getActualEndDate();

            for (Assignment existingAssignment : assignmentList) {
                LocalDate existingStartDate = existingAssignment.getStartDate();
                LocalDate existingProjectedEndDate = existingAssignment.getProjectedEndDate();

                // compare start and end dates
                if ((newStartDate.isBefore(existingStartDate) && newActualEndDate.isAfter(existingProjectedEndDate)
                        || newStartDate.isAfter(existingStartDate) && newStartDate.isBefore(existingProjectedEndDate)
                        || newActualEndDate.isAfter(existingStartDate) && newActualEndDate.isBefore(existingProjectedEndDate))) {
                    customViolations.add("The start and actual end dates cannot conflict with other assignments.");
                }

                // if list assignment actual end date is not null, compare start and end dates to it
                if (existingAssignment.getActualEndDate() != null) {
                    LocalDate existingActualEndDate = existingAssignment.getActualEndDate();

                    if ((newStartDate.isBefore(existingStartDate) && newActualEndDate.isAfter(existingActualEndDate)
                            || newStartDate.isAfter(existingStartDate) && newStartDate.isBefore(existingActualEndDate)
                            || newActualEndDate.isAfter(existingStartDate) && newActualEndDate.isBefore(existingActualEndDate))) {
                        customViolations.add("The start and actual end dates cannot conflict with other assignments.");
                    }
                }

            }
        }

        return customViolations;
    }

}
