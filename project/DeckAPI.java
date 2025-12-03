// DeckAPI.java, interacts with the deck of cards api

import java.util.*; // used for List and ArrayList
import java.net.*; // used for URL and HttpURLConnection
import java.io.*; // used for InputStreamReader and BufferedReader

import com.google.gson.Gson; // used for JSON parsing
import com.google.gson.GsonBuilder; // used for JSON parsing

public class DeckAPI { // interacts with the deck of cards api

    private String baseUrl;
    private Gson gson;
    public DeckAPI() {
        baseUrl = "https://deckofcardsapi.com/api/deck";
        gson = new GsonBuilder().create();
    }

    private String callAPI(String urlString) { // helper method to call the api
        StringBuilder response = new StringBuilder(); // stores the response

        try {
            URL url = new URL(urlString); // create url object
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET"); // set method to get
            conn.setConnectTimeout(5000); // set timeouts so it doesnt hang
            conn.setReadTimeout(5000); // set timeouts so it doesnt hang

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            String line; // read the response line by line
            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            in.close();
        } catch (Exception e) { // catch any exceptions
            System.out.println("Error calling API: " + e.getMessage());
            return null;
        }

        return response.toString();
    }

    private static class NewDeckResponse { // response for new deck
        boolean success; 
        String deck_id;
        int remaining;
        boolean shuffled;
    }

    private static class DrawResponse { // response for drawing cards
        boolean success;
        String deck_id;
        CardJSON[] cards;
        int remaining;
    }

    private static class CardJSON { // card representation in json
        String code;
        String image;
        String value;
        String suit;
    }

    public String createShuffledDeck(int deckCount) { // creates a new shuffled deck
        String url = baseUrl + "/new/shuffle/?deck_count=" + deckCount; // api url
        String json = callAPI(url);

        if (json == null) { // if api call failed
            return null;
        }

        NewDeckResponse resp = gson.fromJson(json, NewDeckResponse.class); // parse json
        return resp.deck_id;
    }

    public List<Card> drawCards(String deckId, int count) { // draws cards from the deck
        List<Card> result = new ArrayList<Card>(); // list to store drawn cards

        String url = baseUrl + "/" + deckId + "/draw/?count=" + count; // api url
        String json = callAPI(url); // call api

        if (json == null) { // if api call failed
            return result;
        }

        DrawResponse resp = gson.fromJson(json, DrawResponse.class); // parse json

        if (resp.cards == null) { // if no cards were drawn
            return result;
        }

        for (CardJSON cj : resp.cards) { // convert CardJSON to Card
            int blackjackValue = convertRankToValue(cj.value); // convert to blackjack value
            Card card = new Card(cj.code, cj.value, cj.suit, blackjackValue); // create Card object
            result.add(card);
        }

        return result;
    }

    public void reshuffle(String deckId, boolean remainingOnly) { // reshuffles the deck
        String url = baseUrl + "/" + deckId + "/shuffle/?remaining=" + remainingOnly; // api url
        String json = callAPI(url); // call api
    }
    
    private int convertRankToValue(String rank) { // helper to convert rank to blackjack value
        if (rank.equals("ACE")) { // ace is 11
            return 11;
        } else if (rank.equals("KING") || rank.equals("QUEEN") || rank.equals("JACK")) {
            return 10;
        } else {
            try {
                return Integer.parseInt(rank); // numeric cards
            }
        }
    }
}