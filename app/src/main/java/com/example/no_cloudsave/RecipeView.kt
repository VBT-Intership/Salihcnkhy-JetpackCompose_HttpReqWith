package com.example.no_cloudsave

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import android.transition.Transition
import android.widget.ImageSwitcher
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import androidx.ui.core.*
import androidx.ui.foundation.Box
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.geometry.Size
import androidx.ui.graphics.ImageAsset
import androidx.ui.graphics.asImageAsset
import androidx.ui.graphics.painter.ImagePainter
import androidx.ui.graphics.painter.Painter
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.res.imageResource
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.Dp
import androidx.ui.unit.TextUnit
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.example.no_cloudsave.ui.NocloudsaveTheme
import com.squareup.picasso.Picasso


@Composable
fun RecipeCard(recipe: Recipe,height : Double){

    val boxModifier = Modifier.height((height * 0.5).dp)
            .fillMaxWidth()
    val typography = MaterialTheme.typography

    Surface(shape = RoundedCornerShape(12.dp),elevation = 10.dp,modifier = Modifier.padding(top = 20.dp).padding(horizontal = 15.dp)) {
        Column(modifier = Modifier.padding(5.dp),horizontalGravity = Alignment.CenterHorizontally) {
            Box(modifier = boxModifier){

                val loadPictureState = loadPicture(recipe.image)
                if (loadPictureState is UiState.Success<Bitmap>)
                    Image(loadPictureState.data.asImageAsset(),contentScale = ContentScale.FillWidth,modifier = Modifier.clip(RoundedCornerShape(12.dp)))
                else
                    Text("Loading...")
            }
            Text(recipe.name,style = typography.h6,modifier = Modifier.padding(vertical = 10.dp))
            Text(recipe.definition,style = typography.body1,modifier = Modifier.padding(vertical = 5.dp))

        }
    }

}

@Composable
fun loadPicture(url: String): UiState<Bitmap> {
    var bitmapState: UiState<Bitmap> by state<UiState<Bitmap>> { UiState.Loading }

    Glide.with(ContextAmbient.current)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(
                resource: Bitmap,
                transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
            ) {
                bitmapState = UiState.Success(resource)

            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }
        })

    return bitmapState
}

