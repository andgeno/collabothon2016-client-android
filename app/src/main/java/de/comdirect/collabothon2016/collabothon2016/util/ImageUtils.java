package de.comdirect.collabothon2016.collabothon2016.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

import de.comdirect.collabothon2016.collabothon2016.R;

public class ImageUtils {

    public static Drawable getGroupAvatar(Context context, int groupId) {
        Drawable drawable = null;
        switch (groupId) {
            case 1:
                drawable = context.getResources().getDrawable(R.drawable.industry_technology);
                break;
            case 2:
                drawable = context.getResources().getDrawable(R.drawable.industry_renweable);
                break;
            case 3:
                drawable = context.getResources().getDrawable(R.drawable.industry_fashion);
                break;
            default:
                throw new IllegalStateException("Unknown group ID");
        }
        return drawable;
    }

    public static Drawable getUserAvatar(Context context, int userId) {
        Drawable drawable = null;
        switch (userId) {
            case 1:
                drawable = context.getResources().getDrawable(R.drawable.spy_icon);
                break;
            case 2:
                drawable = context.getResources().getDrawable(R.drawable.superman_icon);
                break;
            case 3:
                drawable = context.getResources().getDrawable(R.drawable.doctor_icon);
                break;
            default:
                throw new IllegalStateException("Unknown user ID");
        }
        return drawable;
    }

    public static Drawable getUserAvatarWithCrest(Context context, int userId) {
        Drawable drawable = null;
        switch (userId) {
            case 1:
                drawable = context.getResources().getDrawable(R.drawable.walter_gold);
                break;
            case 2:
                drawable = context.getResources().getDrawable(R.drawable.man_silver);
                break;
            case 3:
                drawable = context.getResources().getDrawable(R.drawable.woman_bronze);
                break;
            case 4:
                drawable = context.getResources().getDrawable(R.drawable.cresld_gold);
                break;
            case 5:
                drawable = context.getResources().getDrawable(R.drawable.fry_silver);
                break;
            case 6:
                drawable = context.getResources().getDrawable(R.drawable.nibbler_bronze);
                break;
            case 7:
                drawable = context.getResources().getDrawable(R.drawable.heisenberg_gold);
                break;
            case 8:
                drawable = context.getResources().getDrawable(R.drawable.robot_silver);
                break;
            case 9:
                drawable = context.getResources().getDrawable(R.drawable.stormtrooper_bronze);
                break;
            default:
                throw new IllegalStateException("Unknown user ID");
        }
        return drawable;
    }

    public static Drawable getCrestByRank(Context context, int rank) {
        Drawable drawable = null;
        switch (rank) {
            case 1:
                drawable = context.getResources().getDrawable(R.drawable.crest_gold);
                break;
            case 2:
                drawable = context.getResources().getDrawable(R.drawable.crest_silver);
                break;
            case 3:
                drawable = context.getResources().getDrawable(R.drawable.crest_bronze);
                break;
            default:
                throw new IllegalStateException("Unknown rank");
        }
        return drawable;
    }

    public static Drawable getStockByIsin(Context context, String isin) {
        Drawable drawable = null;
        switch (isin) {
            case "US30303M1027": // facebook
                drawable = context.getResources().getDrawable(R.drawable.stock_fb);
                break;
            case "US64110L1061": // netflix
                drawable = context.getResources().getDrawable(R.drawable.stock_netflix);
                break;
            case "US88160R1014": // tesla
                drawable = context.getResources().getDrawable(R.drawable.stock_tesla);
                break;
            case "US38268T1034": // go pro
                drawable = context.getResources().getDrawable(R.drawable.stock_gopro);
                break;
            case "US02079K3059": // google
                drawable = context.getResources().getDrawable(R.drawable.stock_google);
                break;
            case "US0378331005": // apple
                drawable = context.getResources().getDrawable(R.drawable.stock_apple);
                break;
            default:
                throw new IllegalStateException("Unknown stock");
        }
        return drawable;
    }


}
