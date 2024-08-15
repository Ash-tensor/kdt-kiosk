package ac.su.kiosk.logDto;

import lombok.Data;

@Data
public class PaymentRequest {
    private String storeId;
    private String kioskId;
    private String orderId;
    private String failureReason;

    public PaymentRequest(String storeId, String kioskId, String orderId, String failureReason) {
        this.storeId = storeId;
        this.kioskId = kioskId;
        this.orderId = orderId;
        this.failureReason = failureReason;
    }
}
