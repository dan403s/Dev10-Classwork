/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corbos.fieldagent.service;

import corbos.fieldagent.entities.Agent;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Daniel Bart
 */
public interface FieldAgentService {
    
    Agent findById(String identifier);
    
    List<Agent> findAll();
    
    Agent save(Agent agent);
    
    void deleteById(String identifier);
    
    long count();
    
    boolean existsById(String identifier);
    
    Set<String> validateRules(Agent agent);
    
    Set<String> validateRules(Agent agent, String oldIdentifier);

    
}
