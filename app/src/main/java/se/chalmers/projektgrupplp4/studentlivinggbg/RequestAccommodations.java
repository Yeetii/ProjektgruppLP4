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

import se.chalmers.projektgrupplp4.studentlivinggbg.Controller.MainController;

/**
 * Created by PG on 17/04/2017.
 */

public class RequestAccommodations extends AsyncTask<String, Integer, Void> {
    private Context context = MainController.applicationContext;
    private boolean isDone = false;
    private boolean sendSGS;


    public RequestAccommodations (boolean isSGS) {
        this.sendSGS = isSGS;
    }


    @Override
    protected Void doInBackground(String[] params) {
        try {
            if (sendSGS) {
                sendPost();
            } else {
                sendGet();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isDone() {
        return isDone;
    }

    private void sendRequest(HttpsURLConnection con, String fileName) throws Exception {
        InputStream _is;
        if (con.getResponseCode() < HttpsURLConnection.HTTP_BAD_REQUEST) {
            _is = con.getInputStream();
            System.out.println("Success!");
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

        FileOutputStream outputStream;
        try {
            outputStream = context.openFileOutput(fileName, context.MODE_PRIVATE);
            if (sendSGS) {
                outputStream.write(response.toString().getBytes("UTF-8"));
            } else {
                //Chalmersstudentbostäder uses horrible format, reformat and then save.
                outputStream.write(ChalmersAdapter.getFormattedBytes(response));

            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            isDone = true;
        }
    }

    public void sendGet() throws Exception {
        String fileName = "ChalmersData";
        String url = "https://www.chalmersstudentbostader.se/widgets/?actionId=&omraden=&objektTyper=&maxAntalDagarPublicerad=&callback=jQuery172027556341802877515_1492691004676&widgets%5B%5D=alert&widgets%5B%5D=objektfilter%40lagenheter&widgets%5B%5D=objektsummering%40lagenheter&widgets%5B%5D=objektsortering&widgets%5B%5D=objektlistabilder%40lagenheter&objektfilter%40lagenheter.maxyta=130&objektfilter%40lagenheter.maxhyra=14000";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        sendRequest(con, fileName);
    }


    // HTTPS POST request
    public  void sendPost() throws Exception {
        String fileName = "SGSData";
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

        sendRequest(con, fileName);
    }

}
