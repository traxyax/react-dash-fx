package com.pultrax.reactdashfx.fxcontroller;

import com.pultrax.reactdashfx.sale.Sale;
import com.pultrax.reactdashfx.sale.SaleService;
import com.pultrax.reactdashfx.sale.interfaces.ISaleCountByUnitPriceXQuantity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class DashboardController implements Initializable {

    @FXML
    private TableColumn<Sale, Integer> agentCodeCol;

    @FXML
    private TableColumn<Sale, Integer> amountCol;

    @FXML
    private CheckBox areaCB;

    @FXML
    private AreaChart<?, ?> areaChart;

    @FXML
    private CheckBox barCB;

    @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private TableColumn<Sale, LocalDate> dateCol;

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
    private TableColumn<Sale, String> productCodeCol;

    @FXML
    private TableColumn<Sale, Integer> quantityCol;

    @FXML
    private TableView<Sale> saleTable;

    @FXML
    private Label turnoverL;

    @FXML
    private TableColumn<?, ?> unitPriceCol;

    private static DashboardController instance;

    private static SaleService saleService;

    private ObservableList<Sale> sales;

    private List<ISaleCountByUnitPriceXQuantity> pieChartData;

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
        initData();
        initLabel();
        initTable();
        drawChart();
    }

    private void initData() {
        sales = FXCollections.observableList(saleService.getSales());
        pieChartData = saleService.getPieChartData();
//        System.out.println(pieChartData.get(0).getUnitPrice());
    }

    private void initTable() {
        agentCodeCol.setCellValueFactory(new PropertyValueFactory<>("agentCode"));
        productCodeCol.setCellValueFactory(new PropertyValueFactory<>("productCode"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        unitPriceCol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        refreshTable();
    }

    private void refreshTable() {
        saleTable.setItems(sales);
    }

    private void initLabel() {
        nbAgentL.setText(
                String.valueOf(saleService.getNbAgent())
        );
        nbProductL.setText(
                String.valueOf(saleService.getNbProduit())
        );
        turnoverL.setText(
                String.valueOf(saleService.getTurnover()) + " FCFA"
        );
    }

    private void drawChart() {
        drawPieChart();
    }

    private void drawPieChart() {
        for (ISaleCountByUnitPriceXQuantity data :
                pieChartData) {
            pieChart.getData().add(new PieChart.Data(
                    String.valueOf(data.getUnitPrice()),
                    data.getTotalAmount()
            ));
        }
    }
}
