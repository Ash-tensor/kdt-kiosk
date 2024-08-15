package ac.su.kiosk.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserButtonEventLogger {
    private static final Logger logger = LogManager.getLogger(UserButtonEventLogger.class);

    // 사용자의 버튼 클릭 이벤트를 저장하기 위한 로거
    public static void logButtonEvent(
            String logTime,          // 이벤트 발생 시간
            String url,              // 요청 엔드포인트
            String storeId,          // 매장 ID
            String kioskId,          // 키오스크 ID
            String buttonId,         // 클릭된 버튼 ID
            String eventType,        // 이벤트 유형 (클릭, 더블클릭 등)
            String additionalData    // 추가 데이터 (필요 시)
    ) {
        logger.info(String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s",
                logTime,
                url,
                storeId != null ? storeId : "-",
                kioskId != null ? kioskId : "-",
                buttonId,
                eventType,
                additionalData != null ? additionalData : "-"
        ));
    }
}
