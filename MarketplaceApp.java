package marketplace;

import javafx.application.Application;
import javafx.stage.Stage;

public class MarketplaceApp extends Application {

    private MarketplaceManager marketplaceManager;

    @Override
    public void start(Stage primaryStage) {

        marketplaceManager = new MarketplaceManager();

        LoginPage.show(
            primaryStage,
            marketplaceManager
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}