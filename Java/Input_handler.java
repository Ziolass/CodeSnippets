import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;


/**
 * Klasa zajmujaca sie obsluga parametrow wywolania apliakcji
 *
 */
@SuppressWarnings("deprecation")
public class InputParametersHandler {
	
	/**
	 * Funkcja wczytujaca parametry wywolania aplikacji i ustawiajaca odpowiednie wartosci
	 */
	
	 private static final Logger log = Logger.getLogger(InputParametersHandler.class.getName());
	 private String[] args = null;
	 private Options options = new Options();

	 public InputParametersHandler(String[] args) {
		 this.args = args;
		 options.addOption("h", "help", false, "show help.");
		 options.addOption("c", "camp", true, "Here you can set parameter .");
		 options.addOption("p", "pass", true, "new PostgreSQL passwrod");
		 options.addOption("u", "user", true, "new PostgreSQL user");
		 options.addOption("f", "file", false, "dump to file without sending");
		 options.addOption("n","name",true, "specify name for outfile");
		 options.addOption("r", "remainder",false,"send from remainder database");

	 }
	 public String getInputParameters(){
		 CommandLineParser parser = new BasicParser();
		 CommandLine cmd = null;
		 try {
			 cmd = parser.parse(options, args);
		 } catch (org.apache.commons.cli.ParseException e) {
			e.printStackTrace();
		 }

		 if (cmd.hasOption("h")){
			 help();
		 }
		 if (cmd.hasOption("p")){
			 log.log(Level.INFO, "Using passwrod argument -p=" + cmd.getOptionValue("p"));
			 PasswordHandler passwordHandler = new PasswordHandler();
			 String newPassword = passwordHandler.encrypt(cmd.getOptionValue("p"));
			 XMLFile xmlFile = XMLFile.getInstance();
			 xmlFile.setData("server", "password", "1", newPassword);
			 System.exit(0);
		 }
		 if (cmd.hasOption("u")){
			 log.log(Level.INFO, "Using passwrod argument -p=" + cmd.getOptionValue("u"));
			 XMLFile xmlFile = XMLFile.getInstance();
			 xmlFile.setData("server", "user", "1", cmd.getOptionValue("u"));
			 System.exit(0);
			 
		 }
		 if (cmd.hasOption("f")){
			 log.log(Level.INFO, "Using dump to file only");
			 ApplicationInitializer.global=true;
		 }
		 if(cmd.hasOption("n")){
			 String tmp = cmd.getOptionValue("n");
			 log.log(Level.INFO, "Using filename argument -n="+tmp);
			 ApplicationInitializer.fileName = tmp;
			 
		 }
		 if(cmd.hasOption("r")){
			 log.log(Level.INFO, "Using campaign argument -r=" + cmd.getOptionValue("r"));
			 ApplicationInitializer.reminder = true;
			 return "remainder";

		 }
		 if (cmd.hasOption("c")) {
			 log.log(Level.INFO, "Using campaign argument -c=" + cmd.getOptionValue("c"));
			 return cmd.getOptionValue("c");
		 }
		 else {
			 log.log(Level.SEVERE, "Missing v option");
			 help();
		 }
		return null;
	 }

	 private void help() {
		 HelpFormatter formater = new HelpFormatter();
		 formater.printHelp("Podane parametry wejsciowe sa nieprawidlowe, wywolaj program jeszcze raz z podanymi parametrami:", options);
		 System.exit(0);
	 }
}



