package marketplace;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SignupPage {

    public static void show(Stage stage,
                            MarketplaceManager marketplaceManager) {

        // Header Title
        Label title = new Label("Create Account");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #1e3a5f;");

        Label subtitle = new Label("Join AutoMarket as a Customer or Dealership");
        subtitle.setStyle("-fx-font-size: 14px; -fx-text-fill: #6b7280;");

        VBox headerBox = new VBox(4, title, subtitle);
        headerBox.setAlignment(Pos.CENTER);

        // Form Fields
        TextField userIdField = createStyledTextField("User ID");
        TextField nameField = createStyledTextField("Name");
        TextField emailField = createStyledTextField("Email");
        PasswordField passwordField = createStyledPasswordField("Password");
        TextField phoneField = createStyledTextField("Phone");

        ComboBox<String> accountType = new ComboBox<>();
        accountType.getItems().addAll(
            "Individual Customer",
            "Dealership"
        );
        accountType.setPromptText("Account Type");
        accountType.setPrefHeight(38);
        accountType.setMaxWidth(Double.MAX_VALUE);
        accountType.setStyle(
            "-fx-background-color: #f8fafc;" +
            "-fx-border-color: #cbd5e1;" +
            "-fx-border-radius: 6px;" +
            "-fx-background-radius: 6px;" +
            "-fx-font-size: 13px;"
        );

        TextField dealershipNameField = createStyledTextField("Dealership Name");
        dealershipNameField.setVisible(false);
        dealershipNameField.setManaged(false);

        TextField addressField = createStyledTextField("Business Address");
        addressField.setVisible(false);
        addressField.setManaged(false);

        Label dealershipNameLabel = createFormLabel("Dealership Name:");
        dealershipNameLabel.setVisible(false);
        dealershipNameLabel.setManaged(false);

        Label addressLabel = createFormLabel("Address:");
        addressLabel.setVisible(false);
        addressLabel.setManaged(false);

        accountType.setOnAction(event -> {

            boolean dealership =
                "Dealership".equals(accountType.getValue());

            dealershipNameField.setVisible(dealership);
            dealershipNameField.setManaged(dealership);
            dealershipNameLabel.setVisible(dealership);
            dealershipNameLabel.setManaged(dealership);

            addressField.setVisible(dealership);
            addressField.setManaged(dealership);
            addressLabel.setVisible(dealership);
            addressLabel.setManaged(dealership);
        });

        GridPane formGrid = new GridPane();
        formGrid.setHgap(12);
        formGrid.setVgap(10);
        formGrid.setAlignment(Pos.CENTER);

        formGrid.add(createFormLabel("Account Type:"), 0, 0);
        formGrid.add(accountType, 1, 0);

        formGrid.add(createFormLabel("User ID:"), 0, 1);
        formGrid.add(userIdField, 1, 1);

        formGrid.add(createFormLabel("Name:"), 0, 2);
        formGrid.add(nameField, 1, 2);

        formGrid.add(createFormLabel("Email:"), 0, 3);
        formGrid.add(emailField, 1, 3);

        formGrid.add(createFormLabel("Password:"), 0, 4);
        formGrid.add(passwordField, 1, 4);

        formGrid.add(createFormLabel("Phone:"), 0, 5);
        formGrid.add(phoneField, 1, 5);

        formGrid.add(dealershipNameLabel, 0, 6);
        formGrid.add(dealershipNameField, 1, 6);

        formGrid.add(addressLabel, 0, 7);
        formGrid.add(addressField, 1, 7);

        Button signupButton = new Button("Create Account");
        stylePrimaryButton(signupButton, "#2f6f9f", "#245a82");
        signupButton.setPrefWidth(Double.MAX_VALUE);

        Button backButton = new Button("Back to Login");
        styleSecondaryButton(backButton);

        HBox footerBox = new HBox(8, new Label("Already have an account?"), backButton);
        footerBox.setAlignment(Pos.CENTER);
        ((Label) footerBox.getChildren().get(0)).setStyle("-fx-text-fill: #6b7280; -fx-font-size: 13px;");

        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #e11d48;");

        VBox formCard = new VBox(14, formGrid, messageLabel, signupButton, footerBox);
        formCard.setPadding(new Insets(24));
        formCard.setMaxWidth(460);
        formCard.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 12px;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.06), 12, 0, 0, 4);"
        );

        // Signup Logic
        signupButton.setOnAction(event -> {

            String userId = userIdField.getText().trim();
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String password = passwordField.getText();
            String phone = phoneField.getText().trim();
            String type = accountType.getValue();

            if (userId.isEmpty()
                    || name.isEmpty()
                    || email.isEmpty()
                    || password.isEmpty()
                    || phone.isEmpty()
                    || type == null) {

                messageLabel.setText(
                    "Please fill in all required fields."
                );

                return;
            }

            User user;

            if (type.equals("Dealership")) {

                String dealershipName =
                    dealershipNameField.getText().trim();

                String address =
                    addressField.getText().trim();

                if (dealershipName.isEmpty()
                        || address.isEmpty()) {

                    messageLabel.setText(
                        "Please enter dealership details."
                    );

                    return;
                }

                user = new Dealership(
                    userId,
                    name,
                    email,
                    password,
                    phone,
                    dealershipName,
                    address
                );

            } else {

                user = new Customer(
                    userId,
                    name,
                    email,
                    password,
                    phone
                );
            }

            boolean added =
                marketplaceManager.addUser(user);

            if (added) {

                FileManager.saveUser(user);

                messageLabel.setText(
                    "Account created successfully!"
                );

                userIdField.clear();
                nameField.clear();
                emailField.clear();
                passwordField.clear();
                phoneField.clear();
                accountType.setValue(null);
                dealershipNameField.clear();
                addressField.clear();

                dealershipNameField.setVisible(false);
                dealershipNameField.setManaged(false);
                dealershipNameLabel.setVisible(false);
                dealershipNameLabel.setManaged(false);

                addressField.setVisible(false);
                addressField.setManaged(false);
                addressLabel.setVisible(false);
                addressLabel.setManaged(false);

            } else {

                messageLabel.setText(
                    "User ID already exists."
                );
            }
        });

        backButton.setOnAction(event -> {
            LoginPage.show(stage, marketplaceManager);
        });

        VBox centerContainer = new VBox(20, headerBox, formCard);
        centerContainer.setAlignment(Pos.CENTER);
        centerContainer.setPadding(new Insets(30));

        BorderPane root = new BorderPane();
        root.setCenter(centerContainer);
        root.setStyle("-fx-background-color: #f4f7fb;");

        Scene scene = new Scene(root, 700, 750);

        stage.setTitle("Create Account");
        stage.setScene(scene);
        stage.show();
    }

    // UI Helper Methods
    private static Label createFormLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #334155;");
        return label;
    }

    private static TextField createStyledTextField(String prompt) {
        TextField tf = new TextField();
        tf.setPromptText(prompt);
        tf.setPrefHeight(38);
        tf.setPrefWidth(260);
        tf.setStyle(
            "-fx-background-color: #f8fafc;" +
            "-fx-border-color: #cbd5e1;" +
            "-fx-border-radius: 6px;" +
            "-fx-background-radius: 6px;" +
            "-fx-padding: 6px 10px;" +
            "-fx-font-size: 13px;"
        );
        return tf;
    }

    private static PasswordField createStyledPasswordField(String prompt) {
        PasswordField pf = new PasswordField();
        pf.setPromptText(prompt);
        pf.setPrefHeight(38);
        pf.setPrefWidth(260);
        pf.setStyle(
            "-fx-background-color: #f8fafc;" +
            "-fx-border-color: #cbd5e1;" +
            "-fx-border-radius: 6px;" +
            "-fx-background-radius: 6px;" +
            "-fx-padding: 6px 10px;" +
            "-fx-font-size: 13px;"
        );
        return pf;
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