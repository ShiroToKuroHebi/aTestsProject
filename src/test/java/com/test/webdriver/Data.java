package com.test.webdriver;

/** Just general stuff
 */
public interface Data {
	// -==| Stand URL |==-
	String standURL = "http://stand.vtb.jtcc.ru:16006/";
	
	// -==| General locators |==-
	String tooltipErrorXPath = "//div[@class=\"field__tooltip field__tooltip_error\"]";

	// -==| Bank Data |==-
    // -|БАЙКОНУРСКИЙ ФИЛИАЛ АО "ФОНДСЕРВИСБАНК"|-
    dictRowBIC FONDSERVICEBANK = new dictRowBIC(
		    "040037470",
		    "БАЙКОНУРСКИЙ ФИЛИАЛ АО \"ФОНДСЕРВИСБАНК\"",
		    "30101.810.3.00000000470",
		    "БАЙКОНУР"
    );
    
    // -==| Random Data |==-
	String NUMBERS_5x0 = "00000";
	String NUMBERS_9x0 = "000000000";
	String NO_DIGITS_STRING = "qazWSX~!@#$%^&*()\".,;:'[]{}-_=+`?/\\|<>увсКАМ№";
}
