package baby_After_Bachmann_worksheet;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;

//public class TableList {
//
//	// Im Main habe ich dies Liste auch, dort ist Sie Final... 
//	private ObservableList<Babynamen> data = FXCollections.observableArrayList();
//	
//	
//	
//	public TableView<Babynamen> getTable() {
//		return table; 
//		}
//	
//	 TableView<Babynamen> table = new TableView<Babynamen>();
//	 private TableColumn columnF1;
//	 private TableColumn columnF2;
//	 private TableColumn columnF3;
//	 private TableColumn columnF4;
//	 private TableColumn columnF5;
//	 private TableColumn columnF6;
//	
//	
//
//	
//	
//	
//	
////	
////	TableColumn columnF1 = new TableColumn("name");
////	TableColumn columnF2 = new TableColumn("geschlecht");
////	TableColumn columnF3 = new TableColumn("rang ch");
////	TableColumn columnF4 = new TableColumn("rang alle");
////	TableColumn columnF5 = new TableColumn("rang Welt");
////	TableColumn columnF6 = new TableColumn("biblisch");
////	
//	
//	
//	
////	private Node createTableView() {
//
//	columnF1.setCellValueFactory(
//			new PropertyValueFactory<Babynamen,String>("name"));
//
//	columnF1.setCellFactory(TextFieldTableCell.forTableColumn());
//	columnF1.setOnEditCommit(
//			new EventHandler<CellEditEvent<Babynamen, String>>() {
//				@Override
//				public void handle(CellEditEvent<Babynamen, String> t) {
//					((Babynamen) t.getTableView().getItems().get(
//							t.getTablePosition().getRow())
//							).setName(t.getNewValue());
//				}
//			}
//			);	
//
//	
//	columnF2.setCellValueFactory(
//			new PropertyValueFactory<>("geschlecht"));
//	columnF2.setCellFactory(TextFieldTableCell.forTableColumn());
//	columnF2.setOnEditCommit(
//			new EventHandler<CellEditEvent<Babynamen, String>>() {
//				@Override
//				public void handle(CellEditEvent<Babynamen, String> t) {
//					((Babynamen) t.getTableView().getItems().get(
//							t.getTablePosition().getRow())
//							).setName(t.getNewValue());
//				}
//			}
//			);
//
//	
//	columnF3.setCellValueFactory(
//			new PropertyValueFactory<>("ranglisteSchweiz"));
//	columnF3.setCellFactory(TextFieldTableCell.forTableColumn());
//	columnF3.setOnEditCommit(
//			new EventHandler<CellEditEvent<Babynamen, String>>() {
//				@Override
//				public void handle(CellEditEvent<Babynamen, String> t) {
//					((Babynamen) t.getTableView().getItems().get(
//							t.getTablePosition().getRow())
//							).setName(t.getNewValue());
//				}
//			}
//			);
//
//	
//	columnF4.setCellValueFactory(
//			new PropertyValueFactory<>("ranglisteAllerNamen"));
//	columnF4.setCellFactory(TextFieldTableCell.forTableColumn());
//	columnF4.setOnEditCommit(
//			new EventHandler<CellEditEvent<Babynamen, String>>() {
//				@Override
//				public void handle(CellEditEvent<Babynamen, String> t) {
//					((Babynamen) t.getTableView().getItems().get(
//							t.getTablePosition().getRow())
//							).setName(t.getNewValue());
//				}
//			}
//			);
//
//	
//	columnF5.setCellValueFactory(
//			new PropertyValueFactory<>("ranglisteWelt"));
//	columnF5.setCellFactory(TextFieldTableCell.forTableColumn());
//	columnF5.setOnEditCommit(
//			new EventHandler<CellEditEvent<Babynamen, String>>() {
//				@Override
//				public void handle(CellEditEvent<Babynamen, String> t) {
//					((Babynamen) t.getTableView().getItems().get(
//							t.getTablePosition().getRow())
//							).setName(t.getNewValue());
//				}
//			}
//			);
//
//	
//	columnF6.setCellValueFactory(
//			new PropertyValueFactory<>("biblisch"));
//	columnF6.setCellFactory(TextFieldTableCell.forTableColumn());
//	columnF6.setOnEditCommit(
//			new EventHandler<CellEditEvent<Babynamen, String>>() {
//				@Override
//				public void handle(CellEditEvent<Babynamen, String> t) {
//					((Babynamen) t.getTableView().getItems().get(
//							t.getTablePosition().getRow())
//							).setName(t.getNewValue());
//				}
//			}
//			);
//		}
//	}
//
//	table.setItems(data);
//	table.getColumns().addAll(
//			columnF1, columnF2, columnF3, columnF4, columnF5, columnF6);
//
//	
//	VBox vBox = new VBox();
//	Group root = new Group();
//	
//	// Der Erste WErt der VBox von 555 zählt nicht. der Zweite kürzt die Höhe
//	
//	vBox.setPrefSize(555,250);
//	vBox.getChildren().addAll(table);
//	root.getChildren().add(vBox);
//	//run in background thread
//	new Thread() {
//		@Override
//		public void run() {
//			readCSV();
//		};
//	}.start();
//	return vBox;
//}