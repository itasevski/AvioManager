package mk.ukim.finki.flightmanagement.xhttp.client;

import mk.ukim.finki.flightmanagement.domain.valueobject.Country;
import mk.ukim.finki.flightmanagement.domain.valueobject.CountryId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * CountryClient class - an HTTP client class used to send requests to the "countries" bounded context Open Host Service.
 */
@Service
public class CountryClient {

    private final RestTemplate restTemplate;
    private final String serverUrl;

    public CountryClient(@Value("${aviomanager.countries.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public Country findById(CountryId countryId) {
        try {
            return this.restTemplate.exchange(uri().path("/api/country/find-id/" + countryId.getId()).build().toUri(),
                    HttpMethod.GET, null,
                    new ParameterizedTypeReference<Country>() {
                    }).getBody();
        }
        catch (Exception exception) {
            return null;
        }
    }

}

