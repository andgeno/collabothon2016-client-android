package de.comdirect.collabothon2016.collabothon2016.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

import de.comdirect.collabothon2016.collabothon2016.R;

public class ImageUtils {

    public static Drawable getGroupAvatar(Context context, int groupId) {
        Drawable drawable = null;
        switch (groupId) {
            case 1:
                drawable = context.getResources().getDrawable(R.drawable.Flower_power_01_512);
                break;
            case 2:
                drawable = context.getResources().getDrawable(R.drawable.chemistry);
                break;
            case 3:
                drawable = context.getResources().getDrawable(R.drawable.farming_food_green_grow_leaf_nature_organic_plant_icon_3);
                break;
            default:
                throw new IllegalStateException("Unknown group ID");
        }
        return drawable;
    }

    public static Drawable getUserAvatar(Context context, int groupId) {
        Drawable drawable = null;
        switch (groupId) {
            case 1:
                drawable = context.getResources().getDrawable(R.drawable.heisenberg_1x);
                break;
            case 2:
                drawable = context.getResources().getDrawable(R.drawable.doctor_icon_icons);
                break;
            case 3:
                drawable = context.getResources().getDrawable(R.drawable.superman_icon_icons);
                break;
            default:
                throw new IllegalStateException("Unknown user ID");
        }
        return drawable;
    }

    public static Drawable getUserAvatarWithCrest(Context context, int groupId) {
        Drawable drawable = null;
        switch (groupId) {
            case 1:
                drawable = context.getResources().getDrawable(R.drawable.ic_avatar_dummy);
                break;
            case 2:
                drawable = context.getResources().getDrawable(R.drawable.ic_avatar_dummy);
                break;
            case 3:
                drawable = context.getResources().getDrawable(R.drawable.ic_avatar_dummy);
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

}
