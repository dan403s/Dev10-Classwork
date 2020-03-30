/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.service;

import com.dvb.imaginapainting.data.CategoryRepository;
import com.dvb.imaginapainting.data.MaterialRepository;
import com.dvb.imaginapainting.data.MediumRepository;
import com.dvb.imaginapainting.data.StateRepository;
import com.dvb.imaginapainting.data.StyleRepository;
import com.dvb.imaginapainting.data.SubjectRepository;
import com.dvb.imaginapainting.entities.Category;
import com.dvb.imaginapainting.entities.Material;
import com.dvb.imaginapainting.entities.Medium;
import com.dvb.imaginapainting.entities.State;
import com.dvb.imaginapainting.entities.Style;
import com.dvb.imaginapainting.entities.Subject;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniel Bart
 */
@Service
public class LookupServiceImpl implements LookupService {
    
    @Autowired
    StateRepository stateRepository;
    
    @Autowired
    CategoryRepository categoryRepository;
    
    @Autowired
    StyleRepository styleRepository;
    
    @Autowired
    SubjectRepository subjectRepository;
    
    @Autowired
    MediumRepository mediumRepository;
    
    @Autowired
    MaterialRepository materialRepository;

    // CRUD for State objects
    @Override
    public State findStateById(String stateId) {
        return stateRepository.findById(stateId).orElse(null);
    }

    @Override
    public List<State> findAllStates() {
        return stateRepository.findAll();
    }

    @Override
    public State saveState(State state) {
        return stateRepository.save(state);
    }

    @Override
    public void deleteStateById(String stateId) {
        stateRepository.deleteById(stateId);
    }

    @Override
    public long countStates() {
        return stateRepository.count();
    }

    @Override
    public boolean stateExistsById(String stateId) {
        return stateRepository.existsById(stateId);
    }

    // CRUD for Category objects
    @Override
    public Category findCategoryById(int categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(int categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public long countCategories() {
        return categoryRepository.count();
    }

    @Override
    public boolean categoryExistsById(int categoryId) {
        return categoryRepository.existsById(categoryId);
    }

    // CRUD for Style objects
    @Override
    public Style findStyleById(int styleId) {
        return styleRepository.findById(styleId).orElse(null);
    }

    @Override
    public List<Style> findAllStyles() {
        return styleRepository.findAll();
    }

    @Override
    public Style saveStyle(Style style) {
        return styleRepository.save(style);
    }

    @Override
    public void deleteStyleById(int styleId) {
        styleRepository.deleteById(styleId);
    }

    @Override
    public long countStyles() {
        return styleRepository.count();
    }

    @Override
    public boolean styleExistsById(int styleId) {
        return styleRepository.existsById(styleId);
    }

    // CRUD for Subject objects
    @Override
    public Subject findSubjectById(int subjectId) {
        return subjectRepository.findById(subjectId).orElse(null);
    }

    @Override
    public List<Subject> findAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public void deleteSubjectById(int subjectId) {
        subjectRepository.deleteById(subjectId);
    }

    @Override
    public long countSubjects() {
        return subjectRepository.count();
    }

    @Override
    public boolean subjectExistsById(int subjectId) {
        return subjectRepository.existsById(subjectId);
    }

    // CRUD for Medium objects
    @Override
    public Medium findMediumById(int mediumId) {
        return mediumRepository.findById(mediumId).orElse(null);
    }

    @Override
    public List<Medium> findAllMediums() {
        return mediumRepository.findAll();
    }

    @Override
    public Medium saveMedium(Medium medium) {
        return mediumRepository.save(medium);
    }

    @Override
    public void deleteMediumById(int mediumId) {
        mediumRepository.deleteById(mediumId);
    }

    @Override
    public long countMediums() {
        return mediumRepository.count();
    }

    @Override
    public boolean mediumExistsById(int mediumId) {
        return mediumRepository.existsById(mediumId);
    }

    // CRUD for Material objects
    @Override
    public Material findMaterialById(int materialId) {
        return materialRepository.findById(materialId).orElse(null);
    }

    @Override
    public List<Material> findAllMaterials() {
        return materialRepository.findAll();
    }

    @Override
    public Material saveMaterial(Material material) {
        return materialRepository.save(material);
    }

    @Override
    public void deleteMaterialById(int materialId) {
        materialRepository.deleteById(materialId);
    }

    @Override
    public long countMaterials() {
        return materialRepository.count();
    }

    @Override
    public boolean materialExistsById(int materialId) {
        return materialRepository.existsById(materialId);
    }
    
}
