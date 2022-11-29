# Rewaudio-sdk
This is an audio ad unit which helps publishers add audio ads in their Aps. Audio has a 5-10x more value than the banner Ads. This creates a great opportunity for the advertisers to gernerate higher ARPU.
We have developed Rewaudio SDK for Android OS. This SDK can be used by publishers to render audio banners in their applications. [Demo Video](https://drive.google.com/file/d/1KpltJnMTtqgzm8qsgbHYkIY5y5eK0Yb1/view) to see Rewaudio audio ads in action 
## Integrate SDK
### Gradle Dependencies
Add the following in the dependencies section to your project-level build.gradle file.
>
      allprojects {
        repositories {
            google()
            mavenCentral() // Add this
            maven {
                url "https://jitpack.io"
            }
        }
    }
 
Add the following in the android section to your app-level build.gradle file.
>
        android {
            compileOptions {
                sourceCompatibility JavaVersion.VERSION_1_8
                targetCompatibility JavaVersion.VERSION_1_8
            }
        }
 
Add the following in the dependencies section to your app-level build.gradle file 
>
    dependencies {
        implementation(project(path: ":mRaid"))
        ...
        ...
        ...
    }
 
### Integrating an Ad Unit
#### Java
Declaring ViewStub in XML

    <ViewStub>
     android:id="@+id/ads"
     android:layout_width="match_parent"
     android:layout_height="wrap_content"
     android:layout_alignParentBottom="true"
     android:layout_centerHorizontal="true"
     android:elevation="2dp"
     android:orientation="vertical">
    </ViewStub>
  
Declaring Adview dynamically

    ViewStub ads;
    MraidAds mraidAds;
    @Override
    protected void onCreate(Bundle bundle) {
      ads = findViewById(R.id.ads);
      mraidAds = new MraidAds(this, ads);
      mraidAds.loadRAds();
    }       
    public void onResume() {
      super.onResume();
      if (mraidAds != null)
        mraidAds.resumeAudio();
    }
    @Override
    public void onPause() {
      super.onPause();  
      if (mraidAds != null)
        mraidAds.pauseAudio();
    }

#### Kotlin

    var ads: ViewStub? = null
    var mraidAds: MraidAds? = null
    @Override
    protected void onCreate(Bundle bundle) {
      ads = findViewById(R.id.ads)
      mraidAds = MraidAds(this, ads)
      mraidAds.loadRAds()
    }    
    override fun onPause() {
      super.onPause()
      if (mraidAds != null) mraidAds.pauseAudio()
    }
    override fun onResume() {
      super.onResume()
      if (mraidAds != null) mraidAds.resumeAudio()
    }

### Add Google Publisher id , Google adspot id , Rewaudio Id 
    public class  AppConstant {
      public static  String google_Id  = "abcxyz"; //replace this with your google network id
      public static  String publisher_id  = "98401SDFsdf"; //replace with your Rewaudio Publisher id
      public static  String adspot_id  = "sdf324sd"; //replace with ur Google Ad Spot id
    }

## Mediation 
Rewaudio SDK has been made as a mediation layer where the first ad request is made to the Rewaudio Ad server for direct ads and only if there are no direct ads the calls are made to Other Ad server for Audio ads.

The Rewaudio SDK and server talks using standard json bid request and bid response. The bid request and bid response are as per openRTB standards. Once the SDK receives the Bid response it takes care of the rendering of image as well as playing of audio. This is natively developed by the Rewaudio team.

### IMA SDK
Rewaudio SDK also has IMA SDK integrated inside it which is used to get indirect audio demand from Google, in case there are no direct ads. IMA SDK will make the call to Google Ad Server, receive the VAST response and take care of the rendering.
Example [Audio VAST Tag URL](https://pubads.g.doubleclick.net/gampad/ads?iu=/21775744923/external/audio-preroll&ad_type=audio&sz=1x1&ciu_szs=300x250&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator=)

#### Settings in GAM server
Login with your credentials in [Google Ad Manager](https://admanager.google.com/)

##### Step 1: Create a new Ad Unit in GAM. 

Navigate to Inventory>>Ad units.

Click New ad unit. Enter appropriate name and code. Choose Size mode Fixed. Master size as Audio and companion size as 320x50.
![GAM Create New Ad Unit](https://lh4.googleusercontent.com/-6dXbnLd2IdqIb035eYBK0c80FngYiH4lqMEp4qVhReiBYHqgwqSiMlFhUqeqw3i9zA=w2400)

##### Step 2: Add new order for Rewaudio

Navigate to Delivery>>Orders. 
![GAM Create New Order](https://lh5.googleusercontent.com/tgdqXH7MaSXngdW-VHkBaIJyLz5GVPrSn6UYQFROoMTbjJrOTEP2x-W3u0qeSKYmt_Q=w2400)
Create new company for Rewaudio
![GAM Create New Company](https://lh6.googleusercontent.com/uQA31Kw6Um3fueqJ-I_RNfEE6PVbXMONyBzaFO3_8TKkDo8xo3Nh7-X_1fjnIkRz6tU=w2400)
Enter your order information in the appropriate fields and Save the order
![Save Order](https://lh4.googleusercontent.com/LYrzeXonKzfsddUssD3tGzLw63_rI3gMaSidFmV6ajyKkitQQ3ZyGE1CZaJcFR5Y-co=w2400)

##### Step 3: Create new Line Item

Click Add new line item from the above created orders page. Choose the type as Audio/Video.
![Choose Line Item Type](https://lh6.googleusercontent.com/C0z4x51pZB7yLxByVjj5mc87jLvKEclq86_sTXkv2TQSy7Gjiid4DW2W2W-CQ9iTMlA=w2400)
Enter name and choose Line Item Type as standard with priority Normal. Choose creative size as audio and banner size as 320x50
![Enter Required fields](https://lh6.googleusercontent.com/uCiNb8w9Cz_in2E5KbXQAJMv0jcH2i_r7ldCoo8SiMcazW555n-NkBFXrtYI-wSrOss=w2400)
Enter the start date as immediate and end date 1 year from start date. Enter the quantity as 100Mn and rate as discussed.
![enter start end date and goal](https://lh6.googleusercontent.com/RWcnWJLEX-kDc6jU6fPpkxbrvlFPd_cOU7ewj5i-HWDjPzuIFYjDHfnW1hI1k6r6frY=w2400)
Choose the Ad unit created in Step 1 in the ad unit targeting 
![target the newly create ad unit](https://lh4.googleusercontent.com/3lwD0bpDR55oxY7rojco8uyvhojfEcdanDEW_FiJ25BtNqVXfhIzs7aV362mUW1MWSw=w2400)

Save the Line Item

##### Step 4: Add New Creative

Click Add Creative on the Line Item created above

Choose the type as Redirect
![Creative Type](https://lh4.googleusercontent.com/DqdTlSxi7prY6qaOLGxipyE3binVtONx0Yc87ku8KfrYXpB06COmqk9hB1PsXF4ZqDw=w2400)

Enter the VAST tag shared with you by the team
![VAST Tag](https://lh5.googleusercontent.com/eRh0piE3Gn2_4cwXe7xuW6fAnw0j0KS5XijWu4ElHLtBTtbLgOaaQMsafxBhrgfOpOM=w2400)

Save the creative.

##### Step 5: Update ads.txt in your server

Add the ads.txt shared by Rewaudio team in your existing ads.txt on your server.
