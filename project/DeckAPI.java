// deckapi: simple client for the deckofcardsapi

import java.util.*;
import java.net.*;
import java.io.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DeckAPI {

    private String baseUrl; // base url for api
    private Gson gson; // gson instance for json parsing
    public DeckAPI() {
        baseUrl = "https://deckofcardsapi.com/api/deck"; // api endpoint
        gson = new GsonBuilder().create(); // setup json parser
    }

    private String callAPI(String urlString) { // call the api and return raw json string
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
            );
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Error calling API: " + e.getMessage());
            return null;
        }
        return response.toString();
    }

    private static class NewDeckResponse { // response for new deck creation
        boolean success;
        String deck_id;
        int remaining;
        boolean shuffled;
    }

    private static class DrawResponse { // response when drawing cards
        boolean success;
        String deck_id;
        CardJSON[] cards;
        int remaining;
    }

    private static class CardJSON { // structure for a single card in json
        String code;
        String image;
        String value;
        String suit;
    }

    public String createShuffledDeck(int deckCount) { // create a shuffled deck and return the deck id
        String url = baseUrl + "/new/shuffle/?deck_count=" + deckCount;
        String json = callAPI(url);

        if (json == null) {
            return null;
        }

        NewDeckResponse resp = gson.fromJson(json, NewDeckResponse.class);
        return resp.deck_id;
    }

    public List<Card> drawCards(String deckId, int count) { // draw cards from a deck and convert to Card objects
        List<Card> result = new ArrayList<>();
        String url = baseUrl + "/" + deckId + "/draw/?count=" + count;
        String json = callAPI(url);
        if (json == null) {
            return result;
        }
        DrawResponse resp = gson.fromJson(json, DrawResponse.class);
        if (resp.cards == null) {
            return result;
        }
        for (CardJSON cj : resp.cards) {
            int blackjackValue = convertRankToValue(cj.value);
            Card card = new Card(cj.code, cj.value, cj.suit, blackjackValue);
            result.add(card);
        }
        return result;
    }

    public void reshuffle(String deckId, boolean remainingOnly) { // reshuffle the deck, optionally only remaining cards
        String url = baseUrl + "/" + deckId + "/shuffle/?remaining=" + remainingOnly;
        callAPI(url);
    }

    private int convertRankToValue(String rank) { // convert card rank string to blackjack numeric value
        if (rank.equals("ACE")) {
            return 11;
        } else if (rank.equals("KING") || rank.equals("QUEEN") || rank.equals("JACK")) {
            return 10;
        } else {
            try {
                return Integer.parseInt(rank);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }
}