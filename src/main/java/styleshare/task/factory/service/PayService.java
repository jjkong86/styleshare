package styleshare.task.factory.service;

import styleshare.task.factory.beanFactory.PaymentType;
import org.springframework.stereotype.Service;
import styleshare.task.response.ApiCommonResponse;

@Service
public interface PayService<T extends ApiCommonResponse> {
     T prepare(PaymentType paymentType);
}
