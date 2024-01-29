package com.example.eventhub.view.OrganizerHomePage;

import androidx.lifecycle.ViewModel;

import com.example.eventhub.dao.EventDAO;
import com.example.eventhub.dao.OrganizerDAO;
import com.example.eventhub.memorydao.EventDAOMemory;
import com.example.eventhub.memorydao.OrganizerDAOMemory;

public class OrganizerHomePageViewModel extends ViewModel {
    private OrganizerHomePagePresenter presenter;

    public OrganizerHomePageViewModel() {
        presenter = new OrganizerHomePagePresenter(new OrganizerDAOMemory(), new EventDAOMemory());
    }

    public OrganizerHomePagePresenter getPresenter() {
        return presenter;
    }
}
