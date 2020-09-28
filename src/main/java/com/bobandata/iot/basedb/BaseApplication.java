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
    private static SplashScreen splashScreen;
    private static Class<? extends AbstractFxmlView> savedInitialView;
    private static BooleanProperty appCtxLoaded = new SimpleBooleanProperty(false);

    public static void main(String[] args) {
        splashScreen = new CustomSplash();
        savedInitialView = MainWebView.class;
        launchApp(BaseApplication.class, MainWebView.class, splashScreen, args);
    }
}
