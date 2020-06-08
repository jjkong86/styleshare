package styleshare.task.factory.service;

import org.springframework.stereotype.Service;
import styleshare.task.factory.beanFactory.PaymentType;
import styleshare.task.response.NaverPayResponse;

@Service("NAVERPAY")
public class NaverPayService implements PayService<NaverPayResponse> {

    @Override
    public NaverPayResponse prepare(PaymentType paymentType) {
        return NaverPayResponse.builder().description(paymentType + "를 사용중입니다.").build();
    }
}
