package com.example.movie.controller;

import com.example.movie.entity.Movie;
import com.example.movie.entity.repository.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.List;

@Controller
public class MovieController {
    private MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/movie/create")
    public String create() {
        return "movie/create";
    }

    @PostMapping("/movie/save")
//    @ResponseBody
    public String save(
            @RequestParam String movieName,
            @RequestParam String movieImage,
            @RequestParam String movieBoxOffice,
            @RequestParam String movieLanguage,
            @RequestParam Date movieDate,
            @RequestParam int movieTimeTotal,
            @RequestParam float movieScore,
            @RequestParam String movieType,
            @RequestParam String movieDesc
    ) {
        //接收数据
        Movie movie = new Movie();
        movie.setMovieName(movieName);
        movie.setMovieImage(movieImage);
        movie.setMovieBoxOffice(movieBoxOffice);
        movie.setMovieLanguage(movieLanguage);
        movie.setMovieDate(movieDate);
        movie.setMovieTimeTotal(movieTimeTotal);
        movie.setMovieScore(movieScore);
        movie.setMovieType(movieType);
        movie.setMovieDesc(movieDesc);

        //存储数据
        this.movieRepository.save(movie);
        return "redirect:/movie/index";
    }

    @GetMapping("/movie/index")
    public String index(Model model) {
        // 查询数据
        List<Movie> movies = this.movieRepository.findAll();
// 分配数据到html页面
        model.addAttribute("movies", movies);
        return "movie/index";
    }

    @GetMapping("/movie/delete")
    public String delete(
            @RequestParam int id
    ) {
        this.movieRepository.deleteById(id);
        return "redirect:/movie/index";
    }
}
