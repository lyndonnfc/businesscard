package com.nfc.lyndon.businesscard.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.app.Constants;
import com.nfc.lyndon.businesscard.util.AppUtils;
import com.nfc.lyndon.businesscard.util.BitmapUtils;
import com.nfc.lyndon.businesscard.widget.CameraPreview;
import com.nfc.lyndon.businesscard.widget.FocusImageView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 拍照界面
 */
public class CameraActivity extends Activity implements View.OnClickListener, Camera.AutoFocusCallback, CameraPreview.OnTouchListener {

    public final static int REQUEST_CODE = 0X13;
    public final static int RESULT_CODE = 0X14;

    public static void openCertificateCamera(Fragment fragment) {
        Intent intent = new Intent(fragment.getContext(), CameraActivity.class);
        fragment.startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * @return 结果文件路径
     */
    public static String getResult(Intent data) {
        if (data != null) {
            return data.getStringExtra("result");
        }
        return "";
    }

    private CameraPreview cameraPreview;
    private FocusImageView focusImageView;
    private View containerView;
    private ImageView cropView;
    private ImageView flashImageView;
    private View optionView;
    private View resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_camera);
        cameraPreview = findViewById(R.id.camera_surface);
        //获取屏幕最小边，设置为cameraPreview较窄的一边
        float screenMinSize = Math.min(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);

        containerView = findViewById(R.id.camera_crop_container);
        cropView = findViewById(R.id.camera_crop);

        float height = (int) (screenMinSize * 0.75);
        float width = (int) (height * 75.0f / 47.0f);
        LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams((int) width, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams cropParams = new LinearLayout.LayoutParams((int) width, (int) height);
        containerView.setLayoutParams(containerParams);
        cropView.setLayoutParams(cropParams);
        cropView.setImageResource(R.drawable.camera_company_landscape);

        flashImageView = findViewById(R.id.camera_flash);
        optionView = findViewById(R.id.camera_option);
        resultView = findViewById(R.id.camera_result);
        focusImageView = findViewById(R.id.focusImageView);
        cameraPreview.setOnClickListener(this);
        findViewById(R.id.camera_close).setOnClickListener(this);
        findViewById(R.id.camera_take).setOnClickListener(this);
        flashImageView.setOnClickListener(this);
        findViewById(R.id.camera_alum).setOnClickListener(this);
        findViewById(R.id.camera_result_ok).setOnClickListener(this);
        findViewById(R.id.camera_result_cancel).setOnClickListener(this);

        cameraPreview.setListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.camera_close) {
            finish();
        } else if (id == R.id.camera_take) {
            takePhoto();
        } else if (id == R.id.camera_flash) {
            boolean isFlashOn = cameraPreview.switchFlashLight();
            flashImageView.setImageResource(isFlashOn ? R.drawable.camera_flash_on : R.drawable.camera_flash_off);
        } else if (id == R.id.camera_result_ok) {
            goBack();
        } else if (id == R.id.camera_result_cancel) {
            optionView.setVisibility(View.VISIBLE);
            cameraPreview.setEnabled(true);
            cameraPreview.setListener(this);
            resultView.setVisibility(View.GONE);
            cameraPreview.startPreview();
        } else if (id == R.id.camera_alum) {
            AppUtils.openAlbumPage(this);
        }
    }

    private void takePhoto() {
        optionView.setVisibility(View.GONE);
        cameraPreview.setEnabled(false);
        cameraPreview.takePhoto(new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(final byte[] data, Camera camera) {
                //子线程处理图片，防止ANR
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            File originalFile = getOriginalFile();
                            FileOutputStream originalFileOutputStream = new FileOutputStream(originalFile);
                            originalFileOutputStream.write(data);
                            originalFileOutputStream.close();

                            Bitmap bitmap = BitmapFactory.decodeFile(originalFile.getPath());

                            //计算裁剪位置
                            float left, top, right, bottom;

                            left = ((float) containerView.getLeft() - (float) cameraPreview.getLeft()) / (float) cameraPreview.getWidth();
                            top = (float) cropView.getTop() / (float) cameraPreview.getHeight();
                            right = (float) containerView.getRight() / (float) cameraPreview.getWidth();
                            bottom = (float) cropView.getBottom() / (float) cameraPreview.getHeight();
                            //裁剪及保存到文件
                            Bitmap cropBitmap = Bitmap.createBitmap(bitmap,
                                    (int) (left * (float) bitmap.getWidth()),
                                    (int) (top * (float) bitmap.getHeight()),
                                    (int) ((right - left) * (float) bitmap.getWidth()),
                                    (int) ((bottom - top) * (float) bitmap.getHeight()));

                            final File cropFile = getCropFile();
                            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(cropFile));
                            cropBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                            bos.flush();
                            bos.close();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    resultView.setVisibility(View.VISIBLE);
                                    cameraPreview.stopPreView();
                                    cameraPreview.setListener(null);
                                }
                            });
                            return;
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                optionView.setVisibility(View.VISIBLE);
                                cameraPreview.setEnabled(true);
                            }
                        });
                    }
                }).start();

            }
        });
    }

    /**
     * @return 拍摄图片原始文件
     */
    private File getOriginalFile() {
        return new File(getExternalCacheDir(), "card.jpg");
    }

    /**
     * @return 拍摄图片裁剪文件
     */
    private File getCropFile() {
        return new File(getExternalCacheDir(), "crop.jpg");
    }

    /**
     * 点击对勾，使用拍照结果，返回对应图片路径
     */
    private void goBack() {
        Intent intent = new Intent();
        intent.putExtra("path", getCropFile().getPath());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onAutoFocus(boolean success, Camera camera) {
        if (success) {
            focusImageView.onFocusSuccess();
        }
    }

    @Override
    public void focus(float x, float y) {
        Point point = new Point((int) x, (int) y);
        focusImageView.startFocus(point);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == Constants.ALBUM_REQUEST_CODE) {
            if (data != null && data.getData() != null) {
                final Uri uri = data.getData();
                final Bitmap bitmap = BitmapFactory.decodeFile(BitmapUtils.getImageAbsolutePath(this, uri));
                BitmapUtils.compressImage(bitmap, getCropFile().getAbsolutePath());
                Intent intent = new Intent();
                intent.putExtra("path", getCropFile().getPath());
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }
}
