package com.bobandata.iot.basedb.ui.controller;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import de.felixroske.jfxsupport.FXMLController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

@FXMLController
public class WebViewController implements Initializable {
    @FXML
    private WebView webView;
    @FXML
    private AnchorPane pane;

    public WebViewController() {
    }

    public void initialize(URL location, ResourceBundle resources) {
        WebEngine engine = this.webView.getEngine();
        engine.load("http://localhost:9090/");
//        URL urlHello = getClass().getResource("/static/index.html");
//        engine.load(urlHello.toExternalForm());
        this.webView.prefWidthProperty().bind(this.pane.widthProperty());
        this.webView.prefHeightProperty().bind(this.pane.heightProperty());
    }
}
