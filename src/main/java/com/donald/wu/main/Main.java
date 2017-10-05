package com.donald.wu.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
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

public class Main extends AbstractMain {
	
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
			System.out.println("4. " + ultravioletIndexStr);
			System.out.println("5. " + earthquakeAdvisoryStr);
			System.out.println("");
			System.out.println("0. Exit");
			System.out.println("-------------------------------");

			try {
				int input = scanner.nextInt();
				System.out.println("You select = " + input);

				if (input >= 1 && input <= 5) {
					switch(input) {
						case 1:
							showDescriptionStr(input);
							showXmlStr(twoHourNowcast, input);
							
							break;
						case 2:
							showDescriptionStr(input);
							showXmlStr(twentyFourHourForecast, input);
							
							break;
						case 3:
							showDescriptionStr(input);
							showXmlStr(fourDayOutlook, input);
							
							break;
						case 4:
							showDescriptionStr(input);
							showXmlStr(ultravioletIndex, input);
							
							break;
						case 5:
							showDescriptionStr(input);
							showXmlStr(earthquakeAdvisory, input);
							
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
				inputResultStr = ultravioletIndexStr;
				break;
			case 5:
				inputResultStr = earthquakeAdvisoryStr;
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
		System.out.println("Opening [" + inputResultStr + "] now...");
		System.out.println("");
	}

	private static void showXmlStr(String urlLink, int input) {
		String xmlString = getXmlStr(urlLink);
		System.out.println("xmlString = " + xmlString);
		
		getXmlValue(urlLink, input);
	}

	private static void getXmlValue(String urlLink, int input) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(urlLink);
			//System.out.println("root node name = " + doc.getDocumentElement().getNodeName());
			System.out.println("");
			
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			if (input == 1) {
				String titleValue = (String) xpath.compile("//channel/title").evaluate(doc, XPathConstants.STRING);
				String dateValue = (String) xpath.compile("//channel/item/forecastIssue/@date").evaluate(doc, XPathConstants.STRING);
				String timeValue = (String) xpath.compile("//channel/item/forecastIssue/@time").evaluate(doc, XPathConstants.STRING);
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
				String timeValue = (String) xpath.compile("//channel/main/forecastIssue/@time").evaluate(doc, XPathConstants.STRING);
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
				String titleValue = (String) xpath.compile("//channel/item/title").evaluate(doc, XPathConstants.STRING);
				String dateValue = (String) xpath.compile("//channel/item/forecastIssue/@date").evaluate(doc, XPathConstants.STRING);
				String timeValue = (String) xpath.compile("//channel/item/forecastIssue/@time").evaluate(doc, XPathConstants.STRING);
				
				System.out.println(titleValue);
				System.out.println("----------------------------------------------------------");
				System.out.println("date = " + dateValue);
				System.out.println("time = " + timeValue);
				System.out.println("");
				
				NodeList dayNodeList = (NodeList) xpath.compile("//channel/item/weatherForecast/day").evaluate(doc, XPathConstants.NODESET);
				NodeList forecastNodeList = (NodeList) xpath.compile("//channel/item/weatherForecast/forecast").evaluate(doc, XPathConstants.NODESET);
				System.out.println("day records = " + dayNodeList.getLength());
				System.out.println("forecast records = " + forecastNodeList.getLength());
				
				List<String> dayList = new ArrayList<String>();
				List<String> forecastList = new ArrayList<String>();
				if (dayNodeList != null && dayNodeList.getLength() > 0) {
					for (int a = 0; a < dayNodeList.getLength(); a++) {
						NodeList dayNodeSubList = dayNodeList.item(a).getChildNodes();
						String day = dayNodeSubList.item(0).getNodeValue();
						dayList.add(day);
						
					}
				}
				if (forecastNodeList != null && forecastNodeList.getLength() > 0) {
					for (int b = 0; b < forecastNodeList.getLength(); b++) {
						NodeList forecastNodeSubList = forecastNodeList.item(b).getChildNodes();
						String forecast = forecastNodeSubList.item(0).getNodeValue();
						forecastList.add(forecast);
					}
				}
				
				System.out.println("### day = " + dayList.get(0) + ", forecast = " + forecastList.get(0));
				System.out.println("### day = " + dayList.get(1) + ", forecast = " + forecastList.get(1));
				System.out.println("### day = " + dayList.get(2) + ", forecast = " + forecastList.get(2));
				System.out.println("### day = " + dayList.get(3) + ", forecast = " + forecastList.get(3));
				System.out.println("");
				System.out.println("");
			}
			else if (input == 4) {
				String dateValue = (String) xpath.compile("//uvindex/date").evaluate(doc, XPathConstants.STRING);
				
				System.out.println("UV Index");
				System.out.println("----------------------------------------------------------");
				System.out.println("date = " + dateValue);
				System.out.println("");
				
				NodeList uvNodeList = (NodeList) xpath.compile("//uvindex/data/uv").evaluate(doc, XPathConstants.NODESET);
				System.out.println("uvNodeList records = " + uvNodeList.getLength());
				if (uvNodeList != null && uvNodeList.getLength() > 0) {
					for (int a = 0; a < uvNodeList.getLength(); a++) {
						 NodeList uvNodeSubList = uvNodeList.item(a).getChildNodes();
						 String uv = uvNodeSubList.item(0).getNodeValue();
						 
						 NamedNodeMap nodeMap = uvNodeList.item(a).getAttributes();
						 Node hr = nodeMap.getNamedItem("hr");
						 
						 System.out.println("### uv = " + uv + ", hr = " + hr.getNodeValue());
					}
					System.out.println("");
					System.out.println("");
				}
				
			}
			else if (input == 5) {
				String titleValue = (String) xpath.compile("//channel/item/title").evaluate(doc, XPathConstants.STRING);
				String updateValue = (String) xpath.compile("//channel/item/update").evaluate(doc, XPathConstants.STRING);
				String magnitudeValue = (String) xpath.compile("//channel/item/details/earthquake_details/magnitude").evaluate(doc, XPathConstants.STRING);
				String coordinateOfEpicenterValue = (String) xpath.compile("//channel/item/details/earthquake_details/coordinate_of_epicenter").evaluate(doc, XPathConstants.STRING);
				String estimatedDepthValue = (String) xpath.compile("//channel/item/details/earthquake_details/estimated_depth").evaluate(doc, XPathConstants.STRING);
				String locationValue = (String) xpath.compile("//channel/item/details/earthquake_details/location").evaluate(doc, XPathConstants.STRING);
				String detectedAtValue = (String) xpath.compile("//channel/item/details/earthquake_details/detected_at").evaluate(doc, XPathConstants.STRING);
				String assessmentValue = (String) xpath.compile("//channel/item/details/earthquake_details/assessment").evaluate(doc, XPathConstants.STRING);
				
				System.out.println(titleValue);
				System.out.println("----------------------------------------------------------");
				System.out.println("### Update = " + updateValue);
				System.out.println("### Magnitude = " + magnitudeValue);
				System.out.println("### Coordinate Of Epicenter = " + coordinateOfEpicenterValue);
				System.out.println("### Estimated Depth = " + estimatedDepthValue);
				System.out.println("### Location = " + locationValue);
				System.out.println("### Detected At = " + detectedAtValue);
				System.out.println("### Assessment = " + assessmentValue);
				System.out.println("");
				System.out.println("");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
