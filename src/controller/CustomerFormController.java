package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.DatabaseCode;
import view.tm.CustomerTm;

public class CustomerFormController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;
    public Button btnSave;
    public TableView<CustomerTm> tblCustomer;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public TableColumn colOption;
    public TextField txtSearch;

    public void initialize() {
        loadTable("");
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            loadTable(newValue);
        });
        loadColumn();
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null != newValue) {
                setData(newValue);
            }
        });
    }

    private void setData(CustomerTm newValue) {
        txtId.setText(newValue.getId());
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        txtSalary.setText(String.valueOf(newValue.getSalary()));
        btnSave.setText("Update Customer");
    }

    private void loadColumn() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void loadTable(String searchText) {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();
        for (Customer c : DatabaseCode.loadTable()) {
            if (c.getId().contains(searchText) || c.getName().contains(searchText) || c.getAddress().contains(searchText)) {
                Button btn = new Button("Delete");
                String id = c.getId();
                btn.setOnAction(e -> {
                    new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this customer?", ButtonType.YES, ButtonType.NO).showAndWait().ifPresent(buttonType -> {
                        if (buttonType == ButtonType.YES) {
                            try {
                                DatabaseCode.deleteCustomer(id);
                                new Alert(Alert.AlertType.INFORMATION, "Customer Deleted Successfully").show();
                                loadTable(searchText);
                            } catch (Exception ex) {
                                new Alert(Alert.AlertType.ERROR, "Failed to delete the customer").show();
                            }
                        } else {
                            new Alert(Alert.AlertType.INFORMATION, "Deletion Cancelled").show();
                        }
                    });
                });
                obList.add(new CustomerTm(c.getId(), c.getName(), c.getAddress(), c.getSalary(), btn));
            }
        }
        tblCustomer.setItems(obList);
    }

    public void saveCustomerOnAction(ActionEvent actionEvent) {
        if (btnSave.getText().equals("Save Customer")) {
            try {
                Customer c = new Customer(txtId.getText(), txtName.getText(), txtAddress.getText(), Double.parseDouble(txtSalary.getText()));
                DatabaseCode.saveCustomer(c);
                loadTable("");
                new Alert(Alert.AlertType.INFORMATION, "Customer Saved Successfully").show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the customer").show();
            }
        } else {
            try {
                Customer c = new Customer(txtId.getText(), txtName.getText(), txtAddress.getText(), Double.parseDouble(txtSalary.getText()));
                DatabaseCode.updateCustomer(c);
                loadTable(txtSearch.getText());
                new Alert(Alert.AlertType.INFORMATION, "Customer Updated Successfully").show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the customer").show();
            }
        }
    }
}
