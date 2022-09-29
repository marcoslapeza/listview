package com.example.listview;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {

        //Nodes
        Text txtNote = new Text("Notificacion");
        txtNote.setFont(Font.font(25));

        TextField fldAdd = new TextField();
        fldAdd.setPromptText("Escribe aqui");

        Button btnAdd = new Button("Annadir");
        btnAdd.setMinWidth(85);
        Button btnAddAll = new Button("Annadir Var.");
        btnAddAll.setMinWidth(85);
        Button btnRemove = new Button("Borrar");
        btnRemove.setMinWidth(85);
        Button btnRemoveAll = new Button("Borrar Var.");
        btnRemoveAll.setMinWidth(85);
        Button btnSort = new Button("Ordenar");
        btnSort.setMinWidth(85);
        Button btnReplace = new Button("Remplazar");
        btnReplace.setMinWidth(85);
        Button btnReplaceAll = new Button("Remplazar Var.");
        btnReplaceAll.setMinWidth(85);

        //ListView and Observable List
        ListView<String> listList = new ListView<>();
        ObservableList<String> componentes = FXCollections.observableArrayList(
                "placa",
                "procesador",
                "fuente",
                "caja");

        listList.setItems(componentes);
        listList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listList.setMaxSize(300, 300);

        //ObservableList ChangeListener
        componentes.addListener(new ListChangeListener() {

            @Override
            public void onChanged(ListChangeListener.Change change) {

                txtNote.setText("Lista cambiada con exito");
            }
        });


        //Button Add
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {

                if (!fldAdd.getText().isEmpty()) {
                    if (fldAdd.getText().contains(",")) {
                        txtNote.setText("Para mas de una seleccion utilice Annadir Var.");
                    } else {
                        componentes.add(fldAdd.getText());
                    }
                } else {
                    txtNote.setText("Introduzca informacion en el campo de busqueda");
                }
                listList.getSelectionModel().clearSelection();
            }
        });

        //Button AddAll
        btnAddAll.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {

                if (!fldAdd.getText().isEmpty()) {
                    if (fldAdd.getText().contains(",")) {
                        ObservableList temp = FXCollections.observableArrayList(fldAdd.getText().split(","));
                        componentes.addAll(temp);
                    } else {
                        txtNote.setText("Separa la informacion con (,)");
                    }
                } else {
                    txtNote.setText("Introduzca informacion en el campo de busqueda");
                }
                listList.getSelectionModel().clearSelection();

            }
        });

        //Button Remove
        btnRemove.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {

                if (!listList.getSelectionModel().isEmpty()) {
                    if (listList.getSelectionModel().getSelectedItems().size() > 1) {
                        txtNote.setText("Utiliza Borrar Var.");
                    } else {
                        componentes.remove(listList.getSelectionModel().getSelectedItem());
                    }
                } else {
                    txtNote.setText("Selecciona un elemento");
                }
                listList.getSelectionModel().clearSelection();
            }
        });

        //Button RemoveAll
        btnRemoveAll.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                if (!listList.getSelectionModel().isEmpty()) {
                    ObservableList temp = FXCollections.observableArrayList(listList.getSelectionModel().getSelectedItems());
                    componentes.removeAll(temp);
                } else {
                    txtNote.setText("Selecciona mas de un elemento");
                }
                listList.getSelectionModel().clearSelection();
            }
        });

        //Button Sort
        btnSort.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                FXCollections.sort(componentes);
                listList.getSelectionModel().clearSelection();
            }
        });

        //Button Replace All
        btnReplace.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {

                if (listList.getSelectionModel().isEmpty() || fldAdd.getText().isEmpty()) {
                    txtNote.setText("Seleccione algun campo");
                } else {
                    if (!(listList.getSelectionModel().getSelectedItems().size() > 1)) {
                        componentes.set(listList.getSelectionModel().getSelectedIndex(), fldAdd.getText());
                        listList.getSelectionModel().clearSelection();
                    } else {
                        txtNote.setText("Use Remplazar Var.");
                    }

                }
            }
        });

        //Button Replace All
        btnReplaceAll.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                if (listList.getSelectionModel().isEmpty() || fldAdd.getText().isEmpty()) {
                    txtNote.setText("Selecciona mas de uno");
                } else {
                    FXCollections.replaceAll(componentes, listList.getSelectionModel().getSelectedItem(), fldAdd.getText());
                    listList.getSelectionModel().clearSelection();
                }
            }
        });


        //Pane Left Right
        VBox right = new VBox(10);
        right.setPadding(new Insets(10));
        right.setAlignment(Pos.CENTER);
        right.getChildren().addAll(fldAdd, btnAdd, btnAddAll, btnRemove, btnRemoveAll, btnReplace, btnReplaceAll, btnSort);

        //Root Node
        BorderPane root = new BorderPane();

        root.setCenter(listList);
        root.setRight(right);
        root.setBottom(txtNote);


        Scene scene = new Scene(root, 800, 500);

        primaryStage.setTitle("Marcos Arroyo Rivas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

