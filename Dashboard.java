package marketplace;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Dashboard {

    public static void show(Stage stage,
                            User user,
                            MarketplaceManager marketplaceManager) {

        // =========================
        // TITLE
        // =========================

        Label title = new Label("AutoMarket");

        title.setStyle(
            "-fx-font-size: 36px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #1e3a5f;"
        );

        Label subtitle = new Label("Vehicle Marketplace");

        subtitle.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-text-fill: #6b7280;"
        );


        // =========================
        // WELCOME SECTION
        // =========================

        Label welcome = new Label(
            "Welcome, " + user.getName()
        );

        welcome.setStyle(
            "-fx-font-size: 20px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #263238;"
        );

        Label accountType = new Label(
            "Account Type: " + user.getUserType()
        );

        accountType.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-text-fill: #607d8b;" +
            "-fx-background-color: #e8f0f7;" +
            "-fx-padding: 8px 15px;" +
            "-fx-background-radius: 20px;"
        );


        // =========================
        // MANAGE LISTINGS BUTTON
        // =========================

        Button manageListingsButton =
            new Button("Manage Listings");

        manageListingsButton.setPrefWidth(260);
        manageListingsButton.setPrefHeight(45);

        manageListingsButton.setStyle(
            "-fx-background-color: #2f6f9f;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8px;" +
            "-fx-cursor: hand;"
        );

        // Hover effect
        manageListingsButton.setOnMouseEntered(event -> {

            manageListingsButton.setStyle(
                "-fx-background-color: #245a82;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
            );
        });

        manageListingsButton.setOnMouseExited(event -> {

            manageListingsButton.setStyle(
                "-fx-background-color: #2f6f9f;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
            );
        });


        // =========================
        // BROWSE VEHICLES BUTTON
        // =========================

        Button browseButton =
            new Button("Browse Vehicles");

        browseButton.setPrefWidth(260);
        browseButton.setPrefHeight(45);

        browseButton.setStyle(
            "-fx-background-color: #1e3a5f;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8px;" +
            "-fx-cursor: hand;"
        );

        // Hover effect
        browseButton.setOnMouseEntered(event -> {

            browseButton.setStyle(
                "-fx-background-color: #2b527f;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
            );
        });

        browseButton.setOnMouseExited(event -> {

            browseButton.setStyle(
                "-fx-background-color: #1e3a5f;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
            );
        });


        // =========================
        // LOGOUT BUTTON
        // =========================

        Button logoutButton =
            new Button("Logout");

        logoutButton.setPrefWidth(260);
        logoutButton.setPrefHeight(40);

        logoutButton.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #c0392b;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-border-color: #c0392b;" +
            "-fx-border-width: 1px;" +
            "-fx-border-radius: 8px;" +
            "-fx-background-radius: 8px;" +
            "-fx-cursor: hand;"
        );

        // Hover effect
        logoutButton.setOnMouseEntered(event -> {

            logoutButton.setStyle(
                "-fx-background-color: #c0392b;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #c0392b;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
            );
        });

        logoutButton.setOnMouseExited(event -> {

            logoutButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #c0392b;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #c0392b;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
            );
        });


        // =========================
        // WELCOME BOX
        // =========================

        VBox welcomeBox = new VBox(8);

        welcomeBox.setAlignment(Pos.CENTER);

        welcomeBox.getChildren().addAll(
            welcome,
            accountType
        );


        // =========================
        // MAIN LAYOUT
        // =========================

        VBox layout = new VBox(15);

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));

        layout.setStyle(
            "-fx-background-color: #f4f7fb;"
        );

        layout.getChildren().addAll(
            title,
            subtitle,
            welcomeBox
        );


        // Show Manage Listings for sellers
        if (user instanceof Customer ||
            user instanceof Dealership) {

            layout.getChildren().add(
                manageListingsButton
            );
        }

        layout.getChildren().addAll(
            browseButton,
            logoutButton
        );


        // =========================
        // SCENE
        // =========================

        Scene scene = new Scene(
            layout,
            700,
            500
        );

        stage.setTitle("AutoMarket - Dashboard");
        stage.setScene(scene);
        stage.show();


        // =========================
        // NAVIGATION
        // =========================

        browseButton.setOnAction(event -> {

            VehiclePage.show(
                stage,
                user,
                marketplaceManager
            );
        });


        manageListingsButton.setOnAction(event -> {

            SellerPage.show(
                stage,
                user,
                marketplaceManager
            );
        });


        logoutButton.setOnAction(event -> {

            LoginPage.show(
                stage,
                marketplaceManager
            );
        });
    }
}