
/**
 * Class used to run the GUI
 * 
 * @version 18.11.2020
 */

import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CoinSorterGUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        // Initialize the Coin Sorter
        String currency = "£";
        int min = 0;
        int max = 10000;
        List<Integer> coinList = Arrays.asList(10, 20, 50, 100, 200);
        CoinSorter c = new CoinSorter(currency, min, max, coinList);

        // Input Components
        TextField amountField = new TextField();
        amountField.setMaxWidth(250);
        Label amountFieldLabel = new Label("Amount");

        TextField coinTypeField = new TextField();
        coinTypeField.setMaxWidth(250);
        Label coinTypeFieldLabel = new Label("Coin Type");

        HBox input = new HBox(10);
        input.setAlignment(Pos.CENTER);
        input.getChildren().addAll(amountFieldLabel, amountField, coinTypeFieldLabel, coinTypeField);

        // Display
        TextArea display = new TextArea();
        display.setEditable(false);
        display.setMinSize(420, 50);
        display.setMaxSize(420, 50);
        display.setText("***Coin Sorter - Main Menu***");

        // Option 1
        Button option1Button = new Button();
        option1Button.setText("1 - Coin calculator");
        option1Button.setOnAction(e -> {
            int amount = Integer.parseInt(amountField.getText());
            if (amount < c.getMinCoinIn() || amount > c.getMaxCoinIn()) {
                display.setText("Invalid amount");
                return;
            }
            int coinType = Integer.parseInt(coinTypeField.getText());
            if (coinType < 0) {
                display.setText("Invalid coin type");
                return;
            }
            display.setText(c.coinCalculator(amount, coinType));
        });

        // Option 2
        Button option2Button = new Button();
        option2Button.setText("2 - Multiple coin calculator");
        option2Button.setOnAction(e -> {
            int amount = Integer.parseInt(amountField.getText());
            if (amount < c.getMinCoinIn() || amount > c.getMaxCoinIn()) {
                display.setText("Invalid amount");
                return;
            }
            int coinType = Integer.parseInt(coinTypeField.getText());
            if (coinType < 0) {
                display.setText("Invalid coin type");
                return;
            }
            display.setText(c.multiCoinCalculator(amount, coinType));
        });

        // Option 3
        Button option3Button = new Button();
        option3Button.setText("3 - Print coin list");
        option3Button.setOnAction(e -> {
            display.setText(c.printCoinList());
        });

        // Option 4
        Button option4Button = new Button();
        option4Button.setText("4 - Set details");
        option4Button.setOnAction(e -> {
            // Display
            TextArea display2 = new TextArea();
            display2.setEditable(false);
            display2.setMinSize(300, 50);
            display2.setMaxSize(300, 50);

            // Set currency
            TextField currencyField = new TextField();
            currencyField.setMaxWidth(100);
            Label currencyFieldLabel = new Label("Currency");
            Button currencyButton = new Button();
            currencyButton.setText("Set Currency");
            currencyButton.setOnAction(f -> {
                String currencyString = currencyField.getText();
                if (currencyString.length() <= 10) {
                    c.setCurrency(currencyString);
                    display2.setText("Currency is set to " + c.getCurrency());
                } else {
                    display2.setText("Currency String is too long");
                }  
            });
            HBox currencyBox = new HBox(10);
            currencyBox.getChildren().setAll(currencyFieldLabel, currencyField, currencyButton);
            currencyBox.setAlignment(Pos.CENTER);

            // Set Minimum
            TextField minField = new TextField();
            minField.setMaxWidth(100);
            Label minFieldLabel = new Label("Minimum");
            Button minButton = new Button();
            minButton.setText("Set Minimum");
            minButton.setOnAction(f -> {
                int minValue = Integer.parseInt(minField.getText());
                if (minValue > 0 && minValue < c.getMaxCoinIn()) {
                    c.setMinCoinIn(minValue);
                    display2.setText("Minimum is set to " + c.getMinCoinIn());
                } else {
                    display2.setText("Minimum is invalid");
                }  
            });
            HBox minBox = new HBox(10);
            minBox.getChildren().setAll(minFieldLabel, minField, minButton);
            minBox.setAlignment(Pos.CENTER);

            // Set Maximum
            TextField maxField = new TextField();
            maxField.setMaxWidth(100);
            Label maxFieldLabel = new Label("Maximum");
            Button maxButton = new Button();
            maxButton.setText("Set maximum");
            maxButton.setOnAction(f -> {
                int maxValue = Integer.parseInt(maxField.getText());
                if (maxValue > c.getMinCoinIn() && maxValue <= 10000) {
                    c.setMaxCoinIn(maxValue);
                    display2.setText("Maximum is set to " + c.getMaxCoinIn());
                } else {
                    display2.setText("Maximum is invalid");
                }  
            });
            HBox maxBox = new HBox(10);
            maxBox.getChildren().setAll(maxFieldLabel, maxField, maxButton);
            maxBox.setAlignment(Pos.CENTER);

            // Define second gui window
            VBox root = new VBox(25);
            root.getChildren().setAll(currencyBox, minBox, maxBox, display2);
            root.setAlignment(Pos.CENTER);
            Scene secondaryScene = new Scene(root, 300, 300);
            Stage secondaryStage = new Stage();
            secondaryStage.setScene(secondaryScene);
            secondaryStage.initModality(Modality.APPLICATION_MODAL);
            secondaryStage.show();
        });

        // Option 5
        Button option5Button = new Button();
        option5Button.setText("5 - Display program configurations");
        option5Button.setOnAction(e -> {
            display.setText(c.displayProgramConfigs());
        });

        // Put all components together in Scene
        VBox root = new VBox(25);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(input, display, option1Button, option2Button, option3Button, option4Button,
                option5Button);

        Scene scene = new Scene(root, 500, 400);
        stage.setScene(scene);
        stage.setTitle("Coin Sorter");
        stage.show();
    }
}
