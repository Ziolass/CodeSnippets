import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Klasa rozszerzajaca klase {@link java.lang.Thread}
 *
 */
public class ThreadContainer implements Runnable {
	/**
	 * Lista wiadomosci tego watku
	 */
	private List<Message> messageList;
	/**
	 * id watku
	 */
	private int id;
	/**
	 * Konstruktor watku tworzy watek o zadanym Id i przekazuje do niego liste wiadomosci
	 * @param messageList
	 * @param id
	 */
	public ThreadContainer(List<Message> messageList, int id) {
		this.messageList = messageList;
		this.id = id;
	}
	/**
	 * Funkcja zwracajaca liste wiadomosci
	 * @return
	 */
	public List<Message> getMessageList() {
		return messageList;
	}

	@Override
	public void run() {
		RaportDaoImpl raportDao = new RaportDaoImpl();
		boolean wasSent = false;
		boolean wasCouponAddedToMessage = false;
		
		for(int i=0;i<messageList.size();i++) {
			double stan = (double)(i+1)/(double)messageList.size();
			System.out.println("THREAD: " + id +
								"   MSISDN_CRYPTED: " + messageList.get(i).getMsisdnCrypted() +
								"   MSISDN_DECRYPTED:"+ messageList.get(i).getMsisdn() +
								"   COUPON: " + messageList.get(i).getCoupon().getCouponId() +
								"   STAN watku:   " + stan*100 + "%");
			try {
				long start = System.nanoTime();
				
				if (wasCouponAddedToMessage = messageList.get(i).attachCouponToMessageContent()){
					wasSent = messageList.get(i).sendMessage();
				}	
				long elapsedTime = System.nanoTime() - start;
				System.out.println("Czas "+i+" wyniosl: " + elapsedTime);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(ApplicationInitializer.reminder)
			{
				raportDao.updateRemainderDatabase_second(messageList.get(i));
			}
			else 
			{
				raportDao.updateRaportsDatabase(messageList.get(i));

			}
			
		}
	}
}