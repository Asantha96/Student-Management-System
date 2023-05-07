package lk.developersstack.lms.dto;

public class LaptopDto {
    private long lapId;
    private String brand;

    public LaptopDto() {
    }

    public LaptopDto(long lapId, String brand) {
        this.lapId = lapId;
        this.brand = brand;
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
}
