package org.example.finalrmicallbacks.client;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.finalrmicallbacks.HelloApplication;
import org.example.finalrmicallbacks.MyApp;
import org.example.finalrmicallbacks.interfaces.ClientInt;
import org.example.finalrmicallbacks.interfaces.ServerInt;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;

public class ChatPageController extends UnicastRemoteObject implements ClientInt {
    private String name;
    @FXML
    private Button sendBtn;
    @FXML
    private Label nameTextField;
    @FXML
    private ScrollPane chatScrollPane;
    @FXML
    private VBox messagesVBox;
    @FXML
    private TextField messageTextField;

    ServerInt server;
    Stage primaryStage;

    public ChatPageController() throws RemoteException {
    }

    public ChatPageController(String name)throws RemoteException {
        this.name = name;

    }

    @FXML
    public void initialize() {
        nameTextField.setText(name);
        try {

            Registry reg = LocateRegistry.getRegistry("127.0.0.1");
            server = (ServerInt) reg.lookup("service");
            server.register(this);
            System.out.println(reg.list()[0]);

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onSendBtnClicked(Event event) {
        String message = messageTextField.getText();
        if (!message.isEmpty()) {
            try {
                server.tellOthers(message);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            //appendMessageInList(message);
            messageTextField.setText("");
        }

    }

    @FXML
    public void onMouseEnteredSendBtn(Event event) {
    }

    private void appendMessageInList(String messageString) {
        try {
            Node node = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("message-item.fxml")));

            Label messagelabel = (Label) node.lookup("#messageLabel");
            messagelabel.setText(messageString);
            chatScrollPane.setVvalue(1D);

            messagesVBox.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receiveMessage(String message) throws RemoteException {
        Platform.runLater(() -> {
            appendMessageInList(message);
        });
    }
}