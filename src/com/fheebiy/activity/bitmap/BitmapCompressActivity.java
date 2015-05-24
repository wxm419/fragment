package com.fheebiy.activity.bitmap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.fheebiy.R;
import com.fheebiy.util.BitmapUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouwenbo on 15/5/7.
 */
public class BitmapCompressActivity extends Activity implements View.OnClickListener {

    private String TAG = "BitmapCompressActivity";

    private ImageView img1;

    private ImageView img2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.btimap_compress);


        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);

        img1.setOnClickListener(this);
    }


    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }


    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 480, 800);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }


    private File persistImage(Bitmap bitmap, String name) {
        try {
            File f = new File(getExternalCacheDir().getPath(), name);
            f.createNewFile();
            OutputStream os;
            os = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, os);
            os.flush();
            os.close();
            return f;
        } catch (Exception e) {
            Log.e(TAG, "Error writing bitmap", e);
        }
        return null;
    }

    @Override
    public void onClick(View view) {
        //new BitmapTask().execute();
        List<String> picPath = new ArrayList<>();
        picPath.add("/storage/emulated/0/Pictures/gg/jj.jpg");
        new BitmapCompressTask().execute(picPath);
    }


    class BitmapCompressTask extends AsyncTask<List<String>, Integer, List<File>> {

        @Override
        protected List<File> doInBackground(List<String>... params) {
            List<String> path = params[0];
            List<File> fileList = new ArrayList<File>();
            for (String str : path) {
                Bitmap bitmap = getSmallBitmap(str);
                File file = persistImage(bitmap, "bimg" + System.currentTimeMillis() + (int) (Math.random() * 100)+".jpg");
                fileList.add(file);
            }
            return fileList;
        }

        @Override
        protected void onPostExecute(List<File> files) {
            super.onPostExecute(files);
            //imgeList.addAll(files) ;
        }
    }

    class BitmapTask extends AsyncTask<Void,Integer, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.jj);
            Bitmap newBitmap = BitmapUtil.comp(bmp);
            img2.setImageBitmap(newBitmap);
            return null;
        }
    }

}