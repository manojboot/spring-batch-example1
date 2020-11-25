package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Movies;

public interface MoviesRepository extends JpaRepository<Movies,Integer>{

}
