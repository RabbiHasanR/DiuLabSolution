# Title  
DiuLabSolution
# Description
It's a android application build with java using firebase.This android application has two type users.One authority and one student.Basically This android application make for University lab room problem prevention.User send report for any lab room problem of authority using this application.They send problem description or short video for problem.Then Authority receive report and take immediate action.This application save time for student and also versity authority.And student use up to date lab resouce in lab room.
# Features
### Report problem short video(10-20s) or short description
### notification
### OTP verification
### SignIn and SignUp

# mainfest.xml
``````````
  <uses-permission android:name="android.permission.INTERNET" />
 <service android:name=".service.MyFirebaseMessagingService">
            <intent-filter>
                <action android:name="com.google.firebase.MESSAGING_EVENT" />
            </intent-filter>
        </service>

``````````
# build.gradle(App)
````````
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    //noinspection GradleCompatible
    implementation 'com.android.support:design:27.1.1'
    implementation 'com.android.support:appcompat-v7:27.1.1'
    implementation 'com.android.support:recyclerview-v7:27.1.1'
    implementation 'com.android.support.constraint:constraint-layout:1.1.2'
    implementation 'com.google.firebase:firebase-core:16.0.1'
    implementation 'com.google.firebase:firebase-database:16.0.1'
    implementation 'com.google.firebase:firebase-storage:16.0.1'
    implementation 'com.google.firebase:firebase-auth:16.0.2'
    implementation 'com.google.firebase:firebase-firestore:17.0.4'
    implementation 'com.google.firebase:firebase-messaging:17.1.0'
    implementation 'de.hdodenhof:circleimageview:2.2.0'
    implementation 'com.android.support:support-v4:27.1.1'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'

    implementation 'com.github.bumptech.glide:glide:4.7.1'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.7.1'
}
apply plugin: 'com.google.gms.google-services'
````````
