package tn.esprit.legacy.monivulation.Helpers;

import android.app.Activity;
import android.view.WindowManager;

import com.vlstr.blurdialog.BlurDialog;


public class DialogHelper {

    public void blurDialogShow (Activity callingActivity, BlurDialog blurDialog , String message){
        callingActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        blurDialog.create( callingActivity. getWindow().getDecorView(), 6);
        blurDialog.setTitle(message);
        blurDialog.show();

    }

    public void blurDialogHide (Activity callingActivity, BlurDialog blurDialog){

        blurDialog.hide();
        callingActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

}
