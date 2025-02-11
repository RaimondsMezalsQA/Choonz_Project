package com.qa.choonz.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.TrackDTO;

@SpringBootTest
@ActiveProfiles	("test")
public class TrackServiceTest {
	@Autowired
	private TrackService service;
	
	@MockBean
	private TrackRepository repo;
	
	private ModelMapper mapper=new ModelMapper();
	
	private TrackDTO mapToDTO(Track track) {
		return this.mapper.map(track,TrackDTO.class);
	}
	//class resources
	private final Track testTrack1=new Track(null, "Track 1",null, null, 200L,"lyrics 1");
	private final Track testTrack2=new Track(null, "Track 2",null, null, 200L,"lyrics 2");

	
	private final List<Track> testList=List.of(testTrack1,testTrack2); 
	
	@Test
	void testCreate() throws Exception{
		Track toCreate=new Track(null, "Track",null, null, 200L,"lyrics");
		Track created=new Track(5L,"Track",null, null, 200L,"lyrics");

		
		when(this.repo.save(toCreate)).thenReturn(created);
		
		assertThat(this.service.create(toCreate)).isEqualTo(this.mapToDTO(created));
		verify(this.repo,times(1)).save(toCreate);
	}
	
	@Test
	void testReadAll() throws Exception{
		List<TrackDTO> testListAsDtos=testList.stream().map(this::mapToDTO).collect(Collectors.toList());
		when(this.repo.findAll()).thenReturn(testList);
		assertThat(this.service.read()).isEqualTo(testListAsDtos);
	}
	
	@Test
	void testReadById() throws Exception{
		Long id=1L;
		when(this.repo.findById(id)).thenReturn(Optional.of(testTrack1));
		assertThat(this.service.read(id)).isEqualTo(this.mapToDTO(testTrack1));
		verify(this.repo,times(1)).findById(id);
	}
	
	@Test
	void testUpdate() throws Exception{
		Long id=1L;
		Track updated=new Track(1L,"Track 1 Updated",null, null, 200L,"lyrics updated");

		TrackDTO updatedAsDto=this.mapToDTO(updated);
		when(this.repo.findById(id)).thenReturn(Optional.of(testTrack1));
		when(this.repo.save(updated)).thenReturn(updated);
		assertThat(this.service.update(updated, id)).isEqualTo(updatedAsDto);
		verify(this.repo,times(1)).findById(id);
		verify(this.repo,times(1)).save(updated);
	}
	
	@Test
	void testDeleteFail() throws Exception{
		Long id=1L;
		when(this.repo.existsById(id)).thenReturn(true);
		assertThat(this.service.delete(id)).isFalse();
		verify(this.repo,times(1)).existsById(id);
	}
	@Test
	void testDeletePass() throws Exception{
		Long id=1L;
		when(this.repo.existsById(id)).thenReturn(false);
		assertThat(this.service.delete(id)).isTrue();
		verify(this.repo,times(1)).existsById(id);
	}
  
@Test
	void testReadTracksByArtists() throws Exception{
		Long id=1L;
		List<Track> tracksByArtist = new ArrayList<Track>();
		tracksByArtist.add(testTrack1);
		List<TrackDTO> tracksByArtistAsDtos=tracksByArtist.stream().map(this::mapToDTO).collect(Collectors.toList());
		when(this.repo.findTracksInArtists(id)).thenReturn(tracksByArtist);
		assertThat(this.service.findTracksInArtists(id)).isEqualTo(tracksByArtistAsDtos);
		verify(this.repo,times(1)).findTracksInArtists(id);
	}
  
	void testReadTracksByGenre() throws Exception{
		Long id=1L;
		List<Track> tracksByGenre=List.of(testTrack1,testTrack2);
		List<TrackDTO> tracksByGenreAsDtos=tracksByGenre.stream().map(this::mapToDTO).collect(Collectors.toList());
		when(this.repo.findTracksInGenres(id)).thenReturn(tracksByGenre);
		assertThat(this.service.findTracksInGenres(id)).isEqualTo(tracksByGenreAsDtos);
		verify(this.repo,times(1)).findTracksInGenres(id);
	}
	@Test
	void testReadTracksInAlbum() throws Exception{
		Long id=1L;
		List<Track> tracksByAlbum=List.of(testTrack1,testTrack2);
		List<TrackDTO> tracksByAlbumAsDtos=tracksByAlbum.stream().map(this::mapToDTO).collect(Collectors.toList());
		when(this.repo.findTracksInAlbums(id)).thenReturn(tracksByAlbum);
		assertThat(this.service.findTracksInAlbums(id)).isEqualTo(tracksByAlbumAsDtos);
		verify(this.repo,times(1)).findTracksInAlbums(id);
	}
	
	@Test
	void testReadTracksInPlaylist() throws Exception{
		Long id=1L;
		List<Track> tracksByPlaylist=List.of(testTrack1,testTrack2);
		List<TrackDTO> tracksByPlaylistAsDtos=tracksByPlaylist.stream().map(this::mapToDTO).collect(Collectors.toList());
		when(this.repo.findTracksInPlaylists(id)).thenReturn(tracksByPlaylist);
		assertThat(this.service.findTracksInPLaylists(id)).isEqualTo(tracksByPlaylistAsDtos);
		verify(this.repo,times(1)).findTracksInPlaylists(id);
	}
}
