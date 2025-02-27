package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Songs;

@Repository
public interface songsRepository extends JpaRepository<Songs,Long> {
    List<Songs> findBySong(String song);
    List<Songs> findByLanguage(String language);
    List<Songs> findByCategory(String category);
    List<Songs> findByArtist(String artist);
}
