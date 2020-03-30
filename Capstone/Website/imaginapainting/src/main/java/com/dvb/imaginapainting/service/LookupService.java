/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.service;

import com.dvb.imaginapainting.entities.Category;
import com.dvb.imaginapainting.entities.Material;
import com.dvb.imaginapainting.entities.Medium;
import com.dvb.imaginapainting.entities.State;
import com.dvb.imaginapainting.entities.Style;
import com.dvb.imaginapainting.entities.Subject;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public interface LookupService {

    // CRUD for State objects
    /**
     * Find State by stateId
     * @param stateId
     * @return 
     */
    State findStateById(String stateId);

    /**
     * Find all State objects
     * @return 
     */
    List<State> findAllStates();

    /**
     * Save State
     * @param state
     * @return 
     */
    State saveState(State state);

    /**
     * Delete State by stateId
     * @param stateId
     */
    void deleteStateById(String stateId);

    /**
     * Count State objects
     * @return 
     */
    long countStates();

    /**
     * Check if State exists by stateId
     * @param stateId
     * @return 
     */
    boolean stateExistsById(String stateId);

    // CRUD for Category objects
    /**
     * Find Category by categoryId
     * @param categoryId
     * @return 
     */
    Category findCategoryById(int categoryId);

    /**
     * Find all Category objects
     * @return 
     */
    List<Category> findAllCategories();

    /**
     * Save Category
     * @param category
     * @return 
     */
    Category saveCategory(Category category);

    /**
     * Delete Category by categoryId
     * @param categoryId
     */
    void deleteCategoryById(int categoryId);

    /**
     * Count Category objects
     * @return 
     */
    long countCategories();

    /**
     * Check if Category exists by categoryId
     * @param categoryId
     * @return 
     */
    boolean categoryExistsById(int categoryId);

    // CRUD for Style objects
    /**
     * Find Style by styleId
     * @param styleId
     * @return 
     */
    Style findStyleById(int styleId);

    /**
     * Find all Style objects
     * @return 
     */
    List<Style> findAllStyles();

    /**
     * Save Style
     * @param style
     * @return 
     */
    Style saveStyle(Style style);

    /**
     * Delete Style by styleId
     * @param styleId
     */
    void deleteStyleById(int styleId);

    /**
     * Count Style objects
     * @return 
     */
    long countStyles();

    /**
     * Check if Style exists by styleId
     * @param styleId
     * @return 
     */
    boolean styleExistsById(int styleId);

    // CRUD for Subject objects
    /**
     * Find Subject by subjectId
     * @param subjectId
     * @return 
     */
    Subject findSubjectById(int subjectId);

    /**
     * Find all Subject objects
     * @return 
     */
    List<Subject> findAllSubjects();

    /**
     * Save Subject
     * @param subject
     * @return 
     */
    Subject saveSubject(Subject subject);

    /**
     * Delete Subject by subjectId
     * @param subjectId
     */
    void deleteSubjectById(int subjectId);

    /**
     * Count Subject objects
     * @return 
     */
    long countSubjects();

    /**
     * Check if Subject exists by subjectId
     * @param subjectId
     * @return 
     */
    boolean subjectExistsById(int subjectId);

    // CRUD for Medium objects
    /**
     * Find Medium by mediumId
     * @param mediumId
     * @return 
     */
    Medium findMediumById(int mediumId);

    /**
     * Find all Medium objects
     * @return 
     */
    List<Medium> findAllMediums();

    /**
     * Save Medium
     * @param medium
     * @return 
     */
    Medium saveMedium(Medium medium);

    /**
     * Delete Medium by mediumId
     * @param mediumId
     */
    void deleteMediumById(int mediumId);

    /**
     * Count Medium objects
     * @return 
     */
    long countMediums();

    /**
     * Check if Medium exists by mediumId
     * @param mediumId
     * @return 
     */
    boolean mediumExistsById(int mediumId);

    // CRUD for Material objects
    /**
     * Find Material by materialId
     * @param materialId
     * @return 
     */
    Material findMaterialById(int materialId);

    /**
     * Find all Material objects
     * @return 
     */
    List<Material> findAllMaterials();

    /**
     * Save Material
     * @param material
     * @return 
     */
    Material saveMaterial(Material material);

    /**
     * Delete Material by materialId
     * @param materialId
     */
    void deleteMaterialById(int materialId);

    /**
     * Count Material objects
     * @return 
     */
    long countMaterials();

    /**
     * Check if Material exists by materialId
     * @param materialId
     * @return 
     */
    boolean materialExistsById(int materialId);
}
