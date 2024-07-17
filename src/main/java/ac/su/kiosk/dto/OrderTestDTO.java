package ac.su.kiosk.dto;

// SPAMMAYO : gpt에서 받은 주문을 json으로 받는걸 테스트하기 위한 DTO, 추후 가격만 받게 변경 예정
public class OrderTestDTO {
    private String type;
    private String temperature;
    private String size;
    private int quantity;
    private long price;

    // 기본 생성자
    public OrderTestDTO() {}

    // getter와 setter
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
