package com.donaldwu.test;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainTest {
	private final static String authorizationKey = "781CF461BB6606ADC49D8386041BBFD227CBEBC4F82A9D96";

	private final static String urlLink = "http://api.nea.gov.sg/api/WebAPI/?dataset=2hr_nowcast&keyref=" + authorizationKey;
	
	@Test
	public void test_001_getXmlStr() {
		String xmlStr = getXmlStr(urlLink);
		System.out.println("xmlStr = " + xmlStr);
		
		assertTrue("xmlStr length is not greater than 0", xmlStr.length() > 0);
	}
	
	
	private static String getXmlStr(String urlLink) {
		StringBuilder content = new StringBuilder();

		try {
			URL url = new URL(urlLink);
			URLConnection urlConnection = url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

			String line;

			while ((line = bufferedReader.readLine()) != null) {
				content.append(line + "\n");
			}

			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return content.toString();
	}

}
