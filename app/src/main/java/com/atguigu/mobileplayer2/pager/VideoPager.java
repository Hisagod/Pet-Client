package com.atguigu.mobileplayer2.pager;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aib.player.R;
import com.atguigu.mobileplayer2.activity.SystemVideoPlayer;
import com.atguigu.mobileplayer2.adapter.VideoPagerAdapter;
import com.atguigu.mobileplayer2.base.BasePager;
import com.atguigu.mobileplayer2.domain.MediaItem;
import com.atguigu.mobileplayer2.utils.LogUtil;

import java.util.ArrayList;


///**
// * 本地视频
// */
//public class VideoPager extends BasePager {
//
//}
