import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Klasa budujaca pliki typu .csv na podstawie wysylanych wiadomosci SMS/MMS.
 * 
 */
public class fileMenager {
	/**
	 * Funkcja budujaca plik wiadomosc .csv na podstawie przekazanej listy
	 * wiadmosci
	 * 
	 * @param messageList
	 */
	public void buildFile(List<Message> messageList, String campaignID) {

		String timeStamp = new SimpleDateFormat("yyMMdd_HHmmss:SSSS").format(new Date());
		// String fullNameOfFile = timeStamp+"_"+campaignID+".csv";
		String fullNameOfFile = null;

		if (ApplicationInitializer.fileName.length() > 1) {
			
			fullNameOfFile = ApplicationInitializer.fileName+"_msisdn.csv";
			
		} else {
			
			fullNameOfFile = timeStamp + "_" + campaignID + ".csv";
		}
		String pathToFile = "/sas/data/last_minute/Multikino/";

		fullNameOfFile = pathToFile + fullNameOfFile;

		try {
			FileOutputStream os = new FileOutputStream(fullNameOfFile);
			// Byte order mark (EF BB BF = 239,187,191)
			// cwaniacki sposób na zapis do csv
			os.write(239);
			os.write(187);
			os.write(191);
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
			SmsMessage sms;
			MmsMessage mms;
			//writer.write("msisdn;typ_komunikacji;tresc;resptracking_cd;oferta;picture_mms\n");
			writer.write("msisdn\n");
			for (Message message : messageList) {
				if (message instanceof SmsMessage) {
					sms = (SmsMessage) message;
					writer.write(sms.getMsisdn() +"\n");

				} else if (message instanceof MmsMessage) {
					mms = (MmsMessage) message;
					writer.write(mms.getMsisdn() +"\n");

				}
			}
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sendFileToEmail() {
		// TODO
	}

	public void sendFileToServer() {
		// TODO
	}
}
