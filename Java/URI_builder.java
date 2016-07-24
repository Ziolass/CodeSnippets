import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

/**
 * Klasa pomocnicza z metodami statycznymi do generowania URI do wysylania zadania SMS/MMS
 *
 */
public class UrlBuilder {
	private UrlBuilder() {
	}
	/**
	 * Generator URI SMS
	 *
	 * Wzor uri:
	 * https://api/sendsms/?from=<string>&to=<string>&ucs=<boolean>&flash=<boolean>?&report_url=<string>?&msg=<string>
	 *
	 * @param msisdnFrom od kogo wiadomosc np.Orange lub numer
	 * @param msisdnTo docelowy numer odbiorcy MSISDN np. 48666777888
	 * @param ucs flaga okreslajaca czy wiadomosc ma zawierac polskie znaki (true) (70 znakow) czy nie (false) (160 znakow)
	 * @param flash flaga okreslajaca czy wiadomosc ma sie tylo pojawic i nie zapisywac (true)(flash) czy standardowy SMS (false)
	 * @param reportUrl adres url do wyslania raportu stanu sms
	 * @param msg tresc SMSa
	 * @return {@link java.net.URI}
	 */
	
	public static URI generateSmsUri(
			String msisdnFrom, String msisdnTo, Boolean ucs, Boolean flash, String reportUrl, String msg) {
		URI uri = null;
		String address = "some address"; // 
		
		try {
			uri = new URI(address + "from=" + msisdnFrom + "&to=" +
						msisdnTo + "&ucs=" + ucs + "&flash=" + flash + "&report_url=" + reportUrl + "&msg=" + URLEncoder.encode(msg, "UTF-8") + "");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return uri;
	}

	/**
	 * Generator URI do MMS (bez parametrowy)
	 *
	 * @return {@link java.net.URI}
	 */
	public static URI generateMmsUri() {
		URI uri = null;
		//URI newUri = null;
		String address = "some address 2";
		try {
			uri = new URI(address);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return uri;
	}

}
