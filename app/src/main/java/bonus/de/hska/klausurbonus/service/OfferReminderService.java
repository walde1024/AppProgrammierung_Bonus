package bonus.de.hska.klausurbonus.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import bonus.de.hska.klausurbonus.OfferDetailActivity;
import bonus.de.hska.klausurbonus.R;
import bonus.de.hska.klausurbonus.domain.model.Offer;
import bonus.de.hska.klausurbonus.persistence.contentprovider.OfferPlannerContentProvider;
import bonus.de.hska.klausurbonus.persistence.contract.OfferPlannerContract;
import bonus.de.hska.klausurbonus.view.offer.OfferResources;

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
        Offer offer = readOffer(offerId);

        createAndShowNotification(offer, offerId);
       // playSound();

        stopSelf();
    }

    private void createAndShowNotification(Offer offer, int offerId) {
        Uri notificationRingtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        NotificationCompat.Builder mBuilder =
            new NotificationCompat.Builder(this)
                    .setSmallIcon(OfferResources.getIconIdForOffer(this, offer))
                    .setContentTitle("Klausurbonus")
                    .setAutoCancel(true)
                    .setSound(notificationRingtone)
                    .setContentText(offer.getTitle() + " beginnt um " + offer.getTime() + " Uhr im Raum " + offer.getRoom());


        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, OfferDetailActivity.class);
        resultIntent.putExtra(Offer.ID_KEY, offerId);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(OfferDetailActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = mBuilder.build();
        notification.flags |= Notification.FLAG_INSISTENT;

        // mId allows you to update the notification later on.
        mNotificationManager.notify(1, notification);
    }

    private Offer readOffer(int offerId) {
        Cursor cursor = getContentResolver().query(Uri.parse(OfferPlannerContentProvider.OFFERS_URI + "/" + offerId), null, null, null, null);
        cursor.moveToFirst();

        String title = cursor.getString(cursor.getColumnIndex(OfferPlannerContract.OfferEntry.COLUMN_NAME_TITLE));
        String category = cursor.getString(cursor.getColumnIndex(OfferPlannerContract.OfferEntry.COLUMN_NAME_CATEGORY));
        String room = cursor.getString(cursor.getColumnIndex(OfferPlannerContract.OfferEntry.COLUMN_NAME_ROOM));
        String time = cursor.getString(cursor.getColumnIndex(OfferPlannerContract.OfferEntry.COLUMN_NAME_TIME));
        String teacher = cursor.getString(cursor.getColumnIndex(OfferPlannerContract.OfferEntry.COLUMN_NAME_TEACHER));

        return new Offer(title, category, time, room, teacher);
    }

    private void playSound() {
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
