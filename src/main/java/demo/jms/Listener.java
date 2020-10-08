package demo.jms;

import com.google.gson.Gson;
import demo.dto.CallbackDto;
import demo.dto.RequestDto;
import demo.model.Request;
import demo.service.EmailService;
import demo.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.Map;
import java.util.Optional;

@Component
public class Listener {

    @Autowired
    RequestService requestService;
    @Autowired
    EmailService emailService;

    @JmsListener(destination = "demo.in.queue")
    @SendTo("verifier.out.queue")
    public String receiveMessage(final Message jsonMessage) throws JMSException {
        System.out.println("Received message " + jsonMessage);
        CallbackDto callback = new CallbackDto();
        try {
            if (jsonMessage instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) jsonMessage;
                RequestDto requestDto = new Gson().fromJson(textMessage.getText(), RequestDto.class);
                Request request = requestService.getRequest(requestDto.getRequestId());
                request.setStatus(requestDto.getStatus());
                emailService.sendSimpleMessage(request.getUser().getEmail(), "Verification", request.getStatus());
                requestService.saveRequest(request);
                callback.setModelType(Request.class.getSimpleName());
                callback.setId(requestDto.getRequestId());
            }
            callback.setStatus(true);

        } catch (Throwable ex) {
            callback.setStatus(false);
        }
        return new Gson().toJson(callback);
    }

    @JmsListener(destination = "demo.out.queue")
    public void receiveCallbackMessage(final Message jsonMessage) throws JMSException {
        System.out.println("Received message " + jsonMessage);
        if (jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) jsonMessage;
            CallbackDto callback = new Gson().fromJson(textMessage.getText(), CallbackDto.class);
            if (callback.getModelType().equals(Request.class.getSimpleName()) && callback.getStatus()) {
                Request request = requestService.getRequest(callback.getId());
                request.setDelivered(true);
                requestService.saveRequest(request);
            }
        }
    }
}
