package ac.su.kiosk.logDto;

import lombok.Data;

@Data
public class ButtonEventRequest {
    private String storeId;
    private String kioskId;
    private String buttonId;
    private String eventType;
    private String additionalData;

    public ButtonEventRequest(String storeId, String kioskId, String buttonId, String eventType, String additionalData) {
        this.storeId = storeId;
        this.kioskId = kioskId;
        this.buttonId = buttonId;
        this.eventType = eventType;
        this.additionalData = additionalData;
    }
}
