package demo.jms;

import com.google.gson.Gson;
import demo.dto.CallbackDto;
import demo.model.Request;
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

    @JmsListener(destination = "demo.in.queue")
    @SendTo("verifier.out.queue")
    public String receiveMessage(final Message jsonMessage) throws JMSException {
        String messageData = null;
        System.out.println("Received message " + jsonMessage);
        String response = null;
        if(jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)jsonMessage;
            messageData = textMessage.getText();
            Map map = new Gson().fromJson(messageData, Map.class);
            response  = "Hello " + map.get("name");
        }
        return response;
    }

    @JmsListener(destination = "demo.out.queue")
    public void receiveCallbackMessage(final Message jsonMessage) throws JMSException {
        String messageData = null;
        System.out.println("Received message " + jsonMessage);
        String response = null;
        if(jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)jsonMessage;
            messageData = textMessage.getText();
            CallbackDto callback = new Gson().fromJson(messageData, CallbackDto.class);
            if (callback.getModelType().equals(Request.class.getSimpleName())){
                Request request = requestService.getRequest(callback.getId());
                request.setStatus(Request.RequestStatus.DELIVERED);
                requestService.saveRequest(request);
            }
        }
    }

}
