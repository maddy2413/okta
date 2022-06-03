package com.okta.examples.apiamhooks.controller;

import com.okta.examples.apiamhooks.model.Beer;
import com.okta.examples.apiamhooks.model.Person;
import com.okta.examples.apiamhooks.model.hooks.*;
import com.okta.examples.apiamhooks.repository.PersonRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hooks")
public class HooksController {

    private PersonRepository personRepository;

    public HooksController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping("/apiam")
    public TokenHookResponse apiam(@RequestBody TokenHookRequest request) {
        String login = request.getData().getContext().getUser().getProfile().getLogin();
        Person person = personRepository.findByEmail(login);

        TokenHookResponse response = new TokenHookResponse();
       // if (person != null) {
        AccessTokenPatchResponse idTokenPatchResponse  =new AccessTokenPatchResponse();
          //  IDTokenPatchResponse idTokenPatchResponse = new IDTokenPatchResponse();
            idTokenPatchResponse.getValue().add(
                new TokenPatchResponse.Value(
                //   "add", "/claims/beers", transformBeers(person.getFavoriteBeers())
                     //  "replace","/access/claims/sub",transformBeers(person.getFavoriteBeers())

                       "replace","/claims/sub","yuvi@gmail.com"
                   //     "replace","/token/lifetime/expiration","3500"



                )
            );
            response.getCommands().add(idTokenPatchResponse);
     //   }
        return response;
    }

    private List<String> transformBeers(List<Beer> beers) {
        return beers.stream().map(Beer::getName).collect(Collectors.toList());
    }
}
