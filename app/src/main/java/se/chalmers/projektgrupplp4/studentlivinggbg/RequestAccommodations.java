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

import se.chalmers.projektgrupplp4.studentlivinggbg.controller.MainController;

/**
 * Created by PG on 17/04/2017.
 */

public class RequestAccommodations extends AsyncTask<String, Integer, Void> {
    private Context context;
    private boolean isDone = false;
    private boolean sendSGS;


    public RequestAccommodations (boolean isSGS, Context context) {
        this.sendSGS = isSGS;
        this.context = context;
    }


    @Override
    protected Void doInBackground(String[] params) {
        StringBuffer response;
        String fileName;
        try {
            if (sendSGS) {
                fileName = "SGSData";
                response = sendPost();
            } else {
                fileName = "ChalmersData";
                response = sendGet();
            }
            writeToDisc(response, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            isDone = true;
        }
        return null;
    }

    public boolean isDone() {
        return isDone;
    }

    private void writeToDisc(StringBuffer response, String fileName) throws Exception {
        FileOutputStream outputStream;
        try {
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            if (sendSGS) {
                outputStream.write(response.toString().getBytes("UTF-8"));
            } else {
                //Chalmersstudentbostäder uses horrible format, reformat and then save.
                outputStream.write(ChalmersAdapter.getFormattedBytes(response));
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private StringBuffer sendGet() throws Exception {
        String url = "https://www.chalmersstudentbostader.se/widgets/?actionId=&omraden=&objektTyper=&maxAntalDagarPublicerad=&callback=jQuery172027556341802877515_1492691004676&widgets%5B%5D=alert&widgets%5B%5D=objektfilter%40lagenheter&widgets%5B%5D=objektsummering%40lagenheter&widgets%5B%5D=objektsortering&widgets%5B%5D=objektlistabilder%40lagenheter&objektfilter%40lagenheter.maxyta=130&objektfilter%40lagenheter.maxhyra=14000";
        return NetworkHelper.sendGetRequest(url);
    }


    // HTTPS POST request
    private StringBuffer sendPost() throws Exception {
        return NetworkHelper.sendPostRequestToSGS();
    }

}
