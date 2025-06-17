import java.util.Optional;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class App extends Application {
    private ShoppingList shoppingList = new ShoppingList();
    private ListView<String> listView = new ListView<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TextField nameField = new TextField();
        TextField qtyField = new TextField();
        Button addButton = new Button("Tambah Barang");
        Button deleteButton = new Button("Hapus Barang");
        Button markButton = new Button("Tandai Dibeli");
        Button editButton = new Button("Edit Barang");
        Button searchButton = new Button("Cari Barang");

        addButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                int qty = Integer.parseInt(qtyField.getText());
                shoppingList.addItem(new Item(name, qty));
                refreshList();
                nameField.clear();
                qtyField.clear();
            } catch (NumberFormatException ex) {
                showAlert("Input tidak valid untuk jumlah.");
            }
        });

        deleteButton.setOnAction(e -> {
            int selectedIdx = listView.getSelectionModel().getSelectedIndex();
            if (selectedIdx >= 0) {
                shoppingList.removeItem(selectedIdx);
                refreshList();
            }
        });

        markButton.setOnAction(e -> {
            int selectedIdx = listView.getSelectionModel().getSelectedIndex();
            if (selectedIdx >= 0) {
                shoppingList.markItemAsBought(selectedIdx);
                refreshList();
            }
        });

        editButton.setOnAction(e -> {
            int selectedIdx = listView.getSelectionModel().getSelectedIndex();
            if (selectedIdx >= 0) {
                TextInputDialog nameDialog = new TextInputDialog();
                nameDialog.setHeaderText("Nama baru barang:");
                Optional<String> newName = nameDialog.showAndWait();

                TextInputDialog qtyDialog = new TextInputDialog();
                qtyDialog.setHeaderText("Jumlah baru barang:");
                Optional<String> newQtyStr = qtyDialog.showAndWait();

                if (newName.isPresent() && newQtyStr.isPresent()) {
                    try {
                        int newQty = Integer.parseInt(newQtyStr.get());
                        shoppingList.editItem(selectedIdx, newName.get(), newQty);
                        refreshList();
                    } catch (NumberFormatException ex) {
                        showAlert("Jumlah harus berupa angka.");
                    }
                }
            }
        });

        searchButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Cari berdasarkan kata kunci:");
            Optional<String> keyword = dialog.showAndWait();
            if (keyword.isPresent()) {
                listView.getItems().clear();
                for (Item item : shoppingList.searchItems(keyword.get())) {
                    listView.getItems().add(item.toString());
                }
            }
        });

        HBox inputBox = new HBox(10, new Label("Nama:"), nameField, new Label("Jumlah:"), qtyField, addButton);
        VBox buttonBox = new VBox(10, markButton, editButton, deleteButton, searchButton);
        HBox mainBox = new HBox(10, listView, buttonBox);

        // Tambahkan label copyright di bawah tombol, rata kanan
        Label copyrightLabel = new Label(" © Informatika 2025");
        HBox copyrightBox = new HBox();
        copyrightBox.setAlignment(Pos.CENTER_RIGHT);
        copyrightBox.getChildren().add(copyrightLabel);

        VBox root = new VBox(10, inputBox, mainBox, copyrightBox);
        root.setPadding(new javafx.geometry.Insets(10));

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Aplikasi Daftar Belanja");
        primaryStage.setScene(scene);
        primaryStage.show();

        refreshList();
    }

    private void refreshList() {
        listView.getItems().clear();
        for (Item item : shoppingList.getItems()) {
            listView.getItems().add(item.toString());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}