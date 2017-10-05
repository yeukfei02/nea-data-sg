package com.donald.wu.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main {
	private final static String authorizationKey = "781CF461BB6606ADC49D8386041BBFD227CBEBC4F82A9D96";

	private final static String twoHourNowcast = "http://api.nea.gov.sg/api/WebAPI/?dataset=2hr_nowcast&keyref=" + authorizationKey;
	private final static String twentyFourHourForecast = "http://api.nea.gov.sg/api/WebAPI/?dataset=24hrs_forecast&keyref=" + authorizationKey;
	private final static String fourDayOutlook = "http://api.nea.gov.sg/api/WebAPI/?dataset=2hr_nowcast&keyref=" + authorizationKey;
	private final static String heavyRainWarning = "http://api.nea.gov.sg/api/WebAPI/?dataset=2hr_nowcast&keyref=" + authorizationKey;
	private final static String ultravioletIndex = "http://api.nea.gov.sg/api/WebAPI/?dataset=2hr_nowcast&keyref=" + authorizationKey;
	private final static String earthquakeAdvisory = "http://api.nea.gov.sg/api/WebAPI/?dataset=2hr_nowcast&keyref=" + authorizationKey;
	private final static String psiUpdate = "http://api.nea.gov.sg/api/WebAPI/?dataset=2hr_nowcast&keyref=" + authorizationKey;
	private final static String pm25Update = "http://api.nea.gov.sg/api/WebAPI/?dataset=2hr_nowcast&keyref=" + authorizationKey;

	private final static String twoHourNowcastStr = "2-hour Nowcast";
	private final static String twentyFourHourForecastStr = "24-hour Forecast";
	private final static String fourDayOutlookStr = "4-day Outlook";
	private final static String heavyRainWarningStr = "Heavy Rain Warning";
	private final static String ultravioletIndexStr = "Ultraviolet Index (UVI)";
	private final static String earthquakeAdvisoryStr = "Earthquake Advisory";
	private final static String psiUpdateStr = "PSI Update";
	private final static String pm25UpdateStr = "PM 2.5 Update";

	public static void main(String[] args) {
		boolean exitStatus = false;

		while(!exitStatus) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("### NEA Data Singapore ###");
			System.out.println("Please enter your options: ");
			System.out.println("-------------------------------");
			System.out.println("1. " + twoHourNowcastStr);
			System.out.println("2. " + twentyFourHourForecastStr);
			System.out.println("3. " + fourDayOutlookStr);
			System.out.println("4. " + heavyRainWarningStr);
			System.out.println("5. " + ultravioletIndexStr);
			System.out.println("6. " + earthquakeAdvisoryStr);
			System.out.println("7. " + psiUpdateStr);
			System.out.println("8. " + pm25UpdateStr);
			System.out.println("");
			System.out.println("0. Exit");
			System.out.println("-------------------------------");

			try {
				int input = scanner.nextInt();
				System.out.println("You select = " + input);

				if (input >= 1 && input <= 8) {
					switch(input) {
						case 1:
							showDescriptionStr(input);
							
							showXmlStr(twoHourNowcast, input);
							
							exitStatus = getUserExitStatus(scanner);
							if (exitStatus) {
								System.out.println("Exit...");
								System.out.println("");
							}
							break;
						case 2:
							showDescriptionStr(input);
							
							showXmlStr(twentyFourHourForecast, input);
							
							exitStatus = getUserExitStatus(scanner);
							if (exitStatus) {
								System.out.println("Exit...");
								System.out.println("");
							}
							break;
						case 3:
							showDescriptionStr(input);
							
							showXmlStr(fourDayOutlook, input);
							
							exitStatus = getUserExitStatus(scanner);
							if (exitStatus) {
								System.out.println("Exit...");
								System.out.println("");
							}
							break;
						case 4:
							showDescriptionStr(input);
							
							showXmlStr(heavyRainWarning, input);
							
							exitStatus = getUserExitStatus(scanner);
							if (exitStatus) {
								System.out.println("Exit...");
								System.out.println("");
							}
							break;
						case 5:
							showDescriptionStr(input);
							
							showXmlStr(ultravioletIndex, input);
							
							exitStatus = getUserExitStatus(scanner);
							if (exitStatus) {
								System.out.println("Exit...");
								System.out.println("");
							}
							break;
						case 6:
							showDescriptionStr(input);
							
							showXmlStr(earthquakeAdvisory, input);
							
							exitStatus = getUserExitStatus(scanner);
							if (exitStatus) {
								System.out.println("Exit...");
								System.out.println("");
							}
							break;
						case 7:
							showDescriptionStr(input);
							
							showXmlStr(psiUpdate, input);
							
							exitStatus = getUserExitStatus(scanner);
							if (exitStatus) {
								System.out.println("Exit...");
								System.out.println("");
							}
							break;
						case 8:
							showDescriptionStr(input);
							
							showXmlStr(pm25Update, input);
							
							exitStatus = getUserExitStatus(scanner);
							if (exitStatus) {
								System.out.println("Exit...");
								System.out.println("");
							}
							break;
					}

				} else if (input == 0) {
					System.out.println("Exit...");
					System.out.println("");
					exitStatus = true;
				} else {
					System.out.println("No this options...");
					System.out.println("");

				}
			} catch (Exception e) {
				System.out.println("Please enter a valid number again...");
				System.out.println("");
			}

		}
	}


	private static String getInputResultStr(int input) {
		String inputResultStr = "";
		switch(input) {
			case 1:
				inputResultStr = twoHourNowcastStr;
				break;
			case 2:
				inputResultStr = twentyFourHourForecastStr;
				break;
			case 3:
				inputResultStr = fourDayOutlookStr;
				break;
			case 4:
				inputResultStr = heavyRainWarningStr;
				break;
			case 5:
				inputResultStr = ultravioletIndexStr;
				break;
			case 6:
				inputResultStr = earthquakeAdvisoryStr;
				break;
			case 7:
				inputResultStr = psiUpdateStr;
				break;
			case 8:
				inputResultStr = pm25UpdateStr;
				break;
		}

		return inputResultStr;

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
	
	private static void showDescriptionStr(int input) {
		String inputResultStr = getInputResultStr(input);
		System.out.println("Opening " + inputResultStr + " now...");
		System.out.println("");
	}

	private static void showXmlStr(String urlLink, int input) {
		String xmlString = getXmlStr(urlLink);
		System.out.println("xmlString = " + xmlString);
		
		getXmlValue(urlLink, input);
	}
	
	private static boolean getUserExitStatus(Scanner scanner) {
		boolean exitStatus = false;
		
		System.out.println("Continue? [y/n]");
		try {
			String inputStr = scanner.next();
			if (inputStr.equalsIgnoreCase("y")) {
				exitStatus = false;
			} else if (inputStr.equalsIgnoreCase("n")) {
				exitStatus = true;
			} else {
				System.out.println("Please enter y/n only...");
				System.out.println("");
			}
		} catch (Exception e) {
			System.out.println("Please enter y/n only...");
			System.out.println("");
		}
		
		return exitStatus;
	}

	private static void getXmlValue(String urlLink, int input) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(urlLink);
			System.out.println("root node name = " + doc.getDocumentElement().getNodeName());
			System.out.println("");
			
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			if (input == 1) {
				String titleValue = (String) xpath.compile("//channel/title").evaluate(doc, XPathConstants.STRING);
				String dateValue = (String) xpath.compile("//channel/item/forecastIssue/@date").evaluate(doc, XPathConstants.STRING);
				String timeValue = (String) xpath.compile("//channel/item//forecastIssue/@time").evaluate(doc, XPathConstants.STRING);
				String validTimeValue = (String) xpath.compile("//channel/item/validTime").evaluate(doc, XPathConstants.STRING);
				
				System.out.println(titleValue);
				System.out.println("----------------------------------------------------------");
				System.out.println("date = " + dateValue);
				System.out.println("time = " + timeValue);
				System.out.println("validTime = " + validTimeValue);
				System.out.println("");
				
				NodeList nodeList = (NodeList) xpath.compile("//channel/item/weatherForecast/area").evaluate(doc, XPathConstants.NODESET);
				System.out.println("Total records = " + nodeList.getLength());
				if (nodeList != null && nodeList.getLength() > 0) {
					for (int i = 0; i < nodeList.getLength(); i++) {
						  NamedNodeMap nodeMap = nodeList.item(i).getAttributes();
						  Node name = nodeMap.getNamedItem("name");
						  Node lat = nodeMap.getNamedItem("lat");
						  Node lon = nodeMap.getNamedItem("lon");
						  System.out.println("### " + name.getNodeValue() + "     lat = " + lat.getNodeValue() + ", lon = " + lon.getNodeValue());
					}
					System.out.println("");
					System.out.println("");
				}
			}
			else if (input == 2) {
				String titleValue = (String) xpath.compile("//channel/main/title").evaluate(doc, XPathConstants.STRING);
				String dateValue = (String) xpath.compile("//channel/main/forecastIssue/@date").evaluate(doc, XPathConstants.STRING);
				String timeValue = (String) xpath.compile("//channel/main//forecastIssue/@time").evaluate(doc, XPathConstants.STRING);
				String validTimeValue = (String) xpath.compile("//channel/main/validTime").evaluate(doc, XPathConstants.STRING);
				
				System.out.println(titleValue);
				System.out.println("----------------------------------------------------------");
				System.out.println("date = " + dateValue);
				System.out.println("time = " + timeValue);
				System.out.println("validTime = " + validTimeValue);
				System.out.println("");
				
				String highDegreesCelsius = (String) xpath.compile("//channel/main/temperature/@high").evaluate(doc, XPathConstants.STRING);
				String lowDegreesCelsius = (String) xpath.compile("//channel/main/temperature/@low").evaluate(doc, XPathConstants.STRING);
				String degreesCelsius = (String) xpath.compile("//channel/main/temperature/@unit").evaluate(doc, XPathConstants.STRING);
				System.out.println("### " + degreesCelsius + "     high = " + highDegreesCelsius + ", low = " + lowDegreesCelsius);
				
				String highRelativeHumidity = (String) xpath.compile("//channel/main/relativeHumidity/@high").evaluate(doc, XPathConstants.STRING);
				String lowRelativeHumidity = (String) xpath.compile("//channel/main/relativeHumidity/@low").evaluate(doc, XPathConstants.STRING);
				System.out.println("### Relative Humidity     high = " + highRelativeHumidity + ", low = " + lowRelativeHumidity);
				
				String windDirection = (String) xpath.compile("//channel/main/wind/@direction").evaluate(doc, XPathConstants.STRING);
				String windSpeed = (String) xpath.compile("//channel/main/wind/@speed").evaluate(doc, XPathConstants.STRING);
				System.out.println("### Wind     direction = " + windDirection + ", speed = " + windSpeed);
				
				String forecast = (String) xpath.compile("//channel/main/forecast").evaluate(doc, XPathConstants.STRING);
				System.out.println("### Forecast = " + forecast);
				System.out.println("");
				System.out.println("");
			}
			else if (input == 3) {
				
			}
			else if (input == 4) {

			}
			else if (input == 5) {

			}
			else if (input == 6) {

			}
			else if (input == 7) {
				
			}
			else if (input == 8) {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
