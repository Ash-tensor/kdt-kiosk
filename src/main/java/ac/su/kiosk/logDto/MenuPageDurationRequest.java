package ac.su.kiosk.logDto;

import lombok.Data;

@Data
public class MenuPageDurationRequest {
    private String storeId;
    private String kioskId;
    private String menuPageId;
    private long durationSeconds;

    public MenuPageDurationRequest(String storeId, String kioskId, String menuPageId, long durationSeconds) {
        this.storeId = storeId;
        this.kioskId = kioskId;
        this.menuPageId = menuPageId;
        this.durationSeconds = durationSeconds;
    }
}
