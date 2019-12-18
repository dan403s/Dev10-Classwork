/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.mp3library.dao;

import com.dvb.mp3library.dto.MP3;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Daniel Bart
 */
public class MP3LibraryDaoTest {
    
    private MP3LibraryDao dao = new MP3LibraryDaoFileImpl();
    
    public MP3LibraryDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception {
        List<MP3> mp3List = dao.listAllMp3s();
        for(MP3 mp3 : mp3List) {
            dao.deleteMp3(mp3.getTitle());
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addMp3 method, of class MP3LibraryDao.
     */
    @Test
    public void testAddListMp3Details() throws Exception {
        MP3 mp3 = new MP3("Dreams");
        mp3.setReleaseDate("1995");
        mp3.setAlbum("Dreams");
        mp3.setArtistName("Van Halen");
        mp3.setGenre("Rock;");
        mp3.setUserRatingNote("Awesome album");
        
        dao.addMp3(mp3.getTitle(), mp3);
        
        MP3 fromDao = dao.listMp3Details(mp3.getTitle());
        
        assertEquals(mp3, fromDao);
    }

    /**
     * Test of deleteMp3 method, of class MP3LibraryDao.
     */
    @Test
    public void testDeleteMp3() throws Exception {
        MP3 mp31 = new MP3("Dreams");
        mp31.setReleaseDate("1995");
        mp31.setAlbum("Dreams");
        mp31.setArtistName("Van Halen");
        mp31.setGenre("Rock;");
        mp31.setUserRatingNote("Awesome album");
        
        dao.addMp3(mp31.getTitle(), mp31);
        
        MP3 mp32 = new MP3("Why Can't This Be Love");
        mp32.setReleaseDate("1995");
        mp32.setAlbum("Dreams");
        mp32.setArtistName("Van Halen");
        mp32.setGenre("Rock;");
        mp32.setUserRatingNote("Awesome album");
        
        dao.addMp3(mp32.getTitle(), mp32);
        
        dao.deleteMp3(mp31.getTitle());
        assertEquals(1, dao.listAllMp3s().size());
        assertNull(dao.listMp3Details(mp31.getTitle()));
        
        dao.deleteMp3(mp32.getTitle());
        assertEquals(0, dao.listAllMp3s().size());
        assertNull(dao.listMp3Details(mp32.getTitle()));
        
    }

    /**
     * Test of changeMp3 method, of class MP3LibraryDao.
     */
    @Test
    public void testChangeMp3() throws Exception {
        MP3 mp3 = new MP3("Dreams");
        mp3.setReleaseDate("1995");
        mp3.setAlbum("Dreams");
        mp3.setArtistName("Van Halen");
        mp3.setGenre("Rock;");
        mp3.setUserRatingNote("Awesome album");
        
        dao.addMp3(mp3.getTitle(), mp3);
        
        MP3 editedMp3 = new MP3("Dreams");
        editedMp3.setReleaseDate("2");
        editedMp3.setAlbum("2");
        editedMp3.setArtistName("2");
        editedMp3.setGenre("2;");
        editedMp3.setUserRatingNote("2");
        
        MP3 oldMp3 = dao.changeMp3(editedMp3.getTitle(), editedMp3);
        
        assertNotEquals(mp3, dao.listMp3Details(mp3.getTitle()));
        
        assertEquals(mp3, oldMp3);
    }

    /**
     * Test of listAllMp3s method, of class MP3LibraryDao.
     */
    @Test
    public void testListAllMp3s() throws Exception {
        MP3 mp31 = new MP3("Dreams");
        mp31.setReleaseDate("1995");
        mp31.setAlbum("Dreams");
        mp31.setArtistName("Van Halen");
        mp31.setGenre("Rock;");
        mp31.setUserRatingNote("Awesome album");
        
        dao.addMp3(mp31.getTitle(), mp31);
        
        MP3 mp32 = new MP3("Why Can't This Be Love");
        mp32.setReleaseDate("1995");
        mp32.setAlbum("Dreams");
        mp32.setArtistName("Van Halen");
        mp32.setGenre("Rock;");
        mp32.setUserRatingNote("Awesome album");
        
        dao.addMp3(mp32.getTitle(), mp32);
        
        assertEquals(2, dao.listAllMp3s().size());
        assertEquals(mp31, dao.listAllMp3s().get(1));
        assertEquals(mp32, dao.listAllMp3s().get(0));
    }

    /**
     * Test of listMp3Details method, of class MP3LibraryDao.
     */
    @Test
    public void testListMp3Details() throws Exception {
        // already tested
    }

    /**
     * Test of searchLibrary method, of class MP3LibraryDao.
     */
    @Test
    public void testSearchLibrary() throws Exception {
        MP3 mp31 = new MP3("Dreams");
        mp31.setReleaseDate("1995");
        mp31.setAlbum("Dreams");
        mp31.setArtistName("Van Halen");
        mp31.setGenre("Rock;");
        mp31.setUserRatingNote("Awesome album");
        
        dao.addMp3(mp31.getTitle(), mp31);
        
        MP3 mp32 = new MP3("Dreaming of You");
        mp32.setReleaseDate("1995");
        mp32.setAlbum("Dreams");
        mp32.setArtistName("Van Halen");
        mp32.setGenre("Rock;");
        mp32.setUserRatingNote("Awesome album");
        
        dao.addMp3(mp32.getTitle(), mp32);
        
        assertEquals(2, dao.searchLibrary("D").size());
    }
    
}
