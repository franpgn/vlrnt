package com.riot.vlrnt;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@ApplicationPath("/api")
@Path("/v1")
public class VLRNTApplication extends Application {
    @Path("/stats")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public String playerStats(Player player) throws URISyntaxException, IOException, InterruptedException {
        String url = "https://api.kyroskoh.xyz/valorant/v1/mmr/"
                + player.getRegion()+"/"
                + player.getId()+"/"
                + player.getTag();

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
