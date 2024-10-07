package ac.su.kiosk.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderCompleteLogger {
    private static final Logger logger = LogManager.getLogger(OrderCompleteLogger.class);

    // 주문 정보 및 결제 진행 정보를 저장하기 위한 로거
    public static void logOrder(
            String logType,         // 요청 유형
            String logTime,         // 요청 시간(결제 시간)
            String url,             // 요청 엔드포인트
            String method,          // HTTP METHODS
            String storeId,         // 매장 ID
            String kioskId,         // 키오스크 ID
            String productId,       // 주문한 상품 ID
            String orderId,         // 주문 번호
            String payload,         //
            boolean isVoiceOrder    // 음성 주문 여부
    ) {
        logger.info(String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",
                logType,
                logTime,
                url,
                method,
                storeId != null ? storeId : "-",
                kioskId != null ? kioskId : "-",
                productId,
                orderId,
                payload,
                isVoiceOrder ? "VoiceOrder" : "RegularOrder"
        ));
    }
}
