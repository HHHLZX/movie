package com.example.movie.api;

import com.example.movie.entity.Movie;
import com.example.movie.repository.MovieRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class ApiMovieController {
    private final MovieRepository movieRepository;

    public ApiMovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/api/movie/list")
    public HashMap<String, Object> index() {
        List<Movie> all = this.movieRepository.findAll();
        HashMap<String, Object> res = new HashMap<>();
        res.put("code", 0);
        res.put("message", "success");
        res.put("data", all);
        return res;
    }

    @GetMapping("/api/movie/detail")
    public HashMap<String, Object> detail(@RequestParam int id) {
        Optional<Movie> byId = this.movieRepository.findById(id);
        Movie movie = byId.orElse(null);
        HashMap<String, Object> res = new HashMap<>();
        res.put("code", 0);
        res.put("message", "success");
        res.put("data", movie);
        return res;
    }
}
