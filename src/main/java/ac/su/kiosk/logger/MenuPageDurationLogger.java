package ac.su.kiosk.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MenuPageDurationLogger {
    private static final Logger logger = LogManager.getLogger(MenuPageDurationLogger.class);

    // 메뉴 페이지에서 머문 시간을 저장하기 위한 로거
    public static void logPageDuration(
            String logTime,          // 로그 기록 시간
            String storeId,          // 매장 ID
            String kioskId,          // 키오스크 ID
            String menuPageId,       // 메뉴 페이지 ID
            long durationSeconds     // 머문 시간 (초 단위)
    ) {
        logger.info(String.format("%s\t%s\t%s\t%s\t%d seconds",
                logTime,
                storeId != null ? storeId : "-",
                kioskId != null ? kioskId : "-",
                menuPageId,
                durationSeconds
        ));
    }
}
