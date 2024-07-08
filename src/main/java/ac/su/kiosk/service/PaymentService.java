package ac.su.kiosk.service;

import ac.su.kiosk.constant.PaymentStatus;
import ac.su.kiosk.domain.Order;
import ac.su.kiosk.domain.OrderModuleDTO;
import ac.su.kiosk.repository.OrderModuleDTORepository;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentService {
    private final IamportClient iamportClient;
    private final OrderModuleDTORepository orderModuleDTORepository;

    public OrderModuleDTO findRequestDto(String orderUid) {
        OrderModuleDTO orderModuleDTO = orderModuleDTORepository.findByOrderUid(orderUid)
                .orElseThrow(() -> new IllegalArgumentException("주문이 없습니다."));
        return orderModuleDTO;
    }
    public IamportResponse<Payment> paymentByCallback(OrderModuleDTO request) {
        // 이 Payment는 현재 프로젝트에서 생성한 Payment가 아닌 Iamport에서 만든 Payment

        try {
            // 결제 단건 조회(아임포트)
            IamportResponse<Payment> iamportResponse = iamportClient.paymentByImpUid(request.getPaymentUid());
            // 주문내역 조회
//            Order order = orderRepository.findOrderAndPayment(request.getOrderUid())
//                    .orElseThrow(() -> new IllegalArgumentException("주문 내역이 없습니다."));

            OrderModuleDTO orderModuleDTO = orderModuleDTORepository.findByOrderUid(request.getOrderUid())
                    .orElseThrow(() -> new IllegalArgumentException("주문 내역이 없습니다."));

            if (!iamportResponse.getResponse().getStatus().equals("paid")) {
                // 주문, 결제 삭제
                orderModuleDTORepository.delete(orderModuleDTO);
                throw new RuntimeException("결제 미완료");
            }

            Long price = orderModuleDTO.getPrice();

            // 실 결제 금액
            int iamportPrice = iamportResponse.getResponse().getAmount().intValue();

            if (iamportPrice != price) {
                orderModuleDTORepository.delete(orderModuleDTO);
                iamportClient.cancelPaymentByImpUid(new CancelData(iamportResponse.getResponse().getImpUid(), true, new BigDecimal(iamportPrice)));
            }

            orderModuleDTO.changePaymentBySuccess(PaymentStatus.OK, iamportResponse.getResponse().getImpUid());

            return iamportResponse;

        } catch (IamportResponseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public OrderModuleDTO makeOrderModuleDTO() {

        // 원래는
        // public OrderModuleDTO makeOrderModuleDTO(Admin admin, Payment payment) {}
        // 이렇게 되어야함 그래서 OrderModuleDTO를 생성해서

        OrderModuleDTO orderModuleDTO = new OrderModuleDTO();
        orderModuleDTO.setStatus(PaymentStatus.READY);
        orderModuleDTO.setEmail("test@example.com");
        orderModuleDTO.setAddress("서울특별시 노원구 화랑로");
        orderModuleDTO.setStoreName("5조");
        orderModuleDTO.setOrderUid(UUID.randomUUID().toString());
        orderModuleDTO.setPrice(100L);
        return orderModuleDTO;
    }
}
