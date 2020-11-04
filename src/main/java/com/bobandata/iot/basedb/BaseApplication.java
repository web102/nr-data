package com.bobandata.iot.basedb;

import com.bobandata.iot.basedb.ui.view.CustomSplash;
import com.bobandata.iot.basedb.ui.view.MainWebView;
import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import de.felixroske.jfxsupport.SplashScreen;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.bobandata.iot.entity.dms")
public class BaseApplication extends AbstractJavaFxApplicationSupport {
    public static void main(String[] args) {
        launchApp(BaseApplication.class, MainWebView.class, new CustomSplash(), args);
    }
}
