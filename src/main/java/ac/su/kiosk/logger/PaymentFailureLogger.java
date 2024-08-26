package ac.su.kiosk.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PaymentFailureLogger {
    private static final Logger logger = LogManager.getLogger(PaymentFailureLogger.class);

    // 결제 실패에 대한 정보를 저장하기 위한 로거
    public static void logPaymentFailure(
            String logType,          // 요청 유형
            String logTime,          // 요청 시간
            String url,              // 요청 엔드포인트
            String method,           // HTTP METHODS
            String storeId,          // 매장 ID
            String kioskId,          // 키오스크 ID
            String transactionId,    // 요청 고유값
            String orderId,          // 주문 번호
            String failureReason     // 결제 실패 이유
    ) {
        logger.error(String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",
                logType,
                logTime,
                url,
                method,
                storeId != null ? storeId : "-",
                kioskId != null ? kioskId : "-",
                transactionId,
                orderId,
                failureReason
        ));
    }
}