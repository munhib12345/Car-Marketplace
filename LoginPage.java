package marketplace;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginPage {

    public static void show(Stage stage,
                            MarketplaceManager marketplaceManager) {

        // Header Title
        Label title = new Label("Online Car Marketplace");
        title.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: #1e3a5f;");

        Label subtitle = new Label("Login to continue");
        subtitle.setStyle("-fx-font-size: 14px; -fx-text-fill: #6b7280;");

        VBox headerBox = new VBox(4, title, subtitle);
        headerBox.setAlignment(Pos.CENTER);

        // Form Fields
        Label userIdLabel = new Label("User ID:");
        userIdLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #334155;");

        TextField userIdField = new TextField();
        userIdField.setPromptText("User ID");
        styleTextField(userIdField);

        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #334155;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        styleTextField(passwordField);

        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #e11d48;");

        // Action Buttons
        Button loginButton = new Button("Login");
        stylePrimaryButton(loginButton, "#1e3a5f", "#2b527f");
        loginButton.setPrefWidth(Double.MAX_VALUE);

        Button signupButton = new Button("Create Account");
        styleSecondaryButton(signupButton);

        HBox footerBox = new HBox(8, new Label("Don't have an account?"), signupButton);
        footerBox.setAlignment(Pos.CENTER);
        ((Label) footerBox.getChildren().get(0)).setStyle("-fx-text-fill: #6b7280; -fx-font-size: 13px;");

        // Form Card Container
        VBox formContent = new VBox(12,
                userIdLabel, userIdField,
                passwordLabel, passwordField,
                messageLabel,
                loginButton,
                footerBox
        );
        formContent.setPadding(new Insets(24));
        formContent.setMaxWidth(380);
        formContent.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 12px;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.06), 12, 0, 0, 4);"
        );

        // Logic - Login Action
        loginButton.setOnAction(event -> {

            String userId = userIdField.getText().trim();
            String password = passwordField.getText();

            User user = marketplaceManager.login(userId, password);

            if (user != null) {

                Dashboard.show(
                    stage,
                    user,
                    marketplaceManager
                );

            } else {

                messageLabel.setText(
                    "Invalid User ID or password."
                );
            }
        });

        // Logic - Navigate to Signup
        signupButton.setOnAction(event -> {

            SignupPage.show(
                stage,
                marketplaceManager
            );
        });

        VBox centerContainer = new VBox(20, headerBox, formContent);
        centerContainer.setAlignment(Pos.CENTER);
        centerContainer.setPadding(new Insets(30));

        BorderPane root = new BorderPane();
        root.setCenter(centerContainer);
        root.setStyle("-fx-background-color: #f4f7fb;");

        Scene scene = new Scene(root, 650, 520);

        stage.setTitle(
            "Online Car Dealership & Vehicle Marketplace"
        );

        stage.setScene(scene);
        stage.show();
    }

    private static void styleTextField(TextField field) {
        field.setPrefHeight(38);
        field.setStyle(
            "-fx-background-color: #f8fafc;" +
            "-fx-border-color: #cbd5e1;" +
            "-fx-border-radius: 6px;" +
            "-fx-background-radius: 6px;" +
            "-fx-padding: 8px 12px;" +
            "-fx-font-size: 13px;"
        );
    }

    private static void stylePrimaryButton(Button btn, String baseColor, String hoverColor) {
        btn.setPrefHeight(40);
        btn.setStyle(
            "-fx-background-color: " + baseColor + ";" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 6px;" +
            "-fx-cursor: hand;"
        );

        btn.setOnMouseEntered(e -> btn.setStyle(
            "-fx-background-color: " + hoverColor + ";" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 6px;" +
            "-fx-cursor: hand;"
        ));

        btn.setOnMouseExited(e -> btn.setStyle(
            "-fx-background-color: " + baseColor + ";" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 6px;" +
            "-fx-cursor: hand;"
        ));
    }

    private static void styleSecondaryButton(Button btn) {
        btn.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #1e3a5f;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 13px;" +
            "-fx-cursor: hand;" +
            "-fx-underline: true;"
        );
    }
}