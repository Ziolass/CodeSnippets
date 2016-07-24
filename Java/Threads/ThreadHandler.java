import java.util.List;


/**
 * Interfejs dla glownej klasy zarzadzajacej watkami
 *
 */
public interface ThreadHandler {
	/**
	 * Funkcja rozdzielajaca wszystkei wiadomosci na listy watkow - listy znajdujace sie na Liscie list
	 * @param listMessages
	 */
	public void prepareThreadLists(List<Message> listMessages);
	/**
	 * Funkcja odpalajaca watki
	 */
	public void ThreadRunner();
	/**
	 * Funkcja oczekujaca na zakonczenie pracy wszystkich watkow
	 */
	public void waitForThreadsClosing();
}
