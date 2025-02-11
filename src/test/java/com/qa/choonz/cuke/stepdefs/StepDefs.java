package com.qa.choonz.cuke.stepdefs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.qa.choonz.cuke.pom.PageAlbums;
import com.qa.choonz.cuke.pom.PageArtists;
import com.qa.choonz.cuke.pom.PageBase;
import com.qa.choonz.cuke.pom.PageGenres;
import com.qa.choonz.cuke.pom.PagePlaylists;
import com.qa.choonz.cuke.pom.PageTracks;
import com.qa.choonz.cuke.pom.PageUser;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@ActiveProfiles("test")
@SpringBootTest
@Sql(scripts = {"/Choonz_Project/src/test/resources/Choonz-schema.sql",
			"/Choonz_Project/src/test/resources/data.sql"
			},
	executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class StepDefs {
	
	private static RemoteWebDriver driver;
	
	private static PageBase base;
	private static PageUser userPage;
	private static PageArtists artistsPage;
	private static PageGenres genresPage;
	private static PagePlaylists playlistsPage;
	private static PageAlbums albumsPage;
	private static PageTracks tracksPage;

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chrome/chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Given("that I can navigate to {string}")
	public void that_i_can_navigate_to(String string) {
	    driver.get(string);
		
		base = PageFactory.initElements(driver, PageBase.class);
		userPage = PageFactory.initElements(driver, PageUser.class);
		artistsPage = PageFactory.initElements(driver, PageArtists.class);
		genresPage = PageFactory.initElements(driver, PageGenres.class);
		playlistsPage = PageFactory.initElements(driver, PagePlaylists.class);
		albumsPage = PageFactory.initElements(driver, PageAlbums.class);
		tracksPage = PageFactory.initElements(driver, PageTracks.class);
	}
	
	@Given("that I can genre {string}")
	public void that_i_can_genre(String string) {
	    driver.get(string);
		
		base = PageFactory.initElements(driver, PageBase.class);
		userPage = PageFactory.initElements(driver, PageUser.class);
		artistsPage = PageFactory.initElements(driver, PageArtists.class);
		base.waitUntilPageLoad(driver);
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@When("I go home")
	public void i_go_home() {
		hang();
	    userPage.navHome();
	}
	
	@When("I toggle the navbar")
	public void i_toggle_the_navbar() {
		hang();
		base.toggleNav();
	}

	@When("I click the account button")
	public void i_click_the_account_button() {
		base.waitUntilNavExpand(driver);
	    base.navLogin();
	}
	
	@When("I navigate to the artists page")
	public void i_navigate_to_the_artists_page() {
		base.waitUntilNavExpand(driver);
	    base.navArtists();
	}

	@When("I dismiss the alert")
	public void i_cancel_the_alert() {
	    driver.switchTo().alert().dismiss();
	}
	
	@When("I accept the alert")
	public void i_accept_the_alert() {
		driver.switchTo().alert().accept();
	}

	@When("I enter a username of {string} in the signup form")
	public void i_enter_a_username_of_in_the_signup_form(String string) {
		hang();
	    userPage.inputSignUpUsername(string);
	}
	
	@When("I enter a username of {string} in the login form")
	public void i_enter_a_username_of_in_the_login_form(String string) {
		hang();
	    userPage.inputLoginUsername(string);
	}

	@When("I enter a password of {string} in the signup form")
	public void i_enter_a_password_of_in_the_signup_form(String string) {
	    userPage.inputSignUpPassword(string);
	}
	
	@When("I enter a password of {string} in the login form")
	public void i_enter_a_password_of_in_the_login_form(String string) {
	    userPage.inputLoginPassword(string);
	}

	@When("I submit the sign up form")
	public void i_submit_the_sign_up_form() {
	    userPage.clickSignUpSubmit();
	}
	
	@When("I click the sign in link")
	public void i_click_the_sign_in_link() {
		hang();
	    userPage.navLogin();
		
	}

	@When("I submit the login form")
	public void i_submit_the_login_form() {
	    userPage.clickLoginSubmit();
	}

	@When("I click the create artist button")
	public void i_click_the_create_artist_button() {
		hang();
	    artistsPage.clickCreateArtist();
	}

	@When("I enter the create artist details:")
	public void i_enter_the_create_artist_details(Map<String, String> dataTable) {
	    
		String
			artistName;
		
		artistName = dataTable.get("artist name");
		
		artistsPage.inputCreateArtistName(artistName);
		
	}

	@When("I submit the create artist form")
	public void i_submit_the_create_artist_form() {
		artistsPage.clickCreateArtistSubmit();
	}
	
	@When("I select an artist")
	public void i_select_an_artist() {
		hang();
	    artistsPage.clickArtistName();
	}
	
	@When("I select a genre")
	public void i_select_a_genre() {
		hang();
	    genresPage.clickTextGenreName();
	}
	
	@When("I select a playlist")
	public void i_select_a_playlist() {
		hang();
	    playlistsPage.clickPlaylistName();
	}

	@When("I click the update artist button")
	public void i_click_the_update_artist_button() {
		hang();
	    artistsPage.clickUpdateArtist();
	}

	@When("I enter the update artist details:")
	public void i_enter_the_update_artist_details(Map<String, String> dataTable) {

		String
			artistUpdatedName;
		
		artistUpdatedName = dataTable.get("artist updated name");
		
		artistsPage.inputUpdateArtistName(artistUpdatedName);
	}

	@When("I submit the update artist form")
	public void i_submit_the_update_artist_form() {
	    artistsPage.clickUpdateArtistSubmit();
	}
	
	@When("I click the delete user button")
	public void i_click_the_delete_user_button() {
		hang();
	    userPage.clickDeleteUser();
	}
	
	@When("I click the delete artist button")
	public void i_click_the_delete_artist_button() {
	    hang();
	    artistsPage.clickDeleteArtist();
	}
	
	@When("I navigate to the genres page")
	public void i_navigate_to_the_genres_page() {
		base.waitUntilNavExpand(driver);
	    base.navGenres();
	}
	
	@When("I click the create genre button")
	public void i_click_the_create_genre_button() {
	    hang();
	    genresPage.clickCreateGenreButton();
	}
	
	@When("I enter the create genre details:")
	public void i_enter_the_create_genre_details(Map<String, String> dataTable) {
		hang();
		String
			genreName,
			genreDescription;
			
		genreName = dataTable.get("genre name");
		genreDescription = dataTable.get("genre description");
		
		genresPage.inputCreateGenreName(genreName);
		genresPage.inputCreateGenreDescription(genreDescription);
	}
	
	@When("I submit the create genre form")
	public void i_submit_the_create_genre_form() {
	    genresPage.clickCreateGenreSubmitButton();
	}
	
	@When("I submit the update genre form")
	public void i_submit_the_update_genre_form() {
	    genresPage.clickUpdateGenreButtonSubmit();
	}
	
	@When("I click the update genre button")
	public void i_click_the_update_genre_button() {
	    hang();
		genresPage.clickUpdateGenreButton();
	}
	
	@When("I enter the update genre details:")
	public void i_enter_the_update_genre_details(Map<String, String> dataTable) {
	    hang();
		String
			genreUpdatedName,
			genreUpdatedDescription;
			
		genreUpdatedName = dataTable.get("genre updated name");
		genreUpdatedDescription = dataTable.get("genre updated description");
		
		genresPage.inputUpdateGenreName(genreUpdatedName);
		genresPage.inputUpdateGenreDescription(genreUpdatedDescription);
	}
	
	@When("I click the delete genre button")
	public void i_click_the_delete_genre_button() {
		hang();
	    genresPage.clickDeleteGenre();
	}
	
	@When("I navigate to the playlists page")
	public void i_navigate_to_the_playlists_page() {
	    hang();
	    base.navPlaylists();
	}
	
	@When("I click the create playlist button")
	public void i_click_the_create_playlist_button() {
	    hang();
	    playlistsPage.clickCreatePlaylistButton();
	}
	
	@When("I click the update playlist button")
	public void i_click_the_update_playlist_button() {
	    hang();
	    playlistsPage.clickUpdatePlaylistButton();
	}
	

	@When("I click the delete playlist button")
	public void i_click_the_delete_playlist_button() {
	    hang();
	    playlistsPage.clickDeletePlaylistButton();
	}
	
	@When("I enter the create playlist details:")
	public void i_enter_the_create_playlist_details(Map<String, String> dataTable) {
	    hang();
		String
			playlistName,
			playlistArtwork,
			playlistDescription;
			
		playlistName = dataTable.get("playlist name");
		playlistArtwork = dataTable.get("playlist artwork");
		playlistDescription = dataTable.get("playlist description");
		
		playlistsPage.inputCreatePlaylistName(playlistName);
		playlistsPage.inputCreatePlaylistArtwork(playlistArtwork);
		playlistsPage.inputCreatePlaylistDescription(playlistDescription);
	}
	
	@When("I enter the update playlist details:")
	public void i_enter_the_update_playlist_details(Map<String, String> dataTable) {
	    hang();
		String
			playlistUpdatedName,
			playlistUpdatedArtwork,
			playlistUpdatedDescription;
			
		playlistUpdatedName = dataTable.get("playlist updated name");
		playlistUpdatedArtwork = dataTable.get("playlist updated artwork");
		playlistUpdatedDescription = dataTable.get("playlist updated description");
		
		playlistsPage.inputUpdatePlaylistName(playlistUpdatedName);
		playlistsPage.inputUpdatePlaylistArtwork(playlistUpdatedArtwork);
		playlistsPage.inputUpdatePlaylistDescription(playlistUpdatedDescription);
	}
	
	@When("I submit the create playlist form")
	public void i_submit_the_create_playlist_form() {
	    playlistsPage.clickCreatePlaylistSubmitButton();
	}

	@When("I submit the update playlist form")
	public void i_submit_the_update_playlist_form() {
	    playlistsPage.clickUpdatePlaylistSubmitButton();;
	}
	
	@When("I navigate to the albums page")
	public void i_navigate_to_the_albums_page() {
	    base.waitUntilNavExpand(driver);
	    base.navAlbums();
	}
	
	@When("I click the create album button")
	public void i_click_the_create_album_button() {
		hang();
		albumsPage.clickCreateAlbumButton();
	}
	
	@When("I enter the create album details:")
	public void i_enter_the_create_album_details(Map<String, String> dataTable) {
	    hang();
		String
			albumName,
			albumCover,
			albumArtistId,
			albumGenreId;
			
		albumName = dataTable.get("album name");
		albumCover = dataTable.get("album cover");
		albumArtistId = dataTable.get("album artist id");
		albumGenreId = dataTable.get("album genre id");
		
		albumsPage.inputCreateAlbumName(albumName);
		albumsPage.inputCreateAlbumCover(albumCover);
		albumsPage.inputCreateAlbumArtistId(albumArtistId);
		albumsPage.inputCreateAlbumGenreId(albumGenreId);
	}
	
	@When("I submit the create album form")
	public void i_submit_the_create_album_form() {
	    albumsPage.clickCreateAlbumSubmitButton();
	}
	
	@When("I select an Ablum")
	public void i_select_an_ablum() {
	    hang();
	    albumsPage.clickAlbumName();
	}
	
	@When("I click the update album button")
	public void i_click_the_update_album_button() {
	    hang();
	    albumsPage.clickUpdateAlbumButton();
	}
	
	@When("I enter the update album details:")
	public void i_enter_the_update_album_details(Map<String, String> dataTable) {
	    hang();
		String
			albumUpdatedName,
			albumUpdatedCover,
			albumUpdatedArtistId,
			albumUpdatedGenreId;
			
		albumUpdatedName = dataTable.get("album updated name");
		albumUpdatedCover = dataTable.get("album updated cover");
		albumUpdatedArtistId = dataTable.get("album updated artist id");
		albumUpdatedGenreId = dataTable.get("album updated genre id");
		
		albumsPage.inputUpdateAlbumName(albumUpdatedName);
		albumsPage.inputUpdateAlbumCover(albumUpdatedCover);
		albumsPage.inputUpdateAlbumArtistId(albumUpdatedArtistId);
		albumsPage.inputUpdateAlbumGenreId(albumUpdatedGenreId);
	}
	
	@When("I submit the update album form")
	public void i_submit_the_update_album_form() {
	    albumsPage.clickUpdateAlbumSubmitButton();
	}
	
	@When("I navigate to the user page")
	public void i_navigate_to_the_user_page() {
	    base.waitUntilNavExpand(driver);
	    base.navUser();
	}
	
	@When("I click the update user button")
	public void i_click_the_update_user_button() {
	    hang();
	    userPage.clickUpdateUserButton();
	}
	
	@When("I enter the update user details:")
	public void i_enter_the_update_user_details(Map<String, String> dataTable) {
	    hang();
	    String
	    	userUpdatedUsername,
	    	userUpdatedPassword;
	    
	    userUpdatedUsername = dataTable.get("username updated");
	    userUpdatedPassword = dataTable.get("password updated");
	    
	    userPage.inputUpdateUserUsername(userUpdatedUsername);
	    userPage.inputUpdateUserPassword(userUpdatedPassword);
	}
	
	@When("I submit the update user form")
	public void i_submit_the_update_user_form() {
	    userPage.clickUpdateUserSubmitButton();
	}
	
	@When("I click the delete album button")
	public void i_click_the_delete_album_button() {
	    hang();
	    albumsPage.clickDeleteAlbumButton();
	}
	
	@When("I navigate to the tracks page")
	public void i_navigate_to_the_tracks_page() {
	    base.waitUntilNavExpand(driver);
	    base.navTracks();
	}
	
	@When("I click the create track button")
	public void i_click_the_create_track_button() {
	    hang();
	    tracksPage.clickCreateTrackButton();
	}
	
	@When("I enter the create track details:")
	public void i_enter_the_create_track_details(Map<String, String> dataTable) {
	    hang();
	    String
	    	trackName,
	    	trackLyrics,
	    	trackDuration,
	    	trackAlbumId,
	    	trackPlaylistId;
	    
    	trackName = dataTable.get("track name");
    	trackLyrics = dataTable.get("track lyrics");
    	trackDuration = dataTable.get("track duration");
    	trackAlbumId = dataTable.get("track album id");
    	trackPlaylistId = dataTable.get("track playlist id");
    	
    	tracksPage.inputCreateTrackName(trackName);
    	tracksPage.inputCreateTrackLyrics(trackLyrics);
    	tracksPage.inputCreateTrackDuration(trackDuration);
    	tracksPage.inputCreateTrackAlbumId(trackAlbumId);
    	tracksPage.inputCreateTrackPlaylistId(trackPlaylistId);
	}
	
	@When("I submit the create track form")
	public void i_submit_the_create_track_form() {
	    tracksPage.clickCreateTrackSubmitButton();
	}
	
	@When("I select a Track")
	public void i_select_a_track() {
	    hang();
	    tracksPage.clickTrackNameText();
	}
	
	@When("I click the update track button")
	public void i_click_the_update_track_button() {
	    hang();
	    tracksPage.clickUpdateTrackButton();
	}
	
	@When("I enter the update track details:")
	public void i_enter_the_update_track_details(Map<String, String> dataTable) {
	    hang();
	    String
	    	trackUpdatedName,
	    	trackUpdatedLyrics,
	    	trackUpdatedDuration,
	    	trackUpdatedAlbumId,
	    	trackUpdatedPlaylistId;
	    
    	trackUpdatedName = dataTable.get("track updated name");
    	trackUpdatedLyrics = dataTable.get("track updated lyrics");
    	trackUpdatedDuration = dataTable.get("track updated duration");
    	trackUpdatedAlbumId = dataTable.get("track updated album id");
    	trackUpdatedPlaylistId = dataTable.get("track updated playlist id");
    	
    	tracksPage.inputUpdateTrackName(trackUpdatedName);
    	tracksPage.inputUpdateTrackLyrics(trackUpdatedLyrics);
    	tracksPage.inputUpdateTrackDuration(trackUpdatedDuration);
    	tracksPage.inputUpdateTrackAlbumId(trackUpdatedAlbumId);
    	tracksPage.inputUpdateTrackPlaylistId(trackUpdatedPlaylistId);
	}
	
	@When("I submit the update track form")
	public void i_submit_the_update_track_form() {
	    tracksPage.clickUpdateTrackSubmitButton();
	}
	
	@When("I click the delete track button")
	public void i_click_the_delete_track_button() {
	    hang();
	    tracksPage.clickDeleteTrackButton();
	}
	
	@Then("I can read {string} on the track page")
	public void i_can_read_on_the_track_page(String string) {
	    hang();
	    String expected = string;
	    String result = tracksPage.getDeleteTrackText();
	    assertEquals(expected,result);
	}
	
	@Then("I can read an updated track with the name {string}")
	public void i_can_read_an_updated_track_with_the_name(String string) {
	    hang();
	    String expected = string;
	    String result = tracksPage.getTrackNameSingle();
	    assertEquals(expected,result);
	}
	
	@Then("I can read a single track")
	public void i_can_read_a_single_track() {
	    hang();
	    assertNotNull(tracksPage.getTrackNameSingle());
	}
	
	@Then("I can read a list of tracks")
	public void i_can_read_a_list_of_tracks() {
	    hang();
	    assertNotNull(tracksPage.getTrackNameText());
	}
	
	@Then("I can read an track with the name {string}")
	public void i_can_read_an_track_with_the_name(String string) {
	    hang();
	    String expected = string;
	    String result = tracksPage.getTrackNameText();
	    assertEquals(expected,result);
	}
	
	@Then("I can read read {string} on the album page")
	public void i_can_read_read_on_the_album_page(String string) {
	    hang();
	    String expected = string;
	    String result = albumsPage.getDeleteAlbumText();
	    assertEquals(expected,result);
	}
	
	@Then("the user account is updated")
	public void the_user_account_is_updated() {
		hang();
		String expected = "User credentials updated.";
		String result = userPage.getUserUpdateText();
		assertEquals(expected,result);
	}
	
	@Then("I can read a single user")
	public void i_can_read_a_single_user() {
	    hang();
	    assertNotNull(userPage.getUserDisplayNameText());
	}
	
	@Then("I can read an updated album with the name {string}")
	public void i_can_read_an_updated_album_with_the_name(String string) {
	    hang();
	    String expected = string;
	    String result = albumsPage.getSingleAlbumName();
	    assertEquals(expected,result);
	}
	
	@Then("I can read a single Album")
	public void i_can_read_a_single_album() {
	    hang();
	    assertNotNull(albumsPage.getSingleAlbumName());
	}
	
	@Then("I can read an album with the name {string}")
	public void i_can_read_an_album_with_the_name(String string) {
	    hang();
	    String expected = string;
	    String result = albumsPage.getCreateAlbumText();
	    assertEquals(expected,result);
	}
	
	@Then("I can read a list of albums")
	public void i_can_read_a_list_of_albums() {
	    hang();
	    assertNotNull(albumsPage.getCreateAlbumText());
	}
	
	@Then("I can read a playlist with the name {string}")
	public void i_can_read_a_playlist_with_the_name(String string) {
	    hang();
	    String expected = string;
	    String result = playlistsPage.getCreatePlaylistText();
	    assertEquals(expected,result);
	}
	
	@Then("I can read an updated playlist with the name {string}")
	public void i_can_read_an_updated_playlist_with_the_name(String string) {
	    hang();
	    String expected = string;
	    String result = playlistsPage.getSinglePlaylistText();
	    assertEquals(expected,result);
	}

	@Then("I can read {string} on the genre page")
	public void i_can_read_on_the_genre_page(String string) {
	    hang();
	    String expected = string;
	    String result = genresPage.getSingleGenreNameText();
	    assertEquals(expected,result);
	}
	
	@Then("I can read {string} on the playlist page")
	public void i_can_read_on_the_playlist_page(String string) {
	    hang();
	    String expected = string;
	    String result = playlistsPage.getDeletePlaylistText();
	    assertEquals(expected,result);
	}
	
	@Then("I can read an updated genre with the name {string}")
	public void i_can_read_an_updated_genre_with_the_name(String string) {
	    hang();
	    String expected = string;
	    String result = genresPage.getSingleGenreNameText();
	    assertEquals(expected,result);
	}
	
	@Then("I can read a genre with the name {string}")
	public void i_can_read_a_genre_with_the_name(String string) {
	    hang();
	    String expected = string;
	    String result = genresPage.getTextGenreName();
	    assertEquals(expected,result);
	}
	
	@Then("I can read {string} on the artist page")
	public void i_can_read_on_the_artist_page(String string) {
	    hang();
	    String expected = string;
	    String result = artistsPage.getArtistDeletedText();
	    assertEquals(expected,result);
	}
	
	@Then("the user account is deleted")
	public void the_user_account_is_deleted() {
		hang();
		String expected = "User deleted.";
		String result = userPage.getUserDeleteText();
		assertEquals(expected,result);
	}
	
	@Then("I can read an updated artist with the name {string}")
	public void i_can_read_an_updated_artist_with_the_name(String string) {
		hang();
	    String expected = string;
	    String result = artistsPage.getArtistNameUpdated();
	    assertEquals(expected,result);
	}

	@Then("I can read a list of artists")
	public void i_can_read_a_list_of_artists() {
		hang();
		assertNotNull(artistsPage.getArtistName());
	}

	@Then("I can read a list of genres")
	public void i_can_read_a_list_of_genres() {
		hang();
		assertNotNull(genresPage.getTextGenreName());
	}

	@Then("I can read a list of playlists")
	public void i_can_read_a_list_of_playlists() {
		hang();
		assertNotNull(playlistsPage.getCreatePlaylistText());
	}
	
	@Then("I can read a single artist")
	public void i_can_read_a_single_artist() {
		hang();
		assertNotNull(artistsPage.getArtistNameUpdated());
	}
	
	@Then("I can read a single genre")
	public void i_can_read_a_single_genre() {
		hang();
		assertNotNull(genresPage.getSingleGenreNameText());
	}
	
	@Then("I can read a single playlist")
	public void i_can_read_a_single_playlist() {
		hang();
		assertNotNull(playlistsPage.getSinglePlaylistText());
	}

	@Then("I can read an artist with the name {string}")
	public void i_can_read_an_artist_with_the_name(String string) {
		hang();
	    String expected = string;
	    String result = artistsPage.getArtistName();
	    assertEquals(expected,result);
	}

	@Then("I see the text {string}")
	public void i_see_the_text(String string) {
		hang();
	    String expected = string;
	    String result = userPage.getResultText();
	    assertEquals(expected,result);
	}

	@Then("I can see the logout button")
	public void i_can_see_the_logout_button() {
		base.waitUntilNavExpand(driver);
		String expected = "Logout";
		String result = userPage.getLogoutText();
		assertEquals(expected,result);
	}
	
	@After
	public static void tearDown() {
		driver.quit();
		System.out.println("driver closed");
	}
	
	public static void hang() {
		base.waitUntilPageLoad(driver);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   
}
