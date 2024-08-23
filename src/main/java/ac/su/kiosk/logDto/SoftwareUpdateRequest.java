package ac.su.kiosk.logDto;

import lombok.Data;

@Data
public class SoftwareUpdateRequest {
    private String storeId;
    private String kioskId;
    private String oldVersion;
    private String newVersion;
    private boolean updateSuccess;

    public SoftwareUpdateRequest(String storeId, String kioskId, String oldVersion, String newVersion, boolean updateSuccess) {
        this.storeId = storeId;
        this.kioskId = kioskId;
        this.oldVersion = oldVersion;
        this.newVersion = newVersion;
        this.updateSuccess = updateSuccess;
    }
}
