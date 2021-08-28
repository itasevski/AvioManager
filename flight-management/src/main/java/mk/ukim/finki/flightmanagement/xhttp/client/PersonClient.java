package mk.ukim.finki.flightmanagement.xhttp.client;

import mk.ukim.finki.flightmanagement.domain.valueobject.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class PersonClient {

    private final RestTemplate restTemplate;
    private final String serverUrl;

    public PersonClient(@Value("${aviomanager.role-management.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public List<Person> findAll() {
        try {
            return this.restTemplate.exchange(uri().path("").build().toUri(),
                    HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Person>>() {
                    }).getBody();
        }
        catch (Exception exception) {
            return Collections.emptyList();
        }
    }

}
