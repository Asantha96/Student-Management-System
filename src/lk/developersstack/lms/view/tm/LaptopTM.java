package lk.developersstack.lms.view.tm;


import javafx.scene.control.Button;

public class LaptopTM {
    private long lapId;
    private String brand;
    private Button deleteButton;

    public LaptopTM(long lapId, String brand, Button deleteButton) {
        this.lapId = lapId;
        this.brand = brand;
        this.deleteButton = deleteButton;
    }

    public LaptopTM() {
    }

    public long getLapId() {
        return lapId;
    }

    public void setLapId(long lapId) {
        this.lapId = lapId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }
}
