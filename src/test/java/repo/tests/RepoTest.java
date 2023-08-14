package repo.tests;

import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import repo.base.TestBase;
import repo.pages.RepoPage;
import repo.utils.CommonUtility;

public class RepoTest extends TestBase{
	
	RepoPage repoPage;
	private String searchKeyWord="kubernetes";
		
	@Test(priority=1)
	public void VerifyRepoSearch() {
		SoftAssert sAssert = new SoftAssert();
		repoPage = new RepoPage(getDriver());
		getDriver().get("http://localhost:3000/");
		 
		repoPage.searchRepo(searchKeyWord);
		System.out.println("repoPage.getSearchList().size() : "+repoPage.getSearchList().size());
		Assert.assertEquals(repoPage.getSearchList().size(), 10);
		 
		for(int i=0;i<repoPage.getSearchList().size();i++) {
		 System.out.println(repoPage.getSearchList().get(i).getText());
		 sAssert.assertEquals(repoPage.getSearchList().get(i).getText().toLowerCase().contains(searchKeyWord),true,"Row number : "+(i+1)+" => "+repoPage.getSearchList().get(i).getText()+" => doesn't contain searched keyword => "+searchKeyWord);
		}
		sAssert.assertAll();
	}
	
	@Test(priority=2,dataProvider="dataProviderUrlLinks")
	public void VerifyRepoLinks(String url) {
		//SoftAssert sAssert = new SoftAssert();
		List<String> hrefLinks = repoPage.getRepoSearchResultLinks();
		//for(String url : hrefLinks) {
			boolean isUrlBroken = CommonUtility.verifyLink(url);
			Assert.assertEquals(isUrlBroken, false,url+" => is broken");
		//}		
		//sAssert.assertAll();
	}
	
	@Test(priority=3,dataProvider="dataProviderRowsPerPage")
	public void VerifyNumberOfRowsPerPage(String numberOfRows) {
		repoPage.selectNumberOfRowsPerPage(numberOfRows);
		System.out.println("repoPage.getSearchList().size() : "+repoPage.getSearchList().size());
		Assert.assertEquals(Integer.toString(repoPage.getSearchList().size()), numberOfRows);
	}
	
	@DataProvider
	public String[][] dataProviderRowsPerPage() {
		 String[][] rowsPerPage = {{"10"},{"25"},{"50"}};
		return rowsPerPage;
		
	}
	
	@DataProvider
	public String[][] dataProviderUrlLinks() {
		List<String> hrefLinks = repoPage.getRepoSearchResultLinks();
		 String[][] UrlLinks = new String[hrefLinks.size()][1];
		for (int i=0;i<hrefLinks.size();i++) {
			UrlLinks[i][0]=hrefLinks.get(0);
		}
		System.out.println(Arrays.toString(UrlLinks));
		return UrlLinks;
		
	}
}
