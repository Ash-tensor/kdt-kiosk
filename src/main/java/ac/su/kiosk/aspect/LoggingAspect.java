package ac.su.kiosk.aspect;

import ac.su.kiosk.logDto.*;
import ac.su.kiosk.logger.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Aspect
@Component
public class LoggingAspect {

    // AfterReturning 어노테이션을 사용하여 메서드 실행 후에 로깅을 수행
    @AfterReturning(pointcut = "execution(* ac.su.kiosk.controller.OrderController.createOrderRequest(..))", returning = "result")
    public void logAfterOrderCompletion(JoinPoint joinPoint, Object result) {
        OrderRequest orderRequest = (OrderRequest) joinPoint.getArgs()[0];  // DTO 추출

        OrderCompleteLogger.logOrder(
                "ORDER_COMPLETE",
                LocalDateTime.now().toString(),
                "/completeOrder",
                "POST",
                orderRequest.getStoreId(),
                orderRequest.getKioskId(),
                orderRequest.getProductId(),
                orderRequest.getOrderId(),
                orderRequest.getPayload(),
                false
        );
    }

    @AfterReturning(pointcut = "execution(* ac.su.kiosk.controller.PaymentController.failPayment(..))", returning = "result")
    public void logAfterPaymentFailure(JoinPoint joinPoint, Object result) {
        PaymentRequest paymentRequest = (PaymentRequest) joinPoint.getArgs()[0];  // DTO 추출

        PaymentFailureLogger.logPaymentFailure(
                "PAYMENT_FAILURE",
                LocalDateTime.now().toString(),
                "/failPayment",
                "POST",
                paymentRequest.getStoreId(),
                paymentRequest.getKioskId(),
                UUID.randomUUID().toString(),
                paymentRequest.getOrderId(),
                paymentRequest.getFailureReason()
        );
    }

    @AfterReturning(pointcut = "execution(* ac.su.kiosk.controller.UserInteractionController.buttonEvent(..))", returning = "result")
    public void logUserButtonEvent(JoinPoint joinPoint, Object result) {
        ButtonEventRequest buttonEventRequest = (ButtonEventRequest) joinPoint.getArgs()[0];  // DTO 추출

        UserButtonEventLogger.logButtonEvent(
                LocalDateTime.now().toString(),
                "/buttonEvent",
                buttonEventRequest.getStoreId(),
                buttonEventRequest.getKioskId(),
                buttonEventRequest.getButtonId(),
                buttonEventRequest.getEventType(),
                buttonEventRequest.getAdditionalData()
        );
    }

    @AfterReturning(pointcut = "execution(* ac.su.kiosk.controller.MenuStayController.menuPageDuration(..))", returning = "result")
    public void logMenuPageDuration(JoinPoint joinPoint, Object result) {
        MenuPageDurationRequest menuPageDurationRequest = (MenuPageDurationRequest) joinPoint.getArgs()[0];  // DTO 추출

        MenuPageDurationLogger.logPageDuration(
                LocalDateTime.now().toString(),
                menuPageDurationRequest.getStoreId(),
                menuPageDurationRequest.getKioskId(),
                menuPageDurationRequest.getMenuPageId(),
                menuPageDurationRequest.getDurationSeconds()
        );
    }

    @AfterReturning(pointcut = "execution(* ac.su.kiosk.controller.VersionUpdateController.softwareUpdate(..))", returning = "result")
    public void logSoftwareUpdate(JoinPoint joinPoint, Object result) {
        SoftwareUpdateRequest softwareUpdateRequest = (SoftwareUpdateRequest) joinPoint.getArgs()[0];  // DTO 추출

        SoftwareUpdateLogger.logSoftwareUpdate(
                LocalDateTime.now().toString(),
                softwareUpdateRequest.getStoreId(),
                softwareUpdateRequest.getKioskId(),
                softwareUpdateRequest.getOldVersion(),
                softwareUpdateRequest.getNewVersion(),
                softwareUpdateRequest.isUpdateSuccess()
        );
    }
}
