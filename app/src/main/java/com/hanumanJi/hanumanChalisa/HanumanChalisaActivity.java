package com.hanumanJi.hanumanChalisa;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hanumanJi.jai.NormalChalisa;
import com.hanumanJi.jai.R;

@TargetApi(Build.VERSION_CODES.HONEYCOMB) @SuppressLint("NewApi") public class HanumanChalisaActivity extends Activity 
{
	
	ViewPager viewPager;
	 MyPagerAdapter myPagerAdapter;
		public TextView duration2;
		 Button btnPlay;
			Button btnLyrics;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hanuman_chalisa);
		
		 btnPlay = (Button) findViewById(R.id.playHanumanChalisa);
		 btnLyrics = (Button) findViewById(R.id.lyricsHC);
		 viewPager = (ViewPager) findViewById(R.id.pagerMeaning);
		    myPagerAdapter = new MyPagerAdapter();
		    viewPager.setAdapter(myPagerAdapter);
		    
		   
		btnLyrics.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(HanumanChalisaActivity.this,HClyrics.class);
				startActivity(i);	
			}
		});
		
		btnPlay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
//				
//		if(!mediaPlayer2.isPlaying())
//		{				
//				 	AlertDialog alertDialog = new AlertDialog.Builder(HanumanChalisaActivity.this).create(); //Read Update
//				    alertDialog.setTitle("Speed");
//				    alertDialog.setMessage("Choose Hanuman Chalisa speed");
//				   
//				    alertDialog.setButton( Dialog.BUTTON_NEGATIVE, "NORMAL", new DialogInterface.OnClickListener()   
//				    {
//				      public void onClick(DialogInterface dialog, int which) 
//				      {

				    	  Intent i = new Intent(HanumanChalisaActivity.this,NormalChalisa.class);
				    	  startActivity(i);
				  		
//				      }});
//				    
//				    alertDialog.setButton(Dialog.BUTTON_POSITIVE, "FAST", new DialogInterface.OnClickListener() {
//					       public void onClick(DialogInterface dialog, int which) {
//					    	   Intent i = new Intent(HanumanChalisaActivity.this,FastChalisa.class);
//						    	  startActivity(i);
//						  		
//					       }});
//
//				    alertDialog.show();  //<-- See This!

		 }});	
		
			
	}

	
	
	private class MyPagerAdapter extends PagerAdapter{
		  
		  int NumberOfPages = 12;
		  
		  String[] res = {"श्री गुरु चरण सरोज ऱज , निज मन मुकुर सुधर |\n वर्णों रघुबीर विमल जसु , जो दयाकू फल चार ||\n\n\n गुरु से प्रार्थना करता हूँ ,श्रेष्ट गुरु के पैर जो कमल की तरह कोमल है, जो मन के भ्रम को, चंचल्लता को सुधरता है, उस गुरु के पेरो  की मे पूजा करता हूँ, मैं रघुनाथ जी के उज्जवल (विमल) जस का वंदन करता हूँ,जो दे सकते है फल चार - अर्थ, धन, काम, मोक्छ ||",
		   "बुद्धिहीन तनु जानिके, सुमिरौं पवन कुमार ।\nबल बुद्धिविद्या देहु मोहिं, हरहु कलेश विकार ॥\n\n\n मै अपने आप को बुद्धि हीन समझ के, हनुमान जी को याद करता हूँ,बल बुद्धि और विद्या दीजिये, जो भी मेरी तकलीफ है उसे आप दूर कीजिये ||",
		   "जय हनुमान ज्ञान गुण सागर ।\nजै कपीस तिहुँलोक उजागर ॥\n\n\nहनुमान जी आप ज्ञान के सागर है (आपके पास बहुत ज्ञान है), कपीस (बंदरो के नायक है) तीनो लोक मै आप प्रसिद्ध है  और कल्याण करने वाले है ||",
		  "रामदूत अतुलित बलधामा ।\nअंजनि-पुत्र पवन-सुत नामा ॥\n\n\nआप राम के दूत है और आपके पास इतना ज्ञान है जिसकी कल्पना भी नहीं की जा सकती, आप ( पवन सूत ) अंजनी मैया के पुत्र है ||",
		   "महाबीर बिक्रम बजरंगी ।\nकुमति निवार सुमति के संगी ॥\n\n\nआपका शरीर बज्र की तरह है, आपको याद करने से कष्ट दूर होते है, और आनंद की अनुभूति होती है ||"
		  ,"कंचन बरण बिराज सुबेशा ।\nकानन कुंडल कुंचित केशा ॥\n\n\nआपका शरीर सोने के तरह चमकता है, आपके कान मै कुंडल है और आपके केश (बाल) घुंगराले है ||" ,
		  "हाथ बज्र औ ध्वजा बिराजै ।\nकाँधे मूँज जनेऊ साजै ॥\n\n\nएक हाथ मै बज्र( गदा) है और एक हाथ मै झंडा (धवज ) है, आपके कंधे मै मूंग का जनेऊ है ||",
		  "शंकर-सुवन केशरी-नन्दन ।\nतेज प्रताप महा जग-वंदन ॥\n\n\nजो शंकर के रूद्र अवतार है और  केसरी (हनुमान जी के पिता) के सुपुत्र है, वैसे तेज प्रताप को जग (संसार) वंदन (पूजा) करता है ||"
		  ,"विद्यावान गुणी अति चातुर ।\nराम काज करिबे को आतुर ॥\n\n\nआप विदया के भंडार है और गुणों से युक्त है, श्री राम के काम को करने को हमेशा उत्सुक रहते है ||",
		  "प्रभु चरित्र सुनिबे को रसिया ।\n रामलषण सीता मन बसिया ॥\n\n\nजो भगवन श्री राम की कथा को सुनने मे मग्न हो जाते है  और जिनके हृदय मे राम, लक्ष्मण सीता बसे है ||"
		  ,"सूक्ष्म रूपधरि सियहिं दिखावा ।\nविकट रूप धरि लंक जरावा ॥\n\n\nछोटा रूप बनाके माता सीता से मिले ( अशोक वाटिका मे), और बड़ा रूप बनाके लंका को जला दिया ||",
		  "भीम रूप धरि असुर सँहारे ।\nरामचन्द्र के काज सँवारे ॥\n\n\nजो भीम के रूप को धारण करके असुरो का विनास करने वाले है और राम के हर काम को अच्छी तरह पूरा करते है ||",
		  "लाय सजीवन लखन जियाये ।\nश्री रघुबीर हरषि उर लाये ॥\n\n\nवैसे भगवन को मे नमन करता हूँ  जो युद्ध के दोरान संजीवनी बूटी लाकर के लक्ष्मण जी को मोर्चा से नया जीवन दिया  और श्री राम को हर्षित कर दिया ||"};
		  int[] backgroundcolor = { 
		   0xFFCC0010,
		   0xFFFFCC00,
		   0xFF669900,
		   0xFF000099,
		   0xFF996633,
		   0xFFCC0010,
		   0xFFFFCC00,
		   0xFF669900,
		   0xFF000099,
		   0xFF996633,
		   0xFFCC0010,
		   0xFFFFCC00};

		  @Override
		  public int getCount() {
		   return NumberOfPages;
		  }

		  @Override
		  public boolean isViewFromObject(View view, Object object) {
		   return view == object;
		  }

		  @Override
		  public Object instantiateItem(ViewGroup container, int position) {
		   
		      
		      TextView textView = new TextView(HanumanChalisaActivity.this);
		      textView.setTextColor(Color.WHITE);
		      textView.setTextSize(10);
		     
		      textView.setText("Meaning #"+String.valueOf(position));
		      LayoutParams textParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		      textParams.gravity=Gravity.BOTTOM;
				      textView.setLayoutParams(textParams);
		      
		      final TextView factsText = new TextView(HanumanChalisaActivity.this);
		      factsText.setSingleLine(false);
		      factsText.setText(res[position]);
		      LayoutParams factsTextParams = new LayoutParams(
		        LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		      factsText.setLayoutParams(factsTextParams);
		      factsText.setTextColor(Color.WHITE);
		      factsText.setTextSize(16);
		      factsText.setPadding(5, 5, 5, 5);
		      factsText.setGravity(Gravity.CENTER);
		      factsText.setTypeface(Typeface.DEFAULT_BOLD);
		      LinearLayout layout = new LinearLayout(HanumanChalisaActivity.this);
		      layout.setOrientation(LinearLayout.VERTICAL);
		      LayoutParams layoutParams = new LayoutParams(
		        LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		      layout.setBackgroundColor(backgroundcolor[position]);
		      btnPlay.setBackgroundColor(backgroundcolor[position]);
		      btnLyrics.setBackgroundColor(backgroundcolor[position]);
		      layout.setLayoutParams(layoutParams);
		      layout.addView(textView);
		      layout.addView(factsText);
		      
		      final int page = position;
		      layout.setOnClickListener(new OnClickListener(){

		    @Override
		    public void onClick(View v) 
		    {
		    	switch (page)
		    	{
				case 0:
					factsText.setText("Shreeguru charana saroja raja nija mana mukura sudhaari \n baranau raghubara bimala jasu jo daayak phala chaari \n\n\"Cleansing the mirror of my mind with the dust from the Lotus-feet of Divine Guru, I describe the unblemished glory of Lord Rama, which bestows four fruits of Righteousness,  Wealth, Pleasure and Liberation.\" ");
					break;
				case 1:
					factsText.setText("Buddhiheena tanu jaanikai sumirau pavanakumāra\nBal buddhi bidyā dehu mohi harahu kalesa bikāra\n\n\"Considering this person as intelligence less, I remember Lord Hanuman. Give me strength, intelligence and knowledge, cure my body ailments and mental imperfections\"");
					break;
				case 2:
					factsText.setText("Jaya hanumāna Jnaana guna saagara\nJaya kapeesha tihu loka ujaagara\n\n\"Victory to Hanuman who is the ocean of Wisdom and Virtues,  Victory to the king of Monkeys who is illuminating three worlds\"");
					break;
				case 3:
					factsText.setText("Rāma dūta atulita bala dhāmā\nAnjani putra pavanasuta nāmā\n\n\"You are the messenger of Rama (to Sita), You are the abode of incomparable power. You are also called by the names of 'Anjani Putra' (Son of Anjana) and 'Pavana suta' (son of wind god)\"");
					break;
				case 4:
					factsText.setText("Mahābīra bikrama bajarangī।\nkumati nivāra sumati ke sangī\n\n\"Oh mighty valorous one, of terrific deeds whose body organs are as strong as Diamond (or the weapon of God Indra). Cure my bad mind oh companion of those with pure (good) mind\"");
					break;
				case 5:
					factsText.setText("Kaanchana barana birāja subesā\nkānana kundala kunchita keshā\n\n\"You are golden colored, you are shining in your beautiful attire. You have beautiful ear-rings in your ear and curly hairs\"");
					break;
				case 6:
					factsText.setText("Hātha bajra au dhvajā birājai\nkāndhe mūnji janeū sājai\n\n\"Vajrayudha (mace) and flag are shining in your hand. Sacred thread made of Munja grass adorns your shoulder\"");
					break;
				case 7:
					factsText.setText("Shankara suvana kesarī nandana\nTeja pratāpa mahā jaga bandana\n\n\"O partial incarnation of Lord shiva, giver of joy to King Kesari. Your great majesty is revered by the whole world\"");
					break;
				case 8:
					factsText.setText("Bidyāvāna gunī ati chātura।\nRāma kāja karibe ko ātura\n\n\"Oh one learned in all Vidyas, one full of virtues, Very clever. You are always eager to do Rama's tasks\"");
					break;
				case 9:
					factsText.setText("Prabhu charitra sunibe ko rasiyā\nrāma lakhana sītā mana basiyā\n\n\"You enjoy listening to Lord Rama's story; Lord Rama, Lakshman and Sita reside in your heart\"");
					break;
				case 10:
					factsText.setText("Sūkshma rūpa dhari siyahi dikhāvā\nbikata rūpa dhari lanka jarāvā\n\n\"Assuming the smallest form you saw (visited) Sita. Assuming the gigantic form you burnt down the Lanka\"");
					break;
				case 11:
					factsText.setText("Bhīma rūpa dhari asura samhāre\nrāmachandra ke kāja samvāre\n\n\"Assuming a terrible form you slayed demons. You made Lord Rama's works easier\"");
					break;
				case 12:
					factsText.setText("lāya sanjīvani lakhana jiyāe\nshrī raghubīra harashi ura lāye\n\n\"You brought Sanjeevini mountain to save Lakshmana's Life. Lord Rama embraced you in joy\"");
					break;
				
				default:
					break;
				}
		     //Toast.makeText(HanumanChalisaActivity.this,"Fact " + page + " clicked",Toast.LENGTH_SHORT).show();
		    }});
		      
		      container.addView(layout);
		      return layout;
		  }

		  @Override
		  public void destroyItem(ViewGroup container, int position, Object object) {
		   container.removeView((LinearLayout)object);
		  }

		 }

}
