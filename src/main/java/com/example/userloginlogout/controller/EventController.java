package com.example.userloginlogout.controller;

import com.example.userloginlogout.models.Event;
import com.example.userloginlogout.service.EventService;
import com.example.userloginlogout.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("userspace/user/{id}/series")
public class EventController {
    @Autowired
    private SerieService sservice;

    @Autowired
    private EventService eservice;

    @GetMapping("/")
    public String listofserie()
    {
        return "list_series";
    }

    @GetMapping("/{sid}")
    public String showEventsOfSpecificSerie(@PathVariable("sid") Integer id, Model model)
    {
        List<Event> listeEvent = eservice.findAllByIdEvent(id);
        model.addAttribute("list_specified_events",listeEvent);
        return "list_events";
    }

    @GetMapping("/{sid}/event/new")
    public String addNewEventToASerie(@PathVariable("sid") Integer id,Model model)
    {
        model.addAttribute("event",new Event());
        return "add_Event";
    }

    @PostMapping("/{sid}/event/new/save")
    public String SaveNewEvent(@PathVariable("sid") Integer id, Event event)
    {
        event.setSerie(sservice.getSerieById(id));
        eservice.saveEvent(event);
        return "redirect:/userspace/user/{id}/series/{sid}";
    }

    @GetMapping("/{sid}/event/edit/{eid}")
    public String EditEvent(@PathVariable("sid") Integer id, @PathVariable("eid") Integer eid,Model model)
    {
        model.addAttribute("event",eservice.getEventById(eid));
        return "edit_event";
    }
    @PostMapping("/{sid}/event/edit/{eid}/save")
    public String SaveEditEvent(@PathVariable("sid") Integer id, @PathVariable("eid") Integer eid,Event event)
    {
        event.setSerie(sservice.getSerieById(id));
        eservice.saveEvent(event);
        return "redirect:/userspace/user/{id}/series/{sid}";
    }

    @GetMapping("/{sid}/event/delete/{eid}")
    public String DeleteEvent(@PathVariable("sid") Integer id, @PathVariable("eid") Integer eid)
    {
        eservice.deleteEvent(eid);
        return "redirect:/userspace/user/{id}/series/{sid}";
    }


}
