package com.niteshkumarjha.leboncoinapp;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

import android.content.Context;

import com.niteshkumarjha.leboncoinapp.model.Album;
import com.niteshkumarjha.leboncoinapp.ui.adapter.AlbumAdapter;
import com.niteshkumarjha.leboncoinapp.ui.viewmodel.AlbumViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AlbumAdapterTest {

    @Mock
    private Context mockContext;

    @Mock
    private AlbumViewModel mockViewModel;

    @Mock
    private List<Album> mockAlbumList;

    private AlbumAdapter adapter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        adapter = new AlbumAdapter(mockContext, mockViewModel, mockAlbumList);
    }


    @Test
    public void getItemCount_WithEmptyList_ReturnsZero() {
        when(mockAlbumList.size()).thenReturn(0);
        int itemCount = adapter.getItemCount();
        assertEquals(0, itemCount);
    }
}

