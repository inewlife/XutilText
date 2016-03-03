package com.inewlife.xutiltext.fragment;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.inewlife.xutiltext.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.HashMap;

/**
 * Created by inewlife on 2016/3/2.
 */
public class BitmapFragment extends Fragment {
    public static BitmapUtils bitmapUtils;
   /* private String[] imgSites = {
            "http://image.baidu.com/",
            "http://www.22mm.cc/",
            "http://www.moko.cc/",
            "http://eladies.sina.com.cn/photo/",
            "http://www.youzi4.com/"
    };*/
    //private HashMap<String, Integer> temp = new HashMap<String, Integer>();
   @ViewInject(R.id.img_view)
   ImageView imgview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bitmap_fragment,container,false);
        ViewUtils.inject(this,view);

        bitmapUtils = new BitmapUtils(this.getActivity());
        bitmapUtils.configDefaultLoadingImage(R.drawable.ic_launcher);
        bitmapUtils.configDefaultLoadFailedImage(R.drawable.bitmap);
        bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
        bitmapUtils.configDefaultBitmapMaxSize(BitmapCommonUtils.getScreenSize(getActivity()).scaleDown(3));
        bitmapUtils.display(imgview,"http://bbs.lidroid.com/static/image/common/logo.png");

        return view;
    }


}
