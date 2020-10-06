package demo.service;

import demo.model.Request;
import demo.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;

    public List<Request> list() {
        return requestRepository.findAll();
    }
    public void saveRequest(Request request){
        requestRepository.saveAndFlush(request);
    }

    public Request getRequest(long id){
        return requestRepository.getOne(id);
    }


}
