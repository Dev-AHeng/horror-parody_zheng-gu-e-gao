package xiaoheng.zgtest;

import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.media.*;
import android.os.*;
import android.provider.*;
import android.support.v7.app.*;
import android.support.v7.appcompat.*;
import android.view.*;
import java.io.*;

import android.support.v7.appcompat.R;


// 这是出于无聊写的，就是想整蛊一下我同学。
// 要设置壁纸的图片和要播放的音乐都在drawable这个文件夹，自己换了就行。不要玩得太过了，以免同学都没得做。
// 小亨  2019.1.5


public class MainActivity extends AppCompatActivity 
{
	private Thread thread;
	private MediaPlayer mp=null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);

		/**后台运行相当于home键**/
		moveTaskToBack(false);

		/**设置最大屏幕亮度**/
		try
		{  
			Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 255);  
		}  
		catch (Exception localException)
		{  
			localException.printStackTrace();  
		}


		/**设置壁纸**/
		WallpaperManager b= WallpaperManager.getInstance(this);
		Resources res = getResources();
		Bitmap c=BitmapFactory.decodeResource(res, R.drawable.image);
		b.getDesiredMinimumHeight();
		b.getDesiredMinimumWidth();
		try
		{
			b.setBitmap(c);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}


		/**设置最大音量**/
		AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		am.setStreamVolume(AudioManager.STREAM_MUSIC, am.getStreamMaxVolume(AudioManager.STREAM_MUSIC), AudioManager.FLAG_PLAY_SOUND);
		am.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL);


		/**后台运行播放音乐**/
		thread = new Thread(new Runnable()
			{ 
				@Override 
				public void run()
				{ 
					if (mp != null)
					{ 
						//释放资源 
						mp.release();
					} 
					mp = MediaPlayer.create(MainActivity.this, R.drawable.music); 
					mp.start(); 
				} 
			}); 
		//开启线程
		thread.start();


		/**震动**/
		Vibrator vibrator = (Vibrator)this.getSystemService(this.VIBRATOR_SERVICE);
		long[] patter = {0, 800,0,800};
		vibrator.vibrate(patter, 0);

    }

	/*后台了没用
	// 重写各种键
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == event.KEYCODE_BACK)
		{	
			//返回键 
			ld_yl();
		}
		if (keyCode == event.KEYCODE_HOME)
		{	
			//HOME键 
			ld_yl();
		}
		if (keyCode == event.KEYCODE_MENU)
		{	
			//菜单键 
			ld_yl();
		}
		if (keyCode == event.KEYCODE_VOLUME_DOWN)
		{	
			//音量下键 
			ld_yl();
		}
		if (keyCode == event.KEYCODE_VOLUME_UP)
		{	
			//音量上键 
			ld_yl();
		}
		if (keyCode == event.KEYCODE_POWER)
		{	
			//关机键 
			ld_yl();
		}
		return true;
	}

	public void ld_yl()
	{
		/**设置最大屏幕亮度**
		try
		{  
			Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 255);  
		}  
		catch (Exception localException)
		{  
			localException.printStackTrace();  
		}


		/**设置最大音量**
		AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		am.setStreamVolume(AudioManager.STREAM_MUSIC, am.getStreamMaxVolume(AudioManager.STREAM_MUSIC), AudioManager.FLAG_PLAY_SOUND);
		am.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL);
	}*/
}
