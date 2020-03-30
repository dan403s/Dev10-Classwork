/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.service;

import com.dvb.imaginapainting.entities.FavoriteList;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public interface FavoriteListService {

    // CRUD for FavoriteList objects
    /**
     * Find FavoriteList by favoriteListId
     * @param favoriteListId
     * @return 
     */
    FavoriteList findById(int favoriteListId);

    /**
     * Find all FavoriteList objects
     * @return 
     */
    List<FavoriteList> findAll();

    /**
     * Save FavoriteList
     * @param favoriteList
     * @return 
     */
    FavoriteList save(FavoriteList favoriteList);

    /**
     * Delete FavoriteList by favoriteListId
     * @param favoriteListId
     */
    void deleteById(int favoriteListId);

    /**
     * Count FavoriteList objects
     * @return 
     */
    long count();

    /**
     * Check if FavoriteList exists by favoriteListId
     * @param favoriteListId
     * @return 
     */
    boolean existsById(int favoriteListId);

}
