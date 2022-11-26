package com.example.userloginlogout.service;

import com.example.userloginlogout.models.Serie;
import com.example.userloginlogout.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {
    @Autowired
    private SerieRepository srepo;

    public Serie saveSerie(Serie serie)
    {
        return (Serie) srepo.save(serie);
    }
    public List<Serie> showSeries()
    {
        return srepo.findAll();
    }
    public Serie getSerieById(int id)
    {
        return (Serie) srepo.findById(id).orElse(null);
    }
    public List<Serie> showSeriesById(int id)
    {
        return srepo.findSeriesByUser(id);
    }
    public String deleteProduct(int id)
    {
        srepo.deleteById(id);
        return "Serie deleted";
    }
}
