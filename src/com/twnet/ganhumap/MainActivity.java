package com.twnet.ganhumap;

import java.util.Map;

import com.google.android.gms.analytics.r;
import com.google.android.gms.appindexing.AndroidAppUri;
import com.google.android.gms.drive.realtime.internal.BeginCompoundOperationRequest;
import com.google.android.gms.internal.df;
import com.google.android.gms.maps.MapFragment;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity {

	private MapFragment map;
	private ReadyCallback readyCallback;
	private DialogFragment df;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			// startActivityForResult(new Intent(this, IntroActivity.class), 1);
			df = new DialogFragment() {
				public View onCreateView(LayoutInflater inflater,
						ViewGroup container, Bundle savedInstanceState) {
					// ObjectAnimator anim =
					// ObjectAnimator.ofFloat(findViewById(R.id.welcome_textview),
					// "Alpha", 0f,1f);
					// anim.setDuration(2000);
					// anim.start();
					return inflater.inflate(R.layout.activity_intro, container);
				};

			};
			df.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme);
			df.setCancelable(false);
			getFragmentManager()
					.beginTransaction()
					.add(df, "SPL_DIALOG")
					.setCustomAnimations(R.animator.fade_in,
							R.animator.fade_out).commit();

		}
		setContentView(R.layout.activity_main);

		ActionBar bar = getActionBar();
		bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		bar.setCustomView(R.layout.actiombar_cview);
		bar.setDisplayShowTitleEnabled(true);

		GoogleMapOptions options = new GoogleMapOptions();
		options.camera(new CameraPosition(new LatLng(23.75, 120.98),
				(float) 7.3, 0, 0));
		options.mapType(GoogleMap.MAP_TYPE_NORMAL);
		options.compassEnabled(true);
		options.zoomControlsEnabled(true);
		map = (MapFragment) MapFragment.newInstance(options);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, map)
					.commit();
			readyCallback = new ReadyCallback();
			map.getMapAsync(readyCallback);

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private class ReadyCallback implements OnMapReadyCallback {
		@Override
		public void onMapReady(GoogleMap googleMap) {

			if (df != null) {
				(new Handler()).postDelayed(new Runnable() {

					@Override
					public void run() {
						df.dismiss();
						Toast.makeText(getApplicationContext(), "已經可以開始幹話",
								Toast.LENGTH_LONG).show();
					}
				}, 6000);

			}
		}

		// /**
		// * A placeholder fragment containing a simple view.
		// */
		// public static class PlaceholderFragment extends MapFragment
		// implements
		// OnMapReadyCallback {
		//
		// public PlaceholderFragment() {
		// super();
		// }
		//
		// @Override
		// public void onCreate(Bundle savedInstanceState) {
		// // TODO Auto-generated method stub
		// super.onCreate(savedInstanceState);
		// }
		//
		// @Override
		// public View onCreateView(LayoutInflater inflater, ViewGroup
		// container,
		// Bundle savedInstanceState) {
		// // TODO Auto-generated method stub
		// return super.onCreateView(inflater, container, savedInstanceState);
		// }
		//
		// @Override
		// public void onMapReady(GoogleMap googleMap) {
		//
		// googleMap.addMarker(new MarkerOptions().position(new LatLng(0, 0))
		// .title("Marker"));
		//
		// }
		// }
	}
}
