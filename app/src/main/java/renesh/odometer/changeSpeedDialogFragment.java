package renesh.odometer;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by ReneshN on 2016/11/23.
 */

public class changeSpeedDialogFragment extends DialogFragment {

    ChangeSpeedListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder  builder;
        builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.speed_dialog_msg).setTitle(R.string.speed_dialog_heading);
        builder.setView(R.layout.speed_dialog);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mListener.onOkClicked(changeSpeedDialogFragment.this);
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mListener.onCancelClicked(changeSpeedDialogFragment.this);
            }
        });

        return builder.create();
    }

    public interface ChangeSpeedListener{
         void onOkClicked(DialogFragment dialog);
         void onCancelClicked(DialogFragment dialog);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            mListener = (ChangeSpeedListener) context;
        }
        catch(ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement ChangeSpeedListener");
        }
    }
}
