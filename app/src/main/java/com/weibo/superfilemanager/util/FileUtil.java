package com.weibo.superfilemanager.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import com.weibo.superfilemanager.mvp.model.ImageInfo;
import com.weibo.superfilemanager.mvp.model.Mp3Info;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by weibo on 17-5-14.
 */

public final class FileUtil {
  //查询所有歌曲信息
  public static ArrayList<Mp3Info> getMp3InfoList(Context context) {
    Cursor cursor = context.getContentResolver()
        .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null,
            MediaStore.Audio.Media.DURATION + ">=" + "180000", null,
            MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
    ArrayList<Mp3Info> list = new ArrayList<>();
    int id = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID);
    int album = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM);
    int albumId = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID);
    int artList = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST);
    int duration = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION);
    int size = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE);
    int title = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE);
    int url = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
    int display = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
    while (cursor.moveToNext()) {
      Mp3Info mp3Info = new Mp3Info();
      mp3Info.setId(cursor.getLong(id));
      mp3Info.setAlbum(cursor.getString(album));
      mp3Info.setAlbumId(cursor.getInt(albumId));
      mp3Info.setArtist(cursor.getString(artList));
      mp3Info.setDuration(cursor.getInt(duration));
      mp3Info.setSize(cursor.getLong(size));
      mp3Info.setTitle(cursor.getString(title));
      mp3Info.setUrl(cursor.getString(url));
      mp3Info.setDisplay(cursor.getString(display));
      list.add(mp3Info);
    }
    cursor.close();
    return list;
  }

  /*
   * 根据全部图片
   */
  public static List<ImageInfo> getImageList(Context context) {
    // 指定要查询的uri资源
    Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    // 获取ContentResolver
    ContentResolver contentResolver = context.getApplicationContext().getContentResolver();
    // 查询的字段
    String[] projection = {
        MediaStore.Images.Media._ID, MediaStore.Images.Media.DISPLAY_NAME,
        MediaStore.Images.Media.DATA, MediaStore.Images.Media.SIZE,
        //MediaStore.Images.Media.CONTENT_TYPE,
        //MediaStore.Images.Media._COUNT,
        //MediaStore.Images.Media.DESCRIPTION,
        //MediaStore.Images.Media.WIDTH,
        //MediaStore.Images.Media.TITLE,
        MediaStore.Images.Media.HEIGHT
    };
    // 条件
    String selection = MediaStore.Images.Media.MIME_TYPE + "=?";
    // 条件值(這裡的参数不是图片的格式，而是标准，所有不要改动)
    String[] selectionArgs = { "image/jpeg", "image/png", "image/gif" };
    // 排序
    String sortOrder = MediaStore.Images.Media.DATE_MODIFIED + " desc";
    // 查询sd卡上的图片
    Cursor cursor = contentResolver.query(uri, projection, selection, selectionArgs, sortOrder);
    List<ImageInfo> imageIfos = null;
    ImageInfo imageIfo = null;

    String id = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
    String displayName =
        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));
    Long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE) / 1024);
    String data = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));

    if (cursor != null) {
      imageIfos = new ArrayList<>();
      cursor.moveToFirst();
      while (cursor.moveToNext()) {
        imageIfo = new ImageInfo();
        // 获得图片的id
        imageIfo.setId(id);
        // 获得图片显示的名称
        imageIfo.setDisplay_name(displayName);
        // 获得图片的信息
        imageIfo.setSize(size);
        // 获得图片所在的路径(可以使用路径构建URI)
        imageIfo.setData(data);
                /*imageIfo.setTitle(cursor.getString(
                        cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE)));
                imageIfo.setContent_type(cursor.getString(
                        cursor.getColumnIndexOrThrow(MediaStore.Images.Media.CONTENT_TYPE)));
                imageIfo.setCount(cursor.getString(
                        cursor.getColumnIndexOrThrow(MediaStore.Images.Media._COUNT)));
                imageIfo.setDescription(
                        cursor.getString(cursor.getColumnIndexOrThrow(
                                MediaStore.Images.Media.DESCRIPTION)));
                imageIfo.setWidth(
                        cursor.getString(cursor.getColumnIndexOrThrow
                                (MediaStore.Images.Media.WIDTH)));
                imageIfo.setHeight(
                        cursor.getString(cursor.getColumnIndexOrThrow(
                                MediaStore.Images.Media.HEIGHT)));
                                */
        imageIfos.add(imageIfo);
      }
      // 关闭cursor
      cursor.close();
    }
    return imageIfos;
  }
}
