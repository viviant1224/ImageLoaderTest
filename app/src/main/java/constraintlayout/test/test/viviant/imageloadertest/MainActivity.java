package constraintlayout.test.test.viviant.imageloadertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import constraintlayout.test.test.viviant.imageloadertest.util.ImageLoaderUtil;


public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.user_image);

        ImageLoaderUtil.init(this);

        String url_image = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWsK1HF6hhy/it/u=3266622398,4228444443&fm=116&gp=0.jpg";
        ImageLoaderUtil.displayImage(url_image, imageView,  ImageLoaderUtil.getAvatarDisplayOptions());

    }
}
