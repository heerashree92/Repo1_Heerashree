package repo.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import repo.base.TestBase;

public class RepoPage extends TestBase{
	//kubernetes
	
	@FindBy(xpath = "//input[@placeholder='Search']") WebElement repoSearch;
	@FindBy(xpath = "//tbody[@class='MuiTableBody-root css-1leteql-MuiTableBody-root']/tr") List<WebElement> repoSearchList;
	@FindBy(xpath = "//tbody[@class='MuiTableBody-root css-1leteql-MuiTableBody-root']/tr//a") List<WebElement> repoSearchResultLinks;
	@FindBy(xpath="//button[@aria-label='search']") WebElement searchBtn;
	@FindBy(xpath="//div[@id='mui-1']") WebElement rowsPerPageDropdown;
	@FindBy(xpath="//ul[@aria-labelledby='mui-2']/li")  List<WebElement> rowsPerPageList;
	
	public RepoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void searchRepo(String searchText) {
		repoSearch.sendKeys(searchText);
		searchBtn.click();
	}
	
	public List<WebElement> getSearchList(){
		List<WebElement> rpList = repoSearchList;
		return rpList;
	}
	
	public void selectNumberOfRowsPerPage(String numberOfRows){
		rowsPerPageDropdown.click();
		for(WebElement rowsPerPage : rowsPerPageList) {
			if(rowsPerPage.getText().trim().equals(numberOfRows)) {
				rowsPerPage.click();
				break;
			}
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getRepoSearchResultLinks(){
		List<String> hrefLinks = new ArrayList<String>();
		for(WebElement ele : repoSearchResultLinks) {
			hrefLinks.add(ele.getAttribute("href"));
		}
		
		return hrefLinks;
	}
}