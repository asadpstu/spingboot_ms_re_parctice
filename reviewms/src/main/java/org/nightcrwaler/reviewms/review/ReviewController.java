package org.nightcrwaler.reviewms.review;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/review")
public class ReviewController {

    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.routingkey}")
    private String routingKey;

    public ReviewController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping
    public String root(){
        return "Review service is running";
    }

    @PostMapping("/create")
    public String createReview(@RequestBody ReviewModel review) {
        rabbitTemplate.convertAndSend(exchange, routingKey, review);
        return "Review sent successfully!";
    }
}
