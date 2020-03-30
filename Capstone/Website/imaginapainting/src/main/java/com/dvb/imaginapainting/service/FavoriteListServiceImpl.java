/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.service;

import com.dvb.imaginapainting.data.FavoriteListRepository;
import com.dvb.imaginapainting.entities.FavoriteList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniel Bart
 */
@Service
public class FavoriteListServiceImpl implements FavoriteListService {
    
    @Autowired
    FavoriteListRepository favoriteListRepository;

    @Override
    public FavoriteList findById(int favoriteListId) {
        return favoriteListRepository.findById(favoriteListId).orElse(null);
    }

    @Override
    public List<FavoriteList> findAll() {
        return favoriteListRepository.findAll();
    }

    @Override
    public FavoriteList save(FavoriteList favoriteList) {
        return favoriteListRepository.save(favoriteList);
    }

    @Override
    public void deleteById(int favoriteListId) {
        favoriteListRepository.deleteById(favoriteListId);
    }

    @Override
    public long count() {
        return favoriteListRepository.count();
    }

    @Override
    public boolean existsById(int favoriteListId) {
        return favoriteListRepository.existsById(favoriteListId);
    }

    
    
}
