package styleshare.task.factory.service;

import styleshare.task.factory.beanFactory.PaymentType;
import org.springframework.stereotype.Service;

@Service("NAVERPAY")
public class NaverPayService implements PayService {

    @Override
    public String prepare(PaymentType paymentType) {
        return paymentType + "를 사용중입니다.";
    }
}
