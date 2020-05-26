package styleshare.task.factory.service;

import styleshare.task.factory.beanFactory.PaymentType;
import org.springframework.stereotype.Service;

@Service
public interface PayService {
    String prepare(PaymentType paymentType);
}
