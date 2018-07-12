package vn.tima.timainspection.Service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import vn.tima.timainspection.R;
import vn.tima.timainspection.View.Main.MainActivity;

public class FirebaseMsgService extends FirebaseMessagingService {
//    @Override
//    public void onMessageReceived(RemoteMessage rm) {
//        // Hiển thị thông báo
//        createNotification(rm.getNotification().getBody(), rm.getData().get("title"));
//    }
//
//    // Xử lý hiển thị thông báo
//    private void createNotification(String msg, String title) {
//        Intent i = new Intent(this, MainActivity.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pi = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT);
//
//        Uri u = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder b = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle(title)
//                .setContentText(msg)
//                .setAutoCancel(true)
//                .setSound(u)
//                .setContentIntent(pi);
//
//        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        nm.notify(0, b.build());
//    }

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            Map<String, String> data = remoteMessage.getData();
            String content = data.get("body");
            String loanCreditId = data.get("LoanCreditId");
            String TypeNotify = data.get("TypeNotify");
            Log.e(TAG, "LoanCreditId = " + loanCreditId);
            sendNotification(content, loanCreditId, TypeNotify);
        }

        if (remoteMessage.getNotification() != null) {
            String body = remoteMessage.getNotification().getBody();
            sendNotification(body, null, null);
        }
    }

    private void sendNotification(String messageBody, String loanCreditId, String TypeNotify) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        if (loanCreditId != null && TypeNotify != null) {
            Bundle bundle = new Bundle();
            bundle.putString("loanCreditId", loanCreditId);
            bundle.putString("TypeNotify", TypeNotify);
            intent.putExtras(bundle);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Thẩm định")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify((int) System.currentTimeMillis() /* ID of notification */, notificationBuilder.build());
    }
}
