package baby_After_Bachmann_worksheet;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.Pair;

public class Main extends Application {	
	
	private TableView<Babynamen> table = new TableView<Babynamen>();
	private final ObservableList<Babynamen> data = FXCollections.observableArrayList();
	final HBox hb = new HBox();
	
	List<Babynamen> names;
	String CsvFile;
	
	//main timeline
    private Timeline timeline;
 
    //variable for storing actual frame
    private Integer i=0;

	// Für Passwort eingabe: 	
	private String user = "ff";
	private String pw = "ff";
	private String checkUser, checkPw;

	
	//Variablen für GUI Elemente global deklarieren (sonst kann man ja gar nicht drauf zugreiffen für die Suchabfrage)
	RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
	TextField textProposal;
	Button buttonboy, buttongirl;
	// Initialisiere "DatenTräger"
	CsvBabynamen cb = new CsvBabynamen();
	
	@Override
	public void start(Stage stage)  {	
		stage.setTitle("Baby in ZusammenArbeit mit Hr. Bachmann");
   	
		final Scene scene = new Scene(new Group());	
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(7, 7, 7, 7));		
		vbox.getChildren().add(createMenuPane());
		vbox.getChildren().add(topGrid());				
		vbox.getChildren().add(createGenderChoosePane());
		vbox.getChildren().add(createRadioButtonPane());
		vbox.getChildren().add(createletterProposalPane());
		vbox.getChildren().add(createAddingThingstoList());
 		vbox.getChildren().add(createTableView());		
		vbox.getChildren().add(createBottomLabel());
		scene.getStylesheets().add("styly.css");
		
	// scene.setFont(new Font("Arial", 20));
		
		((Group) scene.getRoot()).getChildren().addAll(vbox);

		
		vbox.setAlignment(Pos.CENTER);
//	       VBox.setMargin(stackPane, new Insets(10, 10, 10, 10));
//	       VBox.setMargin(controlButton, new Insets(10, 10, 10, 10));
//	       stackPane.setPrefSize(300, 150);
//	       stackPane.setStyle("-fx-background-color: Gainsboro;-fx-border-color: blue;");
		 //  HBox.setHgrow(textProposal, Priority.ALWAYS);
		
	
		
		scene.setFill(Color.WHITE);
		stage.setScene(scene);
		stage.show();;	
	}	
	
	
	public static void main(String[] args) {
		// launch(args);
		Application.launch(args);
	}

	public void params ()  {	
		try {
	//		List<Babynamen> names = cb.readPersonFile("src/Babynamen_bereinigt.csv", ";");
			Suchparameter params = new Suchparameter();
			params.setBeginntMit(textProposal.getText());
			params.setRanglisteSchweiz(radioButton1.getText());
			params.setRanglisteAllerNamen(radioButton2.getText());
			params.setRanglisteWelt(radioButton3.getText());
			params.setBiblisch(radioButton4.getText());
			printName(cb.search(params));

		} catch (Exception e) {
			//		e.printStackTrace();
		}
	}

	public  void printName(List<Babynamen> names) {
		  table.getItems().clear();
		for (Babynamen name : names){
			try {
				System.out.println(name);
				table.getItems().add(name);
			}
			catch(Exception ex) {
				ex.getMessage();
			}
		}         
	}	

	private Node createTableView() {
		
		TableColumn columnF1 = createTableColumn("name");
		TableColumn columnF2 = createTableColumn("geschlecht");
		TableColumn columnF3 = createTableColumn("ranglisteSchweiz");
		TableColumn columnF4 = createTableColumn("ranglisteAllerNamen");
		TableColumn columnF5 = createTableColumn("ranglisteWelt");
		TableColumn columnF6 = createTableColumn("biblisch");

	table.setItems(data);
	table.getColumns().addAll(
			columnF1, columnF2, columnF3, columnF4, columnF5, columnF6);

	
	VBox vBox = new VBox();
	Group root = new Group();
	
	// Der Erste WErt der VBox von 555 zählt nicht. der Zweite kürzt die Höhe
	
	vBox.setPrefSize(555,250);
	vBox.getChildren().addAll(table);
	root.getChildren().add(vBox);
	//run in background thread
	new Thread() {
		@Override
		public void run() {
			readCSV();
		};
	}.start();
	return vBox;
}


	private TableColumn createTableColumn(String name) {
		TableColumn tc = new TableColumn(name);
		tc.setCellValueFactory(
				new PropertyValueFactory<Babynamen,String>(name));

		tc.setCellFactory(TextFieldTableCell.forTableColumn());
		tc.setOnEditCommit(
				new EventHandler<CellEditEvent<Babynamen, String>>() {
					@Override
					public void handle(CellEditEvent<Babynamen, String> t) {
						((Babynamen) t.getTableView().getItems().get(
								t.getTablePosition().getRow())
								).setName(t.getNewValue());
					}
				}
				);
		return tc;
	}

public Node createAddingThingstoList() {
	
	final TextField addName = new TextField();
	addName.setMaxWidth(59);
	addName.setPromptText("name");

	final TextField addGeschlecht = new TextField();
	addGeschlecht.setMaxWidth(59);
	addGeschlecht.setPromptText("geschlecht");

	final TextField addRanglisteSchweiz = new TextField();
	addRanglisteSchweiz.setMaxWidth(59);
	addRanglisteSchweiz.setPromptText("ranglisteSchweiz");

	final TextField addRanglisteAllerNamen = new TextField();
	addRanglisteAllerNamen.setMaxWidth(59);
	addRanglisteAllerNamen.setPromptText("ranglischteAllerNamen");

	final TextField addRanglisteWelt = new TextField();
	addRanglisteWelt.setMaxWidth(59);
	addRanglisteWelt.setPromptText("ranglisteWelt");

	final TextField addBiblisch = new TextField();
	addBiblisch.setMaxWidth(59);
	addBiblisch.setPromptText("biblisch");

	final Button addButton = new Button("Add");
	addButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			printName(cb.add(addName.getText(), addGeschlecht.getText(), addRanglisteSchweiz.getText(), addRanglisteAllerNamen.getText(), addRanglisteWelt.getText(), addBiblisch.getText()));
			
		}
	});

	Group root = new Group();
	VBox vBox = new VBox();
	hb.getChildren().addAll(addName, addGeschlecht,addRanglisteSchweiz,addRanglisteAllerNamen,addRanglisteWelt, addBiblisch, addButton);

	
	
	vBox.getChildren().add(hb);
	root.getChildren().add(vBox);
	//run in background thread
//	new Thread() {
//		@Override
//		public void run() {
//			readCSV();
//		};
//	}.start();
	return vBox;
 
}
	public void readCSV() {
		CsvFile = "src/babynamen_bereinigt.csv";
		String FieldDelimiter = ";";		 
		BufferedReader br;		 
		try {
			br = new BufferedReader(new FileReader(CsvFile));		 
			String line;
			while ((line = br.readLine()) != null) {
				String[] fields = line.split(FieldDelimiter, -1);

				System.out.println("Das CSV wird gelesen - und Erstellt VOID readCSV ");
				Babynamen babynamen = new Babynamen(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5]);
				data.add(babynamen);
			}
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Main.class.getName())
			.log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Main.class.getName())
			.log(Level.SEVERE, null, ex);
		}
	}

	private Node createMenuPane() {
		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("_Programm");
		menuFile.setMnemonicParsing(true);			
		MenuItem menuLogin = createMenuItem("LogIn", event -> loginDecision(), KeyCode.O, KeyCombination.CONTROL_DOWN);
		MenuItem menuOpen = createMenuItem("Daten Importieren", event -> loadDecision(), KeyCode.O, KeyCombination.CONTROL_DOWN);
		MenuItem menuSave = createMenuItem("Entscheidungen _speichern", event -> saveDecision(), KeyCode.S, KeyCombination.CONTROL_DOWN);		
		MenuItem menuExit = createMenuItem("_Beenden", event -> exitDecision(), KeyCode.F4, KeyCombination.ALT_DOWN);
	
		menuFile.getItems().addAll(menuLogin, menuOpen,menuSave, menuExit);
		menuBar.getMenus().addAll(menuFile);   
		return menuBar;		
	}
	private MenuItem createMenuItem(String menuText, EventHandler<ActionEvent> value, KeyCode code, KeyCombination.Modifier... modifiers) {
		MenuItem item = new MenuItem(menuText);
		item.setAccelerator(new KeyCodeCombination(code, modifiers));
		item.setOnAction(value);
		return item;
	}
	private void exitDecision() {
		Optional<ButtonType> result = displayAlert(Alert.AlertType.CONFIRMATION, "Baby verlassen?");
		if (result.isPresent() && result.get() == ButtonType.OK) {
			System.exit(0);
		}
	} 

		
	private void loadDecision() {

		
		File file = new File ("src/speicherungvonProgramm.txt");
		try 
		
		// das folgend ausgeklammerte, wäre in einer richtigen tryFunktion.
		 // String fileName =creatFileName();
		//		decisionData.loadDecisions(fileName);
	    //        updateAmount();
	    //        displayAlert(Alert.AlertType.CONFIRMATION, "Datei " + fileName + " geladen.");
		
		(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String zeile = reader.readLine(); 
			while (zeile != null) {
				System.out.println(zeile);
				zeile = reader.readLine(); 
				System.out.println("jetzt versucht er einzulesen."); 
			}
		} catch (FileNotFoundException e) {
			System.out.println("src/files/datei.txt nicht gefunden");
		} catch (IOException e) {
			e.printStackTrace();   
			readCSV();
		}
	}

    /**
     * Serialize the data to a file
     */
    private void saveDecision() {
    	File file = new File ("src/speicherungvonProgramm.txt");
 
        try 
              (FileWriter fw = new FileWriter(file)) {
            	displayAlert(Alert.AlertType.CONFIRMATION, "gespeichert!!");
            	fw.write(toString());
            
            	// HIer speichert er blos die 
            	
            	// Die Speicherung Besser machen. ) 
            } catch (IOException e) {
            	e.printStackTrace(); 
            }
        }


	private void loginDecision() {

		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Login Dialog");
		ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(25,25,25,25));

		TextField txtUserName = new TextField();
		txtUserName.setPromptText("Username");

		PasswordField passwordfield = new PasswordField();
		passwordfield.setPromptText("Password");

		Label lblMessage = new Label(); 
	//	lblMessage.setFont(new Font("Arial", 20));

		grid.add(new Label("Username:"), 0, 0);
		grid.add(txtUserName, 1, 0);
		grid.add(new Label("Password:"), 0, 1);
		grid.add(passwordfield, 1, 1);
		grid.add(lblMessage, 1, 2);

		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);

		//Do some validation (using the Java 8 lambda syntax).
		txtUserName.textProperty().addListener((observable, oldValue, newValue) -> {
			loginButton.setDisable(newValue.trim().isEmpty());
		});
		dialog.getDialogPane().setContent(grid);
		//Request focus on the username field by default.
		Platform.runLater(() -> txtUserName.requestFocus());

		// Here There ist the Hot Passage, how to Check the Passowrt... 

		//Convert the result to a txtUserName-password-pair when the login button is clicked.
		dialog.setResultConverter(dialogButton1 -> {
			checkUser = txtUserName.getText().toString();
			checkPw = passwordfield.getText().toString();
			if (dialogButton1 == loginButtonType && checkUser.equals(user) && checkPw.equals(pw)) {
				System.out.println("Juhuuu ZUTRITT");	
			
			}
			return null;		
		});
		Optional<Pair<String, String>> result = dialog.showAndWait();
	}
		

	private Optional<ButtonType> displayAlert(Alert.AlertType type, String alertText) {
		Alert alert = new Alert(type);
		alert.setTitle("Baby");
		alert.setHeaderText(alertText);
		return alert.showAndWait();
	}	

	
	private Node topGrid() {
		
		timeline = new Timeline(); 
		Image image = new Image("ZHAW_LOGO.png");
		ImageView iv2 = new ImageView();
		iv2.setPreserveRatio(true);
		iv2.setFitWidth(70);
		iv2.setSmooth(true);
		iv2.setCache(true);
		iv2.setImage(image);
		
		Text babyNameLabel = new Text("BabyNamen"); 
		babyNameLabel.setFont(Font.font(56));
		
		
		StackPane topTextstack = new StackPane(babyNameLabel);
		StackPane logoStack = new StackPane(iv2);
		
		topTextstack.setAlignment(Pos.CENTER_LEFT);
		
		// Hier wird die Weite vom Text und des Ortes wo sie ist genmmen. 
		double sceneWidth = topTextstack.getWidth();
		double textWidth = babyNameLabel.getLayoutBounds().getWidth();

 		// Start und Endpunkt für LOGO-Annimation 
	//	Duration startDuration = Duration.ZERO;

		Duration SecondPartDuration = Duration.seconds(6);
		Duration ArrivalDuration = Duration.seconds(9);
		Duration ActionDuration = Duration.seconds(9.05);
		Duration EndofActionDuration = Duration.seconds(9.25);
		Duration EndofActionSleep = Duration.seconds(9.38);
		Duration GoBackDuration = Duration.seconds(9.4);
		Duration AlmostFinish = Duration.seconds(9.5);
		Duration DefineFini = Duration.seconds(9.51);
		
		Duration AtendDuration = Duration.seconds(13);  // ist plus 13
	//	Duration breakDuratio = Duration.seconds(10);
	 //   Duration duration = Duration.millis(3800); 
		
		
		//Setting Start- und Endpunkt vom Babyname Label. 
		KeyValue startKeyValue = new KeyValue(babyNameLabel.translateXProperty(), sceneWidth);
		KeyFrame startKeyFrame = new KeyFrame(SecondPartDuration, startKeyValue);
	    timeline.getKeyFrames().add(startKeyFrame);
		
		
		KeyValue endKeyValue = new KeyValue(babyNameLabel.translateXProperty(), 0.53 * textWidth);
		KeyFrame endKeyFrame = new KeyFrame(ArrivalDuration, endKeyValue);
		timeline.getKeyFrames().add(endKeyFrame);
		
		
		KeyValue start2KeyValue = new KeyValue(babyNameLabel.translateXProperty(), 0.53 * textWidth);
		KeyFrame start2KeyFrame = new KeyFrame(GoBackDuration, start2KeyValue);
		timeline.getKeyFrames().add(start2KeyFrame);
	
		
		KeyValue end2KeyValue = new KeyValue(babyNameLabel.translateXProperty(), sceneWidth);
		KeyFrame end2KeyFrame = new KeyFrame(AtendDuration, end2KeyValue);
		timeline.getKeyFrames().add(end2KeyFrame);
		
		
		// Animation für Logo 
		KeyValue start3KeyValue = new KeyValue(logoStack.scaleXProperty(),1);
		KeyValue keyValueY_LOGO = new KeyValue(logoStack.scaleYProperty(), 1);
		KeyFrame key3StartLogoActionFrame = new KeyFrame(ActionDuration, start3KeyValue, keyValueY_LOGO);
		timeline.getKeyFrames().add(key3StartLogoActionFrame); 
				 
				 
		KeyValue start4KeyValue = new KeyValue(logoStack.scaleXProperty(), 1.4);
		KeyValue start4KeyVOGO = new KeyValue(logoStack.scaleYProperty(), 1.4);
        KeyFrame key4EndLogoActionFrame = new KeyFrame(EndofActionDuration, start4KeyValue, start4KeyVOGO);
        timeline.getKeyFrames().add(key4EndLogoActionFrame);  
        
              
        EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {   
            	System.out.println("jetst countet er im ActionHandler FX_Label Decoraton");  
            	timeline.setDelay(Duration.millis(00));
//              timeline.pause();
            	
            	// Hier ne While Funtkion? 
            	
            }   

        };
       
        KeyValue EndofActionSleepX = new KeyValue(logoStack.scaleXProperty(),1);
    		KeyValue EndofActionSleepY = new KeyValue(logoStack.scaleYProperty(), 1);
    		KeyFrame EndofActionSleepFrame = new KeyFrame(EndofActionSleep,onFinished, EndofActionSleepX, EndofActionSleepY);
    		timeline.getKeyFrames().add(EndofActionSleepFrame); 
        
        


    //    timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setCycleCount(3); // Instead of Indefinite we stop after 3 Cyrcles. 
        timeline.setDelay(Duration.millis(100));  // Only Once, at beginning. 
   //     timeline.setAutoReverse(true);
        timeline.play();


        GridPane grid = new GridPane();
        GridPane.setHgrow(topTextstack, Priority.ALWAYS);
        GridPane.setVgrow(topTextstack, Priority.ALWAYS);
      
        GridPane.setHgrow(logoStack, Priority.SOMETIMES); 
        GridPane.setVgrow(logoStack, Priority.SOMETIMES);

        grid.add(topTextstack, 0, 0);
        grid.add(logoStack, 1, 0);
		return grid; 
	}
	

	private VBox createGenderChoosePane() {
		Label genderchoostext = new Label("Hier kann das Geschlecht gewählt werden ");
		genderchoostext.setPadding(new Insets(5,5,5,5));
		genderchoostext.setId("genderchoostextcss"); 
		buttonboy = new Button("Junge");	
		buttonboy.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	buttonboy.setText("Junge Gewählt");
            	buttongirl.setText("Mädchen nicht Gewählt");
            }
        });		
		buttongirl = new Button("Mädchen");
		buttongirl.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	buttongirl.setText("Mädchen Gewählt");
            	buttonboy.setText("Junge nicht Gewählt");
            }
        });		
		
		HBox GenderChoiseBox = new HBox(7, createSpacer(), buttonboy, buttongirl, createSpacer());
		VBox GenderButton2 = new VBox(genderchoostext, GenderChoiseBox);
		return GenderButton2; 
	}
	
	

	private GridPane createRadioButtonPane() {
		Label namechoostext = new Label("Wie möchten Sie Ihren Namen sonst noch sortieren?");
		radioButton1 = new RadioButton("Top200 Schweiz im Jahr 2017");
		radioButton2 = new RadioButton("Top100 aller Namen in der Schweiz");
		radioButton3 = new RadioButton("Top100 aller Namen auf der Welt");
		radioButton4 = new RadioButton("Biblischer Name");
		
		
		// Set on Action? 
		
		
		

		GridPane grid = new GridPane();
		grid.add(namechoostext, 0, 0);
		grid.add(radioButton1, 0, 1);
		grid.add(radioButton2, 1, 1);
		grid.add(radioButton3, 0, 2);
		grid.add(radioButton4, 1, 2);
		return grid; 	
	}

	private HBox createletterProposalPane () {
		final Label labelProposal = new Label("Anfangsbuchstage:");
		textProposal = new TextField();
		final Button buttonProposal = new Button("Suche");
		EventHandler<ActionEvent> onTextEnter = event -> {
			if (textProposal.getText().isEmpty()) {
				displayAlert(Alert.AlertType.INFORMATION, "Bitte Buchstage eingeben!");
			} else 
				System.out.println("Jetzt müsste diese Anfrage noch wo verbunden werden...");
			params();
		};
		buttonProposal.setOnAction(onTextEnter);
		textProposal.setOnAction(onTextEnter);
		HBox.setHgrow(textProposal, Priority.ALWAYS);
		HBox genderchoose1 = new HBox(7, labelProposal, textProposal, buttonProposal); 
		return genderchoose1;
	}

	private Rectangle createSpacer() {
		Rectangle rect = new Rectangle(15, 20);
		rect.setFill(Color.TRANSPARENT);
		return rect;
	}
	public Label createBottomLabel() {
		Label bottomText = new Label("BabyNamenChooser App Copywright by Markus Bolliger and Daniel da Silva");
		bottomText.setId("bottomTextcss");
		return bottomText;
	}
	private Pane createBottomPane() {
        Label l = new Label("Anzahl Inhalte von was");
        return new HBox(l);
    }
}