/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corbos.fieldagent.service;

import corbos.fieldagent.entities.Assignment;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Daniel Bart
 */
public interface AssignmentService {
    
    Assignment findById(int assignmentId);
    
    List<Assignment> findAll();
    
    Assignment save(Assignment assignment);
    
    void deleteById(int assignmentId);
    
    long count();
    
    boolean existsById(int assignmentId);
    
    List<Assignment> findByAgentIdentifier(String indentifier);
    
    Set<String> validateRules(Assignment assignment, LocalDate originalStartDate, LocalDate originalProjectedEndDate , LocalDate originalActualEndDate, String originalAgentIdentifier);
    
}
