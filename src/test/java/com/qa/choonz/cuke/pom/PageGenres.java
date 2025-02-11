package com.qa.choonz.cuke.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageGenres extends PageBase {
	
	@FindBy(xpath = "/html/body/div[1]/button")
	private WebElement buttonCreateGenre;

	@FindBy(xpath = "//*[@id=\"genreName\"]")
	private WebElement inputCreateGenreName;
	
	@FindBy(xpath = "//*[@id=\"genreDesc\"]")
	private WebElement inputCreateGenreDescription;
	
	@FindBy(xpath = "//*[@id=\"createGenreForm\"]/span[1]/button")
	private WebElement buttonCreateGenreSubmit;
	
	// this will need to be changed from genres2 to genresX
	// when the data.sql is implemented for testing
	@FindBy(xpath = "//*[@id=\"genres2\"]/button/h3")
	private WebElement textGenreName;
	
	@FindBy(xpath = "//*[@id=\"genreNameDisplay\"]/h1")
	private WebElement textGenreNameDisplayHeader;
	
	@FindBy(xpath = "//*[@id=\"genreNameDisplay\"]")
	private WebElement textGenreNameDisplay;
	
	@FindBy(xpath = "//*[@id=\"updateEachGenre\"]")
	private WebElement buttonUpdateGenre;
	
	@FindBy(xpath = "//*[@id=\"genreNameUpdate\"]")
	private WebElement inputUpdateGenreName;
	
	@FindBy(xpath = "//*[@id=\"genreDescUpdate\"]")
	private WebElement inputUpdateGenreDescription;
	
	@FindBy(xpath = "//*[@id=\"updateGenreBtn\"]")
	private WebElement buttonUpdateGenreSubmit;
	
	@FindBy(xpath = "//*[@id=\"deleteEachGenre\"]")
	private WebElement linkDeleteGenre;
	
	public void clickCreateGenreButton() {
		buttonCreateGenre.click();
	}
	
	public void inputCreateGenreName(String name) {
		inputCreateGenreName.sendKeys(name);
	}
	
	public void inputCreateGenreDescription(String description) {
		inputCreateGenreDescription.sendKeys(description);
	}
	
	public void clickCreateGenreSubmitButton() {
		buttonCreateGenreSubmit.click();
	}
	
	public String getTextGenreName() {
		return textGenreName.getText();
	}
	
	public void clickTextGenreName() {
		textGenreName.click();
	}
	
	public String getSingleGenreNameText() {
		return textGenreNameDisplayHeader.getText();
	}
	
	public void clickUpdateGenreButton() {
		buttonUpdateGenre.click();
	}
	
	public void inputUpdateGenreName(String name) {
		inputUpdateGenreName.sendKeys(name);
	}
	
	public void inputUpdateGenreDescription(String description) {
		inputUpdateGenreDescription.sendKeys(description);
	}
	
	public void clickUpdateGenreButtonSubmit() {
		buttonUpdateGenreSubmit.click();
	}
	
	public void clickDeleteGenre() {
		linkDeleteGenre.click();
	}
	
	public String getDeleteGenreText() {
		return textGenreNameDisplay.getText();
	}
}
