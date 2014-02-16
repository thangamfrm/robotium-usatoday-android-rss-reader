package com.thangamfrm.android.usatodayrssreader.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Adapter;
import android.widget.ListView;

import com.robotium.solo.Solo;
import com.thangamfrm.android.usatodayrssreader.SectionDetailActivity;
import com.thangamfrm.android.usatodayrssreader.SectionDetailFragment;
import com.thangamfrm.android.usatodayrssreader.SectionListActivity;
import com.thangamfrm.android.usatodayrssreader.content.USATodayNewsContent.DummyItem;

public class TestSectionListActivity extends ActivityInstrumentationTestCase2<SectionListActivity> {

	private Solo solo;

	public TestSectionListActivity() {
		super(SectionListActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}

	public void testNewsSection() {
		assertEquals("Activity Tile didn't match!", "USAToday RSS Reader", solo.getCurrentActivity().getTitle());
		ListView listView = (ListView) solo.getCurrentViews().get(8);
		Adapter adapter = listView.getAdapter();
		assertEquals("Incorrect News Section Count!", 17, adapter.getCount());
		solo.clickOnText(((DummyItem)adapter.getItem(2)).content);
		solo.waitForFragmentByTag(SectionDetailFragment.ARG_ITEM_ID);
		solo.waitForDialogToClose();
		solo.waitForActivity(SectionDetailActivity.class);
		assertEquals("Activity Tile didn't match!", "Travel", solo.getCurrentActivity().getTitle());
	}
	
	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

}
 