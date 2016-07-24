import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * klasa zajmujaca sie obsluga watkow - ich wytworzeniem, odpaleniem oraz oczekiwaniem na ich zamkniecie
 * 
 * 
 */
public class ThreadHandlerImpl implements ThreadHandler {
	/**
	 * Lista list wiadomosci. Wewnetrzna lista reprezentuje watek, do ktorego zostana przydzielone wiadomosci, zewnetrzna zawiera wszystkei wiadomosci.
	 */
    public List<List<Message>> listOfLists;
    /**
     * Liczba watkow do odpalenia
     */
    public final int NUMBER_OF_THREADS = 10;
    /**
     * Lista watkow
     */
    public ArrayList<Thread> threadList = new ArrayList<Thread>();

    /**
     * Konstruktor tworzacy listy wiadomosci i dodajacy je do listy list. List tych jest tyle co watkow (NUMBER_OF_THREADS).
     */
    public ThreadHandlerImpl(){
       listOfLists = new ArrayList<List<Message>>();
	   for (int i =0;i<NUMBER_OF_THREADS;i++){
		  // System.out.println("CREATE THREAD NUMBER: "+i);
	       listOfLists.add(new ArrayList<Message>());
	   }
    }
    @Override
    public void prepareThreadLists(List<Message> listMessages){
	   int sizeOfListThread = listOfLists.size();
	   int index = 0;
	   for (Message message : listMessages) {
	                   listOfLists.get(index).add(message);
	                   if (index==sizeOfListThread-1)
	                                   index=0;
	                   else
	                                   index++;
	   }
    }
    @Override
    public void ThreadRunner(){
	   Thread thread;
	   for (int i = 0; i<NUMBER_OF_THREADS;i++){
	                   thread = new Thread(new ThreadContainer(listOfLists.get(i), i));
	                   threadList.add(thread);
	                   thread.start();
	   }
    }

	@Override
	public void waitForThreadsClosing() {
		boolean allThreadsDead = true;
		while(true){
			allThreadsDead = true;
			for (Thread thread : threadList){
				if(thread.isAlive()){
					try {
						TimeUnit.SECONDS.sleep(1);
						allThreadsDead=false;
						break;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if(allThreadsDead==true)
				break;
		}
	}
}
