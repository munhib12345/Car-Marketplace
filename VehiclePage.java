package marketplace;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VehiclePage {

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

        Label subtitle = new Label("Browse Vehicles");
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
        // SEARCH CARD
        // =========================
        TextField makeField = createStyledTextField("Make");
        TextField modelField = createStyledTextField("Model");
        TextField minPriceField = createStyledTextField("Min Price");
        TextField maxPriceField = createStyledTextField("Max Price");
        TextField yearField = createStyledTextField("Year");

        Button searchButton = new Button("Search");
        stylePrimaryButton(searchButton, "#2f6f9f", "#245a82");

        Button clearButton = new Button("Clear");
        styleSecondaryButton(clearButton);

        HBox searchBox = new HBox(
                8,
                makeField,
                modelField,
                minPriceField,
                maxPriceField,
                yearField,
                searchButton,
                clearButton
        );
        searchBox.setAlignment(Pos.CENTER_LEFT);

        VBox searchCard = new VBox(searchBox);
        searchCard.setPadding(new Insets(16));
        searchCard.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 10px;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 8, 0, 0, 2);"
        );

        // =========================
        // VEHICLE TABLE & TOOLBAR CARD
        // =========================
        Button priceAscButton = new Button("Price ↑");
        Button priceDescButton = new Button("Price ↓");
        Button yearAscButton = new Button("Year ↑");
        Button yearDescButton = new Button("Year ↓");

        styleSortButton(priceAscButton);
        styleSortButton(priceDescButton);
        styleSortButton(yearAscButton);
        styleSortButton(yearDescButton);

        Label sortLabel = new Label("Sort By:");
        sortLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #475569;");

        HBox sortBox = new HBox(
                8,
                sortLabel,
                priceAscButton,
                priceDescButton,
                yearAscButton,
                yearDescButton
        );
        sortBox.setAlignment(Pos.CENTER_LEFT);

        Button detailsButton = new Button("View Details");
        stylePrimaryButton(detailsButton, "#1e3a5f", "#2b527f");
        detailsButton.setPrefWidth(140);

        BorderPane tableToolbar = new BorderPane();
        tableToolbar.setLeft(sortBox);
        tableToolbar.setRight(detailsButton);
        BorderPane.setAlignment(sortBox, Pos.CENTER_LEFT);
        BorderPane.setAlignment(detailsButton, Pos.CENTER_RIGHT);

        TableView<Vehicle> vehicleTable = new TableView<>();
        vehicleTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        vehicleTable.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #cbd5e1;" +
            "-fx-border-radius: 6px;"
        );

        TableColumn<Vehicle, String> listingIdColumn = new TableColumn<>("Listing ID");
        listingIdColumn.setCellValueFactory(new PropertyValueFactory<>("listingId"));

        TableColumn<Vehicle, String> makeColumn = new TableColumn<>("Make");
        makeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));

        TableColumn<Vehicle, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<Vehicle, Integer> yearColumn = new TableColumn<>("Year");
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<Vehicle, Double> priceColumn = new TableColumn<>("Price ($)");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Vehicle, Integer> mileageColumn = new TableColumn<>("Mileage");
        mileageColumn.setCellValueFactory(new PropertyValueFactory<>("mileage"));

        TableColumn<Vehicle, String> locationColumn = new TableColumn<>("Location");
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        vehicleTable.getColumns().addAll(
                listingIdColumn,
                makeColumn,
                modelColumn,
                yearColumn,
                priceColumn,
                mileageColumn,
                locationColumn
        );

        VBox tableCard = new VBox(12, tableToolbar, vehicleTable);
        tableCard.setPadding(new Insets(16));
        VBox.setVgrow(vehicleTable, Priority.ALWAYS);
        tableCard.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 10px;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 8, 0, 0, 2);"
        );

        // Load all available vehicles initial data
        loadVehicles(
                vehicleTable,
                marketplaceManager.getVehicleList().toArray()
        );

        // =========================
        // EVENT HANDLERS
        // =========================
        searchButton.setOnAction(event -> {
            String make = makeField.getText().trim();
            String model = modelField.getText().trim();

            double minPrice = 0;
            double maxPrice = Double.MAX_VALUE;
            int year = 0;

            try {
                if (!minPriceField.getText().trim().isEmpty()) {
                    minPrice = Double.parseDouble(minPriceField.getText().trim());
                }

                if (!maxPriceField.getText().trim().isEmpty()) {
                    maxPrice = Double.parseDouble(maxPriceField.getText().trim());
                }

                if (!yearField.getText().trim().isEmpty()) {
                    year = Integer.parseInt(yearField.getText().trim());
                }

                if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice) {
                    return;
                }

                Vehicle[] results = marketplaceManager.searchVehicles(
                        make,
                        model,
                        minPrice,
                        maxPrice,
                        year
                );

                loadVehicles(vehicleTable, results);

            } catch (NumberFormatException e) {
                vehicleTable.setItems(FXCollections.observableArrayList());
            }
        });

        clearButton.setOnAction(event -> {
            makeField.clear();
            modelField.clear();
            minPriceField.clear();
            maxPriceField.clear();
            yearField.clear();

            loadVehicles(
                    vehicleTable,
                    marketplaceManager.getVehicleList().toArray()
            );
        });

        // VIEW DETAILS CUSTOM MODAL DIALOG
        detailsButton.setOnAction(event -> {
            Vehicle selectedVehicle = vehicleTable.getSelectionModel().getSelectedItem();

            if (selectedVehicle == null) {
                return;
            }

            User seller = marketplaceManager.getVehicleSeller(selectedVehicle.getListingId());

            showCustomDetailsModal(stage, selectedVehicle, seller);
        });

        priceAscButton.setOnAction(event -> {
            Vehicle[] vehicles = vehicleTable.getItems().toArray(new Vehicle[0]);
            marketplaceManager.sortByPrice(vehicles, true);
            loadVehicles(vehicleTable, vehicles);
        });

        priceDescButton.setOnAction(event -> {
            Vehicle[] vehicles = vehicleTable.getItems().toArray(new Vehicle[0]);
            marketplaceManager.sortByPrice(vehicles, false);
            loadVehicles(vehicleTable, vehicles);
        });

        yearAscButton.setOnAction(event -> {
            Vehicle[] vehicles = vehicleTable.getItems().toArray(new Vehicle[0]);
            marketplaceManager.sortByYear(vehicles, true);
            loadVehicles(vehicleTable, vehicles);
        });

        yearDescButton.setOnAction(event -> {
            Vehicle[] vehicles = vehicleTable.getItems().toArray(new Vehicle[0]);
            marketplaceManager.sortByYear(vehicles, false);
            loadVehicles(vehicleTable, vehicles);
        });

        backButton.setOnAction(event -> Dashboard.show(stage, user, marketplaceManager));

        // =========================
        // MAIN LAYOUT & SCENE
        // =========================
        VBox layout = new VBox(16, header, searchCard, tableCard);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #f4f7fb;");
        VBox.setVgrow(tableCard, Priority.ALWAYS);

        Scene scene = new Scene(layout, 1200, 700);

        stage.setTitle("AutoMarket - Browse Vehicles");
        stage.setScene(scene);
        stage.show();
    }

    // =========================
    // CUSTOM DETAILS MODAL WINDOW
    // =========================
    private static void showCustomDetailsModal(Stage parentStage, Vehicle vehicle, User seller) {
        Stage modalStage = new Stage();
        modalStage.initOwner(parentStage);
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.setTitle("Vehicle Details - #" + vehicle.getListingId());

        // Header Title & Price Badge
        Label vehicleTitle = new Label(vehicle.getYear() + " " + vehicle.getMake() + " " + vehicle.getModel());
        vehicleTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #1e3a5f;");

        Label listingIdLabel = new Label("Listing ID: #" + vehicle.getListingId());
        listingIdLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #6b7280;");

        VBox titleBox = new VBox(2, vehicleTitle, listingIdLabel);

        Label priceBadge = new Label(String.format("$%,.2f", vehicle.getPrice()));
        priceBadge.setStyle(
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #0d9488;" +
            "-fx-background-color: #ccfbf1;" +
            "-fx-padding: 6px 14px;" +
            "-fx-background-radius: 20px;"
        );

        BorderPane heroPane = new BorderPane();
        heroPane.setLeft(titleBox);
        heroPane.setRight(priceBadge);
        BorderPane.setAlignment(priceBadge, Pos.CENTER_RIGHT);

        // Vehicle Specifications Grid Card
        Label specsHeader = new Label("Specifications");
        specsHeader.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #1e3a5f;");

        GridPane specsGrid = new GridPane();
        specsGrid.setHgap(20);
        specsGrid.setVgap(10);

        specsGrid.add(createSpecRow("VIN:", vehicle.getVin()), 0, 0);
        specsGrid.add(createSpecRow("Mileage:", String.format("%,d km", vehicle.getMileage())), 0, 1);
        specsGrid.add(createSpecRow("Fuel Type:", vehicle.getFuelType()), 0, 2);

        specsGrid.add(createSpecRow("Transmission:", vehicle.getTransmission()), 1, 0);
        specsGrid.add(createSpecRow("Location:", vehicle.getLocation()), 1, 1);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        specsGrid.getColumnConstraints().addAll(col1, col2);

        VBox specsCard = new VBox(8, specsHeader, specsGrid);
        specsCard.setPadding(new Insets(14));
        specsCard.setStyle(
            "-fx-background-color: #f8fafc;" +
            "-fx-border-color: #e2e8f0;" +
            "-fx-border-radius: 8px;" +
            "-fx-background-radius: 8px;"
        );

        // Seller Information Card
        Label sellerHeader = new Label("Seller Information");
        sellerHeader.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #1e3a5f;");

        VBox sellerCard = new VBox(8, sellerHeader);
        sellerCard.setPadding(new Insets(14));
        sellerCard.setStyle(
            "-fx-background-color: #f8fafc;" +
            "-fx-border-color: #e2e8f0;" +
            "-fx-border-radius: 8px;" +
            "-fx-background-radius: 8px;"
        );

        if (seller != null) {
            GridPane sellerGrid = new GridPane();
            sellerGrid.setHgap(20);
            sellerGrid.setVgap(10);

            sellerGrid.add(createSpecRow("Name:", seller.getName()), 0, 0);
            sellerGrid.add(createSpecRow("Type:", seller.getUserType()), 0, 1);
            sellerGrid.add(createSpecRow("Email:", seller.getEmail()), 1, 0);
            sellerGrid.add(createSpecRow("Phone:", seller.getPhone()), 1, 1);

            sellerGrid.getColumnConstraints().addAll(col1, col2);
            sellerCard.getChildren().add(sellerGrid);
        } else {
            Label noSellerLabel = new Label("Seller information not found.");
            noSellerLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #6b7280;");
            sellerCard.getChildren().add(noSellerLabel);
        }

        // Action / Close Button
        Button closeBtn = new Button("Close");
        styleSecondaryButton(closeBtn);
        closeBtn.setPrefWidth(100);
        closeBtn.setOnAction(e -> modalStage.close());

        HBox actionBox = new HBox(closeBtn);
        actionBox.setAlignment(Pos.CENTER_RIGHT);

        // Modal Content Layout
        VBox modalContent = new VBox(16, heroPane, specsCard, sellerCard, actionBox);
        modalContent.setPadding(new Insets(24));
        modalContent.setStyle("-fx-background-color: white;");

        Scene modalScene = new Scene(modalContent, 560, 430);
        modalStage.setScene(modalScene);
        modalStage.setResizable(false);
        modalStage.showAndWait();
    }

    private static HBox createSpecRow(String labelText, String valueText) {
        Label label = new Label(labelText);
        label.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #475569;");

        Label value = new Label(valueText != null && !valueText.isEmpty() ? valueText : "N/A");
        value.setStyle("-fx-font-size: 13px; -fx-text-fill: #1e293b;");

        HBox row = new HBox(6, label, value);
        row.setAlignment(Pos.CENTER_LEFT);
        return row;
    }

    private static void loadVehicles(
            TableView<Vehicle> vehicleTable,
            Vehicle[] vehicles) {

        vehicleTable.setItems(FXCollections.observableArrayList());

        for (Vehicle vehicle : vehicles) {
            if (vehicle.isAvailable()) {
                vehicleTable.getItems().add(vehicle);
            }
        }
    }

    // =========================
    // UI HELPER METHODS
    // =========================
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

    private static void styleSecondaryButton(Button btn) {
        btn.setPrefHeight(36);
        btn.setStyle(
            "-fx-background-color: #f1f5f9;" +
            "-fx-text-fill: #475569;" +
            "-fx-border-color: #cbd5e1;" +
            "-fx-border-radius: 6px;" +
            "-fx-background-radius: 6px;" +
            "-fx-font-size: 13px;" +
            "-fx-cursor: hand;"
        );

        btn.setOnMouseEntered(e -> btn.setStyle(
            "-fx-background-color: #e2e8f0;" +
            "-fx-text-fill: #1e293b;" +
            "-fx-border-color: #94a3b8;" +
            "-fx-border-radius: 6px;" +
            "-fx-background-radius: 6px;" +
            "-fx-font-size: 13px;" +
            "-fx-cursor: hand;"
        ));

        btn.setOnMouseExited(e -> btn.setStyle(
            "-fx-background-color: #f1f5f9;" +
            "-fx-text-fill: #475569;" +
            "-fx-border-color: #cbd5e1;" +
            "-fx-border-radius: 6px;" +
            "-fx-background-radius: 6px;" +
            "-fx-font-size: 13px;" +
            "-fx-cursor: hand;"
        ));
    }

    private static void styleSortButton(Button btn) {
        btn.setPrefHeight(30);
        btn.setStyle(
            "-fx-background-color: #e2e8f0;" +
            "-fx-text-fill: #334155;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 4px;" +
            "-fx-cursor: hand;"
        );

        btn.setOnMouseEntered(e -> btn.setStyle(
            "-fx-background-color: #cbd5e1;" +
            "-fx-text-fill: #0f172a;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 4px;" +
            "-fx-cursor: hand;"
        ));

        btn.setOnMouseExited(e -> btn.setStyle(
            "-fx-background-color: #e2e8f0;" +
            "-fx-text-fill: #334155;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 4px;" +
            "-fx-cursor: hand;"
        ));
    }
}