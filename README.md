# Arc-Progress

[![](https://jitpack.io/v/shahram-noorani/arc-progress-android-library.svg)](https://jitpack.io/#shahram-noorani/arc-progress-android-library)


easy and very light library used to ArcProgress view
<br/>
<br/>
<img src="https://raw.githubusercontent.com/shahram-noorani/arc-progress-android-library/master/screenshot.jpg" width="350">

## How to download
### Gradle: 
add this line to your module build.gradle repositories block:
    
    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	
### Gradle: 
add this line to your module build.gradle dependecies block:

	 implementation 'com.github.shahram-noorani:arc-progress-android-library:1.0'
	 
## How use this lib
you can attach ArcProgress view in xml layout like this:
```xml
  
       <com.shahram.noorani.arcprogress.ArcProgress
            android:layout_width="match_parent"
            android:layout_height="200dp"
            android:id="@+id/arcProgress" />

```    
## custom attr view
```xml

		app:arc_background_color="#FFFFFF"
		app:arc_empty_color="#57ca87"
		app:arc_fill_color="#f26c4f"
		app:arc_max="100"
		app:arc_min="1"
		app:arc_progress="50"
		app:arc_image_degree="@drawable/arrow"
		app:arc_min_max_color="#F00"
		app:arc_min_max_text_size="15dp"
		app:arc_progress_text_color="#FFF"
		app:arc_progress_text_size="22dp"
```
## Optional function
```java

arcProgress=findViewById(R.id.arc);
        arcProgress.setMax(180);
        arcProgress.setmin(50);
        arcProgress.setProgress(10);
        arcProgress.setEmptyColor(Color.parseColor("#57ca87"));
        arcProgress.setFillColor(Color.parseColor("#f26c4f"));
        arcProgress.setBackgroundCircleColor(Color.WHITE);
        arcProgress.setTextColor(Color.BLACK);
        arcProgress.setTextColorCurentProgress(Color.BLACK);
        arcProgress.setTextSizeMinMax(20);
        arcProgress.setTextSizeCurentProgress(40);
	arcProgress.setTextTypface(typeface);
        arcProgress.setNotSupportAndroidVersionListner(new NotSupportListner() {
            @Override
            public void notSupport() {

            }
        });

```

## Author
Shahram Noorani

email: gogo19a@gmail.com

github: https://github.com/shahram-noorani
