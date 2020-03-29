
package wrestlingtournamentcli;
import DataClasses.*;
import java.util.ArrayList;
import java.util.Scanner;
import loggingFunctions.*;
import DataClasses.*;
import javafx.scene.control.ListView;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * @author Jared Murphy
 * @author Cody Francis
 */
public class Main extends Application{
    static Error_Reporting log = new Error_Reporting();
    
    public static void main(String[] args) {

    Model m = new Model("wrestling");
    Scanner s = new Scanner(System.in);
    Race race = new Race();
    log.createLogFiles();
    launch(args);

    System.out.println("Please enter the sport you would like to manage(wrestling/soccer):");
	while(true) {
	String sportSelection = s.nextLine();
	switch(sportSelection.toLowerCase()) {
	case "wrestling":
		Model wrestlerModel = new Model("wrestling");
		wrestlingMenu();
		break;
    case "soccer":
    	Model soccerModel = new Model("soccer");
    	soccerMenu();
    	break;
    default:	
    	System.out.println("Incorrect input. Please enter wrestling/soccer");   
				}
    }
    }
    public static void wrestlingMenu() {
    	Scanner s = new Scanner(System.in);
    	System.out.println("Welcome to the Murphy Wrestling Tournament Manager. For available commands, please type 'help'");
	    while(true){
	    System.out.println("\nInput your next command!");
	    String input = s.nextLine();
	    try{
	    ArrayList<String> arguments = new ArrayList();
	    Scanner argScanner = new Scanner(input);
	    while(argScanner.hasNext()){
	        arguments.add(argScanner.next());
	    }
	    processInput(arguments);
	    }catch(Exception e){
	        System.out.println("Sorry! The command '" + input + "' either wasn't recognized or experienced an error.");
	        System.out.println(e.getMessage());
	        log.writeErrorLog(e.getMessage());
	        log.writeErrorStack(e);
	    }
	    }
    }
   
    public static void soccerMenu() { 
	   Scanner s = new Scanner(System.in);
  	   System.out.println("Welcome to the Murphy Soccer Player Manager. For available commands, please type 'help'");
      while(true){
      System.out.println("\nInput your next command!");
      String input = s.nextLine();
      try{
      ArrayList<String> arguments = new ArrayList();
      Scanner argScanner = new Scanner(input);
      while(argScanner.hasNext()){
          arguments.add(argScanner.next());
      }
      processInputSoccer(arguments);
      }catch(Exception e){
          System.out.println("Sorry! The command '" + input + "' either wasn't recognized or experienced an error.");
          System.out.println(e.getMessage());
      }
      }
  }
    
    public static void processInputSoccer(ArrayList<String> args) throws Exception{
    	   args.set(0, args.get(0).toUpperCase());
    	   switch(args.size()){
    	           case 0:
    	               throw new BadCommandException();
    	           case 1: //Single-Command expressions
    	               switch(args.get(0)){
    	                   case "VIEW-TEAMS":
    	                       Model.printTeams();
    	                       return;
    	                   case "VIEW-SOCCER-PLAYERS":
    	                       Model.printSoccerPlayers();
    	                       return;
    	                   case "HELP":
    	                       printHelpSoccer();
    	                       return;
    	                   default:
    	                       throw new BadCommandException();
    	               }
    	           case 2: //Command-Param Expressions
    	               switch(args.get(0)){
    	                   case "IMPORT-TEAMS":
    	                       if(args.get(1).substring(args.get(1).length()-4).equals(".txt")){
    	                       Model.importTeamsFromText(args.get(1));
    	                       }else{
    	                       System.out.println("Error: Not a supported file extension.");
    	                       }
    	                       return;
    	                   case "IMPORT-SOCCER-PLAYERS":
    	                       if(args.get(1).substring(args.get(1).length()-4).equals(".txt")){
    	                       Model.importSoccerPlayersFromText(args.get(1));
    	                       }else{
    	                       System.out.println("Error: Not a supported file extension.");
    	                       }
    	                       return;
    	                   case "VIEW-SOCCER-PLAYER":
    	                       Model.printSoccerPlayerInformation(args.get(1));
    	                       return;
    					default:
    						break;
    	               }
    	           default:
    	               printHelpSoccer();
    	               return;
    	   }
    	}
    
    
    public static void printHelpSoccer(){
        System.out.println("List of Commands and their parameters:\n"
                + "Command Parameter1 Parameter2 Parameter 3...\n"
                + "VIEW-TEAMS //Displays each team's information\n"
                + "VIEW-SOCCER-PLAYERS //Displays soccer players\n"
                + "HELP //Display list of available commands\n"
                + "IMPORT-TEAMS FileName //Parses the provided file for Team objects\n"
                + "IMPORT-SOCCER-PLAYERS FileName //Parses the provided file for Soccer objects\n"
                + "VIEW-SOCCER-PLAYER SoccerPlayerName //Looks for the soccer player and prints his/her information\n"
                );
                }
    
    public static void processInput(ArrayList<String> args) throws Exception{
    args.set(0, args.get(0).toUpperCase());
    switch(args.size()){
            case 0:
                throw new BadCommandException();
            case 1: //Single-Command expressions
                switch(args.get(0)){
                    case "SAVE":
                    	log.writeActionlog("Command Entered: " +args.get(0));
                        Model.saveTournament();
                        return;
                    case "ADVANCE":
                    	log.writeActionlog("Command Entered: " +args.get(0));
                        Model.advanceTournament();
                        return;
                    case "START":
                    	log.writeActionlog("Command Entered: " +args.get(0));
                        Model.generateTournament();
                        return;
                    case "VIEW-TEAMS":
                    	log.writeActionlog("Command Entered: " +args.get(0));
                        Model.printTeams();
                        return;
                    case "VIEW-WRESTLERS":
                    	log.writeActionlog("Command Entered: " +args.get(0));
                        Model.printWrestlers();
                        return;
                    case "HELP":
                    	log.writeActionlog("Command Entered: " +args.get(0));
                        printHelp();
                        return;
                    case "RACE":
                    	Race.printMenu();
                    	return;
                    default:
                        throw new BadCommandException();
                }
            case 2: //Command-Param Expressions
                switch(args.get(0)){
                    case "LOAD":
                    	log.writeActionlog("Command Entered: " +args.get(0)+ " "+ args.get(1));
                        Model.loadTournament(args.get(1));
                        return;
                    case "SAVE":
                    	log.writeActionlog("Command Entered: " +args.get(0)+ " "+ args.get(1));
                        Model.saveTournament(args.get(1));
                        return;
                    case "IMPORT-TEAMS":
                        if(args.get(1).substring(args.get(1).length()-4).equals(".txt")){
                        log.writeActionlog("Command Entered: " +args.get(0)+ " "+ args.get(1));
                        Model.importTeamsFromText(args.get(1));
                        }else{
                        System.out.println("Error: Not a supported file extension.");
                        log.writeErrorLog("Error: Not a supported file extension.");
                        }
                        return;
                    case "IMPORT-WRESTLERS":
                        if(args.get(1).substring(args.get(1).length()-4).equals(".txt")){
                        log.writeActionlog("Command Entered: " +args.get(0)+ " "+ args.get(1));
                        Model.importWrestlersFromText(args.get(1));
                        }else{
                        System.out.println("Error: Not a supported file extension.");
                        log.writeErrorLog("Error: Not a supported file extension.");
                        }
                        return;
                    case "VIEW-WRESTLER":
                    	log.writeActionlog("Command Entered: " +args.get(0)+ " "+ args.get(1));
                        Model.printWrestlerInformation(args.get(1));
                        return;
                    case "COMPARE-WRESTLERS":
                    	Model.compareWrestlersInformation(args.get(1));
                    	return;
                    case "NAME":
                    	log.writeActionlog("Command Entered: " +args.get(0)+ " "+ args.get(1));
                        Model.setTournamentName(args.get(1));
                        return;
                    case "RACE":
                    	switch(args.get(1).toUpperCase()) {
	                    	case "LISTRACERS":
	                    		Race.listRacers();
	                    		return;
	                    	case "START":
	                    		Race.start();
	                    		return;
	                    	case "STATUS":
	                    		Race.getStatus();
	                    		return;
                    	}
                }
            case 3:
            	switch(args.get(0)){
	            	case "RACE":
	            		switch(args.get(1).toUpperCase()) {
	            			case "LAPCOMPLETED":
	            				Race.lapCompleted(Integer.parseInt(args.get(2)));
	            				return;
	            			case "LAPS":
	            				Race.setLapsTotal(Integer.parseInt(args.get(2)));
	            				return;
	            		}
	            }
            case 5:
            	switch(args.get(0)) {
	            	case "RACE":
		            	switch(args.get(1).toUpperCase()) {
		            		case "ADDRACER":
			            		Race.addRacer(args.get(2), args.get(3), Integer.parseInt(args.get(4)));
			            		return;
		            	}
		            	return;
            	}
            case 6:
              log.writeActionlog("Command Entered: " +args.get(0)+ " "+ args.get(1)+ " "+ args.get(2)+ " "+ args.get(3)+ " "+ args.get(4)+ " "+ args.get(5));
            	Model.updateMatch(args.get(1),Integer.parseInt(args.get(2)), Integer.parseInt(args.get(3)), Integer.parseInt(args.get(4)), args.get(5));
                System.out.println("Match Updated!");
                return;
            default:
                printHelp();
                return;
                
    }
    
}
    public static void printHelp(){
        System.out.println("List of Commands and their parameters:\n"
                + "Command Parameter1 Parameter2 Parameter 3...\n"
                + "SAVE //Saves the tournament under the current name\n"
                + "ADVANCE //Makes the next round's matches\n"
                + "START //Makes brackets from the existing wrestlers\n"
                + "VIEW-TEAMS //Displays each team's information\n"
                + "VIEW-WRESTLERS //Displays wrestlers, organizerd by weight class\n"
                + "HELP //Display list of available commands\n"
                + "LOAD TournamentName //Loads all files associated with this tournament name\n"
                + "SAVE TournamentName //Saves all information associated with this tournament to files with tournamentName\n"
                + "NAME TournamentName //Changes the tournament's name\n"
                + "IMPORT-TEAMS FileName //Parses the provided file for Team objects\n"
                + "IMPORT-WRESTLERS FileName //Parses the provided file for Wrestler objects\n"
                + "VIEW-WRESTLER WrestlerName //Looks for the wrestler and prints his/her information\n"
                + "COMPARE-WRESTLERS WrestlerName,WrestlerName //Prints two wrestler's information side-by-side\n"
                + "UPDATE-MATCH matchNumber winningColor greenPoints redPoints fallType(int) fallTime\n"
                + "RACE //View Race commands\n");
                }

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("RaiderSMA");
		BorderPane root = new BorderPane();
		VBox mainMenu = new VBox();
		VBox viewList = new VBox();
		Button viewTeams = new Button();
		Button viewWrestlers = new Button();
		Button importTeams = new Button();
		Button importWrestlers = new Button();
		Button save = new Button();
		Button start = new Button();
		
		//button declarations i've added
		Button advance = new Button();
		Button help = new Button();
		Button update = new Button();
		
		TextField saveTournament = new TextField();
		
		ListView<Team> listView = new ListView<Team>();
		ListView<Wrestler> wrestlerView = new ListView<Wrestler>();
		
		ListView<String> helpView = new ListView<String>();
		
		TextField importWrestlerField = new TextField ();
		Label menu = new Label("Main Menu");
		menu.setStyle("-fx-font-weight: bold; -fx-font: 24 arial");
		// create buttons/textfields for view of team and wrestlers
		
		importTeams.setMinWidth(110);
		importWrestlers.setMinWidth(110);
		viewTeams.setMinWidth(110);
		viewWrestlers.setMinWidth(110);
		saveTournament.setMinWidth(110);
		save.setMinWidth(110);
		start.setMinWidth(110);
		
		//button layouts i've defined
		update.setMinWidth(110);
		help.setMinWidth(110);
		advance.setMinWidth(110);
		advance.setText("Advance");
		help.setText("Help");
		update.setText("Update Match");
		
		save.setText("Save");
		start.setText("Start");
		
		importTeams.setText("Import Teams");
		importWrestlers.setText("Import Wrestlers");
		viewTeams.setText("View Teams");
		viewWrestlers.setText("View Wrestlers");
		saveTournament.setText("Name of Tournament");
		//create the layout of the menu
		GridPane layout = new GridPane();
		layout.setPadding(new Insets(10,10,10,10));
		layout.setMinSize(300, 300);
		layout.setVgap(5);
		layout.setHgap(5);
		layout.setAlignment(Pos.BASELINE_LEFT); 
		layout.add(menu, 1, 0);
		layout.add(importWrestlers, 0, 1);
		layout.add(importTeams, 0, 3);
		layout.add(viewTeams, 0, 4);
		layout.add(viewWrestlers, 0, 2);
		layout.add(save, 0, 5);
		layout.add(saveTournament, 1, 5);
		layout.add(start, 0, 6);
		
		layout.add(advance, 0, 8);
		layout.add(help, 0, 9);
		layout.add(update, 0, 7);
		
		viewTeams.setOnAction(e -> {
			
			ArrayList<Team> show = Model.printTeams();
			for(int i = 0; i < show.size(); i++) {
				listView.getItems().add(show.get(i));
			}
			
		    return;
		});
		
		viewWrestlers.setOnAction(e -> {
			
			ArrayList<Wrestler> wrestlerList = Model.printWrestlers();
			/*for(int i = 0; i < wrestlerListshow.size(); i++) {
				listView.getItems().add(wrestlerListshow.get(i));
			}*/
			for(int i = 0; i < wrestlerList.size(); i++) {
				wrestlerView.getItems().add(wrestlerList.get(i));
			}
			
		    return;
		});
		
		importTeams.setOnAction(e -> {
		
			FileChooser fc = new FileChooser();
			File seletedFile = fc.showOpenDialog(null);
			if(seletedFile != null) {
				String a = seletedFile.getAbsolutePath();
				 if(a.substring(a.length()-4).equals(".txt")) {
					Model.importTeamsFromText(a);
					Alert teamsAlert = new Alert(AlertType.CONFIRMATION);
					teamsAlert.setTitle("Import Alert");
					String info = "Import was a success";
					teamsAlert.setContentText(info);
					teamsAlert.show();
					return;
				 }
				 else {
					 Alert teamsAlert = new Alert(AlertType.ERROR);
					 teamsAlert.setTitle("Import Alert");
					 String info = "IMPORT FAILD! Not a .txt file";
					 teamsAlert.setContentText(info);
					 teamsAlert.show();
					 return;
				 }
					 
				
			}
			else {
				System.out.println("Err");
			}
		});
		
		importWrestlers.setOnAction(e -> {
			FileChooser fc = new FileChooser();
			File seletedFile = fc.showOpenDialog(null);
			if(seletedFile != null) {
				String a = seletedFile.getAbsolutePath();
				 if(a.substring(a.length()-4).equals(".txt")) {
					int check = Model.importWrestlersFromText(a);
					Alert wrestlerAlert = new Alert(AlertType.CONFIRMATION);
					wrestlerAlert.setTitle("Import Alert");
					String info = "Import was a success";
					wrestlerAlert.setContentText(info);
					wrestlerAlert.show();
					return;
				 }
				 else {
					 Alert wrestlerAlert = new Alert(AlertType.ERROR);
					 wrestlerAlert.setTitle("Import Alert");
					 String info = "IMPORT FAILD! Not a .txt file";
					 wrestlerAlert.setContentText(info);
					 wrestlerAlert.show();
					 return;
				 }
					 
				
			}
			else {
				System.out.println("Err");
			}
		});
		
		
		save.setOnAction(e -> {
			String textbox = saveTournament.getText();
			if(textbox.equals("Name of Tournament")) {
				Model.saveTournament();
			}
			else {
				Model.saveTournament(textbox);
			}
		});
		
		start.setOnAction(e -> {
			int check = Model.generateTournament();
			if(check == 0) {
				Alert genTourn0 = new Alert(AlertType.ERROR);
				genTourn0.setTitle("Generate Tournament ALert");
				String genTourn0Info = "Error: No Wrestlers or Teams Found \n"
						+ "Please add wrestlers/teams before generating a tournament.";
				genTourn0.setContentText(genTourn0Info);
				genTourn0.show();	
			}
			else if (check == 1) {
				return;
			}
			else if (check == 2) {
				Alert genTour2 = new Alert(AlertType.CONFIRMATION);
				genTour2.setTitle("Generate Tournament Success!");
				String genTourn2Info = "Generating the tourament was a success!";
				genTour2.setContentText(genTourn2Info);
				genTour2.show();
			}
			else {
				return;
			}
		});
		
		advance.setOnAction(e -> {
			Model.advanceTournament();
			Alert adv = new Alert(AlertType.CONFIRMATION);
			adv.setTitle("Advance success");
			String advInfo = "The tournament has been successfully advanced!";
			adv.setContentText(advInfo);
			adv.show();
		});
		
		help.setOnAction(e ->{
			helpView.getItems().add("Import Wrestlers - Choose a text file with wrestler data to import (You must have teams imported first)");
			helpView.getItems().add("View Wrestlers - Displays information on all imported wrestlers");
			helpView.getItems().add("Import teams - Choose a text file with team data to import");
			helpView.getItems().add("View Teams - Displays information on all imported teams");
			helpView.getItems().add("Save - Save the current tournament");
			helpView.getItems().add("Start - Starts the tournament once wrestlers and teams have been imported");
			helpView.getItems().add("Advance - Advances the tournament to the next match (you will need to have started the tournament and updated the current match)");
			helpView.getItems().add("Update Match - updates the current match");
		});
		
		update.setOnAction(e ->{
			BorderPane root2 = new BorderPane();
			VBox fields = new VBox();
			GridPane pane = new GridPane();
			
			TextField colorField = new TextField();
			TextField greenPts = new TextField();
			TextField redPts = new TextField();
			TextField fallType = new TextField();
			TextField fallTime = new TextField();
			Button confirm = new Button();
			
			Label cfLabel = new Label("Winning Color");
			Label gpLabel = new Label("Green Points");
			Label rpLabel = new Label("Red Points");
			Label ftyLabel = new Label("Fall Type");
			Label ftiLabel = new Label("Fall Time");
			
			colorField.setMinWidth(110);
			greenPts.setMinWidth(110);
			redPts.setMinWidth(110);
			fallType.setMinWidth(110);
			fallTime.setMinWidth(110);
			confirm.setMinWidth(110);
			confirm.setMinHeight(50);
			confirm.setText("Update");
			
			pane.setPadding(new Insets(10, 10, 10, 10));
			pane.setMinSize(300, 300);
			pane.setVgap(5);
			pane.setHgap(5);
			pane.setAlignment(Pos.BASELINE_LEFT);
			pane.add(colorField, 0, 1);
			pane.add(greenPts, 0, 2);
			pane.add(redPts, 0, 3);
			pane.add(fallType, 0, 4);
			pane.add(fallTime, 0, 5);
			pane.add(confirm, 0, 6);
			
			pane.add(cfLabel, 1, 1);
			pane.add(gpLabel, 1, 2);
			pane.add(rpLabel, 1, 3);
			pane.add(ftyLabel, 1, 4);
			pane.add(ftiLabel, 1, 5);
			
			confirm.setOnAction(e2 ->{
				String wc = colorField.getText();
				int gp = Integer.parseInt(greenPts.getText());
				int rp = Integer.parseInt(redPts.getText());
				int ftype = Integer.parseInt(fallType.getText());
				String ftime = fallTime.getText();
				Model.updateMatch(wc, gp, rp, ftype, ftime);
			});
			
			Stage stage2 = new Stage();
			fields.getChildren().addAll(pane);
			root2.setCenter(fields);//put content here(pane)
			
			stage2.setScene(new Scene(root2, 300, 300));
			stage2.setTitle("Update Match");
			stage2.show();
			
		});
		
		mainMenu.getChildren().addAll(layout);
		viewList.prefWidth(100);
		viewList.getChildren().addAll(listView, wrestlerView, helpView);
		root.setLeft(mainMenu);
		root.setCenter(viewList);
		
		stage.setScene(new Scene(root, 700, 700));
		
		stage.show();
		
	}
}