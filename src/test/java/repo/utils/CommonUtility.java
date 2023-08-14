package repo.utils;

import java.net.HttpURLConnection;
import java.net.URL;

public class CommonUtility {
	
	public static boolean verifyLink(String url) {
		boolean isUrlBroken = false;
		try {
			URL link = new URL(url);
			HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
			httpURLConnection.setConnectTimeout(3000); // Set connection timeout to 3 seconds
			httpURLConnection.connect();
			
			if (httpURLConnection.getResponseCode() == 200) {
				isUrlBroken=false;
				System.out.println(url + " - " + httpURLConnection.getResponseMessage());
			} else {
				isUrlBroken=true;
				System.out.println(url + " - " + httpURLConnection.getResponseMessage() + " - " + "is a broken link");
			}
		} catch (Exception e) {
			System.out.println(url + " - " + "is a broken link");
		}
		return isUrlBroken;
	}

}
