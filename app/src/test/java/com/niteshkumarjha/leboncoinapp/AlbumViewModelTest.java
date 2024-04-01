package com.niteshkumarjha.leboncoinapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.niteshkumarjha.leboncoinapp.model.Album;
import com.niteshkumarjha.leboncoinapp.ui.viewmodel.AlbumViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.app.Application;

@RunWith(PowerMockRunner.class)
@PrepareForTest({VolleyLog.class})
public class AlbumViewModelTest {

    @Mock
    RequestQueue mockRequestQueue;

    private Application application;

    @Before
    public void setUp() {
        application = mock(Application.class);
    }

    @Test
    public void fetchData_fetchesAndSetsData() throws Exception {
        PowerMockito.mockStatic(VolleyLog.class);
        AlbumViewModel viewModel = new AlbumViewModel(application);
        doNothing().when(viewModel).log(anyString(), anyString());

        ArgumentCaptor<StringRequest> stringRequestCaptor = ArgumentCaptor.forClass(StringRequest.class);
        viewModel.fetchData(mockRequestQueue);
        Mockito.verify(mockRequestQueue).add(stringRequestCaptor.capture());

        String mockJsonResponse = "[{\"albumId\": 1, \"id\": 1, \"title\": \"accusamus beatae ad facilis cum similique qui sunt\", \"url\": \"https://via.placeholder.com/600/92c952\", \"thumbnailUrl\": \"https://via.placeholder.com/150/92c952\"}," + "{\"albumId\": 1, \"id\": 2, \"title\": \"reprehenderit est deserunt velit ipsam\", \"url\": \"https://via.placeholder.com/600/771796\", \"thumbnailUrl\": \"https://via.placeholder.com/150/771796\"}," + "{\"albumId\": 1, \"id\": 3, \"title\": \"officia porro iure quia iusto qui ipsa ut modi\", \"url\": \"https://via.placeholder.com/600/24f355\", \"thumbnailUrl\": \"https://via.placeholder.com/150/24f355\"}]";
        StringRequest capturedRequest = stringRequestCaptor.getValue();

        when(capturedRequest.getBody()).thenReturn(mockJsonResponse.getBytes());

        List<Album> expectedAlbums = new ArrayList<>();
        expectedAlbums.add(new Album(1, 1, "accusamus beatae ad facilis cum similique qui sunt", "https://via.placeholder.com/600/92c952", "https://via.placeholder.com/150/92c952"));
        expectedAlbums.add(new Album(2, 1, "reprehenderit est deserunt velit ipsam", "https://via.placeholder.com/600/771796", "https://via.placeholder.com/150/771796"));
        expectedAlbums.add(new Album(3, 1, "officia porro iure quia iusto qui ipsa ut modi", "https://via.placeholder.com/600/24f355", "https://via.placeholder.com/150/24f355"));

        LiveData<List<Album>> albumListLiveData = viewModel.getAlbumList();
        Observer<List<Album>> observer = mock(Observer.class);
        albumListLiveData.observeForever(observer);
        Mockito.verify(observer).onChanged(expectedAlbums);
    }

    @Test
    public void fetchData_handlesError() throws Exception {
        AlbumViewModel viewModel = new AlbumViewModel(application);

        ArgumentCaptor<StringRequest> stringRequestCaptor = ArgumentCaptor.forClass(StringRequest.class);
        viewModel.fetchData(mockRequestQueue);
        Mockito.verify(mockRequestQueue).add(stringRequestCaptor.capture());

        StringRequest capturedRequest = stringRequestCaptor.getValue();
        capturedRequest.deliverError(new VolleyError("Network Error"));

        LiveData<List<Album>> albumListLiveData = viewModel.getAlbumList();
        Observer<List<Album>> observer = mock(Observer.class);
        albumListLiveData.observeForever(observer);
        Mockito.verifyNoInteractions(observer);
    }
}
