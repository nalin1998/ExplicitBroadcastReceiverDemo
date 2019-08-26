package com.example.explicitbroadcastreceiverdemo

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            //one way ....
            //val intent : Intent = Intent(this , BroadcastReceiverDemo::class.java)
            //one more way to do it would be...
            /*val intent : Intent = Intent ()
            intent.setClass(this , BroadcastReceiverDemo::class.java)*/
            //one more way is .....
            //var componentName : ComponentName = ComponentName(this , BroadcastReceiverDemo::class.java)

            //now to trigger broadcast receiver in other app
            /*var componentName : ComponentName = ComponentName( "com.example.explicitbroadcastrec2" , "com.example.explicitbroadcastrec2.BroadcastReceiverDemo")
            intent.component = componentName
            sendBroadcast(intent)*/

            //similarly we can do
            /*val intent : Intent = Intent()
            intent.setClass("com.example.explicitbroadcastrec2" , "com.example.explicitbroadcastrec2.BroadcastReceiverDemo")*/

            //one more kinda implicit way to do it is ...
            /*val intent : Intent = Intent("com.example.explicitbroadcastreceiverdemo.SAMPLE_ACTION")
            intent.setPackage("com.example.explicitbroadcastrec2")
            sendBroadcast(intent)*/

            //one way to trigger broadcast receivers in all apps having intent filters related to our action defined here is ....
            val intent : Intent = Intent("com.example.explicitbroadcastreceiverdemo.SAMPLE_ACTION")
            val packageManager : PackageManager = packageManager
            val infoList : List<ResolveInfo> = packageManager.queryBroadcastReceivers(intent , 0)

            for(resolveInfo in infoList)  {
                val componentName = ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name)
                intent.component = componentName
                sendBroadcast(intent)
            }



        }
    }
}
