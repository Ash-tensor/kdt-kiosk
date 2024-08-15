package ac.su.kiosk.logger;

import ac.su.kiosk.controller.OrderCompleteController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PaymentFailureLogger {
    public static final Logger logger = LogManager.getLogger(OrderCompleteController.class);

    // 주문 정보 및 결제 진행 정보를 저장하기 위한 로그
    //    -> 비정상적 활동 감지 및 프로세스 최적화 목표
    public static void logOrder(
            String logType, // 요청 유형
            String logTime, // 요청 시간(결제 시간)
            String url, // 요청 엔드포인트
            String method, // HTTP METHODS
            String storeId, // 매장 ID
            String kioskId, // 키오스크 ID
            String tranctionId, // 요청 고유값
            String productId, // 주문한 상품 ID(not null)
            String orderId, // 주문 번호
            String payload // 수량 또는 커스텀 옵션 데이터
    ) {
        logger.info(String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",
                logType,
                logTime,
                url,
                method,
                storeId != null ? storeId : "-",
                kioskId != null ? kioskId : "-",
                tranctionId,
                productId,
                orderId,
                payload
        ));
    }
}
