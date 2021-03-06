package se.chalmers.projektgrupplp4.studentlivinggbg.service;

import android.content.Context;
import android.os.AsyncTask;


import java.io.FileOutputStream;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.ChalmersAdapter;

/**
 * @author Peter Gärdenäs
 * Used by: DatabaseUpdater
 * Uses: RequestSender, Observer, ChalmersAdapter
 * Responsibility: Calls RequestSender to fetch data from server and then writes it to disc.
 */

public class RequestAccommodations extends AsyncTask<String, Integer, Void> {
    private final Context context;
    private boolean sendSGS;
    private final Observer observer;


    public RequestAccommodations (boolean isSGS, Context context, Observer observer) {
        this.sendSGS = isSGS;
        this.context = context;
        this.observer = observer;
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
            observer.update(sendSGS ? "SGS" : "Chalmers");
        }
        return null;
    }

    private void writeToDisc(StringBuffer response, String fileName) {
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
        return RequestSender.sendGetRequest(url);
    }


    // HTTPS POST request
    private StringBuffer sendPost() throws Exception {
        return RequestSender.sendPostRequestToSGS();
    }

}
