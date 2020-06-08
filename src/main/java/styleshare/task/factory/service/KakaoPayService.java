package styleshare.task.factory.service;

import org.springframework.stereotype.Service;
import styleshare.task.factory.beanFactory.PaymentType;
import styleshare.task.response.KakaoPayResponse;

@Service("KAKAOPAY")
public class KakaoPayService implements PayService<KakaoPayResponse> {

    @Override
    public KakaoPayResponse prepare(PaymentType paymentType) {
        return KakaoPayResponse.builder().description(paymentType + "를 사용중입니다.").build();
    }
}
