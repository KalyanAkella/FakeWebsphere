package com.morrisons.handlers;

import com.morrisons.handlers.data.Content;
import com.morrisons.handlers.data.ESpot;
import com.morrisons.handlers.data.ESpotResponse;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;

public class ESpotHandler implements WCSHandler {

  private static final Map<String, ESpot> espotsMap;

  static {
    espotsMap = new HashMap<String, ESpot>();
    espotsMap.put("home_large_espot1", homePageESpot1());
    espotsMap.put("home_small_espot1", homePageESpot2());
    espotsMap.put("home_small_espot2", homePageESpot3());
    espotsMap.put("home_small_espot3", homePageESpot4());
    espotsMap.put("home_small_espot4", homePageESpot5());
    espotsMap.put("special_offers_large_espot1", null);
    espotsMap.put("mixed_cases_large_espot1", null);
    espotsMap.put("browse_all_wines_large_espot1", null);
  }

  private static ESpot homePageESpot5() {
    return buildESpot("<img src=\"img/promotion/taste_test_lw.png\" alt=\"Taste Test\">", "/MorrisonsCellarWeb/taste-test/", "home_small_espot4");
  }

  private static ESpot homePageESpot4() {
    return buildESpot("<img src=\"img/promotion/science_behind_lw.png\" alt=\"Science Behind\">", "http://edge.liveclicker.net/videos/1147/1016277679_1_liveclicker.mp4", "home_small_espot3");
  }

  private static ESpot homePageESpot3() {
    return buildESpot("<img src=\"img/promotion/mixed_cases_lw.png\" alt=\"Mixed Cases\">", "/MorrisonsCellarWeb/mixed-cases/", "home_small_espot2");
  }

  private static ESpot homePageESpot2() {
    return buildESpot("<img src=\"img/promotion/great_offers_lw.png\" alt=\"Great Offers\">", "/MorrisonsCellarWeb/all-offers/", "home_small_espot1");
  }

  private static ESpot homePageESpot1() {
    return buildESpot("<img src=\"http://m.morrisonscellar.com/img/homeCarousel/Mobilel_taste_test.png\">", "/MorrisonsCellarWeb/taste-test/", "home_large_espot1");
  }

  private static ESpot buildESpot(String contentText, String contentUrl, String eSpotName) {
    Content content = buildContent(contentUrl, contentText);
    return buildESpotWithContents(eSpotName, content);
  }

  private static ESpot buildESpotWithContents(String eSpotName, Content... content) {
    ESpot eSpot = new ESpot();
    eSpot.espotUid = eSpotName;
    eSpot.espotName = eSpotName;
    eSpot.content = asList(content);
    return eSpot;
  }

  private static Content buildContent(String contentUrl, String... contentText) {
    Content content = new Content();
    content.contentText = asList(contentText);
    content.url = contentUrl;
    return content;
  }

  @Override
  public Response handle(Request request) {
    String spotName = request.get("spotName");
    if (espotsMap.containsKey(spotName)) return buildESpotResponse(espotsMap.get(spotName));
    Content urlContent = buildContent("http://www.google.co.uk/nexus/4/", "This is Nexus 4");
    Content imageContent = buildContent("https://play.google.com/store/devices/details?id=nexus_4_8gb", "<img src=\"https://lh5.ggpht.com/4QMRvNtEh_N2oJ9B4sCBxjvGb_4L75H-6ICIOsCtn2jZ0wfgmuqd83CHKAjFqkDpfJV6=w180\" style=\"padding-left:6px;\"/>");
    return buildESpotResponse(buildESpotWithContents(spotName, urlContent, imageContent));
  }

  private Response buildESpotResponse(ESpot eSpot) {
    ESpotResponse eSpotResponse = new ESpotResponse();
    eSpotResponse.resultsFound = 1;
    eSpotResponse.maxResults = 1;
    eSpotResponse.eSpot = eSpot;
    return new Response(eSpotResponse);
  }
}
