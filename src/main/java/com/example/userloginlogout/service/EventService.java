package com.example.userloginlogout.service;

import com.example.userloginlogout.models.Event;
import com.example.userloginlogout.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository erepo;

    public Event saveEvent(Event event)
    {

        return erepo.save(event);
    }
    public List<Event> showEvents()
    {

        return erepo.findAll();
    }
    public Event getEventById(int id)
    {

        return (Event) erepo.findById(id).orElse(null);
    }
    public String deleteEvent(int id)
    {
        erepo.deleteById(id);
        return "Event deleted";
    }
    public List<Event> findAllByIdEvent(int id)
    {
        return erepo.findEventsBySerieId(id);
    }

}
