package styleshare.task.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import styleshare.task.mapper.CommerceMapper;

@Slf4j
@Service
public class CommerceService {

	private final CommerceMapper commerceMapper;

    public CommerceService(CommerceMapper commerceMapper) {
        this.commerceMapper = commerceMapper;
    }

}
