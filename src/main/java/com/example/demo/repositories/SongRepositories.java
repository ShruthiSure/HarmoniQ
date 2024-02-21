package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Song;

public interface SongRepositories extends JpaRepository<Song , Integer>{

	Song findByName(String name);

}
