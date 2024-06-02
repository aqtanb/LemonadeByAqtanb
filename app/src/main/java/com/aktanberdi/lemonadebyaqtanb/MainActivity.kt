package com.aktanberdi.lemonadebyaqtanb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aktanberdi.lemonadebyaqtanb.ui.theme.LemonadeByAqtanbTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeByAqtanbTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(R.color.white)
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeUI(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
        )
}

@Composable
fun LemonadeUI(modifier: Modifier = Modifier) {
    var page by remember{ mutableIntStateOf(1) }
    var clicks by remember { mutableIntStateOf((2..4).random())}
    var imageResource = when (page) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    var description = when (page) {
        1 -> R.string.lemonTreeDesc
        2 -> R.string.lemonDesc
        3 -> R.string.glassOfLemonadeDesc
        else -> R.string.emptyGlassDesc
    }
    var hint = when (page) {
        1 -> R.string.lemonTree
        2 -> R.string.lemon
        3 -> R.string.glassOfLemonade
        else -> R.string.emptyGlass
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(R.color.white)
    ) {
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(R.string.app_name),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .background(colorResource(R.color.yellow)),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(painter = painterResource(imageResource),
            contentDescription = stringResource(description),
            modifier = Modifier
                .width(260.dp)
                .height(250.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(colorResource(R.color.accent))
                .clickable {
                    if (page == 2) {
                        if (clicks > 0) {
                            clicks -= 1
                        }
                        if (clicks == 0) {
                            page++
                            clicks = (2..4).random()
                        }
                    } else {
                        if (page < 4) page++ else page = 1
                    }
                }

        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource(hint), modifier = Modifier, fontSize = 18.sp)
        Spacer(modifier = Modifier.weight(1f))
    }
    }


}