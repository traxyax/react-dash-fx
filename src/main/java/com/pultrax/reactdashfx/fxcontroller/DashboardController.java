package com.pultrax.reactdashfx.fxcontroller;

import com.pultrax.reactdashfx.sale.Sale;
import com.pultrax.reactdashfx.sale.SaleService;
import com.pultrax.reactdashfx.sale.interfaces.ISaleCountByUnitPriceXQuantity;
import com.pultrax.reactdashfx.sale.interfaces.ISaleTotalAmountByAgentCodeAndYear;
import com.pultrax.reactdashfx.sale.interfaces.ISumUnitPriceXQuantitySaleByDate;
import com.pultrax.reactdashfx.sale.interfaces.IUnitPriceAndTotalQuantityByDateAndUnitPrice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class DashboardController implements Initializable {

    @FXML
    private BorderPane rootWindow;

    @FXML
    private HBox chartHBox;

    @FXML
    private TableColumn<Sale, Integer> agentCodeCol;

    @FXML
    private TableColumn<Sale, Integer> amountCol;

    @FXML
    private CheckBox areaCB;

    @FXML
    private AreaChart<String, Number> areaChart;

    @FXML
    private CheckBox barCB;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private TableColumn<Sale, LocalDate> dateCol;

    @FXML
    private CheckBox lineCB;

    @FXML
    private CheckBox tableCB;

    @FXML
    private LineChart<String, Number> lineChart;

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

    private List<ISumUnitPriceXQuantitySaleByDate> lineChartData;

    private List<ISaleTotalAmountByAgentCodeAndYear> barChartData;

    private List<IUnitPriceAndTotalQuantityByDateAndUnitPrice> areaChartData;

    private List<String> years;

    private Long nbProduct;

    private Font font;

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
        lineChartData = saleService.getLineChartData();
        areaChartData = saleService.getAreaChartData();
        years = saleService.getSaleYears();
        nbProduct = saleService.getNbProduit();
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
                String.valueOf(nbProduct)
        );
        turnoverL.setText(
                String.valueOf(saleService.getTurnover()) + " FCFA"
        );
    }

    private void drawChart() {
        drawPieChart();
        drawLineChart();
        drawBarChart();
        drawAreaChart();
    }

    private void drawAreaChart() {
        Object t [][] = new Object[Math.toIntExact(nbProduct)][Math.toIntExact(nbProduct)];

        List<String> listProduit = saleService.getUnitPrice();

        for(int i = 0; i< listProduit.size(); i++){
            t[i][0] = listProduit.get(i);
        }


        for(int i = 0; i < nbProduct; i++){
            t[i][1] = new XYChart.Series<String, Number>();
        }

        XYChart.Series<String, Number> sr = null;

        for (IUnitPriceAndTotalQuantityByDateAndUnitPrice data: areaChartData) {
            for (int i = 0; i < nbProduct; i++){

                if(t[i][0].equals(String.valueOf(data.getUnitPrice()))){
                    sr = (XYChart.Series<String, Number>) t[i][1];
                    sr.getData().add(new XYChart.Data<String, Number>(data.getDate().toString(), data.getTotalQuantity()));
                }
            }
        }

        for(int i = 0; i < nbProduct; i++){
            sr = (XYChart.Series<String, Number>) t[i][1];
            sr.setName("AB"+t[i][0].toString());

            areaChart.getData().add(sr);
        }
    }

    private void drawBarChart() {

        for (String year:
             years) {

            barChartData = saleService.getBarChartData(year);

            XYChart.Series<String, Number> dataSeries = new XYChart.Series<String, Number>();
            dataSeries.setName(year);

            for (ISaleTotalAmountByAgentCodeAndYear data:
                 barChartData) {

                dataSeries.getData().add(new XYChart.Data<String, Number>(String.valueOf(data.getAgentCode()), data.getTotalAmount()));
            }

            barChart.getData().add(dataSeries);
        }
    }

    private void drawLineChart() {
        XYChart.Series<String, Number> dataSeries = new XYChart.Series<String, Number>();
        dataSeries.setName("All Year");

        for (ISumUnitPriceXQuantitySaleByDate data:
             lineChartData) {
            dataSeries.getData().add(new XYChart.Data<String, Number>(data.getDate().toString(), data.getTotalAmount()));
        }

        lineChart.getData().add(dataSeries);
    }

    private void drawPieChart() {
        for (ISaleCountByUnitPriceXQuantity data :
                pieChartData) {
            pieChart.getData().add(new PieChart.Data(
                    "AB" + String.valueOf(data.getUnitPrice()),
                    data.getTotalAmount()
            ));
        }
    }

    private void clearChart(){
        pieChart.getData().clear();
        lineChart.getData().clear();
        areaChart.getData().clear();
        barChart.getData().clear();
    }

    public void update(){
        initData();
        initLabel();
        refreshTable();
        clearChart();
        drawChart();
    }

    @FXML
    private void toggleAreaChart(ActionEvent event) {
        if(areaCB.isSelected()){
            chartHBox.getChildren().add(areaChart);
        }else {
            chartHBox.getChildren().remove(areaChart);
        }
    }

    @FXML
    private void toggleBarChart(ActionEvent event) {
        if(barCB.isSelected()){
            chartHBox.getChildren().add(barChart);
        }else {
            chartHBox.getChildren().remove(barChart);
        }
    }

    @FXML
    private void togglePieChart(ActionEvent event) {
        if(pieCB.isSelected()){
            chartHBox.getChildren().add(pieChart);
        }else {
            chartHBox.getChildren().remove(pieChart);
        }
    }

    @FXML
    private void toggleLineChart(ActionEvent event) {
        if(lineCB.isSelected()){
            chartHBox.getChildren().add(lineChart);
        }else {
            chartHBox.getChildren().remove(lineChart);
        }
    }

    @FXML
    private void toggleTable(ActionEvent event) {
        if (tableCB.isSelected()) {
            rootWindow.setBottom(saleTable);
        } else {
            rootWindow.getChildren().remove(saleTable);
        }
    }
}
