package org.nightcrwaler.companyms.config;
import org.nightcrwaler.companyms.company.ReviewModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReviewListener {

    @RabbitListener(queues = "${app.rabbitmq.queue}")
    public void handleReview(ReviewModel review) {
        System.out.println("Received review: " + review);
    }
}

