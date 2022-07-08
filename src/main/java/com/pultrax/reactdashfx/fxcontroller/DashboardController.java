package com.pultrax.reactdashfx.fxcontroller;

import com.pultrax.reactdashfx.sale.SaleService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class DashboardController implements Initializable {

    @FXML
    private TableColumn<?, ?> agentCodeCol;

    @FXML
    private TableColumn<?, ?> amountCol;

    @FXML
    private CheckBox areaCB;

    @FXML
    private AreaChart<?, ?> areaChart;

    @FXML
    private CheckBox barCB;

    @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private TableColumn<?, ?> dateCol;

    @FXML
    private CheckBox lineCB;

    @FXML
    private LineChart<?, ?> lineChart;

    @FXML
    private Label nbAgentL;

    @FXML
    private Label nbProductL;

    @FXML
    private CheckBox pieCB;

    @FXML
    private PieChart pieChart;

    @FXML
    private TableColumn<?, ?> productCodeCol;

    @FXML
    private TableColumn<?, ?> quant;

    @FXML
    private TableView<?> saleTable;

    @FXML
    private Label turnoverL;

    @FXML
    private TableColumn<?, ?> unitPriceCol;

    private static DashboardController instance;

    private static SaleService saleService;

    @Autowired
    private DashboardController(SaleService saleService) {
        DashboardController.saleService = saleService;
    }

    public static DashboardController getInstance() {
        if (instance == null) {
            instance = new DashboardController(DashboardController.saleService);
        }

        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(saleService.getSales());
    }
}
