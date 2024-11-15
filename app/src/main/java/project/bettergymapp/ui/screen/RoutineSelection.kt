package project.bettergymapp.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.gestures.snapping.snapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import project.bettergymapp.R
import project.bettergymapp.data.viewmodel.RoutineViewModel

@Composable
fun RoutineSelection(
    viewModel: RoutineViewModel = viewModel(factory = RoutineViewModel.Factory)
) {
   val list = viewModel.list.collectAsStateWithLifecycle().value

    val padding_start_end = 10.dp;
    val lazyListState = rememberLazyListState()
    val snapFlingBehavior = rememberSnapFlingBehavior(lazyListState)
    val pagerState = rememberPagerState(pageCount = { list.size })

    val colors = listOf(
        colorResource(id = R.color.happyblue),
        colorResource(id = R.color.happyyellow),
        colorResource(id = R.color.happygreen),
        colorResource(id = R.color.happypink),
        colorResource(id = R.color.happyorange),
        colorResource(id = R.color.happypurple)
    )

    Column (modifier = Modifier.padding(start = padding_start_end, top = 20.dp,end = padding_start_end)){
        Text(text = stringResource(R.string.your_routines),
            color = Color.Black,
            modifier = Modifier.padding(top = 10.dp),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
        )

       LazyRow(modifier = Modifier.padding(top = 10.dp)
           .fillMaxWidth(),
           state = lazyListState,
           flingBehavior = snapFlingBehavior
       ) {
           items(list){
                val color = colors.random()
               RoutineCard(it.name, color,padding_start_end)
           }
       }
    }
}

@Composable
fun RoutineCard(
    routine: String,
    color: Color,
    padding_start_end: Dp
) {
    val box_padding = 8.dp
    Box(
        modifier = Modifier
            .padding(box_padding)
            .width(LocalConfiguration.current.screenWidthDp.dp-padding_start_end*2-box_padding*2)
            .height(60.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = routine,
            color = Color.White,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp // Larger font size
            ),
            modifier = Modifier.padding(8.dp)
        )
    }



}

