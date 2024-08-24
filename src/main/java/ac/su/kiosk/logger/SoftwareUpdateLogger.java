package ac.su.kiosk.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SoftwareUpdateLogger {
    private static final Logger logger = LogManager.getLogger(SoftwareUpdateLogger.class);

    // 소프트웨어 업데이트 정보를 저장하기 위한 로거
    public static void logSoftwareUpdate(
            String logTime,          // 업데이트 시간
            String storeId,          // 매장 ID
            String kioskId,          // 키오스크 ID
            String oldVersion,       // 이전 버전
            String newVersion,       // 새 버전
            boolean updateSuccess    // 업데이트 성공 여부
    ) {
        logger.info(String.format("%s\t%s\t%s\t%s\t%s\t%s",
                logTime,
                storeId != null ? storeId : "-",
                kioskId != null ? kioskId : "-",
                oldVersion,
                newVersion,
                updateSuccess ? "Success" : "Failure"
        ));
    }
}
