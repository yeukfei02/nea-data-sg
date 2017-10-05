package com.donald.wu.main;

public class AbstractMain {
	protected final static String authorizationKey = "781CF461BB6606ADC49D8386041BBFD227CBEBC4F82A9D96";

	protected final static String twoHourNowcast = "http://api.nea.gov.sg/api/WebAPI/?dataset=2hr_nowcast&keyref=" + authorizationKey;
	protected final static String twentyFourHourForecast = "http://api.nea.gov.sg/api/WebAPI/?dataset=24hrs_forecast&keyref=" + authorizationKey;
	protected final static String fourDayOutlook = "http://api.nea.gov.sg/api/WebAPI/?dataset=4days_outlook&keyref=" + authorizationKey;
	protected final static String ultravioletIndex = "http://api.nea.gov.sg/api/WebAPI/?dataset=uvi&keyref=" + authorizationKey;
	protected final static String earthquakeAdvisory = "http://api.nea.gov.sg/api/WebAPI/?dataset=earthquake&keyref=" + authorizationKey;
	
	protected final static String twoHourNowcastStr = "2-hour Nowcast";
	protected final static String twentyFourHourForecastStr = "24-hour Forecast";
	protected final static String fourDayOutlookStr = "4-day Outlook";
	protected final static String ultravioletIndexStr = "Ultraviolet Index (UVI)";
	protected final static String earthquakeAdvisoryStr = "Earthquake Advisory";
}
