package jmuszka.apps.mariocalculator

import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import jmuszka.apps.mariocalculator.R
import jmuszka.apps.mariocalculator.databinding.ActivityMainBinding


class OverworldTheme : ComponentActivity() {

    lateinit var binding: ActivityMainBinding;
    var border = 10;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        var SIZE = getScreenWidth()/4;

        var btnPanel = findViewById<RelativeLayout>(R.id.buttonPanel);
        var params = btnPanel.layoutParams;
        params.width = SIZE*4;
        params.height = SIZE*6;
        btnPanel.layoutParams = params;

        var buttonStrings = arrayOf(
            "^", "ln", "(", ")",
            "sin", "cos", "tan", "AC",
            "1", "2", "3", "+",
            "4", "5", "6", "-",
            "7", "8", "9", "*",
            ".", "0", "=", "/"
        );

        var index = 0;
        for (i in 0..5) {
            for (j in 0..3) {
                var btn = createButton(j*SIZE,i*SIZE,SIZE,SIZE,buttonStrings[index]);
                if (btn.getText() == "^") btn.setBackgroundImage(R.drawable.exponentspritenew)
                else if (btn.getText() == "ln") btn.setBackgroundImage(R.drawable.lnspritenew);
                else if (btn.getText() == "(") btn.setBackgroundImage(R.drawable.openspritenew);
                else if (btn.getText() == ")") btn.setBackgroundImage(R.drawable.closedspritenew)
                else if (btn.getText() == "0") btn.setBackgroundImage(R.drawable.zerospritenew);
                else if (btn.getText() == "1") btn.setBackgroundImage(R.drawable.onespritenew);
                else if (btn.getText() == "2") btn.setBackgroundImage(R.drawable.twospritenew);
                else if (btn.getText() == "3") btn.setBackgroundImage(R.drawable.threespritenew);
                else if (btn.getText() == "4") btn.setBackgroundImage(R.drawable.fourspritenew);
                else if (btn.getText() == "5") btn.setBackgroundImage(R.drawable.fivespritenew);
                else if (btn.getText() == "6") btn.setBackgroundImage(R.drawable.sixspritenew);
                else if (btn.getText() == "7") btn.setBackgroundImage(R.drawable.sevenspritenew);
                else if (btn.getText() == "8") btn.setBackgroundImage(R.drawable.eightspritenew);
                else if (btn.getText() == "9") btn.setBackgroundImage(R.drawable.ninespritenew);
                else if (btn.getText() == "+") btn.setBackgroundImage(R.drawable.plus_sprite);
                else if (btn.getText() == "-") btn.setBackgroundImage(R.drawable.minus_sprite);
                else if (btn.getText() == "*") btn.setBackgroundImage(R.drawable.multiplication_sprite);
                else if (btn.getText() == "/") btn.setBackgroundImage(R.drawable.division_sprite);
                else if (btn.getText() == ".") btn.setBackgroundImage(R.drawable.pointspritenew);
                else if (btn.getText() == "=") btn.setBackgroundImage(R.drawable.equalsspritenew)
                else if (btn.getText() == "sin") btn.setBackgroundImage(R.drawable.sinspritenew);
                else if (btn.getText() == "cos") btn.setBackgroundImage(R.drawable.cosspritenew);
                else if (btn.getText() == "tan") btn.setBackgroundImage(R.drawable.tanspritenew);
                else if (btn.getText() == "AC") btn.setBackgroundImage(R.drawable.acspritenew);
                index++
            }
        }

//        //overworld
//        var owButton = findViewById<android.widget.Button>(R.id.overworld);
//        owButton.setOnClickListener{
//            val intent = Intent(this, OverworldTheme::class.java);
//            startActivity(intent);
//        };
//        //underground
//        var ugButton = findViewById<android.widget.Button>(R.id.underground);
//        ugButton.setOnClickListener{
//            val intent = Intent(this, UndergroundTheme::class.java);
//            startActivity(intent);
//        };
        //castle
//        var caButton = findViewById<android.widget.Button>(R.id.castle);
//        ugButton.setOnClickListener{
//            val intent = Intent(this, CastleTheme::class.java);
//            startActivity(intent);
//        };
        //underwater
//        var uwButton = findViewById<android.widget.Button>(R.id.underwater);
//        ugButton.setOnClickListener{
//            val intent = Intent(this, UnderwaterTheme::class.java);
//            startActivity(intent);
//        };

        var mp = MediaPlayer();
        mp.setDataSource(resources.openRawResourceFd(R.raw.ow_song));
        mp.prepareAsync();
        //mp.setOnPreparedListener {mp.start()};
        mp.setOnCompletionListener {mp.start()};
        mp.setScreenOnWhilePlaying(true)
        //setErrorListener (?)

        findViewById<LinearLayout>(R.id.themePanel).setOnLongClickListener{ event ->
            handleMusic(mp);
            true;
        };


    }

    fun handleMusic(mp: MediaPlayer) {
        if (mp.isPlaying) mp.pause();
        else mp.start();
    }

    fun createButton(x: Int, y: Int, width: Int, height: Int, textVal: String): Button {
        var btn = Button(this);
        
        btn.addButtonTo(binding.root.findViewById(R.id.buttonPanel));
        btn.setWidth(width-border);
        btn.setHeight(height-border);
        btn.setX(x+border/2);
        btn.setY(y+border/2);
        btn.setText(textVal);
        btn.initDisplay(findViewById(R.id.display))

        return btn;
    }

    private fun getScreenWidth(): Int {
        val sdk = Build.VERSION.SDK_INT;
        val outMetrics = DisplayMetrics();
        var width = 0;

        @RequiresApi(Build.VERSION_CODES.R)
        if (sdk >= 30) {
            val display = this.display;
            display?.getRealMetrics(outMetrics);
        }
        else {
            @Suppress("DEPRECATION")
            val display = windowManager.defaultDisplay;
            @Suppress("DEPRECATION")
            display.getMetrics(outMetrics);
        }

        width = outMetrics.widthPixels;
        return width;
    }

    private fun getScreenHeight(): Int {
        val sdk = Build.VERSION.SDK_INT;
        val outMetrics = DisplayMetrics();
        var height = 0;

        @RequiresApi(Build.VERSION_CODES.R)
        if (sdk >= 30) {
            val display = this.display;
            display?.getRealMetrics(outMetrics);
        }
        else {
            @Suppress("DEPRECATION")
            val display = windowManager.defaultDisplay;
            @Suppress("DEPRECATION")
            display.getMetrics(outMetrics);
        }

        height = outMetrics.heightPixels;
        return height;
    }
}