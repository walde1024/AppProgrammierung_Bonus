package bonus.de.hska.klausurbonus.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import bonus.de.hska.klausurbonus.OfferDetailActivity;
import bonus.de.hska.klausurbonus.domain.model.Offer;

/**
 * Created by Walde on 28.12.16.
 */
public class OfferReminderService extends IntentService {

    private static final String TIME = "time";

    public OfferReminderService() {
        super("Offer Reminder Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int offerId = intent.getExtras().getInt(Offer.ID_KEY);

        Intent offerIntent = new Intent(this, OfferDetailActivity.class);
        offerIntent.putExtra(Offer.ID_KEY, offerId);
        offerIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        playSound();

        startActivity(offerIntent);
        
        stopSelf();
    }

    private void playSound() {
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
