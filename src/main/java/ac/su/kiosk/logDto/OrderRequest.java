package ac.su.kiosk.logDto;

import lombok.Data;

@Data
public class OrderRequest {
    private String storeId;
    private String kioskId;
    private String productId;
    private String orderId;
    private String payload;

    public OrderRequest(String storeId, String kioskId, String productId, String orderId, String payload) {
        this.storeId = storeId;
        this.kioskId = kioskId;
        this.productId = productId;
        this.orderId = orderId;
        this.payload = payload;
    }
}
