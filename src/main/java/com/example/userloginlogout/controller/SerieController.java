package com.example.userloginlogout.controller;

import com.example.userloginlogout.models.Serie;
import com.example.userloginlogout.models.User;
import com.example.userloginlogout.repository.UserRepository;
import com.example.userloginlogout.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("userspace/user/{id}")
public class SerieController {
    @Autowired
    private SerieService serieservice;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/series")
    public String listAllSeries(@PathVariable("id") Integer id,Model model) {

        model.addAttribute("listofseriesbyId", serieservice.showSeriesById(id));
        return "list_series";
    }

    @GetMapping("/series/new")
    public String AddNewSerie(Model model)
    {
        model.addAttribute("serie",new Serie());
        return "add_serie";
    }

    @PostMapping("/series/new/save")
    public String SaveNewSeries(@PathVariable("id") Integer id, Serie serie)
    {
        serie.setUser(userRepository.findUserById(id));
        serieservice.saveSerie(serie);
        return "redirect:/userspace/user/{id}/series";
    }

    @GetMapping("/series/edit/{sid}")
    public String editSeries(@PathVariable("sid") Integer sid,@PathVariable("id") Integer id, Model model)
    {
        model.addAttribute("serie", serieservice.getSerieById(sid));
        //List<Event> listeEvent = serieservice.getSerieById(id).getEvents();
        //model.addAttribute("listEvent",listeEvent);
        return "edit_serie";
    }
    @PostMapping("/series/edit/{sid}/save")
    public String SaveEditEvent(@PathVariable("id") Integer id, @PathVariable("sid") Integer sid,Serie serie)
    {
        serieservice.saveSerie(serie);
        return "redirect:/userspace/user/{id}/series";
    }
    //Delete a specific serie.
}
