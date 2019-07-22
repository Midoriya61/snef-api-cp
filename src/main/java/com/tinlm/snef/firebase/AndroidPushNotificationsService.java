package com.tinlm.snef.firebase;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AndroidPushNotificationsService {

    private static final String FIREBASE_SERVER_KEY = "AAAAPcLNzmA:APA91bH8GCGBt__36jllSl6sAqvClana0BrX7nE7guXRpICvo9TaSz-UJCvH2ZPbgWI8WGWmYfn39Q65Z1nPfeJlXr-vACL0mh4YhfvYhnd51liqVZtFugy2QuM8YlcnxDoQkwAC1iu-";
    private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

    @Async
    public CompletableFuture<String> send(HttpEntity<String> entity) {

        RestTemplate restTemplate = new RestTemplate();

        /**
         https://fcm.googleapis.com/fcm/send
         Content-Type:application/json
         Authorization:key=FIREBASE_SERVER_KEY*/

        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
        restTemplate.setInterceptors(interceptors);

        String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);

        return CompletableFuture.completedFuture(firebaseResponse);
    }
}
