package bonus.de.hska.klausurbonus.view.persistence.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import bonus.de.hska.klausurbonus.view.persistence.contract.OfferPlannerContract;

/**
 * Created by Walde on 27.12.16.
 */
public class OfferPlannerContentProvider extends ContentProvider {

    public static final String PROVIDER_NAME = "bonus.de.hska.klausurbonus.provider";

    public static final String OFFERS_URI = "content://" + PROVIDER_NAME + "/" + OfferPlannerContract.OfferEntry.TABLE_NAME;

    private OfferPlannerDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new OfferPlannerDbHelper(getContext());

        return true;
    }

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI("bonus.de.hska.klausurbonus.provider", OfferPlannerContract.OfferEntry.TABLE_NAME, 1);
        uriMatcher.addURI("bonus.de.hska.klausurbonus.provider", OfferPlannerContract.OfferEntry.TABLE_NAME + "/#", 2);
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        SQLiteDatabase sqLiteDatabase = null;

        switch (uriMatcher.match(uri)) {
            case 1:
                sqLiteDatabase = mDbHelper.getReadableDatabase();
                cursor = sqLiteDatabase.query(OfferPlannerContract.OfferEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                //sqLiteDatabase.close();
                break;

            case 2:
                selection = OfferPlannerContract.OfferEntry._ID + " = ?";
                selectionArgs = new String[] {uri.getLastPathSegment()};

                sqLiteDatabase = mDbHelper.getReadableDatabase();
                cursor = sqLiteDatabase.query(OfferPlannerContract.OfferEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
                //sqLiteDatabase.close();
                break;

            default:
                throw new IllegalArgumentException("Wrong URI");
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case 1:
                return "vnd.android.cursor.dir/vnd.bonus.de.hska.klausurbonus.offer";
            case 2:
                return "vnd.android.cursor.item/vnd.bonus.de.hska.klausurbonus.offers";
            default:
                throw new IllegalArgumentException("Wrong URI");
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        return 0;
    }
}
