package com.qa.choonz.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.qa.choonz.exception.PlaylistNotFoundException;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.repository.PlaylistRepository;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.utils.BeanUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlaylistService {

	private final PlaylistRepository repo;
	private final ModelMapper mapper;

	private PlaylistDTO mapToDTO(Playlist playlist) {
		return this.mapper.map(playlist, PlaylistDTO.class);
	}

	public PlaylistDTO create(Playlist playlist) {
		Playlist created = this.repo.save(playlist);
		return this.mapToDTO(created);
	}

	public List<PlaylistDTO> read() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public PlaylistDTO read(long id) {
		Playlist found = this.repo.findById(id).orElseThrow(PlaylistNotFoundException::new);
		return this.mapToDTO(found);
	}

	public PlaylistDTO update(Playlist playlist, long id) {
		Playlist toUpdate = this.repo.findById(id).orElseThrow(PlaylistNotFoundException::new);
		BeanUtils.mergeNotNull(playlist,toUpdate);
		Playlist updated = this.repo.save(toUpdate);
		return this.mapToDTO(updated);
	}

	public boolean delete(long id) {
		try {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
		} catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			return false;
		}
	}


}
