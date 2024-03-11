package com.yeruva.companyservice.company.messaging;

import com.yeruva.companyservice.company.CompanyService;
import com.yeruva.companyservice.company.dto.ReviewMessage;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageConsumer {

    @Autowired
    private CompanyService companyService;
    private final RabbitTemplate rabbitTemplate;

    public ReviewMessageConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage){
    companyService.updateCompanyRating(reviewMessage);
    }

}
