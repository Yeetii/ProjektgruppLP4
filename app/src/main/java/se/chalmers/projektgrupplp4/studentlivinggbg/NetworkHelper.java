package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by PG on 10/05/2017.
 */

public class NetworkHelper {
    public static StringBuffer sendGetRequest(String url) throws Exception {
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        return sendRequest(con);
    }

    public static StringBuffer sendPostRequestToSGS() throws Exception {
        String url = "https://marknad.sgsstudentbostader.se/API/Service/SearchServiceHandler.ashx";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest headers
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("X-Momentum-API-KEY", "u15fJ8yRMCIu////+aEYR7+XJwj1hiE9gIXfoo/eje4=");
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36");
        con.setRequestProperty("Accept", "application/json,text/*");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.8,sv;q=0.6,und;q=0.4");
        con.setRequestProperty("Origin", "https://marknad.sgsstudentbostader.se");
        con.setRequestProperty("Referer", "https://marknad.sgsstudentbostader.se/");
        con.setRequestProperty("X-Requested-With", "XMLHttpRequest");

        String urlParameters = "Parm1=%7B%22CompanyNo%22%3A-1%2C%22SyndicateNo%22%3A1%2C%22ObjectMainGroupNo%22%3A1%2C%22Advertisements%22%3A%5B%7B%22No%22%3A-1%7D%5D%2C%22RentLimit%22%3A%7B%22Min%22%3A0%2C%22Max%22%3A15000%7D%2C%22AreaLimit%22%3A%7B%22Min%22%3A0%2C%22Max%22%3A150%7D%2C%22ApplySearchFilter%22%3Atrue%2C%22Page%22%3A1%2C%22Take%22%3A10%2C%22SortOrder%22%3A%22SeekAreaDescription+asc%2C+street+asc%22%2C%22ReturnParameters%22%3A%5B%22ObjectNo%22%2C%22FirstEstateImageUrl%22%2C%22Street%22%2C%22SeekAreaDescription%22%2C%22PlaceName%22%2C%22ObjectSubDescription%22%2C%22ObjectArea%22%2C%22RentPerMonth%22%2C%22MarketPlaceDescription%22%2C%22CountInterest%22%2C%22FirstInfoTextShort%22%2C%22FirstInfoText%22%2C%22EndPeriodMP%22%2C%22FreeFrom%22%2C%22SeekAreaUrl%22%2C%22Latitude%22%2C%22Longitude%22%2C%22BoardNo%22%5D%7D&CallbackMethod=PostObjectSearch&CallbackParmCount=1&__WWEVENTCALLBACK";
        byte[] reqStr = urlParameters.getBytes("UTF-8");

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.write(reqStr);
        wr.flush();
        wr.close();

        return sendRequest(con);
    }

    private static StringBuffer sendRequest(HttpsURLConnection con) throws Exception {
        InputStream _is;
        if (con.getResponseCode() < HttpsURLConnection.HTTP_BAD_REQUEST) {
            _is = con.getInputStream();
        } else {
            _is = con.getErrorStream();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(_is));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response;
    }


}
