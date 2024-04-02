package com.niteshkumarjha.leboncoinapp;

import static junit.framework.TestCase.assertEquals;

import com.niteshkumarjha.leboncoinapp.model.Album;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class AlbumTest {

    @Test
    public void testAlbumGettersAndSetters() {
        Album album = new Album();
        album.setId(1);
        album.setTitle("accusamus beatae ad facilis cum similique qui sunt");
        album.setUrl("https://via.placeholder.com/600/92c952");
        album.setThumbNailUrl("https://via.placeholder.com/150/92c952");

        assertEquals(1, album.getId());
        assertEquals("accusamus beatae ad facilis cum similique qui sunt", album.getTitle());
        assertEquals("https://via.placeholder.com/600/92c952", album.getUrl());
        assertEquals("https://via.placeholder.com/150/92c952", album.getThumbNailUrl());
    }
}