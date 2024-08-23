package ac.su.kiosk.service;

import ac.su.kiosk.constant.PaymentStatus;
import ac.su.kiosk.domain.Order;
import ac.su.kiosk.domain.OrderModuleDTO;
import ac.su.kiosk.dto.IAMPortDTO;
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

    // 화면에 표시되는 기능
//    public RequestPayDto findRequestDto(String orderUid){
//        Order order = orderRepository.findOrderAndPaymentAndMember(orderUid)
//                .orElseThrow(() -> new IllegalArgumentException("주문이 없습니다."));
//
//        return RequestPayDto.builder()
//                .buyerName(order.getMember().getUsername())
//                .buyerEmail(order.getMember().getEmail())
//                .buyerAddress(order.getMember().getAddress())
//                .paymentPrice(order.getPayment().getPrice())
//                .itemName(order.getItemName())
//                .orderUid(order.getOrderUid())
//                .build();
//    }
    public OrderModuleDTO findRequestDto(String orderUid) {
        OrderModuleDTO orderModuleDTO = orderModuleDTORepository.findByOrderUid(orderUid)
                .orElseThrow(() -> new IllegalArgumentException("주문이 없습니다."));
        return orderModuleDTO;
    }
    public IamportResponse<Payment> paymentByCallback(IAMPortDTO request) {
        // 이 Payment는 현재 프로젝트에서 생성한 Payment가 아닌 Iamport에서 만든 Payment

        try {
            // 결제 단건 조회(아임포트)
            IamportResponse<Payment> iamportResponse = iamportClient.paymentByImpUid(request.getPaymentUid());
            // 주문내역 조회
//            Order order = orderRepository.findOrderAndPayment(request.getOrderUid())
//                    .orElseThrow(() -> new IllegalArgumentException("주문 내역이 없습니다."));

            OrderModuleDTO orderModuleDTO = orderModuleDTORepository.findByOrderUid(request.getOrderUid())
                    .orElseThrow(() -> new IllegalArgumentException("주문 내역이 없습니다."));

            // 결제 완료가 아니면
//            if(!iamportResponse.getResponse().getStatus().equals("paid")) {
//                // 주문, 결제 삭제
//                orderRepository.delete(order);
//                paymentRepository.delete(order.getPayment());
//
//                throw new RuntimeException("결제 미완료");
//            }

            if (!iamportResponse.getResponse().getStatus().equals("paid")) {
                // 주문, 결제 삭제
                orderModuleDTORepository.delete(orderModuleDTO);
                throw new RuntimeException("결제 미완료");
            }

            // DB에 저장된 결제 금액
//            Long price = order.getPayment().getPrice();

            Long price = orderModuleDTO.getPrice();

            // 실 결제 금액
            int iamportPrice = iamportResponse.getResponse().getAmount().intValue();

            // 결제 금액 검증
//            if(iamportPrice != price) {
//                // 주문, 결제 삭제
//                orderRepository.delete(order);
//                paymentRepository.delete(order.getPayment());
//
//                // 결제금액 위변조로 의심되는 결제금액을 취소(아임포트)
//                iamportClient.cancelPaymentByImpUid(new CancelData(iamportResponse.getResponse().getImpUid(), true, new BigDecimal(iamportPrice)));
//
//                throw new RuntimeException("결제금액 위변조 의심");
//            }

            if (iamportPrice != price) {
                orderModuleDTORepository.delete(orderModuleDTO);
                iamportClient.cancelPaymentByImpUid(new CancelData(iamportResponse.getResponse().getImpUid(), true, new BigDecimal(iamportPrice)));
            }


            // 결제 상태 변경
//            order.getPayment().changePaymentBySuccess(PaymentStatus.OK, iamportResponse.getResponse().getImpUid());

            orderModuleDTO.changePaymentBySuccess(PaymentStatus.OK, iamportResponse.getResponse().getImpUid());

            return iamportResponse;

        } catch (IamportResponseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // 인자가 Admin admin, Payment payment 로 OrderDTO 를 ㅇ만들려했음
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


    // by ash : gpt의 주문을 위해 추가된 코드입니다.
    public OrderModuleDTO gptOrderModuleDTO(long price) {
        OrderModuleDTO orderModuleDTO = new OrderModuleDTO();
        orderModuleDTO.setStatus(PaymentStatus.READY);
        orderModuleDTO.setEmail("test@example.com");
        orderModuleDTO.setAddress("서울특별시 노원구 화랑로");
        orderModuleDTO.setStoreName("5조");
        orderModuleDTO.setOrderUid(UUID.randomUUID().toString());
        orderModuleDTO.setPrice(price);
        orderModuleDTO.setGpt(true);
        return orderModuleDTO;
    }
}