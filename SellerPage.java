package marketplace;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SellerPage {

    public static void show(Stage stage,
                            User user,
                            MarketplaceManager marketplaceManager) {

        // =========================
        // HEADER BAR
        // =========================
        Label title = new Label("AutoMarket");
        title.setStyle(
            "-fx-font-size: 26px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #1e3a5f;"
        );

        Label subtitle = new Label("Seller Listing Management");
        subtitle.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-text-fill: #6b7280;"
        );

        VBox titleBox = new VBox(2, title, subtitle);

        Button backButton = new Button("Back to Dashboard");
        backButton.setPrefHeight(36);
        backButton.setStyle(
            "-fx-background-color: #e2e8f0;" +
            "-fx-text-fill: #1e3a5f;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 6px;" +
            "-fx-cursor: hand;"
        );

        backButton.setOnMouseEntered(e -> backButton.setStyle(
            "-fx-background-color: #cbd5e1;" +
            "-fx-text-fill: #1e3a5f;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 6px;" +
            "-fx-cursor: hand;"
        ));

        backButton.setOnMouseExited(e -> backButton.setStyle(
            "-fx-background-color: #e2e8f0;" +
            "-fx-text-fill: #1e3a5f;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 6px;" +
            "-fx-cursor: hand;"
        ));

        BorderPane header = new BorderPane();
        header.setLeft(titleBox);
        header.setRight(backButton);
        BorderPane.setAlignment(backButton, Pos.CENTER_RIGHT);

        // =========================
        // FORM FIELDS & CARD
        // =========================
        Label formTitle = new Label("Listing Details");
        formTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #1e3a5f;");

        TextField listingIdField = createStyledTextField("Listing ID");
        TextField vinField = createStyledTextField("VIN");
        TextField makeField = createStyledTextField("Make");
        TextField modelField = createStyledTextField("Model");
        TextField yearField = createStyledTextField("Year");
        TextField priceField = createStyledTextField("Price");
        TextField mileageField = createStyledTextField("Mileage");
        TextField fuelField = createStyledTextField("Fuel Type");
        TextField transmissionField = createStyledTextField("Transmission");
        TextField locationField = createStyledTextField("Location");

        GridPane form = new GridPane();
        form.setHgap(12);
        form.setVgap(12);
        form.setAlignment(Pos.CENTER);

        form.add(createFormLabel("Listing ID:"), 0, 0);
        form.add(listingIdField, 1, 0);

        form.add(createFormLabel("VIN:"), 2, 0);
        form.add(vinField, 3, 0);

        form.add(createFormLabel("Make:"), 0, 1);
        form.add(makeField, 1, 1);

        form.add(createFormLabel("Model:"), 2, 1);
        form.add(modelField, 3, 1);

        form.add(createFormLabel("Year:"), 0, 2);
        form.add(yearField, 1, 2);

        form.add(createFormLabel("Price:"), 2, 2);
        form.add(priceField, 3, 2);

        form.add(createFormLabel("Mileage:"), 0, 3);
        form.add(mileageField, 1, 3);

        form.add(createFormLabel("Fuel Type:"), 2, 3);
        form.add(fuelField, 3, 3);

        form.add(createFormLabel("Transmission:"), 0, 4);
        form.add(transmissionField, 1, 4);

        form.add(createFormLabel("Location:"), 2, 4);
        form.add(locationField, 3, 4);

        Button addButton = new Button("Add Listing");
        stylePrimaryButton(addButton, "#2f6f9f", "#245a82");

        Button updateButton = new Button("Update Listing");
        stylePrimaryButton(updateButton, "#1e3a5f", "#2b527f");

        Button deleteButton = new Button("Delete Listing");
        styleDangerButton(deleteButton);

        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #1e3a5f;");

        HBox actionBox = new HBox(10, addButton, updateButton, deleteButton);
        actionBox.setAlignment(Pos.CENTER_RIGHT);

        VBox formCard = new VBox(14, formTitle, form, actionBox, messageLabel);
        formCard.setPadding(new Insets(16));
        formCard.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 10px;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 8, 0, 0, 2);"
        );

        // =========================
        // TABLE & LISTINGS CARD
        // =========================
        TableView<Vehicle> listingsTable = new TableView<>();
        listingsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        listingsTable.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #cbd5e1;" +
            "-fx-border-radius: 6px;"
        );

        TableColumn<Vehicle, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("listingId"));

        TableColumn<Vehicle, String> makeColumn = new TableColumn<>("Make");
        makeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));

        TableColumn<Vehicle, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<Vehicle, Integer> yearColumn = new TableColumn<>("Year");
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<Vehicle, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        priceColumn.setCellFactory(column -> new TableCell<Vehicle, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);

                if (empty || price == null) {
                    setText(null);
                } else {
                    setText(String.format("$,.0f", price));
                }
            }
        });

        TableColumn<Vehicle, Integer> mileageColumn = new TableColumn<>("Mileage");
        mileageColumn.setCellValueFactory(new PropertyValueFactory<>("mileage"));

        TableColumn<Vehicle, String> locationColumn = new TableColumn<>("Location");
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        listingsTable.getColumns().addAll(
                idColumn,
                makeColumn,
                modelColumn,
                yearColumn,
                priceColumn,
                mileageColumn,
                locationColumn
        );

        listingsTable.setPrefHeight(250);

        if (user instanceof Dealership) {
            Vehicle[] myVehicles = marketplaceManager.getVehiclesBySeller(user.getUserId());
            listingsTable.getItems().addAll(myVehicles);
        }

        Label listingsTitle = new Label("My Listings");
        listingsTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #1e3a5f;");

        VBox tableCard = new VBox(12, listingsTitle, listingsTable);
        tableCard.setPadding(new Insets(16));
        VBox.setVgrow(listingsTable, Priority.ALWAYS);
        tableCard.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 10px;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 8, 0, 0, 2);"
        );

        // =========================
        // EVENT HANDLERS
        // =========================

        // Add Listing
        addButton.setOnAction(event -> {
            try {
                String listingId = listingIdField.getText().trim();
                String vin = vinField.getText().trim();
                String make = makeField.getText().trim();
                String model = modelField.getText().trim();
                int year = Integer.parseInt(yearField.getText().trim());
                double price = Double.parseDouble(priceField.getText().trim());
                int mileage = Integer.parseInt(mileageField.getText().trim());
                String fuel = fuelField.getText().trim();
                String transmission = transmissionField.getText().trim();
                String location = locationField.getText().trim();

                Vehicle vehicle = new Vehicle(
                        listingId,
                        vin,
                        make,
                        model,
                        year,
                        price,
                        mileage,
                        fuel,
                        transmission,
                        location,
                        user.getUserId()
                );

                boolean added = marketplaceManager.addVehicle(vehicle);

                if (added) {
                    messageLabel.setText("Vehicle listing added successfully.");

                    if (user instanceof Dealership) {
                        listingsTable.getItems().clear();
                        Vehicle[] myVehicles = marketplaceManager.getVehiclesBySeller(user.getUserId());
                        listingsTable.getItems().addAll(myVehicles);
                    }

                    clearFields(
                            listingIdField,
                            vinField,
                            makeField,
                            modelField,
                            yearField,
                            priceField,
                            mileageField,
                            fuelField,
                            transmissionField,
                            locationField
                    );

                } else {
                    messageLabel.setText("Could not add listing. Check the details or Listing ID.");
                }

            } catch (NumberFormatException e) {
                messageLabel.setText("Year, price and mileage must be valid numbers.");
            }
        });

        // Update Listing
        updateButton.setOnAction(event -> {
            try {
                String listingId = listingIdField.getText().trim();

                Vehicle vehicle = marketplaceManager.findVehicle(listingId);

                if (vehicle == null) {
                    messageLabel.setText("Listing not found.");
                    return;
                }

                if (!vehicle.getSellerId().equals(user.getUserId())) {
                    messageLabel.setText("You can only update your own listings.");
                    return;
                }

                vehicle.setMake(makeField.getText().trim());
                vehicle.setModel(modelField.getText().trim());
                vehicle.setYear(Integer.parseInt(yearField.getText().trim()));
                vehicle.setPrice(Double.parseDouble(priceField.getText().trim()));
                vehicle.setMileage(Integer.parseInt(mileageField.getText().trim()));
                vehicle.setFuelType(fuelField.getText().trim());
                vehicle.setTransmission(transmissionField.getText().trim());
                vehicle.setLocation(locationField.getText().trim());

                FileManager.saveAllVehicles(marketplaceManager.getVehicleList());

                if (user instanceof Dealership) {
                    listingsTable.getItems().clear();
                    Vehicle[] myVehicles = marketplaceManager.getVehiclesBySeller(user.getUserId());
                    listingsTable.getItems().addAll(myVehicles);
                }

                messageLabel.setText("Listing updated successfully.");

            } catch (NumberFormatException e) {
                messageLabel.setText("Year, price and mileage must be valid numbers.");
            }
        });

        // Delete Listing
        deleteButton.setOnAction(event -> {
            String listingId = listingIdField.getText().trim();

            Vehicle vehicle = marketplaceManager.findVehicle(listingId);

            if (vehicle == null) {
                messageLabel.setText("Listing not found.");
                return;
            }

            if (!vehicle.getSellerId().equals(user.getUserId())) {
                messageLabel.setText("You can only delete your own listings.");
                return;
            }

            boolean removed = marketplaceManager.removeVehicle(listingId);

            if (removed) {
                messageLabel.setText("Listing deleted successfully.");

                if (user instanceof Dealership) {
                    listingsTable.getItems().clear();
                    Vehicle[] myVehicles = marketplaceManager.getVehiclesBySeller(user.getUserId());
                    listingsTable.getItems().addAll(myVehicles);
                }

                clearFields(
                        listingIdField,
                        vinField,
                        makeField,
                        modelField,
                        yearField,
                        priceField,
                        mileageField,
                        fuelField,
                        transmissionField,
                        locationField
                );

            } else {
                messageLabel.setText("Could not delete listing.");
            }
        });

        backButton.setOnAction(event -> Dashboard.show(stage, user, marketplaceManager));

        // Auto-fill form when clicking a row in the table
        listingsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, selectedVehicle) -> {
            if (selectedVehicle != null) {
                listingIdField.setText(selectedVehicle.getListingId());
                vinField.setText(selectedVehicle.getVin());
                makeField.setText(selectedVehicle.getMake());
                modelField.setText(selectedVehicle.getModel());
                yearField.setText(String.valueOf(selectedVehicle.getYear()));
                priceField.setText(String.valueOf(selectedVehicle.getPrice()));
                mileageField.setText(String.valueOf(selectedVehicle.getMileage()));
                fuelField.setText(selectedVehicle.getFuelType());
                transmissionField.setText(selectedVehicle.getTransmission());
                locationField.setText(selectedVehicle.getLocation());
            }
        });

        // =========================
        // MAIN LAYOUT & SCENE
        // =========================
        VBox layout = new VBox(16, header, formCard);

        if (user instanceof Dealership) {
            layout.getChildren().add(tableCard);
            VBox.setVgrow(tableCard, Priority.ALWAYS);
        }

        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #f4f7fb;");

        Scene scene = new Scene(layout, 1100, 800);

        stage.setTitle("Seller Listing Management");
        stage.setScene(scene);
        stage.show();
    }

    private static void clearFields(
            TextField listingIdField,
            TextField vinField,
            TextField makeField,
            TextField modelField,
            TextField yearField,
            TextField priceField,
            TextField mileageField,
            TextField fuelField,
            TextField transmissionField,
            TextField locationField) {

        listingIdField.clear();
        vinField.clear();
        makeField.clear();
        modelField.clear();
        yearField.clear();
        priceField.clear();
        mileageField.clear();
        fuelField.clear();
        transmissionField.clear();
        locationField.clear();
    }

    // =========================
    // UI HELPER METHODS
    // =========================
    private static Label createFormLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #334155;");
        return label;
    }

    private static TextField createStyledTextField(String prompt) {
        TextField tf = new TextField();
        tf.setPromptText(prompt);
        tf.setPrefHeight(36);
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

    private static void stylePrimaryButton(Button btn, String baseColor, String hoverColor) {
        btn.setPrefHeight(36);
        btn.setStyle(
            "-fx-background-color: " + baseColor + ";" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 6px;" +
            "-fx-cursor: hand;"
        );

        btn.setOnMouseEntered(e -> btn.setStyle(
            "-fx-background-color: " + hoverColor + ";" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 6px;" +
            "-fx-cursor: hand;"
        ));

        btn.setOnMouseExited(e -> btn.setStyle(
            "-fx-background-color: " + baseColor + ";" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 6px;" +
            "-fx-cursor: hand;"
        ));
    }

    private static void styleDangerButton(Button btn) {
        btn.setPrefHeight(36);
        btn.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #c0392b;" +
            "-fx-border-color: #c0392b;" +
            "-fx-border-radius: 6px;" +
            "-fx-background-radius: 6px;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;"
        );

        btn.setOnMouseEntered(e -> btn.setStyle(
            "-fx-background-color: #c0392b;" +
            "-fx-text-fill: white;" +
            "-fx-border-color: #c0392b;" +
            "-fx-border-radius: 6px;" +
            "-fx-background-radius: 6px;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;"
        ));

        btn.setOnMouseExited(e -> btn.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #c0392b;" +
            "-fx-border-color: #c0392b;" +
            "-fx-border-radius: 6px;" +
            "-fx-background-radius: 6px;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;"
        ));
    }
}