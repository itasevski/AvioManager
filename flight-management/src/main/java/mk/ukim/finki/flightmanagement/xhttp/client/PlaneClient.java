package mk.ukim.finki.flightmanagement.xhttp.client;

import mk.ukim.finki.flightmanagement.domain.valueobject.Plane;
import mk.ukim.finki.flightmanagement.domain.valueobject.PlaneId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PlaneClient {

    private final RestTemplate restTemplate;
    private final String serverUrl;

    public PlaneClient(@Value("${aviomanager.planes.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public Plane findById(PlaneId planeId) {
        try {
            return this.restTemplate.exchange(uri().path("/api/plane/" + planeId.getId()).build().toUri(),
                    HttpMethod.GET, null,
                    new ParameterizedTypeReference<Plane>() {
                    }).getBody();
        }
        catch (Exception exception) {
            return null;
        }
    }

}
