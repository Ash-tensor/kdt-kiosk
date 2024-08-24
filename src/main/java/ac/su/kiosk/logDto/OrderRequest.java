package ac.su.kiosk.logDto;

import lombok.Data;

@Data
public class OrderRequest {
    private String storeId;
    private String kioskId;
    private String productId;
    private String orderId;
    private String payload;
    private String uuid;

    public OrderRequest(String storeId, String kioskId, String productId, String orderId, String payload, String uuid) {
        this.storeId = storeId;
        this.kioskId = kioskId;
        this.productId = productId;
        this.orderId = orderId;
        this.payload = payload;
        this.uuid = uuid;
    }
}
