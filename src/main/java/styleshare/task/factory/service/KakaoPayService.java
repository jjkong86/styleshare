package styleshare.task.factory.service;

import org.springframework.stereotype.Service;
import styleshare.task.factory.beanFactory.PaymentType;

@Service("KAKAOPAY")
public class KakaoPayService implements PayService {

    @Override
    public String prepare(PaymentType paymentType) {
        return paymentType + "를 사용중입니다.";
    }
}
